<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>수업</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

	<jsp:include page="/header.jsp"></jsp:include>

	<h1>수업 등록</h1>

	<form action="add" method="post">
		<table border='1'>
			<tr>
				<th>수업명</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents" rows="5" cols="50"></textarea></td>
			</tr>
			<tr>
        <th>시작일</th>
        <td><input type="text" name="startDate" id="startDate"></td>
      </tr>
      <tr>
        <th>종료일</th>
        <td><input type="text" name="endDate" id="endDate"></td>
      </tr>
      <tr>
        <th>총 수업시간</th>
        <td><input type="text" name="totalHours"></td>
      </tr>
      <tr>
        <th>일 수업시간</th>
        <td><input type="text" name="dayHours"></td>
      </tr>
			<tr>
				<th></th>
				<td>
					<button>등록</button>
					<button type="button" onclick="location.href='list'">취소</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
<script>
$.datepicker.setDefaults({
       dateFormat: 'yy-mm-dd'
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true
        ,changeMonth: true
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
});

    $("#startDate").datepicker();   
    $("#endDate").datepicker();
</script>