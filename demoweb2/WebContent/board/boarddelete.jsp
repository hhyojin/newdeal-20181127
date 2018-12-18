<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 삭제할 글 번호 읽기 (없거나 잘못된 경우 뒤로 가기 - 이전 페이지로 가기)
Integer boardNo = null;
try {
	boardNo = Integer.valueOf(request.getParameter("boardno"));
} catch (Exception ex) {}
boolean result = true;
//글번호가 없다면
if (boardNo == null) {
	result = false;
} else {
	//2. 삭제 처리 (데이터베이스의 데이터 변경)
	BoardDao dao = new BoardDao();
	dao.deleteBoard(boardNo);
}
%>
<script type="text/javascript">
<% if (result) { %>
alert('삭제했습니다.');
location.href='boardlist.jsp';
<% } else { %>
alert('삭제 실패');
history.back();//이전 페이지로 이동하는 자바스크립트 명령
<% } %>
</script>





