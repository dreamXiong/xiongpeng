$(function() {
		var timer= null;
		var tag = 1;
		$('.name').hover(function(){
			$(this).find('.name-list').stop().slideDown(100);
			clearTimeout(timer)
		},function(){
			timer = setTimeout(nameHide,300);
		})
		$('.seek-class a').click(function(event) {
			$(this).addClass('active').siblings('a').removeClass('active')
		});
		$('.seek-nav .current').click(function(event) {
			$(this).addClass('active').siblings('.current').removeClass('active')
		});
		
		$('.prev').click(function(){
			var prevText = $('#prev').text();
			var nextText = $('.next').text();
			prevText--;
			
			if(prevText>0 && prevText<5){
				$('#prev').text(prevText);
			}
		})
		$('.next').click(function(){
			var prevText = $('#prev').text();
			var nextText = $('.next').text();
			prevText++;
			
			if(prevText>0 && prevText<5){
				$('#prev').text(prevText);
			}
		})
		$('.seek-icon .cursor').click(function(){
			var index = $(this).index();
			$('.show .style-list').eq(index).show().siblings('.style-list').hide()
		})
		$('.collect .iconfont').click(function(){
			if (tag == 1 ) {
				$(this).html('&#xe609;').attr('title','已收藏');
				tag =2;
			}else{
				$(this).html('&#xe608;').attr('title','收藏');
				tag =1;
			}
			
		})

		// 平台页面，点击图片选中checkbox
		$('.seek-label').click(function(event) {
			var check = $(this).find(':checkbox').prop('checked');
			$(this).find(':checkbox').prop('checked',!check)
		});
		function nameHide(){
			$('.name-list').stop().slideUp(300)
		}
		
	
	});

		