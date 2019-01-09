var http = require('http');
var fs = require('fs');

var server = http.createServer(function(req, res) {
 fs.access('2.png', function(err) { //이름 없는 이름 2.png 테이스 한후에
 if ( err ) {
   res.statusCode = 404;
   res.end();
   return;
  }
  fs.readFile('2.png', function(err, data) {   
   res.end(data);
  });
  
 });
 
}).listen(3333);