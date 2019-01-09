var express = require('express');
var app = express();

app.set('views', __dirname + '/views'); //spring = webapp/WEB-INF/views
app.set('view engine', 'ejs');


var data = [
	{title:'cat1', image:'cat1.png'},
	{title:'cat2', image:'cat2.png'},
	{title:'cat3', image:'cat3.png'}	
];
app.use(express.static('./'));
app.get('/', function(req, res) {
	//cat 템플릿 파일에 Cats 이름으로 데이터를 제공
    res.render('cat', {title:'Cats', cats:data})
});

app.listen(3000);