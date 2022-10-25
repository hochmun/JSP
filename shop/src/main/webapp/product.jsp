<%@page import="java.util.List"%>
<%@page import="kr.co.shop.DAO.ShopProductDAO"%>
<%@page import="kr.co.shop.bean.productBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ShopProductDAO spdao = new ShopProductDAO();
	List<productBean> pbs = spdao.ShopProductListDAO();
	spdao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shop::product</title>
	</head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script src="./js/order.js"></script>
	<script>
		$(document).ready(()=>{
			$(document).on('click','.btnOrder', function(e){
				e.preventDefault();
				const productList = $(this).parent().parent().children('td');
				order(productList);
			});
			
			// 주문 하기
			$(document).on('click','#btnProductOrder', function(){
				const orderProduct = $('input[name=orderProduct]').val();
				const orderCount = $('input[name=orderCount]').val();
				const orderId = $('input[name=orderId]').val();
				const jsonData = {
						"orderProduct":orderProduct,
						"orderCount":orderCount,
						"orderId":orderId
				};
				console.log(jsonData);
				
				$.ajax({
					url: './data/order.jsp',
					method: 'post',
					data: jsonData,
					dataType: 'json',
					success: (data)=>{
						if(data.result == 1) {
							alert('주문성공!');
						} else {
							alert("주문실패!");
						}
					}
				});
				
				//주문 끝나고 테이블 닫기
				$('section').empty();
				$('nav').empty();
			});
		});
	</script>
	<body>
		<h2>상품목록</h2>
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		<table border="1">
			<tr>
				<th>상품번호</th>
				<th>상품명</th>
				<th>재고량</th>
				<th>가격</th>
				<th>제조사</th>
				<th>주문</th>
			</tr>
			<% for (productBean pb : pbs) { %>
			<tr>
				<td><%= pb.getProdNo() %></td>
				<td><%= pb.getProdName() %></td>
				<td><%= pb.getStock() %></td>
				<td><%= pb.getPrice() %></td>
				<td><%= pb.getCompany() %></td>
				<td>
					<button class="btnOrder">주문</button>
				</td>
			</tr>
			<% } %>
		</table>
		<nav></nav>
		<section></section>
	</body>
</html>