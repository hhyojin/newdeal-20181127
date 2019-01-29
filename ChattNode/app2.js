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

// socket.io 서버를 시작합니다.
var io = socketio.listen(server);
console.log('socket.io 요청을 받아들일 준비가 되었습니다.');

// 클라이언트가 연결했을 때의 이벤트 처리
io.sockets.on('connection', function(socket) {
	console.log('connection info :', socket.request.connection._peername);

    // 소켓 객체에 클라이언트 Host, Port 정보 속성으로 추가
    socket.remoteAddress = socket.request.connection._peername.address;
    socket.remotePort = socket.request.connection._peername.port;
    
    // 'message' 이벤트를 받았을 때의 처리
    socket.on('message', function(message) { //message는 json 객체로 받을 거임
    	console.log('message 이벤트를 받았습니다.');
    	console.dir(message);
    	
        if(message.recepient =='ALL') {
            // 나를 포함한 모든 클라이언트에게 메시지 전달
        	console.dir('나를 포함한 모든 클라이언트에게 message 이벤트를 전송합니다.')
            io.sockets.emit('message', message);
        }
    });
    
});
