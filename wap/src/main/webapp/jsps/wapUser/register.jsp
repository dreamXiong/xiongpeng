<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>个人注册</title>
	<link rel="stylesheet" href="${ctx}/css/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/login.css">
	<link rel="stylesheet" href="${ctx}/css/app-base.css">
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/layer-mobile/layer/layer.js"></script>
	<script src="${ctx}/js/hgl/webuser.js"></script>     
</head>
<body>
	<div class="header no-boot">       
    	<a href="${ctx}/" >
    		<span class="icon-angle-left" onclick="history.back(-1);"></span>
    	</a>
    	<h2>个人注册</h2>
	</div>
	<div class="wrap">
		<div class="main">
			<form action="" name="registerForm" method="post" id="registerForm">
				<div class="form-group">
					<label>推荐码</label>
					<c:choose>
						<c:when test="${recommendCode!=null && recommendCode!=''}">
							<input type="text" class="form-control" id="recommendcode" name="recommendcode" value="${recommendCode}" readonly="readonly" onfocus="this.blur();">
						</c:when>
						<c:otherwise>
							<input type="text" class="form-control" id="recommendcode" name="recommendcode" placeholder="请输入推荐码">
						</c:otherwise>
					</c:choose>
					<span id="recommendcodeError" style="color:red;font-size:12px;"></span>				
				</div>
				<div class="form-group">
					<input type="hidden" name="openId" value="${session_openId}">
					<input type="text" class="form-control"  placeholder="请输入用户名" id="userName" name="userName" maxlength="30">
					<span id="userNameError" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group">
					<input type="text" class="form-control"  placeholder="请输入手机号码" id="mobile" name="mobile" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					<span id="mobileError" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group clearfix code">
					<div class="col-xs-8">
						<input type="text" class="form-control"  placeholder="请输入验证码" maxlength="6" id="checkCode">
						<span id="checkCodeError" style="font-size:12px;color:red;"></span>
					</div>
					<div class="col-xs-4 text-right">
						<input type="button" class="btn btn-info btn-block" id="btnSendCode" value="获取验证码" onclick="personalVodePhone()">				
					</div>
				</div>
				<div class="form-group">
					<input type="password" class="form-control"  placeholder="请输入密码" maxlength="18" id="password" name="password">
					<span id="passwordError" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group">
					<input type="password" class="form-control"  placeholder="请输入确认密码" maxlength="18" id="confirm_password">
					<span id="confirmPassword_error" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-info btn-block" id="registerBtn">注册</button>
				</div>
				<div class="form-group">
					<span>        
						<a href="doInitWorkerRegister">我是师傅</a>
					</span>
				</div>
				<c:if test="${isWeiXinBrowser == true }">
					<div>
						<p style="font-size:12px;margin-bottom:5px;">没有帐号？快速省时，一键购物！</p>	       
						<button type="button" id="weixinLoginButton" class="btn btn-block" style="background:#45C018;color:#fff;height:40px;">微信一键登录</button>                  
					</div> 	
				</c:if>					
			</form>		
		</div>
	</div>
</body>
</html>