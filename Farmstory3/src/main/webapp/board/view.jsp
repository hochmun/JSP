<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../_header.jsp"></jsp:include>
<jsp:include page="./_cate${ param.cate }.jsp"></jsp:include>
<script src="/Farmstory3/js/boardView.js"></script>
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
	                <td><a href="/Farmstory3/board/download.do?no=${ avo.no }">${ fvo.oriName }</a>&nbsp;<span>${ fvo.download }</span>회 다운로드</td>
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
	            <a href="/Farmstory3/board/delete.do?cate=${ param.cate }&tit=${ param.tit }&search=${ param.search }&pg=${ param.pg }&no=${ param.no }&file=${ avo.file }" class="btn btnRemove">삭제</a>
	            <a href="/Farmstory3/board/modify.do?cate=${ param.cate }&tit=${ param.tit }&search=${ param.search }&pg=${ param.pg }&no=${ param.no }" class="btn btnModify">수정</a>
            </c:if>
            <a href="/Farmstory3/board/list.do?cate=${ param.cate }&tit=${ param.tit }&search=${ param.search }&pg=${ param.pg }" class="btn btnList">목록</a>
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
            	<input type="hidden" name="uid" value="${ sessUser.uid }"/>
            	<input type="hidden" name="no" value="${ avo.no }">
                <textarea name="content" placeholder="${ sessUser.uid ne null ? '댓글내용 입력' : '로그인 후 작성 하실수 있습니다' }" 
                ${ sessUser.uid eq null ? 'readonly' : '' }></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>

    </section>
</article>
</section>
</div>
</main>
<jsp:include page="../_footer.jsp"></jsp:include>