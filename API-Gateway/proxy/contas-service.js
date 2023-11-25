const httpProxy = require("express-http-proxy");

var contasAPI = 'http://localhost:8081';

const contasServiceProxy = httpProxy(contasAPI, {
  proxyReqPathResolver: function (req) {
    if (req.url.startsWith('/operacoes') && req.method == 'GET') 
      return `/contas/${req.params.numero}/extrato`;
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

module.exports = {
  contasServiceProxy,
}
