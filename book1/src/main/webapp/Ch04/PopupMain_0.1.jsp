<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	날짜 : 2022/10/12
	이름 : 심규영
	내용 : 레이어 팝업창 관리(ver 0.1), p145
		  레이어 팝업창 관리(완성), p148
 -->
<%
// 레이어 팝업창 띄울지 여부
String popupMode = "on";

Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie c : cookies) {
		String cookieName = c.getName();
		String cookieValue = c.getValue();
		if(cookieName.equals("PopupClose")) {
			popupMode = cookieValue;
		}
	}
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키를 이용한 팝업 관리 ver 0.1</title>
		<style>
			div#popup {
				position: absolute; top:100px; left:50px; color:yellow;
				width:270px; height:100px; background-color:gray;
			}
			div#popup > div {
				position: relative; background-color:#ffffff; top:0px;
				border:1px solid gray; padding:10px; color:black;
			}
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/
		jquery.min.js"></script>
		<script>
		$(() => {
			$('#closeBtn').click(function(){
				$('#popup').hide();
				var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
				
				// ajax() - 비동기 HTTP 요청을 보내는 jQuery 함수
				$.ajax({
					url : './PopupCookie.jsp',
					type : 'get',
					data : {inactiveToday : chkVal},
					dataType : "text",
					success : (resData) => {
						if (resData != '') location.reload();
					}
				});
			});
		});
		</script>
	</head>
	<body>
		<h2>팝업 메인 페이지(ver 0.1)</h2>
		<%
			for(int i = 1; i <= 10; i++) {
				out.print("현재 팝업창은 " + popupMode + " 상태입니다.<br>");
			}
			
			if (popupMode.equals("on")) {
		%>
			<div id="popup">
				<h2 align="center">공지사항 팝업입니다.</h2>
				<div align="right"><form name="popFrm">
					<input type="checkbox" id="inactiveToday" value="1"/>
					하루 동안 열지 않음
					<input type="button" value="닫기" id="closeBtn">
				</form></div>
			</div>
		<%
			}
		%>
	</body>
</html>