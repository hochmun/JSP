<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/21
	이름 : 심규영
	내용 : 포함될 외부 JSP 파일1, p235
 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>OuterPage</title>
	</head>
	<body>
		<h2>외부 파일 1</h2>
		<%
			String newVar1 = "고구려 세운 동명왕";
		%>
		<ul>
			<li>page 영역 속성 : <%= pageContext.getAttribute("pAttr") %></li>
			<li>request 영역 속성 : <%= request.getAttribute("rAttr") %></li>
		</ul>
	</body>
</html>