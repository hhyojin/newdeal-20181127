var express = require('express');
var app = express();

app.use(function(req, res, next) {
   var now = new Date();
   console.log(now.toDateString() + ' - url : ' + req.url);
   next();
});

app.use(function(req, res) {
   res.send('Hello Express!!');
});

app.listen(3000);