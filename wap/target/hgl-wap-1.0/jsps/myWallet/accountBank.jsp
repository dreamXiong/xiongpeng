<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="添加账户" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app-base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	<%@include file="../common/common.jsp"%>
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
	
		<c:if test="${fn:length(aList) < 5}">
		<div class="box-shadow kiting">   
			<form id="saveAccountForm"  method="post">
				
				<div class="form-group">
					<label for="">转账银行</label>
					<input type="text" maxlength="10" name="bank" id="bank"  placeholder="XX银行"/>
				</div>
				<div class="form-group"> <!--  onblur="addValidateAccount();"  -->
					<label for="">银行帐号</label>  
					<input type="text" maxlength="19" onblur="addValidateAccount();" name="bankAccount" id="bankAccount" placeholder="16~19位数字"/>
				</div>
				<div class="form-group">
					<label for="">支行</label>
					<input type="text" maxlength="10" name="branch" id="branch" placeholder="深圳市宝安区XX支行"/>
				</div>
				<input type="hidden" name="accountBankId" id="accountBankId" />
				<div class="form-group">
					<label for="">持卡人</label>
					<input type="text" maxlength="8" name="name" id="name" placeholder="张三"/> 
					<input type="hidden" name="accountId" id="accountId" value="${accountId}"/> 
				</div>
			</form>
			<a href="javascript:;" id="saveAccountBtn" class="button-make">确定</a>
		</div> 
		</c:if>
		<div style="background:#fff;padding:10px;">
			<!-- <span>已有银行</span><br> -->
			<h3>已有银行</h3>
			<c:forEach var="item" items="${aList}" varStatus="s3">
				<div style="font-size:12px;line-height:2em;" class="box">
					<span class="box2" style="display: block;">${item.bank}</span>
					<span class="box3" style="display: block;">${item.bankAccount}</span>
					<a href="javascript:;" onclick="deleteBankAccount(${item.id});" class="box1 text-right" style="display: block;">删除</a>
				</div>
			</c:forEach>
		</div>
	</div>        
	
</body>

<script>

	String.prototype.startWith=function(str){
		var reg=new RegExp("^"+str);
		return reg.test(this);
	} ;
	String.prototype.endWith=function(str){
		var reg=new RegExp(str+"$");
		return reg.test(this);
	} ;
	//js保留2位小数
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
	
	$('#saveAccountBtn').click(function(event) {   
		$("#accountBankId").val($("#selector").val());
		var validateFlag = true;
		var str = "银行";
		var bank = $("#bank").val();
		var bankAccountLen = $("#bankAccount").val().length;
		if($("#bankAccount").val() == '' || bankAccountLen < 16){
			$('#bankAccount').toggleClass('border-red'); 
			validateFlag = false;
		}
		var regs = /^[\u2E80-\u9FFF]+$/;
		
		if($("#bank").val() == '' || !bank.endWith(str)){
			$('#bank').toggleClass('border-red'); 
			validateFlag = false;
		}
		
		if($("#branch").val() == ''){
			$('#branch').toggleClass('border-red'); 
			validateFlag = false;
		}
		
		if($("#name").val() == ''){
			$('#name').toggleClass('border-red'); 
			validateFlag = false;
		}
		
		if(!addValidate){
			$('#bankAccount').toggleClass('border-red'); 
			validateFlag = false;
		}
		if(!regs.test($("#name").val())){
			$('#name').toggleClass('border-red'); 
			validateFlag = false;
		}
		if(!regs.test($("#branch").val())){
			$('#branch').toggleClass('border-red'); 
			validateFlag = false;
		}
		if(validateFlag == true){     
			var param = $("#saveAccountForm").serialize();
			$.ajax({
		        type : "POST",
		        url : ctx+'/myWallet/saveAccountBank',
		        data:param,
		        success : function(response) {
		        	layer.close();   
					layer.open({
						content: '添加成功！',
						time: 1 //2秒后自动关闭
					});  
					window.location.href = window.location.href;
		        }
		    }); 
		}
	});
	$('input').focus(function(){
		$('input').removeClass('border-red');
	});
	function deleteBankAccount(id){
		  layer.open({
			    content: '你确定要删除此账户信息吗？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	
			    	$.ajax({
				        type : "POST",
				        url : ctx+'/myWallet/deleteAccountBank',
				        data : {
				        	accountBankId:id
				        },
				        success : function(response) {
				        	layer.close();   
							layer.open({
								content: '删除成功！',
								time: 1 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
				        },error : function(){
				        	layer.close();   
							layer.open({
								content: '删除失败，该账户存在提现未审核记录！',
								time: 1 //2秒后自动关闭
							});  
				        }
				    }); 
			    }, no: function(){
			    	layer.close();
			    }
			}); 
	}
	
	function isNumber(s) {
	    var regu = "^[0-9]+$";
	    var re = new RegExp(regu);
	    if (s.search(re) != -1)
	    {
	        return true;
	    } else
	    {
	        return false;
	    }
	}
	var addValidate = true;
	function addValidateAccount(){
		var bankAccount = $("#bankAccount").val();
		if(!isNumber(bankAccount)){
			$('#bankAccount').toggleClass('border-red'); 
			$("#bankAccount").val("");
			return;
		}
		$.ajax({
	        type : "POST",
	        url : ctx+'/myWallet/addValidateAccount',
	        data:{
	        	bankAccount:bankAccount,
	        },
	        success : function(response) {
	        	if(response.code == 0){
	        		
	        	}else{
	        		$('#bankAccount').toggleClass('border-red'); 
	        		addValidate = false;
	        	}
	        }
		});
	}
</script>
</html>

