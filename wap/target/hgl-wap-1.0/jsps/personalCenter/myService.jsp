<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="服务订单" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/hgl/oderService.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
	<style>
		.pox header{
			box-shadow:none;
		}
	</style>
</head>	

	<form method="post" id="blankShopInfo">
	    <input type="hidden" value="${shop_lon }" name="lon" id="lon" />
		<input type="hidden" value="${shop_lat }" name="lat" id="lat" />
		<input type="hidden" value="" name="dateTime" id="dateTime" />
		<input type="hidden" value="" name="repairmanId" id="repairmanId" />
		<input type="hidden" value="" name="orderId" id="orderId" />
	</form>
	
	
<body>
<div class="pox">    
	<%-- <%@include file="../common/header.jsp"%> --%>
	<header class="cart verify-head " style="z-index:10000;">
		<span class="icon-angle-left text-center" onclick="window.location.href = '${ctx}/personalCenter/index'"></span>
		<h3 class="text-center"><c:out value="${titleName}"/></h3>
		<span class="icon-reorder"></span>
		<%@include file="../common/topBar.jsp"%>
	</header>	
</div>
<c:if test="${!isCUS}">
<div class="nav-tab-top" style="top:43px;">
	<ul class="box-shadow" style="margin-bottom:0;">
		<li id="service1" class="cur" onclick="window.location.href='${ctx}/wapOrderService/myService'">
			<a href="javascript:;" style="color:#555;">我接的服务订单</a>
		</li>
		<li id="106" onclick="window.location.href='${ctx}/wapOrderService/myService?sfSercive=1'">
			<a href="javascript:;" style="color:#555;">我的服务订单</a>
		</li>
	</ul>
</div>
</c:if>
<c:if test="${isCUS}">
	<div class="container serve-order" style="padding-top:44px;margin-bottom:10px;">
</c:if>
<c:if test="${!isCUS}">
	<div class="container serve-order" style="padding-top:88px;margin-bottom:10px;">
</c:if>
<c:if test="${empty orderServiceDtos}">
	
	<div style="background:#F7F7F7;">
		<img src="${ctx}/images/null2.png" alt=""> 
	</div>	
	
</c:if>
	<c:forEach var="item" items="${orderServiceDtos}" varStatus="s">     
	<div class="box-shadow">  
	<c:if test="${item.orderStatus != 800}" var="isdai">        
		<div>
			<div class="box serve-title">
				<c:if test="${item.customer && item.sfUserName != null}" var="isSSFF">
					<div class="name box1">${item.sfUserName}</div>
						<div class="phone box2 text-center">                     
							<span class="icon-phone-sign"></span>
							<a href="tel:${item.sfPhone}" style="color:#969696;">${item.sfPhone}</a>
						</div>  
				</c:if> 
				<c:if test="${!isSSFF }">
					<div class="name box1">${item.userName}</div>
					<div class="phone box2 text-center">                     
						<span class="icon-phone-sign"></span>
						<a href="tel:${item.phone}" style="color:#969696;">${item.phone}</a>
					</div>
				</c:if>
				
				
				<div class="box1 text-right text-hidden" >
					${item.serciceName}
				</div>	
			</div>
			
			<div class="serve-body"  onclick="window.location.href='${ctx}/orderTrackingService/doSearchReasult?orderServiceId=${item.id}'">
			
				<div>
					&nbsp;<span class="icon-map-marker"></span>&nbsp;${item.address}  
				</div>
			</div>
		</div>	          
		</c:if>
		<c:if test="${!isdai }">        
			<div style="margin-bottom:5px;">
				正在为您安排师傅接单，请稍等。。。
			</div>	
		</c:if>  
		<h3 style="font-weight:normal;color:#999;font-size:14px;font-family:'Helvetica Neue', Helvetica, 'Microsoft YaHei', STHeiTi, sans-serif;">
			客户留言：
		</h3>  
		<div class="text-justify serve-info">
				
			<c:if test="${empty item.message}">            
				<span style="color:#ccc;">该客户没有备注信息...</span>
			</c:if>
			<c:if test="${not empty item.message}">
				${item.message}
			</c:if>
			          
		</div>
		
		
		
	
		
		
		<div class="clearfix serve-btn">
			<div class="pull-left text-red">
				${item.serviceStatus}
				<c:if test="${item.totalMoney >0}">
				<span style="font-size:0.6rem;">¥</span>${item.totalMoney}
				</c:if>
			</div>
			<div class="pull-right">
				<c:if test="${item.customer}">
					<c:if test="${item.orderStatus == 800 || item.orderStatus == 802}">
						<a href="javascript:;"  onclick="doCancelOrder(${item.id})">取消订单</a>
						<%-- <c:if test=${application_key.item.id}> --%>
						<c:set value='${item.repairmanId}_${item.id}' var="repairmanId"></c:set>
						<c:if test='${not empty application_key[repairmanId]}'>
							<a href="${ctx}/wapOrderService/masterAddressShow?wapOrderId=${item.id}">师傅位置</a>
						</c:if>
					</c:if>
					<c:if test="${item.orderStatus == 800 }">
						<a href="${ctx}/wapOrderService/goUpdateService?id=${item.id}"  onclick="">修改订单</a>
					</c:if>
					<c:if test="${item.orderStatus == 808}">
						<a href="javascript:;"  onclick="doPayService(${item.id})">线下支付</a>
						<a href="${ctx}/wapOrderService/goServiceOnlinPay?id=${item.id}" >线上支付</a>
					</c:if>
					<c:if test="${item.orderStatus == 810}">
						<a href="${ctx}/wapOrderService/goEvaluationService?id=${item.id}&typeId=${item.serciceId}" >评价</a>
					</c:if>
				</c:if>
				<c:if test="${!item.customer}">
					<c:if test="${item.orderStatus == 802}">
						<a href="javascript:;"  onclick="doAltService1(${item.id})">取消接单</a>
						<a href="javascript:;"  onclick="doOrderService1(${item.id})">开始服务</a>
						<a onclick="goBeiZhan('${item.id}','${item.repairmanId}')">开始导航</a>
					</c:if>
					<c:if test="${item.orderStatus == 804}">
						<a href="javascript:;"  onclick="doAltService2(${item.id})">服务挂起</a>
						<a href="${ctx}/wapOrderService/goServicePrice?id=${item.id}" >完成服务</a>
					</c:if>
					<c:if test="${item.orderStatus == 806}">
						<a href="javascript:;"  onclick="doAltService3(${item.id})">恢复服务</a>
					</c:if>
					<c:if test="${item.orderStatus == 810}">
						<a href="${ctx}/wapOrderService/goServicePrice?id=${item.id}" >修改价格</a>
					</c:if>
					<c:if test="${item.orderStatus == 816}">
						<a href="javascript:;"  onclick="doOrderService2(${item.id})">确认收款</a>
					</c:if>
					<c:if test="${item.orderStatus == 800}">
						<span>已取消此订单</span>
					</c:if>
				</c:if>
					<c:if test="${item.orderStatus == 812 || item.orderStatus == 814}">
						<a href="javascript:;"  onclick="doDeleteService(${item.id})">删除订单</a>
				</c:if>
			</div>
		</div>
	</div>
	</c:forEach>
</div>
</body>
<script>
	$(function(){
		if('${param.sfSercive}' == '1'){
			$("#106").addClass('cur').siblings('li').removeClass('cur');
		}else{
			$("#service1").addClass('cur').siblings('li').removeClass('cur');
		}
	});
</script>	
</html>