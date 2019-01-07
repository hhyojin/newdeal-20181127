//함수 exports
var ex = require('./ex.js');
ex.hello();

//클래스 exports
//클래스는 필요 시 new해서 사용
var ge = require('./ge.js');
var geinstance = new ge();
geinstance.run();