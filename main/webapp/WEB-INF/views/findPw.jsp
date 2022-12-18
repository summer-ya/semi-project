<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style type="text/css">
#findPw_wrap {
	text-align: center;
}

#findPwform > p{  
	position: relative; left: 566px; font-size: 17px; font-weight: bold; margin: 0;
	padding: 0; text-align: left; width: 400px;
}

#findPwform > input{
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 15px;
}

#useremail{
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px; position: relative;
    box-sizing: border-box; font-size: 15px; font-weight: normal; left: -5px;
}

#btnFindPw{
	width: 180px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px; position: relative; left: -108px;
}

#btnCancel{
	width: 180px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px; position: relative; left: 108px; top: -70px;
}


</style>


<div id="findPw_wrap">
<h1>비밀번호 찾기</h1><br><br>
	<form action="/findPw" method="post" id="findPwform" name="findPwform"  onsubmit="return find_pw()">
		<p>이메일</p>
		<p><input type="text" id="useremail" name="useremail" placeholder="이메일을 입력하세요" autofocus><br><br>
		
		<p>휴대폰 번호</p>
		<input type="text" id="userphone" name="userphone" placeholder="휴대폰번호를 입력하세요"><br><br>
		
		<button id="btnFindPw">확인</button> 
	</form>
		<button id="btnCancel">취소</button>
</div>


<script type="text/javascript">
//취소 버튼
$("#btnCancel").click(function() {
	$(location).attr('href', '/login') //로그인으로 가기
})


$(function(){
	$("#userphone").on('keyup', numReplace);
})

function numReplace(){
	const inputNum = document.querySelector("#userphone");
	
	inputNum.value = inputNum.value.replace(/-/gi, '');
}


function find_pw(){
	const frm = document.findPwform;
	
	if(frm.useremail.value == ''){
		alert("이메일을 입력하세요")
		useremail.focus();
		return false;
	}
	
	if(frm.userphone.value == ''){
		alert("휴대폰번호를 입력하세요")
		userphone.focus();
		return false;
	}
	
	return true;
}
 
</script>



<%@ include file="/layout/footer.jsp" %>

