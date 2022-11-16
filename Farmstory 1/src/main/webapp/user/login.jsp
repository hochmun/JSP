<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String error = request.getParameter("error");
%>
<script>
	const error = "<%= error %>";
	
	if (error == "101") {
		alert("일치하는 회원이 없습니다.\n아이디, 비밀번호를 다시 확인 하십시오.");
	} else if (error == "102") {
		alert("회원가입 성공");
	} else if (error == "103") {
		alert("회원가입 실패");
	}
</script>
<main id="user">
    <section class="login">
        <form action="./proc/loginProc.jsp" method="post">
            <table>
                <tr>
                    <td><img src="./img/login_ico_id.png" alt="아이디"></td>
                    <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                </tr>
                <tr>
                    <td><img src="./img/login_ico_pw.png" alt="비밀번호"></td>
                    <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                </tr>
            </table>
            <input type="submit" value="로그인" class="btnLogin">
        </form>
        <div>
            <h3>회원로그인 안내</h3>
            <p>
                아직 회원이 아니시면 회원으로 가입하세요.
            </p>
            <a href="./terms.jsp">회원가입</a>
        </div>
    </section>
</main>
<%@ include file="/_footer.jsp" %>