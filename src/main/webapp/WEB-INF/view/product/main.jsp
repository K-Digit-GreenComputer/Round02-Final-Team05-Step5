<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>상품 목록 페이지</h3>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>상품명</th>
				<th>상품가격</th>
				<th>상품수량</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="productList" items="${productList}">
				<tr>
					<td>${productList.name}</td>
					<td>${productList.price}</td>
					<td>${productList.qty}</td>
					<td>
						<ul class="navbar-nav">
							<li class="nav-item"><a href="/product/${productList.id}">상세보기</a></li>
						</ul>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../layout/footer.jsp"%>