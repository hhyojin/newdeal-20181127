<%@page import="com.demoweb.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="header">
            <div class="title">
                <a href="<%=request.getContextPath()%>/">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <% if (session.getAttribute("loginuser") == null) { %>
                <a href="<%=request.getContextPath()%>/loginform">로그인</a>
                <a href="<%=request.getContextPath()%>/membermanager/registerform.action">등록</a>

            <% } else { %>
            	<% Member member = (Member)session.getAttribute("loginuser"); %>
                <%= member.getMemberId() %>님 환영합니다.
                <a href="<%=request.getContextPath()%>/logout">로그아웃</a>
            <% } %>
            </div>
        </div>        
        <div id="menu">
            <div>
                <ul>
                	<!-- a : 페이지 이동을 처리하는 마크업 -->
                    <li><a href="<%=request.getContextPath()%>/membermanager/list">사용자관리</a></li>
					<li><a href="<%=request.getContextPath()%>/mail/sendmailform.jsp">메일보내기</a></li>
					<li><a href="<%=request.getContextPath()%>/upload/uploadlist.jsp">자료실</a></li>
					<li><a href="<%=request.getContextPath()%>/board/boardlist.jsp">게시판</a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
        	[ TOTAL : <%= application.getAttribute("total") %>명 ]
        	[ CURRENT : <%= application.getAttribute("current") %>명 ]
        </div>
        
        
        
        
        
        
        
        
        
        
        
        