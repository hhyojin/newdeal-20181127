<%@page import="com.demoweb.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//로그인 여부 확인
if (session.getAttribute("loginuser") == null) {
	//로그인 하지 않은 사용자는 loginform으로 이동 (로그인 후 다시 돌아올 경로를 전달)
	response.sendRedirect("/demoweb/loginform?returnurl=" + request.getRequestURI());
	return;
}
%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />	
</head>
<body>

	<div id="pageContainer">
	
		<% pageContext.include("/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 쓰기</div>
		        <form action="boardwrite.jsp" method="post">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	<%-- 로그인한 사용자 정보 --%>
		                	<%= ((Member)session.getAttribute("loginuser")).getMemberId() %>
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td>		                    
		                    <textarea 
		                    	name="content" style="width:580px" 
		                    	rows="15"></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<!-- 아래 a 링크는 input type='submit' 버튼을 누르는 효과 발생 -->		        	
		        	<a href="javascript:document.forms[0].submit();">글쓰기</a>
		        	&nbsp;&nbsp;
		        	<a href="/demoweb/board/boardlist.jsp">목록보기</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>