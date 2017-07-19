<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="店铺导航" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
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
<%@include file="../common/header.jsp"%>
<form id="addressInfoList">

	<input type="hidden" id="shopLat" value="${shopLat}" />
	<input type="hidden" id="shopLon" value="${shopLon }" />   
	<input type="hidden" name="userLat" id="userLatShop" value="${shop_lat}"/> 
	<input type="hidden" name="userLon" id="userLonShop" value="${shop_lon}"/> 

</form>
   <div id="container" tabindex="0"></div>
   <div id="panel"></div>
   <script type="text/javascript">
	    var map = new AMap.Map('container',{resizeEnable: true,zoom: 15});
	    var walking = new AMap.Walking({ map: map, panel: "panel"}); 
	    walking.search( [$("#userLonShop").val(),$("#userLatShop").val()] , [$("#shopLon").val(), $("#shopLat").val()] );
	    map.setFitView();
   </script>
  </body>
</html>