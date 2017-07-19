<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${goodsOrderList}" varStatus="i">
	<c:forEach var="proDetail" items="${item.productInfoList}" varStatus="j">
		<div class="chart-list">
			<div class="chat-title clear verify-title">
				<div class="pull-left pull-icon verify-icon">
					<span class="iconfont shrink shrink1">&#xe61d;</span>
				</div>
				<div class="pull-left pull-text">
					<p><span>${item.brandName }</span> -- <span>${proDetail.productName }</span> -- <small>${proDetail.productTypeName }</small></p>
				</div>
			</div>
			<div class="table table-none">
				<table class="col-10" id="t${proDetail.productId}j">
					<thead>
					<tr>
						<th width="43" style="display: none;"><input type="checkbox" class="check" name="rootCheckboxt${proDetail.productId}j" checked="checked"></th>
						<th width="214">货号</th>
						<th width="220">单价</th>
						<th width="82">规格</th>
						<th width="265">订购数量</th>
						<th width="95">小计</th>
						<th width="137">合计数量</th>
						<th width="138">合计金额</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="cartDetail" items="${proDetail.productInventoryList}" varStatus="s">	
						<tr>
							<td style="display: none;">
								<input type="checkbox" value="${cartDetail.cartId}j" name="t${proDetail.productId}j" id="check${cartDetail.cartId}j" checked="checked">
								<input type="hidden" value="${cartDetail.cartId}" id="hidcart${cartDetail.cartId}j" />
							</td>
							<td>${cartDetail.code }</td>
							<td><del>${cartDetail.outstockPrice }</del>&nbsp;&nbsp;<span class="text-red" id="discounts${cartDetail.cartId}j">${cartDetail.discountPrice }</span>元/${proDetail.meterageUnit}</td>
							<td>${cartDetail.spec}</td>
							<td>${cartDetail.buyNum} X <span id="count${cartDetail.cartId}j">${cartDetail.oneboxCount}</span>/${proDetail.meterageUnit}<input type="hidden" value="${cartDetail.buyNum}" id="num${cartDetail.cartId}j" /></td>
							<td><span id="subtotal${cartDetail.cartId}j">${cartDetail.subtotalPrice}</span>元</td>
							<c:if test="${s.index == 0}">
								<td class="num" rowspan="${fn:length(proDetail.productInventoryList)}"><span id="numCountt${proDetail.productId}j">0</span>${proDetail.meterageUnit}</td>
								<td class="sum" rowspan="${fn:length(proDetail.productInventoryList)}"><span id="sumMoneyt${proDetail.productId}j">0.00</span>元</td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:forEach>
</c:forEach>	