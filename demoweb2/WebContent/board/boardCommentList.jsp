<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.demoweb.model.dao.CommentDao"%>
<%@page import="com.demoweb.model.dto.BoardComment"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>

<%
int boardNo = Integer.parseInt(request.getParameter("boardNo"));
BoardComment boardComment = new BoardComment();
CommentDao commentDao = new CommentDao();

List<BoardComment> commentlist = commentDao.getCommentList(boardNo);

// JSON 데이터
JSONArray jsonarray = JSONArray.fromObject(commentlist); 
%>

<%=jsonarray %>









