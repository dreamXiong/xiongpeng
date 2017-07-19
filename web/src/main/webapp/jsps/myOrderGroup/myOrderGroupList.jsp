<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${dtoList}" varStatus="o">
	<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
	<div class="merchandise">
		<div class="info-title clear">
			<div class="pull-left">
				<i>订单号: </i><a href="javascript:;">${item.orderSerialNo}</a>
			</div>
			<div class="pull-left">
				<i>订单时间: </i><span> <liguo:dateFormatLabel
						value="${item.createDt}" pattern="yyyy-MM-dd  HH:mm:ss" />
				</span>
			</div>  
			<div class="pull-left"><i>品牌 : </i><span> ${item.brandName }</span></div>
		</div>
		<table class="col-10">
			<thead>
				<tr>
					<th width="270">商品信息</th>
					<th width="66">单价</th>
					<th width="46">规格</th>  
					<th width="68">订购数量</th>
					<th width="91">小计</th>
					<th width="100">合计</th>
					<th width="188">总计</th>
					<th width="89">订单状态</th>
					<th width="99">订单操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="pitem" items="${item.productList}" varStatus="p">
				<c:forEach var="orderDetail" items="${pitem.orderDetailList}" varStatus="s">
					<c:if test="${p.index == 0}">
						<c:if test="${s.index == 0}">
							<tr>
								<td rowspan="${fn:length(pitem.orderDetailList)} "><div class="info"><div class="pull-left info-text"><p>${pitem.productName }</p></div></div></td>
								<td>${orderDetail.price }元</td>
								<td>${orderDetail.spec }</td>
								<td>${orderDetail.buyNum } ${orderDetail.meterageUnit}</td>
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}
									<br>${orderDetail.detailPayMoney}元
								</td>  
								<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥${pitem.buyMoneyCount}元</td> 
								<td rowspan="${item.detailCount}">
									${item.buyCount}<!-- 件 -->
									<br> 
									<c:if test='${not empty item.couponMoney }'>
										<del>￥${item.totalMoney}元</del>
										 ${item.totalMoney - item.couponMoney}元<br>(使用优惠券-${item.couponMoney}) 
									</c:if> 
										<c:if test='${empty item.couponMoney }'>
											￥${item.totalMoney}元<br>
										</c:if>  
										<liguo:dictLabel key="${item.orderType}" /> <br> 
										<liguo:dictLabel key="${item.settleType}" />
								</td>
								<td rowspan="${item.detailCount}"><liguo:dictLabel key="${item.orderStatus}" /></td>
								<td rowspan="${item.detailCount}">
								<a href="${ctx}/orderTracking/doSearchOrderTracking?orderGroupId=${item.id}">订单详情</a><br> 
									<!-- 待确认 --> 
									<c:if test="${item.orderStatus == 200}">
										<a onclick="showCancleOrderDivlog('${item.id}');">取消订单</a>
										<br>
									</c:if> 
									<!-- 待付款 --> 
									<c:if test="${item.orderStatus == 202}">
										<!-- 线上支付 -->
										<c:if test="${item.settleType == 240}">
											<a onclick="showCancleOrderDivlog('${item.id}');">取消订单</a>
											<br>
											<a onclick="payOrderGroup('${item.id}');">支付</a>
										</c:if>
	
										<c:if test="${item.settleType == 242}">
											<!-- 线下支付 -->
											<a onclick="showCancleOrderDivlog('${item.id}');">取消订单</a>
											<br>
										</c:if>
									</c:if> <!-- 终止订单 --> <c:if
										test="${item.orderStatus == 208 || item.orderStatus == 210 }">
										<a onclick="showStopOrderDivlog('${item.id}');">终止订单</a>
										<br>
									</c:if> 
									
									
									<!-- 终止订单 --> <c:if test="${item.orderStatus == 214 }">
										<a onclick="">确认收票</a>
										<br>
									</c:if> <!-- 取消终止订单 --> <c:if test="${item.orderStatus == 218 }">
										<a onclick="cancleStopOrderGroup('${item.id}');">取消终止</a>
										<br>
									</c:if>
										<!-- 登记发票--> 
										<c:if test="${item.orderStatus == 212 }">  
											<a onclick="#">确认收票</a>
										</c:if>
										<!-- 登记发票--> 
										<c:if test="${item.orderStatus == 216 }">  
											<a onclick="configStopOrderGroup('${item.id}');">确认终止</a><br>
											<a onclick="cancleStopOrderGroup('${item.id}'); ">取消终止</a>
										</c:if>
										<c:if test="${item.orderStatus == 210 }">  
											<a onclick="configGoodsReceipt('${item.id}'); ">确认收货</a><br>
											<a onclick="showReissueOderGrop('${item.id}'); ">漏发错发</a>
										</c:if>
										<c:if test="${item.orderStatus == 204 }">  
											<a onclick="configGoodsReceipt('${item.id}'); ">确认收货</a><br>
										</c:if>
									</td>
							</tr>
						</c:if>
						
						<c:if test="${s.index != 0}">
							<tr>	
								<td>${orderDetail.price }元</td>
								<td>${orderDetail.spec }</td>  
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元</td>  
							</tr>
						</c:if>
					</c:if>
				 	<c:if test="${p.index != 0}">    
				 		<c:if test="${s.index == 0}">
						<tr>  
							<td rowspan="${fn:length(pitem.orderDetailList)} "><div class="info"><div class="pull-left info-text"><p>${pitem.productName }</p></div></div></td> 
							<td>${orderDetail.price }元</td>
							<td>${orderDetail.spec }</td>  
							<td>${orderDetail.buyNum } ${orderDetail.meterageUnit}</td>
							<td>${orderDetail.buyNum }${orderDetail.meterageUnit}
								<br>${orderDetail.detailPayMoney}元
							</td>
							<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥${pitem.buyMoneyCount}元</td>   
						</tr>
						</c:if>
						
						 <c:if test="${s.index != 0}">
						 	<tr>
							<td>${orderDetail.price }元</td>
								<td>${orderDetail.spec }</td>  
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元
							</td>  
							</tr>
						</c:if> 
					</c:if> 
				</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:forEach>
<liguo:pagination dataDomId="key_myOrderGroup_list"
	pageAction="serachMyOrderGroup" />