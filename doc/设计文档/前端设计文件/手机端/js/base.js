$(function() {
	var li_len=$('.consult-list li').length;
	var li_Last=$('.consult-list li').first().html();
	var li_H=$('.consult-list li').last().height();
	var timer = null,num = 0;
	var color_arr = ['#6600FF','#6633FF','#6666FF','#6699FF','#66CCFF'];

	$("img.lazy").lazyload({
		threshold : 200,
		effect : "fadeIn",
	});
	$('.consult-list li').each(function(i){
		$(this).css('color',color_arr[i]);
	});

	$('.consult-list').append('<li style="color:#6600FF">' + li_Last + '</li>');
	
	timer = setInterval(function(){
		num++;
		if(num > (li_len-1)){
			num = 0;
			$('.consult-list').css({
				top: 0
			});
		};
		$('.consult-list').css({
			top:-num*li_H
		});
		
	},3000) ;

 	$('.icon-reorder').click(function(event) {
 		$(this).siblings('.nav').toggleClass('click');
 		event.stopPropagation();
 	});
 	$(document).click(function(event) {
 		$('header .click').removeClass('click');
 	});
 	
	
});
