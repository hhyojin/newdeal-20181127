var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/sampledb";

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("sampledb");
  dbo.createCollection("customers", function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    db.close();
  });
});