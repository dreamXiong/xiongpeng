<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>地理编码</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> -->
	 <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
	<%@include file="../common/common.jsp"%>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548&plugin=AMap.Walking"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    
     
    
</head>
 <style type="text/css">
      #panel {
          position: fixed;
          background-color: white;
          max-height: 90%;
          overflow-y: auto;
          top: 10px;
          right: 10px;
          width: 280px;
      }
  </style>

<body>

<input type="text" id="lon" value="${shop_lon }" />
<input type="text" id="lat" value="${shop_lat }"/>
<input type="text" id="address" value="${ADDRESS_CITY }"/>
<div id="container"></div>
<div id="panel"></div>
<script type="text/javascript">
		/* var str = encodeURI(str); */
	var str = "北京市地震局(公交站)";
	alert(encodeURI(str)); 
    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: [116.397428, 39.90923],//地图中心点
        zoom: 13 //地图显示的缩放级别
    });
    //步行导航
    var walking = new AMap.Walking({
        map: map,
        panel: "panel"
    }); 
    //根据起终点坐标规划步行路线
    walking.search([116.379028, 39.865042], [116.427281, 39.903719]);
</script>
</body>
</html>				