<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/public.jsp" %>

<!-- title start -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="${root}/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>
<!-- title end -->

<!-- bbs start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr valign="bottom">
		<td nowrap><a href=""><img
			src="${root}/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"></a></td>

		<td width="100%" style="padding-left: 6px" valign="bottom">새글 <b><font
			class="text_acc_02">새글 수를 출력 하는 부분</font></b> / 전체 <font
			class="text_acc_02">전체 글수를 출력 하는 부분</font></td>
		<td width="300" nowrap>
		<div align="right"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
</table>

<form name="listForm" method="post" style="margin: 0px">
<table width="100%" cellpadding="5" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="2" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr class="bg_board_title" align="center" height="28">
		<td nowrap><b>번호</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td></td>
		<td width="100%"><b>제목</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="120" nowrap><b>글쓴이</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td nowrap><b>조회</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="45" nowrap><b>날짜</b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>

	<!-- 공지기능 적용시 -->


	<!-- 공지기능 적용끝  -->
	<tr>
		<td align="center" class="text_gray">글번호 출력하는 부분</td>
		<td></td>
		<td nowrap class="onetext" style="padding-right: 5px"></td>
		<!--td>
     
     </td-->
		<td style="word-break: break-all;"><a href=""
			class="link_board_03">글 제목을 출력 하는 부분&nbsp;&nbsp;&nbsp;</a></td>
		<td></td>
		<td style="word-break: break-all;"><a href="javascript:;"
			onClick="showSideView();" class="link_board_04">작성자를 출력 하는 부분</a></td>
		<td></td>
		<td align="center" class="text_gray">조회수를 출력 하는 부분</td>
		<td></td>
		<td align="center" class="text_gray">작성 일자를 출력 하는 부분</td>
	</tr>

	<tr>
		<td bgcolor="#ededed" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>


	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>
</form>
<!-- bbs end -->

<!-- 하단 페이징 -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="5"></td>
	</tr>
	<tr valign="top">
		<td nowrap><a href=""><img
			src="${root}/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"></a></td>
		<td width="100%" align="center"><!--PAGE--> 페이지 분류를 하는 부분</td>
		<td nowrap class="stext"><b>현재 페이지 출력 부분</b> / 총 페이지수를 출력 하는 부분
		pages</td>
	</tr>
</table>
<br>
<!-- 하단 페이징 -->

<!-- 검색 영역-->
<form name="searchForm" method="post" action="javascript:goBbsSearch();"
	style="margin: 0px"><input type="hidden" name="" value="">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="10"></td>
	</tr>
	<tr>
		<td width="50%"></td>
		<td nowrap><select name="item" onchange="javascript:ch()"
			class="inp">
			<option value="subject">글제목
			<option value="writer">글쓴이
			<option value="no">글번호
		</select> <span id="sear1"> <input type="text" name="query" size="22"
			class="inp" style="margin-top: -19px;"> </span> <span id="sear2"
			style="display: none;"> <select name="head" class="inp">
			<option value="말머리선택">말머리선택
		</select> </span> <a href="javascript:goBbsSearch();"><img
			src="${root}/img/board/sbtn_s.gif" width="32" height="18"
			border="0" align="absmiddle" alt="검색"></a> <a
			href="javascript:goMyList('안효인')"><img
			src="${root}/img/board/sbtn_mytext.gif" width="82" height="20"
			align="absmiddle" alt="내가 쓴 글 보기"></a><br>
		</td>
		<td width="50%" align="right"><a href="#"><img
			src="${root}/img/board/sbtn_top.gif" width="24" height="11"
			align="absmiddle" alt="TOP"></a><br>
		</td>
	</tr>
</table>
</form>
</body>
</html>

