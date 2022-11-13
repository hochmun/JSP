<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::modify</title>
	</head>
	<body>
		<h3>user 수정</h3>
		<a href="/Ch09/">처음으로</a>
		<a href="/Ch09/user4/list.do">user 목록</a>
		
		<form action="/Ch09/user4/modify.do" method="post">
			<input type="hidden" name="seq" value="${ vo.seq }" >
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${ vo.name }"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<select name="gender">
							<c:choose>
								<c:when test="${ vo.gender eq 1 }">
									<option value="1" selected="selected">남자</option>
									<option value="2">여자</option>
								</c:when>
								<c:otherwise>
									<option value="1">남자</option>
									<option value="2" selected="selected">여자</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${ vo.age }"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${ vo.addr }"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정하기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>