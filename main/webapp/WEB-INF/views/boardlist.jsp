<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>

<%	List<Board> searchlist = (List) request.getAttribute("searchlist"); %>
<%	List<Board> boardList = (List) request.getAttribute("boardList"); %>

<style type="text/css">
th, td {
	text-align: center;
}
td:nth-child(2) {
	text-align: justify;
}

body{
	padding-bottom : 300px;
}

.keyword {
	margin-left: 10px;
	border-radius: 10px;
}
 



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

<br>
<hr>

<form action="/list" method="POST">
<div class="keyword">
제목<input type="text" name="keyword">
<button>검색</button>
</div>
<table class="table table-striped table-hover table-condensed">
<tr>
	<th style="width: 10%">글번호</th>
	<th style="width: 10%">회원번호</th>
	<th style="width: 25%">제목</th>
	<th style="width: 15%">작성일</th>
	<th style="width: 20%">조회수</th>
</tr>

<%	for(int i=0; i<boardList.size(); i++) { %>
<tr>
	<td><%=boardList.get(i).getBoardno() %></td>
	<td><%=boardList.get(i).getUser_no() %></td>
	<td>
		<a href="./view?boardno=<%=boardList.get(i).getBoardno() %>">
			<%=boardList.get(i).getTitle() %>
		</a>
	</td>
	<td><%=boardList.get(i).getWriteDate() %></td>
	<td><%=boardList.get(i).getHit() %></td>

</tr>
<%	} %>
</table>
</form>

<div id="btnBox" class="pull-right">
   <%   if( null != session.getAttribute("login") && (Boolean)session.getAttribute("login") ) { %>
	<button id="btnWrite" class="btn btn-outline-dark">글쓰기</button>
	   <% } %>
</div>

<div class="clearfix"></div>

<%@ include file="/layout/paging.jsp" %>

<%@ include file="/layout/footer.jsp" %>












