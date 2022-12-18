<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<style type="text/css">
#login_wrap{ text-align: center; }

#useremail {
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 15px;
}

#userpw {
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 15px;
}

#btnLogin{
	width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

.findjoin {
	text-align: left; font-size: 14px; font-weight: bold;
    margin: 0 0 10px 10px; cursor: pointer; color: #e32a62;
    
}
</style>


<div id="login_wrap">
	<form action="/login" method="post" name="loginform" onsubmit="return sendMain()">
	<h1>로그인</h1><br><br>
		<input type="text" name="useremail" id="useremail" class="input" placeholder="이메일" autofocus><br>
		<input type="password" name="userpw" id="userpw" class="input" placeholder="비밀번호"><br>
		<span id="errorMsg"></span><br><br>
		<a href="/findPw" class="findjoin" style="position: relative; left: -158px;">비밀번호찾기</a><br>
		<button id="btnLogin">로그인</button>
	</form>
	<p>회원이 아니신가요? <a href="/join" class="findjoin">가입하기</a></p>
</div>


<script type="text/javascript">
$(function(){
	$("#userpw").on('keyup', chkLogin);
})


function chkLogin() {
    var email = $('#useremail').val();
    var pw = $('#userpw').val();
    
    $.ajax({
        url: '/loginChk', //Controller에서 요청 받을 주소
        type: 'POST', //POST 방식으로 전달
        data: {
             "useremail": email, "userpw": pw
         },

        success: function(res) { //컨트롤러에서 넘어온 res값을 받는다 
            if (res == 1) { //아이디 혹은 비번 불일치 
                $("#errorMsg").text("❌ 이메일 혹은 비밀번호가 일치하지 않습니다").css("color", "#e42f0a");
            	console.log("불일치")
                $('#btnLogin').attr('disabled', true);

            } else { //아이디/비번 일치
                $("#errorMsg").text("");
            	console.log("일치")
            	$('#btnLogin').attr('disabled', false);
            }
       	}
        });
}


const sendMain = () => {
	
	//변수선언
	const inputEmail = document.querySelector("#useremail");
	const inputPw = document.querySelector("#userpw");
	
	//이메일이 비어있을 경우
	if(inputEmail.value == ""){
		alert("이메일을 입력하세요");
		inputEmail.focus();
		return false;
	}
	
	// 이메일 형식 정규식
    const expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9\.\-]+$/;
    // 이메일값이 정규식에 부합한지 체크
    if(!expEmailText.test(inputEmail.value)) {
        alert("올바른 이메일 형식이 아닙니다");
        inputEmail.focus();
        return false;
    }
    
    //비밀번호가 비어있을 경우
    if(inputPw.value == ""){
		alert("비밀번호를 입력하세요");
		inputPw.focus();
		return false;
	}
	
	return true;
}
</script>


<%@ include file="/layout/footer.jsp" %>
