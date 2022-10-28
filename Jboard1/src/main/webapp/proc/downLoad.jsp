<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="kr.co.Jboard1.bean.BoardFileBean"%>
<%@page import="kr.co.Jboard1.DAO.BoardFileDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/28
	이름 : 심규영
	내용 : 파일 다운로드
 -->
<%
	// 파일 정보 가져오기
	String no = request.getParameter("no"); // 다운 받는 파일의 게시물 번호 받기
	BoardFileDAO bfdao = BoardFileDAO.getInstance(); // 데이터베이스 연결
	BoardFileBean bfb = bfdao.ReadFileData(no); // 해당 게시물의 파일 정보 받기
	bfdao.UpdateDownloadCount(bfb.getFno()); // 다운로드 횟수 증가
	bfdao.close(); // 클래스 종료

	// 파일 다운로드를 위한 response 헤더 수정
	// 파일 띄어쓰기 + 빼기
	String fileName = bfb.getOriName();
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replaceAll("\\+", "%20");
	response.setContentType("application/octet-stream"); // 다운로드
	response.setHeader("Content-Disposition", "attachment; filename="+fileName+";");
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	// response 객체로 파일 스트림 작업
	String savePath = application.getRealPath("/file");
	File file = new File(savePath+"/"+bfb.getNewName());
	
	out.clear();
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	while(true) {
		int data = bis.read();
		
		if (data == -1) {
			break;
		}

		bos.write(data);
	}
	
	bos.close();
	bis.close();
%>