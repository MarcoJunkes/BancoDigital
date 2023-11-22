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

const { authServiceProxy, autoCadastroServiceProxy } = require("./proxy/auth-service");
const { clientesGetServiceProxy } = require("./proxy/clientes-service");
const { contasServiceProxy } = require("./proxy/contas-service");
const { gerentesGetServiceProxy, gerentesPostServiceProxy, gerentesPutServiceProxy, gerentesDeleteServiceProxy } = require("./proxy/gerentes-service");

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
    if (!token)
        return res.status(401).json({ auth: false, message: 'Token não fornecido.' });
    jwt.verify(token, process.env.SECRET, function(err, decoded) {
        if (err) {
            return res.status(401).json({ auth: false, message: 'Falha ao autenticar o token.'});
        }
        // se tudo estiver ok, salva no request para uso posterior
        req.userId = decoded.id;
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
app.post('/autocadastro', (req, res, next) => {
    autoCadastroServiceProxy(req, res, next);
});

// clietnes-service
app.get('/clientes', verifyJWT, (req, res, next) => {
    clientesGetServiceProxy(req, res, next);
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

// gerente-service
app.get('/gerentes', verifyJWT, (req, res, next) => {
    gerentesGetServiceProxy(req, res, next);
})
app.put('/gerentes/:id', verifyJWT, (req, res, next) => {
    gerentesPutServiceProxy(req, res, next);
});
app.get('/gerentes/:id', verifyJWT, (req, res, next) => {
    gerentesGetServiceProxy(req, res, next);
});
app.post('/gerentes/inserir', verifyJWT, (req, res, next) => {
    gerentesPostServiceProxy(req, res, next);
})
// Delete depois arrumar para o nome que estiver na SAGA de remover gerentes
app.delete('/gerentes/:id', verifyJWT, (req, res, next) => {
    gerentesDeleteServiceProxy(req, res, next);
})


// Cria o servidor na porta 3000
var server = http.createServer(app);
server.listen(3000);
console.log('API Gateway running!');
