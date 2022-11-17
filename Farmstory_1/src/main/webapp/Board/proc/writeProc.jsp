<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardArticleDAO"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// multipart form data 수신
	String savePath = application.getRealPath("/file");
 	int maxSize = 1024 * 1024 * 10; // 10MB
 	MultipartRequest mr = new MultipartRequest(request, savePath,
 			maxSize, "UTF-8", new DefaultFileRenamePolicy());
	
	String group 	= mr.getParameter("group");
	String cate 	= mr.getParameter("cate");
	String title 	= mr.getParameter("title");
	String content 	= mr.getParameter("content");
	String fname 	= mr.getFilesystemName("fname");
	String uid 		= mr.getParameter("uid");
	String regip 	= request.getRemoteAddr();
	
	BoardArticleDTO badto = new BoardArticleDTO();
	badto.setTitle(title);
	badto.setCate(cate);
	badto.setContent(content);
	badto.setUid(uid);
	badto.setRegip(regip);
	
	BoardArticleDAO.getInstance().insertArticle(badto, fname, savePath);
	
	// 글을 작성한 그룹의 카테고리의 리스트로 돌아감
	response.sendRedirect("/Farmstory_1/Board/list.jsp?group="+group+"&cate="+cate);
%>