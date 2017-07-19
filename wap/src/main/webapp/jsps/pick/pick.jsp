<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<title>买材料</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<div class="store-head">
	<div class="contact" >
		<div class="but-group">
			<div class="addr text-hidden" >		
				<span class="icon-map-marker"></span>
				<b><a href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=pick" >${city}</a></b>
			</div>
			<div>
				<form id="goPickDetail" method="post" action="${ctx}/pick/pickDetail">
					<input name="id" value="" type="hidden" id="productId"/>
				</form>
				
				<form id="selectForm">
					<input name="orderProduct" value="" type="hidden" id="orderProduct"/>
					<input name="productTypeIds" value="" type="hidden" id="productTypeSelect"/>
					<input name="brandIds" value="" type="hidden" id="brandIdsSelect"/>
					<input name="searchText" value="" id="searchInfo" type="hidden" />
				</form>
				<a onclick="selectProduct();" class="service"><span class="icon-search" ></span></a>  
				<input id="searchText" value="" type="text"/>
			</div>
		</div>
	</div>
	<div class="nab-list box-shadow" style="margin-bottom:0px;">
		<ul class="clearfix choose-list" >
			<li class="nav-tab active" style="width: 25%;" id="defaultType" ><a href="javascript:;">综合</a></li>
			<li class="nav-tab" style="width: 25%;" id="saleNum"><a href="javascript:;">销量 <span class="icon-angle-up"></span></a></li>
			<li class="nav-tab" style="width: 25%;" id="price"><a href="javascript:;">价格 <span class="icon-angle-up"></span></a></li>
			<!-- <li class="nav-tab" id="distance"><a href="javascript:;">距离</a></li> -->
			<li id="screen" style="width: 25%;"><a href="javascript:;">筛选</a></li>
		</ul>
	</div>
</div>
<div class="dealer-nav" id="pickInfoList" style="margin-top:82px;margin-bottom:0;padding-bottom:50px;">       
	<%@include file="pickList.jsp"%>
</div>

	<footer class="footer">
		<ul class="clearfix nav-bar text-center">
			<li><span class="nav-bar-a">
			<%-- <c:if test="${session_key.typeId == 114}">
				<a href="${ctx}/indexShopInfo">
			</c:if>
			
			<c:if test="${session_key.typeId != 114}">
				<a href="${ctx}/customerIndex/index">
			</c:if>
			--%>
			
			<a href="${ctx}/shop/userShop">
			<img src="${ctx}/images/index.png"></a></span></li> 
			
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
			<li>
				<span class="nav-bar-d">
					<a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png"></a>
					<c:if test="${infoCountSum > 0}">
						<span class="float-icon">
							${infoCountSum}
						</span>	  
					</c:if>      
				</span>
			</li>
		</ul>
	</footer>

	<div class="screen-warp" id="productTypeAndBrand">
		<%@include file="productTypeList.jsp"%>
	</div>
</div>
</body>
<script>
 	$(function() {
 		var liW = $('.dealer-nav li').width();
 		$("#orderProduct").val('');
 		$("#productTypeSelect").val('');           
 		$("#brandIdsSelect").val('');
 		$('.dealer-list a').height(liW); 
 		var winH = $(window).height();
 		var head = $('header').height();
 		$('.class-nav-r').height(winH - head);
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
 		$('#save').click(function(event) {
 			$('.screen-warp').animate({
 				left:'100%'
 			},500,function(){
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
			$('body').attr('class','');
			$(".nav-r-main li .active").removeClass("active");
		});
		
		$('.selectPickInfo').click(function(event) {
			$('.screen-warp').animate({
				left:'100%'
			},300,function(){
				$(this).css('display','none');
			});
			$('body').attr('class','');
			selectProduct();
		});
 		$('.dealer-list img').height(liW);
 	});
 	var swiper = new Swiper('.swiper-container', {
		autoplay : 1000,
        pagination: '.swiper-pagination',
    });
 	$('.class-nav-l li').click(function(event) {
		var index = $(this).attr("name");
		$(".productShowHide").hide();
		$(".brandShowHide").hide();
		$("#product_"+index).show();
		$("#brand_"+index).show();
		$(this).addClass('cur').siblings('li').removeClass('cur');
		$(".nav-r-main li .active").removeClass("active");
	});
 	$('.hgl-list').delegate('a','click',function(){
 		
		$(this).parent('li').toggleClass('active');
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
			}
		}  
		var bLen = $("#brandActive .active").length;
		var bObj = $("#brandActive .active");
		for(var v=0 ;v < bLen;v++){
			var a =$($(bObj[v])).attr("name");
			if(v == bLen-1){
				b += a;
			}else{
				b += a+",";
			}
		}
		$("#brandIdsSelect").val(b);
		$("#productTypeSelect").val(p);
	});
 	function selectProduct(){
 		var searchText = $("#searchText").val();
 		$("#searchInfo").val(searchText);
 		var param = $("#selectForm").serialize();
 		$.ajax({
 	         type: "POST",
 	         async: false,
 	         url: "selectPickList",  
 	         data:param,
 	         success: function(response){  
 	        	$("#pickInfoList").html(response);
 	        	laxyImgFun();
 	         }
 		});
 	}
 	function laxyImgFun(){
		$("img.lazy").lazyload({
			threshold : 20,                               
			effect : "fadeIn",                               
		});
		var liW = $('.dealer-nav li').width();
 		$('.dealer-list a').height(liW);  
	}
 	function pickDetail(id){
 		 $("#productId").val(id);
 		 $("#goPickDetail").submit();
 	}
 	$('#searchText').bind('keyup', function(event) {
        if (event.keyCode == "13") {
        	selectProduct(); 
        }
    });
</script>
</html>