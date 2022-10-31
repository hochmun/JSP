<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	String uid = request.getParameter("uid");

	// 데이터베이스 처리
	BoardUserDAO checkId = new BoardUserDAO(); //BoardUserDAO.getInstance();
	int result = checkId.CheckUid(uid);
	checkId.close();

	// JSON 출력
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);
%>