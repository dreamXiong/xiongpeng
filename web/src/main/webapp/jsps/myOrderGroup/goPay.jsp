<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="进入支付" />
<tiles:put name="body" type="String">
<c:set value="takl" var="modalName"></c:set>	
<html>
	<link rel="stylesheet" href="${ctx}/css/cart.css">
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<script src="${ctx}/js/hgl/base.js"></script>
	<script src="${ctx}/js/hgl/cart.js"></script>
<body>
<!-- 选择商品开始 -->
<div class="chart clear">
	<div class="chart-top verify"></div>
	<span>选择商品</span>
	<span>核对信息</span>
	<span class="active">付款</span>  
	<span>收货</span>
</div>
<div class="succeed2 text-center">
	<p>下单成功 ! 请在24小时内完成支付 , 过期后订单自动取消</p>
		<c:forEach var="item" items="${orderGroupList}" varStatus="i">
			<p>订单 : <a href="#">${item.orderSerialNo}</a></p>
		</c:forEach>
	<p>应支付￥${totalPayMoney }元 , 您已选择线上支付</p>
	<ul class="clear">
		<li class="active"><a href="javascript:;"><img src="images/alipay.png" height="65" width="180" alt=""></a></li>
		<li><a href="javascript:;"><img src="images/wechat.png" height="65" width="180" alt=""></a></li>
		<li><a href="javascript:;"><img src="images/line.png" height="65" width="180" alt=""></a></li>
	</ul>
	<div class="pay">
		<a class="btn1" href="javascript:;">进入支付</a>
	</div>
</div>
<!-- 选择商品结束 -->
</body>
</html>
</tiles:put>
</tiles:insert>