<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::list</title>
	</head>
	<body>
		<h3>user 목록</h3>
		<a href="/Ch09/">처음으로</a>
		<a href="/Ch09/user1/register.do">user 등록</a>
		
		<table border="1">
			<tr>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<c:forEach var="user" items="${ requestScope.users }">
				<tr>
					<td>${ user.name }</td>
					<c:choose>
						<c:when test="${ user.gender eq 1 }">
							<td>남자</td>
						</c:when>
						<c:otherwise>
							<td>여자</td>
						</c:otherwise>
					</c:choose>
					<td>${ user.age }</td>
					<td>${ user.addr }</td>
					<td>
						<a href="/Ch09/user1/modify.do?seq=${ user.seq }">수정</a>
						<a href="/Ch09/user1/delete.do?seq=${ user.seq }">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>