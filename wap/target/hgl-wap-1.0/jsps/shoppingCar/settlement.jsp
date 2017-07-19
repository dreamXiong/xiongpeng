<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="确认结算" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/hgl/publicDecimal.js"></script>
	<script src="${pageContext.request.contextPath}/js/hgl/settlement.js"></script>
	<script src="${pageContext.request.contextPath}/js/hgl/district.js"></script>   
	<script src="${pageContext.request.contextPath}/js/common/common.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/jquery.linkon.web.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/publicCheckFormat.js"></script> 
	<style>
		.list label{
			display: inline-block;
			width:32%;
			margin-right:0;
		}
	</style>
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
			<%@include file="address.jsp"  %>
		</div>
		<div class="varify-info-return">
			<span class="icon-angle-right"></span>
		</div>
	</div>
	<!-- 收货方式&支付方式 -->
	<%-- <div class="varify-choose box-shadow" id="payTypeDiv">
		<dl>
			<dt class="clearfix">
				<div class="pull-left title">收货方式</div>
				<div class="pull-right">
					<span class="reap">送货</span>		
					<span class="icon-angle-right"></span>
				</div>
			</dt>
			<dd>
				<label for="ziti">
					<input type="radio" id="ziti" name="goodsType" value="414" onclick="receivingWay('0')"><span>自提</span>
				</label>
				<label for="songhuo">
					<input type="radio" id="songhuo" name="goodsType" class="on" value="416" checked="checked" onclick="receivingWay('1')"><span id="text">送货</span>
				</label>
			</dd>
		</dl>
		<dl>
			<dt class="clearfix">
				<div class="pull-left title">支付方式</div>
				<div class="pull-right">
					<span class="reap">线上支付</span>
					<span class="icon-angle-right"></span>
				</div>
			</dt>
			<dd>
				<label for="lineDown">
					<input type="radio" id="lineDown" name="pay" value="242"><span>线下支付</span>
				</label>
				<label for="lineUp">
					<input type="radio" id="lineUp" name="pay" class="on" value="240" checked="checked"><span>线上支付</span>
				</label>
			</dd>  
		</dl>
		<dl>
			<dt class="clearfix">
				<div class="pull-left title">配送方式</div>
				<div class="pull-right"><span id="hidDescrption${item.shopId }">${item.description }&nbsp;</span></div>	  
			</dt>
		</dl>
		<dl>
			<dt class="clearfix">
				<div class="pull-left title">邮费</div>
				<div class="pull-right"><span class="text-red" id="freight${item.shopId }">${item.freight }1</span></div>	  
			</dt>
		</dl>
	</div> --%>
	<!-- 订单详情 -->
	<div>
		<%@include file="settlementList.jsp"  %>
	</div>
</div>


</div>
<footer class="cart-footer varify-footer">
	<div class="pull-left">
		<div class="varify-top">
			<span class="gap">
				共 <span class="text-red font-bold" id="totalCount">0</span> 件
			</span>
			<span class="text-red font-bold">
				<span class="dollar">¥</span>
				<span class="unit" id="totalMoneyMax">00.</span>
				<span class="mark" id="totalMoneyMin">00</span>
			</span>
		</div>
		<div class="varify-bottom">
			总额 : <span class="gap" id="totalMoney">¥00.00</span> 邮费 : <span>¥<span id="totalFreightMoney">0</span></span>
		</div>
	</div>
	<div class="pull-right">
		<a href="#" id="submitOrder">提交订单</a>
	</div>
</footer>
<!-- 提交订单form -->
<form id="submitOrderForm" method="post" action="${ctx }/wapOrderGroup/submitOrder">
	<input type="hidden" value="" id="cartIdList" name="cartIdList"/>
	<input type="hidden" value="" id="buyMessageList" name="buyMessageList"/>
	<input type="hidden" value="" id="addressId" name="addressId"/>
	<input type="hidden" value="" id="payTypeList" name="payTypeList"/>
	<input type="hidden" value="" id="goodsTypeList" name="goodsTypeList"/>
	<input type="hidden" value="" id="distributionList" name="distributionList"/>
	<input type="hidden" value="" id="recipient" name="recipient"/>
	<input type="hidden" value="" id="phone" name="phone"/>
	<input type="hidden" value="0" id="isSubmit"/>
</form>
<!-- 修改地址 -->
<form id="updateMyaddressForm" method="post" action="${ctx }/shoppingCar/showMyAddress">
	<input type="hidden" value="${wapAddress.id }" id="addressId" name="addressId"/>
	<input type="hidden" value="${cartIdsList }" id="cartIdsList" name="cartIdsList"/>
</form>
<!-- 跳转到产品详情 -->
<form id="productDetailForm" method="post" action="${ctx}/pick/pickDetail">
	<input type="hidden" value="" id="id" name="id"/>
</form>

</body>
</html>