var fs = require('fs');
var output = fs.createWriteStream('stdout.log');
var errorOutput = fs.createWriteStream('error.log');

var Console = require('console').Console;
var logger = new Console(output, errorOutput);

logger.info('info message');
logger.log('log message');

logger.warn('warning');
logger.error('error message');