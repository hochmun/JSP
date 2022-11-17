<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.util.Map"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardUserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String parent = request.getParameter("no");
	String content = request.getParameter("content");
	BoardUserDTO budto = (BoardUserDTO)session.getAttribute("Farmstory_1User");
	
	if (budto == null) {
		response.sendRedirect("/Farmstory_1/user/login.jsp?error=105");
		return;
	}
	
	BoardArticleDTO badto = new BoardArticleDTO();
	badto.setParent(parent);
	badto.setContent(content);
	badto.setRegip(request.getRemoteAddr());
	badto.setUid(budto.getUid());
	
	Map<String, Object> map = BoardArticleDAO.getInstance().insertCommnet(badto);
	BoardArticleDTO badto2 = (BoardArticleDTO)map.get("badto");
	int result = (int)map.get("result");
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("nick", budto.getNick());
	json.addProperty("date", badto2.getRdate().substring(2, 10));
	json.addProperty("content", content);
	json.addProperty("no", String.valueOf(badto2.getNo()));
	json.addProperty("parent", parent);
	
	String jsonData = json.toString();
	out.print(jsonData);
%>