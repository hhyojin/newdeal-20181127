var util = require('util');
function Parent() {
}
//Parent : 1회성 객체
//prototype : Parent를 쓰는 모든 객체는 sayHello를 사용할 수 있음
Parent.prototype.sayHello = function() { 
 console.log('Hello World, from Parent Class!');
}

var obj = new Parent();
obj.sayHello();

function Child() {
}

// 상속
util.inherits(Child, Parent);

var obj2 = new Child();
obj2.sayHello();