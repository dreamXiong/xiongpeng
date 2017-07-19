<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="微信充值" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app-base.css">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/common/common.js"></script>  
	<script src="${pageContext.request.contextPath}/js/common/publicCheckFormat.js"></script>  
	<script src="${pageContext.request.contextPath}/js/common/jquery.linkon.web.js"></script>  
</head>	
<body>
<style>
	.border-red:hover,.border-red:focus{
		border-color:rgb(102,175,233);
		box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	}
	select{
		 appearance:none;
		-moz-appearance:none; 
		-webkit-appearance:none;
	}
	.form-group .icon-plus{
		position:absolute;
		line-height:40px;
		width:40px;
		text-align:center;
		top:1px;
		right:1px;
	}
	
	
</style>	
<%@include file="../common/header.jsp"%>
	<div class="container">
		<div class="box-shadow kiting">   
			<form id="submitForm" action='${ctx}/weixinPay/recharge' method="post">
				<div class="form-group">
					<label for="">充值金额</label>
					<input type="text" maxlength="8" name="money" id="money" class="float" /> 
					<input type="hidden" name="accountId" id="accountId" value="${tbAccount.id}" />
				</div>
			</form>
			<a href="javascript:;" id="rechargeBtn" class="button-make">确定</a>
			<form action="${ctx}/myWallet/accountBank" id="accountBankSub" method="post">
				<input type="hidden" name="accountId" id="accountId" value="${accountId}" />
			</form>
		</div>
	</div>
</body>

<script>
	function toDecimal2(x) {   
		var f = parseFloat(x); 
		if (isNaN(f)) {
			return false;       
		}       
		var f = Math.round(x*100)/100;    
		var s = f.toString();      
		var rs = s.indexOf('.'); 
		if (rs < 0) {      
			rs = s.length;      
			s += '.';    
		}       
		while (s.length <= rs + 2) {  
			s += '0';      
		}       
		return s;     
	}
	
	String.prototype.startWith=function(str){
		var reg=new RegExp("^"+str);
		return reg.test(this);
	} ;
	String.prototype.endWith=function(str){
		var reg=new RegExp(str+"$");
		return reg.test(this);
	} ;
	function isNull(str) {
	    if (str == "")
	        return true;
	    var regu = "^[ |\\n|\\r]+$";
	    var re = new RegExp(regu);
	    return re.test(str);
	}
	$('#rechargeBtn').click(function(event) {   
		
		var validateFlag = true;
		
		var rechargeMoney = $("#money").val()
		
		var re = /^[0-9]+.?[0-9]*$/;
		
		if(!re.test(rechargeMoney)) {
			$('#money').toggleClass('border-red'); 
			return;
		}
		if(rechargeMoney < 0.0000001 || isNull(rechargeMoney)){
			$('#money').toggleClass('border-red'); 
			validateFlag = false;
		}
		var nMoney = rechargeMoney;
		var pmoney = parseFloat(nMoney);
		var dmoney = toDecimal2(pmoney);
		$("#money").val(dmoney);
		if(validateFlag == true){     
			$("#submitForm").submit();
		}
	});
	$('input').focus(function(){
		$('input').removeClass('border-red');
	});
	function accountBank(){
		$("#accountBankSub").submit();
	}
</script>
</html>

