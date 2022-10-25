/**
 * 
 */
 function order (productList) {
	const prodNo = productList.eq(0).text();
	
	$('section').empty();
	$('nav').empty().append("<h4>주문하기</h4>");
	
	let table = 	"<table border='1'>"
								+"<tr>"
									+"<td>상품번호</td>"
									+"<td><input type='text' name='orderProduct' "
									+"readonly='readonly' value='"+prodNo+"'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>수량</td>"
									+"<td><input type='text' name='orderCount'></td>"
								+"</tr>"
								+"<tr>"
									+"<td>주문자</td>"
									+"<td><input type='text' name='orderId'></td>"
								+"</tr>"
								+"<tr>"
									+"<td colspan='2' align='right'>"
									+"<input type='submit' id='btnProductOrder' value='주문하기'></td>"
								+"</tr>"
							+"</table>";
	$('section').append(table);
}