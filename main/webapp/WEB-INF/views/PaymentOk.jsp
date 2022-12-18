<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/layout/header.jsp"%>

<style type="text/css">

.joinOk {
	display: block;
	text-align: center;
	margin: 70px;
}


</style>

<div class="joinOk">
	<h2>결제에 성공했습니다.</h2>
	<a href="/payedList"><button class="button" style="background: #f7323f;">결제내역 확인하러 가기</button></a>
</div>



<%@ include file="/layout/footer.jsp"%>
