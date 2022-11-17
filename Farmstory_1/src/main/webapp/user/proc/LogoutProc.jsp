<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 로그인 정보 삭제
	session.removeAttribute("Farmstory_1User");

	// 홈으로 이동
	response.sendRedirect("/Farmstory_1/");
%>