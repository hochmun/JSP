<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");

	// 직접 접속 막기
	if(group == null) {
		response.sendRedirect("/Farmstory_1/");
	} else if (group.equals("community") || 
			   group.equals("croptalk") || 
			   group.equals("event") || 
			   group.equals("market")) {
		pageContext.include("/Board/_"+group+".jsp");
	} else {
		response.sendRedirect("/Farmstory_1/");
	}
%>
<main id="board">
    <section class="list">
        <table border="0">
            <caption>글목록</caption>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>조회</th>
            </tr>
            <tr>
                <td>1</td>
                <td>
                    <a href="./view.jsp?group=<%=group%>&cate=<%=cate%>">텍스트 제목입니다.</a>&nbsp;[3]
                </td>
                <td>길동이</td>
                <td>20-05-12</td>
                <td>12</td>
            </tr>
        </table>
        <div class="page">
            <a href="#" class="prev">이전</a>
            <a href="#" class="num current">1</a>
            <a href="#" class="num">2</a>
            <a href="#" class="num">3</a>
            <a href="#" class="next">다음</a>
        </div>
        <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnWrite">글쓰기</a>
    </section>
</main>
</article>
</section>
</div>    
<%@ include file="/_footer.jsp" %>