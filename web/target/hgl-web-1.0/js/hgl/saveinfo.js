$(function(){
	$('.ul-list').delegate('li', 'mouseover', function(event) {
		$(this).find('.close').show();
	});
	$('.ul-list').delegate('li', 'mouseout', function(event) {				
		$(this).find('.close').hide();
	});
	
	$('.main-head h3').click(function(event) {
		var index = $(this).index();
		$(this).addClass('active').siblings('h3').removeClass('active');
		$('.ul-list ul').eq(index).css('display','block').siblings('.ul-list ul').css('display','none');
	});
});

	
//删除产品
function deleteGoodInfo(goodId){
	$.ajax({
		type:"post",
		url:"doDeleteSaveInfo",
		data:{"id":goodId},
		success:function(data){
			if(data==1){
				$("#goodInfo"+goodId).remove();
			}else{
				showModifyTips("删除失败");
			}
		}
	});
}
	
//删除店铺
function deleteShopInfo(shopId){
	$.ajax({
		type:"post",
		url:"doDeleteSaveInfo",
		data:{"id":shopId},
		success:function(data){
			if(data==1){
				$("#shoInfo"+shopId).remove();
			}else{
				showModifyTips("删除失败");
			}
		}
	});
}

//调用弹出层
function showModifyTips(tipsInfo){
	$('.myModal #modalSpan').html(tipsInfo);
	$('.myModal').fadeIn(500);
	timer = setTimeout(function(){
		$('.myModal').fadeOut(500);
	},2000);
}