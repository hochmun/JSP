<%@page import="com.google.gson.JsonObject"%>
<%@page import="ko.co.college.DAO.studentDAO"%>
<%@page import="ko.co.college.bean.studentBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String studentId = request.getParameter("studentId");
	String studentName = request.getParameter("studentName");
	String studentHp = request.getParameter("studentHp");
	String studentYear = request.getParameter("studentYear");
	String studentAddr = request.getParameter("studentAddr");
	
	studentBean sb = new studentBean();
	sb.setStdno(studentId);
	sb.setStdName(studentName);
	sb.setStdHp(studentHp);
	sb.setStdYear(Integer.parseInt(studentYear));
	sb.setStdAddress(studentAddr);
	
	studentDAO sdao = new studentDAO();
	int result = sdao.studentInsert(sb);
	sdao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("studentId", studentId);
	json.addProperty("studentName", studentName);
	json.addProperty("studentHp", studentHp);
	json.addProperty("studentYear", studentYear);
	json.addProperty("studentAddr", studentAddr);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>