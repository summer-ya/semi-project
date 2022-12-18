<%@page import="dto.Semi_User"%>
<%@page import="dto.ReviewImage"%>
<%@page import="dto.Review"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	List<Map<String, Object>> list =(List) request.getAttribute("list"); %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>


<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&autoload=false"></script>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
 <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<style>
table.type01 {
  border-collapse: collapse;
  text-align: center;
  line-height: 1.5;
  margin : 20px 10px;
}
table.type01 th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border: 1px solid #ccc;
}
table.type01 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border: 1px solid #ccc;
}
#bt{
  background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
    color: white;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
	 	$("#select").change(function() {		
			var hotel_no = <%=request.getParameter("hotel_no") %>
			var selectedOption = this.value;
			$.ajax({
				type: "GET" ,
				url: "/review/list?hotel_no="+hotel_no+"&selectedOption="+selectedOption,
				datatype: "html",
				success: function(data) {
					console.log("성공");
					$("#result").html(data);
				}
			}) 	
		}) 
		//----------------------------------------------------------------------------------------

		
		
		
		
	 	//----------------------------------------------------------------------------------------
	 	})
</script>

</head>
<body> 
     <div class="reviewArea">
        <div class="reviewHeader">
          <h2 class="subTitle">리뷰 총 ( <%=list.size() %> ) 개</h2>
          <select id="select">
          	<option value="byScore">추천순</option>
          	<option value="byDate">최신순</option>
          </select>
        </div>
 		<div id="result" class ="result">
 		<ul class="reviewList">
        <%	for(int i=0; i< list.size(); i++) { %>
          <li>
            <div class="profile">
                <div>
                  <span><%= ((Semi_User) list.get(i).get("u")).getUser_email() %> ||    </span><span><%= ((Review)list.get(i).get("r")).getReview_date() %>||    </span><span><%=((Review)list.get(i).get("r")).getRoom_type() %>||    </span>
                   <span><% for(int p=0; p<(int)((Review)list.get(i).get("r")).getReview_score(); p++ ) {%>
                   ⭐<% }%></span>
                 <img src="/upload/<%= ((Semi_User) list.get(i).get("u")).getUser_pic() %>" alt="X" width="100" height="100">
                </div>
            </div>
            <div class="reviewContent">
              <div class="imgArea" >
              <% for(int j=0; j < ((List<ReviewImage>) list.get(i).get("ri")).size()  ; j++) { %>
                <img src="/upload/<%=((List<ReviewImage>) list.get(i).get("ri")).get(j).getStoredname() %>" alt="이미지 아님" width="200" height="150" >
              <% } %>
              </div>
              <p>
                <%=((Review)list.get(i).get("r")).getReview_content()  %>
              </p>
              <input id="rno"value ="<%=((Review)list.get(i).get("r")).getReview_no()%>" style="display:none;">
            </div>
            <% if ( (((Semi_User) list.get(i).get("u")).getUser_email()).equals( (String) request.getSession().getAttribute("user_email") ) ) { %>
            <button id="bt"> 수정 </button>
           <% } %>
          </li>
          <%	} %> 
                  </ul>
          </div>
      </div>
</body>
</html>