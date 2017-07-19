<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="材料订单" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/hgl/myOrderGroup.js"></script>
</head>	
	<input type="hidden" id="orderStaus" />
	<input type="hidden" id="orderGroupId" />
	<input type="hidden" id="orderGroupVersion" />
<body class="order-body">
<form id="winxinForm" action="${ctx}/weixinPay/weixinPay" method="post">
		<input type="hidden" name="orderGroupId" id="orderIdWeiXin"  />
		<input type="hidden" name="version" id="versionWeiXin"  />
		<input type="hidden" name="orderState" id="orderStateWeiXin"  />
	</form>
<style>
/* 弹出层 */
.order-body .stopOrder{
	position: fixed;
    top: 0;
    left: 100%;
    width: 100%;
    bottom: 0;
    background: #fff;
    z-index: 1000;
    -webkit-transition: all 0.5s;
    -o-transition: all 0.5s;
    transition: all 0.5s;
    padding: 10px;
    padding-top: 48px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.order-body .stopOrder h3{
	line-height: 30px;
}
.order-body .stopOrder textarea{
	resize:none;
	outline: none;
}
.layermcont{
	padding-bottom:5px;
}

</style>
<div class="order">
	<%@include file="../common/header.jsp"%>
</div>
<div class="nav-tab-top">
	<ul class="box-shadow" style="margin-bottom:0;">
		<li id="" class="cur">全部</li>
		<li id="602">待付款</li>
		<li id="608,610">已付款</li>
		<!-- <li id="">待收货</li> -->
		<li id="612">待评价</li>
		<li id="620,606">已完成</li>
	</ul>
</div>
<div class="container container-order">
	<div class="cart-lists" style="display:block;" id="myOrderGroupListInfo">
			<%@include file="myOrderGroupList.jsp"%>
	</div>
</div>
<div class="on">
	<h3>取消原因</h3>
	<textarea name="" id="cancleReason" cols="30" rows="5" maxlength="100"></textarea>
	<div class="group-btns">
		<div>
			<button type="button" id="cancleCabolish">取消</button>
		</div>
		<div>
			<button type="button" id="cancleVerify">保存</button>
		</div>
	</div>
</div>

<div class="stopOrder">
	<h3>终止原因</h3>
	<textarea name="" id="stopReason" cols="30" rows="5" maxlength="100"></textarea>
	<div class="group-btns">
		<div>
			<button type="button" id="stopAbolish">取消</button>
		</div>
		<div>
			<button type="button" id="stopVerify">保存</button>
		</div>
	</div>
</div>

<form id="goPickDetail" method="post" action="${ctx}/pick/pickDetail">
	<input name="id" value="" type="hidden" id="productId"/>
</form>
<form action="${ctx}/wapOrderGroup/orderGroupDetail" method="post" id="orderGroupDetailForm">
	<input type="hidden" id="orderId" name="orderId" value=""/>
	<input type="hidden" id="balance" name="balance" value=""/>
</form>

<form action="${ctx}/wapOrderGroup/goEvaluate" method="post" id="goEvaluateForm">
	<input type="hidden" id="goEvaluateOrderId" name="shopId" value=""/>
	<input type="hidden" id="goEvaluateBalance" name="orderGroupId" value=""/>
</form>
	<!-- window.location.href = "goEvaluate?shopId="+shopId+"&orderGroupId="+orderGroupId; -->
</body>
</html>