<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터수신
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	
	// 데이터베이스
	try {
	Connection conn = DBCP.getConnection();
	
	String sql = "delete from `user5` where `uid`=?";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>