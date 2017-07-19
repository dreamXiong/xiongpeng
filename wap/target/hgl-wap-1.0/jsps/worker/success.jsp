<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="等待审核" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<script src="${ctx}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/js/dist/js/swiper.min.js"></script>
	<script src="${ctx}/js/dist/js/swiper.jquery.min.js"></script>  
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
<%-- <body>
	<div class="container">           
		<div class="pay">               
			<div class="box-shadow info text-center">
				<div>
					<img src="${ctx}/images/succeed.png" alt="" class="img">
					<h3>您的注册信息已经提交，请耐心等待审核，谢谢</h3>
				</div>
				<div class="submit-success clearfix box" style="margin-top:10px;">
					<a class="box1" href="${ctx }/">回到首页</a>	
					<a class="box1" href="${ctx }/wapOrderService/myService">注册</a>
				</div>	   
			</div>    
		</div>
	</div>
	
</body> --%>
<body>
<header class="cart verify-head " style="z-index:10000;">
	<span class="icon-angle-left text-center" onclick="history.back(-1);"></span>
	<h3 class="text-center"><c:out value="${titleName}"/></h3>
</header>
<%-- <%@include file="../common/header.jsp"%> --%>
<div class="container">           
	<div class="pay">               
		<div class="box-shadow info text-center">
			<div>
				<img src="${ctx}/images/succeed.png" alt="" class="img">
				<h3>您信息已经提交，等待审核！</h3>
			</div> 
			<%-- <div class="submit-success clearfix box" style="margin-top:10px;">
				<a class="box1" href="${ctx }/">回到首页</a>	
				<a class="box1" href="${ctx }/wapOrderService/myService">服务订单</a>
			</div> --%>	   
		</div>    
	</div>
</div>
</body>
</html>