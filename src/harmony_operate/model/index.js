//数据库配置
var mysql = require('mysql')
var conn = mysql.createConnection({
   host: 'localhost',
   user: 'root',
   password: 'root',
   port: 3306,
   database: 'store',
   multipleStatements: true
})
conn.connect();
module.exports = conn
