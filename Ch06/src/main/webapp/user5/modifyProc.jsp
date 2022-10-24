<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String age = request.getParameter("age");
	String address = request.getParameter("address");
	String hp = request.getParameter("hp");
	
	// 데이터베이스
	try {
		Connection conn = DBCP.getConnection();
		
		String sql = "update `user5` set `name`=?, `birth`=?"
				+", `age`=?, `address`=?, `hp`=? where `uid`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, birth);
		psmt.setString(3, age);
		psmt.setString(4, address);
		psmt.setString(5, hp);
		psmt.setString(6, uid);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>