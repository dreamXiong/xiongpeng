<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="我的技能" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">	
	
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/index.css">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/layer-mobile/layer/layer.js"></script>
	<%@include file="../common/common.jsp"%> 
	<script type="text/javascript">
	$("document").ready(function(){
		
		$("#updBtn").click(function(){
			var chk_val = [];
			$("input[name='skill']:checked").each(function(){
				chk_val.push($(this).val());
			});
			if(chk_val.length==0){
				$("#errorTips").text("请选择您会的技能");
				return;
			}
			var params = $("#serviceTypeForm").serialize();
			$.ajax({
				type:"post",
				url:"doUpdateMyServiceType",
				data:params,
				success:function(data){
					if(data=="true"){
						layer.open({
							content:'保存成功',
							time:2
						});
					}else if(data=="false"){
						layer.open({
							content:'保存失败',
							time:2
						});
					}
				}
				
			})
		});
		
		$("input[name='skill']").change(function(){
			$("#errorTips").text("");
		});
	});
	</script>
</head>	
<body>
<%@include file="../common/header.jsp"%>
<div class="container skill">
	<form action="" method="post" id="serviceTypeForm" name="serviceTypeForm">
		<c:forEach items="${serviceTypeDtos}" var="serviceTypeDto">
			<div class="skill-warp clearfix box-shadow">
				<h3 id="${serviceTypeDto.id}">${serviceTypeDto.name}</h3>
				<c:forEach items="${serviceTypeDto.childList}" var="childServiceType">
					<label for="${childServiceType.id}">
						<input type="checkbox" name="skill" id="${childServiceType.id}" value="${childServiceType.id}" <c:if test="${childServiceType.checked==1}">checked</c:if>>
						<span>${childServiceType.name}</span>
					</label>
				</c:forEach>
			</div>
		</c:forEach>	
		<span id="errorTips" style="color:red;font-size:12px;"></span>		
		<div class="clearfix">      
			<a class="btn" href="javascript:void(0)" id="updBtn" style="display:block;width:49%;text-align:center;color:#fff;background:#f60;line-height:30px;float:left;border-radius:3px;">保存</a>         
			<a class="btn" href="${ctx}/personalCenter/index" id="button" style="display:block;width:49%;text-align:center;color:#fff;background:#f60;line-height:30px;float:right;border-radius:3px;">取消</a>
		</div>			   
	</form>	                      
</div>                           
</body>        
<script>           
</script>
</html>