const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/results",
    createProxyMiddleware({
      target: "http://speller.cs.pusan.ac.kr",
      changeOrigin: true,
      // pathRewrite: {
      //   "^/results": "/results",
      // },
    })
  );

  app.use(
    "/grammar_checker.do",
    createProxyMiddleware({
      target: "https://dic.daum.net",
      changeOrigin: true,
      //   pathRewrite: {
      //     "^/grammar_checker.do": "",
      //   },
    })
  );

  // app.use(
  //   "/check",
  //   createProxyMiddleware({
  //     target: "http://127.0.0.1:8000",
  //     changeOrigin: true,
  //     pathRewrite: {
  //       "^/check": "/check",
  //     },
  //   })
  // );
};
