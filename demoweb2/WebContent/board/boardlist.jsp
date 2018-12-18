<%@page import="com.demoweb.common.ThePager"%>
<%@page import="com.demoweb.model.dao.BoardDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="com.demoweb.model.dto.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! //선언문 : 메서드나 변수를 선언하는 스크립트 영역
String getTitleString(Board board, int pageNo) {	
	StringBuilder title = new StringBuilder(128);
	
	String t = board.getTitle();
	//제목이 너무 길면 말줄임표 표시
	if (t.length() > 15) {
		t = String.format("%s...", t.substring(0, 10));
	}	
	//들여쓰기 깊이 만큼 공백 삽입
	for (int i = 0; i < board.getDepth(); i++) {
		title.append("&nbsp;&nbsp;");
	}
	//들여쓰기 이미지 삽입
	if (board.getDepth() > 0) {
		title.append("<img src='/demoweb/image/re.gif' />");
	}
	//삭제 여부에 따라서 표시 방법을 선택
	if (!board.isDeleted()) {//
		title.append("<a href='boardview.jsp")
			 .append("?boardno=")
		 	 .append(board.getBoardNo())
		 	 .append("&pageno=")
		 	 .append(pageNo)
			 .append("'>")
			 .append(t.replaceAll(" ", "&nbsp;"))
			 .append("</a>");
	} else {
		title.append("<span style='color:gray' ")
			 .append("onclick='alert(\"삭제된 글입니다.\");'>")
			 .append(t.replaceAll(" ", "&nbsp;"))
			 .append("&nbsp;[삭제된 글]")
			 .append("</span>");
	}
	
	return title.toString();
}
%>
    
<%
//데이터베이스에서 게시물 목록 조회
BoardDao dao = new BoardDao();

int pageSize = 3;//한 페이지에 표시할 데이터 건수
int pagerSize = 3;//한 번에 보여줄 페이지 번호 갯수
String linkUrl = "boardlist.jsp";//페이지번호를 누르면 이동할 경로
int boardCount = dao.getBoardCount();//전체 글의 갯수

int currentPage = 1;
if (request.getParameter("pageno") != null) {
	currentPage = Integer.parseInt(
		request.getParameter("pageno"));
}

ThePager pager = new ThePager(
	boardCount, currentPage, pageSize, 
	pagerSize, linkUrl);

int start = (currentPage - 1) * pageSize + 1;//페이지의 첫번째 글 순서 번호
int last = start + pageSize;//페이지의 마지막 글 순서 번호

ArrayList<Board> boards = dao.getBoardList(start, last);


%>
    
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="EUC-KR" />
	<title>게시물 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
</head>
<body>

	<% pageContext.include("/include/header.jsp"); %>
	
	<div id="pageContainer">
		
		<div style="padding-top:25px;text-align:center">

			<input type="button" value="글쓰기" 
				style="width:300px;height:25px"
				onclick="javascript:location.href='boardwriteform.jsp';" />
			<br /><br />
			
			<table border="1" align="center">
				<tr style="background-color:beige;height:25px">
					<th style="width:50px">번호</th>
					<th style="width:300px">제목</th>
					<th style="width:150px">작성자</th>
					<th style="width:120px">작성일</th>
					<th style="width:80px">조회수</th>
				</tr>
				
				<% for (Board board : boards) { %>
				<tr style="height:25px">
					<td style="text-align:center"><%= board.getBoardNo() %></td>
					<td style="text-align:left;padding:5px">
						<%= getTitleString(board, currentPage) %>
					</td>
					<td style="text-align:center"><%= board.getWriter() %></td>
					<td style="text-align:center"><%= board.getRegDate() %></td>
					<td style="text-align:center"><%= board.getReadCount() %></td>
				</tr>
				<% } %>
			
			</table>
			
			<br /><br />
			
			<%= pager.toString() %>
	
		</div>
	</div>

</body>
</html>













