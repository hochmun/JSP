<%@page import="kr.co.Jboard1.bean.BoardUserBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	// 데이터베이스 처리
	BoardUserDAO budao = BoardUserDAO.getInstance();	
	BoardUserBean bub = budao.Login(uid, pass);
	budao.close();
	// 아이디 일치 불일치
	if (bub != null) {
		session.setAttribute("sessUser", bub);
		response.sendRedirect("/Jboard1/list.jsp");
	} else {
		response.sendRedirect("/Jboard1/user/login.jsp?success=100");
	}
	
%>