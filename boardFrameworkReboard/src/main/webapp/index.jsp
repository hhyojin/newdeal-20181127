<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.member.model.MemberDto" %>

<%
MemberDto memberDto = new MemberDto();
memberDto.setId("hhyojin");
memberDto.setName("효진");
memberDto.setEmail("hhyp@afs");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(request.getContextPath() + "/badmin/boardmenu.bit");
%>
