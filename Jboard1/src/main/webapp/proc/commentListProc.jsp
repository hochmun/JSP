<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String parent = request.getParameter("parent");

	BoardArticleDAO badao = new BoardArticleDAO();
	List<BoardArticleBean> babs = badao.CommentList(parent);
	badao.close();
	
	Gson gson = new Gson();
	String jsonData = gson.toJson(babs);
	
	out.print(jsonData);
%>