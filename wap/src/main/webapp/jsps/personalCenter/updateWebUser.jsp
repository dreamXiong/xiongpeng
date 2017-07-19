<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="个人信息修改" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>                       
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/hgl/updateWebUser.js"></script>   
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="container" style="margin-bottom:0;">         
	<div class="personal box-shadow  revamp" style="height:auto;">
		<div class="box">                     
			<div class="user-info box1">
				<h3>个人信息</h3>	
				<div id="errorTxt"></div>
				<form action="doUpdateWebUser" method="post" id="webUserForm" name="webUserForm">
					<div class="form-group">        
						<label>昵 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
						<input type="text" value="${webUserDto.name}" id="name" name="name">
					</div>
					<div class="form-group">                       
						<label>电话号码 </label>
						<input type="text" value="${webUserDto.mobile}" id="mobile" name="mobile">						
					</div>      
					<div>
						<a href="javascript:void(0);" id="doSaveWebUser">确认修改</a>
					</div>
				</form>	
				<h3 style="padding-top:15px;">修改密码</h3>
				<form action="" method="post" id="psdForm" name="psdForm">
					<div class="form-group">    
					<label>旧  密  码</label>
					<input type="password" value="" id="oldPassword">
					<span id="oldPsdError" style="font-size:12px;color:red;"></span>
				</div>
				<div class="form-group">                       
					<label>新  密  码  </label>
					<input type="password" value="" name="password" id="password" maxlength="18">
					<span id="newPasswordError" style="font-size:12px;color:red;"></span>						
				</div> 	
				<div class="form-group">                       
					<label>确认密码  </label>
					<input type="password" value="" id="cfmPassword" maxlength="18">	
					<span id="cfmPasswordError" style="color:red;font-size:12px;"></span>					
				</div>	  
				<div>
					<a href="javascript:void(0);" id="updPassword" >
						确认修改
					</a>
				</div>    
				</form>					     
			</div>
		</div>                      
	</div>
</div>
</body>
</html>