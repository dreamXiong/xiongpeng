<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>找服务</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/hgl/customerIndex.js"></script>
	<script src="${pageContext.request.contextPath}/js/hgl/master.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
</head>
<body>
<form method="post" id="blankShopInfo">
    <input type="hidden" value="${shop_lon }" name="lon" id="lon" />
	<input type="hidden" value="${shop_lat }" name="lat" id="lat" />
</form>
<input type="hidden" value="${city }" name="lat" id="city" />
<%-- <div class="contact" style="position:fixed;z-index:1001;width:100%;-webkit-box-shadow: 1px 1px 15px rgba(0,0,0,.1);box-shadow: 1px 1px 15px rgba(0,0,0,.1);">
	<div class="but-group" style="padding-right:60px;">
		<div class="addr">		
			<span class="icon-map-marker" style="font-size:16px;"></span>
			<b><a id="addressInfo" href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=master" >${city}</a></b>
		</div>      
	</div>
</div> --%>
<!-- 轮播图开始 -->
<div class="swiper-container">
    <div class="swiper-wrapper">   
		<div class="swiper-slide" style="height:160px;">
			<img src="${ctx}/images/160x1601.jpg"  alt="" style="height:100%;width:100%;">
		</div>
		<div class="swiper-slide" style="height:160px;">
			<img src="${ctx}/images/160x1602.jpg"  alt="" style="height:100%;width:100%;">
		</div>
		<div class="swiper-slide" style="height:160px;">
			<img src="${ctx}/images/160x1601.jpg"  alt="" style="height:100%;width:100%;">
		</div>
	</div>
	<div class="swiper-pagination"></div> 
</div>     
<!-- 轮播图结束 -->
<div id="masterListId">
	<ul class="clearfix list-icon">
		<li>
			<a href="${ctx }/master/masterIndex" style="display:block;">
				<img src="${ctx}/images/shifu.png" style="height:60px"; >
				<p class="text-hidden">找师傅</p>
			</a>	
		</li>
		
		<c:forEach var="pItem" items="${cList}" varStatus="pindex">	
		<li onclick="goServiceDetail(${pItem.id});">
			<a href="javascript:;" style="display:block;" >
				<img  src="${ctx}/master/generateImage?id=${pItem.id}&imgName=${pItem.imgName}" alt="" style="height:60px";>
				<p class="text-hidden">${pItem.name}</p>
			</a>	
		</li>
	</c:forEach> 
		<li>
			<a href="javascript:;" style="display:block;">
				<img src="${ctx}/images/more.png" style="height:60px";>
				<p class="text-hidden" style="color:#ccc;">等待更新</p>
			</a>	
		</li>
</ul>    
</div>
	<form method="post" id="goServiceDetail" action="${ctx}/master/companyServiceList">
		<input type="hidden" id="serviceIdInfo" value="" name="typeId"/>
	</form>
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
						<a href="${ctx}/master/index"><img src="${ctx}/images/master-red.png"></a>
					</c:if>
				</span>
			</li>
				<li><span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick.png"></a></span></li>
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
</body>
<script>
var mySwiper = new Swiper('.swiper-container',{
	pagination : '.swiper-pagination',
	autoplay:5000
});
</script>	
</html>