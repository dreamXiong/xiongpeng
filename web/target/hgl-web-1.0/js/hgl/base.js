$(function() {
		var tag = 1,timer=null,state = 0,num=0; 
		$('.cantainer-left li').hover(function(){
			$(this).stop().animate({
				paddingLeft:'40px'
			});
			$(this).find('.ui-drop-down').stop().show();
		
		},function(){
			$(this).stop().animate({
				paddingLeft:'30px'
			});
			$(this).find('.ui-drop-down').stop().hide();
			
		});
		$('.cantainer-right li').click(function(){
			var index = $(this).index();
			$(this).addClass('active').siblings('li').removeClass('active');
			$('.cantainer-right .notice div').eq(index).show().siblings('div').hide();

		});
		
		if(state == 1){
			showBigImg();
			showSmallImg();
			closeDiv();
		};
		
		$('#kitten .colse').click(function(){
			$('#kitten').css({
				display:'none'
			});
		});
		$('.fade').hover(function() {
			$(this).animate({
				opacity:'0.8',
			});
		}, function() {
			$(this).animate({
				opacity:'1',
			});
		});
		
		$('.rot img').hover(function(){
			$(this).animate({
				opacity:'0.8'
			}, 30);
		},function(){
			$(this).animate({
				opacity:'1'
			}, 30);
		});    
		
		$(document).delegate('#relation','mouseover',function(){ 
			$('.addr').stop().slideDown();
		});
		$(document).delegate('#relation','mouseout',function(){
			$('.addr').stop().slideUp();
		});
		
		
	});
   
	function showDiv(){
		$('.myModal').fadeIn(500);
		timer = setTimeout(function(){
			$('.myModal').fadeOut(500);
		},2000);
	}
	function showBigImg(){
		var num = 0;
		setTimeout(function(){
			num=300;
			$('#kitten').animate({
				height:num
			},500);
		},5000);
	}
	function showSmallImg(){
		var num=90;
		setTimeout(function(){
			$('#kitten').animate({
				height:num
			},500,function(){
				$('.big').fadeOut()
			});
			
		},15000);
	}
	function closeDiv(){
		setTimeout(function(){
			$('#kitten').animate({
				height:0
			},500);
		},30000);
		
	}
