<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user::modify</title>
	</head>
	<body>
		<h3>user 수정</h3>
		<a href="/Ch09/">처음으로</a>
		<a href="/Ch09/user3/list.do">user 목록</a>
		
		<form action="/Ch09/user3/modify.do" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value="${ vo.uid }"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${ vo.name }"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="${ vo.hp }"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${ vo.age }"></td>
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