<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="去支付" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="pay">
		<div class="box-shadow info text-center">
			<div>        
				
			</div>
			<div style="margin-top:5px;">
				<ul>
					
						<li class="clearfix">
							<span class="pull-left">订单编号</span>
							<span class="pull-right">
								${orderServiceDto.orderSerialNo}
							</span>
						</li>
					
					<li class="clearfix">
						<span class="pull-left">应付金额</span>
						<span class="pull-right">
							<div class="text-red font-bold">
								<span class="dollar">¥</span>
								<span class="unit">${orderServiceDto.totalMoney }</span>   
							</div>
						</span>
					</li>
				</ul>
			</div>
		</div>
		<h2>选择支付方式</h2>
		<div class="box-shadow info list">
				<label>
					<input type="radio" name="list" >
					<img src="${ctx}/images/zhifubao.png" alt="">
				</label>
				<label>
					<input type="radio" name="list">
					<img src="${ctx}/images/weixin.png" alt="">
				</label>
				<label>
					<input type="radio" name="list">
					<img src="${ctx}/images/xianshang.png" alt="">
				</label>
				<button>立即支付</button>
		</div>
	</div>
</div>
</body>
<script>
	$(function() {
		$(document).delegate('dl dt','click',function(){
 			$(this).find('.icon-angle-right').toggleClass('rotate');
 			$(this).siblings('dd').slideToggle(100);
 		});
 		$('.nav-tab-top').delegate('li','click',function(){
 			$(this).addClass('cur').siblings('li').removeClass('cur');
 		});
 		// 选择一个支付方式，按钮才显示
 		$('.list').delegate('label','click',function(){
 			$('.list button').addClass('active');
 		});
	});
</script>
</html>