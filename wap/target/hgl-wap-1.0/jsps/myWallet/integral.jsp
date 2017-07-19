<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="我的积分" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/common/slidePage.js"></script> 
	<style>
		.prolist li{height:40px;line-height:40px;font-size:0.9rem;background:#fff;padding:0 10px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;}  
        .prolist li a{color:#333;font-size:16px;}  
        .prolist .box1:nth-child(1){line-height:normal;}
		/**加载效果旋转**/  
        @-webkit-keyframes rotate {0% {-webkit-transform: rotate(0deg);transform: rotate(0deg);}100% {-webkit-transform: rotate(360deg);transform: rotate(360deg);}}  
        @keyframes rotate {0% {-webkit-transform: rotate(0deg);transform: rotate(0deg);}100% {-webkit-transform: rotate(360deg);transform: rotate(360deg);}}  
          
        .loadmore {display:block;line-height: 50px;text-align:center;color:#ccc;font-size:14px;}  
        .loadmore span{height:20px;width:20px;border-radius:100%;display:inline-block;margin:10px;border:2px solid #f60;border-bottom-color: transparent;vertical-align: middle;-webkit-animation: rotate 1.1s infinite linear;animation: rotate 1.1s infinite linear;}  
        .loadover{position:relative;margin:0 12px;padding:5px 0;color:#909090;text-align: center;}  
        .loadover span{position:relative;display:inline-block;padding:0 6px;height:20px;background:#F2F2F2;z-index:2}  
        .loadover:after {content:''position: absolute;left: 0;top:50%;width: 100%;height:1px;background:#DCDCDC;z-index:1;display:block;}
	</style>
</head>	
<body>
<%@include file="../common/header.jsp"%>
<div class="container" style="margin-bottom:0;position:fixed;top:0;width:100%;z-index:9999;">
	<div class="integral money">
	<c:if test="${not empty tbIntegral.integral}">
		<p style="line-height:30px;">积分总额：<span class="text-red">${tbIntegral.integral}</span></p>
	</c:if>
	<c:if test="${empty tbIntegral.integral}">
		<p style="line-height:30px;">积分总额：<span class="text-red">0</span></p>
	</c:if> 
	<div class="deposit" style="width: 88px;border-color:#eee;">                          
		<a href="${ctx }/integralMallType/index" style="color:#aaa;">积分商城</a>
	</div>
	<input type="hidden" id="tbIntegralId" value="${tbIntegral.id}" />
		<h3 style="font-weight:normal;">积分明细</h3>
		<div class="box text-center" style="padding:5px 0;font-size:0.9rem;">
			<div class="box1">时间</div>        
			<div class="box1">类型</div>
			<div class="box2">订单号</div>       
			<div class="box1">积分</div>      
		</div>
	</div>      
</div>
<input type="hidden" value="${pageCount}" id="pageCount" />
<input type="hidden" value="0" id="pageIndex" />
<ul class="prolist">   
	<%@include file="integralList.jsp"%>
</ul>
</body>
</html>
<script>  
	function selectInfo(pageIndex){
		var tbIntegralId = $("#tbIntegralId").val();
		$.ajax({
		      type: "POST",
		      async: false,
		      url: ctx+"/myWallet/integralDetailedPage",  
		      data:{
		    	  pageIndex:pageIndex,
		    	  tbIntegralId:tbIntegralId,
		      },
		      success: function(response){
		            $(".prolist").append(response);
		     	}
			});
	}
	$(function(){
		var headH = $('header').height();
		var tainerH =  $('.container').height();
		$('.prolist').css('padding-top',headH + tainerH);
		
	});
</script>