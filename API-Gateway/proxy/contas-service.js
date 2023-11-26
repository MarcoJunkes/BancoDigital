const httpProxy = require("express-http-proxy");

var contasAPI = 'http://contas-service:8080';

const contasServiceProxy = httpProxy(contasAPI, {
  proxyReqPathResolver: function (req) {
    if (req.url.startsWith('/operacoes') && req.method == 'GET') 
      return `/contas/${req.params.numero}/extrato?`+new URLSearchParams(req.query).toString();
    if (req.method === 'POST') {
      let operacao = req.url.split('/')[3];
      return `/contas/${req.params.numero}/${operacao}`;
    }

    return req.url;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    return proxyResData;
  }
});

const contasGetServiceProxy = httpProxy(contasAPI);

const contasPostServiceProxy = httpProxy(contasAPI, {
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
  contasServiceProxy,
  contasPostServiceProxy,
  contasGetServiceProxy,
}
