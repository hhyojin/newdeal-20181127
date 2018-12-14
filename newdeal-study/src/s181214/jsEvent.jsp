<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>할일 목록 앱</title>
    <style>
        #add-btn{
            padding: 5px 10px;
            border: 0;
            background: #f80; color: white;
            border-radius: 5px;
        }
        ul{ 
            padding: 0; 
            list-style-position: inside;
        }
        li{
            border-bottom: 1px solid #999;
            padding: 5px 0;
        }
        .active{ background: gold; } /* 목록 클릭 스타일 */
    </style>
</head>
<body>
    
    <h1 id='title'>할일 목록</h1>
    <button id="add-btn">목록 추가</button>
    <ul id='list'>
        <li>밥먹기 1</li>
        <li>잠자기 2</li>
        <li>밥먹기 3</li>
        <li>잠자기 4</li>
    </ul>
 
<script>
    //자바 스크립트를 사용하세요 
    
</script>    
 
</body>
</html>
