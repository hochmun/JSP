<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String content = request.getParameter("content");
	String no = request.getParameter("no");

	BoardArticleDAO badao = new BoardArticleDAO();
	int result = badao.updateComment(content, no);
	badao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>