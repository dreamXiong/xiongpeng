$("document").ready(function(){		
	//判定推荐码
	$("#recommendcode").blur(function(){
		var recommendcode = $("#recommendcode").val();
		if(recommendcode!=""){
			/*$("#recommendcodeError").text("推荐码不能为空");
			$("#recommendcode").css("border","1px solid red");
		}else{*/
			$.ajax({
				type:"post",
				url:"doCheckLogoCode",
				data:{"recommendcode":recommendcode},
				success:function(data){
					if(data=="false"){
						$("#recommendcodeError").text("推荐码输入错误");
						$("#recommendcode").css("border","1px solid red");
					}
				}
			});
		}
	});
	//推荐码获得光标
	$("#recommendcode").focus(function(){
		$("#recommendcodeError").text("");
		$("#recommendcode").removeAttr("style");
	});
	
	//判定用户名
	$("#userName").blur(function(){
		//判定用户名不能为空
		var userName = $("#userName").val();
		if(userName==""){
			$("#userNameError").text("用户名不能为空");
			$("#userName").css("border","1px solid red");
			return;
		}
		
		//判定用户名是否已经被注册
		$.ajax({
			type:"post",
			url:"doCheckUserName",
			data:{"userName":userName},
			success:function(data){
				if(data=="false"){
					$("#userNameError").text("该用户名已经被注册");
					$("#userName").css("border","1px solid red");
				}
			}
		});
	});
	
	//用户名获得光标，清除样式
	$("#userName").focus(function(){
		$("#userNameError").text("");
		$("#userName").removeAttr("style");
	});
	
	//判断真实姓名不能为空
	$("#name").blur(function(){
		var name = $("#name").val();
		if(name==""){
			$("#nameError").text("真实姓名不能为空");
			$("#name").css("border","1px solid red");
		}
	});
	
	//真实姓名：光标移除清除样式
	$("#name").focus(function(){
		$("#nameError").text("");
		$("#name").removeAttr("style");
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
			$("#mobileError").text("您的手机号输入不正确");
			$("#mobile").css("border","1px solid red");
			return;
		}
						
		//手机号不能重复注册
		$.ajax({
			type:"post",
			url:"doCheckMobile",
			data:{"mobile":mobile},
			success:function(data){
				if(data=="false"){
					$("#mobileError").text("该手机号已经被注册");
					$("#mobile").css("border","1px solid red");
				}else if(data=="true"){
					$("#checkCodeBtn").removeAttr("disabled");
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
			$("#passwordError").text("密码不能为空");
			$("#password").css("border","1px solid red");
			return;
		}else if(password!="" && password.length<6){
			$("#passwordError").text("密码长度不能小于6位");
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
			$("#confirm_passwordError").text("密码输入前后不一致");
			$("#confirm_password").css("border","1px solid red");
		}
	});
	
	//清除确认样式
	$("#confirm_password").focus(function(){
		$("#confirm_passwordError").text("");
		$("#confirm_password").removeAttr("style");
	});
	
	//清除技能样式
	$("input[name='skill']").click(function(){
		$("#skillError").text("");
	});			
	
	$("#registerBtn").click(function(){	
		var isError = true;
		//判定推荐码
		var recommendcode = $("#recommendcode").val();
		if(recommendcode!=""){
			$.ajax({
				type:"post",
				url:"doCheckRecommendCode",
				data:{"recommendcode":recommendcode},
				success:function(data){
					if(data=="false"){
						isError = false;
						$("#recommendcodeError").text("推荐码输入错误");
						$("#recommendcode").css("border","1px solid red");
					}
				}
			});
		}
		
		//判定用户名不能为空
		var userName = $("#userName").val();
		if(userName==""){
			$("#userNameError").text("用户名不能为空");
			$("#userName").css("border","1px solid red");
			isError =false;
		}else{
			//判定用户名是否已经被注册
			$.ajax({
				type:"post",
				url:"doCheckUserName",
				data:{"userName":userName},
				async:false,
				success:function(data){
					if(data=="false"){
						$("#userNameError").text("该用户名已经被注册");
						$("#userName").css("border","1px solid red");
						isError = false;
					}
				}
			});
		}
						
		
		//判断真实姓名不能为空
		var name = $("#name").val();
		if(name==""){
			$("#nameError").text("真实姓名不能为空");
			$("#name").css("border","1px solid red");
			isError = false;
		}
		
		var mobile = $("#mobile").val();
		//手机号不能为空
		if(mobile==""){
			$("#mobileError").text("手机号不能为空");
			$("#mobile").css("border","1px solid red");
			isError = false;
		}else if(mobile !=""){
			//验证手机号是否输入正确
			if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
				$("#mobileError").text("手机号输入不正确");
				$("#mobile").css("border","1px solid red");
				isError = false;
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
						isError = false;
					}
				}
			});
		}
						
		//验证码不能为空
		var checkCode = $("#checkCode").val();
		if(checkCode ==""){
			$("#checkCodeError").text("验证码不能为空");
			$("#checkCode").css("border","1px solid red");
			isError = false;
		}else if(checkCode !=""){
			//判断验证码输入是否正确
			$.ajax({
				type:"post",
				url:"doCheckWorkerVodeCode",
				data:"workerVodeCode="+checkCode,
				async:false,
				success:function(data){
					if(data=="false"){
						$("#checkCodeError").text("验证码输入不正确");
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
			$("#passwordError").text("密码不能为空");
			$("#password").css("border","1px solid red");
			isError = false;
		}else if(password!="" && password.length<6){
			$("#passwordError").text("密码长度不能小于6位");
			isError = false;
		}
		
		if(password!="" &&(password!=confirm_password)){
			//确认密码和密码必须一致
			$("#confirm_passwordError").text("密码输入前后不一致");
			$("#password_confirm").css("border","1px solid red");
			isError = false;
		}
		
		//判定师傅必须选择技能
		var skill = $("input[name='skill']:checked").val();
		if(skill==null ||skill==""){
			$("#skillError").text("请您选择技能");
			isError = false;
		}
							
		//判定图片有没有上传
		var image = $("#image").val();
		if(image==""){
			$("#imageError").text("本人图像必须上传");
			isError = false;
			
		}else if(image!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(image)){
			 $("#imageError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}
		
		//判定身份证有没有上传
		var imageFace = $("#imageFace").val();
		if(imageFace==""){
			$("#imageFaceError").text("本人身份证正面照片必须上传");
			isError = false;
		}else if(imageFace!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imageFace)){
			$("#imageFaceError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}
		
		//判定身份证反面照片
		var imageBack = $("#imageBack").val();
		if(imageBack==""){
			$("#imageBackError").text("本人身份证反面照片必须上传 ");
			isError = false;
		}else if(imageBack!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imageBack)){
			$("#imageBackError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}

		if(isError == false){
			return;
		}
		registerForm.action="doRegisterWorker";
		registerForm.submit();
	});		
	
});


//发送短信验证码
function workerVodePhone(){
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
	
	if(isError == true){
		curCount = count;
	    $.ajax({
	        type: "POST",
	        url:'getWorkerVodePhone',
	        data:{"phoneNum":mobile},
			async:false,
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