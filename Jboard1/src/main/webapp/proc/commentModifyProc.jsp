<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String content = request.getParameter("content");
	String cno = request.getParameter("cno");

	BoardCommentDAO bcdao = new BoardCommentDAO();
	int result = bcdao.updateComment(content, cno);
	bcdao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>