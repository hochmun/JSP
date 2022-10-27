<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	String nick = request.getParameter("nick");

	// 데이터베이스 처리
	BoardUserDAO checkNick = BoardUserDAO.getInstance();
	int result = checkNick.CheckNick(nick);
	checkNick.close();
	
	// JSON 출력
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);
%>