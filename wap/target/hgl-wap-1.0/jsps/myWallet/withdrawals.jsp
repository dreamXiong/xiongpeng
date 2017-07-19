<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="提现" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app-base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">  
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
<input type="hidden" maxlength="18" name="maxMoney" id="maxMoney" value="${money}" />
	<div class="container">
		<div class="box-shadow kiting">   
			<form id="submitForm" action='${ctx}/myWallet/withdrawalsSave' method="post">
				<input type="hidden" name="accountBankId" id="accountBankId" />
				<div class="form-group">
					<label for="">银行帐号</label>
					<select id="selector" style="line-height:40px;padding-left:70px;border-radius:5px;height:40px;background:transparent;">
						 <c:forEach var="item" items="${tList}" varStatus="s3">  
							<option value="${item.id }">${item.bank }/${item.bankAccount }</option>
						</c:forEach>
					</select>
						<span class="icon-plus" onclick="accountBank();"></span>	
				</div>        
				<div class="form-group">
					<label for="">提现金额</label>
					<input type="text" maxlength="8" name="money" id="money" class="float" placeholder="本次最多转出${money}"/> 
					<input type="hidden" name="accountId" id="accountId" value="${tbAccount.id}" />
				</div>
			</form>
			<a href="javascript:;" id="withdrawalsBtn" class="button-make">确定</a>
			
			<form action="${ctx}/myWallet/accountBank" id="accountBankSub" method="post">
				<input type="hidden" name="accountId" id="accountId" value="${tbAccount.id}" />
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
	$('#withdrawalsBtn').click(function(event) {   
		var accountBankId = $("#selector").val();
		$("#accountBankId").val(accountBankId);
		var validateFlag = true;
		var money = $("#money").val();    
		$("#money").val(money); 
		if(money < 0.0000001 || accountBankId == null){
			return;
		}
		var maxMoney = $("#maxMoney").val();
		var t=/^\d+(\.\d+)?$/; 
		if(money == '' || !t.test(money) || !(maxMoney - money >= 0)){
			$('#money').toggleClass('border-red'); 
			validateFlag = false;
		}
		$("#money").val(toDecimal2(money));
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

