$(()=>{
	// 글 삭제
	$('.btnRemove').click(()=>{
		const isDelete = confirm('정말 삭제 하시겠습니까?');
		if(isDelete) return true;
		else return false;
	});
	
	// 댓글 삭제
	$('.remove').click(function(e){
		e.preventDefault();
		const isDeleteOk = confirm('정말 삭제 하시겠습니까?');
		
		if(isDeleteOk) {
			const article = $(this).closest('article');
			const no = $(this).attr('data-no');
			const parent = $(this).attr('data-parent');
			const type = 3;
			
			$.ajax({
				url: '/Farmstory3/board/view.do',
				type: 'post',
				data: {"no":no,"parent":parent,"type":type},
				dataType: 'json',
				success: (data)=>{
					alert('댓글이 삭제 되었습니다.');
					article.hide();
				}
			});
		}
	});
	
	// 댓글 수정
	$(document).on('click','.modify', function(e){
		e.preventDefault();
		
		const txt = $(this).text();
		const p_tag = $(this).parent().prev();
		
		if(txt == '수정') {
			// 수정 모드
			$(this).text('수정완료');
			p_tag.attr('contentEditable', true).focus();
		} else {
			// 수정 완료
			$(this).text('수정');
			
			const no = $(this).attr('data-no');
			const content = p_tag.text();
			const type = 2;
			
			$.ajax({
				url: '/Farmstory3/board/view.do',
				type: 'post',
				data: {"no":no,"content":content,"type":type},
				dataType: 'json',
				success: (data)=>{
					if(data.result == 1) {
						alert('댓글이 수정되었습니다.');
						p_tag.attr('contentEditable', false);
					}
				}
			});
		}
	});
	
	// 댓글 작성
	$('.commentForm > form').submit(function(e){
		e.preventDefault();
		
		const uid 		= $(this).children('input[name=uid]').val();
		const content 	= $(this).children('textarea[name=content]').val();
		const no		= $(this).children('input[name=no]').val();
		const textarea	= $(this).children('textarea[name=content]');
		const type 		= 1;
		
		if (uid == null || uid == '') {
			alert('로그인 후 댓글을 작성하실수 있습니다.');
			return false;
		}
		
		if (content == '') {
			alert('댓글을 작성하세요.');
			return false;
		}
		
		$.ajax({
			url: "/Farmstory3/board/view.do",
			method: "post",
			data: {"no":no,"content":content,"type":type},
			dataType: "json",
			success: (data)=>{
				if(data.result > 0) {
					const article = "<article>"
						+ "<span class='nick'>"+data.nick+"</span>&nbsp;"
						+ "<span class='date'>"+data.date+"</span>"
						+ "<p class='content'>"+data.content+"</p>"
						+ "<div>"
						+ "<a href='#' class='remove' data-no="+data.no+" data-parent="+data.parent+">삭제</a>&nbsp;"
						+ "<a href='#' class='modify' data-no="+data.no+">수정</a>"
						+ "</div>"
						+ "</article>";
						
					$('.commentList .empty').hide();
					$('.commentList').append(article);
					textarea.val('');
				}
			}
		});
		return false;
	});
});