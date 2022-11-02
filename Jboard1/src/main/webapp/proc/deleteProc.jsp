<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	String filecheck = request.getParameter("file");
	String path = application.getRealPath("/file");

	BoardArticleDAO badao = new BoardArticleDAO();
	badao.deleteArticle(no, Integer.parseInt(filecheck), path);
	badao.close();
	
	response.sendRedirect("/Jboard1/list.jsp?pg="+pg+"&result=202");
%>