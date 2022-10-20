<%@page import="DAO.BoardTermsDAO"%>
<%@page import="bean.BoardTermsBean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	$(()=>{
		$('.btnNext').click(()=>{
			if ($('input[class=terms]').is(':checked') && 
			$('input[class=privacy]').is(':checked')) {
				return true;
			} else {
				alert('동의 체크를 하셔야 합니다.');
				return false;
			}
		});
	});
</script>
<%
	BoardTermsDAO btdao = new BoardTermsDAO();
	BoardTermsBean btb = btdao.GetBoardTermsDAO();
	btdao.close();
%>
<main id="user">
    <section class="terms">
       <table border="1">
        <caption>사이트 이용약관</caption>
        <tr>
            <td>
                <textarea name="terms"><%= btb.getTerms() %></textarea>
                <label><input type="checkbox" class="terms">&nbsp;동의합니다.</label>
            </td>
        </tr>
       </table>
       <table border="1">
        <caption>개인정보 취급방침</caption>
        <tr>
            <td>
                <textarea name="privacy"><%= btb.getPrivacy() %></textarea>
                <label><input type="checkbox" class="privacy">&nbsp;동의합니다.</label>
            </td>
        </tr>
       </table>

        <p>
            <a href="/Jboard1/user/login.jsp" class="btn btnCancel">취소</a>
            <a href="/Jboard1/user/register.jsp" class="btn btnNext">다음</a>
        </p>

    </section>
</main>
<%@ include file="./_footer.jsp" %>