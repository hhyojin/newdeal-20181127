var express = require('express');
var app = express();

app.use(function(req, res) {
    res.send('Hello Express');
   });

app.listen(3000);
