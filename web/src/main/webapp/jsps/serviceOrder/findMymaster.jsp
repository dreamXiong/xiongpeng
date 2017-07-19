<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="销售订单" />
	<tiles:put name="body" type="String">
	<c:set value="serviceOrder" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/toastr.js"></script>
		<script src="${ctx}/js/hgl/orderTrackService.js"></script>
	<body>
	<c:set value="saleOrderGroup" var="modalName"></c:set>
<!-- 内容板块开始 -->
			 	<div id="findMymasterListAAAA">
	 				<%@include file="findMymasterList.jsp" %>
	 			</div>	
	</body>    
	
	
	
	</html>
	<script>
		function addMaster(){
			var masterId =$('#list').find('input:checked').val();
			var orderMymasterId =$("#orderMymasterId").val();
			debugger;
			if(masterId == null){
				toastr.warning("请先选择一个师傅!");
				return false;
			}
			debugger;
			$.ajax({
				type:'POST',
				url:ctx+'/serviceOrder/updateMaster',
				data:{"id":orderMymasterId,"pId":masterId},
				success:function(response){
					toastr.success("分配服务订单成功!","消息提示");
					show();
					
				},
				error:function(){
					toastr.error("分配服务订单失败","消息提示");
				}
			});
		};
		
		
		function show(){
			setTimeout(function(){
				window.location.href =ctx+"/serviceOrder/myService";
			},3000);
			
		}
	</script>
	</tiles:put>
</tiles:insert>