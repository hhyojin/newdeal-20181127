// Express 기본 모듈 불러오기
var express = require('express')
var http = require('http')
var static = require('serve-static');
var path = require('path');
//Socket.IO 사용
var socketio = require('socket.io');
//클라이언트에서 ajax로 요청 시 CORS(다중 서버 접속) 지원
var cors = require('cors');

// 익스프레스 객체 생성
var app = express();
//클라이언트에서 ajax로 요청 시 CORS(다중 서버 접속) 지원
app.use(cors());
app.set('port', process.env.PORT || 3000);
app.use('/public', static(path.join(__dirname, 'public')));

// 시작된 서버 객체를 리턴받도록 합니다. 
var server = http.createServer(app).listen(app.get('port'), function(){
	console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));
});


//===== Socket.IO를 이용한 채팅 테스트 부분 =====//
//클라이언트는 chat01.html//

//socket.io 서버를 시작합니다.
var io = socketio.listen(server);
console.log('socket.io 요청을 받아들일 준비가 되었습니다.');

//클라이언트가 연결했을 때의 이벤트 처리. 이건 디폴트 이벤트라 emit 불필요
io.sockets.on('connection', function(socket) { //socket's'의 각각의 소켓에 대해 이벤트 처리
	console.log('connection info :', socket.request.connection._peername); 

	// 소켓 객체에 클라이언트 Host, Port 정보 속성으로 추가
	socket.remoteAddress = socket.request.connection._peername.address;
	socket.remotePort = socket.request.connection._peername.port;
});