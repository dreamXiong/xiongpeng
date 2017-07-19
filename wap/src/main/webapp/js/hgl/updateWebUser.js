$("document").ready(function(){	
	//上传图像
	$("#image").change(function(){
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var image = document.getElementById("image");
		var imageSize = image.files[0].size;
		if(imageSize>5*1024*1024){
			alert("上传图片大小不能超过5M");
			return false;
		}
		
		$("#showImage").attr("src",objUrl);
		$("#picForm").submit();
		
	});
		
	//修改用户昵称和电话号码
	$("#doSaveWebUser").click(function(){
		var isError = true;
		/*验证手机号输入是否正确*/
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		if(mobile==""){
			$("#errorTxt").text("手机号必须输入哦");
			isError = false;
			return;
		}else{
			//验证手机号输入是否正确
			if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
				$("#errorTxt").text("手机号输入不正确");
				isError = false;
				return;
			}				
			//验证手机号是否被占用
			$.ajax({
				type:"post",
				url:"doCheckMobile",
				data:"mobile="+mobile,
				async:false,
				success:function(data){
					if(data=="false"){
						$("#errorTxt").text("该手机号已经被占用");
						isError = false;
						return;
					}
				}
			});
		}	
		if(isError==false){
			return;
		}
		webUserForm.submit();
	});
		
	//手机号获得光标，清除样式
	$("#mobile").focus(function(){
		$("#errorTxt").text("");
	});
		
	/*验证旧密码是否输入正确*/
	$("#oldPassword").blur(function(){
		var password = $("#oldPassword").val();
		if(password==""){
			$("#oldPsdError").text("请您输入旧密码");
		}else{
			$.ajax({
				type:"post",
				url:"doCheckPassword",
				data:{"password":password},
				success:function(data){
					if(data=="false"){
						$("#oldPsdError").text("旧密码输入不正确");
					}
				}
			});
		}			
	});
		
	$("#oldPassword").focus(function(){
		$("#oldPsdError").text("");
	});
		
	//新密码验证
	$("#password").blur(function(){
		var password = $("#password").val();
		if(password==""){
			$("#newPasswordError").text("请您输入新密码");
		}else if(newPassword.length<6){
			$("#newPasswordError").text("新密码长度必须不小于6位");
		}
	});
		
	$("#password").focus(function(){
		$("#newPasswordError").text("");
	});
		
		
	//确认密码
	$("#cfmPassword").blur(function(){
		var password = $("#password").val();
		var cfmPassword = $("#cfmPassword").val();
		if(password!=cfmPassword){
			$("#cfmPasswordError").text("新旧密码必须输入一致");
		}
	});
		
	$("#cfmPassword").focus(function(){
		$("#cfmPasswordError").text("");
	});
	
		
	//提交修改密码
	$("#updPassword").click(function(){
		var isError = true;
		
		//旧密码的验证
		var oldPassword = $("#oldPassword").val();
		if(oldPassword==""){
			$("#oldPsdError").text("请您输入旧密码");
			isError = false;
		}else{
			$.ajax({
				type:"post",
				url:"doCheckPassword",
				data:{"password":oldPassword},
				async:false,
				success:function(data){
					if(data=="false"){
						$("#oldPsdError").text("旧密码输入不正确");
						isError = false;
					}
				}
			});	
		}
			
		//新密码的验证
		var password = $("#password").val();
		if(password==""){
			$("#newPasswordError").text("请您输入新密码");
			isError = false;
		}else if(password.length<6){
			$("#newPasswordError").text("新密码长度必须不小于6位");
			isError = false;
		}
			
		//验证确认密码
		var cfmPassword = $("#cfmPassword").val();
		if(password!=cfmPassword){
			$("#cfmPasswordError").text("新旧密码必须输入一致");
			isError = false;
		}
		
		if(isError==false){
			return;
		}
			
		psdForm.action="doUpdatePassword";
		psdForm.submit();
	});
});	
	
function getObjectURL(file){
	var url = null;	
	if (window.createObjectURL!=undefined){ // basic 
        url = window.createObjectURL(file) ; 
    } else if (window.URL!=undefined) { // mozilla(firefox) 
        url = window.URL.createObjectURL(file) ; 
    } else if (window.webkitURL!=undefined) { // webkit or chrome 
        url = window.webkitURL.createObjectURL(file) ; 
    } 
    return url ; 
}