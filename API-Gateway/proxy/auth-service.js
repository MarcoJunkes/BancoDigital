const jwt = require("jsonwebtoken");
const httpProxy = require("express-http-proxy");

var authAPI = 'http://auth-service:8080';
var autoCadastroAPI = 'http://saga-autocadastro:4000';

const authServiceProxy = httpProxy(authAPI, {
  proxyReqBodyDecorator: function(bodyContent, srcReq) {
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
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  },
  userResDecorator: function(proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      var str = Buffer.from(proxyResData).toString('utf-8');
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
    try {
      retBody = {};
      retBody.nome = bodyContent.nome;
      retBody.email = bodyContent.email;
      retBody.cpf = bodyContent.cpf;
      retBody.telefone = bodyContent.telefone;
      retBody.salario = bodyContent.salario;
      retBody.rua = bodyContent.rua;
      retBody.bairro = bodyContent.bairro;
      retBody.numero = bodyContent.numero;
      retBody.complemento = bodyContent.complemento;
      retBody.cep = bodyContent.cep;
      retBody.cidade = bodyContent.cidade;
      retBody.estado = bodyContent.estado;
      bodyContent = retBody;
      console.log(bodyContent);
    }
    catch(e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function(proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    return proxyReqOpts;
  },
});

module.exports = {
  authServiceProxy,
  autoCadastroServiceProxy
}
