<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="现货选购详情页" />
	<tiles:put name="body" type="String">

<head>
	<meta charset="UTF-8">
	<script src="${ctx}/js/hgl/pickDetail.js"></script>
	<script src="${ctx}/js/hgl/publicDecimal.js"></script>
	<link rel="stylesheet" href="${ctx}/css/tender.css">
</head>


<!-- 内容板块开始 -->
	<div class="pd-list cantainer">
		<div class="area">
			<!-- 标题部分开始 -->
			<div class="pd-top clear margin-bottom-10">
				<div class="pull-left ">
					<div class="pd-top-left product-left jqzoom">
						<img    id="bigImg" src="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.pimgOne}" alt="" jqimg="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.pimgOne}" >
					</div>
					<div class="pd-botton-left">
						<ul>
							<li class="active">
								<img src="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.pimgOne}" alt="">
							</li>
							<c:if test="${dto.pimgtwo!=null && dto.pimgtwo!=''}">
								<li>
									<img src="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.pimgtwo}" alt="">
								</li>
							</c:if>
							<c:if test="${dto.pimgthree!=null && dto.pimgthree!=''}">
								<li>
									<img src="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.pimgthree}" alt="">
								</li>
							</c:if>	
							<%-- <li>
								<img src="${ctx}/generateProductImage?id=${tbProduct.id}&imgName=${tbProduct.pimgOne}" alt="">
							</li>
							<li>
								<img src="${ctx}/generateProductImage?id=${tbProduct.id}&imgName=${tbProduct.pimgOne}" alt="">
							</li>--%>
						</ul>
					</div>
				</div>
				<div class="product-right pull-right">
					<div class="product-right-content">
						<span id="hiddenMerchantsId"  style="display: none;">${param.mid == '' ? 0 : param.mid}</span>
						<span id="hiddenBrandId"  style="display: none;">${dto.brandId}</span>
						<h3 class="line-height-50 padding-left">${dto.name}</h3>
						<p class="line-height-50 padding-left">价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：<span class="text-red">￥${dto.price}～${dto.maxPrice}元</span></p>
						<p class="line-height-50 padding-left">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：${dto.brandName}</p>
						<p class="line-height-50 padding-left">大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：${dto.mainTypeName}</p>
						<p class="line-height-50 padding-left">销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：${dto.saleNum}</p>
						<p class="line-height-50 padding-left">支付方式：<i class="iconfont">&#xe606;</i>在线支付　   　　　<i class="iconfont">&#xe604;</i>银行转账</p>
						<p class="line-height-50 padding-left">联系方式：<i class="iconfont">&#xe602;</i>400-680-9696　   　 分销合作热线：<i class="iconfont">&#xe60b;</i>0755-23772661     　 <i class="iconfont">&#xe603;</i>在线客服</p>
					</div>
				</div>
			</div>
			<!-- 标题部分结束 -->

			<!-- 表格部分开始 -->
			<div class="choose margin-top margin-bottom-10">
				<input type="hidden" id="merchantId" name="merchantId" value="${param.mid}" />
				<table id="pickTable">
					<tr>
						<th>
							<input type="checkbox" name="rootCheckbox" value="${st}"/>
						</th>
						<th>单价</th>
						<th>折后价</th>
						<th>规格</th>
						<th>订购数量</th>
						<th>订货</th>
						<th>材质</th>
						<c:forEach items="${attributeList}" var="attribute" varStatus="s">
							<th>${attribute}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${inventoryDtoList}" var="inventoryDto" varStatus="s">
						
						<tr>
							<td><input type="checkbox" value="${inventoryDto.id}" name="checkbox_pick" id="check${inventoryDto.id}"></td>
							<td><del style="text-decoration:line-through;">${inventoryDto.outstockPrice}元</del></td>
							<td><span id="discount${inventoryDto.id}">${inventoryDto.discountPrice}</span>元</td>
							<td>${inventoryDto.spec}</td>
							<td>
							 	<input type="hidden" value="${inventoryDto.cartId}" id="hidCart${inventoryDto.id}" />
							    <input type="hidden" value="${inventoryDto.buyNum}" id="hid${inventoryDto.id}" />
								<a href="javascript:" class="addon" onclick="addOrSubtract(${inventoryDto.id},'subtract',${inventoryDto.oneboxCount})">-</a>
								<input type="text" value="${inventoryDto.buyNum != null ? inventoryDto.buyNum : 1}" id="num${inventoryDto.id}" onkeyup="validateOrderNum(${inventoryDto.id},${inventoryDto.oneboxCount})" name="text_pick" oneboxCount="${inventoryDto.oneboxCount}">
								<a href="javascript:" class="addon" onclick="addOrSubtract(${inventoryDto.id},'add',${inventoryDto.oneboxCount})">+</a> X <span id="count${inventoryDto.id}">${inventoryDto.oneboxCount}</span>
							</td>
							<td><button type="button" onclick="addCart(${inventoryDto.id},${st},'${param.mid}')">加入进货单</button></td>
							<td>${inventoryDto.material}</td>
							<c:forEach var="attributeValue" items="${inventoryDto.attributeValueList}" varStatus="s">
								<td>${attributeValue.value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 表格部分结束 -->
			<p class="text-right padding-bottom border-bottom-dashed margin-bottom">
				<b>共选择了　<span style="display: none;" id="hidGoodsCount">0</span><span class="text-red" id="goodsCount">0</span>　件货物，合计　<span class="text-red">￥</span><span class="text-red" id="goodsMoney">0</span>　元
				　<button type="button" class="btn" id="addBetchCart">加入进货单</button></b>
			</p>
			<!-- 产品介绍开始 -->
			<div class="referral">
				<div class="pull-left">
					<div class="clear margin-bottom-10 choose-left">
						<div class="referral-title">
							<ul class="clear">
								<li class="active"><b>产品介绍</b></li>
								<li><b>咨询评论</b></li>
								<li><b>联系我们</b></li>
							</ul>
							<div class="referral-body">
								<div class="blcok line-height-30 padding text-gray">
									<%--<p class="text-red"><b>产品特点:</b></p>
									<p>· 钳体经锻造热处理，坚固耐用</p>
									<p>· 刃口经特殊处理，剪切能力强。</p>
									<p>· 双沾塑手柄，握持舒适。</p>
									<p>· 符合美国ANSI标准。</p> --%>
									<p>${dto.describes }</p>
									<img class="lazy" data-original="${ctx}/images/advertising-1.jpg" src="${ctx}/images/blank.gif" alt="" >
									<img class="lazy" data-original="${ctx}/images/advertising-2.jpg" src="${ctx}/images/blank.gif"  alt="">
									<img class="lazy" data-original="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.dimgone}" src="${ctx}/images/blank.gif" alt="">
									<c:if test="${dto.dimgtwo!=null && dto.dimgtwo!=''}">
										<img class="lazy" data-original="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.dimgtwo}" src="${ctx}/images/blank.gif"  alt="">
									</c:if>
									<c:if test="${dto.dimgthree!=null && dto.dimgthree!=''}">
										<img class="lazy" data-original="${ctx}/generateProductImage?id=${dto.id}&imgName=${dto.dimgthree}" src="${ctx}/images/blank.gif"  alt="">
									</c:if>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- 推广产品开始 -->
				<div class="choose-right pull-right clear margin-bottom-10">
					<div class="line-height bg-gray text-center font-bold">
						推广产品
					</div>
					<ul>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 
								<span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 
								<span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 
								<span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 
							<span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
					</ul>
				</div>
				<!-- 推广产品结束 -->
			</div>
			<!-- 产品介绍结束 -->
		</div>
	</div>		 
<!-- 内容板块结束 -->

<!-- 确定进货单开始 -->
<div class="myModal">
	<div class="myModal-bg"></div>
	<div class="succeed">
		<h3>提示</h3>
		<p><i class="iconfont">&#xe620;</i><span id="modalSpan"></span></p>
	</div>		
</div>
<!-- 确定进货单结束 -->

	</tiles:put>
<c:if test="${st==262}">
<tiles:put name="footer" type="String">
<div class="fd">
 <%@include file="pickMerchants.jsp"  %>
 <img src="${ctx}/images/zhaoshang-banna.jpg" alt="">
</div>
<!-- fiexd结束 -->
	</tiles:put>
</c:if>
</tiles:insert>