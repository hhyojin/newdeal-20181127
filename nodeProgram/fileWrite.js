var fs = require('fs');

fs.writeFile('textData.txt', 'Hello World', function(err) {
   if ( err ) {
      console.error('Error : ', err);
      return;
   }
   console.log('Write');
});