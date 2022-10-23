/**
	날짜 : 2022/10/23
	이름 : 심규영
	내용 : 수정 화면 구현
 */
 
 function modify(user){
	$(()=>{
		const uid = user.eq(0).text();
		const name = user.eq(1).text();
		const hp = user.eq(2).text();
		const age = user.eq(3).text();
		
		$('section').empty();
		$('nav').empty().append("<h4>user2 수정</h4><a href='#' id='userList'>user2 목록</a>");
		let table = 	"<table border='1'>"
								+"<tr>"
									+"<td>아이디</td>"
									+"<td><input type='text' name='uid' readonly='readonly'' value='"+uid+"'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>이름</td>"
									+"<td><input type='text' name='name' value='"+name+"'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>휴대폰</td>"
									+"<td><input type='text' name='hp' value='"+hp+"'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>나이</td>"
									+"<td><input type='text' name='age' value='"+age+"'></td>"
								+"</tr>"
								+"<tr>"
									+"<td colspan='2' align='right'><input type='submit' id='btnModify' value='수정'></td>"
								+"</tr>"
							+"</table>";
		$('section').append(table);
	})
}