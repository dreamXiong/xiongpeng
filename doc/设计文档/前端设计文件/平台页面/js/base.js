$(function() {
		var tag = 1,timer=null; 
		$('.cantainer-left').find('li').mouseover(function(event) {
			$(this).stop().animate({
				paddingLeft:'40px'
			});
			$(this).find('.ui-drop-down').stop().show();

		}).mouseout(function(event) {
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
		
		$('#kitten span').click(function(){
			if(tag==1){
				$(this).parents('#kitten').animate({
					height: '90px',
				},function(){
					$(this).parent('.big').animate({
						opacity:0
					},100);
					$(this).find('.small').css({
						zIndex:1,
					});
				});
				tag = 2;
			}else{
				$(this).parents('#kitten').animate({
					height:'300px'
				});
				$(this).parent('.small').css({
					zIndex:-1,
				});
				$(this).find('.big').animate({
						opacity:1
				},100);
				tag = 1;
			};
		});

		timer = setTimeout(function(){
			$('#dropdown').animate({
				display:'block',
			})
		},30)
		
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
		$('.shopping').hover(function(){
			$(this).css({
				backgroundColor:'#fff'
			});
			$('.shopping-in').fadeIn(30);
		},function(){
			$(this).css({
				backgroundColor:'#F9F9F9'
			});
			$(this).find('.shopping-in').fadeOut(100);
		});
		
	});

	function showDiv(){
		$('.myModal').fadeIn(500);
		timer = setTimeout(function(){
			$('.myModal').fadeOut(500)
		},2000)
	}
