<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="下单成功" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<style>
		.submit-success a{
			float:left;
			border:1px solid #ccc;
			border-radius:3px;
			box-sizing:border-box;
			margin:10px;
			color:#ccc; 
			line-height:34px;  
		}
	</style>
</head>	

<body>           
<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="pay">
		<div class="box-shadow info text-center">
			<div>
				<img src="${ctx}/images/succeed.png" alt="" class="img">
				<h3>恭喜,下单成功!</h3>
				<h3>${weixinUser.openId }</h3>       
				<h3>${weixinUser.nickname }</h3>
				<h3>${openId }</h3>
			</div>
			<div class="submit-success clearfix box" style="margin-top:10px;">
				<a class="box1" href="${ctx }/">回到首页</a>	
				<a class="box1" href="${ctx }/wapOrderService/myService">服务订单</a>
			</div>	   
		</div>    
	</div>
</div>
</body>
</html>