<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>상품 상세보기 페이지</h3>
	<form onsubmit="return validateForm()" action="/orders/${product.id}" method="post" >
	<table class="table table-striped">
		<thead>
			<tr>
				<th>상품명</th>
				<th>상품가격</th>
				<th>상품수량</th>
				<c:if test="${principal.role == 'user'}">
				<th>구매수량</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.qty}</td>
				<c:if test="${principal.role == 'user'}">
				<td width="150"> <input id="ordersQty" name="ordersQty" type="number"  min="1" class="form-control"></td>
				</c:if>
			</tr>
		</tbody>
	</table>
	<div class="d-flex">
		<input type="hidden" name="ordersName" type="text" class="form-control" value="${product.name}">
		<input type="hidden" name="ordersPrice" type="text" class="form-control" value="${product.price}">
		<input type="hidden" name="productId" type="text" class="form-control" value="${product.id}">
		<c:if test="${principal.role == 'user'}">
			<button id = "buy" type="submit" class="btn btn-primary" >구매하기</button>
		</c:if>
	</form>
	<c:if test="${principal.role == 'admin'}">
		<a href="/product/${product.id}/updateForm" class="btn btn-warning">상품수정</a>
		<form action="/product/${product.id}/delete" method="post">
			<button id="btnDelete" type="submit"  class="btn btn-danger">상품삭제</button>
		</form>
	</c:if>
</div>

<script>
    function validateForm() {
		let ordersQty = $("#ordersQty").val();
        if (${product.qty}-ordersQty<0) {
            alert("상품 구매가 불가능합니다. 재고를 확인해주세요.");
            return false; // 폼을 제출하지 않음
        }
        return true; // 폼을 제출함
    };
</script>

<%@ include file="../layout/footer.jsp"%>
