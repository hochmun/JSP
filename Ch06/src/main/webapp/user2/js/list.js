/**
 * 
 */
 
 function list (){
	 $(()=>{
		$('section').empty();
		$('nav').empty().append("<h4>user2 목록</h4><a href='#' id='userAdd'>user2 등록</a>");
				
		$.get('./data/list.jsp', (data)=>{
			const table = "<table border='1'>"
							+"<tr>"
								+"<th>아이디</th>"
								+"<th>이름</th>"
								+"<th>휴대폰</th>"
								+"<th>나이</th>"
								+"<th>관리</th>"
							+"</tr>"
						 +"</table>";
			$('section').append(table);
			for(const user of data){
				const tr = "<tr>"
								+"<td>"+user.uid+"</td>"
								+"<td>"+user.name+"</td>"
								+"<td>"+user.hp+"</td>"
								+"<td>"+user.age+"</td>"
								+"<td>"
									+"<a href='#' class='userModify'>수정</a>"
									+"<a href='#' class='userDelete'>삭제</a>"
								+"</td>"
							+"</tr>";
				$('table').append(tr);
			}
		});
	});
}