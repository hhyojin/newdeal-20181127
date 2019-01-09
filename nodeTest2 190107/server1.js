var http = require('http'); 
var server = http.createServer(function(req, res) { 
    console.log('HTTP Method : ' + req.method); //요청 메소드 
    console.log('HTTP URL : ' + req.url); //요청 url, 경로와 쿼리 문자열 
    console.log('== HEADERS ==');
    console.log(req.headers); //요청 메시지의 헤더 
    res.end('Hello Node.js');  
}).listen(3000);