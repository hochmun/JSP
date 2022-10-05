<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>page 지시어 - errorPage/isErrorPage 속성</title>
		<%--
			날짜 : 2022/10/05
			이름 : 심규영
			내용 : isErrorPage 속성을 설정한 에러 페이지 작성, p70
		 --%>
	</head>
	<body>
		<h2>서비스 중 일시적인 오류가 발생하였습니다</h2>
		<p>
			오류명 : <%= exception.getClass().getName() %> <br>
			오류 메시지 : <%= exception.getMessage() %>
		</p>
	</body>
</html>