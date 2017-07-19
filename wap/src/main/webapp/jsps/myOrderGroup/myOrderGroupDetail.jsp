<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="材料订单详情" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/hgl/myOrderGroup.js"></script>
</head>	
<body class="ordbodyer">
<%@include file="../common/header.jsp"%>
<div class="orderdetail-foot text-center">
	订单总金额：
	<span class="dollar">¥</span>
	<span class="unit">${orderGroupDetailDto.totalMoney+orderGroupDetailDto.postage}</span>
</div>
<form id="goPickDetail" method="post" action="${ctx}/pick/pickDetail">
	<input name="id" value="" type="hidden" id="productId"/>
</form>
<div class="container orderdetail" style="margin-bottom:0;padding-bottom:50px;" >
	<div class="box-shadow">
		<p>订单编号：${orderGroupDetailDto.orderSerialNo }</p>
		<p>订单状态：<span class="text-red"><liguo:dictLabel key="${orderGroupDetailDto.orderStatus}" /></span></p>
		
		<p>邮<span style="opacity:0;">邮费</span>费：<span class="text-red">¥${orderGroupDetailDto.postage }</span></p>	
	</div>
	<div class="box-shadow">
		<c:forEach var="item" items="${tList}" varStatus="s">
			<p><liguo:dateFormatLabel value="${item.operateDt}" pattern="yyyy-MM-dd  HH:mm:ss" /> &nbsp;&nbsp; ${item.operateName} &nbsp;&nbsp; ${item.userName}</p>
		</c:forEach>
	</div>	
	
	<div class="box-shadow">   
		<h3>收货方式<span><liguo:dictLabel key="${orderGroupDetailDto.platFlag}" /></span></h3>
		<div class="delivery">
			<div class="clearfix">
				<span class="pull-left">${orderGroupDetailDto.recipient }</span>
				<span class="pull-right">${orderGroupDetailDto.phone }</span>
			</div>
			<div>${orderGroupDetailDto.extensionField }</div>
		</div>
	</div>
	<div class="container container-order" style="padding-bottom:0;">
		<c:forEach var="item" items="${dtoList}" varStatus="s">
		<div class="cart-shopping box-shadow" style="padding:0;" >
			<div class="shopping-head" style="padding:0 10px;">
				<div class="clearfix">
					<div onclick="goShop('${item.shopId}','${item.balance}');" style="width:100%;border-bottom:1px solid #eee;"><span class="icon-home" style="color:#f2000e;"></span> ${item.companyName}</div>
				</div>
			</div>
			<ul class="shopping-lists order-list">
			<c:forEach var="itemw" items="${item.wapOrderDetailDtoList}" varStatus="w">
				<li class="clearfix" style="border-bottom:1px solid #eee;">
					<div class="item1">
						<img onclick="productDetail('${itemw.productId}');" src="${ctx}/pick/generateImage?id=${itemw.productId}&imgName=${itemw.pImgOne}" alt="">
					</div>   
					<div class="item2">
						<h3 style="border-bottom:none;">${itemw.productName }/${itemw.brandName }</h3>  
						<dl>
							<dt><span class=" icon-angle-right" style="margin-top:3px;"></span>规格：${itemw.material}</dt>
							<dd>材质：${itemw.spec}</dd>
							<c:forEach var="attr" items="${itemw.attrList}" varStatus="w">   
								<c:forEach var="attrMap" items="${attr}" varStatus="aa">
									<dd>${attrMap.key}：${attrMap.value}</dd>
								</c:forEach>
							</c:forEach>
						</dl>                      
						<div class="clearfix operate" style="padding-top:0;">
							<div class="pull-left text-red font-bold">
								<span class="dollar">¥</span>
								<span class="unit">${itemw.price}</span>
								<!-- <span class="mark">00</span> -->
							</div>
							<div class="pull-right">X${itemw.buyNum}</div>
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</c:forEach>
	</div>
	<c:if test="${not empty orderGroupDetailDto.message }">
		<div class="box-shadow">
			<h3>买家留言</h3>
			<div>
				${orderGroupDetailDto.message }
			</div>
		</div>
	</c:if> 
	<c:if test="${not empty orderGroupDetailDto.evaluateStart }">
		<div class="box-shadow">
			<h3>买家评论</h3>
			<div class="star">
				<ul>
					<li id="evaluateInfo" style="margin-left: 0px;">
						<nav style="left:0;">
						  <c:forEach var="i" begin="1" end="${orderGroupDetailDto.evaluateStart }">
							<a href="javascript:;" class="active"></a>   
						  </c:forEach>
						</nav>
					</li>
				</ul>
			</div>
			<div>
				${orderGroupDetailDto.evaluate }
			</div>
		</div>
	</c:if>
	<c:if test="${not empty orderGroupDetailDto.stopReason }">
		<div class="box-shadow">
			<h3>订单终止原因</h3>
			<div>
				${orderGroupDetailDto.stopReason }
			</div>
		</div>
	</c:if>
	
</div>

</body>
</html>