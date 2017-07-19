<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>服务公司</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${ctx}/css/index.css">
	<%@include file="../common/common.jsp"%>
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
		<div class="swiper-slide" style="height:320px;">
			<img src="${ctx}/master/generateComImage?id=${pifd.id}&imgName=${pifd.companyImage1}"  alt="" style="width:100%;height:100%;" />
		</div>
		<div class="swiper-slide" style="height:320px;">
			<img src="${ctx}/master/generateComImage?id=${pifd.id}&imgName=${pifd.companyImage2}"  alt="" style="width:100%;height:100%;" />
		</div>
		<div class="swiper-slide" style="height:320px;">
			<img src="${ctx}/master/generateComImage?id=${pifd.id}&imgName=${pifd.companyImage3}"  alt="" style="width:100%;height:100%;" />
		</div>
	</div>
	<div class="swiper-pagination"></div> 
	<span class="navigation" onclick="goShop();">           
		<span style="background:rgba(0,122,255,.8);padding:8px 10px;position:absolute;top:0;left:0;">导航</span>
	</span>
</div>
<!-- 首页轮播图结束 -->
<%-- <input type="hidden" id="shopAddress" value="${t.province }${t.city }${t.country }${t.street }${t.regAddress }" /> --%>
<input type="hidden" id="userLat" value="${shop_lat}" />
<input type="hidden" id="userLon" value="${shop_lon}" />
<input type="hidden" id="shopLon" value="${pifd.lon}" />
<input type="hidden" id="shopLat" value="${pifd.lat}" />
<%-- <input type="hidden" value="${t.id}" /> --%>

<div class="contact">
	<div class="contact-top clearfix">
		<p>
			<span class="icon-home" style="color:#f2000e;font-size:1rem;"></span>  
			${pifd.companyName}
		</p>
		<p style="font-size:14px;" "><a href="tel:${pifd.contractPhone}" class="icon-phone-sign" style="font-weight:bold;">${pifd.contractPhone}</a></p>
	</div>
	<div class="contact-top clearfix contact-bottom">
		<p>${pifd.provinceName }${pifd.cityName }${pifd.countryName }${pifd.streetName }${pifd.regAddress }</p>
		<%-- <p><span class="icon-map-marker" style="font-size:0.9rem;"></span> <span id="blance">${blance}</span>km</p> --%>
	</div>
</div>
<%-- <div class="dealer-nav" id="productInfoList" style="margin-bottom:0;padding-bottom:50px;">
	<%@include file="productList.jsp"%>
</div> --%>
	<footer class="footer">
		<ul class="clearfix nav-bar text-center">
			<li><span class="nav-bar-a">
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
				<li><span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick.png"></a></span></li>
			<li><span class="nav-bar-d"><a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png"></a></span></li>
		</ul>
	</footer>
	
	
	<form id="addressForm" action="${ctx }/shop/goShopAddress">
	<%-- 	<input type="hidden" name="shopLat" value="${t.lat }" />
		<input type="hidden" name="shopLon" value="${t.lon }" />    --%>
		<input type="hidden" name="userLat" id="userLatShop" value="${shop_lat}"/> 
		<input type="hidden" name="userLon" id="userLonShop" value="${shop_lon}"/> 
	</form>
</body>

<script>
	$(function(){
		var winW = $(window).width()-100;
		var divH = winW/2;
		$('#attention').click(function(){
			layer.open({
				content:'<div style="width:'+winW+'px;height:'+divH+'px;">'+
							'<img src="${ctx}/images/attention.jpg" style="width:100%;height:100%;"/>'+
						'</div>',
			})
		});
	});
</script>
</html>