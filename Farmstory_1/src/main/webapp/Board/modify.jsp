<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	Map<String, Object> dtos = BoardArticleDAO.getInstance().selectArticle(no);
	BoardArticleDTO badto = (BoardArticleDTO)dtos.get("badto");
%>
<main id="board">
    <section class="modify">
        <form action="/Farmstory_1/Board/proc/modifyProc.jsp" method="post">
        	<input type="hidden" name="no" value="<%= no %>">
        	<input type="hidden" name="pg" value="<%= pg %>">
        	<input type="hidden" name="cate" value="<%= cate %>">
        	<input type="hidden" name="group" value="<%= group %>">
            <table border="0">
                <caption>글쓰기</caption>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" placeholder="제목을 입력하세요." value="<%=badto.getTitle()%>"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content"><%= badto.getContent() %></textarea>
                    </td>
                </tr>
                <!--  
                <tr>
                    <th>첨부</th>
                    <td>
                        <input type="file" name="file">
                    </td>
                </tr>
                -->
            </table>
            <div>
                <a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pg %>&no=<%= no %>" class="btn btnCancel">취소</a>
                <input type="submit" value="수정완료" class="btn btnComplete">
            </div>
        </form>
    </section>
</main>
<%@ include file="/_footer.jsp" %>