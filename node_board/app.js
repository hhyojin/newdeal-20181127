var express = require("express");
var bodyParser = require("body-parser");
var app = express();

var mysql = require("mysql");
var conn = mysql.createConnection({
	host	 : "localhost",
	user	 : "root",
	password : "1111",
	database : "test2"
});
conn.connect();

app.use(bodyParser.urlencoded({extended: false}));
app.use(express.static('public'));
app.set("views", "./views");
app.set("view engine", "ejs"); 

//---------------------------------------------------------------------------
app.get('/', function(req, res) {
	res.render('index', { title: '게시판 만들기'});
});

app.get('/write', function(req, res) {
	res.render('writeform', { title : '글 쓰기' });
});

app.post('/write', function(req, res) {
	var writer = req.body.writer;
	var pwd = req.body.pwd;
	var title = req.body.title;
	var content = req.body.content;

	conn.query('insert into board(writer,title,content,pwd,hit,regdate) values(?,?,?,?,0,now())', 
		           [writer,title,content,pwd], function(err, result) {
		res.redirect('/list'); // list 완성 후 사용
		//conn.release(); // 해제
	});
});

app.get('/list', function(req, res) {
	var sql = "select num,writer,title,content,pwd,hit,DATE_FORMAT( regdate,'%Y-%m-%d %H:%i:%s' ) regdate from board order by num desc";
	
	conn.query(sql, function(err, data, field) {
		if(err) {
			console.log(err);
			res.status(500).send("Internal Server Error");
		} else {
			res.render("list", {title:"게시판 리스트", data:data});
		}
		//conn.release();
	});
});

app.get('/read/:num', function(req, res) {
	var num = req.params.num;

	conn.query('select * from board where num=?', [num], function(err, results) {
		res.render('read', {title:'게시판 읽기', data:results[0]});
		//conn.release();
	});
});

app.get('/update/:num', function(req, res) {
	var num = req.params.num; // 글 번호 얻기

	conn.query('select * from board where num=?', [num], function(err, results) { // 글 번호로 검색
		if (err) console.log('err', err);
		res.render('updateform', {title:'게시판 수정', data:results[0]});
		//conn.release();
	});

});

app.post('/update', function(req, res) {
	var num = req.body.num;         // 글 번호 얻기
	var writer = req.body.writer;   // 글쓴이 얻기
	var pwd = req.body.pwd;         // 비밀번호 얻기
	var title = req.body.title;     // 제목 얻기
	var content = req.body.content; // 내용 얻기

	conn.query('update board set writer=?, title=?, content=? where num=? and pwd=?', 
		[writer, title, content, num, pwd], function(err, result) { // update 문장 실행

		if(result.affectedRows == 1) {     // 수정 성공시
			res.redirect('/list/');        // list/페이지 로 이동
		} else {
			res.send('<script>alert("비밀번호가 틀려 되돌아갑니다!"); history.back();</script>'); // 틀리면 되돌아간다.
		}
		//conn.release();
	});
});

app.post('/delete', function(req, res) {
	var num = req.body.num; // 글 번호 얻기
	var pwd = req.body.pwd; // 비밀번호 얻기

	conn.query('delete from board where num=? and pwd=?', 
		       [num, pwd], function(err, result) { // delete 문 실행
		if(result.affectedRows == 1) { // 성공 시
			res.redirect('/list/');    // /list 로 이동
		}
		else { // 실패 시
			res.send('<script>alert("비밀번호가 틀려 되돌아갑니다!"); history.back();</script>');
		}
		//conn.release();
	});
});

app.listen(3000, function() {
	console.log("Connection 3000 port");
});  