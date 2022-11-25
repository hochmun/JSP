<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"/>
<script>
	$(document).ready(function(){
		// 글 삭제
		$('.btnRemove').click(function(){
			const isDelete = confirm('정말 삭제 하시겠습니까?');
			if(isDelete) return true;
			else return false;
		});
		
		// 댓글 삭제
		$(document).on('click','.remove',function(e){
			e.preventDefault();
			const isDeleteOk = confirm('정말 삭제 하시겠습니까?');
			
			if(isDeleteOk) {
				const article = $(this).closest('article');
				const no = $(this).attr('data-no');
				const parent = $(this).attr('data-parent');
				const type = 3;
				
				$.ajax({
					url: '/Jboard2/view.do',
					type: 'post',
					data: {"no":no,"parent":parent,"type":type},
					dataType: 'json',
					success: function(data){
						alert('댓글이 삭제 되었습니다.');
						article.hide();
					}
				});
			}
		});
		
		
		// 댓글 수정
		$(document).on('click','.modify', function(e){
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
				const type = 2;
				
				$.ajax({
					url: '/Jboard2/view.do',
					type: 'post',
					data: {"no":no,"content":content,"type":type},
					dataType: 'json',
					success: (data)=>{
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
			const content  = $(this).children('textarea[name=content]').val();
			const no 	   = $(this).children('input[name=no]').val();
			const textarea = $(this).children('textarea[name=content]');
			const type 	   = 1;
			
			if (content == '') {
				alert('댓글을 작성하세요.');
				return false;
			}
			
			$.ajax({
				url: '/Jboard2/view.do',
				method: 'post',
				data: {"no":no,"content":content,"type":type},
				dataType: 'json',
				success: (data)=>{
					if(data.result > 0) {
						let article = "<article>"
							+ "<span class='nick'>"+data.nick+"</span>&nbsp;"
							+ "<span class='date'>"+data.date+"</span>"
							+ "<p class='content'>"+data.content+"</p>"
							+ "<div>"
							+ "<a href='#' class='remove' data-no="+data.no+" data-parent="+data.parent+">삭제</a>&nbsp;"
							+ "<a href='#' class='modify' data-no="+data.no+">수정</a>"
							+ "</div>"
							+ "</article>";
						
						$('.commentList .empty').hide();
						$('.commentList').append(article);
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
                <td><input type="text" name="title" value="${ avo.title }" readonly/></td>
            </tr>
            <c:if test="${ avo.file gt 0 }">
	            <tr>
	                <th>파일</th>
	                <td><a href="#">${ fvo.oriName }</a>&nbsp;<span>${ fvo.download }</span>회 다운로드</td>
	            </tr>
            </c:if>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly>${ avo.content }</textarea>
                </td>
            </tr>                    
        </table>
        
        <div>
        	<c:if test="${ sessUser.uid eq avo.uid }">
	            <a href="/Jboard2/delete.do?no=${ param.no }&pg=${ param.pg }&file=${ avo.file }" class="btn btnRemove">삭제</a>
	            <a href="/Jboard2/modify.do?no=${ param.no }&pg=${ param.pg }" class="btn btnModify">수정</a>
            </c:if>
            <a href="/Jboard2/list.do?pg=${ param.pg }" class="btn btnList">목록</a>
        </div>

        <!-- 댓글목록 -->
        <section class="commentList">
            <h3>댓글목록</h3>                   
			<c:choose>
				<c:when test="${ avo.comment gt 0 }">
					<c:forEach var="vo" items="${ avo2 }">
						<article>
			                <span class="nick">${ vo.nick }</span>
			                <span class="date">${ vo.rdate }</span>
			                <p class="content">${ vo.content }</p>                        
			                <div>
			                	<c:choose>
				                	<c:when test="${ sessUser.uid eq vo.uid }">
					                    <a href="#" class="remove" data-no="${ vo.no }" data-parent="${ param.no }">삭제</a>
					                    <a href="#" class="modify" data-no="${ vo.no }">수정</a>
					                </c:when>
					                <c:otherwise>
					                	<a>&nbsp;</a>
					                </c:otherwise>
					            </c:choose>
			                </div>
	            		</article>
            		</c:forEach>
				</c:when>
				<c:otherwise>
					<p class="empty">등록된 댓글이 없습니다.</p>
				</c:otherwise>
			</c:choose>
        </section>

        <!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form>
            	<input type="hidden" name="no" value="${ avo.no }"/>
                <textarea name="content" placeholder="댓글내용 입력"></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>
    </section>
</main>
<jsp:include page="./_footer.jsp"/>