// 정규표현식
let regUid 		= /^[a-z]+[a-z0-9]{4,12}$/g;
let regName 	= /^[가-힣]{2,4}$/;
let regEmail 	= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
let regHp 		= /^\d{3}-\d{3,4}-\d{4}$/;
let regPass 	= /^.*(?=^.{5,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

// 폼 데이터 검증 결과 상태변수
let isUidOK 	= false;
let isPassOK 	= false;
let isPassMatch = false;
let isNameOK 	= false;
let isEmailOK 	= false;
let isHpOK 		= false;
let isGenderOk 	= false;

$(function(){
	
	//ID 유효성 & 중복체크
	$('input[name=uid]').keydown(function(){
		isUidOK = false;
		$('.msgId').text('');
	});
	
	$('#uid').focusout(function(){

		let uid = $('input[name=uid]').val();
		
		if(uid == ''){
			$('.msgId').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		// ID 유효성
		if(isUidOK) return;
		if(!uid.match(regUid)){
			isUidOK = false;
			$('.msgId').css('color', 'red').text('영문, 숫자로 4~12자까지 설정해 주세요.')
			return;
		}
		
		// 중복
		setTimeout(()=>{
			$.ajax({
				url: '/Kmarket/member/checkUid',
				method: 'get',
				data: {"uid": uid},
				dataType: 'json',
				success:function(data){
					if(data.result > 0){
						$('.msgId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
					}else{
						$('.msgId').css('color', 'green').text('사용가능한 아이디 입니다.');
					}
				}
			});
		}, 500);
	});
	//ID 끝
	
	//비밀번호 일치 & 유효성
	$('input[name=pass1]').focusout(function(){
		let pass1= $(this).val();
		isPassMatch = false;
		
		if(pass1 == ''){
			$('.msgPass1').css('color','red').text('필수 정보입니다.');
			return;
		}
		
		//유효성검사
		if(!pass1.match(regPass)){
			//실패
			$('.msgPass1').css('color','red').text('영문,숫자,특수문자를 조합하여 8~12자리까지 설정해주세요.');
			isPassOK = false;
			return;
		}
		
		$('.msgPass1').css('color','green').text('사용가능한 비밀번호입니다.');
		isPassOK = true;
		return;
	});

	//비밀번호 일치여부
	$('input[name=pass2]').focusout(function(){
		let pass1= $('input[name=pass1]').val();
		let pass2= $(this).val();
		
		if(pass2 == ''){
			$('.msgPass2').css('color','red').text('필수 정보입니다.');
			return;
		}
		
		if(pass1 != pass2){
			//실패
			$('.msgPass2').css('color','red').text('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
			isPassMatch = false;
			return;
		}
		//성공
		$('.msgPass2').css('color','green').text('비밀번호가 일치합니다.');
		isPassMatch = true;
		return;
	});
	//비밀번호 끝
	
	//이름
	$('input[name=name]').focusout(function(){
		let name = $(this).val();
		if(!name.match(regName)){
			isNameOK = false;
			$('.msgName').css('color','red').text('이름은 한글 2자 이상 이어야 합니다.');			
		}else{
			isNameOK = true;
			$('.msgName').text('');
		}
	});
	//이름 끝
	
	//성별
	$('input[name=gender]').change(function(){
		$('.msgGender').text('');
		isGenderOk = true;
		return;
	});
	//성별 끝
	
	//email 유효성 검사
	$('input[name=email]').focusout(function(){
		let email = $(this).val();
		
		if(!email.match(regEmail)){
			isEmailOk = false;
			$('.msgEmail').css('color', 'red').text('이메일이 유효하지 않습니다.');
		}else{
			isEmailOk = true;
			$('.msgEmail').text('');
		}			
	});
	//email 끝
	
	// 휴대폰 유효성 검사
	$('input[name=hp]').focusout(function(){
		let hp = $(this).val();
		
		if(!hp.match(regHp)){
			isHpOK = false;
			$('.msgHp').css('color', 'red').text('휴대폰이 유효하지 않습니다.');
		}else{
			isHpOK = true;
			$('.msgHp').text('');
		}
	});
	//휴대폰 끝
	
	// 폼 전송이 시작될 때 실행되는 폼 이벤트(폼 전송 버튼을 클릭했을 때) 
	$('.register > form').submit(function(){
		// 성별 검증
		if(!isGenderOk){
			let gender = $('input[name=gender]').is(':checked');
			if(gender == 0){
				alert('성별를 확인해주세요.');
				$('.msgGender').css('color', 'red').text('필수 정보입니다.');
				return false;
			}
		}
		// 최종 전송
		return true;
	});

});