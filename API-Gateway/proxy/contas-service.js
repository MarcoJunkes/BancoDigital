const httpProxy = require("express-http-proxy");

var contasAPI = 'http://localhost:8081';

const contasServiceProxy = httpProxy(contasAPI, {
  proxyReqPathResolver: function (req) {
    // Modify the proxy path to match the expected route in contas-service

    console.log({req: req.url, method: req.method, params: req.params, body: req.body});

    if (req.url.startsWith('/operacoes') && req.method == 'GET') 
      return `/contas/${req.params.numero}/extrato`
    if (req.method === 'POST') {
      let operacao = req.url.split('/')[3];
      console.log('operacao', operacao)
      return `/contas/${req.params.numero}/${operacao}`
    }

    return req.url;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
      // Modify headers or options if needed
      proxyReqOpts.headers['Content-Type'] = 'application/json';
      // Add any other headers or options you need
      return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
      // Handle the response from contas-service if needed
      // You might want to modify or process the response data here
      return proxyResData;
  }
});

module.exports = {
  contasServiceProxy,
}
