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
	    <input type="hidden" value="${shop_lon }" name="lon" id="lon" />
		<input type="hidden" value="${shop_lat }" name="lat" id="lat" />
		<input type="hidden" value="" name="dateTime" id="dateTime" />
		<input type="hidden" value="" name="repairmanId" id="repairmanId" />
	</form>
	
	<form method="post" id="goServiceDetail" action="${ctx}/customerIndex/goServiceDetail">
	    <input type="hidden" id="serviceId" value="" name="serviceId"/>
	</form>
	
	<div id="mapContainer" style='display:none;'></div>
<header class="cart verify-head ">
	<span class="icon-map-marker text-center" style="position:absolute;line-height:44px;left:10px;"> </span>
	<span style="position:absolute;left:25px;font-size:14px;"><a id="addressInfo" href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=cust" >${city}</a></span>
	
	<input type="hidden" value="${city}" name="city" id="city" />
	<h3 class="text-center">惠给力</h3>
</header>		
<footer class="footer">
	<ul class="clearfix nav-bar text-center">
		<li><span class="nav-bar-a"><a href="${ctx}/customerIndex/index"><img src="${ctx}/images/index-red.png"></a></span></li>
		<li><span class="nav-bar-b"><a href="${ctx}/master/index"><img src="${ctx}/images/master.png"></a></span></li>
		<li><span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick.png" alt=""></a></span></li>
		<li><span class="nav-bar-d"><a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png" alt=""></a></span></li>
	</ul>
</footer>
<div class="swiper-container" style="margin-top:44px;">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
    </div>
    <div class="swiper-pagination"></div>
</div>
<div class="container" style="padding-bottom:50px;margin-bottom:0;padding-top:0;">       
<input type="hidden" id="cInterval" />
	<div class="consult bg-white clearfix common">
		<div class="pull-left left-bg"></div>
		<div class="pull-left left-text">
			<ul class="consult-list">
			<c:forEach var="item" items="${tbNoticeInfolist}" varStatus="s">
				<li><a href="${ctx}/getNoticeInfoDetail?id=${item.id}">${item.noticeName}</a></li>
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
   /* 师傅要出发了 */
 	//1：弹出导航页面
 	 function goBeiZhan(id){
	   $.ajax({
	        type: "POST",
	        async: false,
	        url: ctx+"/wapOrderService/goCustomeraddress",
	        data:{
	        	orderId:id
	        },
	        success: function(response){
	        	goFindMaster(id);
	        	/*重新开一个窗口进行导航*/
	        	var from = encodeURI(response.g.regeocode.formatted_address);
	        	var to = encodeURI(response.t.extensionField);
	        	var url = 'http://m.amap.com/navigation/walkmap/saddr='
	        				+response.lon+'%2C'+response.lat+'%2C'+from+'&daddr='
	        				+response.t.lon+'%2C'+response.t.lat+'%2C'+to+'&saddr_lonlat=&daddr_lonlat=%2C%2C'
	        				+to+'&maddr=&sort=&addPassing=remove';
	        	window.open(url,'_blank');
	       	}
		});
   }
 	 function goFindMaster(repairmanId){
     	$("#repairmanId").val(repairmanId);
     	var t1 = window.setInterval(findMasterAddress,180000); 
     	$("#cInterval").val(t1);
     }
 	/**
 	 * 定时刷新师傅位置
 	 * */
 	function findMasterAddress(){
 		var map = new AMap.Map('container', {
 		    resizeEnable: true,
 		    zoom: 15
 		});
 		map.plugin('AMap.Geolocation', function() {
 		    geolocation = new AMap.Geolocation({
 		        enableHighAccuracy: true,//是否使用高精度定位，默认:true
 		        timeout: 8000,          //超过10秒后停止定位，默认：无穷大
 		        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
 		        zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
 		        buttonPosition:'RB'
 		    });
 		    map.addControl(geolocation);
 		    geolocation.getCurrentPosition();
 		    AMap.event.addListener(geolocation, 'complete',successAlert );//返回定位信息
 		    AMap.event.addListener(geolocation, 'error',failAlert );      //返回定位出错信息
 		});
 	} 

 	function successAlert(data){
 		$("#dateTime").val(new Date());
 		var param = $("#blankShopInfo").serialize();
 		$.ajax({
 	        type: "POST",
 	        url: ctx+"/wapOrderService/findMasterAddress",  
 	        data:param,
 	        success: function(response){
 	       	}
 		});
 	}
 	function stopInterval(){
 		window.clearInterval( $("#cInterval").val()); 
 	}
 	function failAlert(){
 		alert("失败");
 	}
</script>
</html>
	