<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
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
            <tr>
                <td>1</td>
                <td>
                    <a href="/Jboard1/view.jsp">텍스트 제목입니다.</a>&nbsp;[3]
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
        <a href="/Jboard1/write.jsp" class="btn btnWrite">글쓰기</a>
    </section>
</main>
<%@ include file="./_footer.jsp" %>