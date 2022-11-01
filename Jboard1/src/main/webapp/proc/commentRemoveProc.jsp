<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");

	BoardArticleDAO badao = new BoardArticleDAO();
	badao.deleteCommentNumber(parent); // 댓글 삭제시 댓글 갯수 감소
	int result = badao.removeComment(no); // 댓글 삭제
	badao.close();

	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json.toString());
%>