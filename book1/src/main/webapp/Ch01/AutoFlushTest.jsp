<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>page 지시어 - buffer, autoFlush 속성</title>
		<%--
			날짜 : 2022/10/05
			이름 : 심규영
			내용 : 버퍼와 플러시
		 --%>
	</head>
	<body>
		<%
			// JSP 버퍼 오버 플로우
			for(int i = 1; i <= 100; i++) {
				out.println("abcde12345");
			}
		%>
	</body>
</html>