/*
	날짜 : 2022/11/23
	이름 : 심규영
	내용 : 아이디 비밀번호 찾기
 */

const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

let emailChecking = false; // 이메일 체크 확인중
let isEmailCheckSend = false; // 이메일 인증코드 전송 체크
let emailCode = 0; // 이메일 인증 코드

$(()=>{
	// 이메일 입력 변경시 초기화
	$('input[name=email]').change(function(){
		const email = $(this).val();
		
		isEmailCheckSend = false; // 이메일 수정시 전송된 코드의 유효성 해제
		$('input[name=auth]').attr('disabled', true); // 인증 비활성화
		
		if (email.match(reEmail)) {
			isEmailOk = true;
			$('#message').text('');
		} else {
			isEmailOk = false;
			$('#message').css('color','red').text('유효한 메일이 아닙니다.');
		}
	});
	
	$('.btnAuth').click(()=>{
		
		if (emailChecking == true) {
			alert('이메일 확인중 입니다.');
			return;
		} else {
			emailChecking = true;
			$('input[name=email]').attr('readonly', true);
			$('#message').css('color','black').text('이메일 전송 중 입니다.');
		}
		
		const name = $('input[name=name]').val();
		const email = $('input[name=email]').val();
		const uid = $('input[name=uid]').val();
		
		$.ajax({
			url: '/Jboard2/user/emailAuth.do',
			method: 'get',
			data: {"name":name,"email":email,"uid":uid},
			dataType: 'json',
			success: (data)=>{
				if(data.status == 1) {
					// 메일 발송 성공
					emailCode = data.code;
					isEmailCheckSend = true;
					emailChecking = false;
					$('input[name=email]').attr('readonly', false);
					$('input[name=auth]').attr('disabled', false); // 인증 활성화
					$('#message').css('color','green').text('인증코드를 전송 하였습니다.');
				} else {
					// 메일 발송 실패
					emailChecking = false;
					$('input[name=email]').attr('readonly', false);
					$('#message').css('color','red').text('이메일 전송을 실패 했습니다. 이름과 이메일을 확인 후 다시 시도 하시기 바랍니다.');
				}
			}
		});
	});
	
	// 이메일 인증코드 확인
	$('.find > form').submit(function(){
	 	const code = $('input[name=auth]').val();
	 	
	 	// 입력한 코드가 이메일 코드와 맞고 이메일 코드가 전송된 상태일때
	 	if (code == emailCode && isEmailCheckSend == true) {
	 		alert('이메일이 인증 되었습니다.');
	 		return true;
	 	} else {
	 		alert('이메일 인증에 실패하였습니다.');
	 		return false;
	 	}
	});
});