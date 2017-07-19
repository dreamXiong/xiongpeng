<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>找回密码</title>
	<link rel="stylesheet" href="${ctx}/css/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/login.css">
	<link rel="stylesheet" href="${ctx}/css/app-base.css">
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/layer-mobile/layer/layer.js"></script>
</head>
<body>
	<div class="header no-boot">       
    	<a href="${ctx}/" >
    		<span class="icon-angle-left" onclick="history.back(-1);"></span>
    	</a>
    	<h2>找回密码</h2>
	</div>
	<div class="wrap">
		<div class="main">
			<form action="" name="registerForm" method="post" id="registerForm">
				<div class="form-group">
					<input type="text" class="form-control"  placeholder="请输入用户名" id="userName" autocomplete="off" name="userName" maxlength="30">
					<span id="userNameError" style="font-size:12px;color:red;"></span>     
				</div>
				<div class="form-group">
					<!-- 错误提示给input加个border-red类名 -->
					<input type="text" class="form-control" id="verify" name="verify" autocomplete="off" placeholder="请输入验证码" maxlength="6" value="">
					<span class="form-group-img"><img id="verifyImg" src="${ctx}/login/authImage" alt="" width="63" height="25"></span>
				</div>
				<div class="form-group clearfix code">
					<div class="col-xs-8" style="width:57%;">
						<input type="text" class="form-control"  placeholder="请输入手机验证码" autocomplete="off" maxlength="6" id="checkCode">
						<span id="checkCodeError" style="font-size:12px;color:red;"></span>
					</div>
					<div class="col-xs-4 text-right" style="width:43%;font-size:12px;" >
						<input type="hidden" id="checkCodeHidden">
						<input type="button" class="btn btn-info btn-block" id="checkCodeBtn" onclick="formsubmit('1')" value="获取验证码">    
					</div>
				</div>
				<div class="form-group">
					<span id="errorInfo" style="display: none;" color="red"></span>
					<button type="button" class="btn btn-info btn-block" id="registerBtn" onclick="formsubmit('0')">提交</button>
				</div>			
			</form>		
		</div>
	</div>
</body>
</html>
<script>
$(function(){
	$('#verifyImg').click(function(event) {         
	 	$("#verifyImg").attr("src","${ctx}/login/authImage?K="+Math.random());
	});
});

function formsubmit(flag){     
	var checkNullFlag = true;
	if($("#userName").val() == ''){
		$('#userName').addClass('border-red'); 
		checkNullFlag = false;
	}else{
		$('#userName').removeClass('border-red'); 
	}
	if($("#verify").val() == ''){
		$('#verify').addClass('border-red'); 
		checkNullFlag = false;
	}else{
		$('#verify').removeClass('border-red'); 
	}
	if(flag == '0'){
		if($("#checkCode").val() == ''){
			$('#checkCode').addClass('border-red'); 
			checkNullFlag = false;
		}else{
			$('#checkCode').removeClass('border-red'); 
		}
	}
	if(checkNullFlag){
		if(flag == '1'){
			curCount = count;
			$.ajax({                       
				type: "POST",
				url: "${ctx}/login/getPhoneCode",
				data: "userName="+$("#userName").val()+"&verify="+$("#verify").val()+"&random="+Math.random(),
				success: function(response){     
					if(response.tipsInfo == 'success'){
						$("#checkCodeBtn").attr("disabled", "true");
		        		$("#checkCodeBtn").val("重发验证码(" + curCount + "s)");
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
				data: "userName="+$("#userName").val()+"&vcodePhone="+$("#checkCode").val()+"&verify="+$("#verify").val()+"&random="+Math.random(),
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
        $("#checkCodeBtn").removeAttr("disabled");//启用按钮
        $("#checkCodeBtn").val("重新发送验证码");
    }else {
        curCount--;
        $("#checkCodeBtn").val("重发验证码(" + curCount + "s)");
    }
}
</script>