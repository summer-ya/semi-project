
<%@page import="dto.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- flagPickr -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_orange.css">
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/flatpicker.js"></script>   --%>  
<% List<Room> roominfo = (List) request.getAttribute("roominfo"); %>
<% Integer user_no = (Integer) session.getAttribute("user_no"); %>

<style type="text/css">

table {
	border: #ccc;
	margin: 5%;
	font-family: 'Jua', sans-serif;
	margin-left: -10%;

}

tr, td {
	text-align: center;
	vertical-align : middle;
	font-size: 20px;
}

.checkDate {
	border-radius: 5px;
	margin-bottom: 5%;
	font-family: 'Jua', sans-serif;
	margin-left: -5%;
	margin-top: -5%;
	
}

#bookingBtn {
width: 180px;
	height: 40px;
 	color: #fff; 
 	background: #FF5050;
 	border:none;
 	border-radius: 5px;

}

#bookingBtn:hover {
 background: #B90000;
}

.roomimg {
	width: 350px;
	height: 250px;
	border:none;
	border-radius: 10px;
}

#checkin {
	margin: 10px;
	width: 160px;
 	height: 35px;
 	border: 3px solid #FFCA9B;
 	border-radius: 10px;
 	text-align: center;

}

#checkout {
	margin: 10px;
	width: 160px;
 	height: 35px;
 	border: 3px solid #FFCA9B;
 	border-radius: 10px;
 	text-align: center;

}


</style>
<script type="text/javascript">

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
			
});

</script>
	<!-- 날짜선택 -->
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

<table class="table">

		<tr>
			<td rowspan="3">
			<img src="/upload/<%=roominfo.get(i).getRoom_img() %>" class="roomimg" alt="이미지 아님">
			</td>
			<td colspan="4"><%=roominfo.get(i).getRoom_type() %></td>
		</tr>
			<tr>
				<td>수용인원</td>
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
