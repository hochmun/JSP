<%@page import="ko.co.college.bean.registerBean"%>
<%@page import="java.util.List"%>
<%@page import="ko.co.college.DAO.registerDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	registerDAO rdao = new registerDAO();
	List<registerBean> rbs = rdao.readRegisterList();
	rdao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
				// 강좌 등록 열기 및 강좌 검색
				$('.btnOpen').click(function(){
					$('section').show();
					
					$.ajax({
						url: './proc/lectureList.jsp',
						type: 'POST',
						dataType: 'json',
						success: function(data){
							for(const lbs of data) {
								const option = 
								"<option value="+lbs.lecNo+">"
								+ lbs.lecName + "</option>";
								$('select[name=lecName]').append(option);
							}
						}
					});
				});
				// 강좌 닫기
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				// 학생 수강 과목 검색
				$('.btnSearchRegister').click(function(){
					const searchRegister = $('.searchRegister').val();
					// 숫자 체크용
					const chkStyle = /\d/;
					
					// 입력 값이 숫자인지 체크
					if (!searchRegister.match(chkStyle)) {
						alert("올바르지 않은 형식");
						return false;
					}
					
					const jsonData = {
							"searchRegister"	:searchRegister
					}
					
					$.ajax({
						url: './proc/searchRegister.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							$('section').hide();
							if(true) {
								$('.registerList > tbody').empty();
								for(const rbs of data){
			 						const tags = "<tr>"
			 						+ "<td>"+rbs.regStdNo+"</td>"
			 						+ "<td>"+rbs.stdName+"</td>"
			 						+ "<td>"+rbs.lecName+"</td>"
			 						+ "<td>"+rbs.regLecNo+"</td>"
			 						+ "<td>"+rbs.regMidScore+"</td>"
			 						+ "<td>"+rbs.regFinalScore+"</td>"
			 						+ "<td>"+rbs.regTotalScore+"</td>"
			 						+ "<td>"+rbs.regGrade+"</td>"
			 						+ "</tr>";
			 						$('.registerList').append(tags);
			 					}
							} else {
								alert("일치하는 학번이 없습니다.");
							}
						}
					});
				});
				
				// 수강하기
				$('input[type=submit]').click(function(){
					const regStdNo 	= $('input[name=regStdNo]').val();
					const stdName 	= $('input[name=stdName]').val();
					const lecNo 	= $('#lecName option:selected').val();
					const lecName 	= $('#lecName option:selected').text();
					
					console.log(lecNo);
					console.log(lecName);
					
					const jsonData = {
							"regStdNo"	:regStdNo,
							"stdName"	:stdName,
							"lecNo"		:lecNo,
							"lecName"	:lecName
					}
					
					$.ajax({
						url: './proc/registerInsert.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							if(data.result > 0) {
								alert('수강등록성공!');
								let tr = "<tr>"
									   + "<td>"+data.regStdNo+"</td>"
									   + "<td>"+data.stdName+"</td>"
									   + "<td>"+data.lecName+"</td>"
									   + "<td>"+data.lecNo+"</td>"
									   + "<td></td>"
									   + "<td></td>"
									   + "<td></td>"
									   + "<td></td>"
									   + "</tr>";
								
								$('section').hide();
								$('.registerList').append(tr);
							} else {
								alert("수강등록실패!");
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>수강현황</h4>
		<input type="text" class="searchRegister" placeholder="학번 검색">
		<input type="button" class="btnSearchRegister" value="검색">
		<input type="button" class="btnOpen" value="수강신청">
		<table border="1" class="registerList">
			<thead>
				<tr>
					<th>학번</th><th>이름</th><th>강좌명</th><th>강좌코드</th><th>중간시험</th>
					<th>기말시험</th><th>총점</th><th>등급</th>
				</tr>
			</thead>
			<% for (registerBean rb : rbs) { %>
			<tr>
				<td><%= rb.getRegStdNo() %></td>
				<td><%= rb.getStdName()%></td>
				<td><%= rb.getLecName()%></td>
				<td><%= rb.getRegLecNo()%></td>
				<td><%= rb.getRegMidScore()%></td>
				<td><%= rb.getRegFinalScore()%></td>
				<td><%= rb.getRegTotalScore()%></td>
				<td><%= rb.getRegGrade()%></td>
			</tr>
			<% } %>
		</table>
		
		<section style="display:none;">
			<h4>수강신청</h4>
			<input type="button" class="btnClose" value="닫기">
			<table border="1">
				<tr>
					<td>학번</td>
					<td><input type="text" name="regStdNo"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="stdName"></td>
				</tr>
				<tr>
					<td>신청강좌</td>
					<td><select name="lecName" id="lecName">
						<option value="none">강좌선택</option>
					</select></td>
				</tr>
				<tr>
					<td colspan='2' align='right'>
					<input type='submit' id='btnRegisterAdd' value='신청'></td>
				</tr>
			</table>
		</section>
	</body>
</html>