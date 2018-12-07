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

	<jsp:include page="../header.jsp"></jsp:include>

	<h1>회원 가입</h1>

	<form action="add" method="post">
		<table border='1'>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
        <th>비밀번호</th>
        <td><input type="text" name="password"></td>
      </tr>
      <tr>
        <th>사진</th>
        <td><input type="text" name="photo"></td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td><input type="text" name="tel"></td>
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
