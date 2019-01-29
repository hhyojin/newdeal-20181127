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


// 로그인 아이디 매핑 (로그인 ID -> 소켓 ID)
var login_ids = {};



// socket.io 서버를 시작합니다.
var io = socketio.listen(server);
console.log('socket.io 요청을 받아들일 준비가 되었습니다.');

// 클라이언트가 연결했을 때의 이벤트 처리
io.sockets.on('connection', function(socket) {
	console.log('connection info :', socket.request.connection._peername);

    // 소켓 객체에 클라이언트 Host, Port 정보 속성으로 추가
    socket.remoteAddress = socket.request.connection._peername.address;
    socket.remotePort = socket.request.connection._peername.port;
    

    // 'login' 이벤트를 받았을 때의 처리
    socket.on('login', function(login) {
    	console.log('login 이벤트를 받았습니다.');
    	console.dir(login);

        // 기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        console.log('접속한 소켓의 ID : ' + socket.id);
        login_ids[login.id] = socket.id;
        socket.login_id = login.id;

        console.log('접속한 클라이언트 ID 갯수 : %d', Object.keys(login_ids).length);

        // 응답 메시지 전송
        sendResponse(socket, 'login', '200', '로그인되었습니다.');
    });

    
    // 'message' 이벤트를 받았을 때의 처리
    socket.on('message', function(message) {
    	console.log('message 이벤트를 받았습니다.');
    	console.dir(message);
    	
        if (message.recepient =='ALL') {
            // 나를 포함한 모든 클라이언트에게 메시지 전달
        	console.dir('나를 포함한 모든 클라이언트에게 message 이벤트를 전송합니다.')
            io.sockets.emit('message', message);
        } else {
        	// 일대일 채팅 대상에게 메시지 전달
        	if (login_ids[message.recepient]) {
        		io.sockets.connected[login_ids[message.recepient]].emit('message', message);
        		
        		// 응답 메시지 전송
                sendResponse(socket, 'message', '200', '메시지를 전송했습니다.');
        	} else {
        		// 응답 메시지 전송
                sendResponse(socket, 'login', '404', '상대방의 로그인 ID를 찾을 수 없습니다.');
        	}
        }
    });
    

    // 'room' 이벤트를 받았을 때의 처리
    socket.on('room', function(room) {
    	console.log('room 이벤트를 받았습니다.');
    	console.dir(room);
    	
        if (room.command === 'create') {

            //io.sockets.adapter.room 
            //어댑터는 관리자 같은 것. 아예 room이라는 property가 있음

        	if (io.sockets.adapter.rooms[room.roomId]) { // 방이 이미 만들어져 있는 경우
                                                        //room도 고유한 식별값이 있음
                console.log('방이 이미 만들어져 있습니다.');
        		
        	} else { 
        		console.log('방을 새로 만듭니다.');
        		
                socket.join(room.roomId); 
                //방 만들 때 방 이름을 aa라고 주면, room.roomId = aa
        		
	            var curRoom = io.sockets.adapter.rooms[room.roomId]; //rooms 배열
	            curRoom.id = room.roomId;
	            curRoom.name = room.roomName;
	            curRoom.owner = room.roomOwner;
        	}

        } else if (room.command === 'update') {
        	
            var curRoom = io.sockets.adapter.rooms[room.roomId];
            curRoom.id = room.roomId;
            curRoom.name = room.roomName;
            curRoom.owner = room.roomOwner;
             
        } else if (room.command === 'delete') {

            socket.leave(room.roomId);
 
            if (io.sockets.adapter.rooms[room.roomId]) { // 방이  만들어져 있는 경우, 방을 삭제할 수 있다
            	delete io.sockets.adapter.rooms[room.roomId];
            } else {  // 방이  만들어져 있지 않은 경우
            	console.log('방이 만들어져 있지 않습니다.');
            	
            }
        }

        var roomList = getRoomList();
        
        var output = {command:'list', rooms:roomList};
        console.log('클라이언트로 보낼 데이터 : ' + JSON.stringify(output));
        
        io.sockets.emit('room', output);
    });
    
    
    
});

function getRoomList() {
	console.dir(io.sockets.adapter.rooms);
	
    var roomList = [];
    
    Object.keys(io.sockets.adapter.rooms).forEach(function(roomId) { // for each room
    	console.log('current room id : ' + roomId);
    	var outRoom = io.sockets.adapter.rooms[roomId];
    	
    	// find default room using all attributes
    	var foundDefault = false;
    	var index = 0;
        Object.keys(outRoom.sockets).forEach(function(key) {
        	console.log('#' + index + ' : ' + key + ', ' + outRoom.sockets[key]);
        	
        	if (roomId == key) {  // default room
        		foundDefault = true;
        		console.log('this is default room.');
        	}
        	index++;
        });
        
        if (!foundDefault) {
        	roomList.push(outRoom);
        }
    });
    
    console.log('[ROOM LIST]');
    console.dir(roomList);
    
    return roomList;
}

// 응답 메시지 전송 메소드
function sendResponse(socket, command, code, message) {
	var statusObj = {command: command, code: code, message: message};
	socket.emit('response', statusObj);
}



