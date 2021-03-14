const path = require('path')
const config = require('./webpack.config.js')

config.devServer = {
  historyApiFallback: true,
  contentBase: path.join(__dirname, '../build'),
  port: 9090
}

config.devtool = 'inline-source-map'

module.exports = config
