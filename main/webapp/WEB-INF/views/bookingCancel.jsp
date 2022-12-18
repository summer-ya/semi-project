<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>
<% List<Map<String, Object>> dList = (List) request.getAttribute("dList"); %>

<style type="text/css">
ul{ list-style: none; display: inline-block; padding-left: 2px; margin-bottom: 100px;}

#tit { position: relative; text-align: center; }

#btnBack{
	width: 180px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

#btnNext{
	width: 180px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

.cWrap{ position: relative; left: 567px; top:50px; font-size: 17px; display: inline-block; }

</style>

<h1 id="tit">예약 취소</h1>

<div class="cWrap">
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
		<ul style="background: #fe594d; padding: 10px 10px 10px 12px;">
			<li style="color: #fff;">⛔ 환불금액 및 환불내용을 확인하신 후</li> 
			<li style="color: #fff;">정말 예약취소를 원하시면 취소를 진행해주세요.</li>
		</ul>
	</li><br>
	
	<li>
		<ul style="padding-bottom: 10px;">
			<li><p style="font-weight: bold;">환불 예정 정보</p></li>
			<li style="float: left;">결제금액 
			<li style="padding-left:11em"><%=dList.get(i).get("pay_total") %></li>
			<li style="float: left;">환불방법 
			<li style="padding-left:11em"><%=dList.get(i).get("pay_kind") %></li>
		</ul>
	</li><hr>
	
	<li>
		<ul style="padding-top: 10px;">
			<li style="float: left;">최종 환불금액
			<li style="padding-left:11em; color: #fe594d;"><%=dList.get(i).get("pay_total") %></li>
		</ul>
	</li><hr>
	
	<li>
		<ul style="padding-bottom: 30px;">
			<li>취소, 변경, 환불 등</li>
			<li style="font-size: 13px;">① 서비스 이용에 대한 취소 및 환불규정은 전자상거래 등에서의 소비자보호에 관한 법령을 준수합니다.</li>
            <li style="font-size: 13px;">② "제휴판매자"는 별도의 취소 및 환불규정을 제정할 수 있으며 </li>
            <li style="font-size: 13px;">&nbsp;이를 상세페이지에 기재하고 "이용자"의 동의를 받은 경우 우선 적용됩니다.</li>
            <li style="font-size: 13px;">③ "회사"는 "제휴판매자" 에게 전②항의 규정이 없는 경우를 위하여</li>
            <li style="font-size: 13px;">&nbsp;시설 별 취소환불규정을 제정할 수 있으며 이를 상세페이지에 기재하고 "이용자"의 동의를 받아 적용합니다.</li>
		</ul>
	</li>
<button id="btnBack" onclick="history.back(-1)">뒤로가기</button>
<button id="btnNext" onclick="location.href='/booking/delete?booking_no=<%=dList.get(i).get("booking_no")%>'">취소 진행</button>
<% } %>
</ul>
</div>

<%@ include file="/layout/footer.jsp" %>