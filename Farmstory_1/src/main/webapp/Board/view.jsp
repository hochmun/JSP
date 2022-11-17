<%@page import="java.util.List"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardFileDTO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	Map<String, Object> dtos = BoardArticleDAO.getInstance().selectArticle(no);
	BoardArticleDTO badto = (BoardArticleDTO)dtos.get("badto");
	BoardFileDTO bfdto = (BoardFileDTO)dtos.get("bfdto");
	List<BoardArticleDTO> badtos = (List<BoardArticleDTO>)dtos.get("badtos");
	
	// 로그인을 하지 않았거나 로그인한 아이디와 게시글의 글쓴이의 아이디가 같지 않을 때 게시물 조회수 증가
	if(budto == null || !budto.getUid().equals(badto.getUid())) BoardArticleDAO.getInstance().updateHitArticle(no);
%>
<script>
	$(document).ready(()=>{
		// 글 삭제 전 물어 보기
		$('.btnRemove').click(function(){
			const isDelete = confirm('정말 삭제 하시겠습니까?');
			if(isDelete) return true;
			else return false;
		});
		
		// 댓글 삭제
		$(document).on('click', '.remove', function(e){
			e.preventDefault();
			const isDeleteOk = confirm('정말 삭제 하시겠습니까?');
			
			if (isDeleteOk) {
				const article = $(this).closest('article');
				const no = $(this).attr('data-no');
				const parent = $(this).attr('data-parent');
				
				const jsonData = {
						"no": no,
						"parent": parent
				};
				
				$.ajax({
					url: '/Farmstory_1/Board/proc/commentRemoveProc.jsp',
					type: 'POST',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
						if(data.result > 0) {
							alert('댓글이 삭제 되었습니다.');
							article.hide();
						}
					}
				});
			}
		});
		
		// 댓글 수정
		$(document).on('click', '.modify', function(e) {
			e.preventDefault();
			
			const txt = $(this).text();
			const p_tag = $(this).parent().prev();
			
			if (txt == '수정') {
				// 수정 모드
				$(this).text('수정완료');
				p_tag.attr('contentEditable', true).focus();
			} else {
				// 수정 왈료
				$(this).text('수정');
				
				const no = $(this).attr('data-no');
				const content = p_tag.text();
				
				const jsonData = {
						"no": no,
						"content": content
				};
				
				$.ajax({
					url: '/Farmstory_1/Board/proc/commentModifyProc.jsp',
					type: 'POST',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result == 1) {
							alert('댓글이 수정되었습니다.');
							p_tag.attr('contentEditable', false);
						}
					}
				});
			}
		});
		
		//댓글 작성
		$('.commentForm > form').submit(function(){
			const content = $(this).children('textarea[name=content]').val();
			const no = $(this).children('input[name=no]').val();
			const textarea = $(this).children('textarea[name=content]');
			
			if (content == '') {
				alert('댓글을 작성하게요.');
				return false;
			}
			
			const jsonData = {
					"no" : no,
					"content":content
			};
			
			$.ajax({
				url:'/Farmstory_1/Board/proc/commentWriteProc.jsp',
				method:'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result > 0) {
						const article = "<article>"
										+ "<span class='nick'>"+data.nick+"</span>&nbsp;"
										+ "<span class='date'>"+data.date+"</span>"
										+ "<p class='content'>"+data.content+"</p>"
										+ "<div>"
										+ "<a herf='#' class='remove' data-no="+data.no+" data-parent="+data.parent+">삭제</a>&nbsp;"
										+ "<a herf='#' class='modify' data-no="+data.no+">수정</a>"
										+ "</div>" 
										+ "</article>";
						$('.comments .empty').hide();
						$('.comments').append(article);
						textarea.val('');
					} else if(data.result = -1) {
						location.href = "/Farmstory_1/user/login.jsp?error=105";
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
                <td><input type="text" name="title" value="<%=badto.getTitle()%>" readonly></td>
            </tr>
            <% if(badto.getFile() > 0) { %>
		        <tr>
		            <th>첨부파일</th>
		            <td><a href="/Farmstory_1/Board/proc/downLoad.jsp?no=<%=badto.getNo()%>"><%= bfdto.getOriName() %></a>&nbsp;<span><%= bfdto.getDownload() %></span>회 다운로드</td>
		        </tr>
            <% } %>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly><%= badto.getContent() %></textarea>
                </td>
            </tr>
        </table>
        <div>
        	<% if(budto != null && budto.getUid().equals(badto.getUid())) { // 게시물의 글쓴이가 아닐때 삭제와 수정버튼 제거 %>
            	<a href="/Farmstory_1/Board/proc/deleteProc.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pg %>&no=<%= no %>&file=<%= badto.getFile() %>" class="btn btnRemove">삭제</a>
            	<a href="/Farmstory_1/Board/modify.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>&pg=<%= pg %>" class="btn btnModify">수정</a>
            <% } %>
            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pg %>" class="btn btnList">목록</a>
        </div>
        <!-- 댓글목록 -->
        <section class="comments">
            <h3>댓글목록</h3>
            <% for (BoardArticleDTO badto2 : badtos) { %>
	            <article>
	                <span class="nick"><%= badto2.getNick() %></span>
	                <span class="date"><%= badto2.getRdate().substring(2, 10) %></span>
	                <p class="content"><%= badto2.getContent() %></p>
	                <div>
	                	<% if (budto != null && budto.getUid().equals(badto2.getUid())) { %>
	                    	<a href="#" class="remove" data-no="<%=badto2.getNo()%>" data-parent="<%=no%>">삭제</a>
	                    	<a href="#" class="modify" data-no="<%=badto2.getNo()%>">수정</a>
	                    <% } else { %>
	                    	<a>&nbsp;</a>
	                    <% } %>
	                </div>
	            </article>
            <% } %>
            <% if (badtos.size() == 0) { %>
	            <article>
	                <p class="empty">등록된 댓글이 없습니다.</p>
	            </article>
            <% } %>
        </section>

        <!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="#" method="post">
            	<input type="hidden" name="no" value="<%= no %>">
                <textarea name="content" placeholder="댓글내용 입력"></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" value="작성완료" class="btn btnComplete">
                </div>
            </form>
        </section>
    </section>
</main>
<%@ include file="/_footer.jsp" %>