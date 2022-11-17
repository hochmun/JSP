<%@page import="kr.co.Farmstory_1.DTO.BoardTermsDTO"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardTermsDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	BoardTermsDTO btdto = BoardTermsDAO.getInstance().selectTerms();
%>
<script>
	$(()=>{
		$('.btnNext').click(()=>{
			if($('.terms').is(':checked') && $('.privacy').is(':checked')) {
				return true;
			} else {
				alert('동의 체크를 하셔야 합니다.');
				return false;
			}
		});
	});
</script>
<main id="user">
    <section class="terms">
       <table border="1">
        <caption>사이트 이용약관</caption>
        <tr>
            <td>
                <textarea name="terms"><%= btdto.getTerms() %></textarea>
                <label><input type="checkbox" class="terms">&nbsp;동의합니다.</label>
            </td>
        </tr>
       </table>
       <table border="1">
        <caption>개인정보 취급방침</caption>
        <tr>
            <td>
                <textarea name="privacy"><%= btdto.getPrivacy() %></textarea>
                <label><input type="checkbox" class="privacy">&nbsp;동의합니다.</label>
            </td>
        </tr>
       </table>

        <p>
            <a href="./login.jsp" class="btn btnCancel">취소</a>
            <a href="./register.jsp" class="btn btnNext">다음</a>
        </p>

    </section>
</main>
<%@ include file="/_footer.jsp" %>