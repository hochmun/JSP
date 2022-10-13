<%@page import="java.util.ArrayList"%>
<%@page import="bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터베이스 작업
	List<StudentBean> students = null;
	
	try{
		// 1단계, 2단계
		Connection conn = DB.getInstance().getConnection();
		// 3단계
		Statement stmt = conn.createStatement();
		// 4단계
		ResultSet rs = stmt.executeQuery("select * from `student`");
		// 5단계
		students = new ArrayList<>();
		
		while(rs.next()) {
			StudentBean sb = new StudentBean();
			
			sb.setStdNo(rs.getString(1));
			sb.setStdName(rs.getString(2));
			sb.setStdHp(rs.getString(3));
			sb.setStdYear(rs.getInt(4));
			sb.setStdAddress(rs.getString(5));
			
			students.add(sb);
		}
		// 6단계
		rs.close();
		stmt.close();
		conn.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>student::list</title>
	</head>
	<body>
		<h3>student 목록</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./register.jsp">student 등록</a>
		
		<table border="1">
			<tr>
				<th>학생번호</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>학년</th>
				<th>주소</th>
			</tr>
			<% for (StudentBean sb : students) { %>
			<tr>
				<td><%= sb.getStdNo() %></td>
				<td><%= sb.getStdName() %></td>
				<td><%= sb.getStdHp() %></td>
				<td><%= sb.getStdYear() %></td>
				<td><%= sb.getStdAddress() %></td>
				<td>
					<a href="./modify.jsp?stdNo=<%= sb.getStdNo() %>">수정</a>
					<a href="./delete.jsp?stdNo=<%= sb.getStdNo() %>">삭제</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>