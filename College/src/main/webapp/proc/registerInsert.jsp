<%@page import="com.google.gson.JsonObject"%>
<%@page import="ko.co.college.DAO.registerDAO"%>
<%@page import="ko.co.college.bean.registerBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String regStdNo = request.getParameter("regStdNo");
	String stdName	= request.getParameter("stdName");
	String lecNo	= request.getParameter("lecNo");
	String lecName 	= request.getParameter("lecName");
	
	registerBean rb = new registerBean();
	rb.setRegStdNo(regStdNo);
	rb.setStdName(stdName);
	rb.setRegLecNo(Integer.parseInt(lecNo));
	
	registerDAO rdao = new registerDAO();
	int result = rdao.registerInsert(rb);
	rdao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("regStdNo", regStdNo);
	json.addProperty("stdName", stdName);
	json.addProperty("lecNo", lecNo);
	json.addProperty("lecName", lecName);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>