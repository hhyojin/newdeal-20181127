<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>글 수정</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />
</head>
<body>

<%
Integer boardNo = null;
try {
	boardNo = Integer.valueOf(request.getParameter("boardno"));
} catch (Exception ex) {}
//글번호가 없다면
if (boardNo == null) {
	//목록으로 이동
	response.sendRedirect("boardlist.jsp");
	return;
}
if (session.getAttribute("loginuser") == null) {
	response.sendRedirect("/demoweb/loginform.jsp?returnurl=/demoweb/board/boardlist.jsp");
	return;
}
BoardDao dao = new BoardDao();
Board board = dao.getBoardByBoardNo(boardNo);

if (board == null) {
	response.sendRedirect("boardlist.jsp");
	return;
}

String pageNo = "1";
if (request.getParameter("pageno") != null) {
	pageNo = request.getParameter("pageno");
}

%>

	<div id="pageContainer">
	
		<% pageContext.include("/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 쓰기</div>
		        <form action="boardupdate.jsp" method="post">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                	<input type="hidden" name="pageNo" 
		                		value="<%= pageNo %>" />
		                	<input type="hidden" name="boardNo" 
		                		value="<%= board.getBoardNo() %>" />
		                	<input type="text" name="title" style="width:550px"
		                		value="<%= board.getTitle() %>" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= board.getWriter() %></td>
		            </tr>
		            <tr>
		                <th>작성일</th>
		                <td><%= board.getRegDate() %></td>
		            </tr>
					<tr>
		                <th>조회수</th>
		                <td><%= board.getReadCount() %></td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td>
							<textarea 
		                    	name="content" style="width:580px" 
		                    	rows="15"><%= board.getContent() %></textarea>
		                </td>
		            </tr>
		        </table>
		        </form>
		        <div class="buttons">
		        	<!--  <input type="submit" value="수정" /> -->
		        	<a href="javascript:document.forms[0].submit();">수정</a>
		        	&nbsp;
		        	<a href="javascript:history.back();">취소</a>
		        </div>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>