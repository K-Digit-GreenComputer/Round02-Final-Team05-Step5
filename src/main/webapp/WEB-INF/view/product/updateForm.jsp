<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>상품 수정 페이지</h3>
	<form action="/product/${product.id}/update" method="post">
		<input id="id" type="hidden" value="${product.id}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>상품명</th>
					<th>상품가격</th>
					<th>상품수량</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input id="name" name="name" type="text"
						class="form-control" value="${product.name}"></td>
					<td><input id="price" name="price" type="number"
						class="form-control" value="${product.price}"></td>
					<td><input id="qty" name="qty" type="number"
						class="form-control" value="${product.qty}"></td>
				</tr>
			</tbody>
		</table>
		<div align="center">
			<button id="btnInsert" type="submit" class="btn btn-primary">상품수정완료</button>
		</div>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>