<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 로그인 체크
if (session.getAttribute("loginuser") == null) {
	response.sendRedirect("/demoweb/loginform.jsp");
	return;
}
//2. Board 객체를 생성하고 입력된 데이터를 읽어서 객체에 저장
request.setCharacterEncoding("UTF-8");
Board board = new Board();
board.setBoardNo(
	Integer.parseInt(request.getParameter("boardNo")));
board.setTitle(request.getParameter("title"));
board.setWriter(request.getParameter("writer"));
board.setContent(request.getParameter("content"));



//3. 데이터베이스에 변경 사항 적용
BoardDao dao = new BoardDao();
dao.insertReply(board);

//4. boardview.jsp 이동
String pageNo = "1";
if (request.getParameter("pageno") != null)
	pageNo = request.getParameter("pageno");

response.sendRedirect(String.format(
	"boardview.jsp?boardno=%d&pageno=%s",
	board.getBoardNo(), pageNo));
%>







