// registerSeller
let regCompany = /^[(주)]+[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{1,10}$/; //회사명
let regCeo  = /^[가-힣]{2,4}$/; //대표자명
let regBiz	 = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/; //사업자 등록번호
let regCom = /^[가-힣0-9-]{7,100}$/; //통신판매업신고번호
let regTel = /^(070|02|031|032|033|041|042|043|051|052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/u; //전화
let regFax = /^\d{3,4}-\d{3}-\d{4}$/;//팩스

let isCompanyOk = false;
let isCeoOk = false;
let isBizOk = false;
let isComOk = false;
let isTelOk = false;
let isFaxOk = false;

$(function(){
	
	//회사명
	$('input[name=company]').focusout(function(){
		let company = $('input[name=company]').val();
		
		if(company == ''){
			$('.msgCompany').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!company.match(regCompany)){
			isCompanyOk = false;
			$('.msgCompany').css('color','red').text('(주)포함 입력하여 입력해주세요.');
		}else{
			isCompanyOk = true;
			$('.msgCompany').text('');
		}
		
	});
	
	//대표자
	$('input[name=ceo]').focusout(function(){
		let ceo = $('input[name=ceo]').val();
		
		if(ceo == ''){
			$('.msgCeo').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!ceo.match(regCeo)){
			isCeoOk = false;
			$('.msgCeo').css('color','red').text('대표자 이름을 확인해 주세요.');
		}else{
			isCeoOk = true;
			$('.msgCeo').text('');
		}
	});
	
	//사업자등록번호
	$('input[name=bizRegNum]').focusout(function(){
		let bizRegNum = $('input[name=bizRegNum]').val();
		
		if(bizRegNum == ''){
			$('.msgCorp').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!bizRegNum.match(regBiz)){
			isBizOk = false;
			$('.msgCorp').css('color','red').text('- 표시 포함 12자리 입력, 예) 123-45-67890');
		}else{
			isBizOk = true;
			$('.msgCorp').text('');
		}
	});
	
	//통신판매업신고 번호
	$('input[name=comRegNum]').focusout(function(){
		let comRegNum = $('input[name=comRegNum]').val();
		
		if(comRegNum == ''){
			$('.msgOnline').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!comRegNum.match(regCom)){
			isComOk = false;
			$('.msgOnline').css('color','red').text('- 표시 포함, 예) 강남-12345, 제1-01-23-4567호, 2017-경기성남-0011');
		}else{
			isComOk = true;
			$('.msgOnline').text('');
		}
	});
	
	//전화번호
	$('input[name=tel]').focusout(function(){
		let tel = $('input[name=tel]').val();
		
		if(tel == ''){
			$('.msgTel').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!tel.match(regTel)){
			isTelOk = false;
			$('.msgTel').css('color','red').text('- 표시 포함, 지역번호 포함, 예) 02-234-1234');
		}else{
			isTelOk = true;
			$('.msgTel').text('');
		}
	});
	
	//팩스번호
	$('input[name=fax]').focusout(function(){
		let fax = $('input[name=fax]').val();
		
		if(fax == ''){
			$('.msgFax').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!fax.match(regFax)){
			isFaxOk = false;
			$('.msgFax').css('color','red').text('- 표시 포함, 지역번호 포함, 예) 02-234-1234');
		}else{
			isFaxOk = true;
			$('.msgFax').text('');
		}
	});
	
	
	//담당자 이름
	$('input[name=manager]').focusout(function(){
		let name = $(this).val();
		
		if(name == ''){
			$('.manager').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!name.match(regName)){
			isNameOK = false;
			$('.manager').css('color','red').text('이름은 한글 2자 이상 이어야 합니다.');			
		}else{
			isNameOK = true;
			$('.manager').text('');
		}
	});
	//담당자 이름 끝
	
	// 담당자 휴대폰
	$('input[name=managerHp]').focusout(function(){
		let hp = $(this).val();
		
		if(hp == ''){
			$('.mgHp').css('color', 'red').text('필수 정보입니다.');
			return;
		}
		
		if(!hp.match(regHp)){
			isHpOK = false;
			$('.mgHp').css('color', 'red').text('휴대폰이 유효하지 않습니다.');
		}else{
			isHpOK = true;
			$('.mgHp').text('');
		}
	});
	//담당자 휴대폰 끝
	

});