<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.shop.DAO.ShopOrderDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String orderProduct	= request.getParameter("orderProduct");
	String orderCount	= request.getParameter("orderCount");
	String orderId	= request.getParameter("orderId");
	
	int result = 0;
	
	ShopOrderDAO sodao = new ShopOrderDAO();
	result = sodao.ShopOrderAdd(orderId, orderProduct, orderCount);
	sodao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>