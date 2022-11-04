<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - import</title>
		<!-- 
			날짜 : 2022/11/04
			이름 : 심규영
			내용 : <c:import/> 태그 사용하기, p398
		 -->
	</head>
	<body>
		<c:set var="requestVar" value="MustHave" scope="request"/>
		<c:import url="/Ch11JSTL/inc/OtherPage.jsp" var="contents">
			<c:param name="user_param1" value="JSP"/>
			<c:param name="user_param2" value="기본서"/>
		</c:import>
		
		<h4>다른 문서 삽입하기</h4>
		${ contents }
		
		<h4>외부 자원 삽입하기</h4>
		<iframe src="../inc/GoldPage.jsp" style="width:100%;height:600px;"></iframe>
	</body>
</html>