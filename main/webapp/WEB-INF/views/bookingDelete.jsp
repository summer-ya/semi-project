<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>

<style type="text/css">

.wrap{ text-align: center; position: relative; top: 40px; }

#btn{width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

</style>

<div class="wrap">
<h1>취소가</h1>
<h1>완료되었습니다.</h1>
<br><br>
<button id="btn" onclick="location.href='/main'">홈으로 가기</button>
</div>

<%@ include file="/layout/footer.jsp" %>