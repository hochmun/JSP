<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();
	
	BoardUserDAO user = new BoardUserDAO(); //BoardUserDAO.getInstance();
	user.RegisterBoardUser
	(uid, pass, name, nick, email, hp, zip, addr1, addr2, regip);
	user.close();
	
	// 리다이렉트
	response.sendRedirect("/Jboard1/user/login.jsp");
%>