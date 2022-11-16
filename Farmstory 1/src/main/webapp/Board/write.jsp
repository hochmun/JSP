<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	if(budto == null) {
		response.sendRedirect("/Farmstory_1/user/login.jsp?error=104");
		return;
	}

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
%>
<main id="board">
    <section class="write">
        <form action="./proc/writeProc.jsp" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="group" value="<%= group %>">
        	<input type="hidden" name="cate" value="<%= cate %>">
        	<input type="hidden" name="uid" value="<%= budto.getUid() %>">
            <table>
                <caption>글쓰기</caption>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" placeholder="제목을 입력하세요."></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부</th>
                    <td>
                        <input type="file" name="fname">
                    </td>
                </tr>
            </table>
            <div>
                <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pg %>" class="btn btnCancel">취소</a>
                <input type="submit" value="작성완료" class="btn btnComplete">
            </div>
        </form>
    </section>
</main>
<%@ include file="/_footer.jsp" %>