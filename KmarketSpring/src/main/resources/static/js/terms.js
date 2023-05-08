$(function () {
	$('.agree').click(function() {
		
		let isCheck1 = $('input[name=agree1]').is(':checked');
		let isCheck2 = $('input[name=agree2]').is(':checked');
		let isCheck3 = $('input[name=agree3]').is(':checked');
		
		if(isCheck1 && isCheck2 && isCheck3){
			return true;
		}else{
			alert('동의 체크를 하셔야 합니다.');
			return false;
		}
	});
});