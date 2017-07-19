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
	<script src="${pageContext.request.contextPath}/js/common/slidePage.js"></script>
</head>	
<style>

.n_deposit {
    border: 1px solid #45C018;
    border-radius: 5px;
    height: 30px;
    line-height: 30px;
    position: absolute;  
    right: 10px;
    text-align: center;
    top: 60px;
    width: 90px;
    z-index: 100;
    background:#45C018;
    
}
.n_deposit a{color:#fff;}            

.prolist li{line-height:32px;font-size:0.9rem;background:#fff;padding:0 10px;}  
.prolist li a{color:#333;font-size:16px;}  
/**加载效果旋转**/  
      @-webkit-keyframes rotate {0% {-webkit-transform: rotate(0deg);transform: rotate(0deg);}100% {-webkit-transform: rotate(360deg);transform: rotate(360deg);}}  
      @keyframes rotate {0% {-webkit-transform: rotate(0deg);transform: rotate(0deg);}100% {-webkit-transform: rotate(360deg);transform: rotate(360deg);}}  
        
      .loadmore {display:block;line-height: 50px;text-align:center;color:#ccc;font-size:14px;}  
      .loadmore span{height:20px;width:20px;border-radius:100%;display:inline-block;margin:10px;border:2px solid #f60;border-bottom-color: transparent;vertical-align: middle;-webkit-animation: rotate 1.1s infinite linear;animation: rotate 1.1s infinite linear;}  
      .loadover{position:relative;margin:0 12px;padding:5px 0;color:#909090;text-align: center;}  
      .loadover span{position:relative;display:inline-block;padding:0 6px;height:20px;background:#F2F2F2;z-index:2}  
      .loadover:after {content:''position: absolute;left: 0;top:50%;width: 100%;height:1px;background:#DCDCDC;z-index:1;display:block;}
      .integral  p{line-height:24px;}
</style>
<body>
<div class="no-shadow">
	<%@include file="../common/header.jsp"%>
</div>
<div class="container" style="margin-bottom:0;position:fixed;top:0;width:100%;z-index:9999;box-shadow:none;">
	<div class="integral  money" style="padding-bottom:0;">      
		<c:if test="${money gt 0.0}">
			<div class="deposit">
					<a href="#" onclick="withdrawalsMoney();" style="color:#aaa;">提现</a>
			</div>
		</c:if>
		<div class="n_deposit">
			<a href="${ctx }/myWallet/rechargePage">微信充值</a>
		</div>	
		<p>可用金额：<span class="text-red">${money }</span>元</p>
		<p>冻结金额：<span class="text-red"><fmt:formatNumber value="${tbCashAccount.freeze }" pattern="#,##0.00" ></fmt:formatNumber></span>元</p>
		<p>账户总额：<span class="text-red"><fmt:formatNumber value="${tbCashAccount.balance }" pattern="#,##0.00" ></fmt:formatNumber></span>元</p>
		<h3>资金明细</h3>
		<div class="col-10 text-center box-head" style="padding:10px 0;border-bottom:1px solid #eee;">
			<div class="col-3">时间</div>        
			<div class="col-2">类型</div>       
			<div class="col-3">订单号</div>                 
			<div class="col-2">金额</div>
		</div>
	</div>           
	
	<input type="hidden" value="${pageCount}" id="pageCount" />
	<input type="hidden" value="0" id="pageIndex" />
	
	<form id="withdrawalsMoney" action="${ctx}/myWallet/withdrawals" method="post">
		<input type="hidden" name="accountId" value="${tbCashAccount.id}" />
	</form>
	
	<form action="${ctx}/wapOrderGroup/orderGroupDetail" method="post" id="orderGroupDetailForm">
		<input type="hidden" id="orderId" name="orderId" value=""/>
		<input type="hidden" id="balance" name="balance" value=""/>
	</form>
	
</div>
<ul class="prolist" >
	<%@include file="myWalletList.jsp"%>
</ul>


</body>
<script>
	function withdrawalsMoney(){
		$("#withdrawalsMoney").submit();	
	}
	function selectInfo(pageIndex){
		$.ajax({
		      type: "POST",
		      async: false,
		      url: ctx+"/myWallet/tbAccountPage",  
		      data:{
		    	  pageIndex:pageIndex,
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
	function orderGroupDetail(id,balance){
		$("#orderGroupDetailForm #orderId").val(id);
		$("#orderGroupDetailForm #balance").val(balance);
		$("#orderGroupDetailForm").submit();
	}
</script>
</html>