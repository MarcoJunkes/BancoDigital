const httpProxy = require("express-http-proxy");

var gerentesAPI = 'http://gerente-service:3100';
var gerenteSagaInserir = 'http://saga-inserir-gerente:3200';
var gerenteSagaRemover = 'http://dac-saga-remover-gerente-1:3200';

const gerentesGetServiceProxy = httpProxy(gerentesAPI);
const gerentesDeleteServiceProxy = httpProxy(gerenteSagaRemover); 
const gerentesPostServiceProxy = httpProxy(gerenteSagaInserir, {
    proxyReqBodyDecorator: function (bodyContent, srcReq) {
        try {
            retBody = {};
            retBody.nome = bodyContent.nome;
            retBody.email = bodyContent.email;
            retBody.cpf = bodyContent.cpf;
            retBody.telefone = bodyContent.telefone;
            bodyContent = retBody;
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
  gerentesPutServiceProxy,
  gerentesDeleteServiceProxy
}
