<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员注册</title>
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/register.css">
	<script src="${ctx}/jsmin/jquery/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/hgl/base.js"></script>
	<c:set var="ctx" value="${ctx}"/>
	
	<script>
	  	var path="${ctx}";
	  	var ctx ="${ctx}";
	  </script>
	  <script src="${ctx}/js/hgl/district.js"></script>
	<script src="${ctx}/js/hgl/register.js"></script>
</head>
<body>

<!-- 登录logo开始 -->
	<div class="user-head">
		<div class="area">
			<h1 class="pull-left">
				<a href="${ctx}">
					<img src="${ctx}/images/logo.png"  alt="">
				</a>
				<div class="pull-left head-title">
					欢 迎 注 册
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
						<!-- <a href="cjregister">我是厂家</a> -->
						
						<c:if test="${param.recommendCode !=null}" var="ishascode">
						<a href="jxregister?recommendCode=${param.recommendCode}">我是商家</a>
						</c:if>
						<c:if test="${!ishascode}">
						<a href="jxregister">我是商家</a>
						</c:if>
					</div>
				<form action="register" method="post">
					<input type="hidden" id="recommendCode" name="recommendCode" value="${param.recommendCode}">	
					<div class="form-col">
						<div >
						<label for="">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
						<input type="text" id="name" name="name">
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						</div>
							<div id="realnameMsg" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content" title="">请与身份证一致</span>
						</div>
						
					</div>
					
					<div class="form-col" >
					   <div >
						<label for="">用&nbsp;&nbsp; 户 &nbsp;&nbsp;名</label>
						<input type="text" id="userName" name="userName">
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						</div>
						<div id="usernameMsg" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content " title="">登录用户名</span>
						</div>
						
					</div>
					<div class="form-col">
					   <div >
						<label for="">登 录 密 码</label>
						<input type="password" id="password" name="password" onkeyup="checkCapital()">
						<i class="iconfont text-center line-height">&#xe611;</i>
						<span class="text-red">*</span>
						</div>
						<div id="passwordMsg" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content" title="">6-20个字符，建议由字母、数字、符号两种以上组合</span>
						</div>
						
					</div>
					<div class="form-col">
					   <div >
						<label for="">确 定 密 码</label>
						<input type="password" required name="password" id="qrpassword" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" onkeyup="checkCapital1()"/>
						<i class="iconfont text-center line-height">&#xe611;</i>
						<span class="text-red">*</span>
						</div>
						<div id="passwordMsg1" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content" title="">请手动输入确认密码</span>
						</div>
						
					</div>
					<div class="form-col" style="margin-top: 12px;">
						<div >
						<label for="">常 用 地 址</label>
							<select id="province" name="regProvince" class="select1" onchange="changeGrade();">
								 <option value="0">请选择</option>
		                          <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
		                          <option value="${item.id}">${item.name}</option>
		                          </c:forEach>
							</select>
							<select id="city" name="regCity" class="select1" onchange="changeCity();">
								
							</select>
							<select id="country" name="regCountry"  class="select1"  onchange="changeCountry();">
								
							</select>
							<select id="street" name="regStreet" class="select1 " >
								
							</select>
						</div>
						<div  class="posi msg">
							<span class="msg_pic"style="line-height: 25px;margin-top: 8px;"></span> 
							<span class="msg_content " title=""></span>
						</div>
						
					</div>
					<div class="form-col" >
						<div >
						<label class="visibility" for="">详细地址</label>
						<input type="text" id="regAddress" name="regAddress">
						</div>
						<div class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content " title="">详细地址</span>
						</div>
					</div>
					<div class="form-col" >
						<div >
						<label for="">邮 箱 地 址</label>
						<input type="text" id="email" name="email">
						<i class="iconfont text-center line-height">&#xe613;</i>
						</div>
						<div id="emailMsg" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content" title="">验证完成后，你可以直接通过邮箱找回密码</span>
						</div>
						
					</div><div class="form-col">
						<div >
						<label for="">手 机 验 证</label>
						<input type="text" id="mobile" name="mobile"  class="short">
						<i class="iconfont text-center line-height" style="left:265px;">&#xe615;</i>
						<input class="text text-center" type="button" id="btnSendCode" style="background:#f2f2f2;font-size:12px;padding-left:0;line-height:36px;width" onclick="vcodePhoneGet('账号');" value="获取验证码"/>
						<span class="text-red">*</span>
						</div>
						<div id="mobileMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">验证完成后，你可以直接通过手机登录或找回密码</span>
						</div>
					</div>
					<div class="form-col">
						<div>
						<label for="">手机验证码</label>
						<input type="text" id="vcodePhone" name="vcodePhone">
						
						<span class="text-red">*</span>
						</div>
						<div id="phoneVcodeMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>	
					</div>
					<div class="form-col">
						<div >
						<label for="">验&nbsp;&nbsp; 证 &nbsp;&nbsp;码</label>
						<input class="short" type="text" id="vcodeImage" name="vcodeImage">
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
				
				<div class="register-in text-center">
					<p><span class="text-red">*</span>标记栏目为必填信息</p>
					<div class="register-por">
						<input id="cbAgreement" type="checkbox"  name="agreest">
						<label for="yes">我已阅读并同意</label><a class="text-red" href="">《惠给利用户注册协议》</a>
						<div id="" class="posi msg">
							<span class="msg_pic"></span> 
							<span class="msg_content" title=""></span>
					</div>	
					</div>
					
					<!-- <button type="button" onclick="return confirmAction('确定要注册？',ck_data,'true');">立即注册</button> -->
					<input  type="submit" type="button" onclick="return confirmAction('确定注册么？', ck_data);" style="display: block;padding: 8px 120px;margin: 8px 0;background: #ff0000;color: #FFF;font-size: 16px;line-height: 20px;" value="立即注册" />
					
					<p>我已有惠给利账号，立即<a class="text-red" href="${ctx}/login/index">登录</a></p>
				</div>
				</form>
			</div>
			<!-- tab栏开始 -->
			<div class="attract-nav">
				<ul class="clear attract-tilte text-center">
					<li class="active">平台优势</li>
					<li>购货规则</li>
				</ul>
				<div class="slide">
					<div class="show-bg one">
						<div class="abso">
							惠给利充分理解分销商伙伴面临的市场挑战，我们愿意与您分享基于集中采购机制而获得的优惠价格，惠给利将提供给您远低于普通终端客户的产品价格，以协助您提高市场竞争力。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">价格优势</h3>
								<p>惠给利充分理解分销商伙伴面临的市场挑战，我们愿意与您分享基于集中采购机制而获得的优惠价格，惠给利将提供给您远低于普通终端客户的产品价格，以协助您提高市场竞争力。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">海量渠道</h3>
								<p>与数百家国际国内知名品牌建立了良好的合作关系，众多厂商的资源全部在惠给利上体现，构建了核心竞争优势。选择惠给利，您将不必再为寻找产品采购渠道而烦恼。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">技术支持</h3>
								<p>客户对服务的需求正逐渐超出对产品的需求，您是否正在为无法满足客户的技术咨询而苦恼？惠给利拥有自建的化学品实验室，更组建了由公司资深员工及社会行业精英组成的专家团队，竭诚为您提供在线、电话及上门的技术支持服务，解决您的后顾之忧。</p>
							</li>
						</ul>
					</div>
					
					<div class="show-bg two none">
						<div class="abso">
							惠给利充分理解分销商伙伴面临的市场挑战，我们愿意与您分享基于集中采购机制而获得的优惠价格，惠给利将提供给您远低于普通终端客户的产品价格，以协助您提高市场竞争力。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">价格优势</h3>
								<p>惠给利充分理解分销商伙伴面临的市场挑战，我们愿意与您分享基于集中采购机制而获得的优惠价格，惠给利将提供给您远低于普通终端客户的产品价格，以协助您提高市场竞争力。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">海量渠道</h3>
								<p>与数百家国际国内知名品牌建立了良好的合作关系，众多厂商的资源全部在惠给利上体现，构建了核心竞争优势。选择惠给利，您将不必再为寻找产品采购渠道而烦恼。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">技术支持</h3>
								<p>客户对服务的需求正逐渐超出对产品的需求，您是否正在为无法满足客户的技术咨询而苦恼？惠给利拥有自建的化学品实验室，更组建了由公司资深员工及社会行业精英组成的专家团队，竭诚为您提供在线、电话及上门的技术支持服务，解决您的后顾之忧。</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- tab栏结束 -->

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
var ck_data = {'name':'','userName':'','password':'','qrpassword':'','regAddress':'','email':'','mobile':'','vcodeImage':'','vcodePhone':'','cbAgreement':''};
if (!is_null('${errmsg}')) {
	var msg = '${errmsg}';
	if(msg=="验证码错误！" || msg=="vcodeImageEmpty"){
		if(msg == "vcodeImageEmpty") msg = "图片验证码错误";
		f_ck_error($("#imageVcodeMsg"), msg);
	}else if(msg=="vcodePhoneEmpty"){
    		if(msg=="vcodePhoneEmpty" ) msg = "手机验证码错误";
    		f_ck_error($("#phoneVcodeMsg"), msg);
	}else if(msg=="checkEmpty"){
		alert("请按照规范认真填写注册信息");
	}else{
		alert(msg);
	}
};
</script>
</body>
</html>