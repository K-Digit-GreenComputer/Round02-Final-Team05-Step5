<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
      <div class="mb-3 mt-3">
         <input id="username"  type="text" class="form-control" VALUE="ssar" placeholder="아이디를 입력해주세요" >
      </div>
      <div class="mb-3">
         <input id="password" type="password" class="form-control" VALUE="1234" placeholder="비밀번호를 입력해주세요">
      </div>
      <button type="submit" class="btn btn-primary" onclick="login()">로그인</button>
</div>

<%@ include file="../layout/footer.jsp"%>

<script>
let username = "";
let password = "";
function login(){
   let data = {
          username = $("#username").val(),
          password =  $("#password").val()
   };
  // 로그인 요청 보내기
  $.ajax ({
   url: '/login',
   type: 'POST',
   contentType: "application/json;charset=UTF-8",
   data: JSON.stringify(data),
   dataType: "json"
  });
  
    .done(response => {
      if (response.ok) {
        // 로그인 성공
        window.location.href = '/';
      } else {
        // 로그인 실패
        alert('로그인에 실패하였습니다. 다시 시도해주세요.');
      }
    })
    .fail(error => {
      console.error('로그인 요청 실패:', error);
    });
}
</script>