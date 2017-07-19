<%@page pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.12.2.min.js"></script>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
    <form method="post" id="pickInfo" action="pickListInfo">
	    <input type="hidden" value="" name="lon" id="lon" />
		<input type="hidden" value="" name="lat" id="lat" />
	</form>
	
	<script>
	var map, geolocation;
	//加载地图，调用浏览器定位服务
	$(function(){
		map = new AMap.Map('container', {
		    resizeEnable: true
		});
		map.plugin('AMap.Geolocation', function() {
		    geolocation = new AMap.Geolocation({
		        enableHighAccuracy: true,//是否使用高精度定位，默认:true
		        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
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
		$("#pickListInfo").submit();
	}
	//解析定位错误信息
	function onError(data) {
		 alert("定位失败！！");  
	}
	</script>