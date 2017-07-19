<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="服务订单" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%> 
</head>	
<body>
<div style="margin-bottom:10px;">
	<%@include file="../common/header.jsp"%>
</div>

<div class="container serve-order" style="margin-bottom:0;padding-bottom:50px;background:none;" >   
	<a href="${ctx}/master/index" style="border: 1px solid #66afe9;display: block;background:#66afe9;
    line-height:28px;border-radius: 3px;color: #fff;text-align:center;margin:0 10px;">发布服务需求</a>	
	<c:forEach var="item" items="${orderServiceDtos}" varStatus="s">
	<div class="box-shadow">    
		<div class="box serve-title">     
			<div class="name box1">${item.userName}</div>       
			<div class="phone box2 text-center">
				<span class="icon-phone-sign"></span> 
				<a href="tel:${item.phone}">${item.phone}</a>
			</div>
			<div class="serve box2 text-right  text-hidden">${item.serciceName}</div>
		</div>
		<div class="box serve-body">
			<div class="box3">
				${item.address}         
			</div>
			<%-- <div class="box1 text-right">
				<span class="icon-map-marker"></span>${item.distance}km
			</div> --%>
		</div>
		<div class="text-justify serve-info text-hidden">
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
			</div>
			<div class="pull-right">
				<a href="javascript:;"  onclick="doMasterOrder('${item.id}');">接单</a>
			</div>
		</div>
	</div>
	</c:forEach>

</div>

<footer class="footer">
		<ul class="clearfix nav-bar text-center">
			<li><span class="nav-bar-a">
			<%-- <c:if test="${session_key.typeId == 114}">
				<a href="${ctx}/indexShopInfo">
			</c:if>
			
			<c:if test="${session_key.typeId != 114}">
				<a href="${ctx}/customerIndex/index">
			</c:if>
			--%>
			
			<a href="${ctx}/shop/userShop">
			<img src="${ctx}/images/index.png"></a></span></li> 
			
			<li>
				<span class="nav-bar-b">
					<c:if test="${session_key.typeId == 114 || session_key.typeId == 104}">
						<a href="${ctx}/wapOrderService/service"><img src="${ctx}/images/order-red.png"></a>
					</c:if>
					
					<c:if test="${session_key.typeId != 114 && session_key.typeId != 104}">
						<a href="${ctx}/master/index"><img src="${ctx}/images/master.png"></a>
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
	function doMasterOrder(id){
		layer.open({
		    content: '确认接单么？',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	$.ajax({                       
					type: "POST",
					url: ctx+"/wapOrderService/doMasterOrder",
					data: "id="+id,
					success: function(response){     
						if(response.errcode =="0"){
							layer.close();   
							layer.open({
								content: '接单成功！',
								time: 3 //2秒后自动关闭
							});  
							window.location.href = ctx+"/orderTrackingService/doSearchReasult?orderServiceId="+id;
						}else if(response.errcode =="1"){
							layer.close();   
							layer.open({
								content: '接单失败！',
								time: 1 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}
					},
					  
				});
		    }, no: function(){
		    	layer.close();
		    }
		});
	};
</script>

</html>


