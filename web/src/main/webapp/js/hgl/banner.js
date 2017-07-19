$(function() {
	var liW = $('.banner li:first').width();
	var len = $('.banner li').length;
	var Index = 1;
	var timer = null;
	var num = 0;

	$('.index li').click(function(event) {
		if(!$('.banner li').is(':animated')){
			var Zindex = $(this).index();
			Index++;
			$(this).addClass('active').siblings('li').removeClass('active');
			if(Zindex>num){
				$('.banner li').eq(Zindex).css({left:liW,zIndex:Index}).animate({left:0}, 500);
				$('.banner li').eq(num).animate({left:-liW}, 500);
			}else if(Zindex<num){
				$('.banner li').eq(Zindex).css({left:-liW,zIndex:Index}).animate({left:0}, 500);
				$('.banner li').eq(num).animate({left:liW}, 500);
			}
			num = Zindex;
		}
	});

	
	function autoplay(){
		if(!$('.banner li').is(':animated')){
			num++;
			Index++;
			if(num>len-1){
				num = 0;
			}
			$('.index li').eq(num).addClass('active').siblings('li').removeClass('active');
			$('.banner li').eq(num).css({left:liW,zIndex:Index}).animate({left:0}, 500);
			$('.banner li').eq(num-1).animate({left:-liW}, 500);
		}
	}

	function pevrplay(){
		if(!$('.banner li').is(':animated')){
			num--;
			Index++;
			if(num<0){
				num = len-1;
			}
			$('.index li').eq(num).addClass('active').siblings('li').removeClass('active');
			$('.banner li').eq(num).css({left:-liW,zIndex:Index}).animate({left:0}, 500);
			if(num+1<=len-1){
				$('.banner li').eq(num+1).animate({left:liW}, 500);
			}else{
				$('.banner li').eq(0).animate({left:liW}, 500);
			}
		}
		
	}

	timer = setInterval(autoplay,3000);				//定时器

	$('.cantainer-center').hover(function() {
		clearInterval(timer);
	}, function() {
		clearInterval(timer);
		timer = setInterval(autoplay,3000);			//定时器
	});
	$('.banner-right').click(function(event) {
		autoplay();
	});
	$('.banner-left').click(function(event) {
		pevrplay();
	});
});