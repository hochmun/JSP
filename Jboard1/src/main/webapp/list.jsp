<%@page import="java.util.List"%>
<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	String pg = request.getParameter("pg");

	// 게시판 번호 관련 선언
	int limitStart = 0; // 현재 페이지의 시작 게시물 번호
	int currentPage = 1; // 현제 페이지
	int total = 0; // 총 게시물 갯수
	int lastPageNum = 0; // 마지막 페이지 번호
	int pageGroupCurrent = 1; // 현재 그룹 페이지 값
	int pageGroupStart = 1; // 현재 그룹 시작 페이지 번호
	int pageGroupEnd = 0; // 현재 그룹 마지막 페이지 번호
	int pageStartNum = 0; // 현재 페이지 게시물 시작 번호

	// 게시물 DAO 객체 가져오기
	BoardArticleDAO badao = new BoardArticleDAO();//BoardArticleDAO.getInstance();
	// 전체 게시물 갯수 구하기
	total = badao.SelectCountTotalBoardArticleDao();
	
	// 페이지 마지막 번호 계산
	if(total % 10 != 0) {
		lastPageNum = (total / 10) + 1;
	} else {
		lastPageNum = (total / 10);
	}
	
	// 전체 페이지 게시물 limit 시작값 계산
	if (pg != null) {
		currentPage = Integer.parseInt(pg);
	}
	limitStart = (currentPage - 1) * 10;
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int)Math.ceil(currentPage/10.0);
	pageGroupStart	 = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd	 = pageGroupCurrent * 10;
	
	if (pageGroupEnd > lastPageNum) {
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작 번호 계산
	pageStartNum = total - limitStart;
	
	// 전체 페이지 게시물 가져오기
	List<BoardArticleBean> babs = badao.ViewAllListBoardArticleDAO(limitStart);
	// 클래스 닫기
	badao.close();
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
            <% for (BoardArticleBean bab : babs) { %>
            <tr>
                <td><%= pageStartNum-- %></td>
                <td>
                    <a href="/Jboard1/view.jsp?no=<%=bab.getNo()%>&pg=<%=currentPage%>"><%= bab.getTitle() %>
                    </a>&nbsp;[<%= bab.getComment() %>]
                </td>
                <td><%= bab.getNick() %></td>
                <td><%= bab.getRdate().substring(2, 10)%></td>
                <td><%= bab.getHit() %></td>
            </tr>
            <% } %>
        </table>
        <div class="page">
        	<% if (pageGroupStart > 1) { %>
            <a href="/Jboard1/list.jsp?pg=<%=pageGroupStart-1 %>" class="prev">이전</a>
            <% }
            	for (int i = pageGroupStart; i <= pageGroupEnd; i++) { %>
            <a href="/Jboard1/list.jsp?pg=<%= i %>" class="num <%=(currentPage == i)?"current":"off"%>"><%= i %></a>
            <% } 
            	if (pageGroupEnd < lastPageNum ) {%>
            <a href="/Jboard1/list.jsp?pg=<%=pageGroupEnd+1 %>" class="next">다음</a>
            <% } %>
        </div>
        <a href="/Jboard1/write.jsp" class="btn btnWrite">글쓰기</a>
    </section>
</main>
<%@ include file="./_footer.jsp" %>