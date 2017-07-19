<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>附近的店</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<%@include file="../common/common.jsp"%>
	<script src="${ctx}/js/hgl/nearbyShop.js"></script>
	<script src="${ctx}/js/common/iscroll.js"></script>		
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
   	<style>
   		.contact{
   			position: fixed;
   			width:100%;
   			z-index:1001;
	    	-webkit-box-shadow: 2px 0px 10px rgba(0,0,0,.1);
    		box-shadow: 2px 0px 10px rgba(0,0,0,.1);
   		}
   		.prolist .box1{
   			border:none;
   		}
   	</style>
</head>

<body onload="loaded();"> 

	<div class="contact" >
		<div class="but-group" style="padding-right:60px;">
			<div class="addr">		  
				<span class="icon-map-marker"></span>
				<b id="cityName">${city}</b>	
				<input type="hidden" id="cityNameId" value="${city }" />
			</div>
			<div>
				<form id="selectForm">
					<input type="hidden" id="typeList" name="typeList" value="" />
					<input name="searchText" value="" id="searchInfo" type="hidden" />
					<a class="search-icon" onclick="selectShop();" style="right:59px;">
						<span class="icon-search"></span>
					</a>
					<input type="hidden" name="indexPage" id="indexPage" value="1" />
				</form>
				<input name="searchText" id="searchText" type="text">
			</div>
			<div style="position:absolute;line-height:30px;right:0;top:0;" id="screen">筛选
				<span class="icon-filter"></span>
			</div>
		</div>
	</div>    
	<!-- 店铺展示 -->
	<div class="nearby-shop" id="wrapper" style="margin-bottom:0;"> 
	 	<div id="scroller">
	 		<!-- <div id="pullDown" class="">
				<span class="pullDownIcon"></span>
				<span class="pullDownLabel">下拉加载更多</span>
			</div> -->
			<ul class="prolist"  id="thelist">
				<%@ include file="nearbyShopList.jsp" %>
			</ul>
			<div id="pullUp" style="dispaly:none;">
				<span class="pullUpIcon"></span>
				<span class="pullUpLabel">上拉加载更多</span>
			</div>
		</div>
	</div>
	<!-- 筛选页面 -->
	<div class="screen-warp  nearby-screen">
		<div class="screen">
			<header class="text-center">
				<span class="icon-angle-left text-center back"></span>筛选<a href="javascript:;" class="back">确定</a>
			</header>
			<div class="class-nav">
				<h3>大类</h3>
				<ul id="productTypes" class="clearfix">
					<c:forEach var="item" items="${shopTypeList}" varStatus="s">
						<li class='text-hidden' name='${item.id}'>${item.name}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<form id="blankShopInfo" />
		<input type="hidden" name="lat" id="lat"/>
		<input type="hidden" name="lon" id="lon"/>
	</form>
	
	<form id="goShopIndex" action="${ctx}/shop/index" method="post"/>
		<input type="hidden" name="shopId" id="shopId"/>
		<input type="hidden" name="distance" id="distance"/>
	</form>
	
	<form id="addressForm" action="${ctx }/shop/goShopAddress" method="post">
		<input type="hidden" name="shopLat" id="shopLat" value="" />
		<input type="hidden" name="shopLon" id="shopLon" value="" />   
		<input type="hidden" name="userLat" id="userLatShop" value=""/> 
		<input type="hidden" name="userLon" id="userLonShop" value=""/> 
	</form>
	
</body>
<script>
var indexPage = 1;
var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;

function pullUpAction () {
	setTimeout(function () {	
			$("#pageCount").remove();
			++indexPage;
			selectNearShopPage();
			myScroll.refresh();
	}, 1000);				
}

function loaded() {
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll('wrapper', {
		topOffset: pullDownOffset, 
		vScrollbar:false,
		bounce:false,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';
			}
		},
		onScrollMove: function () {
			if (this.y < 0 && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松开立即加载';
			} 
		},
		
		onScrollEnd: function () { 
			if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				var pageCount = $("#pageCount").val();
				if(indexPage < pageCount){
					pullUpAction();
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
				}else{
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '没有更多内容...';
				}
			}
		}
	});
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
 	var map, geolocation;
 	//加载地图，调用浏览器定位服务
 	$(function(){
 			map = new AMap.Map('container', {
 	 		    resizeEnable: true,
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
 	 		    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
 	 		    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
 	 		});
 	});
 	
 	//解析定位结果  
 	function onComplete(data) {
 		$("#lon").val(data.position.getLng());
 		$("#lat").val(data.position.getLat());
 		var param = $("#blankShopInfo").serialize();
 		$.ajax({
 	        type: "POST",
 	        async: false,
 	        url: ctx+"/nearbyShop/getNearShop",  
 	        data:param,
 	        success: function(response){
 	        	$("#thelist").html(response);
 	        	selectNearShopType();
 	        	indexPage = 1;
 	        	$("#indexPage").val(indexPage);
 	        	showOrhide();
 	       	}
 		});
 	}
 	function selectNearShopType(){
 		$.ajax({
 	        type: "POST",
 	        async: false,
 	       	dataType:"json",
 	        url: ctx+"/nearbyShop/selectNearShopType",  
 	        success: function(response){
	 	          $("#cityName").text(response.address);
	 	          $("#cityNameId").val(response.address);
	 	       	  $("#userLatShop").val(response.userLat);
	 	       	  $("#userLonShop").val(response.userLon);
 	       	} 
 		});
 	}
 	
 	function laxyImgFun(){
 		$("img.lazy").lazyload({
 			threshold : 20,                               
 			effect : "fadeIn",                            
 		});
 	}
 	//解析定位错误信息
 	function onError(data) {
 		alert("定位失败，请手动获取城市信息。");
 	}
 	function isNull(str) {
 	    if (str == "")
 	        return true;
 	    var regu = "^[ |\\n|\\r]+$";
 	    var re = new RegExp(regu);
 	    return re.test(str);
 	}
 	
 	$('#searchText').bind('keyup', function(event) {
        if (event.keyCode == "13") {
        	selectShop(); 
        }
    });
 	
 	function selectShop(){
 		indexPage = 1;
 		var searchText = $("#searchText").val();
 		$("#searchInfo").val(searchText);
 		var param = $("#selectForm").serialize();
 		$.ajax({
 	         type: "POST",
 	         async: false,
 	         url: "selectNearShopList",  
 	         data:param,
 	         success: function(response){  
 	        	$("#thelist").html(response);
 	        	myScroll.refresh();
 	       		indexPage = 1;
 	       		$("#indexPage").val(1);
 	       		showOrhide();
 	         }
 		});
 	}
 	
 	function selectNearShopPage(){
 		var param = $("#selectForm").serialize();
 		$.ajax({
 	         type: "POST",
 	         async: false,
 	         url: "selectNearShopPage",  
 	         data:param,
 	         success: function(response){  
 	        	$("#thelist").append(response);
 	        	$("#indexPage").val(indexPage);
 	        	showOrhide();
 	         }
 		});
 	}
 	
 	function goShop(lat,lon){
	  $("#shopLat").val(lat);
      $("#shopLon").val(lon);
	  	layer.open({
		    content: '导航需消耗流量，确定使用吗？',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	$("#addressForm").submit();
		    }, no: function(){
		    	layer.close();
		    }
		});
 	} 
	function goShopIndex(shopId,distance){
		 $("#shopId").val(shopId);
		 $("#distance").val(distance);
		 $("#goShopIndex").submit();
	}
	$(function(){
		$('#productTypes li').on('click touchend',function(){
			$(this).toggleClass('active');
			return false;
		});
	 });
	
	function showOrhide(){
		var pageCount = $("#pageCount").val();
		if(indexPage < pageCount){
			$("#pullUp").show();
		}else{
			$("#pullUp").hide();
		}
	}
</script>
</html>
	