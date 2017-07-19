<%@page pageEncoding="UTF-8"%>
<div class="nav-r-main">
	<div class="hgl-list">
		<h3>服务分类</h3>
		<ul class="clearfix">
			<c:forEach var="pItem" items="${p}" varStatus="pindex">	
				<li><a href="javascript:;">${pItem.name}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<script>
$(function() {
 		var winH = $(window).height();
 		var headH = $('.screen header').outerHeight(true);
 		$('#screen').click(function(event) {
 			$('html').addClass('hidden classification');
 			$('.screen-warp').css('display','block').animate({
 				left:'0'
 			},300);
 		});
 		$('#save').click(function(event) {
 			$('.screen-warp').animate({
 				left:'100%'
 			},300,function(){
 				$(this).css('display','none');
 			});
 			$('body').removeClass('hidden');
 		});
 		$('.back').click(function(event) {
 			$('.screen-warp').animate({
 				left:'100%'
 			},300,function(){
 				$(this).css('display','none');
 			});
 			$('html').attr('class','');
 		});
 		$('.selectMaster').click(function(event) {
 			/* $('.screen-warp').animate({
 				left:'100%'
 			},300,function(){
 				$(this).css('display','none');
 			}); */
 			$('.screen-warp').fadeOut(30).css('left','100%'); 
 			$('html').attr('class','');
 		});
 		$('.class-nav').height(winH-headH);
 		$('.dealer-list img').height($('.dealer-list img').width());
 		$('.hgl-list').delegate('li','click',function(){
 			$(this).toggleClass('active');
 		});
 	});
 	</script>