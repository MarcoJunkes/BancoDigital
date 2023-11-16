/*require("dotenv-safe").config();
const jwt = require('jsonwebtoken');
var http = require('http');
const express = require('express')
const httpProxy = require('express-http-proxy')
const app = express()
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser')
var logger = require('morgan');
const helmet = require('helmet');
const cors = require('cors');
*/

require("dotenv-safe").config();
const jwt = require("jsonwebtoken");
const http = require("http");
const express = require("express");
const httpProxy = require("express-http-proxy");
const cors = require("cors");
const app = express();
const cookieParser = require("cookie-parser");
const bodyParser = require("body-parser");
const logger = require("morgan");
const helmet = require("helmet");
const axios = require("axios");


app.use(cors({
  origin: 'http://localhost:4200'
}));

var urlencodedParser = bodyParser.urlencoded({ extended: false });

// parse application/x-www-form-urlencoded
app.use( bodyParser.urlencoded({ extended: false}))

// parse application/json
app.use( bodyParser.json() )

const clientesServiceProxy = httpProxy('http://172.18.0.7:5001');
const contasServiceProxy = httpProxy('http://localhost:5001');
const gerentesServiceProxy = httpProxy('http://172.18.0.6:3100');

const authServiceProxy = httpProxy('http://172.19.0.8:8080', {
    proxyReqBodyDecorator: function(bodyContent, srcReq) {
        try {
            retBody = {};
            retBody.email = bodyContent.email; // retBody.email a variável depois do ponto precisa ser igual ao REST
            retBody.senha = bodyContent.senha; // bodyContent.email a variável depois do ponto precisa ser igual ao que for declarado no Postman
            bodyContent = retBody;
            // console.log(retBody);
        }
        catch(e) {
            console.log('- ERRO: ' + e);
        }
        return bodyContent;
    },
    proxyReqOptDecorator: function(proxyReqOpts, srcReq) {
        proxyReqOpts.headers['Content-Type'] = 'application/json';
        proxyReqOpts.method = 'POST';
        return proxyReqOpts;
    },
    userResDecorator: function(proxyRes, proxyResData, userReq, userRes) {
        console.log(proxyRes.statusCode);
        if (proxyRes.statusCode == 200) {
            var str = Buffer.from(proxyResData).toString('utf-8');
            // var str = proxyResData.toString()
            // console.log(str)
            var objBody = JSON.parse(str)
            const id = objBody.id
            const token = jwt.sign({ id }, process.env.SECRET, {
                expiresIn: 3600 // expira em 1 hora
            });
            console.log(token)
            userRes.status(200);
            return { auth: true, token: token, data: objBody };
        }
        else {
            userRes.status(401);
            return {message: 'Login inválido!'};
        }
    }    
}); 

function verifyJWT(req, res, next){
    const token = req.headers['x-access-token'];
    console.log('Token', token);
    if (!token)
        return res.status(401).json({ auth: false, message: 'Token não fornecido.' });
    jwt.verify(token, process.env.SECRET, function(err, decoded) {
        if (err) {
            console.log('Erro: ', err);
            return res.status(500).json({ auth: false, message: 'Falha ao autenticar o token.'});
        }

        // se tudo estiver ok, salva no request para uso posterior
        req.userId = decoded.id;
        next();
    });
}

// API Gateway
app.post('/login', (req, res, next) => {
    authServiceProxy(req, res, next);
})

app.post('/logout', function(req, res) {
    res.json({ auth: false, token: null });
})

// Requisições aos serviços, já autenticados
app.get('/clientes'/*, verifyJWT*/, (req, res, next) => {
    clientesServiceProxy(req, res, next);
})

app.get('/contas', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
})

app.get('/gerentes'/*, verifyJWT*/, (req, res, next) => {
    gerentesServiceProxy(req, res, next);
})

app.post('/gerentes', verifyJWT, (req, res, next) => {
    gerentesServiceProxy(req, res, next);
})

// Configurações do app
app.use(logger('dev'));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false}));
app.use(cookieParser());

// Cria o servidor na porta 3000
var server = http.createServer(app);
server.listen(3000);
console.log('API Gateway running!');
