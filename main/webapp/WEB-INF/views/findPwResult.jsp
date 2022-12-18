<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>


<style type="text/css">
.not-found {text-align: center; position:relative; top: 100px;}
.found {text-align: center; position:relative; top: 100px;}

.not-found > strong { font-size: 20px; }

.found > p { font-size: 20px; }
.found > strong { font-size: 20px; text-decoration: underline; color: #e32a62;}

#btnBack {
	width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

#btnGologin {
	width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}
</style>

<div class="not-found">
<%	if(session.getAttribute("res") == null ) { %>
<strong>등록된 정보가 없습니다.</strong><br><br><br><br>
<button id="btnBack" onclick="location.href='/findPw'">다시 찾기</button>
<%	} %>
</div>

<div class="found">
<%if( session.getAttribute("res") != null && (boolean) session.getAttribute("res")) { %>
<p>회원님의 임시비밀번호는</p><br>
<strong>" <%=session.getAttribute("userpw") %> " 입니다.</strong><br><br><br>
<p>로그인 후 [내 정보 수정] 에서 변경하실 수 있습니다.</p><br><br>
<form action="/findPw/result" method="post">
<button id="btnGologin" onclick="location.href='/login'">로그인 하러가기</button>
</form>
<%	} %>
</div>


<%@ include file="/layout/footer.jsp" %>