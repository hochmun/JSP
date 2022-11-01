<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cno = request.getParameter("no");

	BoardCommentDAO bcdao = new BoardCommentDAO();
	int result = bcdao.removeComment(cno);
	bcdao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>