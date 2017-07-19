<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/Font-Awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/css/user.css">
<script src="${ctx}/js/jquery/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){

		//验证手机验证码是否输入正确
		$("#checkCodeRandom").blur(function(){
			
			var checkCodeRandom = $("#checkCodeRandom").val();
			//判断验证码是否有输入
			if(checkCodeRandom==""){
				$("#checkCodeRandomError").text("请输入验证码");
				return;
			}
			
			//判断验证码是否输入正确
			$.ajax({
				type:"post",
				url:"docheckCodeRandom",
				data:{"checkCodeRandom":checkCodeRandom},
				success:function(data){
					if(data=="false"){
						$("#checkCodeRandomError").text("验证码输入错误");
					}
				}
			});
		});
		
		//验证码重新获得光标清除样式
		$("#checkCodeRandom").focus(function(){
			$("#checkCodeRandomError").text("");
		});
		
		//新密码输入是否正确
		$("#password").blur(function(){
			var password = $("#password").val();
			if(password==""){
				$("#passwordError").text("请输入新密码");
				$("#password").css("border","1px solid red");
				return;
			}
		});
		
		$("#password").focus(function(){
			$("#passwordError").text("");
			$("#password").removeAttr("style");
		});
		
		$("#password_confirm").blur(function(){
			var password_confirm = $("#password_confirm").val();
			var password = $("#password").val();
			if(password_confirm==""){
				$("#password_confirm_error").text("请输入确认密码");
				$("#password_confirm").css("border","1px solid red");
				return;
			}
			
			if(password_confirm!="" && password_confirm!=password){
				$("#password_confirm_error").text("密码输入前后不一致");
				$("#password_confirm").css("border","1px solid red");
			}
		});
		
		$("#password_confirm").focus(function(){
			$("#password_confirm_error").text("");
			$("#password_confirm").removeAttr("style");
		});
		
		//重置密码
		$("#updatePassword").click(function(){
			
			debugger;
			
			
			var isError = true;
			var checkCodeRandom = $("#checkCodeRandom").val();
			//判断验证码是否有输入
			if(checkCodeRandom==""){
				$("#checkCodeRandomError").text("请输入验证码");
				isError = false;
			}
			
			//判断验证码是否输入正确
			$.ajax({
				type:"post",
				url:"docheckCodeRandom",
				data:{"checkCodeRandom":checkCodeRandom},
				success:function(data){
					if(data=="false"){
						$("#checkCodeRandomError").text("验证码输入错误");
						isError = false;
					}
				}
			});
			
			var password = $("#password").val();
			if(password==""){
				$("#passwordError").text("请输入新密码");
				$("#password").css("border","1px solid red");
				isError = false;
			}
			
			var password_confirm = $("#password_confirm").val();
			if(password_confirm==""){
				$("#password_confirm_error").text("请输入确认密码");
				$("#password_confirm").css("border","1px solid red");
				isError = false;
			}
			
			if(password_confirm!="" && password_confirm!=password){
				$("#password_confirm_error").text("密码输入前后不一致");
				$("#password_confirm").css("border","1px solid red");
				isError = false;
			}
			
			if(isError == false){
				return;
			}
			
			var updateByMobile = $("#updateByMobile").val();
			var updateByEmail = $("#updateByEmail").val();			
			if(updateByMobile!=null && updateByEmail==null){
				updatePasswordForm.action = "doUpdatePasswordByMobile";
				updatePasswordForm.submit();
			}else if(updateByMobile==null && updateByMobile!=null){
				updatePasswordForm.action = "doUpdatePasswordByMobile";
				updatePasswordForm.submit();
			}
			
		});
		
		//重新获取手机验证码
		$("#sendMobileBtn").click(function(){
			var updateByMobile = $("#updateByMobile").val();
			$.ajax({
				type:"post",
				url:"doGetCheckCode",
				data:"",
				success:function(data){
					$("#checkCode").text(data);
				}
				
			});
		});

	});
</script>
</head>
<body>	
	<body style="margin:0px;padding:0px;">
	<p id="checkCode">验证码:${random}</p>
	<div>
		<div>
			<p style="background:#3c8dbc;height:30px;line-height:30px;font-weight:bold;padding-left:20px;">找回密码</p>
		</div>
		<div class="row" style="padding-left:35px;">
			<form action="" id="updatePasswordForm" method="post" name="updatePasswordForm">
				<input type="hidden" value="${adminUser.mobile}" id="updateByMobile">
				<input type="hidden" value="${adminUser.email}" id="updateByemail">
				<p>请输入您
					<c:choose>
						<c:when test="${adminUser.mobile!=null && adminUser.mobile!=''}">手机${adminUser.mobile}上</c:when>
						<c:when test="${adminUser.email!=null && adminUser.email!=''}">邮箱${adminUser.email}</c:when>
					</c:choose>
					收到的确认码，填写正确以后可以设置新的登录密码。
					如果您3分钟未收到确认码，请点击
					<c:choose>
						<c:when test="${adminUser.mobile!=null && adminUser.mobile!=''}"><a href="javascript:void(0)" id="sendMobileBtn">获取手机验证码</a></c:when>
						<c:when test="${adminUser.email!=null && adminUser.email!=''}"><a href="javascript:void(0)" id="sendEmailBtn">获取邮箱验证码</a></c:when>
					</c:choose>
				</p>
				<div>
					<label>用户名</label>
					<span>${adminUser.userName}</span>
				</div>
				<div>
					<label>验证码</label>
					<input type="text" name="checkCodeRandom" id="checkCodeRandom">
					<span id="checkCodeRandomError" style="font-size:12px;color:red"></span>
				</div>
				<div>
					<label>请输入新密码</label>
					<input type="password" name="password" id="password">
					<span id="passwordError" style="font-size:12px;color:red"></span>
				</div>
				<div>
					<label>请输入验证码</label>
					<input type="password" name="password_confirm" id="password_confirm">
					<span id="password_confirm_error" style="font-size:12px;color:red"></span>
				</div>
				<div>
					<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="updatePassword">重置密码</a>
				</div>
			</form>
		</div>
	</div>
</body>
</body>
</html>