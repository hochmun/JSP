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
				$('.btnOpen').click(function(){
					$('section').show();
					
					$.ajax({
						url: './proc/lectureList.jsp',
						type: 'POST',
						dataType: 'json',
						success: function(data){
							for(const lbs of data) {
								const option = 
								"<option value="+lbs.lecName+">"
								+ lbs.lecName + "</option>";
								$('select[name=lecName]').append(option);
							}
						}
					});
				});
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				$('.btnSearchRegister').click(function(){
					const searchRegister = $('.searchRegister').val();
					
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
				
				$
			});
		</script>
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>수강현황</h4>
		<input type="text" class="searchRegister">
		<input type="button" class="btnSearchRegister" value="검색">
		<input type="button" class="btnOpen" value="수강신청">
		<table border="1" class="registerList">
			<thead>
				<th>학번</th><th>이름</th><th>강좌명</th><th>강좌코드</th><th>중간시험</th>
				<th>기말시험</th><th>총점</th><th>등급</th>
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
					<td><select name="lecName">
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