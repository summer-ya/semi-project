<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&autoload=false"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2f47283a16fca78743abba9b8a1f5ba&libraries=services"></script>
<meta charset="UTF-8">
<title>KH 5조 세미프로젝트 - 여기서놀자</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- flagPickr -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_orange.css">
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/material_orange.css">

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/flatpicker.js"></script> --%>

<!-- 부트스트랩 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

		$(document).scroll(function () {
			var $nav = $(".header");
			$nav.toggleClass('scrolled', $(this).scrollTop() > $nav.height());
		});
</script>

<style type="text/css">

/* HEADER_STYLE */
html, body {
	height: 100%;
	font-family: 'Jua', sans-serif;
	letter-spacing: 1.5px;

}

.header {	
  	top: 0;
  	width: 100%;
	background-color: #f7323f;
	position: relative;
}

.header.scrolled {
	background-color: #B90000 !important;
	transition: background-color 200ms linear;
		}

.section {
	display: relative;
	width: 1024px;
	height: 72px;
	margin-left: 440.5px;
	margin-right: 440.5px;
	background-color: none;
	margin: 0 auto;	
}

.section h1 {
	display: inline-block;
	font-size: 27px;
	font-weight: bold;
	color: #fff;
}

.section h1 a {
	margin: 30px 20px;
	color: #fff;
	text-decoration: none;
}

.btn {
  	position : relative;
	display: inline-block;
	float: right;
	margin: 10px 80px;
}

.button {
	font-size: 18px;
	padding : 7px 10px;
	color: rgba(255,255,255,0.8);
	border: none;
	background: none;
}

.button:hover {
	color: #fff;
}

.dropdown {
    display: inline-block;
}

#dropdown-btn {
	color: rgba(255,255,255,0.8);
    border: none;
    cursor: pointer;
}

#dropdown-btn:hover {
	color: #fff;
}

.dropdown-submenu{
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.192);
    background-color: #fff;
    display: none;
}

.dropdown-submenu a {
    display: block;
    padding: 7px;
    text-align: center;
    color: #000;
	text-decoration: none;
}

.dropdown-submenu a:hover{
    background-color: #fff;
}

.dropdown:hover .dropdown-submenu {
    display: block;

}

/* CONTENT_STYLE */

.content {
	width: 100%;
	margin: 0 auto;
	height: auto;
	min-height: 100%;
	padding-bottom: 40px;
	padding-top: 72px;
}

</style>

</head>
<body>

<!-- HEADER -->


<!-- header 시작 -->
	<div class="header">
		<div class="section"> 
		<h1>
			<a href="/main" title="여기서놀자">여기서놀자</a>
		</h1>
		<div class="btn">
		<%	if( null != session.getAttribute("login") && (Boolean)session.getAttribute("login") ) { %>
		<% 		if( session.getAttribute("user_email").equals("admin@naver.com")) { %>		
			<a href="/hotel/insert"><button class="button">호텔등록</button></a>
			<a href="/room/info"><button class="button">객실등록</button></a>
			<a href="/list"><button class="button">문의게시판</button></a>
			<div class="dropdown">
					<a href="#"><button class="button" id="dropdown-btn">내정보</button></a>
				<div class="dropdown-submenu">
            		<a href="/member/modify">내정보수정</a>
            		<a href="/logout">로그아웃</a>
            	</div>
    		</div>
		<% } else { %>
			<a href="/list"><button class="button">문의게시판</button></a>
			<a href="/hotel/list"><button class="button">숙소예약</button></a>
			<div class="dropdown">
					<a href="#"><button class="button" id="dropdown-btn">내정보</button></a>
				<div class="dropdown-submenu">
            		<a href="/member/modify">내정보수정</a>
            		<a href="/booking/list">예약내역</a>
            		<a href="/payedList">결제(이용)내역</a>
            		<a href="/mark/list">찜한숙소</a>
            		<a href="/logout">로그아웃</a>
            	</div>
    		</div>
		<% } %>
		<% } %>
		<% if( null == session.getAttribute("login") || !(Boolean)session.getAttribute("login") ) { %>
			<a href="/list"><button class="button">문의게시판</button></a> 
			<a href="/hotel/list"><button class="button">숙소예약</button></a> 
			<a href="/login"><button class="button">로그인</button></a>
		<% } %>
		</div>
		</div>
	</div> 	
	
<!-- header 끝 -->
	
<!-- content 시작 -->
	<div class="content">
	
	
	