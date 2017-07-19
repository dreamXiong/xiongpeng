<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>找回密码</title>
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/register.css">
	<script src="${ctx}/jsmin/jquery/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/hgl/base.js"></script>
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
					找 回 密 码
				</div>
			</h1>
			
		</div>
	</div>
<!-- 登录logo结束 -->
<!-- 注册表单开始 -->
	<div class="register">
		<div class="area">
			<div class="register-body">
			<div class="register-nav line-height">
			</div>
				<form action="" method="post" >
					<div class="form-col">
						<label for="">新&nbsp;&nbsp; 密 &nbsp;&nbsp;码</label>
						<input type="password" id="password" name="password">
					    <i class="iconfont text-center line-height">&#xe611;</i>
						<span class="text-red">*</span>
						<div id="passwordMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">请输入密码</span>
						</div>
					</div>
				<div class="register-in text-center">
					<span id="errorInfo" style="display: none;" color="red"></span>
					<input  type="submit" type="button" onclick="return formsubmit()" style="display: block;padding: 8px 142px;margin: 8px 0;background: #ff0000;color: #FFF;font-size: 16px;line-height: 20px;" value="提交" />
				</div>
				</form>
			</div>
		</div>
	</div>
<!-- 注册表单结束 -->

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
<script type="text/javascript">
function formsubmit(){     
	if($("#password").val() == ''){
		$("#passwordMsg .msg_content").html('<font color=red>密码不能为空</font>');
		return false;
	}else{
		if($("#password").val().length >=6 && $("#password").val().length <=20){
			$.ajax({                       
				type: "POST",
				url: "${ctx}/login/modifyPassword",
				data: "password="+$("#password").val()+"&random="+Math.random(),
				success: function(response){     
					alert("修改成功");
					window.location.href = "${ctx}"+response;   
				},
				error: function() {
					
				}   
			});
		}else{
			$("#passwordMsg .msg_content").html('<font color=red>长度为6-20字符</font>');
			return false;
		}
	}
	return false;
}
   
</script>
</body>
</html>