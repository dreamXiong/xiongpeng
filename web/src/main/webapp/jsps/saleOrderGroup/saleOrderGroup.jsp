<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="销售订单" />
	<tiles:put name="body" type="String">
	<c:set value="saleOrderGroup" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/pick.js"></script>
		<script src="${ctx}/js/hgl/saleOrderGroup.js"></script>
	<body>
	<c:set value="saleOrderGroup" var="modalName"></c:set>
<!-- 内容板块开始 -->
		<div class="area me">
			<div class="main-right pull-right">
				<form id="key_${modalName}_qryFrm" >
					<input type="hidden" value="0" id="orderType" name="orderType"/>
					<input type="hidden" value="0" id="orderState" name="orderState"/>  
					<input type="hidden" id="orderGroupId" name="orderGroupId"/>
					<input type="hidden" id="stopReason" name="stopReason"/>
					<input type="hidden" id="version" name="version"/>
					<div class="main-nav" style="border-bottom:none;">
						<div class="clear time">
							<div class="pull-left label">订单时间</div>
							<div class="pull-left navs">
								<span class="select-time pull-left">
									<input type="text" name="startTime" readonly id="from"> ~ <input type="text" name="endTime" readonly id="to" >
								</span>
								<span class="select-time-big pull-right ">
									<input type="text" name="searchText" placeholder="请输入订单号或商品信息"/>
									<button type="button" onclick="submitform();" >搜索订单</button>
								</span>
							</div>
						</div>
					<div class="clear deal">
						<div class="pull-left label">交易状态</div>
						<div id="orderStateSel" class="pull-left navs">
							<a id="allOrderGroup" class="active" name="0">全部</a>
							<a id="staus200" name="600">待确定(${orderStatesCount.staus200 })</a>
							<a id="staus202" name="602">待付款(${orderStatesCount.staus202 })</a>
							<a id="staus204" name="604">待补发货(${orderStatesCount.staus204 })</a>
							<a id="staus208" name="608">待发货(${orderStatesCount.staus208 })</a>
							<a id="staus210" name="610">待确定收货(${orderStatesCount.staus210 })</a>
 							<a id="staus218" name="618">待确定终止(${orderStatesCount.staus218 })</a>
							<a id="staus220" name="620">交易完成(${orderStatesCount.staus220 })</a>
						</div>
					</div>  
					</div>
				</form>
			 <div id="key_${modalName}_list">
	 			<%@include file="saleOrderGroupList.jsp" %>
	 		</div>
		 	</div
		</div>	
	<!-- 内容板块结束 -->
		<div id="datepicker"></div>
	</body>  
	
	<!-- 改订单单价 -->
	<div id="unitPrice" class="dialog">
	  <form>
	    <label for="name">请输入新的单价</label>
	    <input type="hidden" name="groupId" id="group_id"/><!-- 订单id -->
	    <input type="hidden" name="detailId" id="detail_id"> <!-- 订单详情id -->
		<input style="width: 400px;" name="new_price" id="new_price"/>
		<span id="moneyError" style="color:red;font-size:12px;"></span>	
	  </form>
	</div>   
	
	<!-- 改价时弹出框 -->
	<div id="shipcost" class="dialog">
	  <form>
	    <label for="name">请输入新的总价</label>
	    <input type="hidden" name="id" id="id"/>
		<input style="width: 400px;" name="totalMoney" id="totalMoney"/>
		<span id="moneyError" style="color:red;font-size:12px;"></span>	
	  </form>
	</div> 
	
	<!-- 订单取消时弹出框 -->
	<div id="dialog" class="dialog">
	  <form>
	    <label for="name">取消原因</label>
	     <textarea  style="width: 400px; height: 78px;" type="text" id="textShow" maxlength="100"></textarea>
	  </form>
	</div>
	
	<!-- 订单终止 -->
	<div id="stopOrderDivlog" class="dialog">
	  <form>
	    <label for="name">终止原因</label>
	     <textarea style="width: 400px; height: 78px;" type="text" id="stopTextShow" maxlength="100"></textarea>
	  </form>
	</div>
	
	<div id="configOrderGroupShow" class="dialog">
		<div>确定确认该订单吗？</div>
	</div>
	
	<div id="configReceivePaymentShow" class="dialog">
		<div>确定该订单已经到款吗？</div>
	</div>
<!-- 底部结束 -->
	<div id="datepicker"></div>
	<form action="${ctx}/myOrderGroup/orderGroupDetail" method="post" id="orderGroupDetailForm">
		<input type="hidden" id="orderId" name="orderId" value=""/>
	</form>
	</html>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
	</script>
	</tiles:put>
</tiles:insert>