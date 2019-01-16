var express = require('express');
var router = express.Router();

var fs = require('fs');
var initialData = fs.readFileSync('initialDB.json');
var movieList = JSON.parse(initialData); //데이터 들어있는 리스트

/*
라우터 구성
*/
router.get('/movies', showMovieList);
router.get('/movies/:movieId', showMovieDetail);
router.post('/movies/:movieId', addReview);



function addReview(req, res, next) {
    var movieId = req.params.movieId;
    var movie = findMovie(movieId);
    if ( ! movie ) {
       var error = new Error('Not Found');
       error.code = 404;
       return next(error);
    }
    
    var review = req.body.review;
    movie.reviews.push(review);
    // res.send({msg:'success'});   
    res.redirect('/movies/' + movieId);
 }
 
 function findMovie(movieId) {
    for(var i = 0 ; i < movieList.length; i++) {
       var item = movieList[i];
       if ( item.movieId == movieId ) {
          return item;
       }
    }   
    return null;
 }
 
 function showMovieDetail(req, res, next) {
    var movieId = req.params.movieId;
    var movie = findMovie(movieId);
 
    if ( ! movie ) {
       var error = new Error('Not Found');
       error.code = 404;
       return next(error);
    }
    
    //res.send(movie); //postman 같은 툴 이용해서 데이터 확인
    res.render('movieDetail', {movie:movie} );  
 }
 
 function showMovieList(req, res) {
    var data = [];
    movieList.forEach(function(movie) {
       var info = {
          movieId : movie.movieId,
          title : movie.title
       };
       data.push(info);
    });
    var result = { //forEach 끝난 후, 그 결과를 담음
       count : data.length,
       data : data
    };
    // res.send(result);
    res.render('movieList', result);  //rendor: forward와 같음. movieList라는 키값에 result 값을 담아 보냄
 }

module.exports = router;
