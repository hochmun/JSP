<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");

	//직접 접속 막기
	if(cate == null) {
	response.sendRedirect("/Farmstory_1/");
	return;
	}
%>
<div id="sub">
    <div>
        <img src="../img/sub_top_tit4.png" alt="EVENT">
    </div>
    <section class="cate4">
        <aside>
            <img src="../img/sub_aside_cate4_tit.png" alt="이벤트">
            <ul class="lnb">
                <li class="on"><a href="./list.jsp?group=event&cate=event">이벤트</a></li>
            </ul>
        </aside>
        <article>
            <nav>
                <img src="../img/sub_nav_tit_cate4_tit1.png" alt="이벤트">
                <p>HOME > 이벤트 > <em>이벤트</em></p>
            </nav>