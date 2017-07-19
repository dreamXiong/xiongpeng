<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="结束订单" />
	<tiles:put name="body" type="String">
	<c:set value="cashAccount" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/pick.js"></script>
		<script src="${ctx}/js/hgl/saleOrderGroup.js"></script>
	<body>
<!-- 内容板块开始 -->
		<div class="area me">        
			<div class="main-right pull-right">      
				<div class="font-bold">    
					店铺积分：<span class="text-red" style="margin-right:20px;">${tbIntegral.integral}</span>
			 	</div>      
				<div class="font-bold line-height border-bottom">
					账户总额：<span class="text-red" style="margin-right:20px;"><fmt:formatNumber value="${tbCashAccount.balance }" pattern="#,##0.00" ></fmt:formatNumber></span>
					冻结金额：<span class="text-red" style="margin-right:20px;"><fmt:formatNumber value="${tbCashAccount.freeze }" pattern="#,##0.00" ></fmt:formatNumber></span>
					可用余额：<span class="text-red" style="margin-right:20px;">${money}</span> 
					<c:if test="${money gt 0.0}">
						<button type="button" onclick="accountWithdraw();" class="btn-bg" style="margin-top:-1px;">
							<span>提现</span>	
						</button>  
					</c:if>
		 		</div>    
				<form id="key_${modalName}_qryFrm" >
					<input type="hidden" value="0" id="orderType" name="orderType"/>
					<input type="hidden" value="0" id="orderState" name="orderState"/>  
					<input type="hidden" id="orderGroupId" name="orderGroupId"/>
					<input type="hidden" id="stopReason" name="stopReason"/>
					<input type="hidden" id="version" name="version"/>
					<div class="main-nav" style="padding-bottom:0;border-bottom:0 none;">
						<div class="clear time">
							<div class="pull-left label">记录时间</div>  
							<div class="pull-left navs"> 
								<span class="select-time pull-left">
									<input type="text" name="startTime" readonly id="from"> ~ <input type="text" name="endTime" readonly id="to" >
								</span>
								<span class="select-time-big pull-right ">
									<input type="text" name="searchText" placeholder="请输入订单号"/>
									<button type="button" onclick="serachCashAccount();" >搜索</button>
								</span>
							</div>
							
						</div>
					</div>
				</form>
				
			 <div id="key_cashAccount_list">
	 			<%@include file="cashAccountList.jsp" %>
	 		</div>
		 	</div
		</div>	
	<!-- 内容板块结束 -->
		<div id="datepicker"></div>
	</body>    
	</html>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
		 /* 表单提交 */
		  function serachCashAccount(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
				$.ajax({
			        type : "POST",
			        url : ctx+'/cashAccount/serachCashAccount',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  }
		 function accountWithdraw(){
			 window.location.href = ctx+'/cashAccount/accountWithdraw';
		 }
	</script>
	</tiles:put>
</tiles:insert>