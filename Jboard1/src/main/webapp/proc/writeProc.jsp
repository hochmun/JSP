<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	String title 	= request.getParameter("title");
	String content 	= request.getParameter("content");
	String uid 		= request.getParameter("uid");
	String regip 	= request.getRemoteAddr();
	
	// 데이터 베이스 처리
	BoardArticleDAO badao = new BoardArticleDAO();
	badao.InsertBoardArticleDAO(title, content, uid, regip);
	badao.close();
	
	// 목록으로 이동
	response.sendRedirect("/Jboard1/list.jsp");
%>