package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 날짜 : 2022/10/13
 * 이름 : 심규영
 * 내용 : 쿠키 관리자, p157
 */
/**
 * 쿠키를 편리하게 관리하기 위한 클래스
 * @author 심규영
 */
public class CookieManager {
	
	/**
	 * 명시한 이름, 값, 유지 기간 조건으로 새로운 쿠키를 생성
	 * @param response response 응답 객체
	 * @param cName 쿠키의 이름
	 * @param cValue 쿠키의 값
	 * @param cTime 쿠키의 유지 시간
	 */
	public static void makeCookie(HttpServletResponse response,
			String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	
	/**
	 * 명시한 이름의 쿠키를 찾아 그 값을 반환
	 * @param request request 내장객체
	 * @param cName 쿠키의 이름
	 * @return String - 찾은 쿠키의 값
	 */
	public static String readCookie(HttpServletRequest request,
			String cName) {
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		return cookieValue;
	}
	/**
	 * 명시한 이름의 쿠키를 삭제합니다
	 * @param response response 객체
	 * @param cName 쿠키의 이름
	 */
	public static void deleteCookie(HttpServletResponse response, 
			String cName) {
		makeCookie(response, cName, "", 0);
	}
}
