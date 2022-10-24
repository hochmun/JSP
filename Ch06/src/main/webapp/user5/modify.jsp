<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.Jboard1.bean.User5Bean"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	User5Bean ub = null;
	
	// 데이터베이스
	try {
		Connection conn = DBCP.getConnection();
		
		String sql = "select * from `user5` where `uid`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		
		ResultSet rs = psmt.executeQuery();
		ub = new User5Bean();
		if (rs.next()) {
			ub.setUid(rs.getString(1));
			ub.setName(rs.getString(2));
			ub.setBirth(rs.getString(3));
			ub.setAge(rs.getInt(4));
			ub.setAddress(rs.getString(5));
			ub.setHp(rs.getString(6));
		}
		
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
		<title>user5::modify</title>
	</head>
	<body>
		<h3>user5 수정</h3>
		
		<a href="../2_DBCPTest.jsp">처음으로</a>
		<a href="./list.jsp">user5 목록</a>
		
		<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value="<%=ub.getUid()%>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%=ub.getName()%>"></td>
				</tr>
				<tr>
					<td>생일</td>
					<td><input type="date" name="birth" value="<%=ub.getBirth()%>"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="<%=ub.getAge()%>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value="<%=ub.getAddress()%>"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="hp" value="<%=ub.getHp()%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<input type="submit" value="등록하기"></td>
				</tr>
			</table>
		</form>
	</body>
</html>