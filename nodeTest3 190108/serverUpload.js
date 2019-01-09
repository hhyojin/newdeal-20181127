var fs = require('fs');
var pathUtil = require('path');

// 업로드 된 파일 경로
var uploadDir = __dirname + '/upload';

// 이미지 파일 경로
var imageDir = __dirname + '/image';

var http = require('http');
var formidable = require('formidable');

// 업로드 된 데이터 목록
var paintList = [];

var server = http.createServer(function (req, res) {
   // 루트 경로로 요청
   if (req.url == '/' && req.method.toLowerCase() == 'get') {
      showList(res);
   }
   // <img> 태그로 인한 이미지 요청
   else if (req.method.toLowerCase() == 'get' && req.url.indexOf('/image') == 0) {
      console.log(path);
      var path = __dirname + req.url;
      console.log(path);
      res.writeHead(200, { 'Content-Type': 'image/jpeg' })
      fs.createReadStream(path).pipe(res);
   }   
   // 업로드 요청
   else if (req.method.toLowerCase() == 'post') {
      addNewPaint(req, res);
   }

});

function showList(res) {
   res.writeHeader(200, { 'content-type': 'text/html' });

   var body = '<html>';
   body += '<head><meta charset="UTF-8"></head>';
   body += '<body>';
   body += '<h3>Favorite Paint</h3>';

   body += '<ul>';
   paintList.forEach(function (item, index) {
      body += '<li>';
      if (item.image) {
         body += '<img src="' + item.image + '" style="height:100pt"></img>';
      }
      body += item.title;
      body += '</li>';
   });
   body += '</ul>';

   body += '<form action="." enctype="multipart/form-data" method="post">' +
   '<div><label>작품 이름 : </label><input type="text" name="title"></div>' +
   '<div><label>작품 이미지 : </label><input type="file" name="image" value="작품 파일 선택"></div>' +
   '<input type="submit" value="upload">' +
   '</form>';
   body += '</body></html>';

   res.end(body);
}

server.listen(3000, function () {
   console.log('Server is running on 3000');
});

function addNewPaint(req, res) {
    var form = formidable.IncomingForm();
    form.uploadDir = uploadDir;
    
    form.parse(req, function(err, fields, files) {
        var title = fields.title;
        var image = files.image;
        
        console.log(image);
        
        var date = new Date();
        var newImageName = 'image_' + date.getHours() + date.getMinutes() + date.getSeconds();
        var ext = pathUtil.parse(image.name).ext;
        
        var newPath = __dirname + '/image/' + newImageName + ext;
        
        fs.renameSync(image.path, newPath);
        
        var url = 'image/' + newImageName + ext;
        
        var info = {
            title : title, image:url
        }
        
        paintList.push(info);
        
        res.statusCode = 302;
        res.setHeader('Location', '.');        
        res.end('Success');
        
    });
}
