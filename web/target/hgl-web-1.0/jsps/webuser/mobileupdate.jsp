<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="手机号码修改"/> 
<tiles:put name="body" type="String">   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
	<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/index.css">
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/hgl/webuser.js"></script>
</head>
<body>
	<div class="main-right pull-right">
		<div class="main-title">
			<div>
				<div class="main-head clear">
					<h3>手机号码修改</h3>
				</div>
				<div class="main-info">
					<div>
						<form class="info-form" id="updateMyMobileForm" name="updateMyMobileForm" method="post">
							<div><span class="label">当前手机号码 : </span><i id="currentMobile">${webUser.mobile}</i></div>
							<div><span class="label">新手机号码 : </span><input type="text" id="new_mobile" name ="mobile" maxlength="11"><span id="newMobileError" style="color:red;font-size:12px;"></span></div>
							<div>										
								<span class="label">验证码 :</span><input type="text" id="identCode" name="identCode" size="6" maxlength="4">										
								<span class="verify-img" style="">
									<img src="authImage" height="36" width="100" alt="" style="display:inline-block;">
									<span class="cursor">换一张</span>
								</span>	
								<span id="identCodeError" style="color:red;font-size:12px;"></span>	
							</div>																		
							<div>
								<span class="label">手机验证码 :</span><input type="text" id="newMobileVerfityIpt" name="newMobileVerfityIpt" maxlength="6" size="6">
								<input type="button" id="newMobileVerfity" value="获取验证码" onclick="userNewVodePhone()">
								<span id="newVerifyTipTxt" style="color:red;font-size:12px;"></span>										
							</div>
						</form>
						<div class="btn-refer">
							<a href="javascript:void(0)" id="updMobileBtn">修改</a>
						</div>							
					</div>
				</div>
			</div>
		</div>		
	</div>	
<!-- 弹出层 -->	
<div class="myModal">
	<div class="myModal-bg"></div>
	<div class="succeed">
		<h3>提示</h3>
		<p><i class="iconfont">&#xe620;</i><span id="modalSpan"></span></p>
	</div>		
</div>	
</body>
</html>
</tiles:put>
</tiles:insert>