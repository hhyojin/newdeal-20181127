var url = require('url');

var urlStr = 'http://api.flickr.com/services/feeds/photos_public.gne?tags=raccoon&tagmode=any&format=json&jsoncallback=?';
var parsed = url.parse(urlStr, true);
console.log(parsed);

console.log('protocol : ', parsed.protocol);
console.log('host : ', parsed.host);
console.log('query : ', parsed.query);