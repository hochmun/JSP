package utils;

import javax.servlet.jsp.JspWriter;

/*
 * 날짜 : 2022/10/13
 * 이름 : 심규영
 * 내용 : 도우미 클래스, p155
 */
/**
 * JSP 에서 자바스크립트를 쉽게 사용하기 위한 클래스
 * 
 * @author 심규영
 * @version 0.1
 */
public class JSFunction {
	/**
	 *  메시지 알림창을 띄운 후 명시한 URL로 이동
	 *  
	 *  @param msg 스크립트를 이용해 알림창으로 출력할 메시지
	 *  @param url 이동할 URL
	 *  @param out out
	 *  @return out.println(script);
	 */
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
						  + "<script>"
						  + "	alert('" + msg + "');"
						  + "	location.href='" + url +"';"
						  + "</script>";
			out.println(script);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 *  메시지 알림창을 띄운 후 이전 페이지로 돌아감
	 *  
	 *  @param msg 알림창을 통해 출력할 메시지
	 *  @param out out
	 *  @return out.println(script)
	 */
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
						  + "<script>"
						  + "	alert('" + msg + "');"
						  + "	history.back();"
						  + "</script>";
			out.println(script);
		} catch (Exception e) {}
	}
}
