<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>厂家注册</title>
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/register.css">
	<script src="${ctx}/jsmin/jquery/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/hgl/base.js"></script>
	
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	
		
	<script>
	  	var path="${ctx}";
	  	var ctx ="${ctx}";
	  </script>
	  <script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	   <script src="${ctx}/js/hgl/shopadd.js"></script>
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
						<a href="register">我是个人</a>
						<a href="jxregister">我是商家</a>
					</div>
					<form action="cjregister" method="post" enctype="multipart/form-data">
					   <input type="hidden" name="licenseImage" id="licenseImage1_Path" />
			           <input type="hidden" name="taxpayerImage" id="taxpayerImage1_Path"/>
			           <input type="hidden" name="organizationImage" id="organizationImage1_Path"/>
			            <input type="hidden" name="logoName" id="logoName1_Path"/>
			           <input type="hidden" name="shopImage1" id="shopImage1_Path"/>
			           <input type="hidden" name="shopImage2" id="shopImage2_Path"/>
			           <input type="hidden" name="shopImage3" id="shopImage3_Path"/>
			           <input type="hidden" name="productTypeName" id="productTypeName"/>
					<div class="form-col">
						<label for="">企 业 名 称</label>
						<input type="text" id="companyName" name="companyName">
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						<div id="realnameMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">请输入企业名称</span>
						</div>
					</div>
					<div class="form-col">
						<div>
						<label for="">企 业 地 址</label>
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
						<span class="text-red">*</span>
						</div>
						<div class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>
					</div>
					<div class="form-col" >
						<div >
						<label class="visibility" for="">具体地址</label>
						<input type="text" id="regAddress" name="regAddress">
						</div>
						<div id="regAddressMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content " title="">具体地址</span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">经 营 类 别</label>
						<select id=productTypeId name="productTypeId" class="select1" style="width: 332px;">
	                          <c:forEach  var="item" items="${productTypes}" varStatus="status">
	                          <option value="${item.id}">${item.name}</option>
	                          </c:forEach>
						</select>
						<span class="text-red">*</span>
						</div>
						<div class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">品 牌 名 称</label>
						<input type="text" id="brandName" name="brandName">
						<span class="text-red">*</span>
						</div>
						<div id="brandMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">与商标相同</span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">经 营 品类</label>
						<input type="text" id="scope" name="scope">
						<span class="text-red">*</span>
						</div>
						<div class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">与营业执照相同</span>
						</div>
					</div>
					<div class="form-col">
						<span class="text-red" style="top:3px">*</span>
						<label for="">企 业 资 质</label>
						<div class="col-block">
							<p>请上传：营业执照、税务登记证、组织机构证</p>
							<div class="por">
								<div class="pull-left">
									<input type="file" class="pImg" id="licenseImage1" name="imgFile" title="营业执照"/>
									<div id="pImgOnediv ">	
					                    <img id="pImgOneShow" >
					                    <span class="iconfont">&#xe612;</span>
					                    <p class="text-center">营业执照</p>
					                 </div>
					                
								</div>
								<div class="pull-left">
									<input type="file">
									<input id="taxpayerImage1" name="imgFile" class="pImg" type="file" title="税务登记证"/>
					                  <div id="pImgTwodiv">
					                    <img id="pImgTwoShow">
					                    <span class="iconfont">&#xe612;</span>
					                    <p class="text-center">税务登记证</p>
					                  </div>
								</div>
								
								<div class="pull-left">
									<input id="organizationImage1" name="imgFile" class="pImg" type="file" title="组织机构证">
					                  <div id="pImgThreediv">
					                    <img id="pImgThreeShow">
					                    <span class="iconfont">&#xe612;</span>
										<p class="text-center">组织机构证</p>
					                  </div>
								</div>
								<div class="pull-left">
									<input id="logoName1" name="imgFile" class="pImg" type="file" title="品牌logo图">
					                  <div id="pImgFouriv">
					                    <img id="pImgFourShow">
					                    <span class="iconfont">&#xe612;</span>
										<p class="text-center">品牌logo图</p>
					                  </div>
								</div>
							</div>
						</div>
						<div id="" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>
					</div>

					<div class="form-col">
					   <span class="text-red" style="top:3px">*</span>
						<label for="">工 厂 照 片</label>
						<div class="col-block">
							<p>请至少上传一张工厂实景照片</p>
							<div class="por">
								<div class="pull-left">
									 <input type="file" class="pImg" name="imgFile" id="shopImage1" title="实景图片1">
					                  <div id="dImgOnediv" >
					                    <img id="dImgOneShow">
					                    <span class="iconfont">&#xe612;</span>
									<p class="text-center">工厂实景1</p>
					                  </div>
								</div>
								<div class="pull-left">
									<input type="file" class="pImg" name="imgFile" id="shopImage2" title="实景图片2">
					                  <div id="dImgTwodiv">
					                    <img id="dImgTwoShow">
					                    <span class="iconfont">&#xe612;</span>
									<p class="text-center">工厂实景2</p>
					                  </div>
									
								</div>
								<div class="pull-left">
									<input type="file" class="pImg" name="imgFile" id="shopImage3" title="实景图片3">
					                  <div id="dImgThreediv">
					                    <img id="dImgThreeShow">
					                    <span class="iconfont">&#xe612;</span>
									<p class="text-center">工厂实景3</p>
					                  </div>
									
								</div>
							</div>
						</div>
						<div id="" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title=""></span>
						</div>
					</div>
					
					
					<div class="form-col">
						<div>
						<label for="">企 业 法 人</label>
						<input type="text" id="legalRepresentative" name="legalRepresentative">
						<span class="text-red">*</span>
						</div>
						<div id="" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">不能为空</span>
						</div>
					</div>

					<div class="form-col">
						
						<div>
						<label for="">公 司 电 话 </label>
						<input type="text" id="companyTel" name="companyTel">
						<span class="text-red">*</span>
						</div>
						<div id="" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">不能为空</span>
						</div>
						
					</div>
					<div class="form-col">
						<div >
						<label for="">管理用户名</label>
						<input type="text" id="userName" name="userName">
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						</div>
						<div id="usernameMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content " title="">登录用户名</span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">管理员姓名</label>
						<input type="text" id="name" name="name">
						<i class="iconfont text-center line-height">&#xe610;</i>
						<span class="text-red">*</span>
						</div>
							<div id="realnameMsg" class="posi msg">
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">请与身份证一致</span>
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
							<span class="msg_pic "></span> 
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
							<span class="msg_pic "></span> 
							<span class="msg_content" title="">请手动输入确认密码</span>
						</div>
					</div>
					<div class="form-col">
						<div >
						<label for="">手 机 验 证</label>
						<input type="text" id="mobile" name="mobile"  class="short">
						<i class="iconfont text-center line-height" style="left:265px;">&#xe615;</i>
						<input class="text text-center" type="button" id="btnSendCode" style="background:#f2f2f2;font-size:12px;padding-left:0;line-height:36px;width" onclick="vcodePhoneGet('厂商');" value="获取验证码"/>
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
					<!-- <button type="button" onclick="return confirmAction('确定注册么？', ck_data);">立即注册</button> -->
					<input  type="submit" type="button" onclick="return confirmAction('确定注册么？', ck_data);" style="display: block;padding: 8px 120px;margin: 8px 0;background: #ff0000;color: #FFF;font-size: 16px;line-height: 20px;" value="立即注册" />
					<div class="less clear">
						<a href="register" class="pull-left">我是个人</a>
						<a href="jxregister" style="margin-left:11px" class="pull-right"> 我是经销商</a>
					</div>
					<p>我已有惠给利账号，立即<a class="text-red" href="${ctx}/login/index">登录</a></p>
				</div>
				</form>
				
			</div>
			<div class="attract-nav">
				<ul class="clear attract-tilte text-center">
					<li class="active">招商政策</li>
					<li>招商规则</li>
					<li>商家感言</li>
				</ul>
				<div class="slide">
					<div class="show-bg one merchant-one">
						<div class="abso">
							一个好汉三个帮，成功的公司通常因为有卓越的伙伴，惠给利的健康成长，离不开分销商伙伴的支持和帮助。无论您是创业初期的小微贸易公司，还是特定领域的行业翘楚，惠给利诚邀您与我们合作共赢，共创美化的未来。
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
					
					<div class="show-bg two none  merchant-two">
						<div class="abso">
							一定的进货量，您将获得您区域内的独家经营权，充分保证了经营区域的品牌效应，后续的海量数据分析，实时满足您的经营，后续的供货保障，让您无后顾之忧。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">招商限制</h3>
								<p>惠给利为单区域内的品牌独立建立保护，在该区域的品牌已经获得招商后其他的经销商将不能再获得经营权。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商渠道</h3>
								<p>惠给利平台会在已经建立市场的区域发布招商，经销商在获得平台认证以后将可以参与招商，在招商的时候需要完成规定的购货量。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商事项</h3>
								<p>在开始注册时，您需要提供相关的证件证明您的经营权，营业执照，法人身份证，惠给利也会需要实地考察您的店面时候满足招商要求。</p>
							</li>
						</ul>
					</div>
					<div class="show-bg three none merchant-three">
						<div class="abso">
							惠给利有众多优秀的经销商，他们为惠给利的成长带来了众多的机遇和帮助。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">肖伟</h3>
								<img src="${ctx}/images/geren.jpg" height="150" width="200">
								<p>和惠给利合作了将近10年，全部正品和贴心服务，是我选择震坤行的理由。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商渠道</h3>
								<p>惠给利平台会在已经建立市场的区域发布招商，经销商在获得平台认证以后将可以参与招商，在招商的时候需要完成规定的购货量。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商事项</h3>
								<p>在开始注册时，您需要提供相关的证件证明您的经营权，营业执照，法人身份证，惠给利也会需要实地考察您的店面时候满足招商要求</p>
							</li>
						</ul>
					</div>
				</div>
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
    var ck_data = {'companyName':'','street':'','regAddress':'','brandName':'','scope':'','organizationImage1':'','shopImage3':'','legalRepresentative':'','companyTel':'','name':'','userName':'','password':'','qrpassword':'','mobile':'','vcodeImage':'','vcodePhone':'','cbAgreement':''};
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