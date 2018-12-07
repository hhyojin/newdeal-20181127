<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

	<h1>회원</h1>
	<p><a href="add">회원 가입</a></p>
	
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>비밀번호</th>
			<th>사진</th>
			<th>전화번호</th>
			<th>가입일</th>
		</tr>
		<c:forEach items="${list}" var="member"><!-- =request.getAttribute("list"); -->
			<tr>
				<td>${member.no}</td>
				<td><a href="detail?no=${member.no}">${member.name}</a></td>
				<td>${member.email}</td>
				<td>${member.password}</td>
				<td>${member.photo}</td>
				<td>${member.tel}</td>
				<td>${member.registeredDate}</td>
			</tr>
			<tr>
		</c:forEach>
	</table>
</body>
</html>
