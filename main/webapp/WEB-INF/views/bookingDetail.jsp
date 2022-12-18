<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>
<% List<Map<String, Object>> dList = (List) request.getAttribute("dList"); %>

<style type="text/css">
ul{ list-style: none; display: inline-block; padding-left: 2px;}

#tit { position: relative; top: 21px; text-align: center; }

#btnCancel{width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

.dWrap{ position: relative; left: 550px; top: 84px; font-size: 19px; display: inline-block; margin-bottom: 100px; }

</style>

<h1 id="tit">예약 내역 상세</h1>

<div class="dWrap">
<ul>
<% for(int i = 0; i < dList.size(); i++) { %>
	<li>
		<ul>
			<li style="font-size: 28px; font-weight: bold;"><%=dList.get(i).get("hotel_name") %></li>
			<li><%=dList.get(i).get("room_type") %></li>
		</ul>
	</li><br>
	
	<li>
		<ul>
			<li style="float: left;">체크인</li>
			<li style="padding-left:11em"><%=dList.get(i).get("hotel_in") %> <%=dList.get(i).get("hotel_intime") %></li>
			<li style="float: left;">체크아웃</li> 
			<li style="padding-left:11em"><%=dList.get(i).get("hotel_out") %> <%=dList.get(i).get("hotel_outtime") %></li>
		</ul>
	</li><br>
	
	<li>
		<ul style="padding-bottom: 10px;">
			<li style="float: left;">예약번호</li> 
			<li style="padding-left:11em"><%=dList.get(i).get("booking_no") %></li>
			<li style="float: left;">예약자 이름</li>
			<li style="padding-left:11em"><%=session.getAttribute("username") %></li>
		</ul>
	</li><hr>
	
	<li>
		<ul style="padding-top: 10px; padding-bottom: 10px;">
			<li style="font-weight: bold;">결제정보</li>
			<li style="float: left;">총 결제금액</li>
			<li style="padding-left:11em; color: #fe594d;"><%=dList.get(i).get("pay_total") %></li>
		</ul>
	</li><hr><br>
<button id="btnCancel"onclick="location.href='/booking/cancel?booking_no=<%=dList.get(i).get("booking_no")%>'">예약 취소</button>
<% } %>
</ul>
</div>

<%@ include file="/layout/footer.jsp" %>









