<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>店铺首页</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<%@include file="../common/common.jsp"%>
	<script src="${ctx}/js/common/base.js"></script>	
	<script src="${ctx}/js/hgl/shop.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
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
    	<c:if test="${empty imageList}">
	    	<div class="swiper-slide"  style="height:320px;"><img src='${ctx}/login/generateImage?id=${t.id}&imgName=${t.shopImage1}' alt="" style="height:100%;width:100%;"></div>    	
        </c:if>     
		<c:forEach items="${imageList}" var="image">
			<div class="swiper-slide" onclick="window.location.href='${ctx}/activityInfo/doSearchActivityInfo?id=${image.id}'" style="height:320px;">
				<img src="showActivityImage?id=${image.id}&imgName=${image.image}"  alt="" style="width:100%;height:100%;">
			</div>
		</c:forEach>
	</div>
	<div class="swiper-pagination"></div> 
	<span class="collect" id="attention">
		<span style="background:rgba(242,0,14,.8);padding:8px 10px;position:absolute;top:0;right:0;">关注公共号</span>
	</span>	      
	<span class="navigation" onclick="goShop();">           
		<span style="background:rgba(0,122,255,.8);padding:8px 10px;position:absolute;top:0;left:0;">导航</span>
	</span>
</div>
<!-- 首页轮播图结束 -->
<input type="hidden" id="shopAddress" value="${t.province }${t.city }${t.country }${t.street }${t.regAddress }" />
<input type="hidden" id="userLat" value="${shop_lat}" />
<input type="hidden" id="userLon" value="${shop_lon}" />
<input type="hidden" id="shopLon" value="${t.lon}" />
<input type="hidden" id="shopLat" value="${t.lat}" />
<input type="hidden" value="${t.id}" />
<c:if test="${not empty integralRules }">
	<div class="consult bg-white  common box">
		<div class="left-bg"></div>   
		<div class="left-text box1">
			<ul class="consult-list">
				 <c:forEach var="i" items="${integralRules}" varStatus="s">
				 	<c:if test="${i.type == 0}">
						<li>送订单金额${i.money }%积分。</li>
					</c:if>
					<c:if test="${i.type == 1}">
						<li>满${i.payMoney }送${i.money}积分。</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	</c:if>
<div class="contact" style="border-top:1px solid #eee;">
	<div class="contact-top clearfix">
		<p>
			<span class="icon-home" style="color:#f2000e;font-size:1rem;"></span>  
			${t.shopName}
		</p>
		<p style="font-size:14px;" "><a href="tel:${t.contractPhone }" class="icon-phone-sign" style="font-weight:bold;"> ${t.contractPhone }</a></p>
	</div>
	          
	<div class="contact-top clearfix contact-bottom">
		<p><span class="icon-map-marker" style="font-size:0.9rem;"></span>&nbsp;${t.province }${t.city }${t.country }${t.street }${t.regAddress }</p>
		<p><span class="icon-map-marker" style="font-size:0.9rem;"></span> <span id="blance">${blance}</span>km</p>
	</div>
</div>
<div class="dealer-nav" id="productInfoList" style="margin-bottom:0;padding-bottom:50px;">
	<%@include file="productList.jsp"%>
</div>
	<footer class="footer">
		<ul class="clearfix nav-bar text-center">
			<li><span class="nav-bar-a">
				<%-- <c:if test="${session_key.typeId == 114}">
					<a href="${ctx}/indexShopInfo">
				</c:if>
				
				<c:if test="${session_key.typeId != 114}">
					<a href="${ctx}/customerIndex/index">
				</c:if> --%>
				<a href="${ctx}/shop/userShop">
			<img src="${ctx}/images/index-red.png"></a></span></li>
			
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
		<input type="hidden" name="shopLat" value="${t.lat }" />
		<input type="hidden" name="shopLon" value="${t.lon }" />   
		<input type="hidden" name="userLat" id="userLatShop" value="${shop_lat}"/> 
		<input type="hidden" name="userLon" id="userLonShop" value="${shop_lon}"/> 
	</form>
	
	<div class="screen-warp" id="productTypeAndBrand">
		<%@include file="productTypeList.jsp"%>
	</div>
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