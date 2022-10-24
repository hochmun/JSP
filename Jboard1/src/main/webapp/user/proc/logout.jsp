<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// session에 유저 정보 제거
	session.invalidate();
	// 로그인 페이지 이동
	response.sendRedirect("/Jboard1/user/login.jsp");
%>