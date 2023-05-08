$(function(){
    $(window).scroll(function(){
        let top = $(this).scrollTop();
        if(top > 10){
            $("header.navi").css('background','white');
            $("a.navi").css('color','#222');
            $("img.navi").attr('src','/Kmarket/img/company/company_header_logo1.png');
        }else{
            $("header.navi").css('background','transparent');
            $("a.navi").css('color','white');
            $("img.navi").attr('src','/Kmarket/img/company/company_header_logo2.png');
        }
    });
});