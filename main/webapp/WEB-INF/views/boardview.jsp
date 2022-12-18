<%@page import="dto.BoardFile"%>
<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Board viewBoard = (Board) request.getAttribute("viewBoard"); %>
<%	BoardFile boardFile = (BoardFile) request.getAttribute("boardFile"); %>
<% String user_email = (String) request.getAttribute("writerEmail"); %>


<%@ include file="/layout/header.jsp" %>

<style>
	.info {
		border: 1px solid #eeeeee;
		min-height: 300px;
		text-align: left;
		font-family: ns-light;
	}
	


</style>
<script type="text/javascript">
$(document).ready(function() {
	
	//목록 버튼
	$("#btnList").click(function() {
		$(location).attr('href', './list')
	})

	//수정 버튼
	$("#btnUpdate").click(function() {
		$(location).attr('href', './update?boardno=<%=viewBoard.getBoardno() %>')
	})

	//삭제버튼
	$("#btnDelete").click(function() {
		$(location).attr('href', './delete?boardno=<%=viewBoard.getBoardno() %>')
	})

})
</script>

<h2>문의게시판</h2>
<hr>
<br>
<br>
<br>

<table class="table table-bordered">

<tr>
<td class="info" style= "background-color: #eeeeee; text-align: center;">글번호</td><td><%=viewBoard.getBoardno() %></td>
<td class="info" style= "background-color: #eeeeee; text-align: center;">작성일</td><td><%=viewBoard.getWriteDate() %></td>
</tr>


<tr>
<td class="info" style= "background-color: #eeeeee; text-align: center;">회원 이메일</td><td><%=user_email %></td>
</tr>

<tr>
<td class="info" style= "background-color: #eeeeee; text-align: center;">제목</td><td colspan="3"><%=viewBoard.getTitle() %></td>
</tr>

<tr>
<td class="info" style= "background-color: #eeeeee; text-align: center;">조회수</td><td colspan="3"><%=viewBoard.getHit() %></td>
</tr>

<tr>
<td class="info" style= "background-color: #eeeeee; text-align: center;" colspan="4">본문</td>
</tr>
<tr><td colspan="4"><%=viewBoard.getContent() %></td></tr>

</table>

<!-- 첨부파일 -->
<div>
<%	if( boardFile != null ) { %>
<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
 download="<%=boardFile.getOriginname() %>">
	<%=boardFile.getOriginname() %>
</a>
<%	} %>
</div>


<div class="text-center">
	<button id="btnList" class="btn btn-outline-dark">목록</button>
	<button id="btnUpdate" class="btn btn-outline-success">수정</button>
	<button id="btnDelete" class="btn btn-outline-warning">삭제</button>
</div>

<%@ include file="/layout/footer.jsp" %>






