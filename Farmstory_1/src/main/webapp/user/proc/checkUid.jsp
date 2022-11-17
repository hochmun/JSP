<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardUserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String uid = request.getParameter("uid");

	int result = BoardUserDAO.getInstance().uidCheck(uid);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);
%>