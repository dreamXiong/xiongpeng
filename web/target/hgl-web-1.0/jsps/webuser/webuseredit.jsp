<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="个人信息中心" />
	<tiles:put name="body" type="String">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/base.css">
		<link rel="stylesheet" href="${ctx}/css/index.css">
		<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">

		<script src="${ctx}/js/hgl/pick.js"></script>
		<script src="${ctx}/js/hgl/webuser.js"></script>
		<!-- 内容板块开始 -->
		<div class="main-right pull-right">
			<div class="main-title">
				<div>
					<div class="main-head clear bg-gray">
						<h3 class="active">基本信息修改</h3>    
						<h3>密码修改</h3>
						<h3>手机号码修改</h3>
					</div>
					<div class="main-body">
						<div class="main-info">
							<div id="baseAdminInfo">
								<form class="info-form" id="updWebUser" name="updWebUser"
									method="post">
									<div>
										<span class="label">用户名 : </span><i>${webUser.userName}</i><small>用于登录,请牢记哦~</small>
									</div>
									<div>
										<span class="label">姓名 : </span><input id="name" name="name"
											value="${webUser.name}" class="inputNotNull" type="text">
											<span id="nameError" style="color:red;font-size:12px;"></span>
									</div>
									<div>
										<span class="label">性别 : </span> <label for="male"><input
											type="radio" name="gender" id="male" value="232"
											<c:if test="${userInfo.gender==232}">checked</c:if>>男</label>
										<label for="female"><input type="radio" name="gender"
											id="female" value="234"
											<c:if test="${userInfo.gender==234}">checked</c:if>>女</label>
										<label for="unknow"><input type="radio" name="gender"
											id="unknow" value="236"
											<c:if test="${userInfo.gender==236}">checked</c:if>>保密</label>
									</div>
									<div>
										<input type="hidden" id="birthYear"
											value="${userInfo.birthYear}" /> <input type="hidden"
											id="birthMonth" value="${userInfo.birthMonth}" /> <input
											type="hidden" id="birthDay" value="${userInfo.birthDay}" /> <span
											class="label">出生日期 : </span> <select id="selYear"
											name="birthYear">
										</select> 年 <select id="selMonth" name="birthMonth">
										</select> 月 <select id="selDay" name="birthDay">
											<option value="0">日</option>
										</select> 日
									</div>
									<div>
										<span class="label">邮箱 :</span><input type="text" id="email"
											name="email" class="inputNotNull email"
											value="${webUser.email}">
									</div>
									<div class="btn-refer">
										<a href="javascript:void(0)" id="updWebUserBtn"
											onclick="javascript:void(0)">提交</a>
									</div>
								</form>
							</div>
						</div>
						<div class="main-info" style="display: none">
							<div>
								<form class="info-form" id="updPasswordForm"
									name="updPasswordForm">
									<div>
										<span class="label">用户名</span><i id="userNamesed">${webUser.userName}</i>
									</div>
									<div>
										<span class="label">请输入原密码 : </span><input type="password"
											id="oldPassword" name="oldPassword"
											class="inputNotNull input.password" class="input.password">
											<span id="oldPsdError" style="color:red;font-size:12px;"></span>
									</div>
									<div>
										<span class="label">请输入新密码 : </span><input type="password"
											id="newPassword" name="password"
											class="inputNotNull input.password">
											<span id="newPsdError" style="color:red;font-size:12px;"></span>
									</div>
									<div>
										<span class="label">请确认新密码 : </span><input type="password"
											id="cfmPassword" name="cfmPassword"
											class="inputNotNull input.password">
											<span id="cfmPsdError"></span>
									</div>
									<div class="btn-refer">
										<a href="javascript:void(0)" id="updPassword">提交</a>
									</div>
								</form>
							</div>
						</div>
						<div class="main-info" style="display: none">
							<div>
								<form class="info-form" id="mobileForm" name="mobileForm"
									method="post">
									<div id="oldResource">
										<div>
											<span class="label">当前手机号码 :</span><i id="currentMobile">${webUser.mobile}</i>
										</div>
										<div>
											<span class="label">验证码 :</span><input type="text"
												id="identCode" name="identCode" class="inputNotNull">
											   <span class="verify-img"
												style=""> <img src="authImage" height="36"
												width="100" alt="" style="display: inline-block;"> <span
												class="cursor">换一张</span>
											   </span>
											<span id="identCodeError" style="color:red;font-size:12px;"></span>
										</div>
										<div>
											<span class="label">手机验证码 :</span><input type="text"
												id="mobileVerfityIpt" name="mobileVerfityIpt"
												class="inputNotNull"> 
												<input type="button" id="mobileVerfity" onclick="userVodePhone()" value="获取验证码">
												<span id="vertifyTipTxt" style="color:red;font-size:12px;"></span> <input type="hidden"id="hiddenVerify">
										</div>

										<div class="btn-refer">
											<a href="javascript:void(0)" id="nextStep">下一步</a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 内容板块结束 -->

		<!-- 弹出层 -->
		<div class="myModal">
			<div class="myModal-bg"></div>
			<div class="succeed" style="border-radius:10px;">
				<h3>提示</h3>
				<p style="text-align: center;line-height: ">
					<i class="iconfont">&#xe620;</i><span id="modalSpan"></span>
				</p>
			</div>
		</div>
		</body>
		<script>
	var selYear = window.document.getElementById("selYear"); 
	var selMonth = window.document.getElementById("selMonth"); 
	var selDay = window.document.getElementById("selDay");
	
	// 新建一个DateSelector类的实例，将三个select对象传进去 
	new DateSelector(selYear,selMonth, selDay); 
		
</script>
		</html>
	</tiles:put>
</tiles:insert>
