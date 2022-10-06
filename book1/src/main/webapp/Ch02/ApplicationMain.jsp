<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내장 객체 - application</title>
		<%--
		날짜 : 2022/10/06
		이름 : 심규영
		내용 : 다양한 서버 정보 얻어오기, p104
		 --%>
	</head>
	<body>
		<h2>web.xml에 설정한 내용 읽어오기</h2>
		초기화 매개변수 : <%= application.getInitParameter("INIT_PAEAM") %>
		
		<h2>서버의 물리적 경로 얻어오기</h2>
		application 내장 객체 : <%= application.getRealPath("/Ch02") %>
		
		<h2>선언부에서 application 내장 객체 사용하기</h2>
		<%!
		public String useImplicitObject() {
			return this.getServletContext().getRealPath("/Ch02");
		}
		public String useImplicitObject(ServletContext app) {
			return app.getRealPath("/Ch02");
		}
		%>
		<ul>
			<li>this 사용 : <%= useImplicitObject() %></li>
			<li>내장 객체를 인수로 전달 : <%= useImplicitObject(application) %></li>
		</ul>
	</body>
</html>