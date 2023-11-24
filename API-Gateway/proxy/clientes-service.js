const httpProxy = require("express-http-proxy");

var clientesAPI = 'http://localhost:8084';
var sagaAutoCadastro = 'http://localhost:8085';

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
