<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/layout/header.jsp" %>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style type="text/css">
#join_wrap {
	text-align: center;
}

#joinform > p{  
	position: relative; left: 566px; font-size: 17px; font-weight: bold; margin: 0;
	padding: 0; text-align: left; width: 400px;
}

#joinform  .textput{
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 15px;
}

/* #useremail{ */
/* 	border: 1px solid #d9d9d9; width: 400px; height: 50px; */
/*     margin: 5px; padding-left: 10px; border-radius: 5px; position: relative; */
/*     box-sizing: border-box; font-size: 15px; font-weight: normal; left: -5px; */
/* } */

#btnJoin{
	width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

textarea{
	border: 1px solid #d9d9d9; width: 400px; height: 150px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 15px;
}
.check{ position: relative; left: -26px; }
.check1{ position: relative; left: -153px; font-size: 17px; }

#result{ font-size: 13px; }

</style>


<div id="join_wrap">
	<form action="/join" method="post" id="joinform" name="joinform" onsubmit="return sendLogin()">
	<h1>회원가입</h1><br><br>
		<p>이름</p>
		<input type="text" id="username" name="username" placeholder="예)홍길동" autofocus class="textput"><br><br>
		
		<p>이메일</p>
		<input type="text" id="useremail" name="useremail" placeholder="이메일을 입력하세요" class="textput">
		<p id="result">&nbsp;</p><br>
		
		<p>휴대폰 번호</p> 
		<input type="text" id="userphone" name="userphone" placeholder="휴대폰번호를 입력하세요" class="textput"><br><br>
		
		<p>비밀번호</p> <p style="font-weight: normal; font-size: 11px;"> * 4자 이상 20자 이하</p>
		<input type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력하세요" class="textput"><br><br>
		
		<p>비밀번호 확인</p>
		<input type="password" id="userpwChk" name="userpwChk" placeholder="비밀번호를 입력하세요" class="textput"><br><br>
		
		<input type="checkbox" id="chk" name="chk" class="check">
    	<label for="chk"><span class="check1">이용약관동의</span></label><br>
    	<textarea readonly>(회원가입)
① "이용자"는 "플랫폼"이 정한 절차에 따라 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다.
② "회사"는 제①항과 같이 회원으로 가입할 것을 신청한 "이용자" 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다.

1. 회원자격 상실 후 24시간이 경과하지 않은 경우
2. 등록 내용에 타인의 정보를 사용한 경우
3. 만 14세 미만의 아동이 신청하는 경우

③ 회원가입계약의 성립 시기는 "회사"의 승낙이 회원에게 도달한 시점으로 합니다.
④ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한 기간 이내에 "플랫폼"에 대하여 회원정보 수정하거나 E-mail 등의 방법으로 그 변경사항을 알려야 합니다.
⑤ "회사"는 관련법령에 따라 필요한 경우 별도의 성인인증 절차를 실시할 수 있습니다.</textarea><br>
	
		<button id="btnJoin">가입하기</button>
	</form>
</div>



<script type="text/javascript">
$(function(){
	$("#userphone").on('keyup', numReplace);
})

$(function(){
	$("#useremail").on('keyup', chkEmail);
})


function numReplace(){
	const inputNum = document.querySelector("#userphone");
	inputNum.value = inputNum.value.replace(/-/gi, '');
}

function chkEmail() {
    var email = $('#useremail').val(); 
    
    $.ajax({
        url: '/emailChk', //Controller에서 요청 받을 주소
        type: 'POST', //POST 방식으로 전달
        data: {
             "useremail": email
         },

        success: function(res) { //컨트롤러에서 넘어온 res값을 받는다 
            if (res == 0) { //res가 1이 아니면(0일 경우) -> 사용 가능한 아이디 
                $("#result").text("✔ 사용할 수 있는 이메일입니다").css("color", "#37a9f5");
                console.log(email);

            } else { // res가 1일 경우 -> 이미 존재하는 아이디
                $("#result").text("❌ 이미 존재하는 이메일입니다").css("color", "#e42f0a");
            }
       	}
        });
}


const sendLogin = () => {
	
	//변수선언
	const inputName = document.querySelector("#username");
	const inputEmail = document.querySelector("#useremail");
	const inputNum = document.querySelector("#userphone");
	const inputPw = document.querySelector("#userpw");
	const inputPwChk = document.querySelector("#userpwChk");
	const chkBox = document.querySelector("#chk");
	
	
	//이름이 비어있을 경우
	if(inputName.value == ""){
		alert("이름을 입력하세요");
		inputName.focus();
		return false;
	}
	
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
    
  	//휴대폰번호가 비어있을 경우
	if(inputNum.value == ""){
		alert("휴대폰번호를 입력하세요");
		inputNum.focus();
		return false;
	}
    
 	// 핸드폰 번호 형식 정규식
    const expHpText = /^\d{3}\d{3,4}\d{4}$/;
    // userphone값이 정규식에 부합한지 체크
    if(!expHpText.test(inputNum.value)) {
        alert('10자리 혹은 11자리의 번호만 입력 가능합니다');
        inputNum.focus()
        return false;
    }
    
    //비밀번호가 비어있을 경우
    if(inputPw.value == ""){
		alert("비밀번호를 입력하세요");
		inputPw.focus();
		return false;
	}
    
 	// 비밀번호 확인칸이 비어있으면 실행.
    if(inputPwChk.value == '') {
        alert('비밀번호 확인을 입력해주세요.');
        inputPwChk.focus();
        return false;
    }
    // userpw값이 4자 이상 20자 이하를 벗어나면 실행.
    if(inputPw.value.length < 4 || inputPw.value.length > 20){
        alert("비밀번호는 4자 이상 20자 이하로 입력해주세요.");
        inputPw.focus();
        return false;
    }
    
 	// userpw값과 userpw_ch값이 다르면 실행.
    if(inputPw.value != inputPwChk.value) {
        alert('비밀번호가 다릅니다. 다시 입력해주세요.');
        inputPwChk.focus();
        return false;
    }
	
  	//이용약관에 체크 안되었을 시
	if(chkBox.checked == false){
		console.log("이용약관에 체크하세요");
		alert("이용약관에 체크하세요");
		return false;
	}
	
	alert("✔ 회원가입이 완료되었습니다!");
	return true;
}

</script>


<%@ include file="/layout/footer.jsp" %>









