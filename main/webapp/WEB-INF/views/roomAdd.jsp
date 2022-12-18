<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp"%>

<style type="text/css">

label {
   display: inline-block;
   width: 200px;
   margin: 0 0 20px 0;
}

#addform {
   width: 400px;
   margin: 80px auto;
}

input {
   margin: 0 0 20px 0;
   border: 1px solid gray;
}


.addBtn {
   text-align: center;
   margin: 30px;
   font-color: black;
   display: block;
   background: black;
}

textarea {
width: 680px;
height: 300px;
}

</style>

<br>
<br>
<br>
<br>



<h1 style="text-align: center;">객실 등록 </h1>


<form action="/room/info" method="post" enctype="multipart/form-data">

<!-- 첫번 째 객실 등록 -->
<div id="addform">
<label for="hotel_no">호텔번호</label>
<input type="text" name="hotel_no" id="hotel_no"><br>

<label for="room_type">객실타입</label>
<input type="text" name="room_type" id="room_type"><br>

<label for="people">기준인원</label>
<input type="text" name="people" id="people"><br>

<label for="max_people">최대인원</label>
<input type="text" name="max_people" id="max_peopl"><br>

<label for="room_price">객실가격</label>
<input type="text" name="room_price" id="room_price"><br>

<!--  객실 타입 사진1 -->
<label for="room_img">객실사진</label>
<input type="file" name="room_img" id="room_img"><br>

<div class="addBtn">
<button class="button">등록하기</button>
</div>

</div>
</form>

   <%@ include file="/layout/footer.jsp"%>