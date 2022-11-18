<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h3>고객수정</h3>
		
		<a href="/Bookstore2/">처음으로</a>
		<a href="/Bookstore2/customer/list.do">고객목록</a>
		
		<form action="/Bookstore2/customer/modify.do" method="post">
			<table border="1">
				<tr>
					<th>고객번호</th>
					<td><input type="text" name="custId" readonly="readonly" value="${ cv.custId }"></td>
				</tr>
				<tr>
					<th>고객명</th>
					<td><input type="text" name="name" value="${ cv.name }"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" value="${ cv.address }"></td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><input type="text" name="phone" value="${ cv.phone }"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="등록하기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>