var pool = require('./dbConnection');

pool.getConnection(function(err, conn) {
   var sql = 'INSERT INTO movies (title, director, year) VALUES ("인셉션", "크리스토퍼 놀란", 2010);';
   conn.query(sql,function(err, results) {
      if ( err ) {
         console.error('INSERT Error', err);
      }
      else {
         console.log('results : ', results);
      }
   });
});