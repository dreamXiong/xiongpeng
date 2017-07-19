<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/Font-Awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/css/user.css">
<script src="${ctx}/js/jquery/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
		$("input:radio").change(function(){
			var radioCheck = $("input[name='passwordRadio']:checked").val();
			if(radioCheck == 1){
				$("#email").attr("disabled","disabled");
				$("#mobile").removeAttr("disabled");
			}else if(radioCheck == 2){
				$("#mobile").attr("disabled","disabled");
				$("#email").removeAttr("disabled");
			}
		});
		
		//验证手机号
		$("#mobile").blur(function(){
			var radioCheck = $("input[name='passwordRadio']:checked").val();
			if(radioCheck==1){
				var mobile = $("#mobile").val();
				//判断手机号是否输入
				if(mobile==""){
					$("#mobileError").text("手机号不能为空");
					$("#mobile").css("border","1px solid red");
				}
				
				//验证手机号是否输入正确
				if(mobile!="" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
					$("#mobileError").text("手机号输入不正确");
					$("#mobile").css("border","1px solid red");
				}
				
				//手机号必须为注册手机号
				$.ajax({
					type:"post",
					url:"doCheckMobile",
					data:{"mobile":mobile},
					success:function(data){
						if(data=="false"){
							$("#mobileError").text("该手机号尚未注册");
							$("#mobile").css("border","1px solid red");
						}
					}
				});
			}			
		});
		
		//手机号重新获得光标，清除样式
		$("#mobile").focus(function(){
			$("#mobileError").text("");
			$("#mobile").removeAttr("style");
		});
		
		//验证邮箱是否输入
		$("#email").blur(function(){
			var radioCheck = $("input[name='passwordRadio']:checked").val();
			if(radioCheck == 2){
				var email = $("#email").val();
				//邮箱不能为空
				if(email == ""){
					$("#emailError").text("电子邮箱不能为空");
					$("#email").css("border","1px solid red");
				}
				
				//邮箱格式必须正确
				var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if(email!="" && !reg.test(email)){
					$("#emailError").text("电子邮箱格式不正确");
					$("#email").css("border","1px solid red");
				}
				
				$.ajax({
					type:"post",
					url:"doCheckEmail",
					data:{"email":email},
					success:function(data){
						if(data=="false"){
							$("#emailError").text("该电子邮箱尚未注册");
							$("#email").css("border","1px solid red");
						}
					}
				});
			}
		});
		
		//电子邮箱重新获得光标，清除样式
		$("#email").focus(function(){
			$("#emailError").text("");
			$("#email").removeAttr("style");
		});
		
		//判断验证码输入是否正确
		$("#checkCode").blur(function(){
			var checkCode = $("#checkCode").val();
			
			$.ajax({
				type:"post",
				url:"checkCode",
				data:{"checkCode":checkCode},
				success:function(data){
					if(data=="false"){
						$("#checkCodeError").text("验证码输入错误");
					}
				}
			});
		});
		
		//验证码再次获得光标，清除样式
		$("#checkCode").focus(function(){
			$("#checkCodeError").text("");		
		});
		
		
		//点击提交后发送验证码到手机或邮箱并跳转页面
		$("#sendCode").click(function(){
			var isError = true;
			var radioCheck = $("input[name='passwordRadio']:checked").val();
			var mobile = $("#mobile").val();
			if(radioCheck == 1){  //手机号验证
				//判断手机号是否输入
				if(mobile==""){
					$("#mobileError").text("手机号不能为空");
					$("#mobile").css("border","1px solid red");
					isError = false;
				}
				
				//验证手机号是否输入正确
				if(mobile!="" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
					$("#mobileError").text("手机号输入不正确");
					$("#mobile").css("border","1px solid red");
					isError = false;
				}	
				
				//手机号必须为注册手机号
				$.ajax({
					type:"post",
					url:"doCheckMobile",
					data:{"mobile":mobile},
					success:function(data){
						if(data=="false"){
							$("#mobileError").text("该手机号尚未注册");
							$("#mobile").css("border","1px solid red");
							isError = false;
						}
					}
				});
			}else if(radioCheck == 2){  //邮箱验证
				var email = $("#email").val();
				//邮箱不能为空
				if(email == ""){
					$("#emailError").text("电子邮箱不能为空");
					$("#email").css("border","1px solid red");
					isError = false;
				}
				
				//邮箱格式必须正确
				var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if(email!="" && !reg.test(email)){
					$("#emailError").text("电子邮箱格式不正确");
					$("#email").css("border","1px solid red");
					isError = false;
				}
				
				$.ajax({
					type:"post",
					url:"doCheckEmail",
					data:{"email":email},
					success:function(data){
						if(data=="false"){
							$("#emailError").text("该电子邮箱尚未注册");
							$("#email").css("border","1px solid red");
							isError = false;
						}
					}
				});
			}
			
			if(isError == false){
				return;
			}
			
			findPasswordForm.action = "doInitUpdatePassword";
			findPasswordForm.submit();
			
		});
	});
</script>
</head>
<body style="margin:0px;padding:0px;">
	<div>
		<div>
			<p style="background:#3c8dbc;height:30px;line-height:30px;font-weight:bold;padding-left:20px;">找回密码</p>
		</div>
		<div class="row" style="padding-left:35px;">
			<form action="" id="findPasswordForm" method="post" name="findPasswordForm">
				<p>请输入以下资料中的任何一项</p>
				<div>
					<label><input type="radio" id="mobileRadio" name="passwordRadio" checked value="1">手机号码</label>
					<input type="text" name="mobile" id="mobile" maxlength="11">
					<span id="mobileError" style="font-size:12px;color:red"></span>
				</div>
				<div>
					<label><input type="radio" id="emailRadio" name="passwordRadio" value="2">电子邮箱</label>
					<input type="text" name="email" id="email" disabled>
					<span id="emailError" style="font-size:12px;color:red"></span>
				</div>
				<div>
					<label>验证码</label>
					<input type="text" id="checkCode" size="6">
					<img alt="" src="authImage">
					<span id="changeCheckCode">换一张</span>
					<span id="checkCodeError" style="font-size:12px;color:red;"></span>
				</div>
				<div>
					<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="sendCode">提交</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>