<%@page import="dto.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Hotel"%>

<% Hotel hotelinfo = (Hotel) request.getAttribute("hotelinfo"); %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


<style type="text/css">

.font {
	font-family: 'Jua', sans-serif;
}
</style>

<div id="map" style="width:1100px; height:400px;"></div> 

<!-- 카카오지도 실행 script -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&autoload=false"></script>

<!-- 카카오지도 주소->좌표로 반환하여 구현되는 라이브러리 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&libraries=services"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>	
	<script type="text/javascript">
			kakao.maps.load(function() {
				
			    var mapContainer = document.getElementById("map");
			    
			    var options = {
			    		
			    	// 지도 초기값 좌표
			        center: new kakao.maps.LatLng(33.450701, 126.570667),
					level: 3 // 확대시 레벨수치
					
			    };
				
			    // 비동기로 동적 지도구현하는경우 실행하는 콜백함수
			    kakao.maps.load(function() {
			        var map = new kakao.maps.Map(mapContainer, options);
			        
			        // DB에서 호텔주소 얻기
					var htadr = '<%=hotelinfo.getHotel_addr()%>';
					
			     	// 주소-좌표 변환
			        var geocoder = new kakao.maps.services.Geocoder();
			     	
					// 호텔 주소가 담긴 htadr을 세팅해줌
			        geocoder.addressSearch(htadr, function(result, status) {

			            // 정상적으로 검색이 완료됐으면 
			            if (status === kakao.maps.services.Status.OK) {
							
			            	// 해당 주소로 좌표 변환 후 세팅
			                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			                // 결과값으로 받은 위치를 마커로 표시
			                var marker = new kakao.maps.Marker({
			                    map: map,
			                    position: coords
			                });
			                
			            

			                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			                map.setCenter(coords);
			            };
			        });
			   	 });	
			});
	</script>
	<div class="font">
	<h2>기본정보</h2>
	<hr>
	<div>
	</div>
	정보 : <%=hotelinfo.getHotel_info()%>
	</div>