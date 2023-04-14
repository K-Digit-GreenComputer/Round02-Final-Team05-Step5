<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
		<input id="id" name="id" type="hidden" value="${userProfile.id}" />
		<div class="mb-3 mt-3">
			<span>아이디</span>
			<input type="text" class="form-control" placeholder="Enter username" value="${userProfile.username}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<span>비밀번호</span>
			<input id="password" type="password" class="form-control" placeholder="Enter password"
				value="${userProfile.password}">
		</div>
		<div class="mb-3">
			<span>이메일</span>
			<input id="email" type="email" class="form-control" placeholder="Enter email"
				value="${userProfile.email}">
		</div>
		<button type="submit" id="btnUpdate" class="btn btn-primary">회원수정완료</button>
    <button id="btnDelete" class="btn btn-danger">회원탈퇴</button>
</div>



<script>
$("#btnUpdate").click(()=>{
	userUpdate();
	});
$("#btnDelete").click(()=>{
	userDelete();
	});


function userUpdate(){
	let data ={
		password : $("#password").val(),
		email : $("#email").val()
	};
	
	$.ajax({
		type: "post",
        url: `/user/update` ,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        dataType: "json"
	}).done((res)=> {
		if(res.code == 1){
			alert("회원정보 수정 완료")
			location.replace("/"); //새로고침
		}else {
			alert("회원정보 수정 실패");
		}
	});
}

function userDelete(){
	$.ajax({
		type: "delete",
        url: `/user/delete`
	}).done((res)=> {
		if(res.code == 1){
			alert("회원탈퇴 완료")
			location.replace("/"); //새로고침
		}else {
			alert("회원탈퇴 실패");
		}
	});
}
</script>


<%@ include file="../layout/footer.jsp"%>