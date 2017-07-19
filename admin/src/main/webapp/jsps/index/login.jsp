<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/Font-Awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/css/user.css">
<script src="${ctx}/js/jquery/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
		$("#userName").focus(function(){
			$("#errorTips").text("");
			$("#errorTips").removeAttr("style");
		});
	});
</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">  
			<a href="../../index2.html"><b class="text-primary">惠给利11${ctx}登录</b></a>    
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<div style="text-align:center;color:red;" id="errorTips">${error}</div>
			<form action="${ctx}/login/login" method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="请输入登录名" name="userName" id="userName">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="请输入登录密码" name="pwd">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row" style="line-height: 34px">
					<div class="col-xs-6">
						<input type="checkbox" id="remember"
							style="vertical-align: middle; margin-top: -3px"> <label
							for="remember">记住密码</label>
					</div>
					<div class="col-xs-6 text-right">
						<a href="${ctx}/login/doInitFindPassword">忘记密码？</a>
					</div>
				</div>
				<!-- <div class="row" style="margin-bottom: 15px">
					<div class="col-xs-12">
						<button type="submit" class="btn btn-primary btn-block btn-flat">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
					</div>
				</div> -->
				<div class="row">
					<div class="col-xs-12">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-xs-6">
					<a href=""></a>
				</div>
			</div>
		</div>
		<!-- /.login-box-body -->
		<div class="login">
			<div class="bg"></div>
		</div>
	</div>

</body>
</html>
