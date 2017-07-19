<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="cardPage">
	<tiles:put name="title" value="购物车" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>         
	<meta charset="UTF-8">
	<title>购物车</title>
	<link rel="stylesheet" href="${ctx}/css/cart.css">
	<script src="${ctx}/js/hgl/cart.js"></script>
	<script src="${ctx}/js/hgl/publicDecimal.js"></script>
</head>
<body>
<c:if test="${fn:length(goodsOrderList) == 0 && fn:length(merchantOrderList) == 0}">
<div class="empty">
<span style="display: none;" id="cartNumber">0</span>
	<div class="clear">
		<div class="pull-left">
			<span class="iconfont">&#xe621;</span>
		</div>
		<div class="pull-left">
			<h3>您的购物车空空如也,赶紧行动！</h3>
			<p>去<a href="${ctx}/pick/index">现货选购</a>挑选您喜欢的商品吧！</p>
		</div>
	</div>
</div>
</c:if>
<c:if test="${fn:length(goodsOrderList) > 0 || fn:length(merchantOrderList) > 0}">
<div class="warp">
	<div class="area">
		<!-- 选择商品开始 -->
		<div class="chart clear">
			<div class="chart-top"></div>
			<span class="active">选择商品</span>
			<span>核对信息</span>
			<span>付款</span>
			<span>收货</span>
		</div>
		<div id="cartDiv">
			<%@include file="shoppingCarList.jsp"  %>
		</div>
		<div class="chart-sum">
			<label class="pull-left" for="check-only">
				<input type="checkbox" id="check-onlys"> 全选
			</label>
			<div class="pull-left">
				<button type="button" id="delShop1" style="padding-top: 12px;">删除所中的商品</button>
				<span style="display: none;" id="hiddenCartNumber">0</span>
				<span style="display: none;" id="hiddenCartId"></span>
				<span style="display: none;" id="hiddenOneboxCount"></span>
			</div>
			<div class="pull-right account">
				<a href="javascript:" id="settlementButton">去结算</a>
			</div>
			<div class="pull-right back">
				<a href="/web/pick/index"><<返回继续购物</a>
			</div>
			<div class="pull-right text">
				合计(物流费到付) : <span class="text-red" id="totalMoney">0.00</span> 元
			</div>
			<div class="pull-right text">
			已选商品 <span class="text-red" id="totalCount">0</span> 件
			</div>
		</div>
		<!-- 选择商品结束 -->
	</div>
</div>
</c:if>
<div id="dialog1" style="display: none;">您确定要删除该所选的商品吗？</div> 
<div id="dialog2" style="display: none;">请勾选要删除的商品</div> 
<div id="dialog3" style="display: none;">没有需要去结算的商品,请添加</div> 
<div id="dialog4" style="display: none;">  你的选购的产品<span id="msg"></span><div style="margin-top: 8px;">！如果继续提交将转为普通订单产品是否继续？</div></div> 
</body>
</html>
</tiles:put>
</tiles:insert>