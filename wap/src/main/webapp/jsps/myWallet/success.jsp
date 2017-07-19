<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="我的钱包" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	<%@include file="../common/common.jsp"%>
</head>	
<body>             
<%@include file="../common/header.jsp"%>
<div class="container" >              
	<h3 class="text-center" style="padding-top:20px;margin-bottom:15px;">        
		<span class="icon-ok-sign" style="color:#11CD6D;font-size:30px;display:inline-block;vertical-align: middle;"></span>      
		恭喜你提现申请成功！！
	</h3>
	<div class="text-center">
		<a href="javascript:;" onclick="withdrawalsMoney();" style="border:1px solid #aaa;border-radius:5px;padding:5px 8px;">回到提现</a>	
		<a href="javascript:;" onclick="withdrawalsMoney();" style="border:1px solid #aaa;border-radius:5px;padding:5px 8px;">回到找w</a>	
	</div>	    
</div>	        
	<form id="withdrawalsMoney" action="${ctx}/myWallet/withdrawals" method="post">
		<input type="hidden" name="accountId" value="${accountId}" />
	</form>
</body>
<script>
	function withdrawalsMoney(){
		$("#withdrawalsMoney").submit();	
	}
</script>
</html>