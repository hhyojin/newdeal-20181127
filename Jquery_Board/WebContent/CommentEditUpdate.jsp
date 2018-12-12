<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jquery.ajax.board.BoardDAO" %>
<%@ page import="com.jquery.ajax.board.BoardVO" %>
<%@ page import ="java.util.List" %>
<%
	request.setCharacterEncoding("UTF-8");
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String regdate = request.getParameter("regdate");
	int hit = Integer.parseInt(request.getParameter("hit"));
	
	BoardVO vo = new BoardVO(seq, title, content, regdate, hit);
	
	BoardDAO dao = BoardDAO.getInstance();
	boolean result = dao.modifyBoard(vo);
%>
{"result" : <%= result %>}











