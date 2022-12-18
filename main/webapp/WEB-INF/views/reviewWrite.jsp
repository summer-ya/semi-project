<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String pay_no = (String) request.getAttribute("pay_no"); %>
<%String booking_no =(String)  request.getAttribute("booking_no"); %>
<%String room_type = (String)  request.getAttribute("room_type"); %>
<%String hotelPhotoLocation = (String)  request.getAttribute("hotelPhotoLocation"); %> 
<%String hotel_name = (String)  request.getAttribute("hotel_name"); %>
<%String hotel_no = (String) request.getAttribute("hotel_no"); %>
<% String user_email = (String) session.getAttribute("user_email"); %>
<% String user_no = (String) request.getAttribute("user_no"); %>
<%@ include file="/layout/header.jsp" %>

<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="/assets/css/star.css" rel="stylesheet"/>




<form action="/review/write" method="POST" enctype="multipart/form-data">
    <main class="container">
      <h1 class="title"><%=hotel_no%> 번 호텔 리뷰쓰기입니다 </h1>
      <div class="infoArea">
       <img src="/upload/<%=hotelPhotoLocation %>" alt="이미지 아님" width="50" height="50"><!-- 호텔 사진 -->
        <div class="text">
          <strong><%=hotel_name%></strong>
          <span><%=room_type%></span>
        </div>
      </div>
      <div class="rateArea">
        <h2 class="subTitle">어떠셨나요?</h2>
        <div class="startRadio">
          <input type="radio" name="review_score" value="5" id="rate5" /><label
            for="rate5">★</label>
          <input type="radio" name="review_score" value="4" id="rate4" /><label
            for="rate4"
            >★</label>
          <input type="radio" name="review_score" value="3" id="rate3" /><label
            for="rate3"
            >★</label>
          <input type="radio" name="review_score" value="2" id="rate2" /><label
            for="rate2"
            >★</label>
          <input type="radio" name="review_score" value="1" id="rate1" /><label
            for="rate1"
            >★</label>
        </div>
      </div>
      <!-- 리뷰 -->
      <div class="reviewArea" >
        <h2 class="subTitle">리뷰를 남겨주세요</h2>
        <textarea cols="10" rows="10" name="review_content" placeholder="다른 이용자에게 호텔을 추천해보세요!" ></textarea>
      </div>
 <input name="pay_no" value = "<%=pay_no%>"style="display:none;">
<input name="booking_no" value = "<%=booking_no%>" style="display:none;">
<input name="hotel_no" value = "<%=hotel_no%>" style="display:none;">
<input name="user_email" value = "<%=user_email%>" style="display:none;">
<input name="room_type" value = "<%=room_type%>" style="display:none;">
<input name="user_no" value = "<%= user_no%>" style="display:none;">

<%try {
			for (int i = 0; i < 1; i++) {
				Thread.sleep(1000);
				System.out.println("Sleep "+i);
			}
		}catch(Exception e) {
			System.out.println(e);
		}%>
            <!-- 사진 -->
      <div class="imgArea">
        <label for="upfile1">
          사진첨부 <input type="file" name="upfile" id="upfile1" />
        </label>
        <label for="upfile2">
          사진첨부 <input type="file" name="upfile" id="upfile2" />
        </label>
        <label for="upfile3">
          사진첨부 <input type="file" name="upfile" id="upfile3" />
        </label>
      </div>
      <button type="submit" class="submitBtn"> 리뷰 등록하기</button>
      </main>
      </form> 
<%@ include file="/layout/footer.jsp" %>