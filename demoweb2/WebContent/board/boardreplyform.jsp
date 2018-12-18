<%@page import="com.demoweb.model.dto.Member"%>
<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	
		<%
		//로그인 여부 확인하고 로그인 하지 않은 사용자는 로그인 페이지로 이동
		if (session.getAttribute("loginuser") == null) {
			response.sendRedirect("/demoweb/loginform.jsp");
			return;
		}

		//댓글의 대상 글번호를 읽어서 변수에 저장
		//(대상 글번호는 boardview.jsp에서 전송된 데이터)
		if (request.getParameter("boardno") == null) {
			response.sendRedirect("/demoweb/board/boardlist.jsp");
			return;
		}
		int number = Integer.parseInt(
			request.getParameter("boardno"));
		//댓글  대상글의 번호로 데이터베이스에서 조회
		BoardDao dao = new BoardDao();
		Board board = dao.getBoardByBoardNo(number);
		//조회 실패하면 목록으로 이동
		if (board == null) {
			response.sendRedirect("/demoweb/board/boardlist.jsp");
			return;			
		}
		
		String pageNo = "1";
		if (request.getParameter("pageno") != null)
			pageNo = request.getParameter("pageno");
		%>
	
		<% pageContext.include("/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">댓글 쓰기</div>
		        <form action="boardreply.jsp" method="post">
		        <input type="hidden" name="pageno" 
		        	value="<%= pageNo %>" />
		        <%-- 댓글을 작성할 대상글의 글번호를 저장 / submit 할 때 서버로 전송 --%>
		        <input type="hidden" name="boardNo" 
		        	value="<%= board.getBoardNo() %>" />
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:550px"
		                    	value="[RE] <%= board.getTitle() %>" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
							<% Member member = (Member) session.getAttribute("loginuser"); %>
		                	<input type="hidden" name="writer"
		                		value='<%= member.getMemberId() %>' />
		                	<%= member.getMemberId() %>
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
		        	<a href="javascript:document.forms[0].submit();">저장</a>
		        	&nbsp;&nbsp;
		        	<a href="javascript:history.back();">취소</a>
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