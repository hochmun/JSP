<%@page import="common.Person"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 속성 저장
request.setAttribute("requestString", "requset 영역의 문자열");
request.setAttribute("requestPerson", new Person("안중근", 31));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>request 영역</title>
		<!-- 
			날짜 : 2022/10/08
			이름 : 심규영
			내용 : 최초 페이지(request 영역 동작 확인용), p124
		 -->
	</head>
	<body>
		<h2>request 영역의 속성값 삭제하기</h2>
		<%
			request.removeAttribute("requestString");
			request.removeAttribute("requestInteger"); // 에러 없음
		%>
		<h2>request 영역의 속성값 읽기</h2>
		<%
		Person rPerson = (Person)(request.getAttribute("requestPerson"));
		%>
		<ul>
			<li>String 객체 : <%= request.getAttribute("requestString") %></li>
			<li>Person 객체 : <%= rPerson.getName() %>, <%= rPerson.getAge() %></li>
		</ul>
		<h2>포워드된 페이지에서 request 영역 속성값 읽기</h2>
		<%
		request.getRequestDispatcher(
				"RequestForward.jsp?paramHan=한글&paramEng=English")
				.forward(request, response);
		%>
	</body>
</html>