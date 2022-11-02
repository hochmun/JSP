<%@page import="com.mysql.cj.protocol.a.NativeConstants.IntegerDataType"%>
<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardArticleBean bab = new BoardArticleBean();
	bab.setTitle(title);
	bab.setContent(content);
	bab.setNo(Integer.parseInt(no));

	BoardArticleDAO badao = new BoardArticleDAO();
	badao.ModifyArticle(bab);
	badao.close();
	
	response.sendRedirect("/Jboard1/view.jsp?no="+no+"&pg="+pg+"&result=201");
%>