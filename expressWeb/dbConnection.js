var mysql = require('mysql');

var dbconfig = { //port 빼고 다 문자열
    host:'192.168.0.98',
    user:'root',
    password:'1111',
    port:3306,
    database: 'studydb'
}

var pool = mysql.createPool(dbconfig);

module.exports=pool;