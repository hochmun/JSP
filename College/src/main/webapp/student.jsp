<%@page import="ko.co.college.bean.studentBean"%>
<%@page import="java.util.List"%>
<%@page import="ko.co.college.DAO.studentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	studentDAO sdao = new studentDAO();
	List<studentBean> sbs = sdao.studentList();
	sdao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
				$('.btnOpen').click(function(){
					$('section').show();
				});
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				$('input[type=submit]').click(function(){
					const studentId 	= $('input[name=studentId]').val();
					const studentName 	= $('input[name=studentName]').val();
					const studentHp 	= $('input[name=studentHp]').val();
					const studentYear 	= $('#studentYear option:selected').val();
					const studentAddr 	= $('input[name=studentAddr]').val();
					
					const jsonData = {
							"studentId"		:studentId,
							"studentName"	:studentName,
							"studentHp"		:studentHp,
							"studentYear"	:studentYear,
							"studentAddr"	:studentAddr
					}
					
					$.ajax({
						url: './proc/studentInsert.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							if(data.result > 0) {
								alert('학생등록성공!');
								let tr = "<tr>"
									   + "<td>"+data.studentId+"</td>"
									   + "<td>"+data.studentName+"</td>"
									   + "<td>"+data.studentHp+"</td>"
									   + "<td>"+data.studentYear+"</td>"
									   + "<td>"+data.studentAddr+"</td>"
									   + "</tr>";
								
								$('section').hide();
								$('.studentList').append(tr);
							} else {
								alert("학생등록실패!");
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h3>학생관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>학생목록</h4>
		<input type="button" class="btnOpen" value="등록">
		<table border="1" class="studentList">
			<tr>
				<th>학번</th><th>이름</th><th>휴대폰</th><th>학년</th><th>주소</th>
			</tr>
			<% for (studentBean sb : sbs) { %>
			<tr>
				<td><%= sb.getStdno() %></td>
				<td><%= sb.getStdName()%></td>
				<td><%= sb.getStdHp()%></td>
				<td><%= sb.getStdYear()%></td>
				<td><%= sb.getStdAddress()%></td>
			</tr>
			<% } %>
		</table>
		
		<section style="display:none;">
			<h4>학생등록</h4>
			<input type="button" class="btnClose" value="닫기">
			<table border="1">
				<tr>
					<td>학번</td>
					<td><input type="text" name="studentId"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="studentName"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="studentHp"></td>
				</tr>
				<tr>
					<td>학년</td>
					<td><select id="studentYear">
						<option value="1">1학년</option>
						<option value="2">2학년</option>
						<option value="3">3학년</option>
					</select></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="studentAddr"></td>
				</tr>
				<tr>
					<td colspan='2' align='right'>
					<input type='submit' id='btnStudentAdd' value='등록'></td>
				</tr>
			</table>
		</section>
	</body>
</html>