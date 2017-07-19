<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>找师傅</title>
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
<div class="contact" style="position:fixed;z-index:1001;width:100%;-webkit-box-shadow: 1px 1px 15px rgba(0,0,0,.1);box-shadow: 1px 1px 15px rgba(0,0,0,.1);">
	<div class="but-group" style="padding-right:60px;">
		<div class="addr">		
			<span class="icon-map-marker" style="font-size:16px;"></span>
			<b><a id="addressInfo" href="${ctx}/openedRegional/cityView?cityName=${city}&actionName=master" >${city}</a></b>
		</div>      
		<div>
			<input name="serchText" id="serchText" type="text">
			<input id="serviceId" name="serviceId" type="hidden">
			<input type="hidden" value="${city}" name="city" id="city" />
			<a class="service" onclick="selectMasterInfo();" style="right:59px;" ><span class="icon-search"></span></a>
		<form method="post" id="goServiceDetail" action="${ctx}/customerIndex/goServiceDetail">
		    <input type="hidden" id="serviceIdInfo" value="" name="serviceId"/>
		</form>
		</div>
		<div style="position:absolute;line-height:30px;right:0;top:0;" id="screen">筛选<span class="icon-filter"></span>
		</div>
	</div>
</div>
<div id="masterListId" class="nav-list new-list" style="padding-top:50px;margin-top:0;padding-bottom:50px;">
	<%@include file="masterList.jsp"%>
</div>

<div class="screen-warp" >
	<div class="screen">
		<header class="text-center">
			<span class="icon-angle-left text-center back"></span>找师傅
			<a class="selectMaster">确定</a>
		</header>
		<div class="class-nav">
			<div class="class-nav-l">
				<ul>
					<c:forEach var="item" items="${s}" varStatus="index">
						<li name="${item.tstId}" id="index_${index.index}"
								<c:if test='${index.index == 0 }'>
									class='cur'
								</c:if>
							>${item.tstName}
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="class-nav-r" id="masterWorkerInfo">
				<div class="nav-r-main">
				<c:forEach var="sItem" items="${s}" varStatus="sindex">
					<div id="sindex_${sindex.index}" class="hgl-list"
						<c:if test="${sindex.index != 0 }">
							style='display:none'
						</c:if>
					>
						<h3>服务分类</h3>
					<ul class="clearfix">
						<c:forEach var="pItem" items="${sItem.wapServiceType}" varStatus="pindex">	
							<li name="${pItem.id}"><a href="javascript:;">${pItem.name}</a></li>
						</c:forEach>
					</ul>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<footer class="footer">
		<ul class="clearfix nav-bar text-center">
			<li><span class="nav-bar-a">
			<%-- <c:if test="${session_key.typeId == 114}">
				<a href="${ctx}/indexShopInfo">
			</c:if>
			
			<c:if test="${session_key.typeId != 114}">
				<a href="${ctx}/customerIndex/index">
			</c:if> --%>
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
			<li><span class="nav-bar-d"><a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png"></a></span></li>
		</ul>
	</footer>
</body>
</html>