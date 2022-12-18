<%@page import="dto.Room"%>
<%@page import="java.util.List"%>
<%@page import="dto.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% Hotel hotelDetail = (Hotel) request.getAttribute("hotelDetail"); %>
<% Integer user_no = (Integer) session.getAttribute("user_no"); %>
<% Double score = (Double) request.getAttribute("reviewScore"); %>
<% Integer reviewCnt = (Integer) request.getAttribute("reviewCnt"); %>
<% String user_email = (String) session.getAttribute("user_email"); %>
<% Integer like_check = (Integer) request.getAttribute("like_check"); %>
<% List<Room> roominfo = (List) request.getAttribute("roominfo"); %>
<%@ include file="/layout/header.jsp"%>
<!-- flatpickr -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_orange.css">
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 카카오지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&autoload=false"></script>




<style type="text/css">

.container { display: grid; grid-template-columns: repeat(2, 1fr); }

.container div { margin-top: 50px; padding: 0 20px 0px 20px; }

.container::before { content: "::before element"; order:9999; visibility:hidden;
  					 display:none; height:0; overflow:hidden; }

.container::after { content: "::before element"; order:9999; visibility:hidden;
  					display:none; height:0; overflow:hidden; }

.htimg { display: block; width: 490px; height: 350px; border: 0px; border-radius: 10px; }

.htinfo { font-family: 'Jua', sans-serif; }

.tab { text-align: left; grid-column: span 2;	 font-family: 'Jua', sans-serif; margin-bottom: -5%; }

.room { width: 80px; height: 40px; color: #fff; background: #FF5050; border:none; border-radius: 5px; }

.room:hover { background: #B90000; }

.intro { width: 80px; height: 40px; color: #fff;  background: #FF5050; border:none; border-radius: 5px; }

.intro:hover { background: #B90000; }

.tab_review { width: 80px; height: 40px; color: #fff; background: #FF5050; border:none; border-radius: 5px; }

.tab_review:hover { background: #B90000; }

.result { text-align: left; grid-column: span 2; }

h2 {  font-weight: bold; }

#mark { margin-top: -60%; margin-left: 160%; }

table { border: #ccc; margin: 5%; font-family: 'Jua', sans-serif; margin-left: -10%; }


tr, td { text-align: center; vertical-align : middle; font-size: 20px; }

.checkDate { border-radius: 5px; margin-bottom: 5%; font-family: 'Jua', sans-serif;
			 margin-left: -5%; margin-top: -5%; }

#bookingBtn { width: 180px; height: 40px; color: #fff; background: #FF5050;
 			  border:none; border-radius: 5px; }

#bookingBtn:hover { background: #B90000; }

.roomimg { width: 350px; height: 250px; border:none; border-radius: 10px; }

#checkin { margin: 10px; width: 160px; height: 35px; border: 3px solid #FFCA9B;
 		   border-radius: 10px; text-align: center; }

#checkout { margin: 10px; width: 160px; height: 35px; border: 3px solid #FFCA9B;
 			border-radius: 10px; text-align: center; }

</style>

<script type="text/javascript">


$(document).on('click', '#bt', function(e) {
	
	var val = this.parentNode.childNodes[3].childNodes[3].innerText;
	this.parentNode.childNodes[3].childNodes[3].innerText= "";
	this.parentNode.childNodes[3].childNodes[3].innerHTML='<textarea cols="10" rows="10" name="review_content" style="background-color:white;">'+val+'</textarea>';
	this.parentNode.childNodes[3].childNodes[3].innerHTML += '<button id="revModify"> 수정완료 </button>';
	this.innerHTML= "";	
	this.parentNode.childNodes[5].remove();	
	});    
	
$(document).on('click', '#revModify', function(e) {
		var review_no =  this.parentNode.nextSibling.nextSibling.value;
		var review_content = this.previousSibling.value;
		
		$.ajax({
		
			type: "GET",
			url: "/review/modify?review_no="+review_no+"&review_content="+review_content,
			datatype: "html",
			async:false,
			success: function(data) {
			}			
		}) 
	
	  var hotel_no = <%=hotelDetail.getHotel_no() %>
      var selectedOption = this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[3].value

      $.ajax({
         type: "GET",
     	url: "/review/list?hotel_no="+hotel_no+"&selectedOption="+selectedOption,   
		datatype: "html",
		async:false,
         success: function(data) {
            $("#result").html(data);

         }
      }) 
		
	}); 


//-------------------체크인/체크아웃날짜 체크여부 검사---------------
function check() {
  	
	var checkin = document.getElementById("checkin");
	var checkout = document.getElementById("checkout");
	var user_no = document.getElementById("user_no");
	if( user_no.value == "null" ) { 
		alert('로그인 후에 예약이 가능합니다.');
		return false;
	} else {
		if( (checkin.value == "") || (checkout.value == "") ) {  
			 alert("체크인/체크아웃 날짜를 모두 선택해주세요"); 
			 checkin.focus();
		 	return false; 
		}
	}
} 


 	$(document).ready(function() {
 		
	$("#roomBtn").click(function() {
 			
 			var hotel_no = "hotel_no=" + <%=hotelDetail.getHotel_no() %> 				
 				$.ajax({
 					type: "POST",
 					url: "/room/detail",
 					data: hotel_no,
 					datatype: "html",
 					success: function(data) {
 						console.log("성공");
 						$("#result").html(data);
 					}
 				
 				})
 				
 			})
 		
 		/* 숙소정보 버튼 클릭시 비동기호출 */
 		$("#infobtn").click(function() {
 			
 			var hotel_no = "hotel_no=" + <%=hotelDetail.getHotel_no() %>				
 				$.ajax({
 					type: "POST",
 					url: "/hotel/detail",
 					data: hotel_no,
 					datatype: "html",
 					success: function(data) {
 						console.log("성공");
 						$("#result").html(data);
 					}
 				
 				})
 				
 			})
 		//-----------------------------------------------------------------
 		
 			var hotel_no = <%=hotelDetail.getHotel_no() %>
			var user_no = <%=user_no%> 
			var like_check = <%=like_check%>
			var user_email = '<%=user_email%>'
			
			// 체크여부
			if( like_check > 0 ) {
				$("#heartimg").attr("src", "/resources/image/heart.png");
			} else {
				$("#heartimg").attr("src", "/resources/image/empty_heart.png");
			}
			
			// 찜하기 아이콘 눌렀을때 일어나는 일들.......
	 		$("#heartimg").click(function() {
				
				// 비로그인 상태일 때 
				 if( null == user_no ) { 
					alert('로그인 하셔야 하트를 누를수 있습니다.');
				 } 
				
				// 로그인 상태일 때
				 if( null != user_no ) {
					 $.ajax({
						type: "POST",
						url: "/mark",
						data: {hotel_no: hotel_no, user_no: user_no},
						success: function(data) {
							console.log("성공");
							if ( $("#heartimg").attr("src") == "/resources/image/empty_heart.png")  {
								$("#heartimg").attr("src", "/resources/image/heart.png");
								alert('해당호텔을 찜 하셨습니다.');
							} else {
								$("#heartimg").attr("src", "/resources/image/empty_heart.png"); 
								alert('찜하기를 취소하셨습니다.');
							}
						} 
					 }) 
				 }
			})
			
 		//-----------------------------------------------------------------
 	   $("#revbtn").click(function() {      
 	      var hotel_no = <%=hotelDetail.getHotel_no() %>
 	      var selectedOption = "byDate"
 	      $.ajax({
 	         type: "GET",
 	     	url: "/review/list?hotel_no="+hotel_no+"&selectedOption="+selectedOption,   
 			datatype: "html",
 	         success: function(data) {
 	            console.log("성공");
 	            $("#result").html(data);
 	         }
 	      })
 		})
 		
 		//------------------------------------
 	      
 	      	$("#select").change(function() {		
			var hotel_no = <%=hotelDetail.getHotel_no() %>
			var selectedOption = this.value;
			var type = "";
			
			$.ajax({
				type: "GET",
				url: "/review/list?hotel_no="+hotel_no,
				datatype: "html",
				success: function(data) {
					console.log("성공");
					$("#result").html(data);
				}
				
			})   //ajax 끝 }
 	  	 }) 
 	
 	//----------------------------체크인/체크아웃----------------------------------  	 
 	var from = "";
	var to = "";
	var checkin = flatpickr("#checkin", {
		locale: "ko",
		minDate: "today",
		dateFormat: "Ymd",

		onChange: function(dateStr, dateObj) {
			checkout.set("minDate", dateObj);
			checkin.setDate(dateObj);
			from = $("#checkin").val();
			$("#checkin").val(from);
		}
	});
	
	var checkout = flatpickr("#checkout", {
		locale: "ko",
		minDate: "today",
		dateFormat: "Ymd",
		onChange: function() {
			to = $("#checkout").val();
			console.log(to);
			console.log(from); 
			$("#checkout").val(to);
		}
	})
}) 	// jQuery 끝	
		
</script>
</head>
<body>

<div class="container">
	
 	<div>
		<img src="/upload/<%=hotelDetail.getHotel_photo() %>" class="htimg" alt="이미지 아님">
	</div>
	
	<div class="htinfo">
		<h2><%=hotelDetail.getHotel_name() %></h2>
		<h4>별점 <%=score %> &nbsp;&nbsp; 후기(<%=reviewCnt %>)</h4>
		<hr>
		<h3>주소 : <%=hotelDetail.getHotel_addr() %></h3>
		<h3>대표번호 : <%=hotelDetail.getHotel_tel() %></h3>
	</div> 
	
	<!-- 찜하기 -->
	<div id="mark">
		<img src="/resources/image/empty_heart.png" id="heartimg">
	</div>
		
	<!-- 탭메뉴 -->
	<div class="tab">
		<button class="room" id="roomBtn">
		<span>객실정보</span>
		</button>
	
		<button class="intro" id="infobtn">
		<span>숙소정보</span>
		</button>
		
		<button class="tab_review" id="revbtn">
		<span>리뷰</span>
		</button>
	</div>
	
	<!-- #result 영역 -->
	<div id="result" class="result">
		
	<form action="/hotel/booking">
		<div class="checkDate">
			<div>  	
				<b>체크인</b>	
				<input type="text" name="checkin" id="checkin" placeholder="체크인 날짜 선택" value="" required />
			  
				<b>체크아웃</b>	
				<input type="text" name="checkout" id="checkout" placeholder="체크아웃 날짜 선택" value="" required /> 
 	        </div>
		</div>
	
<% for (int i = 0; i < roominfo.size(); i++) { %>

<table class="table" style="vertical-align: middle;">

		<tr>
			<td rowspan="3">
			<img src="/upload/<%=roominfo.get(i).getRoom_img() %>" class="roomimg" alt="이미지 아님">
			</td>
			<td colspan="4"><%=roominfo.get(i).getRoom_type() %></td>
		</tr>
			<tr>
				<td >수용인원</td>
				<td><%=roominfo.get(i).getPeople() %></td>
				<td>최대인원</td>
				<td><%=roominfo.get(i).getMax_people() %></td>
			</tr>
			<tr>
				<td colspan="2">가격</td>
				<td colspan="2"><%=roominfo.get(i).getRoom_price() %>원</td>
			</tr>
			<tr>
				<td><input type="hidden" name="hotel_no" id="hotel_no"value = "<%=roominfo.get(i).getHotel_no()%>"></td>
			    <td><input type="hidden" id="room_price" name="room_price" value="<%=roominfo.get(i).getRoom_price() %>"></td>
				<td><input type="hidden" id="user_no" name="user_no" value="<%=user_no %>"></td>
				<td colspan="3">
			<button type="submit" id="bookingBtn" name="room_no" value="<%=roominfo.get(i).getRoom_no()%>" onclick="return check();">예약하기</button></td>
				
			</tr>
	</table>
			
			<% } %>
</form> 
		
		
		</div>
</div>	
	
</body>
</html>