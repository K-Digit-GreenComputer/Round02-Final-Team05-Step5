<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>상품 등록 페이지</h3>
	<form action="/product/add" method="post">
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
					<td><input name="name" id="name" type="text"
						class="form-control" placeholder="상품명을 입력하세요"></td>
					<td><input name="price" type="number"
						class="form-control" placeholder="가격을 입력하세요"></td>
					<td><input name="qty" type="number"
						class="form-control" placeholder="수량을 입력하세요"></td>
				</tr>
			</tbody>
		</table>
		<div align="center">
			<button id="SameCheck" type="button"
				class="btn btn-warning" onclick="sameCheck()">상품명 중복 확인</button>
			<button id="btnInsert" type="submit" class="btn btn-primary">상품등록완료</button>
		</div>
	</form>
</div>

<script>
        let submitCheck = false;
        function valid() {
            if (submitCheck) {
                return true;
            } else {
                alert("제품이름을 중복체크를 해주세요");
                return false;
            }
        }
        function sameCheck() {
            let productname = $("#name").val();
            $.ajax({
                type: "get",
                url: "/product/productnameSameCheck?productname=" + productname
            }).done((res) => {
                //console.log(res);
                if (res.data === true) {
                    alert(res.msg);
                    submitCheck = true;
                } else {
                    alert(res.msg);
                    submitCheck = false;
                }
            }).fail((err) => {
            });
        }
</script>

<script src="/js/insert.js"></script>
<%@ include file="../layout/footer.jsp"%>