<%@page import="com.google.gson.Gson"%>
<%@page import="ko.co.college.bean.lectureBean"%>
<%@page import="java.util.List"%>
<%@page import="ko.co.college.DAO.lectureDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	lectureDAO ldao = new lectureDAO();
	List<lectureBean> lbs = ldao.readLecNameList();
	ldao.close();
	
	Gson gson = new Gson();
	String jsondata = gson.toJson(lbs);
	
	out.print(jsondata);
%>