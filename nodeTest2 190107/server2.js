var http = require('http');
var server = http.createServer(function(req, res) {
 res.statusCode = 200;
 res.statusMessage = 'OKOK~~~';
 res.setHeader('content-type','text/plain');  //설정에 따라 Client 화면 다르게 보인다
 
 res.write('<html><body><h1>Hello World</h1></body></html>');
 res.end();
}).listen(3000);