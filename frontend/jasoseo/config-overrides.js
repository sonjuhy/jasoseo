// const webpack = require("webpack");

// module.exports = function override(config, env) {
//   config.plugins.push(
//     new webpack.ProvidePlugin({
//       util: "util/browser.js",
//     })
//   );

//   return config;
// };

// const path = require("path");

// module.exports = function override(config, env) {
//   // Add resolve fallback configuration
//   config.resolve.fallback = {
//     assert: false,
//     stream: false,
//     util: false,
//     crypto: false,
//   };

//   return config;
// };
const webpack = require("webpack");
module.exports = function override(config) {
  const fallback = config.resolve.fallback || {};
  Object.assign(fallback, {
    crypto: require.resolve("crypto-browserify"),
    stream: require.resolve("stream-browserify"),
    assert: require.resolve("assert"),
    http: require.resolve("stream-http"),
    https: require.resolve("https-browserify"),
    os: require.resolve("os-browserify"),
    url: require.resolve("url"),
    querystring: false,
    net: false,
    tls: false,
    path: false,
    zlib: false,
    fs: false,
    vm: require.resolve("vm-browserify"),
    // net: require.resolve("net-browserify"),
    // tls: require.resolve("tls"),
    // path: require.resolve("path"),
    // zlib: require.resolve("zlib"),
  });
  config.resolve.fallback = fallback;
  config.plugins = (config.plugins || []).concat([
    new webpack.ProvidePlugin({
      process: "process/browser",
      Buffer: ["buffer", "Buffer"],
    }),
  ]);
  return config;
};
