const path = require('path');
const webpack = require('webpack');
const { CheckerPlugin } = require('awesome-typescript-loader');

module.exports = {
  context: path.resolve(__dirname, './src'),
  entry: {
      app: './main.ts',
  },
  output: {
    path: path.resolve(__dirname, './dist'),
    filename: './[name].bundle.js'
  },
  devServer: {      
    publicPath: "/",
    contentBase: path.resolve(__dirname, './src'),
    inline: true,
  },
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx']
  },
  devtool: 'inline-source-map',
  module: {
      rules: [
          {
            enforce: 'pre',
            test: /\.js$/,
            loader: "source-map-loader"
          },
          {
            test: /\.ts$/,
            loader: 'awesome-typescript-loader',
            include: [
                path.resolve(__dirname, './src')
            ]
          }
      ]
  },
  plugins: [
      new CheckerPlugin(),
      new webpack.ContextReplacementPlugin(
        // The (\\|\/) piece accounts for path separators in *nix and Windows
        /angular(\\|\/)core(\\|\/)(esm(\\|\/)src|src)(\\|\/)linker/,
        ('./src'), // location of your src
        { }
      )
  ]
};

function root(__path) {
  return path.join(__dirname, __path);
}