<%@page import="bean.UserBean"%>
<%@page import="java.sql.ResultSet"%>
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
	
	// 데이터베이스 작업
	String host = "jdbc:mysql://127.0.0.1:3306/java2db";
	String user = "root";
	String pass = "1234";
	UserBean ub = null;
	
	try {
		// 1단계
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		String sql = "select * from `user3` where `uid`= ?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		// 4단계
		ResultSet rs = psmt.executeQuery();
		// 5단계
		ub = new UserBean();
		if(rs.next()) {
			ub.setUid(rs.getString(1));
			ub.setName(rs.getString(2));
			ub.setHp(rs.getString(3));
			ub.setAge(rs.getInt(4));
		}
		// 6단계
		rs.close();
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::modify</title>
	</head>
	<body>
		<h3>user 수정</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./list.jsp">user 목록</a>
		
		<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value="<%= ub.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= ub.getName() %>"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= ub.getHp() %>"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="<%= ub.getAge() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정하기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>