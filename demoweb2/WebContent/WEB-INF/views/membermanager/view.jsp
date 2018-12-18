<%@page import="com.demoweb.model.dto.Member"%>
<%@page import="com.demoweb.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>사용자 정보</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />		
</head>
<body>

	<div id="pageContainer">
		
		<% pageContext.include("/include/header.jsp"); %>
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>



		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>${ member.memberId }</td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>${ member.email }</td>
		            </tr>
		            <tr>
		                <th>등록일</th>
		                <td>${ member.regDate }</td>
		            </tr>		            
		            <tr>
		                <th>사용자구분</th>
		                <td>${ member.userType }</td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>${ member.active }</td>
		            </tr>		            		            
		        </table>
		        <div class="buttons">
		        	<a href="list">목록</a>
		        </div>
		    </div>
		</div>   		
		
	</div>

</body>
</html>