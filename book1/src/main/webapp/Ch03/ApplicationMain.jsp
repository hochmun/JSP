<%@page import="java.util.HashMap"%>
<%@page import="common.Person"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>application 영역</title>
		<!-- 
			날짜 : 2022/10/08
			이름 : 심규영
			내용 : 최초 페이지(application 영역 동작 확인용), p132
		 -->
	</head>
	<body>
		<h2>application 영역의 공유</h2>
		<%
		Map<String, Person> maps = new HashMap<>();
		maps.put("actor1", new Person("이수일", 30));
		maps.put("actor2", new Person("심순애", 28));
		application.setAttribute("maps", maps);
		%>
		application 영역에 속성이 저장되었습니다.
	</body>
</html>