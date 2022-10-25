<%@page import="bean.MemberBean"%>
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
	MemberBean mb = null;
	
	try {
		// 1단계
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		String sql = "select * from `member` where `uid`= ?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		// 4단계
		ResultSet rs = psmt.executeQuery();
		// 5단계
		mb = new MemberBean();
		if(rs.next()) {
			mb.setUid(rs.getString(1));
			mb.setName(rs.getString(2));
			mb.setHp(rs.getString(3));
			mb.setPos(rs.getString(4));
			mb.setDep(rs.getInt(5));
			mb.setRdate(rs.getString(6));
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
		<title>member::modify</title>
	</head>
	<body>
		<h3>member 수정</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./list.jsp">member 목록</a>
		
		<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value="<%= mb.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= mb.getName() %>"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= mb.getHp() %>"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option <%= mb.getPos().equals("사원") ? "selected" : "" %>>사원</option>
							<option <%= mb.getPos().equals("대리") ? "selected" : "" %>>대리</option>
							<option <%= mb.getPos().equals("과장") ? "selected" : "" %>>과장</option>
							<option <%= mb.getPos().equals("차장") ? "selected" : "" %>>차장</option>
							<option <%= mb.getPos().equals("부장") ? "selected" : "" %>>부장</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<option value="101" <%= mb.getDep() == 101 ? "selected" : "" %>>영업1부</option>
							<option value="102" <%= mb.getDep() == 102 ? "selected" : "" %>>영업2부</option>
							<option value="103" <%= mb.getDep() == 103 ? "selected" : "" %>>영업3부</option>
							<option value="104" <%= mb.getDep() == 104 ? "selected" : "" %>>인사부</option>
							<option value="105" <%= mb.getDep() == 105 ? "selected" : "" %>>영업지원부</option>
						</select>
					</td>
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