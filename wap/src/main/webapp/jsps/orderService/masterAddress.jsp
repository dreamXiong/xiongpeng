<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<title>师傅位置</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
 	<%@include file="../common/common.jsp"%>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548&plugin=AMap.Walking"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>	
<style type="text/css">
  body,html,#container{
    height: 100%;
    margin: 0px
  }
</style>
<body>
<header class="cart verify-head ">
	<span class="text-center" style="position:absolute;line-height:44px;left:10px;">离目的地仅<a style="color: red;" id="addressHref">${distance}km</a></span> 
	<span style="position:absolute;left:25px;font-size:14px;" id="addressInfo"></span>
	<h3 class="text-center">惠给利</h3>
	<span class="icon-reorder"></span>
	 <%@include file="../common/topBar.jsp"%>
</header>		

<!-- 师傅的坐标 -->
<input type="hidden" id="lon" value="${lon}">
<input type="hidden" id="lat" value="${lat}">
<form id="addressInfoList">
	<!-- 客户提交服务订单坐标的坐标 -->
	<input type="hidden" id="addressLat" name="lat" value="${address.lat}">
	<input type="hidden" id="addressLon" name="lon" value="${address.lon}">
	
	<input type="hidden" id="addressId" name="addressId" value="${w.id}">
	<input type="hidden" id="repairmanId" name="repairmanId" value="">
	
	<input type="hidden" id="cInterval" name="cInterval"/>
	<input type="hidden" id="dateTime" name="dateTime"/>
</form>
   <div id="container" tabindex="0"></div>
   <div id="panel"></div>
   <script type="text/javascript">
	    var map = new AMap.Map('container',{resizeEnable: true,zoom: 15});
	    var walking = new AMap.Walking({ map: map, panel: "panel"}); 
	    walking.search([$("#lon").val(), $("#lat").val()], [$("#addressLon").val(),$("#addressLat").val()]);
	    map.setFitView();
		/* 刷新师傅的位置 */
    	var t1 = window.setInterval(masterAddressShow,180000); 
    	$("#cInterval").val(t1); 
	    /*客户查看师傅位置*/
	    function masterAddressShow(){
	 	  $("#dateTime").val(new Date());
	 	  var lon = $("#addressLon").val();
	 	  var lat = $("#addressLat").val();
	 	  var repairmanId = $("#repairmanId").val();
	 	  var orderId = $("#addressId").val();
	 	   $.ajax({
	 	        type: "POST",
	 	        url: ctx+"/wapOrderService/masterAddressMapShow",  
	 	        data:"repairmanId="+repairmanId+"&lon="+lon+"&lat="+lat+"&orderId="+orderId,
	 	        success: function(response){
	 	     	    var maps = new AMap.Map('container',{resizeEnable: true,zoom: 15});
	 	     	    var walkings = new AMap.Walking({ map: maps, panel: "panel"}); 
	 	     	    walkings.search([$("#lon").val(), $("#lat").val()], [$("#masterlon").val(),$("#masterlat").val()]);
	 	  	    	maps.setFitView();
	 	  	    	$("#addressHref").text(response.distance+"km");
	 	       	}
	 		});
	    }
   </script>
  </body>
</html>