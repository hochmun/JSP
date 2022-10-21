/**
 * 
 */
 
 function register (){
	 $(()=>{
		$('section').empty();
			$('nav').empty().append("<h4>user2 등록</h4><a href='#' id='userList'>user2 목록</a>")
			let table = 	"<table border='1'>"
								+"<tr>"
									+"<td>아이디</td>"
									+"<td><input type='text' name='uid'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>이름</td>"
									+"<td><input type='text' name='name'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>휴대폰</td>"
									+"<td><input type='text' name='hp'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>나이</td>"
									+"<td><input type='text' name='age'></td>"
								+"</tr>"
								+"<tr>"
									+"<td colspan='2' align='right'><input type='submit' id='btnRegister' value='등록'></td>"
								+"</tr>"
							+"</table>";
			$('section').append(table);
	});
}