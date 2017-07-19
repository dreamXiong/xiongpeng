<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="" />
	<tiles:put name="body" type="String">
	<c:set value="merchantsDetails" var="modalName"></c:set>
<head>
	<meta charset="UTF-8">
	<title>招商详情页</title>
	<link rel="stylesheet" href="${ctx}/css/tender.css">
	<script src="${ctx}/js/hgl/pick.js"></script>
	
	<script>
		
	function nameHide(){
		$('.name-list').stop().slideUp(300);
	}	
</script>
</head>



<!-- 内容板块开始 -->
	<div class="cantainer">
		<div class="area">
			<c:if test="${errcode == 'true'}" var="ishasMerchants">
			<div class="list-banner clear">
				<div class="pull-left banner">
					<img src="../generateImage?id=${param.bid}" alt="">
				</div>		
				<div class="pull-right text">
					${remark}
					<span id="addShopInfo${bid}">
						<c:choose>
							<c:when test="${saveInfo!=null}">
								<span class="iconfont" title="收藏" onclick="addMerchantsShopInfoSave(${bid},0)">&#xe609;</span>
							</c:when>
							<c:otherwise>
								<span class="iconfont" title="收藏" onclick="addMerchantsShopInfoSave(${bid},1)">&#xe608;</span>
							</c:otherwise>
						</c:choose>	
					</span>				
				</div>		
			</div>	
			
				
			
			<div class="clear">
				<div class="pull-left">
					<div class="clear margin-bottom-10 choose-left ">
					<div class="text-seek bg-gray clear line-height border-bottom-dashed">
						<div class="pull-left seek-title">热门产品  </div>
						<div class="pull-left seek-class">
							<a href="javascript:">150*50 扳手</a>
							<a href="javascript:">18 钳子</a>
							<a href="javascript:">150*50 扳手</a>
							<a href="javascript:">18 钳子</a>
							<a href="javascript:">150*50 扳手</a>
							<a href="javascript:">18 钳子</a>
						</div>
					</div>
					
			
					<div class="text-seek border-bottom-dashed clear">
						<div class="pull-left seek-title">品&nbsp;&nbsp; 名  </div>
						<div class="pull-left seek-class" id="secondForAjax">
							 <c:forEach var="sItem" items="${productTypes}" varStatus="s">
								<a href="javascript:" onclick="secondProductType(${sItem.id});">${sItem.name}</a>
							</c:forEach>
						</div>
					</div>
					<div class="text-seek border-bottom-dashed clear" id="thirdForAjaxDiv" style="display: none">
						<div class="pull-left seek-title">产&nbsp;&nbsp; 品  </div>
						<div class="pull-left seek-class" id="thirdForAjax">
								<!-- <a href="javascript:">xx</a> -->
						</div>
					</div>
				</div>

				<!-- 表单提交 -->
				<form id="key_${modalName}_qryFrm">
					<input type="hidden" name="cursor" id="cursor" value="0">
					<input type="hidden" name="secondTypeId" id="secondTypeId" value="">
					<input type="hidden" name="thirdTypeId" id="thirdTypeId" value="">
					<input type="hidden" name="brandIds" id="brandIds" value="${bid}">
					<input type="hidden" name="orderby" id="orderby" value="">
					<input type="hidden" name="ordersort" id="ordersort" value="">
					<input type="hidden" name="minprice" id="minprice" value="">
					<input type="hidden" name="maxprice" id="maxprice" value="">
					<input type="hidden" name="shoppingType" id="shoppingType" value="${st}">
				</form>
				<div id="key_${modalName}_list">
						<%@include file="../pick/productList.jsp"  %>
				</div>	

			</div>
			<div class="choose-right pull-right clear margin-bottom-10">
				<div class="line-height bg-gray text-center font-bold">
					推广产品
				</div>
				<ul>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
				</ul>
			</div>
			</div>
			</c:if>
			<c:if test="${!ishasMerchants}">
				<div style="margin-top: 50px;font-size: 26px;">${msg }</div>
				<div style="margin-top: 20px;font-size: 16px;"><a href="${ctx}/merchants/merchants">查看我能购买的招商</a></div>
			</c:if>
		</div>
	</div>
<!-- 内容板块结束 -->

<!-- 弹出层:收藏提示 -->	
	<div class="myModal">
		<div class="myModal-bg"></div>
		<div class="succeed" style="border-radius:2px;">
			<h3>提示</h3>
			<p><i class="iconfont">&#xe620;</i><span id="modalSpan"></span></p>
		</div>		
	</div>	
	
<!-- 内容板块结束 -->
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
	</script>
<!-- fiexd开始 -->  

</tiles:put>
<tiles:put name="footer" type="String">
<c:if test="${errcode == 'true'}" var="ishasMerchants"> 
<div class="fd">
	<div class="area fd-in">     
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
	<img src="${ctx}/images/zhaoshang-banna.jpg" alt="">
</div>
</c:if>
<!-- fiexd结束 -->
	</tiles:put>
</tiles:insert>