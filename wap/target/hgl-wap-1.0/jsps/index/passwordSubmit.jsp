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
					<input type="password" class="form-control"  placeholder="请输入新密码" id="password" autocomplete="off" name="password" maxlength="30">
					<span id="userNameError" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-info btn-block" id="registerBtn" onclick="formsubmit()">提交</button>
				</div>			
			</form>		
		</div>
	</div>
</body>
</html>
<script>
function formsubmit(){     
	if($("#password").val() == ''){
		$('#password').toggleClass('border-red'); 
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
			layer.open({
		 		content: '密码长度为6-20字符',
		 		time: 2 
			}); 
			return false;
		}
	}
	return false;
}
</script>