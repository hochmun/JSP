<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("utf-8");
	String bookId = request.getParameter("bookId");
	String bookName = request.getParameter("bookName");
	String publisher = request.getParameter("publisher");
	String price = request.getParameter("price");
	
	// 데이터베이스 작업
	try {
		Connection conn = DBCP.getConnection();
		
		String sql = "update `book` set `bookName`=?, `publisher`=?"
				+ ", `price`=? where `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookName);
		psmt.setString(2, publisher);
		psmt.setString(3, price);
		psmt.setString(4, bookId);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>