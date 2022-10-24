<%@page import="kr.co.Jboard1.config.DB"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("UTF-8");

	String stdNo = request.getParameter("stdNo");
	String stdName = request.getParameter("stdName");
	String stdHp = request.getParameter("stdHp");
	String stdYear = request.getParameter("stdYear");
	String stdAddress = request.getParameter("stdAddress");
	
	// 보안
	if (stdNo == null) {
		response.sendRedirect("./list.jsp");
		return;
	}
	
	// 데이터베이스 처리	
	try {
		// 1단계 - JDBC 드라이버 로드
		// 2단계 - 데이터베이스 연결
		Connection conn = DB.getInstance().getConnection();
		// 3단계 - Statement 생성
		String sql = "INSERT INTO `student` values (?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, stdNo);
		psmt.setString(2, stdName);
		psmt.setString(3, stdHp);
		psmt.setString(4, stdYear);
		psmt.setString(5, stdAddress);
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