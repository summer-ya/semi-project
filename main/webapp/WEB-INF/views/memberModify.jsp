<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style type="text/css">

.wrap { text-align: center;}

.put {
	border: 1px solid #d9d9d9; width: 400px; height: 50px;
    margin: 5px; padding-left: 10px; border-radius: 5px;
    box-sizing: border-box; font-size: 17px;
}

#btnJoin{
	width: 400px; height: 50px; margin: 10px; border-radius: 5px; border: 0; cursor: pointer;
    background: linear-gradient(to left, #f857a6, #ff5858);
    color: #fff; font-size: 18px;
}

form > p {
	position: relative; left: 566px; font-size: 20px;  margin: 0;
	padding: 0; text-align: left; width: 400px;
}

#upfile {     display: inline-block; padding-top: 10px; }

</style>


<div class="wrap">
<h1>회원 정보 수정</h1><br><br>
	<form action="/member/modify" method="post" enctype="multipart/form-data" onsubmit="return chk()">
	
        <p>프로필 이미지 첨부 또는 수정</p>
        <input type="file" name="upfile" id="upfile" class="put"/><br><br>
        
		<p>이름</p>
		<input type="text" id="username" name="user_name" value="<%=session.getAttribute("username") %>" autofocus class="put"><br><br>
		
		<p>새 비밀번호</p> <p style="font-weight: normal; font-size: 11px;"> * 4자 이상 20자 이하</p>
		<input type="password" id="user_pw" name="user_pw" placeholder="비밀번호를 입력하세요" class="put"><br><br>
		
		<p>새 비밀번호 확인</p>
		<input type="password" id="user_pwc" name="user_pwc" placeholder="비밀번호를 입력하세요" class="put"><br><br>
	
		<button id="btnJoin" type="submit">수정 완료</button>
	</form>
</div>


<script type="text/javascript">
const chk = () => {
	//변수선언
	const inputName = document.querySelector("#username");
	const inputPw = document.querySelector("#user_pw");
	const inputPwChk = document.querySelector("#user_pwc");
	
	//이름이 비어있을 경우
	if(inputName.value == ""){
		alert("이름을 입력하세요");
		inputName.focus();
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
	
    alert("✔ 정보 수정이 완료되었습니다!");
	return true;
	
}
</script>

<%@ include file="/layout/footer.jsp" %>