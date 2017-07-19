<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>修改用户信息</title>
	<link rel="stylesheet" href="${ctx}/css/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/login.css">     
	<link rel="stylesheet" href="${ctx}/css/app-base.css">    
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/layer-mobile/layer/layer.js"></script>   
	<script src="${ctx}/js/hgl/reject.js"></script>
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
	.main{
		color:#999;
	}
	.main .form-group{
		margin-bottom:0;
		line-height:24px;
		font-size:0.9rem;
	}
	.main .lab{
		width:80px;    
		float:left;
		font-weight:normal;
	}
	.skill.no-skill{
		margin-bottom:5px;
	}
	.main h4{
		margin-bottom:5px;  
	}
	.form-group .img{
		margin-bottom:10px;
		position:relative; width:80px; height:80px;
	}
	.main .lab,.main h4{
		color:#333;
		font-weight:500;
	}
	</style>	             
</head>  
<body>     
	<div class="header no-boot">
    	<a href="${ctx}/"> 
    		<span class="icon-angle-left" onclick="history.back(-1);"></span>
    	</a>
    	<h2>审核拒绝</h2>   
	</div>  
	
	<div class="wrap">
		<div class="main">
			<div class="clearfix form-group">
				<label class="lab">审核状态</label>
				<span>
					<c:if test="${webUser.state==0}">待审核</c:if>
					<c:if test="${webUser.state==1}">审核通过</c:if>
					<c:if test="${webUser.state==2}">审核拒绝</c:if>
				</span>
			</div>
			<c:if test="${webUser.state==2}">
				<div class="clearfix form-group">
					<label class="lab">拒绝理由</label>
					<span>${webUser.remark}</span>
				</div>
			</c:if>
			<form action="" name="updateForm" method="post" id="updateForm" enctype="multipart/form-data">
				<input type="hidden" value="${webUser.id}" name="id">
				<div class="form-group clearfix">
					<label class="lab">推荐码</label>
					<span>${webUser.recommendcode}</span>											
				</div>
				<div class="form-group clearfix">
					<label class="lab">用户名</label>
					<span>${webUser.userName}</span>									
				</div>
				<div class="form-group clearfix">
					<label class="lab">真实姓名</label>
					<span>${webUser.name}</span>
				</div>
				<div class="form-group clearfix code">
					<label class="lab">手机号</label>
					<span>${webUser.mobile}</span>
				</div>
				<div class="form-group clearfix">
					<label class="label-group label-add" id="serve" style="overflow:hidden;">
						<span class="icon-th-large"></span>
						请选择服务项目      
						<span class="icon-angle-right"></span>
					</label>	
					<div class="skill no-skill" style="margin-top:-8px;">			
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
					<div id="skillError" style="color:red;font-size:12px;"></div>
				</div>
				
				<div class="form-group clearfix">
					<h4>本人头像</h4>
					<div class="img" >
						<img class="showImage" src="${ctx}/login/displayImage?id=${userInfo.id}&imageName=${userInfo.image}" alt="" onclick="image.click()" id="showImage" style="">
						<input type="file" name="image" id="image" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	
 					</div>	
 					<span style="color:red;font-size:12px;" id="imageError"></span>						 					    
				</div>	
							
				<div class="form-group clearfix">
					<h4>本人身份证正面照</h4>
					<div class="img" >
						<img class="showImage" src="${ctx}/login/displayImage?id=${userInfo.id}&imageName=${userInfo.imageFace}" alt="" onclick="imageFace.click()" id="showImageFace">
						<input type="file" name="imageFace" id="imageFace" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	
 					</div> 
 					<span id="imageFaceError" style="color:red;font-size:12px;"></span>					    
				</div>
				
				<div class="form-group clearfix">  
					<h4>本人身份证反面照</h4>					
					<div class="img" >
						<img class="showImage" src="${ctx}/login/displayImage?id=${userInfo.id}&imageName=${userInfo.iamgeBack}" alt="" onclick="imageBack.click()" id="showImageBack">
						<input type="file" name="imageBack" id="imageBack" accept="image/*" class="upload-input"/>
						<span class=" icon-plus"></span>	 
 					</div>         
 					<span id="imageBackError" style="color:red;font-size:12px;"></span> 
				</div>			
				<div class="form-group">
					<button type="button" class="btn btn-info btn-block" id="registerBtn">重新提交</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
<!--传递图片-->

</script>
</html>