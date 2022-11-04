<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - resirect</title>
		<!-- 
			날짜 : 2022/11/04
			이름 : 심규영
			내용 : <c:redirect/> 태그 사용하기, p400
		 -->
	</head>
	<body>
		<c:set var="requestVar" value="MustHave" scope="request"/>
		<c:redirect url="/Ch11JSTL/inc/OtherPage.jsp">
			<c:param name="user_param1" value="출판사"/>
			<c:param name="user_param2" value="골든래빗"/>
		</c:redirect>
	</body>
</html>