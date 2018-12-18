<%@page import="com.demoweb.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.demoweb.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
    <title></title>
    <link rel="Stylesheet" href="/demoweb/styles/default.css" />
</head>
<body>

    <div id="pageContainer">
     	<% pageContext.include("/include/header.jsp"); %>
        
        <div id="content">
        	<br /><br />
        	<div style='text-align:center'>
        		[&nbsp;<a href="registerform.action">사용자 등록</a>&nbsp;]
        	</div>
        	<br /><br />        	
        	<table border="1" align="center" width="700px">
        		<tr style="height:30px;background-color:orange">
        			<td>아이디</td>
        			<td>이메일</td>
        			<td>사용자구분</td>
        			<td>활성화여부</td>
        			<td>등록일자</td>
        		</tr>        	
        	<c:forEach var="member" items="${ members }">
        		<tr style="height:30px">
        			<td>
        				<a href="view?memberid=${ member.memberId }">
        					${ member.memberId }
        				</a>
        			</td>
        			<td>${ member.email }</td>
        			<td>${ member.userType }</td>
        			<td>${ member.active }</td>
        			<td>${ member.regDate }</td>
        		</tr>
        	</c:forEach>
        	<%-- 
        	<% for (int i = 0; i < members.size(); i++) { %>
        		<tr style="height:30px">
        			<td>
        				<a href="view?memberid=<%= members.get(i).getMemberId() %>">
        					<%= members.get(i).getMemberId() %>
        				</a>
        			</td>
        			<td><%= members.get(i).getEmail() %></td>
        			<td><%= members.get(i).getUserType() %></td>
        			<td><%= members.get(i).isActive() %></td>
        			<td><%= members.get(i).getRegDate() %></td>
        		</tr>
        	<% } %>
        	--%>
        	</table>
        	
        </div>
    </div>
    
</body>
</html>









