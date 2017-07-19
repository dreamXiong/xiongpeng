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
<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
<script src="${ctx}/js/hgl/toastr.js"></script>
<body>
	<c:set value="saleOrderGroup" var="modalName"></c:set>
	<!-- 内容板块开始 -->
	<div class="main-right pull-right skill">
		<h3 class="bg-gray" style="padding-left:10px;">我的技能</h3>	
		<form action="" method="post" id="serviceTypeForm"
			name="serviceTypeForm" style="padding-left:15px;">
			<c:forEach items="${serviceTypeDtos}" var="serviceTypeDto">
				<div class="skill-warp clearfix box-shadow">
					<h3 id="${serviceTypeDto.id}">${serviceTypeDto.name}</h3>        
					<c:forEach items="${serviceTypeDto.childList}"             
						var="childServiceType">
						<label for="${childServiceType.id}" style="margin-right:10px;"> 
							<input type="checkbox" name="skill" id="${childServiceType.id}" value="${childServiceType.id}"
							<c:if test="${childServiceType.checked==1}">checked</c:if> style="margin-right:0;">
							<span>${childServiceType.name}</span>
						</label>
					</c:forEach>
				</div>
			</c:forEach>
			<span id="errorTips" style="color: red; font-size: 12px;"></span>
			<div class="clearfix" style="margin-top:10px;">
				<%-- <a class="btn" href="javascript:void(0)" id="updBtn"
					style="display: block; width: 49%; text-align: center; color: #fff; background: #f60; line-height: 30px; float: left; border-radius: 3px;">保存</a>
				<a class="btn" href="${ctx}/personalCenter/index" id="button"
					style="display: block; width: 49%; text-align: center; color: #fff; background: #f60; line-height: 30px; float: right; border-radius: 3px;">取消</a> --%>
				<a href="javascript:void(0)" id="updBtn" class="btn-bg">
					<span>保存</span>	
				</a>	
				<a href="${ctx}/serviceOrder/index" id="button" class="btn-bg1">
					<span>返回</span>	
				</a>	
			</div>
		</form>
	</div>
</body>
		</html>
		<script type="text/javascript">
			$("document").ready(function() {

				$("#updBtn").click(function() {
					var chk_val = [];
					$("input[name='skill']:checked").each(function() {
						chk_val.push($(this).val());
					});
					/* if (chk_val.length == 0) {
						$("#errorTips").text("请选择您会的技能");
						return;
					} */
					var params = $("#serviceTypeForm").serialize();
					$.ajax({
						type : "post",
						url : "doUpdateMyServiceType",
						data : params,
						success : function(data) {
							if (data >0) {
								toastr.success("保存成功!","操作提示");
							}
						},
						error:function(){
							toastr.error("保存失败","操作提示");
						}

					})
				});

				$("input[name='skill']").change(function() {
					$("#errorTips").text("");
				});
			});
		</script>
	</tiles:put>
</tiles:insert>