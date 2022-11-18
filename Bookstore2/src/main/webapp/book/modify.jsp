<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h3>도서수정</h3>
		
		<a href="/Bookstore2/">처음으로</a>
		<a href="/Bookstore2/book/list.do">도서목록</a>
		
		<form action="/Bookstore2/book/modify.do" method="post">
			<table border="1">
				<tr>
					<th>도서번호</th>
					<td><input type="text" name="bookId" readonly="readonly" value="${ bv.bookId }"></td>
				</tr>
				<tr>
					<th>도서명</th>
					<td><input type="text" name="bookName" value="${ bv.bookName }"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td><input type="text" name="publisher" value="${ bv.publisher }"></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="price" value="${ bv.price }"></td>
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