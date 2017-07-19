$("document").ready(function(){	
	$('.icon-reorder').click(function(){
		$(this).siblings('.nav').toggleClass('click');
	});
	//用户必须输入推荐码，并且输入正确
	$("#recommendcode").blur(function(){
		var recommendcode = $("#recommendcode").val();
		if(recommendcode!=""){
			//推荐码不为空的话
			$.ajax({
				type:"post",
				url:"doCheckLogoCode",
				data:{"recommendcode":recommendcode},
				success:function(data){
					if(data=="false"){
						$("#recommendcodeError").text("您输入的推荐码不正确");
						$("#recommendcode").css("border","1px solid red");
					}
				}
			});
		}
	});
	
	//推荐码重新获得光标
	$("#recommendcode").focus(function(){
		$("#recommendcodeError").text("");
		$("#recommendcode").removeAttr("Style");
	});
	
	//如果注册填写了用户名，则必须验证用户名不能重复
	$("#userName").blur(function(){
		var userName = $("#userName").val();
		if(userName==""){
			$("#userNameError").text("您还没有输入用户名");
		}else{
			$.ajax({
				type:"post",
				url:"doCheckUserName",
				data:{"userName":userName},
				success:function(data){
					if(data=="false"){
						$("#userNameError").text("您输入的用户名已经被占用");
					}
				}
			});
		}
	});
	
	$("#userName").focus(function(){
		$("#userNameError").text("");
	});
	
	
	//判断手机号	
	$("#mobile").blur(function(){
		var mobile = $("#mobile").val();
		//手机号不能为空
		if(mobile==""){
			$("#mobileError").text("您还没有输入手机号");
			$("#mobile").css("border","1px solid red");				
			return;
		}
		
		//验证手机号是否输入正确
		if(mobile!="" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
			$("#mobileError").text("您输入的手机号不正确");
			$("#mobile").css("border","1px solid red");
			return;
		}
						
		//手机号不能重复注册
		$.ajax({
			type:"post",
			url:"doCheckMobile",
			data:{"mobile":mobile},
			async:false,
			success:function(data){
				if(data=="false"){
					$("#mobileError").text("您输入的手机号已经被注册");
					$("#mobile").css("border","1px solid red");
					return;
				}
			}
		});			
	});
	
	$("#mobile").blur(function(){
		var mobile = $("#mobile").val();
		//手机号不能为空
		if(mobile==""){
			$("#mobileError").text("您还没有输入手机号");
			$("#mobile").css("border","1px solid red");				
			return;
		}
			
		//验证手机号是否输入正确
		if(mobile!="" && !/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
			$("#mobileError").text("您输入的手机号不正确");
			$("#mobile").css("border","1px solid red");
			return;
		}
							
		//手机号不能重复注册
		$.ajax({
			type:"post",
			url:"doCheckMobile",
			data:{"mobile":mobile},
			async:false,
			success:function(data){
				if(data=="false"){
					$("#mobileError").text("该手机号已经被注册");
					$("#mobile").css("border","1px solid red");
					return;
				}
			}
		});			
	});
		
	//获得光标，清除手机号样式
	$("#mobile").focus(function(){
		$("#mobileError").text("");
		$("#mobile").removeAttr("style");
	});
			
	//验证码重新获得光标，清除样式
	$("#checkCode").focus(function(){
		$("#checkCodeError").text("");
		$("#checkCode").removeAttr("style");
	});
		
	//判定密码输入是否输入
	$("#password").blur(function(){
		var password = $("#password").val();
		if(password==""){
			$("#passwordError").text("您还没有输入密码");
			$("#password").css("border","1px solid red");
			return;
		}else if(password!=null && password.length<6){
			$("#passwordError").text("您输入的密码长度必须不小于6位");
			$("#password").css("border","1px solid red");
		}
	});
		
	//获得光标，清除密码样式
	$("#password").focus(function(){
		$("#passwordError").text("");
		$("#password").removeAttr("style");
	});
		
	//确认密码和密码必须一致			
	$("#confirm_password").blur(function(){
		var password = $("#password").val();
		var password_confirm = $("#confirm_password").val();
		if(password!="" && password!=password_confirm){
			$("#confirmPassword_error").text("您输入的密码前后不一致");
			$("#confirm_password").css("border","1px solid red");
		}
	});	
	
	//清除确认样式
	$("#confirm_password").focus(function(){
		$("#confirmPassword_error").text("");
		$("#confirm_password").removeAttr("style");
	});
									
	$("#registerBtn").click(function(){				
		var isError = true;
		
		//判定推荐码是否有输入
		var recommendcode = $("#recommendcode").val();
		if(recommendcode!=""){
			$.ajax({
				type:"post",
				url:"doCheckRecommendCode",
				data:{"recommendcode":recommendcode},
				async:false,
				success:function(data){
					if(data=="false"){
						$("#recommendcodeError").text("您输入的推荐码不正确");
						$("#recommendcode").css("border","1px solid red");
						isError = false;
					}
				}
			});
		}
		
		var userName = $("#userName").val();
		if(userName==""){
			$("#userNameError").text("您还没有输入用户名");
		}else{
			$.ajax({
				type:"post",
				url:"doCheckUserName",
				data:{"userName":userName},
				async:false,
				success:function(data){
					if(data=="false"){
						$("#userNameError").text("您输入的用户名已经被占用");
						isError = false;
					}
				}
			});
		}
		
		var mobile = $("#mobile").val();
		//手机号不能为空
		if(mobile==""){
			$("#mobileError").text("您还没有输入手机号");
			$("#mobile").css("border","1px solid red");
			isError = false;
		}else if(mobile !=""){
			//验证手机号是否输入正确
			if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
				$("#mobileError").text("您的手机号输入不正确");
				$("#mobile").css("border","1px solid red");
				isError = false;
			}
							
			//手机号不能重复注册
			$.ajax({
				type:"post",
				url:"doCheckMobile",
				data:{"mobile":mobile},
				aysnc:false,
				success:function(data){
					if(data=="false"){
						$("#mobileError").text("您输入的手机号已经被注册");
						$("#mobile").css("border","1px solid red");
						isError = false;
					}
				}
			});
		}
						
		//验证码不能为空
		var checkCode = $("#checkCode").val();
		if(checkCode ==""){
			$("#checkCodeError").text("您还没有输入验证码");
			$("#checkCode").css("border","1px solid red");
			isError = false;
		}else if(checkCode !=""){  //判定验证码输入是否正确
			//判断验证码输入是否正确
			$.ajax({
				type:"post",
				url:"doCheckPersonalVodeCode",
				data:"personalVodeCode="+checkCode,
				async:false,
				success:function(data){
					if(data=="false"){
						$("#checkCodeError").text("您输入的验证码不正确");
						$("#checkCode").css("border","1px solid red");
						isError = false;
					}
				}
			});			
		}
		
		//判定密码输入是否输入
		var password = $("#password").val();
		var confirm_password = $("#confirm_password").val();
		if(password==""){
			$("#passwordError").text("您还没有输入密码");
			$("#password").css("border","1px solid red");
			isError = false;
		}else if(password!="" && password.length<6){
			$("#passwordError").text("您输入的密码长度不能小于6位");
			$("#password").css("border","1px solid red");
			isError = false;
		}else if(password!="" &&(password!=confirm_password)){ //确认密码和密码必须一致			
			$("#confirmPassword_error").text("您输入的密码前后不一致");
			$("#password_confirm").css("border","1px solid red");
			isError = false;
		}
						
		if(isError == false){
			return;
		}
		registerForm.action="doRegisterUser";
		registerForm.submit();
	});		
	
	// 微信登录
	$('#weixinLoginButton').click(function(){				
		$.ajax({                       
			type: "POST",
			url: "../weixin/getOpenWeixinUrl",
			data: "recommendcode="+$("#recommendcode").val()+"&random="+Math.random(),
			success: function(response){     
				window.location.href = response;
			},
			error: function() {
				
			}   
		});
	});
});

//发送短信验证码
function personalVodePhone(){
	var isError = true;
	var mobile =$("#mobile").val();
	//验证手机号
	if(mobile==""){
		$("#mobileError").text("您还没有输入手机号");
		isError = false;
	}else{
		//验证手机号是否输入正确
		if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){ //验证手机号是否正确
			$("#mobileError").text("您输入的手机号不正确");
			$("#mobile").css("border","1px solid red");
			isError = false;
		}
		
		$.ajax({
			type:"post",
			url:"doCheckMobile",
			data:{"mobile":mobile},
			async:false,
			success:function(data){
				if(data=="false"){
					$("#mobileError").text("您输入的手机号已经被占用");
					isError = false;
				}
			}
		});
	}
	if(isError ==true){
		curCount = count;
	    $.ajax({
	        type: "POST",
	        url:'getPersonalVodePhone',
	        data:{"phoneNum":mobile},
	        success: function(response){
	        	if(response==""){
	        		//设置button效果，开始计时
	        		$("#btnSendCode").attr("disabled", "true");
	        		$("#btnSendCode").val("重发验证码(" + curCount + "s)");
	        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
	        	}else{
	        		alert(response);
	        	}
	        }
	    }); 
	}	   
}

var InterValObj; //timer变量，控制时间
var count = 70; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var isMobileExisted = false;

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
    }else {
        curCount--;
        $("#btnSendCode").val("重发验证码(" + curCount + "s)");
    }
}

