<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="ko.co.college.bean.registerBean"%>
<%@page import="java.util.List"%>
<%@page import="ko.co.college.DAO.registerDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String searchRegister = request.getParameter("searchRegister");
	
	registerDAO rdao = new registerDAO();
	List<registerBean> rbs = rdao.searchRegisterList(searchRegister);
	rdao.close();
	
	Gson gson = new Gson();
	String jsondata = gson.toJson(rbs);
	
	out.print(jsondata);
%>