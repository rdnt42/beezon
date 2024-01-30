const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: process.env.BEEZON_STORAGE_MANAGER_BACKEND_HOST,
            changeOrigin: true
        }),
    );
};