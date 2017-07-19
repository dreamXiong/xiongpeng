<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="购物车" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>   
	<script src="${pageContext.request.contextPath}/js/hgl/publicDecimal.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hgl/cart.js"></script>   
</head>
<body>
<%@include file="../common/header.jsp"%>
<input type="hidden" id="shopIdInput" value="${session_key.shopId}"/>
<div class="container container-cart" style="display: ${fn:length(goodsOrderList) == 0 ? 'block' : 'none'};" id="cartNullDiv">
	<div class="empty text-center">
		<span class="icon-shopping-cart"></span>
		你的购物车空空如也T.T<br>
		<a href="${ctx}/pick/pickList">去逛逛</a>
	</div>
</div>
<div id="cartInfoDiv" style="display: ${fn:length(goodsOrderList) > 0 ? 'block' : 'none'};">
	<div class="container container-cart" id="cartDiv">
		<%@include file="shoppingCarList.jsp"  %>
	</div>
</div>	
<footer id="cartInfoFooter" class="cart-footer" style="display: ${fn:length(goodsOrderList) > 0 ? 'block' : 'none'};">
	<ul class="clearfix">
		<li style="text-align:left;padding-left:5px;">
			<label for="checkbox_all">
				<input type="checkbox" id="checkbox_all">
				全选
			</label>
		</li>
		<li>合计:<span style="color:#f2000e;" class="font-bold" id="totalCount">0</span>个</li>
		<li>
			<span style="color:#f2000e;" class="font-bold">
				<span class="dollar">¥</span>
				<span class="unit" id="totalMoneyMax">00.</span>
				<span class="mark" id="totalMoneyMin">00</span>		
			</span>元
		</li>
		<li><a href="javascript:;" id="settlementButton">去结算</a></li>
	</ul>
</footer>
</body>
</html>