const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
  mode: 'development',
  devtool: 'source-map',
  entry: {
    path: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
    journey: {
      import: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'journey.js')
    },
  },
  devServer: {
    static: './dist',
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:5000'
    ],
    //proxy: 'https://localhost:8000'
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      {
        test: /\.css$/,
        use: [
                'vue-style-loader',
                'css-loader'
              ]
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin()
  ],
  resolve: {
      modules: [
          path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
          path.join(__dirname, 'node_modules'),
      ],
  }
}