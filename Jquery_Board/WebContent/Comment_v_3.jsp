<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jquery.ajax.comment.CommentDAO" %>
<%@ page import="com.jquery.ajax.comment.CommentVO" %>
<%@ page import ="java.util.List" %>
<%
	//글번호 
	int bbsSeq = 1;
	CommentDAO dao = CommentDAO.getInstance();
	List<CommentVO> commentlist = dao.getCommentList(bbsSeq);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CommentLIST</title>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//데이터 출력
			function PrintData(data){
				$('#container').find("table tr").not(":first").remove();
				
				$.each(data, function(){
					//console.log(this);
					$('#container').find("table tr:last").after(
						"<tr>"
						+ " <td>" + this.seq + "</td>"
						+ " <td>" + this.comment + "</td>"
						+ " <td><button seq='" + this.seq +"'>삭제</button></td>"
						+ "</tr>"
					);
				});
				$('#comment').val();
				
			}
			
			
			
			//데이터 추가
			$('#addBtn').on("click",function(){
				if($.trim($('#comment').val()) == "")
				{
					alert("덧글을 입력하세여");
					$('#comment').focus();
					return false;
				}
				var data2 = {
						      bbsSeq  : "<%= bbsSeq %>" ,
						      comment : $.trim($('#comment').val())  
						
				            };
				$.ajax(
						{
							url : "CommentAdd_v_1.jsp",  
							dataType : "JSON",           
							async : true ,
							type : "POST",
							data : data2 ,
							success : function(data)
							{
								PrintData(data);		
							}
						}	
				      );

			});
			
			//데이터 삭제
			$('#container').on("click","button",function(){
				var data = {
					      bbsSeq  : "<%= bbsSeq %>" ,
					      seq : $(this).attr("seq")     
				            };
	
				$.getJSON('CommentDel.jsp',data)
				 .done(function(data){
					 
					 PrintData(data);
					 
				 }) //ajax > success
				 .fail(function(){console.log('delete fail');}) 
				 .always(function(){console.log('delete always');})
				 ;
			});
			
			
			
			
			
		});
	
	</script>
</head>
<body>
	<table width="700px" border="1">
		<tr>
			<th width="200px">번호</th>	
			<td><%=bbsSeq%></td>
		</tr>
		<tr>
			<th width="200px">제목</th>	
			<td>Jquery 넘 재미있어요</td>
		</tr>
		<tr>
			<th width="200px">내용</th>	
			<td>당황하지 않고 Jquery 보면...</td>
		</tr>
	</table>
	<br>
	<!-- 덧글 입력  -->
	<div>
		<input type="text" name="comment" id="comment" style="width: 600px;" placeholder="덧글을 입력하세요"> 
		<button id="addBtn">덧글등록</button>
		<br>
		<div id="container">
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
							<td>
								<button seq="<%=vo.getSeq()%>">삭제</button>
							</td>
						</tr>		
				<%		
					}				
				%>
			</table>
		</div>
	</div>
	<hr>
	<table id="tbllist" border="1">
	</table>
</body>
</html>










