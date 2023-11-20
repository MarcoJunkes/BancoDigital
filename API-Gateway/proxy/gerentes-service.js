const httpProxy = require("express-http-proxy");

var gerentesAPI = 'http://localhost:8082';
var gerenteSagaInserir = 'http://172.18.0.2:3200';

const gerentesGetServiceProxy = httpProxy(gerentesAPI);
const gerentesPostServiceProxy = httpProxy(gerenteSagaInserir, {
    proxyReqBodyDecorator: function (bodyContent, srcReq) {
        try {
            retBody = {};
            retBody.nome = bodyContent.nome;
            retBody.email = bodyContent.email;
            retBody.cpf = bodyContent.cpf;
            retBody.telefone = bodyContent.telefone;
            bodyContent = retBody;
            console.log('API-Gateway/index.js: retBody = ', retBody);
        }
        catch (e) {
            console.log('- ERRO: ' + e);
        }
        return bodyContent;
    },
    proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
        proxyReqOpts.headers['Content-Type'] = 'application/json';
        proxyReqOpts.method = 'POST';
        return proxyReqOpts;
    }
});
const gerentesPutServiceProxy = httpProxy(gerentesAPI, {
    proxyReqBodyDecorator: function (bodyContent, srcReq) {
        try {
            retBody = {};
            retBody.nome = bodyContent.nome;
            retBody.email = bodyContent.email;
            retBody.cpf = bodyContent.cpf;
            retBody.telefone = bodyContent.telefone;
            bodyContent = retBody;
            console.log('API-Gateway/index.js: retBody = ', retBody);
        }
        catch (e) {
            console.log('- ERRO: ' + e);
        }
        return bodyContent;
    },
    proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
        proxyReqOpts.headers['Content-Type'] = 'application/json';
        proxyReqOpts.method = 'PUT';
        return proxyReqOpts;
    }
});

module.exports = {
  gerentesGetServiceProxy,
  gerentesPostServiceProxy,
  gerentesPutServiceProxy
}
