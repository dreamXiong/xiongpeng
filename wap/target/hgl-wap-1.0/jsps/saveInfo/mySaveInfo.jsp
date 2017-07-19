<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:set value="我的收藏" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${ctx}/css/index.css">
	<link rel="stylesheet" href="${ctx}/css/my.css">
	<%@include file="../common/common.jsp"%> 
	<script>
	$("document").ready(function(){
		var arr = ['你确认要删除此收藏店铺??','你确认要删除此收藏产品??','你确认要删除此收藏服务??'];
		var len = $('.collect-nav li').length;
		var str = '你确认要删除此收藏产品??';
		
		$('.collect-nav li').on('click',function(){
			var index = $(this).index();
			str = arr[index];
			$(this).addClass('active').siblings('li').removeClass('active');
			$('.show-list').eq(index).show().siblings('.show-list').hide();
		});
		
		
		
		$('.show-list').delegate('.remove','click',function(){
			var _this = $(this);
			var saveId = $(this).attr("id");
			layer.open({
		    	content: str,
		    	btn: ['确认','取消'],
		    	shadeClose: true,
			    yes: function(index){
			    	removeList(_this,saveId);
 					layer.close(index);
			    }, 
			    no: function(index){
			    	layer.close(index);
			    }
			});
		});
	});
	function removeList(obj,saveId){
		
		/*ajax删除用户收藏*/
		$.ajax({
			type:"post",
			url:"doDeleteSaveInfo",
			data:{"id":saveId},
			success:function(data){
				obj.parents('.box').remove();
			}
		});
		
	} 
</script>
</head>	
<body>
	<div class="no-shadow">
		<%@include file="../common/header.jsp"%>
	</div>
	<nav class="collect-nav box-shadow" style="margin-bottom:0;">
		<ul class="box clearfix text-center">
			<li class="box1 active">
				<a href="javascript:;">店铺</a>
			</li>
			<li class="box1 ">
				<a href="javascript:;">产品</a>
			</li>
			<li class="box1">
				<a href="javascript:;">服务</a>
			</li>		
		</ul>
	</nav>
	
	<div class="container" style="margin-top:50px;">
		<div class="show-list"  style="display: block;">
			<c:if test="${fn:length(shopList) ==0}">
				<img src="${pageContext.request.contextPath}/images/no-store.png" alt=""> 
			</c:if>
			<div class="list-info">				
				<c:forEach items="${shopList}" var="shop">
					<div class="box box-shadow" style="position:relative;">
						<img src="${ctx}/login/generateImage?id=${shop.saveId}&imgName=${shop.saveUrl}" alt="" onclick="window.location.href='${ctx}/shop/userShop?sId=${shop.saveId}'">
						<div class="box3" onclick="window.location.href='${ctx}/shop/userShop?sId=${shop.saveId}'" style="line-height:80px;"><span class="icon-home" style="color:#f2000e;"></span> ${shop.saveName}</div>
						<div class="box1" style="padding:0;line-height:80px;">
							<button type="button" onclick="window.location.href='${ctx}/personalCenter/goSaveShopInfo?sId=${shop.saveId}&recommendCode=${code}'"
							 style="background:#f2000e;color:#fff;padding:5px 8px;vertical-align: middle;">推荐</button>
						</div>
						<c:if test="${shop.bind==0}">    
							<span class="remove" id="${shop.id}"></span>
						</c:if>			          
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="show-list">              
			<c:if test="${fn:length(productList) ==0}">
				<img src="${pageContext.request.contextPath}/images/null2.png" alt=""> 
			</c:if>
			<div class="list-info">
				<c:forEach items="${productList}" var="product">
					<div class="box-shadow box" style="position:relative;">
						<img src="${ctx}/pick/generateImage?id=${product.saveId}&imgName=${product.saveUrl}" alt="" onclick="window.location.href='${ctx}/pick/pickDetail?id=${product.saveId}'">
						<div class="box1" onclick="window.location.href='${ctx}/pick/pickDetail?id=${product.saveId}'">
							<p style="height:20px;">${product.saveName}</p>     
							<p style="color:red;font-weight:bold;">${product.price} 元</p>
						</div>						
						<span class="remove" id="${product.id}" style="margin-left:50px;"></span>					
					</div>  
				</c:forEach>
			</div>
		</div>		
		<div class="show-list">
			<c:if test="${fn:length(serviceList) ==0}">
				<img src="${pageContext.request.contextPath}/images/null2.png" alt=""> 
			</c:if>
			<div class="list-info">
				<c:forEach items="${serviceList}" var="service">
					<div class="box box-shadow" style="position:relative;">
						<img src="${ctx}/customerIndex/generateImage?id=${service.saveId}&imgName=${service.saveUrl}" alt="" onclick="window.location.ref='${ctx}/customerIndex/goServiceDetail?serviceId=${service.saveId}'">
						<div class="box1" onclick="window.location.href='${ctx}/customerIndex/goServiceDetail?serviceId=${service.saveId}'">
							<p style="height:20px;">${service.saveName}</p>     
							<p style="color:red;font-weight:bold;">${service.price}元</p>
						</div>
						<span class="remove" id="${service.id}"></span>						
					</div>
				</c:forEach>				
			</div>
		</div>
	</div>	
</body>

</html>