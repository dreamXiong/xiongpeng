<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<c:set value="商品详情" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>
<body style="background:#fff;">
	<%@include file="../common/header.jsp"%>
	<div class="container" style="margin-bottom:0;padding-bottom:50px;">
		<div class="swiper-container outlet" id="serve">
	    	<div class="swiper-wrapper">
		         <c:if test="${not empty integralMall.goodsImage}">
	               <div class="swiper-slide"><img src='${ctx }/integralMall/generateImage?id=${integralMall.id}&imgName=${integralMall.goodsImage}' 
	               style="height:200px;"/></div>
	             </c:if>
	   		</div>	       
		</div> 
		<div id="int-com">
			<div style="position:relative;"> 
				<h3>${integralMall.goodsName }</h3> 	                      
				<div style="padding:10px 0;">
					 <span style="color:red;font-size:1.4rem;font-family:'arial'",>${integralMall.integral} </span> 积分           
				</div>
				<div style="font-size:0.9rem;color:#333;">
					兑换后支付: ${integralMall.payAmount } 元
				</div>
				<div style="font-size:0.8rem;color:#666;">
					市场参考价: ${integralMall.marketAmount } 元
				</div>	
				
				<%-- <span style="font-size: 13px;color: red;"><b>${integralMall.integral}</b></span>&nbsp;积分<br/>       --%>                  
				<%-- <span>市场参考价: ${integralMall.marketAmount }&nbsp;元</span><br/>      --%>
				<c:if test="${integral.integral >= integralMall.integral}">
					<div class="number" style="position:absolute;right:10px; top:30px;">
						<input type="hidden" id="exchangeIntegral" value="${integralMall.integral }" />
						<input type="hidden" id="currentIntegral" value="${integral.integral }" />      
						<a href="javascript:;" class="min" id="min" onclick="addOrSubtract('subtract')"></a>
						<input type="number" value="1" class="input" id="exchangeNum" maxlength="3" readonly="readonly"/>
						<a href="javascript:;" class='add' id="add" onclick="addOrSubtract('add')"></a>
					</div>
				</c:if>     
			</div> 
		</div> 
			<div>                 
				<h3 class="bg-gray" style="padding:8px 10px;font-size:12px;color:#999;">商品描述:</h3>
				<div class="particulars" style="padding:10px;font-size:0.8rem;color:#666;line-height:1.2rem;white-space: pre-line;">${integralMall.goodsDescribe }</div>
				<c:if test="${not empty integralMall.attentionMatters }">
					<p>注意事项<p/>  
					<div>${integralMall.attentionMatters }</div>
				</c:if>
				<c:if test="${not empty integralMall.exchangeProcess }">
					<p>兑换流程<p/>
					<div>${integralMall.exchangeProcess }</div>
				</c:if>
			</div>  
		 
	</div>
<c:if test="${session_key.typeId!=104}">	
	<footer class="footer serve-foot">
		<c:if test="${integralMall.integral > integral.integral }">
			<button type="button" disabled="disabled" style="background:#ccc;">积分不足</button>
		</c:if>
		<c:if test="${integral.integral >= integralMall.integral}">
			<button type="button" onclick="$('#integralExchangeForm').submit();">我要兑换</button>        
		</c:if>
	</footer>
</c:if>
<!-- 我要兑换form -->
<form id="integralExchangeForm" method="post" action="${ctx }/integralMall/integralExchange">
	<input type="hidden" value="${integralMall.id }" id="integralMallId" name="integralMallId"/>
	<input type="hidden" value="1" id="exchangeNumber" name="exchangeNumber"/>
</form>
</body>
</html>
<script type="text/javascript">
//单击加或者减操作
function addOrSubtract(numType){
	validateExchangeNum();
	var currentNum = $("#exchangeNum").val();
	currentNum = parseInt(currentNum);
	if(numType == 'add'){ //增
		if(currentNum < 999){    
			currentNum = currentNum + 1;
		}
	}else{ //减
		if(currentNum > 1){
			currentNum = currentNum - 1;
		}
	}
	var exchangeIntegral = $("#exchangeIntegral").val();
	var currentIntegral = $("#currentIntegral").val();
	var totalExchangeIntegral = currentNum*exchangeIntegral;
	if(totalExchangeIntegral>currentIntegral){
		layer.open({
	 		content: '您的积分不足',
	 		time: 2
		});
		currentNum = 1;
	}
	$("#exchangeNum").val(currentNum);
	$("#exchangeNumber").val(currentNum);
}

//输入数量校验
function validateExchangeNum(){
	var currentNum = $("#exchangeNum").val();
	if(currentNum == ''){
		$("#exchangeNum").val(1);
		return;
	}
	var t=/^\d+(\.\d+)?$/; 
	if(t.test(currentNum) == false){
		$("#exchangeNum").val(1);
		return;
	}
	if(currentNum.length >3){
		$("#exchangeNum").val(1);
		return;
	}
	if(parseInt(currentNum) != currentNum){
		$("#exchangeNum").val(1);
		return;
	}
}
</script>