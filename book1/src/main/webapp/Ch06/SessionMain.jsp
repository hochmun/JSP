<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	long creationTime = session.getCreationTime();
	String creationTimeStr = dateFormat.format(new Date(creationTime));
	
	long lastTime = session.getLastAccessedTime();
	String lastTimeStr = dateFormat.format(new Date(lastTime));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session 설정 확인</title>
		<!-- 
			날짜 : 2022/10/18
			이름 : 심규영
			내용 : 세션 설정값 확인, p212
		 -->
	</head>
	<body>
		<h2>Session 설정 확인</h2>
		<ul>
			<li>세션 유지 시간 	: <%= session.getMaxInactiveInterval() %></li>
			<li>세션 아이디 	: <%= session.getId() %></li>
			<li>최초 요청 시각 	: <%= creationTimeStr %></li>
			<li>마지막 요철 시각	: <%= lastTimeStr %></li>
		</ul>
	</body>
</html>