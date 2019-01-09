var express = require('express');
var app = express();

app.use(express.static(__dirname + '/images'));
// localhost:3000/1111.png 하면 images 폴더의 1111.png를 읽어옴

app.use(function(req, res) {
 res.send('Hello Express');
});

app.listen(3000);
