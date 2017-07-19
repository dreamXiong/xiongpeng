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
		<div class="area me">
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list">
						<h3 class="bg-gray"><font style="cursor: pointer;" onclick="history.back(-1);">销售订单</font> | 订单详情</h3>
							<div class="clear">
							<p class="pull-left">订单号 : <em>${orderGroupDetailDto.orderSerialNo }</em></p>
							<p class="pull-right">订单状态 : <liguo:dictLabel key="${orderGroupDetailDto.orderStatus}" />
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
						<div style="margin-left:30px;">							
							<c:forEach items="${orderTrackingDtos}" var="orderTrackingDto">
								<ul class="clear line-height">    
									<li class="pull-left margin-right">						
									   <liguo:dateFormatLabel value="${orderTrackingDto.operateDt}" pattern="yyyy-MM-dd  HH:mm:ss" />
								   	</li>
								   	<li class="pull-left margin-right" style="width:150px;">
								   		${orderTrackingDto.operateName}
			                        </li>
								   	<li class="pull-left margin-right" style="width:90px;">
								   		<liguo:dictLabel key="${orderTrackingDto.orderState}" />
			                        </li>
			                        <li class="pull-left margin-right">${orderTrackingDto.userName}</li>
			                    </ul>    
							 </c:forEach>							    		                     
						</div>
					</div>
					<c:if test="${not empty orderGroupDetailDto.postage}">
						<div class="details-list">
							<h3 class="bg-gray">邮费</h3>
							<div>
								<p><span>邮费 : ￥${orderGroupDetailDto.postage }元</span></p>
							</div>
						</div>
					</c:if>
					<div class="details-list">
						<h3 class="bg-gray">收货信息</h3>
						<div>
							<p><span>收货人 : ${orderGroupDetailDto.recipient }</span>  联系电话 : <em>${orderGroupDetailDto.phone }</em></p>
							<p>收货地址 : ${orderGroupDetailDto.extensionField }</p>
						</div>
					</div>
					<c:if test="${not empty orderGroupDetailDto.message}">
						<div class="details-list">
							<h3 class="bg-gray">订单留言</h3>
							<div>
								<p><span>${orderGroupDetailDto.message}</span></p>    
							</div>
						</div>
					</c:if>
					<c:if test="${not empty orderGroupDetailDto.evaluateStart}">
						<div class="details-list">
							<h3 class="bg-gray">订单评论</h3>
							<div>
								<div class="star" id="evaluateInfo" style="height:18px;margin:10px 0">
									<nav style="left:0;">
									  <c:forEach var="i" begin="1" end="${orderGroupDetailDto.evaluateStart }">
										<a href="javascript:;" class="active"></a>   
									  </c:forEach>
									</nav>
								</div>
								<p><span>${orderGroupDetailDto.evaluate}</span></p>    
							</div>
						</div>
					</c:if>
					<c:if test="${not empty orderGroupDetailDto.stopReason}">
						<div class="details-list">
							<h3 class="bg-gray">订单终止原因</h3>
							<div>
								<p><span>${orderGroupDetailDto.stopReason}</span></p>    
							</div>      
						</div>
					</c:if>
					<div class="details-list">
						<h3 class="bg-gray">商品信息</h3>		
							<div style="padding:10px;border:1px solid #ccc;margin-top:-1px;">       
								<%@include file="saleOrderGroupDetailList.jsp" %>
							</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
</tiles:put>
</tiles:insert>