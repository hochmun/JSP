<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/20
	이름 : 심규영
	내용 : 로그아웃 처리 JSP
 -->
<%
	// 방법 1: 회원인증정보 속성 삭제
	session.removeAttribute("UserId");
	session.removeAttribute("UserName");
	
	// 방법 2: 모든 속성 한꺼번에 삭제
	session.invalidate();
	
	// 속성 삭제 후 페이지 이동
	response.sendRedirect("LoginForm.jsp");
%>