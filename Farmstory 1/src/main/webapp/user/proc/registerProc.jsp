<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid	 = request.getParameter("uid");
	String pass	 = request.getParameter("pass2");
	String name	 = request.getParameter("name");
	String nick  = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp	 = request.getParameter("hp");
	String zip	 = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();
	
	BoardUserDTO budto = new BoardUserDTO();
	budto.setUid(uid);
	budto.setPass(pass);
	budto.setName(name);
	budto.setNick(nick);
	budto.setEmail(email);
	budto.setHp(hp);
	budto.setZip(zip);
	budto.setAddr1(addr1);
	budto.setAddr2(addr2);
	budto.setRegip(regip);
	
	
%>