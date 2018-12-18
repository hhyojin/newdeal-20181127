<%@page import="com.demoweb.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>메일보내기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />	
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
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">메일보내기</div>
		        <form action="sendmail.jsp" method="post">
		        <table>
		            <tr>
		                <th>받는이</th>
		                <td>
		                    <input type="text" name="to" style="width:550px" />
		                </td>
		            </tr>
		            <tr>
		                <th>보내는이</th>
		                <td>
		                	<%= ((Member)session.getAttribute("loginuser")).getEmail() %>
		                </td>
		            </tr>		            
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="subject" style="width:550px" />
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td>
		                    <textarea name="content" rows="20" cols="95"></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">		        	
		        	<a href="javascript:document.forms[0].submit();">보내기</a>
		        	&nbsp;&nbsp;
		        	<a href="/demoweb/index.jsp">취소</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>