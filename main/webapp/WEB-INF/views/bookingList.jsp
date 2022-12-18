<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>
<% List<Map<String, Object>> list = (List) request.getAttribute("list"); %>

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

<h2 style="text-align: center;">예약 내역</h2><br>

<div id="hWrap">
<ul>
<% for(int i = 0; i < list.size(); i++) { %>
	<li class="hlist">
		<ul>
			<a href="/booking/detail?booking_no=<%=list.get(i).get("booking_no") %>">
<%-- 			<li><a  href="/booking/detail?booking_no=<%=list.get(i).get("booking_no") %>"> --%>
<%-- 			<li><img src="/upload/<%=list.get(i).get("hotel_photo") %>" alt="이미지 아님"></li> --%>
			<div class="imgBox" style="background-image: url('/upload/<%=list.get(i).get("hotel_photo") %>');"></div>
			<li class="titBox"><%=list.get(i).get("hotel_name") %></li>
			<li class="ck"><span><%=list.get(i).get("hotel_in") %> <%=list.get(i).get("hotel_intime") %></span></li>
			</a>
		</ul>
	</li>
<% } %>
</ul>
</div>

<%@ include file="/layout/footer.jsp" %>