var map, geolocation;
//加载地图，调用浏览器定位服务
$(function(){
	map = new AMap.Map('container', {
	    resizeEnable: true
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
        url: "ajaxIndexShopInfo",  
        data:param,
        success: function(response){
        	paginationInit();
        	$("#indexShopInfoPage").show();
	       	$("#indexShopInfoPage").html(response);
	       	$('.dealer-list img').height($('.dealer-list img').width());
	       	laxyImgFun();
	       	navListLiImg();
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