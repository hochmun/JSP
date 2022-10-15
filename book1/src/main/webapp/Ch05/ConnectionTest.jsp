<%@page import="common.DBConnPool"%>
<%@page import="common.JDBConnect"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JDBC</title>
		<!-- 
			날짜 : 2022/10/15
			이름 : 심규영
			내용 : JDBC 연결 테스트
				두 번째 생성자 테스트 코드 추가, p187
				세 번째 생성자 테스트 코드 추가, p189
				커넥션 풀 테스트 코드 추가, p199
		 -->
	</head>
	<body>
		<h2>JDBC 테스트 1</h2>
		<%
		JDBConnect jdbc1 = new JDBConnect();
		jdbc1.close();
		%>
		
		<h2>JDBC 테스트 2</h2>
		<%
			String driver = application.getInitParameter("MysqlDriver");
			String url = application.getInitParameter("MysqlURL");
			String id = application.getInitParameter("MysqlId");
			String pwd = application.getInitParameter("MysqlPwd");
			
			JDBConnect jdbc2 = new JDBConnect(driver, url, id, pwd);
			jdbc2.close();
		%>
		
		<h2>JDBC 테스트 3</h2>
		<%
			JDBConnect jdbc3 = new JDBConnect(application);
			jdbc3.close();
		%>
		
		<h2>커넥션 풀 테스트</h2>
		<%
			DBConnPool pool = new DBConnPool();
			pool.close();
		%>
	</body>
</html>