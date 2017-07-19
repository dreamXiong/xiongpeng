<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="商品兑换支付订单" var="titleName"></c:set>
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
			margin:3px;
			color:#ccc; 
			line-height:30px; 
			text-align: center; 
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
				<h3>恭喜,商品兑换订单提交成功!</h3>
			</div>
			<div style="margin-top:5px;">
				<ul>
					<li class="clearfix">
						<span class="pull-left">订单编号</span>
						<span class="pull-right">${integralMallRecord.integralSerialNo}</span>
						<input type="hidden" value="${integralMallRecord.id}" id="orderId"  />
						<input type="hidden" value="${integralMallRecord.version}" id="version"  />
						<input type="hidden" value="${integralMallRecord.payStatus}" id="orderState"  />
					</li>
					<li class="clearfix">
						<span class="pull-left">应付金额</span>
						<span class="pull-right">
							<div class="text-red font-bold">
								<span class="dollar">¥</span>
								<span class="unit">${integralMallRecord.payMoney }</span>   
							</div>
						</span>
					</li>
					<li class="clearfix">
						<span class="pull-left">账户金额</span>
						<span class="pull-right">
							<div class="text-red font-bold">
								<span class="dollar">¥</span>
								<span class="unit">
									<fmt:formatNumber value="${money }" pattern="#,##0.00" ></fmt:formatNumber>
								</span>   
							</div>
						</span>
					</li>
				</ul>
			</div>
		</div>
		<c:if test="${integralMallRecord.payMoney == 0}">
			<div class="submit-success clearfix box" style="margin-top:10px;">
				<a class="box1" href="${ctx }/">回到首页</a>	
				<a class="box1" href="${ctx }/integralMallRecord/index">兑换记录</a>
			</div>	
		</c:if>
		<c:if test="${integralMallRecord.payMoney > 0}">
			<h2>选择支付方式</h2>
			<div class="box-shadow info list">
					<label>
						<input type="radio" value="0" name="list">
						<img src="${ctx}/images/weixin.png" alt="">
					</label>
					<c:if test="${integralMallRecord.payMoney < money }">
						<label>
							<input type="radio" value="1" name="list">
							<img src="${ctx}/images/xianshang.png" alt="">
						</label>
					</c:if>
					<button onclick="goPayMoney();">立即支付</button>
			</div>
		</c:if>
	</div>
</div>

	<form id="winxinForm" action="${ctx}/weixinPay/integralMallPay" method="post">
		<input type="hidden" name="orderGroupId" id="orderIdWeiXin"  />
		<input type="hidden" name="version" id="versionWeiXin"  />
		<input type="hidden" name="orderState" id="orderStateWeiXin"  />
	</form>
	
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
	
	function goPayMoney(){
		var payType = $('input:radio:checked').val();
		if(payType == '0'){
			weixinPay();
		}
		if(payType == '1'){
			payMyOrderGroup();
		}
	}
	
	function weixinPay(){
		$("#orderStateWeiXin").val($("#orderState").val());
		$("#orderIdWeiXin").val( $("#orderId").val());
		$("#versionWeiXin").val($("#version").val());
		$("#winxinForm").submit();
	}
	
	
	function payMyOrderGroup(){
		var orderStaus = $("#orderState").val();
		var orderGroupId = $("#orderId").val();
		var version = $("#version").val();
		layer.open({
		    content: '确定支付订单吗？',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	$.ajax({
			        type : "POST",
			        url : ctx+'/integralMallRecord/payIntegralMallRecord',
			        data : {
			        	orderState:orderStaus,
			        	orderGroupId:orderGroupId,
			        	version:version
			        },
			        success : function(response) {
				        	layer.close();   
							layer.open({
								content: '支付成功！',
								time: 1 //2秒后自动关闭
							});  
						window.location.href = ctx +'/integralMallRecord/index';
			        },
			        error : function(response) {
			        	layer.close();   
						layer.open({
							content: '支付失败，账户余额不足，请选用微信支付！',
							time: 2 //2秒后自动关闭
						});  
			        }
			    });
		    }, no: function(){
		    	layer.close();
		    }
		});
	  }
	
</script>
</html>