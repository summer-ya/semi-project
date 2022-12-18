<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>

<%	List<Board> searchlist = (List) request.getAttribute("searchlist"); %>
<%	List<Board> boardList = (List) request.getAttribute("boardList"); %>
<% String keyWord = (String) request.getAttribute("keyword"); %>    

<style type="text/css">
th, td {
	text-align: center;
}
td:nth-child(2) {
	text-align: justify;
}

body{padding-bottom : 200px;} 
</style>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnWrite").click(function() {
		location.href="./write"
	})
	
	$("#btnSearch").click(function() {
		$("form").submit();
	})
	
})
</script>



<h1></h1><br>
<br>
<hr>
<form action="/board/list" method="POST">

제목<input type="text" name="keyword">
<button>검색</button>

<table class="table table-striped table-hover table-condensed">
<tr>
	<th style="width: 10%">글번호</th>
	<th style="width: 10%">아이디</th>
	<th style="width: 25%">제목</th>
	<th style="width: 15%">작성일</th>
	<th style="width: 20%">조회수</th>
</tr>
 
<%	for(int i=0; i<searchlist.size(); i++) { %>
<tr>
	<td><%=searchlist.get(i).getBoardno() %></td>
	<td><%=searchlist.get(i).getUser_no() %></td>
	<td>
		<a href="./view?boardno=<%=searchlist.get(i).getBoardno() %>">
			<%=searchlist.get(i).getTitle() %>
		</a>
	</td>
	<td><%=searchlist.get(i).getWriteDate() %></td>
	<td><%=searchlist.get(i).getHit() %></td>

</tr>
<%	} %>
</table>
</form>

<div id="btnBox" class="pull-right">
	<button id="btnWrite" class="btn btn-outline-dark">글쓰기</button>
</div>

<div class="clearfix"></div>

<%@ include file="/layout/paging.jsp" %>

<%@ include file="/layout/footer.jsp" %>












