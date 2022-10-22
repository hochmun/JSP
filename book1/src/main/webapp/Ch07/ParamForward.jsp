<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/21
	이름 : 심규영
	내용 : 포워드되는 페이지, p250
 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>액션 태그 - param</title>
	</head>
	<body>
		<jsp:useBean id="person" class="common.Person" scope="request"/>
		<h2>포워드된 페이지에서 매개변수 확인</h2>
		<ul>
			<li><jsp:getProperty property="name" name="person"/></li>
			<li>나이 : <jsp:getProperty name="person" property="age"/> </li>
			<li>본명 : <%= request.getParameter("param1") %> </li>
			<li>출생 : <%= request.getParameter("param2") %> </li>
			<li>특징 : <%= request.getParameter("param3") %> </li>
		</ul>
		<!-- 
			날짜 : 2022/10/22
			이름 : 심규영
			내용 : 인클루드하는 페이지로 매개변수 전달, p252
		 -->
		<jsp:include page="inc/ParamInclude.jsp">
			<jsp:param name="loc1" value="강원도 영월"/>
			<jsp:param name="loc2" value="김삿갓면"/>
		</jsp:include>
	</body>
</html>