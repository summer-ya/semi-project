<%@page import="dto.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Room rotelDetail = (Room) request.getAttribute("roomDetail"); %>
<%
// Controller가 전달한 객체 가져오기.
List<Room> roomList = (List) request.getAttribute("roomList");
%>

<style type="text/css">

table {
	border-collapse: collapse;
	width: 600px;
	margin: 60px auto;
}

td {
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	text-align: center;
	padding: 5px 10px;
	font-size: 20px;
	font-weight: bold;
	
}

tbody tr:hover { background-color: skyblue; }

/* "메인으로" 버튼 높이 */
tfoot th { height: 80px; }

.htimg {
display: block;
width: 600px;
height: 280px;
margin: 0px auto;

}

</style>


</head>


<body>

	<table>

		<% for (int i = 0; i < roomList.size(); i++) { %>
		<thead>
		<tr>
			<th colspan="6">
			<a href="/detail?no=<%=roomList.get(i).getHotel_no()%>"><img src="/upload/<%=roomList.get(i).getRoom_img() %>" class="roomimg" alt="이미지 아님"></a>
			</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<a href="/detail?hotel_no=<%=roomList.get(i).getHotel_no()%>">
						<%=roomList.get(i).getRoom_type() %>
					</a>
				</td>
				
				<td><%=roomList.get(i).getPeople() %></td>
			<td><%=roomList.get(i).getMax_people() %></td>
			<td><%=roomList.get(i).getRoom_price() %></td>
			</tr>
			<% } %>
		</tbody>
		<tfoot>
			<tr style=>
				<th colspan="7"><a href="/detail"><button class="button">메인으로</button></a></th>
			</tr>
		
		</tfoot>

	</table>
