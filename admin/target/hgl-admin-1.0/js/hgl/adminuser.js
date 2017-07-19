$("document").ready(function(){		
	
	$("#userName").keyup(function(){
		$("#userNameError").text("");	
	});
	
	$("#mobile").keyup(function(){
		$("#mobileError").text("");	
	});
	
	$("#email").keyup(function(){
		$("#emailError").text("");
	});
	
	$("#idCard").keyup(function(){
		$("#idCardError").text("");
	});
	
	$("#addAdminUser #cfdPassword").keyup(function(){
		$("#cfdPasswordError").text("");
	});
	
	$("#addAdminUser #cfdPassword").keyup(function(){
		$("#cfdPasswordError").text("");
	});
	
	$("#roleId").focus(function(){
		$("#roleId").removeAttr("style");
	});

	$("#type").focus(function(){
		$("#type").removeAttr("style");
	});
	
	//添加用户--开始
	//验证用户名是否重复(添加)
	var addUserNameError = true;
	$("#addAdminUser #userName").blur(function(){
		var userName=$("#addAdminUser #userName").val();	
		if(userName!=""){
			$.ajax({
				type:"post",
				url:"checkAdminUser",
				data:{"params":userName,"checkTypeHtml":0},
				success:function(data){
					if(data=="false"){		
						addUserNameError =false;
						$("#addAdminUser #userNameError").html("用户名已被占用");
						$("#addAdminUser #userNameError").css("color","red");
						$("#addAdminUser #userNameError").css("font-size","12px");
						$("#addAdminUser #userName").focus();
					}else{
						addUserNameError = true;
					}
				}
			});
		}			
	});
	
	//验证手机号是否重复(添加)
	var addMobileError = true;
	$("#addAdminUser #mobile").blur(function(){
		var mobile =$("#addAdminUser #mobile").val();		
		if(mobile==""){
			return;
		}		
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":mobile,"checkTypeHtml":1},
			success:function(data){
				if(data=="false"){
					addMobileError = false;
					$("#addAdminUser #mobileError").text("该手机号已被占用");
				    $("#addAdminUser #mobileError").css("color","red");
				    $("#addAdminUser #mobileError").css("font-size","12px");
					$("#addAdminUser #mobile").focus();
				}else{
					addMobileError = true;
				}
			}
		});			
	});
	

	//验证邮箱是否被占用(添加)
	var addEmailError = true;
	$("#addAdminUser #email").blur(function(){
		var email =$("#addAdminUser #email").val();
		
		if(email==""){
			return;
		}
		
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":email,"checkTypeHtml":2},
			success:function(data){
				if(data=="false"){
					addEmailError = false;
					$("#addAdminUser #emailError").text("该邮箱已经被占用 ");
					$("#addAdminUser #emailError").css("color","red");
					$("#addAdminUser #emailError").css("font-size","12px");
					$("#addAdminUser #email").focus();
				}else{
					addEmailError = true;
				}
			}
		});	
	});
	

	//验证身份证号是否被占用(添加)
	var idCardError = true;
	$("#addAdminUser #idCard").blur(function(){
		var idCard =$("#addAdminUser #idCard").val();		
		if(idCard==""){
			return;
		}
		
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":idCard,"checkTypeHtml":3},
			success:function(data){
				if(data=="false"){
					idCardError =false;
					$("#addAdminUser #idCardError").text("身份证号码已经被占用");
					$("#addAdminUser #idCardError").css("color","red");
					$("#addAdminUser #idCardError").css("font-size","12px");
					$("#addAdminUser #idCard").focus();
				}else{
					idCardError = true;
				}
			}
		});	
	});


	$("#addBtnInsert").click(function(){
		var isError = validateForm("addAdminUser");
		var roleId=$("#addAdminUser #roleId").val();
		var type=$("#addAdminUser #type").val();
				
		if(roleId==0){
			$("#addAdminUser #roleId").css("border","1px solid red");
			isError = false;
		}
		
		if(type==0){
			$("#addAdminUser #type").css("border","1px solid red");
			isError =false;
		}
		var password = $("#addAdminUser #password").val();
		var cfdPassword = $("#addAdminUser #cfdPassword").val();
		if(password !=cfdPassword){
			$("#cfdPasswordError").text("前后密码输入不一致");
			$("#cfdPasswordError").css("color","red");
			$("#cfdPasswordError").css("font-size","12px");
			return;
		}
									
		if(isError==false||addUserNameError==false ||addMobileError==false||addEmailError==false||idCardError==false){
			return;
		}
		
		var params=$("#addAdminUser").serialize();
		$.ajax({
			type:"post",
			url:"addAdminUser",
			data:params,
			success:function(data){
				if(data=="1"){
					alert("添加成功");
					$("#addAdminUser #userName").val("");
					$("#addAdminUser #name").val("");
					$("#addAdminUser #password").val("");
					$("#addAdminUser #cfdPassword").val("");
					$("#addAdminUser #address").val("");
					$("#addAdminUser #mobile").val("");
					$("#addAdminUser #email").val("");
					$("#addAdminUser #roleId").get(0).selectedIndex=0;
					$("#addAdminUser #type").get(0).selectedIndex=0;
					$("#addAdminUser #idCard").val("");
					$("#addAdminUser #remark").val("");					
				}else{
					alert("添加失败");
				}
			}		  
		});
				
	});
	//添加用户 --结束
	
		
	//修改用户信息验证 --开始
	//验证用户名是否重复(修改)
	var updUseNameError = true;
	$("#updAdminUser #userName").blur(function(){
		var userName=$("#updAdminUser #userName").val();
		var id=$("#updAdminUser #id").val();
		if(userName!=""){
			$.ajax({
				type:"post",
				url:"checkAdminUser",
				data:{"params":userName,"checkTypeHtml":0,"id":id},
				success:function(data){
					if(data=="false"){		
						updUseNameError = false;
						$("#updAdminUser #userNameError").html("用户名已被占用");
						$("#updAdminUser #userNameError").css("color","red");
						$("#addAdminUser #userNameError").css("font-size","12px");
						$("#updAdminUser #userName").focus();
					}else{
						updUseNameError = true;
					}
				}
			});
		}			
	});
	
	//验证手机号是否重复(修改)
	var updMobileError = true;
	$("#updAdminUser #mobile").blur(function(){
		var mobile =$("#updAdminUser #mobile").val();
		var id = $("#updAdminUser #id").val();		
		if(mobile==""){
			return;
		}
			
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":mobile,"checkTypeHtml":1,"id":id},
			success:function(data){
				if(data=="false"){
					updMobileError = false;
					$("#updAdminUser #mobileError").text("该手机号已被占用");
				    $("#updAdminUser #mobileError").css("color","red");
				    $("#addAdminUser #mobileError").css("font-size","12px");
					$("#updAdminUser #mobile").focus();
				}else{
					updMobileError = true;
				}
			}
		});			
	});


	//验证邮箱是否被占用(修改)
	var updEmailError = true;
	$("#updAdminUser #email").blur(function(){
		var email =$("#updAdminUser #email").val();
		var id = $("#updAdminUser #id").val();
		
		if(email==""){
			return;
		}
		
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":email,"checkTypeHtml":2,"id":id},
			success:function(data){
				if(data=="false"){
					updEmailError = false;
					$("#updAdminUser #emailError").text("该邮箱已经被占用 ");
					$("#updAdminUser #emailError").css("color","red");
					$("#addAdminUser #emailError").css("font-size","12px");
					$("#updAdminUser #email").focus();
				}else{
					updEmailError = true;
				}
			}
		});	
	});
	

	//验证身份证号是否被占用(添加)
	var updIdCardError = true;
	$("#updAdminUser #idCard").blur(function(){
		var idCard =$("#updAdminUser #idCard").val();
		var id = $("#updAdminUser #id").val();
		
		if(idCard==""){
			return;
		}
		
		$.ajax({
			type:"post",
			url:"checkAdminUser",
			data:{"params":idCard,"checkTypeHtml":3,"id":id},
			success:function(data){
				if(data=="false"){
					updIdCardError = false;
					$("#updAdminUser #idCardError").text("身份证号码已经被占用");
					$("#updAdminUser #idCardError").css("color","red");
					$("#addAdminUser #idCardError").css("font-size","12px");
					$("#updAdminUser #idCard").focus();
				}else{
					updIdCardError = true;
				}
			}
		});	
	});
	$("#updBtn").click(function(){	
		var isError = validateForm("updAdminUser");
		var roleId=$("#updAdminUser #roleId").val();
		var type=$("#updAdminUser #type").val();
				
		if(roleId==0){
			$("#updAdminUser #roleId").css("border","1px solid red");
			isError = false;
		}
		
		if(type==0){
			$("#updAdminUser #type").css("border","1px solid red");
			isError =false;
		}
					
		if(isError==false||updUseNameError==false||updMobileError==false||updEmailError==false||updIdCardError==false){
			return;
		}
		updAdminUser.action="updateAdminUser";
		updAdminUser.submit();
	});	
	//修改用户信息验证 --结束
	
});

//重置密码开始
function resetPassword(userId){
	if(confirm("您确认重置密码吗？")){
		$.ajax({
			   type:"post",
			   url:"resetPassword",
			   data:{"id":userId},
			   success:function(data){
				  if(data=="false"){
					 alert("重置失败");
				  } 
			   }				   
		   });	
	}
}
//重置密码结束

//删除记录开始
function deleteAdminUser(userId){
	if(confirm("你确认删除该条记录吗？")){
		$.ajax({
			type:"post",
			url:"deleteAdminUser",
			data:{"id":userId},
			success:function(data){
				if(data=="1"){
					$("#adminUser"+userId).parent().parent().hide();
					adminUserForm.action="init";
					adminUserForm.submit();
				}else{
					alert("删除失败！");
				}
			}
		});
	}
}
//删除记录结束
	
function updateAdminUserStatus(id){
	var timer= null;			
	var targetNode = document.getElementById("updateStatus"+id);
	var tdNode = document.getElementById("td"+id);
	var status=132;
	if(targetNode.innerHTML.trim()=="启用"){
		status=132;
	}else if(targetNode.innerHTML.trim()=="禁用"){
		status=134;
	}

	$.ajax({
		type:"post",
		url:"updateAdminUserStatus",
		data:{"id":id,"status":status},
		success:function(data){
			if(data=="1"){
				if(status==132){
					tdNode.innerHTML="启用";
					tdNode.style="font-weight:700;color:red";
					targetNode.innerHTML="禁用";							
				}else if(status==134){
					tdNode.innerHTML="禁用";					
					tdNode.style="font-weight:700;color:red";
					targetNode.innerHTML="启用";							
				}	
			}else{
				alert("操作失败");
			}
		}
	});	
	
	timer=setTimeout(function(){
		tdNode.className="";
		tdNode.style="";
	},1000);
}
