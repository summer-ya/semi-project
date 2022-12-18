<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Booking"%>
<%@page import="dto.Payment"%>
<%@ page import="java.text.SimpleDateFormat"%>
<% List<Map<String, Object>> list = (List) request.getAttribute("list"); %>

<% SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd"); %>
<% String user_no = (String) request.getAttribute("user_no"); %>
<%@ include file="/layout/header.jsp" %>
<meta charset="UTF-8">
<title>이용내역</title>
<style type="text/css">
#hWrap{ width: 70%; margin: 0 auto; }

ul { list-style: none; text-align: center;}

.hlist { width: 45%; height: 500px; display: inline-block; margin: 7px;}

.hlist > ul { padding: 0; margin: 0; }

.imgBox{ width: 100%;
    height: 100px;
    height: 380px;
    background-repeat: no-repeat, no-repeat;
    background-position: right, left;
    background-size: cover;
    }

.titBox{ padding: 18px 0 7px 0; font-size: 20px;}

.ck sapn { font-weight: bold; color: #ff5454; margin-right: 8px; }


</style>


<h2 style="text-align: center;">결제 내역</h2><br>

<div id="hWrap">
<ul>
<% for(int i = 0; i < list.size(); i++) { %>
	<li class="hlist">
		<ul>
			<a href="/hotel/detail?hotel_no=<%= ((Booking) list.get(i).get("b")).getHotel_no()  %>">
           <div class="imgBox" style="background-image: url('/upload/<%= list.get(i).get("hotel_photo") %>');"></div>
            <li class="titBox"><%= list.get(i).get("hotel_name") %></li>
           <li class="ck"> <span><%= list.get(i).get("room_type") %></span></li>
            <%-- <td> 해당 호텔 no : <%= ((Booking) list.get(i).get("b")).getHotel_no()  %>></td> --%>
        	<li class="titBox">결제 일자 : <%= formatter.format(((Payment) list.get(i).get("p")).getPay_date()) %></li>
        				</a>
        <li class="titBox"><a href="./review/write?hotel_no=<%= ((Booking) list.get(i).get("b")).getHotel_no()  %>&pay_no=<%= ((Payment) list.get(i).get("p")).getPay_no() %>&booking_no=<%= ((Booking) list.get(i).get("b")).getBooking_no()
 %>&room_type=<%= list.get(i).get("room_type") %>&user_no=<%= ((Booking) list.get(i).get("b")).getUser_no() %>"> 리뷰쓰기</li>
		</ul>
	</li>
<% } %>
</ul>
</div>


<%@ include file="/layout/footer.jsp" %>