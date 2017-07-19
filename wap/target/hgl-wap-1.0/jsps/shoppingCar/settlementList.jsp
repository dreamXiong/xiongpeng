<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${goodsOrderList}" varStatus="i">
	<div class="varify-details box-shadow">
		<div>
			<div class="clearfix details-head">
				<input type="hidden" id="s${item.shopId }" value="${item.shopId }" name="shopCheck">
				<div class="pull-left title" onclick="location.href='${ctx}/shop/index?id=${item.shopId }&distance=${item.shopDistance }'"><span class="icon-home" style="color:#f2000e;"></span> ${item.shopName }</div>
				<div style="text-align: right;">
					<span class="icon-phone-sign"></span><span>${item.shopPhone }</span>
				</div>
			</div>
			<div>
			<c:forEach var="proDetail" items="${item.productInventoryList}" varStatus="j">
				<div class="details-lists" onclick="openProductDetail('${proDetail.productId}')">
					<div class="details-lists-img">
						<input type="hidden" value="${proDetail.cartId }" name="shop${item.shopId }" id="check${proDetail.cartId}">
						<input type="hidden" value="${proDetail.buyNum }" id="num${proDetail.cartId  }">
						<input type="hidden" value="${proDetail.outstockPrice }" id="discounts${proDetail.cartId  }">
						<input type="hidden" value="${proDetail.buyNum }" id="hid${proDetail.cartId  }">
						<img src='${ctx}/shoppingCar/generateImage?id=${proDetail.productId}&imgName=${proDetail.logoName}' alt="">
					</div>
					<div class="details-lists-text">
						<p>
						<c:if test="${proDetail.pushFlag == 1}">
							<span style="color:red;">推</span>
						</c:if>
						${proDetail.productName }@${proDetail.productTypeName }@${proDetail.brandName }</p>
						<div class="clearfix">
							<div class="pull-left text-red font-bold">
								<span class="dollar">¥</span>
								<span class="unit" id="inventoryMax${proDetail.cartId }">00.</span>
								<span class="mark" id="inventoryMin${proDetail.cartId }">00</span>
							</div>
							<div class="pull-right">&times;<span>${proDetail.buyNum}</span>	</div>
						</div> 
					</div>
				</div>
			</c:forEach>	
			</div>
			<div class="details-foot" style="padding-top:5px;">
				<div class="varify-choose" id="payTypeDiv" style="padding:0;">
					<dl>             
						<dt class="clearfix">
							<div class="pull-left title">收货方式：</div>
							<div class="pull-right">
								(<span>${item.description }</span>)<span class="reap">${item.deliveryFlag != '1' ? '送货' : '自提'}</span>		
								<span class="icon-angle-right"></span>
							</div>
						</dt>
						<dd>
							<c:if test="${item.deliveryFlag == '1' }">
								<label for="ziti">
									<input type="radio" id="ziti${item.shopId }" name="goodsType${item.shopId }" value="414" class="on" checked="checked" onclick="receivingWay('0')"><span>自提</span>
								</label>
								<label for="songhuo">
									<input type="radio" id="songhuo${item.shopId }" name="goodsType${item.shopId }" disabled="disabled" value="416" onclick="receivingWay('1')"><span id="text">送货</span>
								</label>
							</c:if>
							<c:if test="${item.deliveryFlag != '1' }">
								<label for="ziti">
									<input type="radio" id="ziti${item.shopId }" name="goodsType${item.shopId }" value="414" onclick="receivingWay('0')"><span>自提</span>
								</label>
								<label for="songhuo">
									<input type="radio" id="songhuo${item.shopId }" name="goodsType${item.shopId }" class="on" value="416" checked="checked" onclick="receivingWay('1')"><span id="text">送货</span>
								</label>
							</c:if>
						</dd>
					</dl>
					<dl>
						<dt class="clearfix">
							<div class="pull-left title">支付方式：</div>
							<div class="pull-right">
								<span class="reap">线上支付</span>
								<span class="icon-angle-right"></span>
							</div>
						</dt>
						<dd>
							<label for="lineDown">
								<input type="radio" id="lineDown${item.shopId }" name="pay${item.shopId }" value="242"><span>线下支付</span>
							</label>
							<label for="lineUp">
								<input type="radio" id="lineUp${item.shopId }" name="pay${item.shopId }" class="on" value="240" checked="checked"><span>线上支付</span>
							</label>
						</dd>  
					</dl>
					<dl>
						<dt class="clearfix">
							<div class="pull-left title">优&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;惠：</div>    
							<div class="pull-right">
								<span class="reap">${fn:length(groupList) > 0 ? '不使用' : '无优惠'}</span>
								<span class="icon-angle-right"></span>
							</div>
						</dt>
						<dd class="list">
							<label for="no">
								<input type="radio" value="0" checked="checked" name="discount${item.shopId }"><span>${fn:length(groupList) > 0 ? '不使用' : '无优惠'}</span>
							</label>
							<c:forEach var="group" items="${groupList}" varStatus="j">
								<c:if test="${group.shopId == item.shopId}">
									<label for="one">
										<input type="radio" id="discount${group.id }" value="${group.id }" name="discount${item.shopId }"><span>${group.discount }折</span>
									</label>
								</c:if>
							</c:forEach>
						</dd> 
					</dl>	
				</div>
				<div class="leave clearfix" style="padding-left:0;line-height:30px;">
					<div class="pull-left">
					配送方式：(<span id="hidDescrption${item.shopId }">${item.description }</span>)
					</div>
					<div class="pull-right">
						<span class="text-red" id="hidFreight${item.shopId }" style="display: none;">${item.freight }</span>
						邮费：<span class="text-red" id="freight${item.shopId }">${item.freight }</span>
					</div>
				</div>
				<div class="leave">
					<label for="">买家留言：</label>
					<input type="text" placeholder="选填，可填写您和卖家达成一致的要求" id="buyMessage${item.shopId }" maxlength="60">
				</div>
				<div class="text-right clearfix">
					<div class="pull-right font-bold">
						共<span id="goodsCount${item.shopId }"> 0 </span>件,
						<span>合计:
							<span class="text-red font-bold">
								<span class="dollar">¥</span>  
								<span class="unit" id="shopMax${item.shopId }">00.</span>
								<span class="mark" id="shopMin${item.shopId }">00</span>
							</span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>	