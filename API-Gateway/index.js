require("dotenv-safe").config();
const jwt = require("jsonwebtoken");
const http = require("http");
const express = require("express");
const cors = require("cors");
const cookieParser = require("cookie-parser");
const bodyParser = require("body-parser");
const logger = require("morgan");
const helmet = require("helmet");
const axios = require("axios");

const { authServiceProxy } = require("./proxy/auth-service");
const { contasServiceProxy } = require("./proxy/contas-service");

const app = express();

// config app
app.use(cors({
  origin: 'http://localhost:4200'
}));
app.use( bodyParser.urlencoded({ extended: false}))
app.use( bodyParser.json() )
app.use(logger('dev'));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false}));
app.use(cookieParser());

// API Gateway
function verifyJWT(req, res, next) {
    const token = req.headers['x-access-token'];
    console.log({token});
    if (!token)
        return res.status(401).json({ auth: false, message: 'Token não fornecido.' });
    jwt.verify(token, process.env.SECRET, function(err, decoded) {
        if (err) {
            console.log('Erro: ', err);
            return res.status(500).json({ auth: false, message: 'Falha ao autenticar o token.'});
        }

        // se tudo estiver ok, salva no request para uso posterior
        req.userId = decoded.id;

        console.log('user logado', req.userId);

        next();
    });
}

// auth-service
app.post('/login', (req, res, next) => {
    authServiceProxy(req, res, next);
});
app.post('/logout', (req, res, next) => {
    res.json({ auth: false, token: null });
});

// contas-service
app.get('/contas/:numero', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
});
app.get('/operacoes/:numero', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
});
app.post('/operacoes/:numero/deposito', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
});
app.post('/operacoes/:numero/saque', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
});
app.post('/operacoes/:numero/transferencia', verifyJWT, (req, res, next) => {
    contasServiceProxy(req, res, next);
});



// Cria o servidor na porta 3000
var server = http.createServer(app);
server.listen(3000);
console.log('API Gateway running!');
