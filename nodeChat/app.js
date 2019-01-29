var express = require('express');
var http = require('http');
var app = express();

var server = http.createServer(app); //서버 생성
server.listen(3000); //대기요청

app.get('/', function(req, res){
    res.sendFile(__dirname + "/client.html");
});

//여기까지 서버 구성 및 요청에 대한 처리

//실시간 웹서비스 -> socket.io

var io= require('socket.io')(server); //이 모듈은 server 객체 위에서 동작한다

//서버에 event(1. 기본으로 제공되는 이벤트, 2. 사용자 등록 이벤트)
io.on('connect',function(socket){ //클라이언트 요청 자동 발생 이벤트
    //서버와 클라이언트는 같은 socket 객체를 공유한다(parameter를 통해)
    console.log('클라이언트 접속');
    

    //클라이언트와 공유하는 socket 객체를 통해 이벤트 발생
    socket.on('disconnect', function(){
        console.log('클라이언트 접속 종료');
    });

    setInterval(function(){
        socket.emit('message', 'hello world event');
    }, 3000)

});