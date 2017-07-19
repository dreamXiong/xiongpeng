<%@page pageEncoding="UTF-8"%>

<c:if test="${empty dtoList }">
	<div class="container">
		<img src="${ctx}/images/null2.png" alt="">
	</div>
</c:if>

<c:forEach var="item" items="${dtoList}" varStatus="s">
		<div class="cart-shopping box-shadow" >
			<div class="shopping-head">
				<div class="clearfix box">
					<div class="box5"><a  onclick="goShop(${item.shopId},${item.balance});"><span class="icon-home" style="color:#f2000e;"></span> ${item.companyName}&nbsp;<span class="icon-angle-right"></a></span></div>
					<div class="box1"><span><liguo:dictLabel key="${item.orderStatus}" /></span></div>
				</div>
			</div>
			<ul class="shopping-lists order-list">
			<c:forEach var="itemw" items="${item.wapOrderDetailDtoList}" varStatus="w">
				<li class="clearfix">
					<div class="item1">
						<img onclick="productDetail(${itemw.productId});" src="${ctx}/pick/generateImage?id=${itemw.productId}&imgName=${itemw.pImgOne}" alt="">
					</div>   
					<div class="item2">
						<h3>${itemw.productName }/${itemw.brandName }</h3>  
						<dl>
							<dt><span class=" icon-angle-right"></span>规格：${itemw.material}</dt>
							<dd>材质：${itemw.spec}</dd>
							<c:forEach var="attr" items="${itemw.attrList}" varStatus="w">
								<c:forEach var="attrMap" items="${attr}" varStatus="aa">
									<dd>${attrMap.key}：${attrMap.value}</dd>
								</c:forEach>
							</c:forEach>
						</dl>
						<div class="clearfix operate" >
							<div class="pull-left text-red font-bold">
								<span class="dollar">¥</span><!--postage -->
								<span class="unit">${itemw.price}</span>
							</div>
							<div class="pull-right">X${itemw.buyNum}</div>
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
			<div class="shopping-foot">
				<div class="order-top font-bold">
					<span>共 <em>${item.totalNum}</em>件,</span>
					<span>合计: ¥ <em>
					<fmt:formatNumber value=" ${item.totalMoney + item.postage}" pattern="#,##0.00" ></fmt:formatNumber></em></span>
					<c:if test="${not empty item.postage && item.postage !=0}">
						<span>(含运费 ¥ <em>${item.postage}</em> )</span>
					</c:if>
				</div>
				<div class="text-right">
				<input type="hidden" value="${item.totalMoney}" id="money${item.id}">
				<input type="hidden" value="${item.postage}" id="postage${item.id}">
				<%-- <a href="javascript:javascript:void(0);" class="cost" onclick="initUpdatePrice(${item.id},${item.totalMoney+item.postage})">改价</a> --%>
				<a href="#" onclick="orderGroupDetail('${item.id}','${item.balance}')">订单详情</a>
				<!-- 待确认 --> 
				<c:if test="${item.orderStatus == 600}">
					<a href="javascript:;" id="${item.id}" name="${item.version}" class="off">取消订单</a>
				</c:if> 
				
				<!-- 待付款 --> 
				<c:if test="${item.orderStatus == 602}">
					<!-- 线上支付 -->
					<c:if test="${item.settleType == 240}">
						<a href="javascript:;" id="${item.id}" name="${item.version}" class="off">取消订单</a>
						<a href="javascript:;" onclick="payMyOrderGroupShow('${item.id}','${item.version}');">去支付</a>
					</c:if>
					<!-- 线下支付 -->
					<c:if test="${item.settleType == 242}">
						<a href="javascript:;" id="${item.id}" name="${item.version}" class="off">取消订单</a>
					</c:if>
				</c:if> 
				
				<!-- 终止订单 --> 
				<c:if test="${item.orderStatus == 608 || item.orderStatus == 610 }">
					<a href="javascript:;" id="${item.id}" name="${item.version}" class="stop">终止订单</a>
				</c:if> 
				<c:if test="${item.orderStatus == 610 }">
					<a href="javascript:;"  onclick="configGoodsReceipt('${item.id}','${item.version}');">确认收货</a>
				</c:if> 
				 <!-- 取消终止订单 --> 
				 <c:if test="${item.orderStatus == 618 }">
					<a href="javascript:;" onclick="cancleStopOrderGroup('${item.id}','${item.version}');">取消终止</a>
				</c:if>
				
				<c:if test="${item.orderStatus == 616 }">  
					<a href="javascript:;" onclick="configStopOrderGroup('${item.id}','${item.version}');">确认终止</a>
					<a href="javascript:;" onclick="cancleStopOrderGroup('${item.id}','${item.version}');">取消终止</a>
				</c:if>
				<c:if test="${item.orderStatus == 612 }">  
					<a href="javascript:;" onclick="goevaluate('${item.shopId}','${item.id}');">去评价</a>
				</c:if>
				<c:if test="${item.orderStatus == 606 || item.orderStatus == 620}">  
					<a href="javascript:;" onclick="deleteOrderGroup('${item.id}','${item.version}');">删除订单</a>
				</c:if>
				</div>
			</div>
		</div>
		</c:forEach>