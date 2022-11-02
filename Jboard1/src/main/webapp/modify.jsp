<%@page import="kr.co.Jboard1.bean.BoardFileBean"%>
<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="java.util.Map"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	BoardArticleDAO badao = new BoardArticleDAO();
	Map<Object, Object> dtos = badao.ViewBoardArticleDAO(no);
	BoardArticleBean bab = (BoardArticleBean)dtos.get("bab");
	//BoardFileBean bfb = (BoardFileBean)dtos.get("bfb");
	badao.close();
%>
<main id="board">
    <section class="modify">
        <form action="/Jboard1/proc/articleModifyProc.jsp" method="POST">
        	<input type="hidden" name="no" value="<%= no %>">
        	<input type="hidden" name="pg" value="<%= pg %>">
            <table>
                <caption>글쓰기</caption>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" placeholder="제목을 입력하세요." value="<%=bab.getTitle()%>"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content"><%= bab.getContent() %></textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Jboard1/view.jsp?no=<%=no %>&pg=<%=pg %>" class="btn btnCancel">취소</a>
                <input type="submit" value="수정완료" class="btn btnComplete">
            </div>
        </form>
    </section>
</main>
<%@ include file="./_footer.jsp" %>