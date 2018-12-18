<%@page import="com.demoweb.model.dto.Upload"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.demoweb.model.dao.UploadDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>자료 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
</head>
<body>

	<div id="pageContainer">
	
		<%
		if (session.getAttribute("loginuser") == null) {
			response.sendRedirect("/demoweb/loginform?returnurl=" + request.getRequestURI());
			return;
		}
		%>
	
		<% pageContext.include("/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
			[ <a href="/demoweb/upload/uploadwriteform.jsp">자료 등록</a> ]
			<br /><br />
			
			<%
			UploadDao dao = new UploadDao();
			ArrayList<Upload> uploads = dao.getUploadList();
			%>			
			<table border="1" style="width:600px" align="center">
				<tr style="background-color:orange;height:30px">
					<th style="width:50px">번호</th>
					<th style="width:400px">제목</th>
					<th style="width:150px;text-align:center">작성일</th>
				</tr>
				<% for (int i = 0; i < uploads.size(); i++) { %>
				<tr style="height:30px">
					<td><%= uploads.get(i).getUploadNo() %>
					</td>
					<td style="text-align:left;padding-left:10px">
						<a href='/demoweb/upload/uploadview.jsp?number=<%= uploads.get(i).getUploadNo() %>'>
							<%= uploads.get(i).getTitle() %>
						</a>
					</td>
					<td>
						<%= uploads.get(i).getRegDate().toString() %>
					</td>
				</tr>
				<% } %>
			</table>
			<br /><br /><br /><br />
		
		</div>
		
	</div>
		

</body>
</html>











