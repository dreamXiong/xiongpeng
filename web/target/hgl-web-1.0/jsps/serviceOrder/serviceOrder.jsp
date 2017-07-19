<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="接单" />
	<tiles:put name="body" type="String">
	<c:set value="serviceOrder" var="modalName"></c:set>	
	<html>
		
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/toastr.js"></script>
	<body>
	
		<form id="key_${modalName}_qryFrm" style="margin-bottom:10px;" >
		
		</form>
<!-- 内容板块开始 -->
		<div class="area me">
			<div class="main-right pull-right">
			<button type="button" class="btn-bg" 
					onclick="selectSkill();" 
				style="margin-top: -1px; margin-left: 5px;">
				<span style="margin-right: 0;">技能选择</span>
			</button>
			<c:if test="${fn:length(wapOrderServiceDtoList)==0}">
				<div class="no-data"></div>	
			</c:if>
			<c:if test="${fn:length(wapOrderServiceDtoList)>0}">
				 <div id="key_serviceOrder_list">
		 			<%@include file="serviceOrderList.jsp" %>
		 		 </div>
			</c:if>
		 	</div
		</div>	
	<!-- 内容板块结束 -->
		<div id="datepicker"></div>
	</body>    
	</html>
	<script>
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
					$("#key_serviceOrder_list").html(response);
					toastr.success("接单成功!请在我的服务中操作该笔单!","操作提示");
				},
				error:function(){
					toastr.success("接单失败!","操作提示");
				}
			});
		};
		
		function selectSkill(){
			window.location.href = ctx+"/serviceType/doSearchMySkill";
		}
	
		function show(){
			setTimeout(function(){
				window.location.href =window.location.href;
			},3000);
		}
	</script>
	</tiles:put>
</tiles:insert>