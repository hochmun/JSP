<%@page import="config.DBCP"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
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
	
	// 데이터베이스 작업
	try {
		//1단계 - JNDI 서비스 객체생성
		//Context initCtx = new InitialContext();
		//Context ctx = (Context) initCtx.lookup("java:comp/env"); // JNDI 기본 환경 이름
		
		//2단계 - 커넥션 풀에서 커넥션 가져오기
		//DataSource ds = (DataSource) ctx.lookup("dbcp_java2db"); // 커넥션 풀 얻기
		//Connection conn = ds.getConnection(); // 커넥션 풀에서 커넥션 얻기
		Connection conn = DBCP.getConnection();
		
		//3단계
		String sql = "insert into `user5` values (?,?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		psmt.setString(2, name);
		psmt.setString(3, birth);
		psmt.setString(4, age);
		psmt.setString(5, address);
		psmt.setString(6, hp);
		
		//4단계
		psmt.executeUpdate();
		//5단계
		//6단계
		psmt.close();
		conn.close();
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>