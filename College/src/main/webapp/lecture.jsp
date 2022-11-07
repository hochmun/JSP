<%@page import="ko.co.college.bean.lectureBean"%>
<%@page import="java.util.List"%>
<%@page import="ko.co.college.DAO.lectureDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	lectureDAO ldao = new lectureDAO();
	List<lectureBean> lbs = ldao.readLectureList();
	ldao.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
				$('.btnLectureOpen').click(function(){
					$('section').show();
				});
				$('.btnLectureClose').click(function(){
					$('section').hide();
				});
				
				$('input[type=submit]').click(function(){
					const lecNo 	= $('input[name=lecNo]').val();
					const lecName 	= $('input[name=lecName]').val();
					const lecCredit = $('input[name=lecCredit]').val();
					const lecTime 	= $('input[name=lecTime]').val();
					const lecClass 	= $('input[name=lecClass]').val();
					
					const jsonData = {
							"lecNo"		:lecNo,
							"lecName"	:lecName,
							"lecCredit"	:lecCredit,
							"lecTime"	:lecTime,
							"lecClass"	:lecClass
					}
					
					$.ajax({
						url: './proc/lectureInsert.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							if(data.result > 0) {
								alert('강좌등록성공!');
								let tr = "<tr>"
									   + "<td>"+data.lecNo+"</td>"
									   + "<td>"+data.lecName+"</td>"
									   + "<td>"+data.lecCredit+"</td>"
									   + "<td>"+data.lecTime+"</td>"
									   + "<td>"+data.lecClass+"</td>"
									   + "</tr>";
								
								$('section').hide();
								$('.lectureList').append(tr);
							} else {
								alert("강좌등록실패!");
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h3>강좌관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>강좌현황</h4>
		<input type="button" class="btnLectureOpen" value="등록">
		<table border="1" class="lectureList">
			<tr>
				<th>번호</th><th>강좌명</th><th>학점</th><th>시간</th><th>강의장</th>
			</tr>
			<% for(lectureBean lb : lbs) { %>
			<tr>
				<td><%= lb.getLecNo() %></td>
				<td><%= lb.getLecName() %></td>
				<td><%= lb.getLecCredit() %></td>
				<td><%= lb.getLecTime() %></td>
				<td><%= lb.getLecClass() %></td>
			</tr>
			<% } %>
		</table>
		
		<section style="display:none;">
			<h4>강좌등록</h4>
			<input type="button" class="btnLectureClose" value="닫기">
			<table border="1">
				<tr>
					<td>번호</td>
					<td><input type="text" name="lecNo"></td>
				</tr>
				<tr>
					<td>강좌명</td>
					<td><input type="text" name="lecName"></td>
				</tr>
				<tr>
					<td>학점</td>
					<td><input type="number" name="lecCredit"></td>
				</tr>
				<tr>
					<td>시간</td>
					<td><input type="number" name="lecTime"></td>
				</tr>
				<tr>
					<td>강의장</td>
					<td><input type="text" name="lecClass"></td>
				</tr>
				<tr>
					<td colspan='2' align='right'>
					<input type='submit' id='btnLectureAdd' value='추가'></td>
				</tr>
			</table>
		</section>
	</body>
</html>