var pool = require('./dbConnection');

console.log('연결풀: ', pool);

pool.getConnection(function(err, conn){
    if(err){
        console.error('error: ', err);
        return;
    } 
    console.log('db 연결 성공');
    //정상처리라면 반환코드가 있어야 함
    conn.release(); //풀에 연결 객체 반환하기
});