<%@page import="java.util.Date"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.Jboard1.bean.BoardCommentBean"%>
<%@page import="kr.co.Jboard1.bean.BoardUserBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	String parent = request.getParameter("no");
	String content = request.getParameter("content");
	//String pg = request.getParameter("pg");
	BoardUserBean bub = (BoardUserBean) session.getAttribute("sessUser");
	
	if(bub == null) {
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
	
	BoardCommentBean bcb = new BoardCommentBean();
	bcb.setParent(Integer.parseInt(parent));
	bcb.setUid(bub.getUid());
	bcb.setNick(bub.getNick());
	bcb.setContent(content);
	bcb.setRegip(request.getRemoteAddr());
	
	// 데이터베이스 처리
	BoardCommentDAO bcdao = new BoardCommentDAO(); //BoardCommentDAO.getInstance();
	int result = bcdao.insertComment(bcb); // 댓글 작성
	String date = bcdao.updateCommentNumber(parent); // 댓글 갯수 증가, 마지막 등록 댓글 날짜 리턴
	bcdao.close();
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	json.addProperty("nick", bub.getNick());
	json.addProperty("date", date.substring(2, 10));
	json.addProperty("content", content);
	
	String jsonData = json.toString();
	out.print(jsonData);
	
	//response.sendRedirect("/Jboard1/view.jsp?no="+parent+"&&pg="+pg);
%>