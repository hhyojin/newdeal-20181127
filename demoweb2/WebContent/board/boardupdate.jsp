<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//post 요청인 경우 요청 데이터에 한글 문자셋으로 설정
if (request.getMethod().toLowerCase().equals("post")) {
	request.setCharacterEncoding("UTF-8");
}

//1. Board 객체를 만들고 사용자가 입력한 데이터를 저장
Board board = new Board();
board.setBoardNo(
	Integer.parseInt(request.getParameter("boardNo")));
board.setTitle(request.getParameter("title"));
board.setContent(request.getParameter("content"));

//2. 데이터베이스에 변경된 내용 적용
BoardDao dao = new BoardDao();
dao.updateBoard(board);

String pageNo = "1";
if (request.getParameter("pageNo") != null) {
	pageNo = request.getParameter("pageNo");
}
//3. boardview.jsp로 이동
response.sendRedirect(
	String.format("boardview.jsp?boardno=%d&pageno=%s",
	board.getBoardNo(), pageNo));
%>




