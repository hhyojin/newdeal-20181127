


var initval = 3;
var obj = {name:'node.js', how:'world'};
console.log(obj.name);
console.log('intaval: ' + initval);
console.log('obj: ' + obj); //결합 연산자라서 오브젝트로만 찍힘
console.log('obj: ', obj);

function sayHello(who){
    console.log('bye', who);
}

setInterval(sayHello, 1*1000, 'kim');