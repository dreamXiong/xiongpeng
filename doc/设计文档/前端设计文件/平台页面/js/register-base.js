$(function() {
	$('.attract-nav>ul>li').click(function(){
		var index = $(this).index();
		$(this).addClass('active').siblings('li').removeClass('active');
		$('.slide .show-bg').eq(index).show().siblings('.show-bg').hide();
	})
});