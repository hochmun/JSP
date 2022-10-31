<%@page import="kr.co.Jboard1.bean.BoardCommentBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.co.Jboard1.bean.BoardFileBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardFileDAO"%>
<%@page import="kr.co.Jboard1.bean.BoardArticleBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	String no = request.getParameter("no"); // 조회하는 게시글 번호 받기
	String pg = request.getParameter("pg"); // 현제 게시물이 존재했던 페이지 번호
	
	// 데이터 베이스 연결
	BoardArticleDAO badao = new BoardArticleDAO();//BoardArticleDAO.getInstance(); 
	BoardCommentDAO bcdao = new BoardCommentDAO();//.getInstance(); 
	
	Map<Object, Object> beans = badao.ViewBoardArticleDAO(no); // 게시물 불러오기
	List<BoardCommentBean> bcbs = bcdao.CommentList(no); // 댓글 불러오기
	
	BoardArticleBean bab = (BoardArticleBean) beans.get("bab");
	BoardFileBean bfb = (BoardFileBean) beans.get("bfb");
	
	// 로그인한 아이디와 게시글의 글쓴이의 아이디가 같지 않을때
	if (!bub.getUid().equals(bab.getUid())) {
		badao.UpdateHitCount(no); // 게시물 조회수 증가
	}
	
	// 클래스 종료
	badao.close(); 
	bcdao.close();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('.commentForm > form').submit(function(){
			let content = $(this).children('textarea[name=content]').val();
			let no		= $(this).children('input[name=no]').val();
			
			if(content == '') {
				alert('댓글을 작성하세요.');
				return false;
			}
			
			let jsonData = {
					"no": no,
					"content":content
			};
			
			$.ajax({
				url:'/Jboard1/proc/commentWriteProc.jsp',
				method:'POST',
				data: jsonData,
				dataType:'json',
				success: function(data){
					if(data.result > 0) {
						let article = "<article>"
									+ "<span class='nick'>"+data.nick+"</span>"
									+ "<span class='date'>"+data.date+"</span>"
									+ "<p class='content'>"+data.content+"</p>"
									+ "<div>"
									+ "<a href='#' class='remove'>삭제</a>"
									+ "<a href='#' class='modify'>수정</a>"
									+ "</div>"
									+ "</article>";
						
						$('.comments > .empty').hide();
						$('.comments').append(article);
					}
				}
			});
			return false;
		});
	});
</script>

<main id="board">
    <section class="view">
        <table>
            <caption>글보기</caption>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="<%= bab.getTitle() %>" readonly></td>
            </tr>
            <% if(bab.getFile() > 0) { %>
            <tr>
                <th>첨부파일</th>
                <td><a href="/Jboard1/proc/downLoad.jsp?no=<%=bab.getNo()%>"><%= bfb.getOriName() %></a>&nbsp;<span><%= bfb.getDownload() %></span>회 다운로드</td>
            </tr>
            <% } %>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly><%= bab.getContent() %></textarea>
                </td>
            </tr>
        </table>
        <div>
        	<% if (bub.getUid().equals(bab.getUid())) { // 게시물의 글쓴이가 아닐때 삭제와 수정버튼 제거 %>
            <a href="#" class="btn btnRemove">삭제</a>
            <a href="/Jboard1/modify.jsp" class="btn btnModify">수정</a>
            <% } %>
            <a href="/Jboard1/list.jsp?pg=<%= pg %>" class="btn btnList">목록</a>
        </div>
        <!-- 댓글목록 -->
        <section class="comments">
            <h3>댓글목록</h3>
            <article>
            <% if (bcbs.size() != 0) {
            	for (BoardCommentBean bcb : bcbs) { %>
                <span class="nick"><%= bcb.getNick() %></span>
                <span class="date"><%= bcb.getRdate().substring(2, 10) %></span>
                <p class="content"><%= bcb.getContent() %></p>
                <% 		if (bub.getUid().equals(bcb.getUid())) { %>
                <div>
                    <a href="#" class="remove">삭제</a>
                    <a href="#" class="modify">수정</a>
                </div>
                <% 		}
                	}
            	} else {%>
                <p class="empty">등록된 댓글이 없습니다.</p>
                <% } %>
            </article>
        </section>

        <!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="#" method="post">
            	<input type="hidden" name="no" value="<%=no%>" >
                <textarea name="content" placeholder="댓글 입력"></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" value="작성완료" class="btn btnComplete">
                </div>
            </form>
        </section>
    </section>
</main>
<%@ include file="./_footer.jsp" %>