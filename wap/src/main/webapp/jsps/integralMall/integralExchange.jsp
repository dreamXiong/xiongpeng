<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="订单填写" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>  
	<script src="${pageContext.request.contextPath}/js/common/common.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/jquery.linkon.web.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/publicCheckFormat.js"></script>
</head>
<body>
<div class="page1">
<%@include file="../common/header.jsp"%>
<div class="container container-cart verify">
	<!-- 收货地址 -->
	<div class="box-shadow verify-title">
		<div>
			<input type="hidden" id="mainAddressId" value="${wapAddress.id }">
			<label style="color:#333;">收货人</label><input type="text" value="${wapAddress.recipient }" id="recipientInput" maxlength="5" style="font-size:14px;">
		</div>
		<div>
			<label style="color:#333;">手机号码</label><input type="number" value="${wapAddress.phone }" id="phoneInput" style="font-size:14px;">
		</div>
	</div>
	<div class="verify-info box-shadow" onclick="$('#updateMyaddressForm').submit()">
		<div class="varify-info-ico">
			<span class="icon-map-marker"></span>
		</div>
		<div class="varify-info-text" id="addressDiv">
			<c:choose>
				<c:when test="${empty wapAddress.extensionField}">
					<p>添加/选择地址</p>
				</c:when>
				<c:otherwise>
					<p>收货地址:<span>${wapAddress.extensionField }</span></p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="varify-info-return">
			<span class="icon-angle-right"></span>
		</div>
	</div>
	<!-- 订单详情 -->
	<div class="details-lists" onclick="window.location.href = '${ctx}/integralMall/getIntegralMallInfo?id=${integralMall.id }'"
	style="box-sizing:border-box;margin:0;background:#fff;">
		<div class="details-lists-img">
			<img src='${ctx }/integralMall/generateImage?id=${integralMall.id}&imgName=${integralMall.goodsImage}'/>
		</div>
		<div class="details-lists-text">
			<p class="text-hidden" style="color:#333;">${integralMall.goodsName }</p>
			<div class="clearfix" style="margin-top:0;">
				<div class="pull-left">
					<div style="color:#666;font-size:0.9rem;line-height:1.5rem;">
						<span style="color: red;">${integralMall.integral}</span> 积分        
					</div>
					<div style="font-size:0.8rem;color:#999;">
						<span>市场参考价: ${integralMall.marketAmount }&nbsp;元</span> 
					</div>                    
					                       
				</div>
				<div class="pull-right">&times;<span>${param.exchangeNumber}</span>	</div>
			</div> 
		</div>
	</div>
	<div class="box" style="background:#fff;padding:0 10px 10px;margin-top:-5px;
	-webkit-box-sizing:border-box;box-sizing:border-box;line-height:24px;">
		<div>备注：</div>
		<div class="box1">
			<input type="text" id="description" style="margin-bottom:0;" maxlength="200" autocomplete="off" placeholder="选填，可填写您的说明信息"/>	
		</div>	
	</div>	
</div>
</div>
<footer class="cart-footer varify-footer">
	<div class="pull-left" style="padding-top:0;line-height:44px;">
		<div class="varify-top">
			<span class="gap">
				兑换后支付: <span class="text-red font-bold" id="totalCount">${integralMall.payAmount*param.exchangeNumber }</span> 元
			</span>
		</div>
	</div>
	<div class="pull-right">
		<a href="#" id="submitOrder">立即兑换</a>
	</div>
</footer>
<!-- 提交订单form -->
<form id="submitOrderForm" method="post" action="${ctx }/integralMallRecord/wantPay">
	<input type="hidden" value="" id="addressId" name="addressId"/>
	<input type="hidden" value="" id="recipient" name="recipient"/>
	<input type="hidden" value="" id="phone" name="phone"/>
	<input type="hidden" value="${param.integralMallId }" id="id" name="id"/>
	<input type="hidden" value="${param.exchangeNumber}" id="exchangeNum" name="exchangeNum"/>
	<input type="hidden" value="${param.remark}" id="remark" name="remark"/>
	<input type="hidden" value="0" id="isSubmit"/>
</form>
<!-- 修改地址 -->
<form id="updateMyaddressForm" method="post" action="${ctx }/integralMall/showMyAddress"> 
	<input type="hidden" value="${wapAddress.id }" id="addressId" name="addressId"/>
	<input type="hidden" value="${param.integralMallId }" id="integralMallId" name="integralMallId"/> 
	<input type="hidden" value="${param.exchangeNumber}" id="exchangeNumber" name="exchangeNumber"/>
</form>
</body>
</html>
<script type="text/javascript">
$(document).delegate('#submitOrder','click',function(event) {  //提交订单
	if($("#submitOrderForm #isSubmit").val() == '0'){     
		var addressId = $("#mainAddressId").val();
		var recipientInput = $("#recipientInput").val();
		var phoneInput = $("#phoneInput").val();
		if(recipientInput == undefined || recipientInput == ''){
		 	layer.open({
		 		content: '请填写收货人',
		 		time: 2
			});  
			return;
		}
		if(phoneInput == undefined || phoneInput == ''){
		 	layer.open({
		 		content: '请填写手机号码',
		 		time: 2
			});  
			return;
		}
		if(checkMobile(phoneInput) || isTel(phoneInput)){
		}else{
			layer.open({
		 		content: '手机号码格式不正确',
		 		time: 2
			}); 
			return;
		}
		if(addressId == undefined || addressId == ''){
		 	layer.open({
		 		content: '请选择收货地址',
		 		time: 2
			});  
			return;
		}
		$("#submitOrderForm #addressId").val(addressId);
		$("#submitOrderForm #recipient").val(recipientInput); 
		$("#submitOrderForm #phone").val(phoneInput); 
		$("#submitOrderForm #remark").val($("#description").val());  
		$("#submitOrderForm #isSubmit").val('1');  
		$('#submitOrderForm').submit();
	}else{
		layer.open({
	 		content: '订单已提交',
	 		time: 2
		});  
	}
});
</script>