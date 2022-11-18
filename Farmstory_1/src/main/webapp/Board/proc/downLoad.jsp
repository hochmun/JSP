<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="kr.co.Farmstory_1.DTO.BoardFileDTO"%>
<%@page import="kr.co.Farmstory_1.DAO.BoardFileDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	BoardFileDTO bfdto = BoardFileDAO.getInstance().selectFile(no);
	
	String fileName = bfdto.getOriName();
	fileName = URLEncoder.encode(fileName, "UTF-8");
	fileName = fileName.replaceAll("\\+", "%20");
	response.setContentType("application/octet-stream"); // 다운로드
	response.setHeader("Content-Disposition", "attachment; filename="+fileName+";");
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	String savePath = application.getRealPath("/file");
	File file = new File(savePath+"/"+bfdto.getNewName());
	
	out.clear();
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	while(true) {
		int data = bis.read();
		
		if (data == -1) break;
		
		bos.write(data);
	}
	
	bos.close();
	bis.close();
%>