const jwt = require("jsonwebtoken");
const httpProxy = require("express-http-proxy");

// var authAPI = 'http://localhost:8080';
var authAPI = 'http://172.18.0.10:8080';
var autoCadastroAPI = 'http://172.18.0.7:4000';

const authServiceProxy = httpProxy(authAPI, {
  proxyReqBodyDecorator: function(bodyContent, srcReq) {
    // intercepta a chamada ao /login, alterando, o corpo da mensagem, 
    // converte user e password para objeto Login

    try {
        retBody = {};
        retBody.email = bodyContent.email;
        retBody.senha = bodyContent.senha;
        bodyContent = retBody;
    }
    catch(e) {
        console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function(proxyReqOpts, srcReq) {
    // intercepta a chamada ao /login, alterando os headers da mensagem
    // e o método, acerta o Content-type e o método POST

    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  },
  userResDecorator: function(proxyRes, proxyResData, userReq, userRes) {
    // intercepta o retorno da chamada ao /login. Se
    // retornou 200, gera o token e o retorna, junto com o usuário. Se não,
    // retorna 401.

    if (proxyRes.statusCode == 200) {
        var str = Buffer.from(proxyResData).toString('utf-8');
        // var str = proxyResData.toString()
        // console.log(str)
        var objBody = JSON.parse(str)
        const id = objBody.id
        const token = jwt.sign({ id }, process.env.SECRET, {
            expiresIn: 3600 // expira em 1 hora
        });
        userRes.status(200);
        return { auth: true, token: token, data: objBody };
    }
    else {
        userRes.header('Content-Type','application/json');
        userRes.status(401);
        return {message: 'Login inválido!'};
    }
  }
});

const autoCadastroServiceProxy = httpProxy(autoCadastroAPI, {
  proxyReqBodyDecorator: function(bodyContent, srcReq) {
    // intercepta a chamada ao /login, alterando, o corpo da mensagem, 
    // converte user e password para objeto Login

    try {
        retBody = {};
        retBody.nome = bodyContent.email;
        retBody.email = bodyContent.senha;
        retBody.cpf = bodyContent.senha;
        retBody.telefone = bodyContent.senha;
        retBody.salario = bodyContent.senha;
        retBody.rua = bodyContent.senha;
        retBody.logradouro = bodyContent.senha;
        retBody.numero = bodyContent.senha;
        retBody.complemento = bodyContent.senha;
        retBody.cep = bodyContent.senha;
        retBody.cidade = bodyContent.senha;
        retBody.estado = bodyContent.senha;
        bodyContent = retBody;
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
});

module.exports = {
  authServiceProxy,
  autoCadastroServiceProxy
}
