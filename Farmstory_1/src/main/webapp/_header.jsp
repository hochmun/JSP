<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardUserDTO budto = (BoardUserDTO)session.getAttribute("Farmstory_1User");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory_1/css/style.css">
    <link rel="stylesheet" href="/Farmstory_1/user/css/style.css">
    <link rel="stylesheet" href="/Farmstory_1/Board/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script>
        $(function(){
            $('.slider > ul').bxSlider({
                slideWidth: 980,
                pager: false,
                controls: false,
                auto: true
            });

            $('#tabs').tabs();
        });
    </script>
</head>
<body>
    <div id="wrapper">
        <header>
            <a href="/Farmstory_1/">
                <img src="/Farmstory_1/img/logo.png" alt="로고">
            </a>
            <p>
                <a href="/Farmstory_1/">HOME |</a>
                <% if (budto == null) { %>
                	<a href="/Farmstory_1/user/login.jsp">로그인 |</a>
                	<a href="/Farmstory_1/user/terms.jsp">회원가입 |</a>
                <% } else { %>
                	<a href="/Farmstory_1/user/proc/LogoutProc.jsp">로그아웃 |</a>
                <% } %>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory_1/img/head_txt_img.png" alt="3만원 이상 무료배송">
            <ul class="gnb">
                <li><a href="/Farmstory_1/introduction/hello.jsp">팜스토리 소개</a></li>
                <li><a href="/Farmstory_1/Board/list.jsp?group=market&cate=market"><img src="/Farmstory_1/img/head_menu_badge.png" alt="30%">장보기</a></li>
                <li><a href="/Farmstory_1/Board/list.jsp?group=croptalk&cate=story">농작물이야기</a></li>
                <li><a href="/Farmstory_1/Board/list.jsp?group=event&cate=event">이벤트</a></li>
                <li><a href="/Farmstory_1/Board/list.jsp?group=community&cate=notice">커뮤니티</a></li>
            </ul>
        </header>