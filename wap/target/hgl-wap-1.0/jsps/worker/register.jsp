<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>师傅注册</title>
	<link rel="stylesheet" href="${ctx}/css/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/login.css">     
	<link rel="stylesheet" href="${ctx}/css/app-base.css">    
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<%-- <link rel="stylesheet" href="${ctx}/css/idnex.css"> --%>
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/layer-mobile/layer/layer.js"></script>   
	<script src="${ctx}/js/hgl/register.js"></script>
	<style>
	.form-group .icon-plus{
		position:absolute;
		height:100%;        
		width:100%;
		top:0;
		left:0;
		text-align:center;
		line-height:80px;
		font-size:30px;      
		color:#ccc;
		z-index:1;
	}       
	.upload-input{
		display: block!important;
		opacity:0;
		filter:alpha(opacity=0);
	}  
	.showImage{
		width:80px;
		height:80px;
		position:absolute;
		z-index:2;
	}
	.img{
		position:relative; width:80px; height:80px;
	}
	</style>	        
</head> 
<body>     
	<div class="header no-boot">
    	<a href="${ctx}/"> 
    		<span class="icon-angle-left" onclick="history.back(-1);"></span>
    	</a>
    	<h2>师傅注册</h2>   
	</div>  
	
	<div class="wrap">
		<div class="main">
			<form action="" name="registerForm" method="post" id="registerForm" enctype="multipart/form-data">
				<div class="form-group">
					<c:choose>
						<c:when test="${recommendCode!=null && recommendCode!=''}">
							<input type="text" class="form-control" id="recommendcode" name="recommendcode" value="${recommendCode}" readonly="readonly" onfocus="this.blur();">
						</c:when>
						<c:otherwise>
							<input type="text" class="form-control" id="recommendcode" name="recommendcode" placeholder="请输入推荐码">
						</c:otherwise>
					</c:choose>		
					<span id="recommendcodeError" style="color:red;font-size:12px;"></span>		
				</div>
				<div class="form-group">
					<input type="hidden" name="openId" value="${session_openId}">
					<input type="text" class="form-control"  placeholder="请输入用户名" id="userName" name="userName" maxlength="30"  onkeyup="value=value.replace(/[\W]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">					
					<span id="userNameError" style="color:red;font-size:12px;"></span>
				</div>
				<div class="form-group">
					<input type="text" class="form-control"  placeholder="请输入真实姓名" id="name" name="name" maxlength="15">
					<span id="nameError" style="color:red;font-size:12px;"></span>
				</div>
				<div class="form-group clearfix code">
					<div class="col-xs-8">	
						<input type="text" class="form-control"  placeholder="请输入手机号码" id="mobile" name="mobile" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						<span id="mobileError" style="color:red;font-size:12px;"></span>
					</div>
					<div class="col-xs-4 text-right">
						<input type="button" class="btn btn-info btn-block" value="获取验证码" id="btnSendCode" onclick="workerVodePhone()"/>					
					</div>
				</div>
				<div class="form-group">
						<input type="text" class="form-control"  placeholder="请输入验证码" maxlength="6" id="checkCode">
						<span id="checkCodeError" style="color:red;font-size:12px;"></span>
				</div>
				<div class="form-group">
					<input type="password" class="form-control"  placeholder="请输入密码" maxlength="18" id="password" name="password">
					<span id="passwordError" style="color:red;font-size:12px;"></span>
				</div>

				<div class="form-group">
					<input type="password" class="form-control"  placeholder="请输入确认密码" maxlength="18" id="confirm_password">
					<span id="confirm_passwordError" style="color:red;font-size:12px;"></span>
				</div>
				<div class="form-group">
					<label class="label-group label-add" id="serve" style="overflow:hidden;">
						<span class="icon-th-large"></span>
						请选择服务项目      
						<span class="icon-angle-right"></span>
					</label>
					<div class="skill no-skill">
						<c:forEach items="${serviceTypeDtos}" var="serviceTypeDto">
							<div class="skill-warp clearfix box-shadow">       
								<h3 id="${serviceTypeDto.id}">${serviceTypeDto.name}</h3>
								<c:forEach items="${serviceTypeDto.childList}" var="childServiceType">
									<label for="${childServiceType.id}">
										<input type="checkbox" name="skill" id="${childServiceType.id}" value="${childServiceType.id}" <c:if test="${childServiceType.checked==1}">checked</c:if>>
										<span>${childServiceType.name}</span>
									</label>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
					<span id="skillError" style="color:red;font-size:12px;visibility: visible;"></span>
				</div>
				
				<div class="form-group">
					<h4 style="margin:0px;padding:0px;line-height:1.4;">本人头像</h4>
					<div class="img" >
						<img class="showImage" src="" alt="" onclick="image.click()" id="showImage" style="">
						<input type="file" name="image" id="image" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	
 					</div>	
 					<span style="color:red;font-size:12px;" id="imageError"></span>						 					    
				</div>	
							
				<div class="form-group">
					<h4 style="margin:0px;padding:0px;line-height:1.4;">本人身份证正面照</h4>
					<div class="img" >
						<img class="showImage" src="" alt="" onclick="imageFace.click()" id="showImageFace">
						<input type="file" name="imageFace" id="imageFace" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	
 					</div> 
 					<span id="imageFaceError" style="color:red;font-size:12px;"></span>					    
				</div>
				
				<div class="form-group">
					<h4 style="margin:0px;padding:0px;line-height:1.4;">本人身份证反面照</h4>					
					<div class="img" >
						<img class="showImage" src="" alt="" onclick="imageBack.click()" id="showImageBack">
						<input type="file" name="imageBack" id="imageBack" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	 
 					</div>         
 					<span id="imageBackError" style="color:red;font-size:12px;"></span> 
				</div>			
				<div class="form-group">
					<button type="button" class="btn btn-info btn-block" id="registerBtn">注册</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
<!--传递图片-->
$(function(){  
	$(document).delegate('#serve','click',function(){
		$(this).children('.icon-angle-right').stop().toggleClass('click');
		$(this).siblings('.no-skill').stop().slideToggle(500);
	});
	$('.icon-reorder').click(function(){
		$(this).siblings('.nav').toggleClass('click');
	});
	
	$("#image").change(function(){
		$("#imageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var image = document.getElementById("image");
		var imageSize = image.files[0].size;
		if(imageSize>5*1024*1024){
			$("#imageError").text("上传图片大小不能超过5M");
			$("#showImage").attr("src","");
			return false;
		}		
		$("#showImage").attr("src",objUrl);			
	});
	
	$("#imageFace").change(function(){
		$("#imageFaceError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var imageFace = document.getElementById("imageFace");
		var imageFaceSize = imageFace.files[0].size;
		if(imageFaceSize>5*1024*1024){
			$("#imageFaceError").text("上传图片大小不能超过5M");
			$("#showImageFace").attr("src","");
			return false;
		}
		$("#showImageFace").attr("src",objUrl);
	});
	
	$("#imageBack").change(function(){
		$("#imageBackError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var imageBack = document.getElementById("imageBack");
		var imageBackSize = imageBack.files[0].size;
		if(imageBackSize>5*1024*1024){
			$("#imageBackError").text("上传图片大小不能超过5M");
			$("#showImageBack").attr("src","");
			return false;
		}
		$("#showImageBack").attr("src",objUrl);
		
	}); 
});

//建立一個可存取到該file的url 
function getObjectURL(file){ 
    var url = null ;  
    if (window.createObjectURL!=undefined){ // basic 
        url = window.createObjectURL(file) ; 
    } else if (window.URL!=undefined) { // mozilla(firefox) 
        url = window.URL.createObjectURL(file) ; 
    } else if (window.webkitURL!=undefined) { // webkit or chrome 
        url = window.webkitURL.createObjectURL(file) ; 
    } 
    return url ; 
}
</script>
</html>