<%@page pageEncoding="UTF-8"%>

	<div id="merchants" class="area fd-in">     
		<div class="fd-title">            
			<div>
				<div>
					您正在参与 <span class="text-red">${merchant.brandName}&nbsp;${merchant.addressName}</span> 的招商 
				</div>
				<div>
					该招商需要购买 <span class="text-red">${merchant.coupons}元</span> 招商服务费(全额返优惠券)
				</div> 
				<div>
					同时需要一次性进货 <span class="text-red">${merchant.number}元</span> ,保障产品的铺货
				</div>    
			</div>
		</div>
		     
		
		
		
			<div class="fd-text text-right">
				<div>
					您已经选购了 <span class="text-red"><fmt:formatNumber  value="${shopCartMoney}" pattern="##.##"   minFractionDigits="2"/>元</span> 商品,还需选购 
					<span class="text-red">
					<c:if test="${shopCartMoney < merchant.number}" var="isneedBuy">
						
						<fmt:formatNumber  value="${merchant.number-shopCartMoney}" pattern="##.##"   minFractionDigits="2"/>
						</c:if>	
						<c:if test="${!isneedBuy}">0</c:if>元</span>
				</div>
				<div>
				平台服务费(我们将全额返优惠券) <span class="text-red">${merchant.coupons}元</span>
				</div>
				<div>
					<span class="text-red"></span>
				</div>
				<span class="text-add"></span>
				<span></span>
				
				<c:if test="${isBuyService}">
				<a  class="serve" href="merchantService?id=${merchant.id}">服务购买</a>
				</c:if>
				<c:if test="${!isneedBuy and !isBuyService}">
				<a  href="${ctx}/shoppingCar/doSettlementMerchant?bid=${merchant.brandid}">确定结算</a>
				</c:if>
			</div>
	</div>
