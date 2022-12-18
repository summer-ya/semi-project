<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>

<style type="text/css">

label {
	display: inline-block;
	width: 200px;
	margin: 0 0 20px 0;
}

#joinform {
	width: 400px;
	margin: 80px auto;
}

input {
	margin: 0 0 20px 0;
	border: 1px solid gray;
}

/* 가입하기 버튼 위치설정 - 가운데 */
.joinBtn {
	text-align: center;
	margin: 30px;
}

textarea {
width: 680px;
height: 300px;
}

</style>


<h1 style="text-align: center;">호텔등록 페이지</h1>


<form action="/hotel/insert" method="post" enctype="multipart/form-data">

<div id="joinform">
<label for="hotel_name">호텔명</label>
<input type="text" name="hotel_name" id="hotel_name"><br>

<label for="hotel_addr">호텔주소</label>
<input type="text" name="hotel_addr" id="hotel_addr"><br>

<label for="hotel_tel">대표번호</label>
<input type="text" name="hotel_tel" id="hotel_tel"><br>

<label for="hotel_info">호텔정보</label>
<textarea name="hotel_info" id="hotel_info" wrap="on"></textarea><br>

<label for="hotel_photo">호텔사진</label>
<input type="file" name="hotel_photo" id="hotel_photo"><br>

<label for="mark_hit">찜횟수</label>
<input type="text" name="mark_hit" id="mark_hit"><br>

<label for="hotel_intime">입실시간</label>
<input type="text" name="hotel_intime" id="hotel_intime"><br>

<label for="hotel_outtime">퇴실시간</label>
<input type="text" name="hotel_outtime" id="hotel_outtime"><br>


<div class="joinBtn">
<button class="button" style="background: #f7323f;">등록하기</button>
</div>

</div>
</form>

<%@ include file="/layout/footer.jsp" %>