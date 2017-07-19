<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="订单详情"/>
<tiles:put name="body" type="String">    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script>
		$(function(){
			var Time = $('#orderTrackingTime').val();
			if(Time==1){
				$('.details-bg').css({
					backgroundPosition:'center 0px',
				});
			}else if(Time==2){
				$('.details-bg').css({
				backgroundPosition:'center -36px',});
			}else if(Time==3){
				$('.details-bg').css({
					backgroundPosition:'center -72px',});
			}else if(Time==4){
				$('.details-bg').css({
				backgroundPosition:'center -108px',});
			}else{
				$('.details-bg').css({
					backgroundPosition:'center 0px',
				});
			};
		});
	</script>
</head>
<body>
<div class="cantainer">
			<h2>我的惠给力</h2>
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list">
						<h3 class="bg-gray"><a href="${ctx}/myOrderGroup/index">我的订单 </a>|<a href="">订单详情</a></h3>
						<c:forEach var="item" items="${dtoList}" varStatus="o">
							<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
							<div class="clear">
							<p class="pull-left">订单号 : <em>${item.orderSerialNo}</em></p>
							<p class="pull-right">订单状态 :
								<c:if test="${item.orderStatus==200}">待确认</c:if>
						   		<c:if test="${item.orderStatus==202}">待付款</c:if>
		                        <c:if test="${item.orderStatus==204}">待补发货</c:if>
		                        <c:if test="${item.orderStatus==206}">已终止</c:if>
		                        <c:if test="${item.orderStatus==208}">待发货</c:if>
		                        <c:if test="${item.orderStatus==210}">待确认收货</c:if>
		                        <c:if test="${item.orderStatus==212}">待登记发票</c:if>
		                        <c:if test="${item.orderStatus==214}">待确认收票</c:if>
		                        <c:if test="${item.orderStatus==216}">待买确认终止</c:if>
		                        <c:if test="${item.orderStatus==218}">待卖确认终止</c:if>
		                        <c:if test="${item.orderStatus==220}">交易完成</c:if>
		                        <c:if test="${item.orderStatus==222}">交易关闭</c:if>
                           </p>
						</div>
						
						<div class="details-bg ">
							<div class="po">
								<input type="hidden" value="${orderTrackings.size()}" id="orderTrackingTime">							
								<span>提交订单</span>
								<span>订单支付</span>
								<span>商品发货</span>
								<span>收货完成</span>								
							</div>	
							<div class="bo">
								<c:forEach items="${orderTrackings}" var="orderTracking">
									<span>
										<jsp:useBean id="operateDtTracking" class="java.util.Date" />  
										<jsp:setProperty name="operateDtTracking" property="time" value="${orderTracking.operateDt}" />  
										<fmt:formatDate value="${operateDtTracking}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="details-list">
						<h3 class="bg-gray">订单跟踪</h3>     
						<div style="margin-left:80px;">							
							<c:forEach items="${orderTrackingDtos}" var="orderTrackingDto">
								<ul class="clear line-height">    
									<li class="pull-left margin-right">						
									   <jsp:useBean id="operateDt" class="java.util.Date"/>
									   <jsp:setProperty property="time" name="operateDt" value="${orderTrackingDto.operateDt}"/>
									   <fmt:formatDate value="${operateDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
									   
								   	</li>
								   	<li class="pull-left margin-right" style="width:90px;">
									   	<c:if test="${orderTrackingDto.orderState==200}">待确认</c:if>
								   		<c:if test="${orderTrackingDto.orderState==202}">待付款</c:if>
				                        <c:if test="${orderTrackingDto.orderState==204}">待补发货</c:if>
				                        <c:if test="${orderTrackingDto.orderState==206}">已终止</c:if>
				                        <c:if test="${orderTrackingDto.orderState==208}">待发货</c:if>
				                        <c:if test="${orderTrackingDto.orderState==210}">待确认收货</c:if>
				                        <c:if test="${orderTrackingDto.orderState==212}">待登记发票</c:if>
				                        <c:if test="${orderTrackingDto.orderState==214}">待确认收票</c:if>
				                        <c:if test="${orderTrackingDto.orderState==216}">待买确认终止</c:if>
				                        <c:if test="${orderTrackingDto.orderState==218}">待卖确认终止</c:if>
				                        <c:if test="${orderTrackingDto.orderState==220}">交易完成</c:if>
				                        <c:if test="${orderTrackingDto.orderState==222}">交易关闭</c:if>
			                        </li>
			                        <li class="pull-left margin-right">${orderTrackingDto.userName}</li>
			                    </ul>    
							 </c:forEach>							    		                     
						</div>
					</div>
					<div class="details-list">
						<h3 class="bg-gray">收货信息</h3>
						<div>
							<p><span>收货人 : ${address.recipient}</span>  联系电话 : <em>${address.phone}</em></p>
							<p>收货地址 : ${address.extensionField}</p>
						</div>
					</div>
					<div class="merchandise">
							
					<h3 class="bg-gray">商品信息</h3>						
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
					</tr>
				</thead>
				<tbody>
				<c:forEach var="pitem" items="${item.productList}" varStatus="p">
					<c:forEach var="orderDetail" items="${pitem.orderDetailList}" varStatus="s">
						<c:if test="${p.index == 0}">
							<c:if test="${s.index == 0}">
								<tr>							
									<td rowspan="${fn:length(pitem.orderDetailList)} ">
										<div class="info">
											<div class="pull-left info-img">
												<img src="generateProductImage?id=${pitem.productId}&imgName=${pitem.pImageOne}" height="80" width="80" alt="">
											</div>
											<div class="pull-left info-text">
												${pitem.productName}
											</div>
										</div>
									</td>
									<td>${orderDetail.price }元</td>
									<td>${orderDetail.spec }</td>
									<td>${orderDetail.buyNum } X ${orderDetail.oneboxCount}${orderDetail.meterageUnit}</td>
									<td>${orderDetail.buyNum * orderDetail.oneboxCount}${orderDetail.meterageUnit}
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
								</td>
	
								</tr>
							</c:if>
							
							<c:if test="${s.index != 0}">
								<tr>	
									<td>${orderDetail.price}元</td>
									<td>${orderDetail.spec}</td>  
									<td>${orderDetail.buyNum } X ${orderDetail.oneboxCount}${orderDetail.meterageUnit}</td>
									<td>${orderDetail.buyNum * orderDetail.oneboxCount}${orderDetail.meterageUnit}
										<br>${orderDetail.detailPayMoney}元
									</td>  
								</tr>
							</c:if>
						</c:if>
					 	<c:if test="${p.index != 0}">    
				 			<c:if test="${s.index == 0}">
								<tr>  
									<td rowspan="${fn:length(pitem.orderDetailList)} ">
										<div class="info">
											<div class="pull-left info-img">
												<img src="generateProductImage?id=${pitem.productId}&imgName=${pitem.pImageOne}" height="80" width="80" alt="">
											</div>
											<div class="pull-left info-text">
												${pitem.productName}
											</div>
										</div>
									</td> 
									<td>${orderDetail.price }元</td>
									<td>${orderDetail.spec }</td>  
									<td>${orderDetail.buyNum } X ${orderDetail.oneboxCount}${orderDetail.meterageUnit}</td>
									<td>${orderDetail.buyNum * orderDetail.oneboxCount}${orderDetail.meterageUnit}
										<br><fmt:formatNumber type="number" value="${orderDetail.detailPayMoney} " maxFractionDigits="2"/>元
									</td>
									<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥<fmt:formatNumber type="number" value="${pitem.buyMoneyCount} " maxFractionDigits="2"/>元</td>   
								</tr>
						</c:if>						
						 <c:if test="${s.index != 0}">
						 	<tr>
								<td>${orderDetail.price }元</td>
									<td>${orderDetail.spec }</td>  
									<td>${orderDetail.buyNum } X ${orderDetail.oneboxCount}${orderDetail.meterageUnit}</td>
									<td>${orderDetail.buyNum * orderDetail.oneboxCount}${orderDetail.meterageUnit}<br><fmt:formatNumber type="number" value="${orderDetail.detailPayMoney} " maxFractionDigits="2"/>元
								</td>	 
							</tr>
						</c:if> 
					</c:if> 
					</c:forEach>
					</c:forEach>
					<c:if test="${item.stopReason!='' && item.stopReason!=null}">
						<tr>
							<td style="text-align:left;" colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单终止原因</td>
							<td colspan="5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.stopReason}</td>
						</tr>
					</c:if>
					
					<c:if test="${item.reissueDescription!='' && item.reissueDescription!=null}">
						<tr>
							<td style="text-align:left;" colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;漏发描述</td>
							<td colspan="6" style="text-align:left;">
							<div style="">${item.reissueDescription}</div>
							<c:forEach items="${item.reissueImageList}" var="h">
								<span style="float: left;margin-right: 15px;margin-top: 8px;"><img src='reissueImage?id=${param.orderGroupId}&imgName=${h}' style="height: 170px;width: 140px;"></span>
							</c:forEach>
							</td>
						</tr>
						
					</c:if>
				</tbody>				
			</table>
	</div>
</c:forEach>
				
				</div>
			</div>
		</div>
</body>
</html>
</tiles:put>
</tiles:insert>