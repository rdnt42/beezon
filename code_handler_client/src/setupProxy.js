const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: process.env.BEEZON_CODE_HANDLER_BACKEND_HOST,
            changeOrigin: true
        }),
    );
};