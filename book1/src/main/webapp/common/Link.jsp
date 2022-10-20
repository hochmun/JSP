<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/20
	이름 : 심규영
	내용 : 공동 링크 추가
 -->
<table border="1" width="90%">
	<tr>
		<td align="center">
			<!-- 로그인 여부에 따른 메뉴 변화 -->
			<% if (session.getAttribute("UserId") == null) { %>
				<a href="../Ch06/LoginForm.jsp">로그인</a>
			<% } else { %>
				<a href="../Ch06/Logout.jsp">로그아웃</a>
			<% } %>
			<!-- 8장과 9장의 회원제 게시판 프로젝트에서 사용할 링크 -->
			&nbsp;&nbsp;&nbsp;
			<a href="../Ch08/List.jsp">게시판(페이징x)</a>
			&nbsp;&nbsp;&nbsp;
			<a href="../Ch09/List.jsp">게시판(페이징O)</a>
		</td>
	</tr>
</table>

