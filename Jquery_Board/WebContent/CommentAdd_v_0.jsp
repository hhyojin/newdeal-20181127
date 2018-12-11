<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jquery.ajax.comment.CommentDAO" %>
<%@ page import="com.jquery.ajax.comment.CommentVO" %>
<%@ page import ="java.util.List" %>

<%
	String comment = request.getParameter("comment");
	int bbsSeq = Integer.parseInt(request.getParameter("bbsSeq"));
	//System.out.println(bbsSeq + " / " + comment);
	
	CommentDAO dao = CommentDAO.getInstance();
	
	//덧글등록
	dao.addComment(comment);
	List<CommentVO> commentlist = dao.getCommentList(bbsSeq);
%>
<table border="1">
				<tr>
					<td>순번</td>
					<td>내용</td>
					<td>삭제</td>
				</tr>
				<%
					for(int k = 0 ; k < commentlist.size() ; k++){
						CommentVO vo = commentlist.get(k);
				%>		
						<tr>
							<td><%= vo.getSeq() %></td>
							<td><%= vo.getComment() %></td>
							<td></td>
						</tr>		
				<%		
					}				
				%>
</table>










