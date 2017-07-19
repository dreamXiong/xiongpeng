<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>惠给力</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<script src="${pageContext.request.contextPath}/js/hgl/customerIndex.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
    
</head>
<body>
 	<form method="post" id="blankShopInfo">
	    <input type="hidden" value="" name="lon" id="lon" />
		<input type="hidden" value="" name="lat" id="lat" />
	</form>
	
	<form method="post" id="goServiceDetail" action="customerIndex/goServiceDetail">
	    <input type="hidden" id="serviceId" value="" name="serviceId"/>
	</form>
	
	<div id="mapContainer" style='display:none;'></div>
<header class="cart verify-head ">
	<span class="icon-map-marker text-center" style="position:absolute;line-height:44px;left:10px;"> </span>
	<span style="position:absolute;left:25px;font-size:14px;" id="addressInfo"></span>
	<h3 class="text-center">惠给力</h3>
	<span class="icon-reorder"></span>
	 <%@include file="../common/topBar.jsp"%>
</header>		

<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/12.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/12.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
    </div>
    <div class="swiper-pagination"></div>
</div>

<div class="container" style="padding-bottom:50px;">
	<div class="consult bg-white clearfix common">
		<div class="pull-left left-bg"></div>
		<div class="pull-left left-text">
			<ul class="consult-list">
			<c:forEach var="item" items="${tbNoticeInfolist}" varStatus="s">
				<li>${ item.noticeName}</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="common nav-activity clearfix text-center bg-white">
		<div class="pull-left">
			<a href="${ctx}/masterWorker/index">找师傅</a>
		</div>
		<div class="pull-left">
			<a href="${ctx}/pick/pickList">买材料</a>
		</div>
		<%-- <div class="pull-left">
			<a href="${ctx}/nearbyShop/index">附近的店</a>
		</div> --%>
	</div>
	<div class="nav-list" id="indexShopInfoPage">
		 <%@include file="customerList.jsp"%> 
	</div>
</div>
  	<%@include file="../common/footer.jsp"%>
</body>
<script>
	var swiper = new Swiper('.swiper-container', {autoplay : 1000,
        pagination: '.swiper-pagination',
    });
	$(function(){
		$("img.lazy").lazyload({
			threshold : 20,
			effect : "fadeIn",
		});
	});
	function paginationInit(){
			swiper = new Swiper('.swiper-container', {autoplay : 1000,
	        pagination: '.swiper-pagination',
	    });
	}
   var liW = $('.nav-list li').width();
   $('.nav-list li>a').height(liW);
   $('.nav-list li img').height($('.nav-list li img').width());
   
   function goBeiZhan(){
	   $.ajax({
	        type: "POST",
	        async: false,
	        url: "customerIndex/goBeiZhan",  
	        success: function(response){
	        	var from = encodeURI(response.g.regeocode.formatted_address);
	        	var to = encodeURI(response.t.address);
	        	var url = 'http://m.amap.com/navigation/walkmap/saddr='+response.lon+'%2C'+response.lat+'%2C'+from+'&daddr='+response.t.lon+'%2C'+response.t.lat+'%2C'+to+'&saddr_lonlat=&daddr_lonlat=%2C%2C'+to+'&maddr=&sort=&addPassing=remove';
	        	window.open(url,'_blank');
	       	}
		});
   }
</script>
</html>
	