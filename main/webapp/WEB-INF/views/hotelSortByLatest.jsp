<%@page import="dto.SortedHotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<SortedHotel> latestView = (List) request.getAttribute("latestView"); %>

    
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
 	
 	<div id="listView">
    
    <table>
	<% for (int i = 0; i < latestView.size(); i++) { %>
		<thead>
			<tr>
				<th colspan="6">
				<a href="/hotel/detail?hotel_no=<%=latestView.get(i).getHotel_no()%>"><img src="/upload/<%=latestView.get(i).getHotel_photo() %>" class="htimg" alt="이미지 아님"></a></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><h3><%=latestView.get(i).getHotel_name() %></h3></td>
				<td rowspan="2"><%=latestView.get(i).getHotel_tel() %></td>
			</tr>
			<tr>
				<td>리뷰 (<%=latestView.get(i).getHotel_reviewCnt() %>) &nbsp;&nbsp; 별점 <%=latestView.get(i).getHotel_score() %></td>
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
    
    
    