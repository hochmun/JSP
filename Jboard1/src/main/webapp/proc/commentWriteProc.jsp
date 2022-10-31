<%@page import="kr.co.Jboard1.bean.BoardCommentBean"%>
<%@page import="kr.co.Jboard1.bean.BoardUserBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardUserDAO"%>
<%@page import="kr.co.Jboard1.DAO.BoardCommentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	String parent = request.getParameter("parent");
	String content = request.getParameter("content");
	String pg = request.getParameter("pg");
	BoardUserBean bub = (BoardUserBean) session.getAttribute("sessUser");
	
	BoardCommentBean bcb = new BoardCommentBean();
	bcb.setParent(Integer.parseInt(parent));
	bcb.setUid(bub.getUid());
	bcb.setNick(bub.getNick());
	bcb.setContent(content);
	bcb.setRegip(request.getRemoteAddr());
	
	// 데이터베이스 처리
	BoardCommentDAO bcdao = BoardCommentDAO.getInstance();
	bcdao.insertComment(bcb); // 댓글 작성
	bcdao.updateCommentNumber(parent); // 댓글 갯수 증가
	bcdao.close();
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent+"&&pg="+pg);
%>