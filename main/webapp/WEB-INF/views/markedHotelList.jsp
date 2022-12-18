<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="dto.Hotel"%>
<% List<Hotel> list = (List) request.getAttribute("markedHotelList"); %>
<%@ include file="/layout/header.jsp" %>
<meta charset="UTF-8">
<title>찜한 숙소 목록</title>
<style>
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

<% int mo = list.size()/3; %>
<% int na = list.size()%3; %>
<% int newStart  = 0; %>

<h2 style="text-align: center;">내가 찜한 숙소</h2><br>

<div id="hWrap">
<ul>
<% for(int i = 0; i < list.size(); i++) { %>
<li class="hlist">
		<ul>
            <a href="/hotel/detail?hotel_no=<%= list.get(i).getHotel_no() %>">
            <div class="imgBox" style="background-image: url('/upload/<%= list.get(i).getHotel_photo() %>');"></div>
			<li class="titBox">><%= list.get(i).getHotel_name() %></li>
            <li class="ck"><span>찜한 사람 <%= list.get(i).getMark_hit() %>명</span></li>
            </a>
		</ul>
	</li>
<% } %>
</ul>
</div>

<%@ include file="/layout/footer.jsp" %>