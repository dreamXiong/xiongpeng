$("document").ready(function(){
			
	//Tab控制开始
	$('dd').click(function(event) {
		$('dd').removeClass('active');
		$(this).addClass('active');
	});
	$('.main-head h3').click(function(event) {
		var index = $(this).index();
		$(this).addClass('active').siblings('h3').removeClass('active');
		$('.main-body .main-info').eq(index).css('display','block').siblings('.main-info').css('display','none');
	});
	//Tab控制结束
	
	$("#newResource").hide();
					
	//修改基本信息开始	
	
	//name获得光标移除样式
	$("#updWebUser #name").focus(function(){
		$("#updWebUser #nameError").text("");
		$("#updWebUser #name").removeAttr("style");
	});
	
	//email获得光标后移除样式
	$("#updWebUser #email").focus(function(){
		$("#emailError").text("");
		$("#email").removeAttr("style");
	});
	
	
	/*3.提交修改基本信息表单*/
	$("#updWebUserBtn").click(function(){
		var isError = true;
		//验证姓名
		var name = $("#updWebUser #name").val();
		if(name==""){
			$("#updWebUser #nameError").text("您还没有输入用户名");
			isError = false;
		}
		
		
		//验证邮箱
		var email = $("#email").val();
		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(email!="" && !reg.test(email)){
			$("#emailError").text("您输入的邮箱格式不正确");
			isEmailError = false;
		}
		
		if(isError==false){
			return;
		}
		
		var params = $("#updWebUser").serialize();
		$.ajax({
			type:"post", 
			url:ctx+"/webuser/doEditWebUser",
			data:params,
			success:function(data){
				if(data=="true"){
					showModifyTips("基本信息修改成功");					
				}else{
					showModifyTips("基本信息修改失败");	
				}
			}	  
		});		
	});		
	//修改基本信息结束
			
		
		
	//重置密码开始		
	//旧密码获得光标提示信息移除
	$("#updPasswordForm #oldPassword").focus(function(){
		$("#updPasswordForm #oldPsdError").text("");
		$("#updPasswordForm #oldPassword").removeAttr("style");
	});
	
	//新密码获得光标移除提示信息
	$("#updPasswordForm #newPassword").focus(function(){
		$("#newPsdError").text("");
		$("#updPasswordForm #newPassword").removeAttr("style");
	});
	
		
	//确认密码获得光标提示信息移除
	$("#updPasswordForm #cfmPassword").focus(function(){
		$("#updPasswordForm #cfmPsdError").text("");
		$("#updPasswordForm #cfmPassword").removeAttr("style");
	});
	
	
	//点击提交按钮提交密码修改功能
	$("#updPasswordForm #updPassword").click(function(){
		var isError = true;
		//验证旧密码
		var oldPassword= $("#updPasswordForm #oldPassword").val();
		if(oldPassword==""){
			$("#updPasswordForm #oldPsdError").text("您还没有输入原始密码");
			isError = false;
		}else{
			$.ajax({
		        type:"post",
		        url:"doCheckPassword",
		        data:{"password":oldPassword},
		        async:false,
		        success: function(data){
		        	if(data=="false"){
						$("#updPasswordForm #oldPsdError").text("您输入的原始密码不正确");
						isError = false;
					}
		        }
		 	});
		}
		
		
		//新密码长度必须在6-20之前
		var newPassword = $("#updPasswordForm #newPassword").val();
		if(newPassword==""){
			$("#newPsdError").text("您还没有输入新密码");
			isError = false;
		}else if(newPassword!="" && newPassword.length<6){
			$("#newPsdError").text("密码长度必须在6-20位之间");
			isError = false;
		}else{
			var cfmPassword = $("#updPasswordForm #cfmPassword").val();			
			if(cfmPassword!=newPassword){
				$("#updPasswordForm #cfmPsdError").text("您输入的新密码和确认密码不一致");
				isError=false;
			}
		}
				
		if(isError==false){
			return;
		}	
		
		var params = $("#updPasswordForm").serialize();
		$.ajax({
			type:"post",
			url:"doUpdatePassword",
			data:params,
			success:function(data){
				if(data=="true"){
					showModifyTips("密码修改成功");				
				}else if(data=="false"){
					showModifyTips("密码修改失败");
				}else if(data=="-1"){
					showModifyTips("原密码错误，修改失败");
				}
				$("#updPasswordForm #oldPassword").val("");
				$("#updPasswordForm #newPassword").val("");
				$("#updPasswordForm #cfmPassword").val("");	
			}
		});
	});
	//重置密码结束
	
	
	
	
	//修改手机号码    开始	

	//验证码光标移除清除样式
	$("#mobileForm #identCode").focus(function(){
		$("#identCodeError").text("");
	});
	

	//2、获取旧手机验证码后点击下一步
	$("#mobileForm #nextStep").click(function(){
		var isError = true;
		var identCode = $("#identCode").val();	
		if(identCode==""){
			$("#identCodeError").text("您还没有输入验证码");
			isError = false;
		}else{
			$.ajax({
				type:"post",
				url:"checkVerify",
				data:{"identCode":identCode},
				async:false,
				success:function(data){
					if(data=="false"){
						$("#identCodeError").text("您输入的验证码不正确");
						isError = false;
					}
				}			
			});			
		}
		
		var mobileVerfityIpt =$("#mobileForm #mobileVerfityIpt").val();  //用户输入的旧手机号验证码
		if(mobileVerfityIpt==""){
			$("#mobileForm #vertifyTipTxt").text("您还没有输入手机验证码");
			isError = false;
		}else{
			$.ajax({
				type:"post",
				url:"checkUserVodeCode",
				data:"userVodeCode="+mobileVerfityIpt,
				async:false,
				success:function(data){
					if(data=="false"){
						isError = false;
						$("#mobileForm #vertifyTipTxt").text("您输入的手机验证码不正确");
					}
				}
			});
		}						
		if(isError==false){
			return;
		}		
		//如果一致则跳转到新手机号验证页面
		$("#mobileForm #identCode").val("");
		$("#mobileForm #mobileVerfityIpt").val("");
		$("#mobileForm #nextStep").attr("href","doInitUpdateMobile");
		
	});
	
	//手机验证码获得光标提示信息消失
	$("#mobileForm #mobile").keyup(function(){
		$("#mobileForm #vertifyTxt").text("");
	});
	
	
		
	//第二次验证码	
	//判断手机号格式是否正确	
	$("#new_mobile").focus(function(){
		$("#newMobileError").text("");
		$("#new_mobile").removeAttr("style");
	});
	
	
	//判断验证码
	$("#updateMyMobileForm #identCode").focus(function(){
		$("#updateMyMobileForm #identCodeError").text("");
	});
		
	//判断手机验证码是否正确
	$("#newMobileVerfityIpt").blur(function(){
		var newMobileVerfityIpt = $("#newMobileVerfityIpt").val();
		if(newMobileVerfityIpt==""){
			$("#newVerifyTipTxt").text("您还没有输入手机验证码");		
		}else{
			$.ajax({
				type:"post",
				url:"checkNewUserVodeCode",
				data:"newUserVodeCode="+newMobileVerfityIpt,
				success:function(data){
					if(data=="false"){
						$("#newVerifyTipTxt").text("验证码输入不正确");
					}
				}
			});
		}
		
	});
	
	//4、获取新手机验证码后点击修改按钮修改手机号
	$("#updMobileBtn").click(function(){
		//判断新手机号是否输入正确
		var isErrorPhone = true;
		var mobile =$("#new_mobile").val();
		if(mobile==""){
			isErrorPhone = false;
			$("#newMobileError").text("您还没有输入新的手机号");
		}else{
			if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
				$("#newMobileError").text("您输入的手机号不正确");
				isErrorPhone = false;
			}else{
				//判断该新手机号是否已经被其他用户注册
				$.ajax({
					type:"post",
					url:"doCheckMobile",
					data:{"mobile":mobile},
					async:false,
					success:function(data){
						if(data=="false"){
							$("#newMobileError").text("您输入的手机号已经被注册");
							isErrorPhone= false;
						}
					}
				});
			}						
		}
					
			
		//判断验证码是否输入正确
		var identCode = $("#updateMyMobileForm #identCode").val();
		if(identCode==""){
			$("#updateMyMobileForm #identCodeError").text("您还没有输入验证码");
			isErrorPhone = false;
		}else{
			$.ajax({
				type:"post",
				url:"checkVerify",
				data:{"identCode":identCode},
				success:function(data){
					if(data=="false"){					
						$("#updateMyMobileForm #identCodeError").text("验证码输入错误");
						isErrorPhone = false;
					}
				}			
			});
		}
		
			
		//判断手机验证码是否输入正确
		var newMobileVerfityIpt = $("#newMobileVerfityIpt").val();  //用户输入的新手机验证码
		if(newMobileVerfityIpt==""){
			$("#newVerifyTipTxt").text("您还没有输入手机验证码");
			isErrorPhone = false;
		}else{
			$.ajax({
				type:"post",
				url:"checkNewUserVodeCode",
				data:"newUserVodeCode="+newMobileVerfityIpt,
				async:false,
				success:function(data){
					if(data=="false"){
						$("#newVerifyTipTxt").text("验证码输入不正确");
						isErrorPhone = false;
					}
				}
			});
		}
		
		if(isErrorPhone == false){
			return;
		}
		
		updateMyMobileForm.action ="doUpdateMobile";
		updateMyMobileForm.submit();		
	});
	
		
	//手机验证码获得光标提示信息消失
	$("#mobileVerfityIpt").focus(function(){
		$("#vertifyTipTxt").text("");
	});
	
	$("#newMobileVerfityIpt").focus(function(){
		$("#newVerifyTipTxt").text("");
	});
	
	
	//产生验证码图片
	$(document).delegate(".verify-img","click",function(){
		 $(".verify-img img").attr("src","authImage?K="+Math.random());
	});
	
		
	//修改信息提示框
	function showModifyTips(tipsInfo){
		$('.myModal #modalSpan').html(tipsInfo);
		$('.myModal').fadeIn(500);
		timer = setTimeout(function(){
			$('.myModal').fadeOut(500);
		},2000);
	}
});
	
function DateSelector(selYear,selMonth,selDay){ 
	this.selYear = selYear; 
	this.selMonth = selMonth; 
	this.selDay = selDay; 
	this.selYear.Group = this; 
	this.selMonth.Group = this; 
	
	// 给年份、月份下拉菜单添加处理onchange事件的函数 
	if (window.document.all != null){ //IE
		this.selYear.attachEvent("onchange", DateSelector.Onchange); 
		this.selMonth.attachEvent("onchange", DateSelector.Onchange); 
	}else{ //Firefox
		this.selYear.addEventListener("change", DateSelector.Onchange, false); 
		this.selMonth.addEventListener("change", DateSelector.Onchange, false); 
	} 
	
	var birthYear = document.getElementById("birthYear").value;
	var birthMonth = document.getElementById("birthMonth").value;
	var birthDay = document.getElementById("birthDay").value;

	this.InitSelector(birthYear,birthMonth,birthDay); 			
} 

// 增加一个最小年份的属性 
DateSelector.prototype.MinYear = 1900; 
// 增加一个最大年份的属性 
DateSelector.prototype.MaxYear = (new Date()).getFullYear(); 
// 初始化年份下拉框
DateSelector.prototype.InitYearSelect = function(){ 
	// 循环添加OPION元素到年份select对象中 
	for (var i=this.MaxYear+1; i>=this.MinYear; i--){ 
		// 新建一个OPTION对象 
		var op = window.document.createElement("OPTION"); 
		// 设置OPTION对象的值 和显示内容
		if(i>this.MaxYear){
			op.value="0";
			op.innerHTML="年"; 
		}else{
			op.value = i; 
			op.innerHTML=i;
		}				
		// 添加到年份select对象 
		this.selYear.appendChild(op); 
	} 
} 

// 初始化月份下拉框
DateSelector.prototype.InitMonthSelect = function(){ 
	// 循环添加OPION元素到月份select对象中 
	for (var i = 0; i < 13; i++){ 
		// 新建一个OPTION对象 
		var op = window.document.createElement("OPTION"); 
		// 设置OPTION对象的值 和显示内容
		op.value = i; 
		if(i==0){
			op.innerHTML ="月";
		}else{
			op.innerHTML =i;
		}								 
		// 添加到月份select对象 
		this.selMonth.appendChild(op); 
	} 
}

// 根据年份与月份获取当月的天数 
DateSelector.DaysInMonth = function (year, month){ 
	var date = new Date(year, month, 0); 
	return date.getDate(); 
} 

// 初始化天数 下拉框
DateSelector.prototype.InitDaySelect = function(){ 
	// 使用parseInt函数获取当前的年份和月份 
	var year = parseInt(this.selYear.value); 
	var month = parseInt(this.selMonth.value); 

	// 获取当月的天数 
	var daysInMonth = DateSelector.DaysInMonth(year, month); 
	// 清空原有的选项 
	this.selDay.options.length = 0; 
	// 循环添加OPION元素到天数select对象中 
	for (var i = 0; i <= daysInMonth; i++) { 
	// 新建一个OPTION对象 
		var op = window.document.createElement("OPTION"); 
		// 设置OPTION对象的值 和内容
		op.value = i; 
		if(i==0){
			op.innerHTML = "日";					
		}else{
			op.innerHTML = i;
		}				
		// 添加到天数select对象 
	this.selDay.appendChild(op); 
	} 
} 
		
// 处理年份和月份onchange事件的方法，它获取事件来源对象（即selYear或selMonth） 
// 并调用它的Group对象（即DateSelector实例，请见构造函数）提供的InitDaySelect方法重新初始化天数 
// 参数e为event对象 
DateSelector.Onchange = function (e){ 
	var selector = window.document.all != null ? e.srcElement : e.target; 
	selector.Group.InitDaySelect(); 
} 

// 根据参数初始化下拉菜单选项 
DateSelector.prototype.InitSelector = function (year,month,day){ 
	// 由于外部是可以调用这个方法，因此我们在这里也要将selYear和selMonth的选项清空掉 
	// 另外因为InitDaySelect方法已经有清空天数下拉菜单，因此这里就不用重复工作了 
	this.selYear.options.length = 0; 
	this.selMonth.options.length = 0; 
	// 初始化年、月 
	this.InitYearSelect(); 
	this.InitMonthSelect(); 
	// 设置年、月初始值 
	if(year==0){
		this.selYear.selectedIndex = 0;
		this.selMonth.selectedIndex = 0;
		this.selDay.selectedIndex = 0;
	}else{
		this.selYear.selectedIndex = this.MaxYear - year+1; 
		this.selMonth.selectedIndex = month; 
		// 初始化天数 
		this.InitDaySelect(); 
		// 设置天数初始值 
		this.selDay.selectedIndex = day; 
	}
	
	function allowEdit(nodeId){
		if(nodeId == "nameEdit"){
			document.getElementById("name").readonly=false;
		}
	} 
} 


//发送短信验证码
function userVodePhone(){
	var mobile =$("#currentMobile").text().trim();
	curCount = count;
    $.ajax({
        type:"post",
        url:'getUserVodePhone',
        data:{"phoneNum":mobile},
        success: function(response){
        	if(response==""){
        		//设置button效果，开始计时
        		$("#mobileVerfity").attr("disabled", "true");
        		$("#mobileVerfity").val("重发验证码(" + curCount + "s)");
        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        	}else{
        		alert(response);
        	}
        }
    });    
}

var InterValObj; //timer变量，控制时间
var count = 70; //间隔函数，1秒执行
var curCount;//当前剩余秒数

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        $("#mobileVerfity").removeAttr("disabled");//启用按钮
        $("#mobileVerfity").val("重新发送验证码");
    }else {
        curCount--;
        $("#mobileVerfity").val("重发验证码(" + curCount + "s)");
    }
}



var InterNewValObj; //timer变量，控制时间
var countNew = 70; //间隔函数，1秒执行
var curCountNew;//当前剩余秒数
//发送短信验证码
function userNewVodePhone(){
	var isError = true;
	var mobile =$("#new_mobile").val().trim();
	
	//判断新手机号是否为空
	if(mobile==""){
		$("#newMobileError").text("您还没有输入新的手机号");
		isError = false;
	}else{                         //判断手机号是否被占用
		/*判定手机号是否输入正确*/
		if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile)){
			$("#newMobileError").text("请您输入正确的手机号");
			return;
		}
		
		$.ajax({
			type:"post",
			url:"doCheckMobile",
			data:{"mobile":mobile},
			async:false,
			success:function(response){
				if(response=="false"){
					$("#newMobileError").text("你输入的手机号已经被占用");
					isError = false;
				}
			}
		});
	}
	if(isError == false){
		return;
	}
	
	curCountNew = countNew;
    $.ajax({
        type:"post",
        url:'getNewUserVodePhone',
        data:{"phoneNum":mobile},
        success: function(response){
        	if(response==""){
        		//设置button效果，开始计时
        		$("#newMobileVerfity").attr("disabled", "true");
        		$("#newMobileVerfity").text("重发验证码(" + curCountNew + "s)");
        		InterNewValObj = window.setInterval(SetNewRemainTime, 1000); //启动计时器，1秒执行一次
        	}else{
        		alert(response);
        	}
        }
    });    
}

function SetNewRemainTime() {
    if (curCountNew == 0) {                
        window.clearInterval(InterNewValObj);//停止计时器
        $("#newMobileVerfity").removeAttr("disabled");//启用按钮
        $("#newMobileVerfity").text("重新发送验证码");
    }else {
    	curCountNew--;
        $("#newMobileVerfity").text("重发验证码(" + curCountNew + "s)");
    }
}
