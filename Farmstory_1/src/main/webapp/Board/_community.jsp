<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");

	// 직접 접속 막기
	if(cate == null) {
	response.sendRedirect("/Farmstory_1/");
	return;
}
%>
<div id="sub">
    <div>
        <img src="/Farmstory_1/img/sub_top_tit5.png" alt="COMMUNITY">
    </div>
    <section class="cate5">
        <aside>
            <img src="/Farmstory_1/img/sub_aside_cate5_tit.png" alt="커뮤니티">
            <ul class="lnb">
                <li class="<%= cate.equals("notice") ? "on" : "off" %>"><a href="./list.jsp?group=community&cate=notice">공지사항</a></li>
                <li class="<%= cate.equals("menu") ? "on" : "off" %>"><a href="./list.jsp?group=community&cate=menu">오늘의식단</a></li>
                <li class="<%= cate.equals("chef") ? "on" : "off" %>"><a href="./list.jsp?group=community&cate=chef">나도요리사</a></li>
                <li class="<%= cate.equals("qna") ? "on" : "off" %>"><a href="./list.jsp?group=community&cate=qna">1:1고객문의</a></li>
                <li class="<%= cate.equals("faq") ? "on" : "off" %>"><a href="./list.jsp?group=community&cate=faq">자주묻는질문</a></li>
            </ul>
        </aside>
        <article>
            <nav>
                <img src="../img/sub_nav_tit_cate5_<%= cate %>.png" alt="<%= cate %>">
                <p>HOME > 커뮤니티 > 
		            <% if (cate.equals("notice")) { %>
	                	<em>공지사항</em>
	                <% } else if (cate.equals("menu")) { %>
	                	<em>오늘의식단</em>
	                <% } else if (cate.equals("chef")) { %>
	                	<em>나도요리사</em>
	                <% } else if (cate.equals("qna")) { %>
	                	<em>1:1고객문의</em>
	                <% } else if (cate.equals("faq")) { %>
	                	<em>자주묻는질문</em>
	                <% } %>
                </p>
            </nav>