<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="com.demoweb.model.dao.CommentDao"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@page import="com.demoweb.model.dto.BoardComment"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray"%>

<%
BoardComment boardComment = new BoardComment();
CommentDao commentDao = new CommentDao();
int boardNo = Integer.parseInt(request.getParameter("boardNo"));

boardComment.setBoardNo(boardNo);
boardComment.setWriter(request.getParameter("writer"));
boardComment.setContent(request.getParameter("comment"));

commentDao.insertComment(boardComment);
List<BoardComment> commentlist = commentDao.getCommentList(boardNo);

//JSON 데이터
JSONArray jsonarray = JSONArray.fromObject(commentlist); 
%>

<%=jsonarray %>









