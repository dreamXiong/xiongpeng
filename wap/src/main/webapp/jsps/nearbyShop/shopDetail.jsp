<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>店铺首页</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${ctx}/layer-mobile/layer/need/layer.css">
	<link rel="stylesheet" href="${ctx}/css/index.css"> 
	<%@include file="../common/common.jsp"%>
	<script src="${ctx}/js/hgl/shop.js"></script>
</head>
<body>

<form id="goPickDetail" method="post" action="${ctx}/pick/pickDetail">
	<input name="id" value="" type="hidden" id="productId"/>
</form>

<form id="selectForm">
	<input name="distance" value="${distance}" type="hidden" id="distance"/>
	<input name="id" value="${id}" type="hidden" id="id"/>
	<input name="orderProduct" value="" type="hidden" id="orderProduct"/>
	<input value="" type="hidden" name="productTypeIds" id="productTypeSelect"/>
	<input value="" type="hidden" name="brandIds" id="brandIdsSelect"/>
</form>
<div class="swiper-container outlet">
    <div class="swiper-wrapper">
    	<div class="swiper-slide"><img src='${ctx}/login/generateImage?id=${t.id}&imgName=${t.shopImage1}' alt=""></div>
    	<div class="swiper-slide"><img src='${ctx}/login/generateImage?id=${t.id}&imgName=${t.shopImage2}' alt=""></div>
        <div class="swiper-slide"><img src='${ctx}/login/generateImage?id=${t.id}&imgName=${t.shopImage3}' alt=""></div>
	</div>
	<div class="swiper-pagination"></div>
	<span class="collect" id="${t.id}">收藏店铺</span>
</div>
<!-- 首页轮播图结束 -->


<div class="contact">
	<div class="contact-top clearfix">
		<p>${t.shopName}</p>
		<p style="font-size:14px;"><a href="tel:${t.companyTel }" class="icon-phone-sign" style="font-weight:bold;"> ${t.contractPhone }</a></p>
	</div>
	          
	<div class="contact-top clearfix contact-bottom">
		<p>${t.province }${t.city }${t.country }${t.street }${t.regAddress }</p>
		<p><span class="icon-map-marker"></span> ${distance}km</p>
	</div>
</div>
<div class="nab-list">
	<ul class="clearfix">
		<li class="nav-tab active" id="defaultType"><a href="javascript:;">综合</a></li>
		<li class="nav-tab" id="saleNum"><a href="javascript:;">销量<span class="icon-angle-up"></span></a></li>
		<li class="nav-tab" id="price"><a href="javascript:;">价格<span class="icon-angle-up"></span></a></li>
		<li id="screen"><a href="javascript:;">筛选</a></li>
	</ul>
</div>

<div class="dealer-nav" id="productInfoList">
	<%@include file="productList.jsp"%>
</div>

	<%-- <%@include file="../common/footer.jsp"%> --%>
		<footer class="footer">
			<ul class="clearfix nav-bar text-center">
				<li>
					<span class="nav-bar-a"><a href="${ctx}/shop/userShop"><img src="${ctx}/images/index.png"></a></span>
				</li> 
				<li>
					<span class="nav-bar-b">
						<c:if test="${session_key.typeId == 114 || session_key.typeId == 104}">
							<a href="${ctx}/wapOrderService/service"><img src="${ctx}/images/order.png"></a>
						</c:if>
						<c:if test="${session_key.typeId != 114 && session_key.typeId != 104}">
							<a href="${ctx}/master/index"><img src="${ctx}/images/master.png"></a>
						</c:if>
					</span>
				</li>
					<li><span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick-red.png"></a></span></li>
				<li><span class="nav-bar-d"><a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png"></a></span></li>
			</ul>
		</footer>
		
		<div class="screen-warp" id="productTypeAndBrand">
			<%@include file="productTypeList.jsp"%>
		</div>
</body>

<script>

$(function() {
	var winW = $(window).width();           
	var height = winW*0.85;
	$('.swiper-wrapper .swiper-slide').height(height);   
	var swiper = new Swiper('.swiper-container', {autoplay : 2000,
        pagination: '.swiper-pagination',
    });

	$("#orderProduct").val('');
	$("#productTypeSelect").val('');
	$("#brandIdsSelect").val('');
		$('.nav-tab').click(function(){
			var span = $(this).find('span');
			if ($(this).hasClass('active')) {
 				if(span.hasClass('icon-angle-up')){
 					$("#orderProduct").val($(this).attr("id")+"_desc");
					span.attr('class','icon-angle-down');
					selectProduct();
 				}else{
 					span.attr('class','icon-angle-up');
 					$("#orderProduct").val($(this).attr("id")+"_asc");
					selectProduct();
 				}
 			}else{
 				if($(this).attr("id") == 'saleNum'){
 					$("#orderProduct").val($(this).attr("id")+"_desc");
 				}else{
 					$("#orderProduct").val($(this).attr("id")+"_asc");
 				}
 				$(this).addClass('active').siblings('.nav-tab').removeClass('active');
 				span.attr('class','icon-angle-up');
 				selectProduct();
 				$('#saleNum').find('span').attr('class','icon-angle-down');
 			};
		});
		$('#screen').click(function(event) {
			$('html').addClass('hidden classification');
			$('.screen-warp').css('display','block').animate({
				left:'0'
			},300);
		});
		
		$('.save').click(function(event) {
			$('.screen-warp').animate({
				left:'100%'
			},300,function(){
				$(this).css('display','none');
			});
			$('html').removeClass('hidden');
		});
		$('.back').click(function(event) {
			$('.screen-warp').animate({
				left:'100%'
			},300,function(){
				$(this).css('display','none');
			});
			$('body').attr('class','');
			$(".nav-r-main li .active").removeClass("active");
		});
		
		$('.selectProductInfo').click(function(event) {
			$('.screen-warp').animate({
				left:'100%'
			},300,function(){
				$(this).css('display','none');
			});
			$('body').attr('class','');
			selectProduct();
		});
		$('.stair li').click(function(event) {
			var title = $(this).find('a').text();
			$(this).parent('.stair').hide();
			$('.movers').show();
			$('.screen-header').html('<span class="icon-angle-left text-center pull-left back" ></span>' +
		title +
		'<a href="javascript:;" class="pull-right save" >确定</a>');
		});
		$('.movers li').click(function(event) {
			$(this).toggleClass('currten');
		});
		var divH = 44;
		var winH = $(window).height();
		$('.class-nav-l').height(winH-divH);
		$('.class-nav-r').height(winH-divH); 
		$('.class-nav-l li').click(function(event) {
			var index = $(this).attr("name");
			$(".productShowHide").hide();
			$(".brandShowHide").hide();
			$("#product_"+index).show();
			$("#brand_"+index).show();
			$(this).addClass('cur').siblings('li').removeClass('cur');
			/* $(".class-nav-l li").removeClass("active"); */
			$(".nav-r-main li .active").removeClass("active");
	});
	$('.hgl-list').delegate('a','click',function(){
		$(this).toggleClass('active');
		var p = '';
		var b = '';
		var pLen = $("#productActive .active").length;
		var pObj = $("#productActive .active");
		for(var i=0 ;i<pLen;i++){
			var s =$(pObj[i]).attr("name");
			if(i == pLen-1){
				p += s;
			}else{
				p += s+",";
			};
		}  
		var bLen = $("#brandActive .active").length;
		var bObj = $("#brandActive .active");
		for(var v=0 ;v < bLen;v++){
			var a =$($(bObj[v])).attr("name");
			if(v == bLen-1){
				b += a;
			}else{
				b += a+",";
			};
		}
		$("#brandIdsSelect").val(b);
		$("#productTypeSelect").val(p);
	});
	$('.dealer-list img').height($('.dealer-list img').width());
});
function pickDetail(id){
	 $("#productId").val(id);
	 $("#goPickDetail").submit();
}

$('.collect').click(function(event) {
	var id = this.id;
	var typeId = 1;
	$.ajax({
		type:"post",
		url:"doOperateShopSaveInfo",
		data:{"id":id,"typeId":typeId},
		success:function(data){
			if(data=="1"){
				layer.open({
				    content:'收藏成功',
				    type:0,
				    time:1
				});
			}else if(data=="-1"){
				layer.open({
				    content:'收藏失败',
				    type:0,
				    time:1
				});
			}else if(data=="0"){
				layer.open({
				    content:'您已收藏该店铺',
				    type:0,
				    time:1
				});
			}
		}
	});	
});
</script>
</html>