<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.demoweb.model.dao.CommentDao"%>
<%@page import="com.demoweb.model.dto.BoardComment"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>

<%
BoardComment boardComment = new BoardComment();
CommentDao commentDao = new CommentDao();

boardComment.setContent(request.getParameter("content"));
boardComment.setCommentNo(Integer.parseInt(request.getParameter("commentNo")));

commentDao.updateComment(boardComment);

//JSON 데이터
JSONArray jsonarray = null; //JSONArray.fromObject(commentlist);
%>
<%=jsonarray %>









