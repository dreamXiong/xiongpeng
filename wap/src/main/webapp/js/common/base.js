$(function() {
	var li_len=$('.consult-list li').length;
	var li_Last=$('.consult-list li').first().html();
	var li_H=$('.consult-list li').last().height();
	var timer = null,num = 0;

	$("img.lazy").lazyload({
		threshold : 200,
		effect : "fadeIn",
	});

	$('.consult-list').append('<li >' + li_Last + '</li>');
	
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
});
