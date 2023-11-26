const httpProxy = require("express-http-proxy");

var clientesAPI = 'http://cliente-service:3300';
var sagaAutoCadastro = 'http://saga-autocadastro:4000';

const clientesGetServiceProxy = httpProxy(clientesAPI);

const clientesPostServiceProxy = httpProxy(sagaAutoCadastro, {
    proxyReqBodyDecorator: function (bodyContent, srcReq) {
        try {
            retBody = {};
            retBody.cpf = bodyContent.cpf;
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

module.exports = {
    clientesGetServiceProxy,
    clientesPostServiceProxy
}
