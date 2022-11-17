<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String group		= request.getParameter("group");
	String cate			= request.getParameter("cate");
	String pg			= request.getParameter("pg");
	String no			= request.getParameter("no");
	String filecheck	= request.getParameter("file");
	String path			= application.getRealPath("/file");
	
	BoardArticleDAO.getInstance().deleteArticle(no, Integer.parseInt(filecheck), path);
	
	response.sendRedirect("/Farmstory_1/Board/list.jsp?group="+group+"&cate="+cate+"&pg="+pg);
%>