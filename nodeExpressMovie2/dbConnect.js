var mysql = require('mysql');
var dbConfig = {
   host : 'localhost',
   user : 'root',
   password : '1111',
   port : 3306,
   multipleStatements : true,
   database : 'studydb'
};

var pool = mysql.createPool(dbConfig);
module.exports = pool;