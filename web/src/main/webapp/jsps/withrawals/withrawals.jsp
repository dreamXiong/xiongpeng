<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="店铺提现审核" />
	<tiles:put name="body" type="String">
	<c:set value="withrawals" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/pick.js"></script>
		<script src="${ctx}/js/hgl/withrawals.js"></script>
	<body>
		<div class="area me">        
			<div class="main-right pull-right">        
				<form id="key_${modalName}_qryFrm" >
					<!-- <input type="hidden" value="0" id="orderType" name="orderType"/>
					<input type="hidden" value="0" id="orderState" name="orderState"/>  
					<input type="hidden" id="orderGroupId" name="orderGroupId"/>
					<input type="hidden" id="stopReason" name="stopReason"/>
					<input type="hidden" id="version" name="version"/> -->
					<div class="main-nav" style="padding-bottom:0;border-bottom:0 none;">
						<div class="clear time">
							<div class="pull-left navs"> 
								<span class="select-time-big pull-right ">
									<input type="text" name="userName" placeholder="客户姓名"/>
									<button type="button" onclick="searchResult();" >搜索</button>
								</span>
							</div>
						</div>
					</div>
				</form>
				<input type="hidden" id="id" />  
			 <div id="key_withrawals_list">
	 			<%@include file="withrawalsList.jsp" %>
	 		</div>
		 	</div
		</div>	
	</body>    
	<div id="dialog4" class="dialog">
		<div>您确认提现吗？</div>
	</div>
	</html>
	<script>
		EcWeb.currentModalName = '${modalName}';
	
	</script>
	</tiles:put>
</tiles:insert>