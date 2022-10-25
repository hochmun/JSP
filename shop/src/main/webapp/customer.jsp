<%@page import="kr.co.shop.bean.customerBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.shop.DAO.ShopCustomerDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ShopCustomerDAO scdao = new ShopCustomerDAO();
	List<customerBean> cbs = scdao.CustomerListDAO();
	scdao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shop::customer</title>
	</head>
	<body>
		<h2>고객목록</h2>
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>주소</th>
				<th>가입일</th>
			</tr>
			<% for (customerBean cb : cbs) { %>
			<tr>
				<td><%= cb.getCustid() %></td>
				<td><%= cb.getName() %></td>
				<td><%= cb.getHp() %></td>
				<td><%= cb.getAddr() %></td>
				<td><%= cb.getRdate() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>