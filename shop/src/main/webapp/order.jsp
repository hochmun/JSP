<%@page import="kr.co.shop.DAO.ShopOrderDAO"%>
<%@page import="kr.co.shop.bean.orderBean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ShopOrderDAO sodao = new ShopOrderDAO();
	List<orderBean> obs = sodao.ShopOrderListDAO();
	sodao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shop::order</title>
	</head>
	<body>
		<h2>주문목록</h2>
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		<table border="1">
			<tr>
				<th>주문번호</th>
				<th>주문자</th>
				<th>주문상품</th>
				<th>주문수량</th>
				<th>주문일</th>
			</tr>
			<% for (orderBean ob : obs) { %>
			<tr>
				<td><%= ob.getOrderNo() %></td>
				<td><%= ob.getOrderName() %></td>
				<td><%= ob.getProductName() %></td>
				<td><%= ob.getOrderCount() %></td>
				<td><%= ob.getOrderDate() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>