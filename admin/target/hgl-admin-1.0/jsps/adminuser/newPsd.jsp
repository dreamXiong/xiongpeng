<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="修改密码" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<script type="text/javascript">
	$("#document").ready(function(){
		//验证旧密码
		$("#oldPassword").blur(function(){			
			var oldPassword = $("#oldPassword").val();
			/*1.验证旧密码必须输入*/
			if(oldPassword ==""){
				$("#oldPasswordError").text("请输入旧密码");
				$("#oldPasswordError").css("color","red");
				$("#oldPasswordError").css("font-size","12px");
				$("#oldPassword").css("border","1px solid red");
				return;
			}
			
			/*验证旧密码输入是否正确*/
			$.ajax({
				type:"post",
				url:"doCheckOldPassword",
				data:{"oldPassword":oldPassword},
				success:function(data){
					if(data=="false"){
						$("#oldPasswordError").text("旧密码输入不正确");
						$("#oldPasswordError").css("color","red");
						$("#oldPasswordError").css("font-size","12px");
						$("#oldPassword").css("border","1px solid red");
					}
				}
			});						
		});
		
		//旧密码获得光标，清除样式
		$("#oldPassword").focus(function(){
			$("#oldPasswordError").text("");
			$("#oldPasswordError").removeAttr("style");
			$("#oldPassword").removeAttr("style");
		});
		
		
		//验证新密码
		
		$("#password").blur(function(){
			var password = $("#password").val();	
			if(password==""){
				$("#passwordError").text("请输入新密码");
				$("#passwordError").css("color","red");
				$("#passwordError").css("font-size","12px");
				$("#password").css("border","1px solid red");
			}			
		});
		
		
		//新密码获得光标,清除样式
		$("#password").focus(function(){
			$("#passwordError").text("");
			$("#passwordError").removeAttr("style");
			$("#password").removeAttr("style");
		});
		
		//验证确认必须一致
		$("#cfdPassword").blur(function(){
			
			var password = $("#password").val();
			var cfdPassword = $("#cfdPassword").val();
			if(password!=cfdPassword){
				$("#cfdPasswordError").text("确认密码必须和新密码一致");
				$("#cfdPasswordError").css("color","red");
				$("#cfdPasswordError").css("font-size","red");
				$("#cfdPassword").css("border","1px solid red");
			}
		});
		
		//光标移除确认密码清除样式
		$("#cfdPassword").focus(function(){
			$("#cfdPasswordError").text("");
			$("#cfdPasswordError").removeAttr("style");
			$("#cfdPassword").removeAttr("style");
		});
		
		
		//点击提交
		$("#updPasswordBtn").click(function(){
			var isError = true;
			var oldPassword = $("#oldPassword").val();
			var password = $("#password").val();
			var cfdPassword = $("#cfdPassword").val();
			
			/*旧密码不能为空*/
			if(oldPassword ==""){
				$("#oldPasswordError").text("请输入旧密码");
				$("#oldPasswordError").css("color","red");
				$("#oldPasswordError").css("font-size","12px");
				$("#oldPassword").css("border","1px solid red");
				isError = false;
			}
			
			//新密码不能为空
			if(password==""){
				$("#passwordError").text("请输入新密码");
				$("#passwordError").css("color","red");
				$("#passwordError").css("font-size","12px");
				$("#password").css("border","1px solid red");
				isError = false;
			}
			
			if(password!=cfdPassword){
				$("#cfdPasswordError").text("确认密码必须和新密码一致");
				$("#cfdPasswordError").css("color","red");
				$("#cfdPasswordError").css("font-size","red");
				$("#cfdPassword").css("border","1px solid red");
				isError = false;
			}
			
			if(isError==false){
				return;
			}
			
			updatePsdForm.action = "doUpdatePassword";
			updatePsdForm.submit();			
		});
		
	});
</script>
</head>
<body>
	<div class="wrapper">
  		<div class="content-wrapper">
  			<section class="content-header">
      			<h1>
        			我的主页
        			<small>修改密码</small>
      			</h1>
    		</section>
    		<section class="content">
    			<div>
    				<p style="background:#3c8dbc;height:30px;line-height:30px;font-weight:bold;">修改密码</p>
    			</div>
    			<div class="row" style="margin-left:10px;">
    				<form action="" id="updatePsdForm" method="post" name="updatePsdForm">
    					<div class="form-group">
    						<label style="width:90px;">用户名:</label>
    						<span>${adminUser.userName}</span>
    					</div>
    					<div class="form-group">
    						<label style="width:90px;"><span style="color:red;">*</span>旧密码:</label>
    						<input type="password" id="oldPassword" placeholder="请输入旧密码">
    						<span id="oldPasswordError"></span>
    					</div>
    					<div class="form-group">
    						<label style="width:90px;"><span style="color:red;">*</span>新密码:</label>
    						<input type="password" id="password" placeholder="请输入新密码" name="password">
    						<span id="passwordError"></span>
    					</div>
    					<div class="form-group">
    						<label style="width:90px;"><span style="color:red;">*</span>确认新密码:</label>
    						<input type="password" id="cfdPassword" placeholder="请输入确认密码">
    						<span id="cfdPasswordError"></span>
    					</div>
    					<div style="width:250px;text-align:center;">
    						<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="updPasswordBtn">修改密码</a>
    					</div>
    				</form>
    			</div>
    		</section>
		</div>
	</div>
</body>
</html>
</tiles:put>
</tiles:insert>