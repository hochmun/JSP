<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cookie</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 심규영
			내용 : 쿠키 동작 확인, p141
		 -->
	</head>
	<body>
		<h2>1. 쿠키(Cookie) 설정</h2>
		<%
		// 쿠키 생성
		Cookie cookie = new Cookie("myCookie", "쿠키맛나요");
		// 경로를 컨텍스트 루트로 설정
		cookie.setPath(request.getContextPath());
		// 유지 기간을 1시간으로 설정
		cookie.setMaxAge(3600);
		// 응답 헤더에 쿠키 추가
		response.addCookie(cookie);
		%>
		
		<h2>2. 쿠키 설정 직후 쿠키값 확인하기</h2>
		<%
		// 요청 헤더의 모든 쿠키 얻기
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 쿠키 각각의...
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				out.println(String.format("%s : %s<br>", cookieName,
						cookieValue));
			}
		}
		%>
		
		<h2>3. 페이지 이동 후 쿠키값 확인하기</h2>
		<a href="CookieResult.jsp">
			다음 페이지에서 쿠키값 확인하기
		</a>
	</body>
</html>