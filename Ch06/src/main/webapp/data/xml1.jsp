<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// XML 생성
	String xml = "<user>"
				+"<uid>a101</uid>"
				+"<name>홍길동</name>"
				+"<hp>010-1234-1001</hp>"
				+"<age>23</age>"
				+"</user>";
				
	// xml 출력
	out.print(xml);
%>