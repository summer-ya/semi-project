<%@page import="dto.BoardFile"%>
<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>

<%	Board updateBoard = (Board) request.getAttribute("updateBoard"); %>
<%	BoardFile boardFile = (BoardFile) request.getAttribute("boardFile"); %>

<style type="text/css">
.text-center {
	margin-bottom: 3%;
}

</style>


<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼
	$("#btnUpdate").click(function() {
		
		//작성된 내용을 <textarea>에 적용하기
		updateContents()

		$("form").submit();
	})
	
	//취소버튼
	$("#btnCancel").click(function() {
		history.go(-1)
	})
	
	//파일이 없을 경우
	if(<%=boardFile != null %>) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일이 있을 경우
	if(<%=boardFile == null %>) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//파일 삭제 버튼(X) 처리
	$("#delFile").click(function() {
		$("#beforeFile").toggle();
		$("#afterFile").toggle();
	})
	
})

function updateContents() {
	
	
	
}

</script>

<h1>게시글 작성</h1>
<hr>

<form action="./update" method="post" enctype="multipart/form-data">

<input type="hidden" name="boardno" value="<%=updateBoard.getBoardno() %>">

<table class="table table-stripped"
			style="text-align: center; boarder: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2"
						style="background-color: #eeeeee; text-align: center;">게시글 수정</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" placeholder="글 제목"
						name="title" maxlength="50" value="<%=updateBoard.getTitle() %>"></td>
				</tr>
				<tr>
					<td><textarea class="form-control" placeholder="본문 작성"
							name="content" maxlength="2048" style="height: 350px"><%=updateBoard.getContent() %></textarea></textarea></td>
				</tr>
			</tbody>
		</table>
<!-- 첨부파일 -->

<div>

<div id="beforeFile">
	<%	if( boardFile != null ) { %>
	<a href="<%=request.getContextPath() %>/upload/<%=boardFile.getStoredname() %>"
	 download="<%=boardFile.getOriginname() %>">
		<%=boardFile.getOriginname() %>
	</a>
	<span id="delFile" style="color: red; font-weight: bold; cursor: pointer;">X</span>
	<%	} %>
</div>

<div id="afterFile">
	새 첨부파일 <input type="file" name="file">
</div>

</div>

</form>

<div class="text-center">
	<button id="btnUpdate" class="btn btn-outline-dark">수정</button>
	<button id="btnCancel" class="btn btn-outline-dark">취소</button>
</div>


<%@ include file="/layout/footer.jsp" %>










