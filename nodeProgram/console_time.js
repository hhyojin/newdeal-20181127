console.time('TIMER');
var sum = 0;
for(var i = 1 ; i < 100000; i++ ) {
 sum += i;
}
console.log('sum : ', sum);
console.timeEnd('TIMER');
