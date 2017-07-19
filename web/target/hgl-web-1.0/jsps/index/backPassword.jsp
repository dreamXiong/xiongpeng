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
						<label for="">用&nbsp;&nbsp; 户 &nbsp;&nbsp;名</label>
						<input type="text" id="userName" name="userName" autocomplete="off" >
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						<div id="shopNameMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">请输入用户名</span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">验&nbsp;&nbsp; 证 &nbsp;&nbsp;码</label>
						<input class="short" type="text" id="vcodeImage" name="vcodeImage" autocomplete="off" >
						<div class="text verify-img">
							<img src="${ctx}/login/authImage" height="36" width="100" alt="">
						</div>
						<span class="text-red">*</span>
						</div>
						<div id="imageVcodeMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>
					</div>
					<div class="form-col">
						<div>
						<label for="">手机验证码</label>
						<input class="short" type="text" id="vcodePhone" name="vcodePhone" autocomplete="off" >
						<button class="text text-center" id="vcodeButton" type="button" style="background:#f2f2f2" onclick="formsubmit('1');">获取验证码</button>
						<span class="text-red">*</span>
						</div>
						<div id="phoneVcodeMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>	
					</div>
				<div class="register-in text-center">
					<span id="errorInfo" style="display: none;" color="red"></span>
					<input  type="submit" type="button" onclick="return formsubmit('0')" style="display: block;padding: 8px 142px;margin: 8px 0;background: #ff0000;color: #FFF;font-size: 16px;line-height: 20px;" value="提交" />
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
$(function() {
	$(document).delegate(".verify-img","click",function(){
		 $(".verify-img img").attr("src","${ctx}/login/authImage?K="+Math.random());
	});
});

function formsubmit(flag){     
	var checkNullFlag = true;
	if($("#userName").val() == ''){
		$("#shopNameMsg .msg_content").html('<font color=red>用户名不能为空</font>');
		checkNullFlag = false;
	}else{
		$("#shopNameMsg .msg_content").html('');
	}
	if($("#vcodeImage").val() == ''){
		$("#imageVcodeMsg .msg_content").html('<font color=red>验证码不能为空</font>');
		checkNullFlag = false;
	}else{
		$("#imageVcodeMsg .msg_content").html('');
	}
	if(flag == '0'){
		if($("#vcodePhone").val() == ''){
			$("#phoneVcodeMsg .msg_content").html('<font color=red>手机验证码不能为空</font>');
			checkNullFlag = false;
		}else{
			$("#phoneVcodeMsg .msg_content").html('');
		}
	}
	if(checkNullFlag){
		if(flag == '1'){
			curCount = count;
			$.ajax({                       
				type: "POST",
				url: "${ctx}/login/getPhoneCode",
				data: "userName="+$("#userName").val()+"&verify="+$("#vcodeImage").val()+"&random="+Math.random(),
				success: function(response){     
					if(response.tipsInfo == 'success'){ 
						//设置button效果，开始计时
		        		$("#vcodeButton").attr("disabled", "true");
		        		$("#vcodeButton").text("重发验证码(" + curCount + "s)");
		        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
					}else{
						$("#errorInfo").show();
						$("#errorInfo").text(response.tipsInfo);
					}
				},
				error: function() {
					
				}   
			});
		}
		if(flag == '0'){
			$.ajax({                       
				type: "POST",
				url: "${ctx}/login/submitPhoneCheck",
				data: "userName="+$("#userName").val()+"&vcodePhone="+$("#vcodePhone").val()+"&verify="+$("#vcodeImage").val()+"&random="+Math.random(),
				success: function(response){     
					if(response.tipsInfo == 'success'){
						window.location.href = "${ctx}"+response.updateUrl;
					}else{
						$("#errorInfo").show();
						$("#errorInfo").text(response.tipsInfo);
					}
				},
				error: function() {
					
				}   
			});
		}
	}
	return false;
}

var InterValObj; //timer变量，控制时间
var count = 70; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var isMobileExisted = false;

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        $("#vcodeButton").removeAttr("disabled");//启用按钮
        $("#vcodeButton").text("重新发送验证码");
    }else {
        curCount--;
        $("#vcodeButton").text("重发验证码(" + curCount + "s)");
    }
}
</script>
</body>
</html>