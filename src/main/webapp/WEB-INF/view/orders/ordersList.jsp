<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>구매 목록 페이지</h3>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>구매 상품명</th>
				<th>구매 상품가격</th>
				<th>구매 상품수량</th>
				<th>구매 취소</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderlist" items="${orderslist}">
				<tr>
					<td>${orderlist.ordersName}</td>
					<td>${orderlist.ordersPrice}</td>
					<td>${orderlist.ordersQty}</td>
					<td>
					<form action="/orders/${orderlist.ordersId}/cancle" method="post">
						<input type="hidden" name="ordersId" type="number" class="form-control" value="${orderlist.ordersId}">
						<input type="hidden" name="ordersName" type="text" class="form-control" value="${orderlist.ordersName}"">
						<input type="hidden" name="ordersPrice" type="number" class="form-control" value="${orderlist.ordersPrice}"">
						<input type="hidden" name="ordersQty" type="number" class="form-control" value="${orderlist.ordersQty}">
						<input type="hidden" name="productId" type="number" class="form-control" value="${orderlist.productId}">
						<button id="btnDelete" type="submit"  class="btn btn-danger">구매취소</button>
					</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../layout/footer.jsp"%>