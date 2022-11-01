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
	BoardArticleDAO badao = new BoardArticleDAO();
	BoardCommentDAO bcdao = new BoardCommentDAO();
	
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
		
		// 댓글 삭제
		$(document).on('click', '.remove', function(e){
			e.preventDefault();
			const isDeleteOk = confirm('정말 삭제 하시겠습니까?');
			
			if(isDeleteOk) {
				const article = $(this).closest('article');
				const no = $(this).attr('data-no');
					
				const jsonData = {
						"no": no
				};
				
				$.ajax({
					url: '/Jboard1/proc/commentRemoveProc.jsp',
					type: 'POST',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result > 0) {
							alert('댓글이 삭제 되었습니다.');
							article.hide();
						}
					}
				});
			}
		});
		
		// 댓글 수정
		$(document).on('click', '.modify', function(e){
			e.preventDefault();
			
			const txt = $(this).text();
			const p_tag = $(this).parent().prev();
			
			if(txt == '수정') {
				// 수정 모드
				$(this).text('수정완료');
				p_tag.attr('contentEditable', true).focus();
			} else {
				// 수정 완료
				$(this).text('수정');
				
				const no = $(this).attr('data-no');
				const content = p_tag.text();
				
				const jsonData = {
						"cno": no,
						"content": content
				};
				
				$.ajax({
					url: '/Jboard1/proc/commentModifyProc.jsp',
					type: 'POST',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
						if(data.result == 1) {
							alert('댓글이 수정되었습니다.');
							p_tag.attr('contentEditable', false);
						}
					}
				});
			}
		});
		
		// 댓글 작성
		$('.commentForm > form').submit(function(){
			let content 	= $(this).children('textarea[name=content]').val();
			let no			= $(this).children('input[name=no]').val();
			let textarea 	= $(this).children('textarea[name=content]');
			
			
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
									+ "<span class='nick'>"+data.nick+"</span>&nbsp;"
									+ "<span class='date'>"+data.date+"</span>"
									+ "<p class='content'>"+data.content+"</p>"
									+ "<div>"
									+ "<a href='#' class='remove'>삭제</a>&nbsp;"
									+ "<a href='#' class='modify'>수정</a>"
									+ "</div>"
									+ "</article>";
						
						$('.comments .empty').hide();
						$('.comments').append(article);
						textarea.val('');
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
             <% for (BoardCommentBean bcb : bcbs) { %>
            <article>
                <span class="nick"><%= bcb.getNick() %></span>
                <span class="date"><%= bcb.getRdate().substring(2, 10) %></span>
                <p class="content"><%= bcb.getContent() %></p>
                <div>
                <% 		if (bub.getUid().equals(bcb.getUid())) { %>
                    <a href="#" class="remove" data-no="<%=bcb.getCno()%>">삭제</a>
                    <a href="#" class="modify" data-no="<%=bcb.getCno()%>">수정</a>
                <%		} else { %>
                	<a>&nbsp;</a>
                <% 		} %>
                </div>
  			</article>
                <% } 
                if (bcbs.size() == 0) {%>
            <article>
                <p class="empty">등록된 댓글이 없습니다.</p>
            </article>
                <% } %>
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