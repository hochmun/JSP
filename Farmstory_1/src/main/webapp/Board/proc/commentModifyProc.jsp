<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String content = request.getParameter("content");
	String no = request.getParameter("no");
	
	int result = BoardArticleDAO.getInstance().updateComment(content, no);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>