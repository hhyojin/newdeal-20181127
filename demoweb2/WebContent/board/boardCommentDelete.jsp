<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.demoweb.model.dao.CommentDao"%>
<%@page import="com.demoweb.model.dto.BoardComment"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>

<%
int commentNo = Integer.parseInt(request.getParameter("commentNo"));

CommentDao commentDao = new CommentDao();
commentDao.deleteComment(commentNo);


//JSON 데이터
JSONArray jsonarray = null;//JSONArray.fromObject(commentlist);
%>
<%=jsonarray %>









