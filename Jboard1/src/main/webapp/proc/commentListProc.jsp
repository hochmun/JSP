<%@page import="com.google.gson.Gson"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@page import="kr.co.Jboard1.bean.BoardCommentBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String parent = request.getParameter("parent");

	BoardCommentDAO bcdao = new BoardCommentDAO();
	List<BoardCommentBean> bcbs = bcdao.CommentList(parent);
	bcdao.close();
	
	Gson gson = new Gson();
	String jsonData = gson.toJson(bcbs);
	
	out.print(jsonData);
%>