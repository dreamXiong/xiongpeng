<%@page pageEncoding="UTF-8"%>
<form id="settlementForm" method="post" action="doSettlement">
	<input type="hidden" value="" id="orderListNum" name="orderListNum"/>
	<input type="hidden" value="" id="cartIdsList" name="cartIdsList"/>
</form>
<form id="productDetailForm" method="post" action="${ctx}/pick/pickDetail">
	<input type="hidden" value="" id="id" name="id"/>
	<input type="hidden" value="" id="distance" name="distance"/>
</form>
<div class="cart-lists" id="cartListsDiv">
<div id="cartListCountDiv">
	<c:forEach var="item" items="${goodsOrderList}" varStatus="i">
		<div class="cart-shopping box-shadow">
			<div class="shopping-head">
				<div class="clearfix box">
					<div class="box2">
						<input type="checkbox" id="s${item.shopId }" value="${item.shopId }" onclick="singleAllChecked(${item.shopId })" name="shopCheck">
						<span class="icon-home" style="color:#f2000e;"></span>                  
						<font onclick="location.href='${ctx}/shop/index?id=${item.shopId }&distance=${item.shopDistance }'">${item.shopName }</font>
					</div>
					<div class="box1">
						<span class="icon-phone-sign"></span><span>${item.shopPhone }</span>
					</div>
				</div>
				<div class="clearfix shopping-head-bottom box">
					<div class="box2">
						&nbsp;<span class="icon-map-marker"></span>&nbsp;
						<span>${item.province }${item.city }${item.country }${item.street }</span>
					</div>
					<div class="box1">
						<span class="icon-map-marker"></span>${item.shopDistance }km
					</div>
				</div>
			</div>
			<c:forEach var="proDetail" items="${item.productInventoryList}" varStatus="j">
				<ul class="shopping-lists item${item.shopId }" id="lists${proDetail.cartId }" name="lists${item.shopId }">
					<li class="clearfix">
						<c:choose>
							<c:when test="${proDetail.status == 0}">
								<span class="lose" id="hidFailure${proDetail.cartId }">失效</span>
								<input type="hidden" value="${proDetail.cartId }" name="failureCart">
							</c:when>
							<c:otherwise>
								<input type="checkbox" value="${proDetail.cartId }" name="shop${item.shopId  }" onclick="singleChecked(${item.shopId })" id="check${proDetail.cartId}">
							</c:otherwise>
						</c:choose>
						<input type="hidden" value="${proDetail.buyNum }" id="num${proDetail.cartId  }">
						<input type="hidden" value="${proDetail.outstockPrice }" id="discounts${proDetail.cartId  }">
						<input type="hidden" value="${proDetail.buyNum }" id="hid${proDetail.cartId  }">
						<input type="hidden" value="${proDetail.pushFlag }" id="pushFlag${proDetail.cartId  }">
						<div class="item1">
							<a href="#" onclick="openProductDetail(${proDetail.productId},'${item.shopDistance }')">
								<img src='${ctx}/shoppingCar/generateImage?id=${proDetail.productId}&imgName=${proDetail.logoName}' alt="">
							</a>
						</div>
						<div class="item2">
							<c:choose>                         
								<c:when test="${proDetail.status == 0}">
									<h3 style="color: #CCC;">${proDetail.productName }@${proDetail.productTypeName }@${proDetail.brandName }</h3>
									<span style="font-size: 12px;margin-top: 15px;">产品已不能购买， 请联系卖家</span>     
								</c:when>                              
								<c:otherwise>                            
									<h3>
									<c:if test="${proDetail.pushFlag == 1}">
										<span style="color:red;">推</span>
									</c:if>
									${proDetail.productName }@${proDetail.productTypeName }@${proDetail.brandName }</h3>
									<dl>
										<dt><span class=" icon-angle-right"></span>规格：${proDetail.spec}</dt>
										<c:forEach var="attributeValue" items="${proDetail.attributeValueList}" varStatus="s">
											<dd>${attributeValue.key}：${attributeValue.value}</dd>
										</c:forEach>
									</dl>
									<div class="clearfix operate">
										<div class="pull-left text-red font-bold">
											<span class="dollar">¥</span>
											<span class="unit" id="cartMax${proDetail.cartId }">00.</span>
											<span class="mark" id="cartMin${proDetail.cartId }">00</span>
										</div>
										
										<div class="pull-right icon">
											<a href="javascript:;">
												<span class="icon-trash" id="delete${proDetail.cartId }" onclick="removeCart(${proDetail.cartId },${item.shopId })"></span>
											</a>
										</div>
										<div class="number pull-right">
											<a href="javascript:;" class="min" onclick="addOrSubtract(${proDetail.cartId},'subtract')"></a>
											<input type="number" value="${proDetail.buyNum }" onblur="validateOrderNum(${proDetail.cartId})" id="numText${proDetail.cartId}" maxlength="3">
											<a href="javascript:;" class="add" onclick="addOrSubtract(${proDetail.cartId},'add')"></a>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
	</c:forEach>
</div>
	<div class="shopping-foot text-right" style="margin-top:-1px;padding-right:12px;display: none;" id="failureCartDiv">
		<button type="button" onclick="removeFailureCart()">&nbsp;清空失效产品&nbsp;</button>
	</div>
</div>
