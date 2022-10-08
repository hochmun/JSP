<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>session 영역</title>
		<!-- 
			날짜 : 2022/10/08
			이름 : 심규영
			내용 : 링크를 클릭해 이동된 페이지, p129
		 -->
	</head>
	<body>
		<h2>페이지 이동 후 session 영역의 속성 읽기</h2>
		<%
		ArrayList<String> lists = (ArrayList<String>)session.getAttribute("lists");
		for (String str : lists) out.print(str + "<br>");
		%>
	</body>
</html>