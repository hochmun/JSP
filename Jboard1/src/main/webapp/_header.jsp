<%@page import="kr.co.Jboard1.bean.BoardUserBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardUserBean bub = (BoardUserBean) session.getAttribute("sessUser");
	
	if(bub == null) {
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    <link rel="stylesheet" href="/Jboard1/css/style.css">
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>Board System v1.0</h3>
            <p>
                <span><%= bub.getNick() %></span>님 반갑습니다.
                <a href="/Jboard1/user/proc/logout.jsp">[로그아웃]</a>
            </p>
        </header>