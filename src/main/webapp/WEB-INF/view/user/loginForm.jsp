<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
   <form action="/login" method="post">
      <div class="mb-3 mt-3">
         <input name="username"  type="text" class="form-control" VALUE="ssar" placeholder="아이디를 입력해주세요" >
      </div>
      <div class="mb-3">
         <input name="password" type="password" class="form-control" VALUE="1234" placeholder="비밀번호를 입력해주세요">
      </div>
      <button type="submit" class="btn btn-primary">로그인</button>
   </form>
</div>

<%@ include file="../layout/footer.jsp"%>