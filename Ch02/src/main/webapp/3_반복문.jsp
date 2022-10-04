<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>3_반복문</title>
		<%--
			날짜 : 2022/10/04
			이름 : 심규영
			내용 : JSP 반복문 실습하기
		 --%>
	</head>
	<body>
		<h3>반복문</h3>
		
		<h4>for</h4>
		<%
			for(int i = 1; i <= 3; i++) {
				out.println("<p>i : "+i+"</p>");
			}
		%>
		
		<% 
			for(int k = 1; k <= 3; k++) { %>
				<p>k : <%=k %></p>
		<% } %>
		<h4>while</h4>
		<%
			int j = 1;
			while(j <= 3) {
		%>
			<p>j : <%= j %></p>
		<%
			j++; }
		%>
		<h4>구구단</h4>
		<table border="1">
			<tr>
				<td>2단</td>
				<td>3단</td>
				<td>4단</td>
				<td>5단</td>
				<td>6단</td>
				<td>7단</td>
				<td>8단</td>
				<td>9단</td>
			</tr>
			<% 
				for(int a = 1; a <= 9; a++) {
					out.println("<tr>");
					for(int b = 2; b <= 9; b++) {
						out.println("<td>"+b+" x "+a+" = "+(b*a));
					}
					out.println("</tr>");
				}
			%>
			
		</table>
	</body>
</html>