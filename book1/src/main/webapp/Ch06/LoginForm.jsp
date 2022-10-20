<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session</title>
		<!-- 
			날짜 : 2022/10/19
			이름 : 심규영
			내용 : 로그인 폼, p216
		 -->
	</head>
	<body>
		<!-- 
			날짜 : 2022/10/20
			이름 : 심규영
			내용 : 로그인 폼에 공동 링크 추가, p226
		 -->
		<%@ include file="../common/Link.jsp" %>
		<h2>로그인 페이지</h2>
		<span style="color: red; font-size: 1.2em;">
			<%= request.getAttribute("LoginErrMsg") == null ?
					"" : request.getAttribute("LoginErrMsg")%>
		</span>
		<%
			// 로그인 상태 확인
			if(session.getAttribute("UserId") == null) {
			// 로그아웃 상태				
		%>
		<script>
				const validateForm = (form) => {
					if (!form.user_id.value) {
						alert("아이디를 입력하세요.");
						return false;
					}
					if (form.user_pw.value == "") {
						alert("패스워드를 입력하세요.");
						return false;
					}
				}
		</script>
		<form action="LoginProcess.jsp" method="post" name="loginFrm"
		 onsubmit="return validateForm(this);">
		 	아이디 : <input type="text" name="user_id"><br/>
		 	패스워드 : <input type="password" name="user_pw"><br>
		 	<input type="submit" value="로그인하기"/>
		 </form>
		 <%
			} else { // 로그인된 상태
		 %>
		 	<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br>
		 	<a href="Logout.jsp">[로그아웃]</a>
		 <%
			}
		 %>
	</body>
</html>