<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp"%>

<style type="text/css">

.table table-stripped {
 margin: 5%;
}
.text-center {
	margin-bottom: 3%;
}

</style>
	

<script type="text/javascript">
$(document).ready(function() {

	
	//작성버튼
	$("#btnWrite").click(function() {
		$("form").submit();
	})
	
	//취소버튼
	$("#btnCancel").click(function() {
		history.go(-1)
	})
	
})
</script>

<h1>문의게시판</h1>
<hr>

<div>
	<form name="form" action="./write" method="post" enctype="multipart/form-data">

		<table class="table table-stripped"
			style="text-align: center; boarder: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2"
						style="background-color: #eeeeee; text-align: center;">게시판
						글쓰기</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" placeholder="글 제목"
						name="title" maxlength="50"></td>
				</tr>
				<tr>
					<td><textarea class="form-control" placeholder="본문 작성"
							name="content" maxlength="2048" style="height: 350px"></textarea></td>
				</tr>
			</tbody>
		</table>
		
		첨부파일<input type="file" name="file">
	</form>
	
		<div class="text-center">
		<button id="btnWrite" class="btn btn-outline-dark">작성</button>
		<button id="btnCancel" class="btn btn-outline-dark">취소</button>
		</div>
</div>


<%@ include file="/layout/footer.jsp"%>






