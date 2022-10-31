<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.Jboard1.DAO.BoardArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//multipart 폼 데이터 수신
	String savePath = application.getRealPath("/file");
	int maxSize = 1024 * 1024 * 10;
		// MultipartRequest(request, 저장경로,파일최대크기,utf-8,new defaultfileRenamePolicy());
	MultipartRequest mr = new MultipartRequest(request, savePath,
			maxSize, "UTF-8", new DefaultFileRenamePolicy());

	String title 	= mr.getParameter("title");
	String content 	= mr.getParameter("content");
	String uid 		= mr.getParameter("uid");
	String fname 	= mr.getFilesystemName("fname");
	String regip 	= request.getRemoteAddr();
	
	// 데이터 베이스 처리
	BoardArticleDAO badao = new BoardArticleDAO(); //BoardArticleDAO.getInstance();
	badao.InsertBoardArticleDAO(title, content, fname, 
			uid, regip, savePath);
	badao.close();
	
	// 목록으로 이동
	response.sendRedirect("/Jboard1/list.jsp");
%>