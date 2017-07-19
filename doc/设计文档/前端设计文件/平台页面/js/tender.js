
	$(function() {
		var timer= null;
		$('.name').hover(function(){
			$(this).find('.name-list').stop().slideDown(100);
			clearTimeout(timer)
		},function(){
			timer = setTimeout(nameHide,500);
		})
		$('.seek-class a').click(function(event) {
			$(this).addClass('active').siblings('a').removeClass('active')
		});
		$('.seek-nav .current').click(function(event) {
			$(this).addClass('active').siblings('.current').removeClass('active')
		});
		
		$('.seek-icon .cursor').click(function(){
			var index = $(this).index();
			$('.show .style-list').eq(index).show().siblings('.style-list').hide()
		})
		function nameHide(){
			$('.name-list').stop().slideUp(300)
		}
	});