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
			$('#addBtn').on("click",function(){
				if($.trim($('#comment').val()) == "")
				{
					alert("덧글을 입력하세여");
					$('#comment').focus();
					return false;
				}
				
				var data = "bbsSeq=<%=bbsSeq%>";
				data += "&comment=" + $.trim($('#comment').val());
				var data2 = {
						      bbsSeq  : "<%= bbsSeq %>" ,
						      comment : $.trim($('#comment').val())  
				            };
				//url  : 요청할 주소(insertboard.do , insert.action , insert.jsp)
				//type : 전송방식(GET ,POST)
				//data : 서버로 전송할 데이터 (조합 문자열 또는 javaScript 객체 형태)
				//dataType : 서버로 부터 받을 TYPE(XML , HTML , JSON , SCRIPT, TEXT)
				//timeout : 요청의 시간의 제한
				//async : 비동기 , 동기 (default : true > 비동기)
				//beforeSend : 요청이 전송되기전에 앞서 호출될 수 있는 함수 구현
				               //보통 요청 시 로딩 중의 빙글빙글 도는 동그라미
				//success : 응답 성공시 호출될 함수 (데이터 받아서 처리) > $.each(data,function(){}) ....
				//error : 응답 에러시 호출된 함수
				//complete : seccess , error 처리가 끝난수 마지막에 호출되는 함수
				$.ajax(
						{
							url : "CommentAdd_v_0.jsp",
							dataType : "HTML",
							async : true ,
							type : "POST",
							data : data2 ,
							success : function(htmldata)
							{
								$('#container').html(htmldata);
								$('#comment').val("");
							}
						}	
				      );

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
				
			  <!-- 이건 was가 돌림. json 으로 넘기면 클라이언트가 정보 뿌림 -->
			  <!-- 서버의 데이터이므로, 서버를 끄지 않는 이상 입력한 데이터는 그대로 남아 있음 -->
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
		</div>
	</div>
</body>
</html>










