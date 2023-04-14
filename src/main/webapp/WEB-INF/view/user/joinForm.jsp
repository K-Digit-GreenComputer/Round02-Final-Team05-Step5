<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
		<div class="mb-3 mt-3">
			<input id="username" name="username" type="text" class="form-control" placeholder="아이디를 입력해주세요" maxlength="20">
			<button id="SameCheck" type="button"
				class="btn btn-warning" onclick="sameCheck()">아이디 중복확인</button>
		</div>
		<div class="mb-3">
			<input id="password" name="password" type="password" class="form-control" placeholder="비밀번호를 입력해주세요" maxlength="20">
		</div>
		<div class="mb-3">
			<input id="email" name="email" type="email" class="form-control" placeholder="이메일을 입력해주세요" maxlength="20">
		</div>
		<button id="btnJoin" name="join" type="submit" class="btn btn-primary">회원가입</button>
</div>

<script>
        let submitCheck = false;
        let userNameCheckEvent = "";

        // function capitalLetter() {	// 영어만 입력 가능
        // 	let username = $("#userName").val();
        // 	let capiRule = /[a-zA-Z]/;
        // 	if (capiRule.test(username)) {
        // 		return true;
        // 	} else {
        // 		return false;
        // 	}
        // }       

        function emailCheck(){
            var email = $("#email").val();
            var emailRule= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            //이메일 형식실패
            if (email.match(emailRule)==null) {
                return true;
            } else {
                return false;
            }
        }

        function blankUserName() {	// 아이디 공백 || 띄어쓰기 막아줌
	    let username = $("#username").val();
	    let blank = /\s/g; // 띄어쓰기 막아줌
    	if(!username || blank.test(username)){
	    	return true;
	        }
        }


        function valid() {
            if (submitCheck == true) {
                return true;
            } else {
                alert("아이디를 중복체크를 해주세요");
                return false;
            }
           
        }
        function sameCheck() {
            let username = $("#username").val();
            $.ajax({
                type: "get",
                url: "/user/usernameSameCheck?username=" + username
            }).done((res) => {
                console.log(res);
                if (res.data === true) {
                    alert(res.msg);
                    submitCheck = true;
                    userNameCheckEvent = $("#username").val();
                } else {
                    alert(res.msg);
                    submitCheck = false;
                }
            }).fail((err) => {
            });
        }


        $("#btnJoin").click(()=>{join();})

//현재 alert에 이메일이나 비밀번호 미입력시 값이 안뜸
//브라우저에 값은 들어옴
function join(){
	if(submitCheck == false){
		alert("아이디 중복 확인을 해주세요");
		return; 
	}
    if (userNameCheckEvent != $("#username").val()) {
        alert("아이디 중복 확인을 다시 해주세요")
        return;
    }
    if (blankUserName() ==true) {
        alert("아이디를 다시 입력해주세요");
        return;
    }
    if (emailCheck() == true) {
        alert("이메일 형식을 확인해주세요");
        return;
    }
    // if (capitalLetter() == true) {
    //     alert("아이디는 영어로 입력해주세요");
    //     return;
    // }
	
	let data ={
		username : $("#username").val(),
		password : $("#password").val(),
		email : $("#email").val()
	};
	
	$.ajax({
		type : "POST",
        url : "/join",
		dataType : "json",
		data : JSON.stringify(data), //위의 data를 넣을건데, 그냥 넣으면 JS오브젝트로 접근됨→JSON으로 바꿔서 통신!
		headers :{ //http header에 들고 갈 요청 데이터
			"Content-Type" : "application/json"	//json 타입의 컨텍트 타입을 날림
		}
	}).done((res)=> {
		if(res.code == 1){
			alert("회원가입에 성공하였습니다.");
			location.replace("/loginForm");
		}else {
			alert("회원가입에 실패하였습니다.");
			location.replace("/joinForm");
		}
	}).fail((err)=>
    {
    alert(err.responseJSON.msg); });
}


</script>


<%@ include file="../layout/footer.jsp"%>
