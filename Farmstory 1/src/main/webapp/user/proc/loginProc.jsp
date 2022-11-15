<%@page import="java.io.Console"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardUserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");
	String password = request.getParameter("password");
	
	// 유저 정보 받기
	BoardUserDTO budto = BoardUserDAO.getInstance().selectUser(uid, password);
	
	// 아이디 일치 또는 불일치
	if (budto != null) {
		session.setAttribute("Farmstory_1User", budto);
		response.sendRedirect("/Farmstory_1/");
	} else {
		response.sendRedirect("/Farmstory_1/user/login.jsp?error=101");
	}
%>