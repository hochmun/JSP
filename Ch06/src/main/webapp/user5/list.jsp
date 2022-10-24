<%@page import="user5.User5DAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.Jboard1.bean.User5Bean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 리스트 준비
	List<User5Bean> users5 = new User5DAO().GetUsers5DAO();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user5::list</title>
	</head>
	<body>
		<h3>user5 목록</h3>
		
		<a href="../2_DBCPTest.jsp">처음으로</a>
		<a href="./register.jsp">user5 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>나이</th>
				<th>주소</th>
				<th>전화</th>
				<th>관리</th>
			</tr>
			<% for(User5Bean ub : users5) { %>
			<tr>
				<td><%= ub.getUid() %></td>
				<td><%= ub.getName() %></td>
				<td><%= ub.getBirth() %></td>
				<td><%= ub.getAge() %></td>
				<td><%= ub.getAddress() %></td>
				<td><%= ub.getHp() %></td>
				<td>
					<a href="./modify.jsp?uid=<%=ub.getUid()%>">수정</a>
					<a href="./delete.jsp?uid=<%=ub.getUid()%>">삭제</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>