function formCheck(){
	var form1 = $("form")[0];
	var account = document.getElementById("userName").value;
	var password = document.getElementById("verifyPwd").value;
	var newPassword = document.getElementById("newPwd").value;
	var role = document.getElementById("roleId").value;
	var community = document.getElementById("communityId").value;
	var contactName = document.getElementById("contactName").value;
	var phone = document.getElementById("phone").value;
	//var account = $("#userName").value;
	//var password = $("#verifyPwd").value;		//第二次
	//var newPassword = $("#newPwd").value;		//第一次
	//var role = $("#roleId").value;
	//var community = $("#communityId").value;
	//var contactName = $("#contactName").value;
	//var phone = $("#phone").value;
	if(isNull(account)){				//账号为空
		alert("用户账号不能为空!");
		return false;
	}
	if(isNull(newPassword)){
		alert("账号密码不能为空");
		return false;
	}
	if(!isPassWord(newPassword)){	//密码格式错误
		alert("账号密码格式不正确!");
		return false;
	}
	if(isNull(password)){
		alert("密码确认不能为空");
		return false;
	}	
	if(!isPassWord(password)){	//重复的密码,格式错误
		alert("密码确认格式不正确!");
		return false;
	}
	if(!pwdCheck()){	//判断密码一致
		return false ;   	
	}
	if(isNull(role)){
		alert("角色不能为空");
		return false;
	}
	if(isNull(community)){
		alert("物业(小区)不能为空!");
		return false;
	}
	
	/* clear must fill in field
	if(isNull(contactName)){
		alert("联系人姓名不能为空");
		return false;
	}
	if(isNull(phone)){
		alert("手机号码不能为空");
		return false;
	}
	if(!isNumber(phone)||!checkMobile(phone)){
		alert("手机号码格式不正确");
		return  false;
	}
	*/
	return true;
}

function pwdCheck() {
	var verifyPwd = document.getElementById("verifyPwd").value;
	var newPwd = document.getElementById("newPwd").value;
	if(verifyPwd != "") {
		if(newPwd != verifyPwd) {
			alert("确认密码和账号密码不一致，请您重新输入！");
			return false;
		} 
		return true;	
	}else {
		return true;
	}
}