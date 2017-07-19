<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="积分规则" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>
<body>
<%@include file="../common/header.jsp"%>   
<!-- 积分规则开始 -->
<div class="container">
	<img src="${ctx}/images/integral.jpg">	
	<div class="rule-warp">
		<div class="rule">
			<h3>1.什么是积分？</h3>
			<p>中国移动为答谢客户的支持和厚爱，推出积分计划回馈广大客户，您可以利用积分兑换中国移动为您精心准备的精美礼品。
			2007年，中国移动将推出更丰富、更实惠的礼品，带给您前所未有的回馈体验。</p>		
		</div>
		<div class="rule">
			<h3>2.三大品牌积分政策如何？</h3>
			<p>全球通客户有积分，可参加积分计划兑换相应礼品；动感地带客户有M值，可以参加M计划兑换相应礼品；
			神州行客户当前的积分可以兑换相应礼品，2007年1月1日起，神州行品牌将不再享受积分优惠</p>		
		</div>
		<div class="rule">
			<h3>3.怎么获得积分？</h3>
			<p>您的积分由消费积分和奖励积分两部分组成。全球通，动感地带客户每消费1元钱的移动业务积1分。
			（例：您上个月消费100元，您就得到100积分/M值。）同时，移动公司会根据您使用移动业务情况及入网时间等因素，不定期的赠送给您相应奖励积分。</p>		
		</div>
	</div>
</div>
<!-- 积分规则结束 -->
</body>
</html>