<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

	<h1>수업</h1>
	<p><a href="add">새 수업</a></p>
	
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>시작일</th>
			<th>종료일</th>
			<th>총 수업시간</th>
		</tr>
		<c:forEach items="${list}" var="lesson"><!-- =request.getAttribute("list"); -->
			<tr>
				<td>${lesson.no}</td>
				<td><a href="detail.jsp?no=${lesson.no}">${lesson.title}</a></td>
				<td>${lesson.startDate}</td>
				<td>${lesson.endDate}</td>
				<td>${lesson.totalHours}시간</td>
			</tr>
			<tr>
		</c:forEach>

	</table>
</body>
</html>
