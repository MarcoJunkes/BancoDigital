const httpProxy = require("express-http-proxy");

var clientesAPI = 'http://172.18.0.9:3300';

const clientesGetServiceProxy = httpProxy(clientesAPI);

module.exports = {
    clientesGetServiceProxy
}
