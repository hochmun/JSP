<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");
	
	int result = BoardArticleDAO.getInstance().deleteComment(parent, no);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>