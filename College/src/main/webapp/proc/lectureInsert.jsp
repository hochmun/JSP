<%@page import="com.google.gson.JsonObject"%>
<%@page import="ko.co.college.DAO.lectureDAO"%>
<%@page import="ko.co.college.bean.lectureBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String lecNo = request.getParameter("lecNo");
	String lecName = request.getParameter("lecName");
	String lecCredit = request.getParameter("lecCredit");
	String lecTime = request.getParameter("lecTime");
	String lecClass = request.getParameter("lecClass");
	
	lectureBean lb = new lectureBean();
	lb.setLecNo(Integer.parseInt(lecNo));
	lb.setLecName(lecName);
	lb.setLecCredit(Integer.parseInt(lecCredit));
	lb.setLecTime(Integer.parseInt(lecTime));
	lb.setLecClass(lecClass);
	
	lectureDAO ldao = new lectureDAO();
	int result = ldao.insertLecture(lb);
	ldao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("lecNo", lecNo);
	json.addProperty("lecName", lecName);
	json.addProperty("lecCredit", lecCredit);
	json.addProperty("lecTime", lecTime);
	json.addProperty("lecClass", lecClass);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>