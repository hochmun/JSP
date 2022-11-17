<%@page import="com.google.gson.Gson"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");

	List<BoardArticleDTO> badtos = BoardArticleDAO.getInstance().selectLatests(cate);
	
	Gson gson = new Gson();
	String jsonData = gson.toJson(badtos);
	
	out.print(jsonData);
%>