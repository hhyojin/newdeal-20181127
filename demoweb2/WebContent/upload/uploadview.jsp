<%@page import="java.util.List"%>
<%@page import="com.demoweb.model.dto.UploadFile"%>
<%@page import="com.demoweb.model.dto.Upload"%>
<%@page import="com.demoweb.model.dao.UploadDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>자료업로드</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<%
		//목록화면에서 사용자가 선택한 자료의 번호가 없는 경우 
		//목록화면으로 재전송 
		if (request.getParameter("number") == null) {			
			response.sendRedirect("/demoweb/upload/uploadlist.jsp");
			return;
		}
		
		//목록화면에서 전송된 자료번호를 저장
		int number = Integer.parseInt(request.getParameter("number"));
		
		UploadDao dao = new UploadDao();
		Upload upload = dao.getUploadByUploadNo(number);
		
		if (upload == null) {//지정된 번호에 해당하는 자료가 없는 경우
			response.sendRedirect("/demoweb/upload/uploadlist.jsp");
			return;
		}
		%>
	
		<% pageContext.include("/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">업로드 자료 정보</div>
		        <form action="" method="post">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td><%= upload.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= upload.getUploader() %></td>
		            </tr>
		            <tr>
		                <th>첨부자료</th>
		                <td>
		                	<% 
		                	List<UploadFile> files = upload.getFiles();  
		                	%>
		                	<% for (int i = 0; i < files.size(); i++) { %>
		                	<a href="/demoweb/upload/download?number=<%= files.get(i).getUploadFileNo() %>">
		                		<%= files.get(i).getUserFileName() %>
		                	</a><br />
		                	<% } %>
		                </td>
		            </tr>
		            <tr>
		                <th>자료설명</th>
		                <td><%= upload.getContent() %></td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="button" value="편집" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px" />
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>