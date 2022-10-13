<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
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
	
	// 데이터베이스 작업
	try {
		Connection conn = DB.getInstance().getConnection();
		
		String sql = "update `student` set `stdName`=?, `stdHp`=?, "
				+"`stdYear`=?, `stdAddress`=? where `stdNo`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, stdName);
		psmt.setString(2, stdHp);
		psmt.setString(3, stdYear);
		psmt.setString(4, stdAddress);
		psmt.setString(5, stdNo);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>