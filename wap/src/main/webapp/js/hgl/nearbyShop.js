$(function(){
	$('.dealer-list a').height($('.dealer-list img').width());
	$('#screen').click(function(event) {
		$('html').addClass('hidden classification');
		$('.screen-warp').css('display','block').animate({
			left:'0'
		},300);
	});
	
	$(document).delegate('.back','click',function(){
		$('.screen-warp').hide().css('left','100%');
		$('html').attr('class','');
		var p = '';
		var pLen = $("#productTypes .active").length;
		var t = $("#productTypes .active");
		for(var i=0 ; i < pLen ; i++){
			var s = $(t[i]).attr("name");
			if(i == pLen-1){
				p += s;
			}else{
				p += s+",";
			}
		}
		$("#typeList").val(p);
		selectShop();
	});
});
