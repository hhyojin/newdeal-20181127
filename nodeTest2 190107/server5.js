var http = require('http');
var querystring = require('querystring'); // ? 뒤의 파라미터를 뽑아낼 수 있는 모듈
var movieList = [{title : '비트', director : '뉴딜'}];


var server = http.createServer(function(req, res) {
    if(req.method.toLowerCase() == 'post') {
        // post 방식으로 전송되었다면
        //처리 로직(실 데이터 처리): 클라이언트의 데이터 받아서 배열에 넣기
        addNewMovie(req, res);
    } else {
        // get 방식으로 전송되었다면
        //처리 로직(화면 보여주기)
        showList(req, res);
    }
});
server.listen(3000);

function showList(req, res) {
    res.writeHeader(200, { 'Content-Type': 'text/html; charset=UTF-8' });
    // 포트 번호, 클라이언트에게 응답할 뷰단 설정
    res.write('<html>');
    res.write('<meta charset="UTF-8">');
    res.write('<body>');

    res.write('<h3>Favorite Movie</h3>');
    res.write('<div><ul>');

    movieList.forEach(function (item) {
        // each문과 비슷한 역할
        res.write('<li>' + item.title + '(' + item.director + ')</li>');
    }, this);
    res.write('</ul></div>');

    res.write(
        '<form method="post" action="."><h4>새 영화 입력</h4>' +
        '<div><input type="text" name="title" placeholder="영화제목"></div>' +
        '<div><input type="text" name="director" placeholder="감독"></div>' +
        '<input type="submit" value="upload">' +
        '</form>'
        );
    res.write('</body>');
    res.write('</html>');
    res.end();
}

function addNewMovie(req, res) {
    var body = '';
    req.on('data', function(chunk) {
        // 데이터 들어오면 발생하는 이벤트 부착
        body += chunk;
    });
    // PRG 방식, 데이터 들어올 때마다 누적
    req.on('end', function() { //데이터 다 받아서 분석(입력값)
        // 데이터 다 들어오면 발생하는 이벤트 부착
        var data = querystring.parse(body); //body에 모은 값을 parse
        // 데이터는 json 객체
        var titledata = data.title;
        var directordata = data.director;

        // 비즈니스 로직은 배열에 넣기
        // 자바스크립트의 배열은 스택구조임
        // 때문에 스택의 메서드를 사용할 수 있음
        //movieList.push({title:titledata, director:directordata});
        movieList.push(data);
        // json 객체 push
        //res.end("success");
        res.statusCode = 302;
        res.setHeader('Location', '.');
        // 화면 재요청, get 방식으로 요청했을 때의 함수를 탄다 = redirect = location.href
        res.end();
    });
}