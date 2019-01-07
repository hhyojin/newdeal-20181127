var http = require('http');
var url = require('url');
var server = http.createServer(function(req, res) {
 // URL 분석 : 쿼리 문자열
 var parsed = url.parse(req.url, true);
 console.log("parsed: ", parsed);
 var query = parsed.query; //query는 json 객체
 console.log("query: ", query);

 // start와 end
 var start = parseInt(query.start);
 console.log("start: ", start);
 var end = parseInt(query.end);
 console.log("end: ", end);
 
 if ( !start || !end ) {
  res.statusCode = 404;
  res.end('Wrong Parameter');
 }
 else {
  // 합계 구하기
  var result = 0;
  for(var i = start ; i < end ; i++) {
   result += i;
  }
  res.statusCode = 200;
  res.end('Result : ' + result);
 }

}).listen(3000);