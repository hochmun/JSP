<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no	= request.getParameter("no");
	String pg	= request.getParameter("pg");
	String cate	= request.getParameter("cate");
	String group	= request.getParameter("group");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardArticleDTO badto = new BoardArticleDTO();
	badto.setTitle(title);
	badto.setContent(content);
	badto.setNo(no);
	
	BoardArticleDAO.getInstance().updateArticle(badto);
	
	response.sendRedirect("/Farmstory_1/Board/view.jsp?group="+group+"&cate="+cate+"&pg="+pg+"&no="+no);
%>