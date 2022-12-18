<%@page import="dto.SortedHotel"%>
<%@page import="dto.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>

<%@ include file="/layout/header.jsp"%>
<% List<SortedHotel> hotelList = (List) request.getAttribute("hotelList"); %>
    
<style type="text/css">

table { border-collapse: collapse; width: 600px; margin: 60px auto; }

td { border-top: 1px solid #ccc; border-bottom: 1px solid #ccc; text-align: center;
	 padding: 5px 10px; font-size: 20px; font-weight: bold; }

.sortBtn { width: 80px; height: 40px; color: #fff; 
 		   background: #FF5050; border:none; border-radius: 5px; }

.sort { text-align: center; }

tbody tr:hover { background-color: skyblue; }

tfoot th { height: 80px; }

.htimg { display: block; width: 600px; height: 280px; margin: 0px auto; }

</style>

<script type="text/javascript">
$(document).ready(function() {
 		
	$("#sortBtn").click(function() {
 		$.ajax({
 			type: "POST",
 			url: "/hotel/sortcount",
 			datatype: "html",
 			success: function(data) {
 				console.log("성공");
 				$("#listView").html(data);
 			}
 				
 		})
 				
 	})
 	
 	$("#sortBtn2").click(function() {
 		$.ajax({
 			type: "POST",
 			url: "/hotel/score",
 			datatype: "html",
 			success: function(data) {
 				console.log("성공");
 				$("#listView").html(data);
 			}
 				
 		})
 				
 	})
 	
 	$("#sortBtn3").click(function() {
 		$.ajax({
 			type: "POST",
 			url: "/hotel/list",
 			datatype: "html",
 			success: function(data) {
 				console.log("성공");
 				$("#listView").html(data);
 			}
 				
 		})
 				
 	})
 	
 	
 	
})
</script>
   
 	<div class="sort">
 		<button class="sortBtn" id="sortBtn">리뷰많은순</button>
 		<button class="sortBtn" id="sortBtn2">별점높은순</button>
 		<button class="sortBtn" id="sortBtn3">최신순</button>
 	</div>   
 	
 	<div id="listView">
    
    <table>
	<% for (int i = 0; i < hotelList.size(); i++) { %>
		<thead>
			<tr>
				<th colspan="6">
				<a href="/hotel/detail?hotel_no=<%=hotelList.get(i).getHotel_no()%>"><img src="/upload/<%=hotelList.get(i).getHotel_photo() %>" class="htimg" alt="이미지 아님"></a></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><h3><%=hotelList.get(i).getHotel_name() %></h3></td>
				<td rowspan="2"><%=hotelList.get(i).getHotel_tel() %></td>
			</tr>
			<tr>
				<td>리뷰 (<%=hotelList.get(i).getHotel_reviewCnt() %>) &nbsp;&nbsp; 
				    별점 <%=hotelList.get(i).getHotel_score() %></td> 
			</tr>
			<% } %>
		</tbody>
		<tfoot>
			<tr style=>
				<th colspan="7"><a href="/main"><button class="button">메인으로</button></a></th>
			</tr>
		
		</tfoot>

	</table>
    </div>
    
    
    <%@ include file="/layout/footer.jsp"%>