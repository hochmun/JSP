<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - out</title>
		<!-- 
			날짜 : 2022/11/04
			이름 : 심규영
			내용 : <c:out/> 태그 사용하기, p403
		 -->
	</head>
	<body>
		<c:set var="iTag">
			i 태그는 <i>기울임</i>을 표현합니다.
		</c:set>
		
		<h4>기본 사용</h4>
		<c:out value="${ iTag }"/>
		
		<h4>escapeXml 속성</h4>
		<c:out value="${ iTag }" escapeXml="false"/>
		
		<h4>default 속성</h4>
		<c:out value="${ param.name }" default="이름 없음"/>
		<c:out value="" default="빈 문자열도 값입니다."/>
	</body>
</html>