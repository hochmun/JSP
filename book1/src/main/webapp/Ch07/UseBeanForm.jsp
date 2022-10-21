<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/21
	이름 : 심규영
	내용 : 폼값을 전송하는 페이지, p245
 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>액션 태그 - UseBean</title>
	</head>
	<body>
		<h2>액션 태그로 폼값 한 번에 받기</h2>
		<form action="UseBeanAction.jsp" method="post">
			이름 : <input type="text" name="name"/><br/>
			나이 : <input type="text" name="age"/><br/>
			<input type="submit" value="폼값 전송"/>
		</form>
	</body>
</html>