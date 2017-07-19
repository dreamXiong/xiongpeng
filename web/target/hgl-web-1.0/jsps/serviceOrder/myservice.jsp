<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="销售订单" />
	<tiles:put name="body" type="String">
	<c:set value="myServiceList" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/toastr.js"></script>
		<script src="${ctx}/js/hgl/orderTrackService.js"></script>
	<body>
		<form id="key_${modalName}_qryFrm" style="margin-bottom:10px;" >
		
		</form>
<!-- 内容板块开始 -->
		
		
		<div class="area me">
			<div class="main-right pull-right">
			<c:if test="${fn:length(wapOrderServiceDtoList)==0}">
				<div class="no-data"></div>	
			</c:if>
			<c:if test="${fn:length(wapOrderServiceDtoList)>0}">
				 <div id="key_myService_list">
		 			<%@include file="myServiceList.jsp" %>
		 		</div>
	 		</c:if>
		 	</div
		</div>	
	<!-- 内容板块结束 -->
		<div id="datepicker"></div>
	</body>    
	
	<!-- 改价时弹出框 -->
	<div id="shipcost" class="dialog">
	  <form>
	    <label for="name">请输入新的单价</label>
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
<!-- 底部结束 -->
	<div id="datepicker"></div>
	<form action="${ctx}/myOrderGroup/orderGroupDetail" method="post" id="orderGroupDetailForm">
		<input type="hidden" id="orderId" name="orderId" value=""/>
	</form>
	</html>
	<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	function orders(id){
		
		if(!confirm("确定接单吗?")){
			return false;
		}
		
		$.ajax({
			type:"POST",
			url:ctx+"/serviceOrder/doMasterOrder",
			data:{"id":id},
			success:function(response){
				debugger;
				toastr.success("接单成功!","操作提示");
				window.location.href = ctx+"/orderTracking/doSearchReasult?orderServiceId="+id;
			},
			error:function(){
				toastr.success("接单失败!","操作提示");
			}
		});
	};
	
	function sleep(numberMillis) {
	    var now = new Date();
	    var exitTime = now.getTime() + numberMillis;
	    while (true) {
	        now = new Date();
	        if (now.getTime() > exitTime)
	            return;
	    }
	}
		
	</script>
	</tiles:put>
</tiles:insert>