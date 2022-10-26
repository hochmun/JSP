<%@page import="utils.JSFunction"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/24
	이름 : 심규영
	내용 : 로그인하지 않았다면 로그인 폼으로 이동, p279
 -->
<%
if (session.getAttribute("UserId") == null) {
	JSFunction.alertLocation("로그인 후 이용해주십시오", "../Ch06/LoginForm.jsp",
			out);
	return;
}
%>