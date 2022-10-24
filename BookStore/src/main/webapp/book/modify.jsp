<%@page import="org.apache.el.parser.BooleanNode"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.Jboard1.config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.Jboard1.bean.BookBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("utf-8");
	String bookId = request.getParameter("bookId");
	BookBean bb = null;
	
	// 데이터베이스 작업
	try {
		Connection conn = DBCP.getConnection();
		
		String sql = "select * from `book` where `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookId);
		
		ResultSet rs = psmt.executeQuery();
		
		bb = new BookBean();
		if(rs.next()) {
			bb.setBookId(rs.getInt(1));
			bb.setBookName(rs.getString(2));
			bb.setPublisher(rs.getString(3));
			bb.setPrice(rs.getInt(4));
		}
		
		rs.close();
		psmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>도서수정</title>
	</head>
	<body>
		<h2>도서수정</h2>
		<a href="../index.jsp">처음으로</a>
		<a href="./list.jsp">도서목록</a>
		
		<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>도서번호</td>
					<td><input type="text" name="bookId" 
					readonly="readonly" value="<%= bb.getBookId() %>"></td>
				</tr>
				<tr>
					<td>도서명</td>
					<td><input type="text" name="bookName"
					value="<%= bb.getBookName() %>"></td>
				</tr>
				<tr>
					<td>출판사</td>
					<td><input type="text" name="publisher"
					value="<%= bb.getPublisher() %>"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" name="price"
					value="<%= bb.getPrice() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>