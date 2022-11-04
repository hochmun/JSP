<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - forTokens</title>
		<!-- 
			날짜 : 2022/11/04
			이름 : 심규영
			내용 : <c:forTokens/> 사용하기, p396
		 -->
	</head>
	<body>
		<%
		String rgba = "Red,Green,Blue,Black";
		%>
		<h4>JSTL의 forTokens 태그 사용</h4>
		<c:forTokens items="<%= rgba %>" delims="," var="color">
			<span style="color:${ color };">${ color }</span> <br/>
		</c:forTokens>
	</body>
</html>