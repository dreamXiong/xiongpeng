<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>惠给利登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app-base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${pageContext.request.contextPath}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>
</head>
<body>
	<div class="header">
    	<a href="javascript:;" >
    		<span class="icon-angle-left" onclick="history.back(-1);"></span>         
    	</a>
    	<h2>惠给利登录</h2>
	</div>
	<div class="wrap">
		<div class="main">
			<!-- 提示错误信息 -->
			<div class="form-group int" >   
				<div class="error text-red">${error}</div>
			</div>
			<form id="login" method="post" action="login">
				<!-- <input type="" value="" name="lon" id="lon" />
				<input type="" value="" name="lat" id="lat" /> -->
				<div class="form-group">
					<input type="text" class="form-control" id="userName" name="userName" maxlength="28" placeholder="请输入用户名/邮箱/已验证手机">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="pwd" name="pwd"  placeholder="请输入密码" maxlength="18">
					<b class="tp-btn btn-off"></b>
				</div>
				<div class="form-group">
					<!-- 错误提示给input加个border-red类名 -->
					<input type="text" class="form-control" id="verify" name="verify"  placeholder="请输入验证码" maxlength="6">
					<span class="form-group-img"><img id="verifyImg" src="${ctx}/login/authImage" alt="" width="63" height="25"></span>
				</div>
			</form>
			<div class="form-group">
				<button type="button" class="btn btn-info btn-block" >登录</button>
			</div>
			<div class="form-group">
				<span class="pull-left">
					<a href="${ctx}/register/doInitWorkerRegister">快速注册</a>
				</span>
				<span class="pull-right">
					<a href="javascript:;">找回密码</a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>
<script>

$(function(){
	$('.tp-btn').click(function(event) {
		var input = $(this).siblings('input');
		$(this).toggleClass('btn-on');
		if ($(this).hasClass('btn-on')) {
			input.attr('type','text');
		}else{
			input.attr('type','password');
		};
	});
	
	$('#verifyImg').click(function(event) {         
	 	$("#verifyImg").attr("src","${ctx}/login/authImage?K="+Math.random());
	});
});
	// 点击按钮出现红色提示
	$('button').click(function(event) {   
		var validateFlag = true;
		if($("#userName").val() == ''){
			$('#userName').toggleClass('border-red'); 
			validateFlag = false;
		}
		if($("#pwd").val() == ''){
			$('#pwd').toggleClass('border-red'); 
			validateFlag = false;
		}
		if($("#verify").val() == ''){
			$('#verify').toggleClass('border-red'); 
			validateFlag = false;
		}
		if(validateFlag == true){     
			$("#login").submit();
		}
	});
	
   function supportsGeoLocation(){
          return !!navigator.geolocation;
	}
   function getLocation(){
       navigator.geolocation.getCurrentPosition(mapIt,locationError);
    }
   function mapIt(position){ 
       var lon = position.coords.longitude;
       var lat = position.coords.latitude;
        $("#lon").val(lon);
        $("#lat").val(lat);
   }
// 定位失败时，执行的函数
   function locationError(error){
  	switch(error.code)
    {
    case error.PERMISSION_DENIED:
      alert("暂时无法定位.");
      break;
    case error.POSITION_UNAVAILABLE:
       alert("位置信息不正确.");
      break;
    case error.TIMEOUT:
       alert("请求获得用户位置超时.");
      break;
    case error.UNKNOWN_ERROR:
       alert("发生未知错误 .");
      break;
    }
  }
</script>