<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/25
	이름 : 심규영
	내용 : 삭제 처리 페이지, p303
 -->
<%@ include file="./IsLoggedIn.jsp" %>
<%
	String num = request.getParameter("num");
	BoardDTO dto = new BoardDTO();
	BoardDAO dao = new BoardDAO(application);
	dto = dao.selectView(num);
	
	// 로그인된 사용자 ID 얻기
	String sessionId = session.getAttribute("UserId").toString();
	
	int delResult = 0;
	
	// 작성자가 본인인지 확인
	if (sessionId.equals(dto.getId())) {
		// 작성작 본인이면...
		dto.setNum(num);
		delResult = dao.deletePost(dto);
		dao.close();
		
		// 성공 실패 처리
		if (delResult == 1) {
			// 성공시 목록 페이지로 이동
			JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
		} else {
			// 실패 시 이전 페이지로 이동
			JSFunction.alertBack("삭제에 실패하였습니다.", out);
		}
	} else {
		// 작성자 본인이 아니라면 이전 페이지로 이동
		JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
		
		return;
	}
%>