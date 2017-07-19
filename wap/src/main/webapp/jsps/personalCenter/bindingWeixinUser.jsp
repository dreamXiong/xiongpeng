<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="绑定帐号" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>                       
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
	<script type="text/javascript">
	$("document").ready(function(){
		// 绑定
		$('#bindingButton').click(function(event) {
			if($("#name").val() == ''){
				layer.open({
			 		content: '用户名不能为空!',
			 		time: 2 //2秒后自动关闭
				}); 
			   return;
			}
			if($("#password").val() == ''){
				layer.open({
			 		content: '密码不能为空!',
			 		time: 2 //2秒后自动关闭
				}); 
			   return;
			}
			$.ajax({                       
				type: "POST",
				url: "${ctx}/login/bindingWeixin",
				data: $("#bindingForm").serialize(),
				success: function(response){  
					if(response.tipsInfo=='true'){
						alert('绑定成功');
						window.location.href = "${ctx}/personalCenter/index";
					}else{
						$("#errorSpan").text(response.tipsInfo);
					}
				},
				error: function() {
					
				}   
			});
		});
	});	
	</script>
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="container" style="margin-bottom:0;">
	<div class="personal box-shadow" style="height:auto;">
		<div class="user box">                     
			<div class="user-info box1">
				<div id="errorTxt" style="font-size:12px;color:red;line-height:24px;"></div>
				<form action="" method="post" id="bindingForm" >
					<span id="errorSpan" style="color: red;"></span>         
					<div style="position:relative;">
						<label style="position:absolute;height:40px;width:80px;top:0;left:0;line-height:40px;text-indent:5px;color:#ccc;">用户名</label>
						<input type="text" value="" id="name" name="name" style="padding-left:80px;height:40px;line-height:40px;" placeholder="请输入已注册的商城用户名">
					</div>
					<div style="position:relative;">                       
						<label style="position:absolute;height:30px;width:80px;top:0;left:0;line-height:40px;text-indent:5px;color:#ccc;">密码 </label>
						<input type="password" value="" id="password" name="pwd" style="padding-left:80px;height:40px;line-height:40px;" placeholder="请输入登录密码">						
					</div>      
					<div>
						<a href="javascript:void(0);" id="bindingButton" style="background:#f2000e;color:#fff;width:100%;display:block;text-align:center;border-radius:5px;line-height:40px;">立即绑定</a>
					</div>
					<p class="text-right" style="font-size:12px;margin-top: 2px;">还没有商城账号?&nbsp;<a href="${ctx}/register/doInitWebUserRegister" style="color:blue;">&nbsp;注册新帐号并绑定</a></p>	
				</form>					
			</div>
		</div>                          
	</div>
	</div>
</body>
</html>