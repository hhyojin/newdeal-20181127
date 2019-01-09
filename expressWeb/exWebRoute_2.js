var express = require('express');
var app = express();

app.use(require('./greetingRouter'));

app.listen(3000);