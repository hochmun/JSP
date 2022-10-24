<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.Jboard1.bean.MemberBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/java2db";
	String user = "root";
	String pass = "1234";
	List<MemberBean> members = null;
	
	try {
		// 1단계 - JDBC 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계 - 데이터베이스 연결
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계 - statement 생성
		Statement stmt = conn.createStatement();
		// 4단계 - SQL문 전송
		ResultSet rs = stmt.executeQuery("select * from `member`");
		// 5단계 - 결과 받기
		members = new ArrayList<>();
		
		while(rs.next()) {
			//String uid = rs.getString(1);
			//String name = rs.getString(2);
			//String hp = rs.getString(3);
			//int age = rs.getInt(4);
			MemberBean mb = new MemberBean();
			
			mb.setUid(rs.getString(1));
			mb.setName(rs.getString(2));
			mb.setHp(rs.getString(3));
			mb.setPos(rs.getString(4));
			mb.setDep(rs.getInt(5));
			mb.setRdate(rs.getString(6));
			
			members.add(mb);
		}
		// 6단계 - 연결 해제
		rs.close();
		stmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::list</title>
	</head>
	<body>
		<h3>member 목록</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./register.jsp">member 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>직급</th>
				<th>부서</th>
				<th>입사일</th>
				<th>관리</th>
			</tr>
			<% for (MemberBean mb : members) { %>
			<tr>
				<td><%= mb.getUid() %></td>
				<td><%= mb.getName() %></td>
				<td><%= mb.getHp() %></td>
				<td><%= mb.getPos() %></td>
				<td><%= mb.getDep() %></td>
				<td><%= mb.getRdate() %></td>
				<td>
					<% if(!(mb.getPos().equals("사장"))) { %>
					<a href="./modify.jsp?uid=<%= mb.getUid() %>">수정</a>
					<a href="./delete.jsp?uid=<%= mb.getUid() %>">삭제</a>
					<% } %>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>