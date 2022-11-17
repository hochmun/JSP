<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.util.Map"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String parent = request.getParameter("no");
	String content = request.getParameter("content");
	BoardUserDTO budto = (BoardUserDTO)session.getAttribute("Farmstory_1User");
	
	int result = 0;
	JsonObject json = new JsonObject();
	
	if (budto == null) {
		result = -1; // 로그인이 아닐경우 -1값을 반환
	} else {
		BoardArticleDTO badto = new BoardArticleDTO();
		badto.setParent(parent);
		badto.setContent(content);
		badto.setRegip(request.getRemoteAddr());
		badto.setUid(budto.getUid());
		
		Map<String, Object> map = BoardArticleDAO.getInstance().insertComment(badto);
		BoardArticleDTO badto2 = (BoardArticleDTO)map.get("badto");
		result = (int)map.get("result");
		
		json.addProperty("nick", budto.getNick());
		json.addProperty("date", badto2.getRdate().substring(2, 10));
		json.addProperty("content", content);
		json.addProperty("no", String.valueOf(badto2.getNo()));
		json.addProperty("parent", parent);
	}
	
	json.addProperty("result", result);
	
	
	String jsonData = json.toString();
	out.print(jsonData);
%>