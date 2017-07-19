<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="${orderServiceDto.serciceName}" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>
	<%@include file="../common/common.jsp"%>  
	<script src="${pageContext.request.contextPath}/js/common/common.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/jquery.linkon.web.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/publicCheckFormat.js"></script>
</head>
<body>                 
<%@include file="../common/header.jsp"%>
<div class="container" style="padding-bottom:50px;">
	<div class="mylist mylist-down">
		<ul class="box-shadow">
			<li id="time" class="make-time list-two">       
				<a href="javascript:;">             
					<img src="${ctx }/images/icon-16.png" alt="">
					<span><liguo:dateFormatLabel
						value="${orderServiceDto.appointmentDt}" pattern="yyyy.MM.dd" /> ${orderServiceDto.appointmentPeriodDt}</span>
					<i class="right"></i>
				</a>
				<div class="make-select" >        
					<dl>         
						<dt>${dateOne }</dt>
						<dd id="1,1" ${hour >0 ? 'class=bg-gray' : '' }>08:00-12:00 (正常收费)</dd>
						<dd id="1,2" ${hour >1 ? 'class=bg-gray' : '' }>12:00-19:00 (正常收费)</dd>
						<dd id="1,3" ${hour >2 ? 'class=bg-gray' : '' }>19:00-23:00 (收费稍贵)</dd>
					</dl>
					<dl>
						<dt>${dateTwo }</dt>
						<dd id="2,1">08:00-12:00 (正常收费)</dd>
						<dd id="2,2">12:00-19:00 (正常收费)</dd>
						<dd id="2,3">19:00-23:00 (收费稍贵)</dd>
					</dl>
					<dl>
						<dt>${dateThree }</dt>
						<dd id="3,1">08:00-12:00 (正常收费)</dd>
						<dd id="3,2">12:00-19:00 (正常收费)</dd>
						<dd id="3,3">19:00-23:00 (收费稍贵)</dd>
					</dl>
				</div>
			</li>
			<li class="list-two" onclick="modifyMyAddress()">
				<a href="#">    
					<input type="hidden" id="lon" name="lon" value="${wapAddress.lon }"/>                
					<input type="hidden" id="lat" name="lat" value="${wapAddress.lat }"/>                 
					<img src="${ctx }/images/icon-17.png" alt="">
					<c:choose>
						<c:when test="${empty wapAddress.extensionField}">
							<span>添加/选择地址</span>   
						</c:when>
						<c:otherwise>
							<span>${wapAddress.extensionField }</span>
						</c:otherwise>
					</c:choose>
					<i class="right"></i>
				</a>
			</li>       
		</ul>
		<ul class="box-shadow" style="padding-top:10px;">
			<li>
				<div class="form-group">
					<input type="text" placeholder="请输入您的名称" value="${wapAddress.recipient }" id="recipientInput" maxlength="5">
					<img src="${ctx }/images/icon-14.png" alt="">
				</div>
			</li>
			<li>
				<div class="form-group">
					<input type="number" placeholder="请输入您的号码" value="${wapAddress.phone }" id="phoneInput">    
					<img src="${ctx }/images/icon-15.png" alt="" style="width:14px;">
				</div>
			</li>
			<li>                                        
				<div class="form-group">
					<input type="text" placeholder="请输入您设备的大致状况（选填）" value="${orderServiceDto.message}" id="message">
					<img src="${ctx }/images/icon-13.png" alt="">
				</div>
			</li>
		</ul>
	</div>
	<div class="referral">
		<h3>服务范围</h3>
		<div style="padding:10px 0;background:#fff;">
			<p style="margin:0;">上门服务时间：8：00 - 23：00</p>
			<p style="margin:0;">19：00-23：00为夜间服务段，需加收20元夜间服务费。</p>
		</div>
	</div>
	<div class="referral">
		<h3>服务特色</h3>
		<div>
			<div class="box">
				<div class="box1">
					<img src="${ctx }/images/15.jpg" alt="">
				</div>
				<div class="box1">
					<img src="${ctx }/images/16.jpg" alt="">
				</div>
			</div>	
			<div class="box">
				<div class="box1">
					<img src="${ctx }/images/17.jpg" alt="">
				</div>
				<div class="box1">
					<img src="${ctx }/images/18.jpg" alt="">
				</div>
			</div>	
		</div>
	</div>
	<div class="referral">
		<h3>用户保障</h3>
		<div class="box">
			<div class="box1">
				<img src="${ctx }/images/19.jpg" alt="">
			</div>
		</div>
	</div>           
</div>

<!-- 提交订单form -->
<form id="submitOrderForm" method="post" action="${ctx }/wapOrderService/updateOrderService">
	<input type="hidden" value="${wapAddress.id }" id="addressId" name="addressId"/>
	<input type="hidden" value="" id="recipient" name="recipient"/>
	<input type="hidden" value="" id="phone" name="phone"/>
	<input type="hidden" value="${orderServiceDto.cityCode}" id="cityCode" name="cityCode"/>
	<input type="hidden" value="" id="message" name="message"/>
	<input type="hidden" value="${orderServiceDto.id }" id=oderServiceId name="oderServiceId"/>
	<input type="hidden" value="${orderServiceDto.serciceId }" id="serviceTypeId" name="serviceTypeId"/>
	<input type="hidden" value="" id="appointments" name="appointments"/>
	<input type="hidden" value="0" id="isSubmit"/>
</form>
<footer class="footer serve-foot">
	<button type="button" id="submitOrderButton">修改订单</button>
</footer>

<!-- 修改地址 -->
<form id="updateMyaddressForm" method="post" action="${ctx }/wapOrderService/showMyAddress?updateServiceId=${param.id}">
	<input type="hidden" value="${wapAddress.id }" id="addressId" name="addressId"/>
	<input type="hidden" value="${orderServiceDto.serciceId }" id="serviceTypeId" name="serviceTypeId"/>
	<input type="hidden" value="" id="messageStr" name="messageStr"/>
	<input type="hidden" value="" id="appointmentsStr" name="appointmentsStr"/>
	<input type="hidden" value="" id="appointmentsStrId" name="appointmentsStrId"/>
</form>
</body>
</html>
<script>
$(function() {
	$('#time').click(function(event) {
		if($('.make-select').is(':hidden')){
			$('.make-select').slideDown(300);
		}else{
			$('.make-select').slideUp(300);
		}	
	});
	$('#time dt').click(function(event) {
		event.stopPropagation();
	});
	$('#time dd').click(function(event) {
		if ($(this).hasClass('bg-gray')) {         
			return false;
		}else{
			$('#time dd').removeClass('active');
			$(this).addClass('active');
			var dt_text = $(this).siblings('dt').text();
			var dd_text = $(this).text();
			$("#submitOrderForm #appointments").val($(this).attr("id"));
			$('#time span').text(dt_text+' '+dd_text);
		};
	});
});

//修改地址跳转
function modifyMyAddress(){
	if($("#appointmentsStrSpan").text() != '请选择预约时间'){
		$("#updateMyaddressForm #appointmentsStr").val($("#appointmentsStrSpan").text());
		$("#updateMyaddressForm #appointmentsStrId").val($("#submitOrderForm #appointments").val());
	}
	if($("#message").val() != ''){
		$("#updateMyaddressForm #messageStr").val($("#message").val());
	}
	$("#updateMyaddressForm").submit();
}

//提交订单
$(document).delegate('#submitOrderButton','click',function(event) { 
	if($("#submitOrderForm #isSubmit").val() == '0'){     
		var serviceTypeId = $("#submitOrderForm #serviceTypeId").val();
		var addressId = $("#addressId").val();
		var recipientInput = $("#recipientInput").val();
		var phoneInput = $("#phoneInput").val();
		var messageInput = $("#message").val();
		if(serviceTypeId == undefined || serviceTypeId == ''){
		 	layer.open({
		 		content: '服务类型为空,下单失败',
		 		time: 2
			});  
			return;
		}
		/* if(addressId == undefined || addressId == ''){
		 	layer.open({
		 		content: '请添加/选择地址',
		 		time: 2
			});  
			return;
		} */
		if(recipientInput == undefined || recipientInput == ''){
		 	layer.open({
		 		content: '请填写名称',
		 		time: 2
			});  
			return;
		}
		if(phoneInput == undefined || phoneInput == ''){
		 	layer.open({
		 		content: '请填写手机号码',
		 		time: 2
			});  
			return;
		}
		if(checkMobile(phoneInput) || isTel(phoneInput)){
		}else{
			layer.open({
		 		content: '手机号码格式不正确',
		 		time: 2
			}); 
			return;
		}
		$.ajax({                       
	        type: "POST",
	        url: "../wapOrderService/checkOpenCity",
	        data: "lon="+$("#lon").val()+"&lat="+$("#lat").val()+"&random="+Math.random(),
	        success: function(response){     
	        	if(response.isOpenCiry){
					$("#submitOrderForm #serviceTypeId").val(serviceTypeId); 
					//$("#submitOrderForm #addressId").val(addressId); 
					$("#submitOrderForm #recipient").val(recipientInput); 
					$("#submitOrderForm #phone").val(phoneInput); 
					$("#submitOrderForm #message").val(messageInput); 
					$("#submitOrderForm #cityCode").val(response.cityCode); 
					$("#submitOrderForm #isSubmit").val('1');  
					$('#submitOrderForm').submit();
	        	}else{
	        		alert('当前城市未开通服务!');
	        	}
	        },
	       
	    });
	}else{
		layer.open({
	 		content: '订单已提交',
	 		time: 2
		});  
	}
});
</script>