<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>服务公司</title>
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
<%-- <div class="contact" style="position:fixed;z-index:1001;width:100%;-webkit-box-shadow: 1px 1px 15px rgba(0,0,0,.1);box-shadow: 1px 1px 15px rgba(0,0,0,.1);">
	<div class="but-group" style="padding-right:60px;">
		<div class="addr">		
			<span class="icon-map-marker" style="font-size:16px;"></span>
			<b><a id="addressInfo" href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=master" >${city}</a></b>
		</div>      
	</div>
</div> --%>
<div class="store-head">
	<div class="contact" style="position:fixed;z-index:1001;width:100%;-webkit-box-shadow: 1px 1px 15px rgba(0,0,0,.1);box-shadow: 1px 1px 15px rgba(0,0,0,.1);">
		<div class="but-group">
			<div class="addr">		
				<span class="icon-map-marker"></span>
				<b><a href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=company&typeId=${typeId}">${city}</a></b>
			</div>
			<div>
				<form id="selectForm">
					<input name="searchText" value="" id="searchInfo" type="hidden" />
					<input name="searchText" value="${typeId}" id="typeId" type="hidden" />
				</form>
				<a onclick="selectInfo();" class="service"><span class="icon-search" ></span></a>  
				<input id="searchText" value="" type="text"/>
			</div>
		</div>
	</div>
</div>
<div id="masterListId" class="nav-list new-list">
	<ul class="clearfix">
	<c:if test="${empty cList}">
		<div class="container">
			<img src="${ctx}/images/null2.png" alt="">
		</div>
	</c:if>
	<div class="dealer-nav" id="selectInfoList" >       
		<%@include file="companyServiceList.jsp"%>
	</div>	
</ul>    
</div>
	<form method="post" id="goServiceDetail" action="${ctx}/master/showdetailsInfo">
		<input type="hidden" id="serviceIdInfo" value="" name="id"/>
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
function selectInfo(){
		var searchText = $("#searchText").val();
		var typeId = $("#typeId").val();
		$.ajax({
	         type: "POST",
	         async: false,
	         url: "selectInfoList",  
	         data:{
	        	 name:searchText,
	        	 typeId:typeId,
	         },
	         success: function(response){  
	        	$("#selectInfoList").html(response);
	        	laxyImgFun();
	         }
		});
	}
	function laxyImgFun(){
	$("img.lazy").lazyload({
		threshold : 20,                               
		effect : "fadeIn",                               
	});
	var liW = $('.dealer-nav li').width();
		$('.dealer-list a').height(liW);  
	}
	
	$('#searchText').bind('keyup', function(event) {
    if (event.keyCode == "13") {
    	selectInfo(); 
    }
});

</script>
</html>