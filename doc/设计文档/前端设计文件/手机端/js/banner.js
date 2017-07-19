$(function() {
	var liW = $('.banner .swiper-slide:first').width();
	var liH = $('.banner .swiper-slide:first').height();
	var len = $('.banner .swiper-slide').length;
	$('.banner').height(liH )
	var Index = 1;
	var timer = null;
	var num = 0;

	// $('.index li').click(function(event) {
	// 	if(!$('.banner li').is(':animated')){
	// 		var Zindex = $(this).index();
	// 		Index++;
	// 		$(this).addClass('active').siblings('li').removeClass('active');
	// 		if(Zindex>num){
	// 			$('.banner li').eq(Zindex).css({left:liW,zIndex:Index}).animate({left:0}, 500);
	// 			$('.banner li').eq(num).animate({left:-liW}, 500);
	// 		}else if(Zindex<num){
	// 			$('.banner li').eq(Zindex).css({left:-liW,zIndex:Index}).animate({left:0}, 500);
	// 			$('.banner li').eq(num).animate({left:liW}, 500);
	// 		}
	// 		num = Zindex;
	// 	}
	// });

	
	function autoplay(){
		if(!$('.banner .swiper-slide').is(':animated')){
			num++;
			Index++;
			if(num>len-1){
				num = 0;
			}
			// $('.index li').eq(num).addClass('active').siblings('li').removeClass('active');
			$('.banner .swiper-slide').eq(num).css({left:liW,zIndex:Index}).animate({left:0}, 500);
			$('.banner .swiper-slide').eq(num-1).animate({left:-liW}, 500);
		}
	}

	function pevrplay(){
		if(!$('.banner .swiper-slide').is(':animated')){
			num--;
			Index++;
			if(num<0){
				num = len-1;
			}
			// $('.index li').eq(num).addClass('active').siblings('li').removeClass('active');
			$('.banner .swiper-slide').eq(num).css({left:-liW,zIndex:Index}).animate({left:0}, 500);
			if(num+1<=len-1){
				$('.banner .swiper-slide').eq(num+1).animate({left:liW}, 500);
			}else{
				$('.banner .swiper-slide').eq(0).animate({left:liW}, 500);
			}
		}
		
	}

	timer = setInterval(autoplay,3000);				//定时器

	$('.banner').hover(function() {
		clearInterval(timer);
	}, function() {
		clearInterval(timer);
		timer = setInterval(autoplay,3000);			//定时器
	});
	// $('.banner-right').click(function(event) {
	// 	
	// });
	// $('.banner-left').click(function(event) {
	// 	
	// });
	$(".banner").on("swipeleft",function(){
  		autoplay();
	});
	$(".banner").on("swiperight",function(){
  		pevrplay();
	});
});