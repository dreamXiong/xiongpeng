<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>微信支付</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
</head>
<body onload="callPay()">
<form id="payFlagForm" method="post" action="">
	<input type="hidden" value="0" id="isPay"/>
</form>
	<script type="text/javascript">
		//去支付
		function onBridgeReady() {
			var appId = "${resultSortedMap['appId']}";  
			var timeStamp = "${resultSortedMap['timeStamp']}";  
			var nonceStr = "${resultSortedMap['nonceStr']}";  
			var Package = "${resultSortedMap['packageValue']}";  
			var signType = "${resultSortedMap['signType']}";  
			var paySign = "${resultSortedMap['paySign']}";  
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : appId, 
				"timeStamp" : timeStamp, 
				"nonceStr" : nonceStr, 
				"package" : Package, 
				"signType" : signType, 
				"paySign" : paySign, 
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request:ok") {      
					//alert("支付成功");   
					document.getElementById("isPay").value = "1";
					window.location.href = "${pageContext.request.contextPath}/weixinPay/payAfterPage?location=weixinPay/paySuccess";
				}       
				if (res.err_msg == "get_brand_wcpay_request:cancel") {      
					//alert("交易取消");  
					window.location.href = "${pageContext.request.contextPath}/weixinPay/payAfterPage?location=weixinPay/payCancel";
				}    
				if (res.err_msg == "get_brand_wcpay_request:fail") {  
					//alert("支付失败");     
					window.location.href = "${pageContext.request.contextPath}/weixinPay/payAfterPage?location=weixinPay/payFail";
				}   
			});  
		} 
		
		//调用支付监听
		function callPay() {
			//判断是否已经支付过了，防止重复支付
			if(document.getElementById("isPay").value == "1"){             
				window.location.href = "${pageContext.request.contextPath}/weixinPay/payAfterPage?location=weixinPay/completePay";
				return;
			}
			//调用支付
			if (typeof WeixinJSBridge == "undefined") {
				if (document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady',onBridgeReady, false);
				} else if (document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		}
	</script>
</body>
</html>