<%@page pageEncoding="UTF-8"%>
<div class="chart-nav">
	<c:forEach var="item" items="${merchantOrderList}" varStatus="s">
		<c:choose>
			<c:when test="${s.index == 0}">
				<span style="display: none;" id="tabId">d${item.brandId }</span>
				<div class="active" id="d${item.brandId }">${item.brandName}</div>
			</c:when>
			<c:otherwise>
				<div id="d${item.brandId }">${item.brandName}</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${fn:length(goodsOrderList) > 0 && fn:length(merchantOrderList) > 0}">
			 <div id="receipt">进货单</div>
		</c:when>
		<c:when test="${fn:length(goodsOrderList) > 0 && fn:length(merchantOrderList) <= 0}">
			 <span style="display: none;" id="tabId">receipt</span>
			 <div class="active" id="receipt">进货单</div>
		</c:when>
	</c:choose>
</div>
<form id="settlementForm" method="post" action="doSettlement">
	<input type="hidden" value="" id="orderListNum" name="orderListNum"/>
	<input type="hidden" value="" id="cartIdsList" name="cartIdsList"/>
	<input type="hidden" value="" id="cartNumber" name="cartNumber"/>
	<input type="hidden" value="" id="cartIds" name="cartIds"/>
</form>
<form id="submitProductDetail" method="post" action="${ctx}/pick/pickDetail">
	<input type="hidden" value="" id="id" name="id"/>
	<input type="hidden" value="" id="st" name="st"/>
	<input type="hidden" value="" id="mid" name="mid"/>
</form>
<div class="chart-main">
	<c:forEach var="item" items="${merchantOrderList}" varStatus="i">
	<div class="chart-table" id="brand${item.brandId }" style="display:${i.index == 0 ? 'block':'none'};">
		<c:forEach var="proDetail" items="${item.productInfoList}" varStatus="j">
		<div class="chart-list">
			<div class="chat-title clear">
				<div class="pull-left pull-icon">
					<span class="iconfont shrink">&#xe61a;</span>
				</div>
				<div class="pull-left pull-img"><img src='${ctx}/shoppingCar/generateImage?id=${proDetail.productId}&imgName=${proDetail.logoName}'></div>
				<div class="pull-left pull-text">
					<p>${item.brandName }</p>
					<p>${proDetail.productName }</p>
					<small>${proDetail.productTypeName }</small>
				</div>

				<div class="pull-right pull-btn">
					<a href="#" onclick="productDetail('${proDetail.productId}','${item.shoppingType==true?262:264}','${item.merchantsId}')">添加规格</a>
				</div>
				<div class="pull-right pull-sum">已选商品 <span class="text-red">￥</span><span class="text-red" id="singt${proDetail.productId}z">0.00</span>元</div>
			</div>
			<div class="table">
				<table class="col-10" id="t${proDetail.productId}z">
					<thead>
					<tr>
						<th width="89"><input type="checkbox" class="check" name="rootCheckboxt${proDetail.productId}z" onclick="singleAllChecked('t${proDetail.productId}z')"></th>
						<th width="200">货号</th>
						<th width="200">单价</th>
						<th width="80">规格</th>
						<th width="265">订购数量</th>
						<th width="95">小计</th>
						<th width="137">合计数量</th>
						<th width="138">合计金额</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="cartDetail" items="${proDetail.productInventoryList}" varStatus="s">	
						<tr>
							<td>
								<c:choose>
									<c:when test="${cartDetail.status == 0}">
										<span class="text-red" style="padding:0 3px;">已下架<i onclick="deleteDownGoods('${cartDetail.cartId}','${cartDetail.oneboxCount}','${cartDetail.buyNum}')">删除</i></span>
									</c:when>
									<c:otherwise>
										<input type="checkbox" value="${cartDetail.id}z" name="t${proDetail.productId}z" id="check${cartDetail.id}z" onclick="singleChecked('t${proDetail.productId}z')">
									</c:otherwise>
								</c:choose>
							</td>
							<td>${cartDetail.code }</td>
							<td><del>${cartDetail.outstockPrice }</del>&nbsp;&nbsp;<span class="text-red" id="discounts${cartDetail.id}z">${cartDetail.discountPrice }</span>元/${proDetail.meterageUnit}</td>
							<td>${cartDetail.spec}</td>
							<td>
								<input type="hidden" value="${cartDetail.cartId}" id="hidcart${cartDetail.id}z" />
							 	<input type="hidden" value="${cartDetail.buyNum}" id="hid${cartDetail.id}z" />
								<a href="javascript:" class="addon" onclick="addOrSubtract('${cartDetail.id}z','subtract','${proDetail.productId}z')">-</a>
								<input type="text" value="${cartDetail.buyNum != null ? cartDetail.buyNum : 1}" id="num${cartDetail.id}z" onkeyup="validateOrderNum('${cartDetail.id}z','${proDetail.productId}z')" name="text_pick" oneboxCount="${cartDetail.oneboxCount}">
								<a href="javascript:" class="addon addon-right" onclick="addOrSubtract('${cartDetail.id}z','add','${proDetail.productId}z')">+</a> X <span id="count${cartDetail.id}z">${cartDetail.oneboxCount}</span>/${proDetail.meterageUnit}
							</td>
							<td><span id="subtotal${cartDetail.id}z">${cartDetail.subtotalPrice}</span>元</td>
							<c:if test="${s.index == 0}">
								<td class="num" rowspan="${fn:length(proDetail.productInventoryList)}"><span id="numCountt${proDetail.productId}z">0</span>${proDetail.meterageUnit}</td>
								<td class="sum" rowspan="${fn:length(proDetail.productInventoryList)}"><span id="sumMoneyt${proDetail.productId}z">0.00</span>元</td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:forEach>
</div>
</c:forEach>	
<c:if test="${fn:length(goodsOrderList) > 0 }">
<div class="chart-table" id="rt" style="display:${fn:length(merchantOrderList) > 0 ? 'none':'block'};">	
<c:forEach var="item" items="${goodsOrderList}" varStatus="i">
	<c:forEach var="proDetail" items="${item.productInfoList}" varStatus="j">
		<div class="chart-list">
			<div class="chat-title clear">
				<div class="pull-left pull-icon">
					<span class="iconfont shrink">&#xe61a;</span>
				</div>
				<div class="pull-left pull-img"><img src='${ctx}/shoppingCar/generateImage?id=${proDetail.productId}&imgName=${proDetail.logoName}'></div>
				<div class="pull-left pull-text">
					<p>${item.brandName }</p>
					<p>${proDetail.productName }</p>
					<small>${proDetail.productTypeName }</small>
				</div>
				<div class="pull-right pull-btn">
					<a href="#" onclick="productDetail('${proDetail.productId}','${item.shoppingType==true?262:264}','')">添加规格</a>
				</div>
				<div class="pull-right pull-sum">已选商品 <span class="text-red">￥</span><span class="text-red" id="singt${proDetail.productId}j">0.00</span>元</div>
			</div>
			<div class="table">
				<table class="col-10" id="t${proDetail.productId}j">
					<thead>
					<tr>
						<th width="89"><input type="checkbox" class="check" name="rootCheckboxt${proDetail.productId}j" onclick="singleAllChecked('t${proDetail.productId}j')"></th>
						<th width="200">货号</th>
						<th width="200">单价</th>
						<th width="80">规格</th>
						<th width="265">订购数量</th>
						<th width="95">小计</th>
						<th width="137">合计数量</th>
						<th width="138">合计金额</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="cartDetail" items="${proDetail.productInventoryList}" varStatus="s">	
						<tr>
							<td>
								<c:choose>
									<c:when test="${cartDetail.status == 0}">
										<span class="text-red" style="padding:0 3px;">已下架<i onclick="deleteDownGoods('${cartDetail.cartId}','${cartDetail.oneboxCount}','${cartDetail.buyNum}')">删除</i></span>
									</c:when>
									<c:otherwise>
										<input type="checkbox" value="${cartDetail.id}j" name="t${proDetail.productId}j" id="check${cartDetail.id}j" onclick="singleChecked('t${proDetail.productId}j')">
									</c:otherwise>
								</c:choose>
							</td>
							<td>${cartDetail.code }</td>
							<td><del>${cartDetail.outstockPrice }</del>&nbsp;&nbsp;<span class="text-red" id="discounts${cartDetail.id}j">${cartDetail.discountPrice }</span>元/${proDetail.meterageUnit}</td>
							<td>${cartDetail.spec}</td>
							<td>
								<input type="hidden" value="${cartDetail.cartId}" id="hidcart${cartDetail.id}j" />
							 	<input type="hidden" value="${cartDetail.buyNum}" id="hid${cartDetail.id}j" />
								<a href="javascript:" class="addon" onclick="addOrSubtract('${cartDetail.id}j','subtract','${proDetail.productId}j')">-</a>
								<input type="text" value="${cartDetail.buyNum != null ? cartDetail.buyNum : 1}" id="num${cartDetail.id}j" onkeyup="validateOrderNum('${cartDetail.id}j','${proDetail.productId}j')" name="text_pick" oneboxCount="${cartDetail.oneboxCount}">
								<a href="javascript:" class="addon addon-right" onclick="addOrSubtract('${cartDetail.id}j','add','${proDetail.productId}j')">+</a> X <span id="count${cartDetail.id}j">${cartDetail.oneboxCount}</span>/${proDetail.meterageUnit}
							</td>
							<td><span id="subtotal${cartDetail.id}j">${cartDetail.subtotalPrice}</span>${cartDetail.status == 0 ? 0.00 : ''}元</td>
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
</div>
</c:if>
</div>