<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${dtoList}" varStatus="o">
	<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
	<div class="merchandise">
		<div class="info-title clear">
			<div class="pull-left">
				<i>订单号: </i><a href="javascript:;">${item.orderSerialNo}</a>
			</div>
			<div class="pull-left">
				<i>订单时间: </i><span> <liguo:dateFormatLabel value="${item.createDt}" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
			</div>  
		</div>
		<table class="col-10 stock">
			<thead>
				<tr>
					<th width="270">商品信息</th>
					<th width="66">单价</th>
					<th width="126">规格</th>  
					<th width="68">订购数量</th>
					<th width="91">小计</th>
					<th width="100">总计</th>
					<th width="97">订单状态</th>
					<th width="99">订单操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="witem" items="${item.wapOrderDetailDtoList}" varStatus="p">
				<c:if test="${p.index == 0}">
					<tr style="border-bottom:1px solid #eee;">
						<td width="270"><div class="info"><div class="pull-left info-text"><p>${witem.productName}/${witem.brandName}</p></div></div></td>
						<td width="66" id="td${witem.id}"><fmt:formatNumber value="${witem.price}" pattern="#,##0.00" ></fmt:formatNumber>元<br>
							<c:if test="${item.orderStatus==602 }">
								<a class="btn" href="javascript:void(0);" onclick="updateUnitPrice(${witem.id},${item.id},${witem.price})">改价</a><br>
							</c:if>	
						</td>
						<td class="text-ellipsis" width="126">${witem.spec}</td>
						<td width="68" id="buyNum${witem.id}">${witem.buyNum}</td>
						<td width="91" id="netAmount${witem.id}"><fmt:formatNumber value="${witem.buyNum * witem.price}" pattern="#,##0.00" ></fmt:formatNumber>元</td>  
						<td width="100" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<br><span id="item${item.id}">￥<fmt:formatNumber value="${item.totalMoney}" pattern="#,##0.00" ></fmt:formatNumber>
							元</span><liguo:dictLabel key="${item.orderType}" /><br>          
							<liguo:dictLabel key="${item.settleType}" />
						</td>
						<td width="97" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<span class="color_${item.orderStatus}">
								<liguo:dictLabel key="${item.orderStatus}" />
							</span>
						</td>
						<td width="99" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<c:if test="${item.orderStatus==602 }">
								<a class="btn" href="javascript:;" onclick="updatePrice(${item.id},${item.totalMoney})" id="${item.id}">改价</a><br>
							</c:if>		
								<a class="btn" href="javascript:;" onclick="orderGroupDetail('${item.id}')">订单详情</a><br>
								<!-- 待确认 --> 
								<c:if test="${item.orderStatus == 600}">
									<a class="btn" onclick="configOrderGroupShow('${item.id}');">确认订单</a><br>  
									<a class="btn" onclick="showCancleOrderDivlog('${item.id}');">取消订单</a><br>
								</c:if> 
								<!-- 待付款 --> 
								<c:if test="${item.orderStatus == 602}">
									<!-- 线上支付 -->            
									<!-- <a  href="#">改价</a><br>  -->
									
									<c:if test="${item.settleType == 240}">
										<!-- 线下支付 -->
										<a class="btn" href="javascript:;" onclick="showCancleOrderDivlog('${item.id}');">取消订单</a><br> 
									</c:if>
									<c:if test="${item.settleType == 242}">
										<!-- 线下支付 -->
										<a class="btn" href="javascript:;"  onclick="showCancleOrderDivlog('${item.id}');">取消订单</a><br> 
										<a class="btn" href="javascript:;"  onclick="configReceivePaymentShow('${item.id}');">确认到款</a><br>
									</c:if>
								</c:if> 
									<!-- 终止订单 -->
								<c:if test="${item.orderStatus == 608 || item.orderStatus == 610}">
									<a class="btn" href="javascript:;"  onclick="showStopOrderDivlog('${item.id}');">终止订单</a><br>
								</c:if>
								<!-- 待发货 -->
								<c:if test="${item.orderStatus == 608}">
										<a class="btn" href="javascript:;"  onclick="sendOutGoodsByShop('${item.id}');">发货</a><br>
								</c:if>
								<c:if test="${item.orderStatus == 618 }">
									<a class="btn" href="javascript:;"  onclick="configStopOrderGroupShop('${item.id}');">确认终止</a><br>
									<a class="btn" href="javascript:;" onclick="cancleStopOrderGroupShop('${item.id}');">取消终止</a><br>
								</c:if>
								<c:if test="${item.orderStatus == 616 }">
									<a class="btn" href="javascript:;"  onclick="cancleStopOrderGroupShop('${item.id}');">取消终止</a><br>
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${p.index != 0}">
						<tr>
							<td><div class="info"><div class="pull-left info-text"><p>${witem.productName}/${witem.brandName}</p></div></div></td>
							<td>${witem.price }元</td>
							<td>${witem.spec }</td>
							<td>${witem.buyNum }</td>
							<td>${witem.buyNum * witem.price }元</td>  
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:forEach>
<liguo:pagination dataDomId="key_saleOrderGroup_list" pageAction="serachSaleOrderGroup" />