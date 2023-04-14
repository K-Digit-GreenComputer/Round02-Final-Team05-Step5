<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>상품 등록 페이지</h3>
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
					<td><input name="price" id="price" type="number"
						class="form-control" placeholder="가격을 입력하세요"></td>
					<td><input name="qty" id="qty" type="number"
						class="form-control" placeholder="수량을 입력하세요"></td>
				</tr>
			</tbody>
		</table>
		<div align="center">
			<button id="SameCheck" type="button"
				class="btn btn-warning" onclick="sameCheck()">상품명 중복 확인</button>
			<button id="btnInsert" type="submit" class="btn btn-primary">상품등록완료</button>
		</div>
</div>

<script>
        let submitCheck = false;
		let productNameCheckEvent = "";
		
        function valid() {
            if (submitCheck==true) {
                return true;
            } else {
                alert("제품이름을 중복체크를 해주세요");
                return false;
            }
        }
		// 상품등록
		function insertProduct() {
			if(blankProductName() == true){
				alert("상품명을 입력해주세요");
				return
			}
		}

		function blankProductName() {	// 상품명 공백 || 띄어쓰기 막아줌
			let productName = $("#name").val();
			let blank = /\s/g;
			if(!productName || blank.test(productName)){
				return true;
			}
		}
		function numProductPrice() {	// 상품가격에 숫자만 입력 가능
			let productPrice = $("#price").val();
			let numRule = /^[0-9]+$/;
			if (numRule.test(productPrice)) {
				return true;
			} else {
				return false;
			}
		}
		function numProductQty() {	// 상품수량에 숫자만 입력 가능
			let productQty = $("#qty").val();
			let numRule = /^[0-9]+$/;
			if (numRule.test(productQty)) {
				return true;
			} else {
				return false;
			}
		}



        function sameCheck() {
            let productname = $("#name").val();
            $.ajax({
                type: "get",
                url: "/product/productnameSameCheck?productname=" + productname
            }).done((res) => {
                console.log(res);
                if (res.data === true) {
                    alert(res.msg);
                    submitCheck = true;
					productNameCheckEvent = $("#name").val();
                } else {
                    alert(res.msg);
                    submitCheck = false;
                }
				
            }).fail((err) => {
            });
		}
			 
		$("#btnInsert").click(()=>{productAdd();})

	function productAdd(){

	if(submitCheck == false){
		alert("제품이름 중복 확인을 해주세요");
		return; 
	}
	if(productNameCheckEvent != $("#name").val()){
		alert("제품이름 중복 확인을 다시 해주세요");
		return; 
	}
    if (blankProductName() ==true) {
        alert("제품이름을 다시 입력해주세요");
        return;
    }
    function insertProduct() {
	if(numProductPrice() == false){
		alert("상품가격에 숫자만 입력해주세요");
		return;
		}
	}
	// 상품등록
	function insertProduct() {
		if(numProductQty() == false){
			alert("상품수량에 숫자만 입력해주세요");
			return;
		}
	}
	
	let data ={
		productname : $("#name").val(),
		price : $("#price").val(),
		qty : $("#qty").val()
	};
	
	$.ajax({
		type : "POST",
        url : "/product/add",
		dataType : "json",
		data : JSON.stringify(data), //위의 data를 넣을건데, 그냥 넣으면 JS오브젝트로 접근됨→JSON으로 바꿔서 통신!
		headers :{ //http header에 들고 갈 요청 데이터
			"Content-Type" : "application/json"	//json 타입의 컨텍트 타입을 날림
		}
	}).done((res)=> {
		if(res.code == 1){
			alert("제품등록에 성공하였습니다.");
			location.replace("/");
		}else {
			alert("제품등록에 실패하였습니다.");
			location.replace("/");
		}
	}).fail((err)=>
    {
    alert(err.responseJSON.msg); });
}
</script>

<%@ include file="../layout/footer.jsp"%>