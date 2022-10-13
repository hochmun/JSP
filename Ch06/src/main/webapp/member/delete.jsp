<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	
	// 보안
	if (uid == null) {
		response.sendRedirect("./list.jsp");
		return;
	}
	
	// 데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/java2db";
	String user = "root";
	String pass = "1234";
	
	try {
		// 1단계 - JDBC 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계 - 데이터베이스 연결
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계 - Statement 생성
		String sql = "delete from `member` where `uid`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		// 4단계 - SQL문 전송
		psmt.executeUpdate();
		// 5단계 - 결과받기
		// 6단계 - 연결해제
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>