<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@page import="java.util.Date"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Jboard1.bean.BoardCommentBean"%>
<%@page import="kr.co.Jboard1.bean.BoardUserBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	String parent = request.getParameter("no");
	String content = request.getParameter("content");
	//String pg = request.getParameter("pg");
	BoardUserBean bub = (BoardUserBean) session.getAttribute("sessUser");
	
	if(bub == null) {
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
	
	BoardArticleBean bab = new BoardArticleBean();
	bab.setParent(Integer.parseInt(parent));
	bab.setContent(content);
	bab.setRegip(request.getRemoteAddr());
	bab.setUid(bub.getUid());
	
	// 데이터베이스 처리
	BoardArticleDAO badao = new BoardArticleDAO();
	int result = badao.insertComment(bab);
	String date = badao.updateCommentNumber(parent);
	badao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("nick", bub.getNick());
	json.addProperty("date", date.substring(2, 10));
	json.addProperty("content", content);
	
	String jsonData = json.toString();
	out.print(jsonData);
	
	//response.sendRedirect("/Jboard1/view.jsp?no="+parent+"&&pg="+pg);
%>