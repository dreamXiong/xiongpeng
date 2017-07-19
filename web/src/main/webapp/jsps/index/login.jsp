<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员登录</title>
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/user.css">
	<script src="${ctx}/jsmin/jquery/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/base.js"></script>
	
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery-ui.js"></script>
	<script src="${ctx}/jsmin/jquery/jquery-ui-1.8.16.custom.min.js"></script>
	<script src="${ctx}/jsmin/all.min.js"></script>
	
	<script src="${ctx}/js/common/publicCheckFormat.js"></script>
	<script src="${ctx}/js/common/common.js"></script>
	<script src="${ctx}/js/common/jquery.linkon.web.js"></script>
</head>
<body>

<!-- 登录logo开始 -->
	<div class="user-head">
		<div class="area">
			<h1 class="pull-left">
				<a href="${ctx}/">
					<img src="${ctx}/images/logo.png"  alt="">
				</a>
				<div class="pull-left head-title">
					欢 迎 登 录
				</div>
			</h1>
			
		</div>
	</div>
<!-- 登录logo结束 -->
<!-- 登录表单开始 -->
	<div class="user-body">
		<div class="area user-bg">
			<div class="pull-right forms">
				<h2 class="text-center text-red">会员登录</h2>
				<h2 class="text-center text-red" id="errorTips">${error}</h2>
				<form id="login" action="${ctx}/login/login" method="post">
					<div class="form-row">
						<label class="iconfont" for="use">&#xe610;</label>
						<input class="col-10" type="text" name="userName" id="use" value="${userName }" placeholder="请输入用户名" autocomplete="off" onkeydown='if(event.keyCode==13){formsubmit();}' />
					</div>
					<div class="form-row">
						<label class="iconfont" for="pas">&#xe611;</label>
						<input class="col-10"  name="pwd" type="password" id="pas" value="" placeholder="请输入密码" onkeydown='if(event.keyCode==13){formsubmit();}' />
					</div>
					<div class="form-row">
							<label for="">验证码</label>
							<input type="text" class="verify" id="verify" name="verify" onkeydown='if(event.keyCode==13){formsubmit();}'>
						<div class="verify-img"> 
							<img src="${ctx}/login/authImage" height="36" width="100" alt="">
							<span class="cursor">换一张</span>
						</div>
					</div>
					<div>
						<input type="checkbox" id="breapass" value="0" name="breapass" style="vertical-align:middle" checked="checked">
						<label for="breapass">记住账号</label>
						<a href="${ctx }/login/backPasswordUrl" class="pull-right" style="margin-top:8px">忘记密码?</a>
					</div>
				</form>
				<div class="btn-group">
					<div class="text-center">
						<a href="javascript:;" onclick="formsubmit()">登录</a>
					</div>
					<%-- <div class="text-center">
						<a href="${ctx}/register/register">个人用户注册</a>
					</div> --%>
					<div class="text-center">
						<a href="${ctx}/register/jxregister">销售商会员注册</a>
					</div>
				<%-- 	<div class="text-center">
						<a href="${ctx}/register/cjregister">厂家会员注册</a>
					</div> --%>
				</div>
				<div>个人用户注册，赠10元代金券</div>
				<div>商家用户注册，赠100元代金券</div>
				<div>厂家用户注册，赠100元代金券</div>
			</div>
		</div>
	</div>
<!-- 登录表单结束 -->

<!-- 底部开始 -->
	<div class="foot">
		<div class="area">
			<p class="font-text text-center">
				<span><a href="javascript:">购物指南</a></span>
				<span><a href="javascript:">免费注册</a></span>
				<span><a href="javascript:">联系客服</a></span>
				<span><a href="javascript:">特色服务</a></span>
				<span><a href="javascript:">招商代理</a></span>
				<span><a href="javascript:">满额赠送</a></span>
				<span><a href="javascript:">商家中心</a></span>
				<span><a href="javascript:">入驻平台</a></span>
				<span><a href="javascript:">入驻协议</a></span>
				<span><a href="javascript:">交易规则</a></span>
			</p>
			<p class="text-center" style="line-height:26px">Copyright © 2012-2019 http://www.hgeili.com All Rights Reserved.</p>
			<p class="text-center" style="line-height:26px">粤ICP备16000497号-1 立国网络技术有限公司 版权所有</p>
		</div>
	</div>
<!-- 底部结束 -->

</body>
<script>
	function formsubmit(){
		if($("#use").val() == ''){
			$('#errorTips').text('用户名不能为空'); 
			return;
		}
		if($("#pas").val() == ''){
			$('#errorTips').text('密码不能为空'); 
			return;
		}
		/* if($("#verify").val() == ''){
			$('#errorTips').text('验证码不能为空'); 
			return;
		} */
		$("#login").submit();
		//var addType = validateForm("login");
		//if(!addType){
		//	return;
		//}
	}
	
	$(function() {
		$(document).delegate(".verify-img","click",function(){
			 $(".verify-img img").attr("src","${ctx}/login/authImage?K="+Math.random());
		});
		
	});
</script>
</html>
