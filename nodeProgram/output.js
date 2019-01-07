var fs = require('fs');
var os = fs.createWriteStream('output.txt');
os.on('finish', function() {
 console.log('== FINISH EVENT');
});
os.write('1234\n');
os.write('5678\n');
os.end();