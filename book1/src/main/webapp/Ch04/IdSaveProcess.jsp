<%@page import="utils.JSFunction"%>
<%@page import="utils.CookieManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/13
	이름 : 심규영
	내용 : 로그인 및 아이디 저장
 -->
 <%
 	String user_id = request.getParameter("user_id");
 	String user_pw = request.getParameter("user_pw");
 	String save_check = request.getParameter("save_check");
 	
 	// 사용자 인증
 	if ("must".equals(user_id) && "1234".equals(user_pw)) {
 		// 로그인 성공
 		// 아이디 저장 체크
 		if (save_check != null && save_check.equals("Y")) {
 			// 쿠키 생성
 			CookieManager.makeCookie(response, "loginId", user_id, 86400);
 		} else {
 			// 쿠키 제거
 			CookieManager.deleteCookie(response, "loginId");
 		}
 		
 		JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
 	} else {
 		// 로그인 실패
 		JSFunction.alertBack("로그인에 실패했습니다.", out);
 	}
 %>