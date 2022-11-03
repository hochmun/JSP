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
		$(function(){
			$('.btnOrder').click(function(){
				const prodNo = $(this).val();
				$('section').show().find('input[name=orderProduct]').val(prodNo);
			});
			
			$('.btnClose').click(function(){
				$('section').hide();
			});
			
			$('input[type=submit]').click(function(){
				const orderProduct 	= $('input[name=orderProduct]').val();
				const orderCount 	= $('input[name=orderCount]').val();
				const orderId 		= $('input[name=orderId]').val();
				
				const jsonDate = {
						"orderProduct"	:orderProduct,
						"orderCount"	:orderCount,
						"orderId"		:orderId
				}
				
				$.post('./data/order.jsp',jsonDate, function(data){
					if(data.result > 0) {
						alert('주문성공!');
						// 페이지 이동
						location.href="/shop/order.jsp";
					} else {
						alert("주문실패!");
					}
				});
			});
		});
		/*
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
		*/
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
					<button class="btnOrder" value="<%= pb.getProdNo() %>">주문</button>
				</td>
			</tr>
			<% } %>
		</table>
		
		<section style="display: none;">
			<h4>주문하기</h4>
			<table border='1'>
				<tr>
					<td>상품번호</td>
					<td><input type='text' name='orderProduct' 
					readonly='readonly' value=''></td>
				</tr>
				<tr>
					<td>수량</td>
					<td><input type='text' name='orderCount'></td>
				</tr>
				<tr>
					<td>주문자</td>
					<td><input type='text' name='orderId'></td>
				</tr>
				<tr>
					<td colspan='2' align='right'>
					<input type='submit' id='btnProductOrder' value='주문하기'></td>
				</tr>
			</table>
			<button class="btnClose">닫기</button>
		</section>
	</body>
</html>