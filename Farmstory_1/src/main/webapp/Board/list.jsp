<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");

	// 직접 접속 막기
	if(group == null) {
		response.sendRedirect("/Farmstory_1/");
		return;
	} else if (group.equals("community") || 
			   group.equals("croptalk") || 
			   group.equals("event") || 
			   group.equals("market")) {
		pageContext.include("/Board/_"+group+".jsp");
	} else {
		response.sendRedirect("/Farmstory_1/");
		return;
	}
	
	// 게시판 기능 시작
	String pg = request.getParameter("pg");
	String delete = request.getParameter("delete");
	
	// 게시판 번호 관련 선언
	int limitStart = 0; // 현재 페이지의 시작 게시물 번호
	int currentPage = 1; // 현재 페이지
	int total = 0; // 총 게시물 객수
	int lastPageNum = 0; // 마지막 페이지 번호
	int pageGroupCurrent = 1; // 현재 그룹 페이지 번호
	int pageGroupStart = 1; // 현재 그룹 시작 페이지 번호
	int pageGroupEnd = 0; // 현재 그룹 마지막 페이지 번호
	int pageStartNum = 0; // 현재 페이지 게시물 시작 번호
	
	// 전체 게시물 갯수 구하기
	total = BoardArticleDAO.getInstance().selectCountArticle(cate);
	
	// 페이지 마지막 번호 계산
	if(total % 10 != 0) lastPageNum = (total / 10) + 1; 
	else lastPageNum = (total / 10);
	
	// 현재 페이지와 현재 페이지의 시작 게시물 번호 계산
	if(pg != null) currentPage = Integer.parseInt(pg);
	limitStart = ( currentPage - 1) * 10;
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int)Math.ceil(currentPage/10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if (pageGroupEnd > lastPageNum) pageGroupEnd = lastPageNum;
	
	// 현재 페이지 첫번째 게시물 번호 계산
	pageStartNum = total - limitStart;
	
	// 전체 페이지 게시물 가져오기 
	List<BoardArticleDTO> badtos = BoardArticleDAO.getInstance().selectArticles(cate, limitStart);
%>
<main id="board">
    <section class="list">
        <table>
            <caption>글목록</caption>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>조회</th>
            </tr>
            <% for (BoardArticleDTO badto : badtos) { %>
            <tr>
                <td><%= pageStartNum-- %></td>
                <td>
                    <a href="./view.jsp?group=<%=group%>&cate=<%=cate%>&no=<%=badto.getNo()%>&pg=<%=currentPage%>"><%= badto.getTitle() %></a>&nbsp;[<%= badto.getComment() %>]
                </td>
                <td><%= badto.getNick() %></td>
                <td><%= badto.getRdate().substring(2, 10) %></td>
                <td><%= badto.getHit() %></td>
            </tr>
            <% } %>
        </table>
        <div class="page">
        	<% if(pageGroupStart > 1) { %>
            	<a href="/Farmstory_1/Board/list.jsp?group=<%=group%>&cate=<%=cate%>&pg=<%=pageGroupStart-1%>" class="prev">이전</a>
            <% } %>
            <% for(int i = pageGroupStart; i <= pageGroupEnd; i++) { %>
            	<a href="/Farmstory_1/Board/list.jsp?group=<%=group%>&cate=<%=cate%>&pg=<%=i%>" class="num <%=(currentPage == i)?"current":"off"%>"><%=i%></a>
            <% } %>
            <% if(pageGroupEnd < lastPageNum) { %>
           		<a href="/Farmstory_1/Board/list.jsp?group=<%=group%>&cate=<%=cate%>&pg=<%=pageGroupEnd+1%>" class="next">다음</a>
            <% } %>
        </div>
        <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= currentPage %>" class="btn btnWrite">글쓰기</a>
    </section>
</main>
</article>
</section>
</div>    
<%@ include file="/_footer.jsp" %>