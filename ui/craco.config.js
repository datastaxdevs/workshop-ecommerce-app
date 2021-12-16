const path = require("path");

module.exports = {
  style: {
    postcss: {
      plugins: [require("tailwindcss"), require("autoprefixer")],
    },
  },
  webpack: {
    configure: (webpackConfig, { env, paths }) => {
      // set the output folder to be served by spring boot
      paths.appBuild = webpackConfig.output.path = path.resolve(
        "../backend/src/main/resources/static"
      );
      return webpackConfig;
    },
  },
};
