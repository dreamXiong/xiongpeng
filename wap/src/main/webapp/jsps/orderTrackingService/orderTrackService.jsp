<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="服务订单详情" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${ctx}/js/hgl/orderTrackService.js"></script>
	<style>
	.icon-trash,.icon-ok{
		display: inline-block;
		width:30px;
		text-align:center;
	}
	.icon-trash{    
		right:30px!important;          
	}
	</style>
</head>	
<body>
<%@include file="../common/header.jsp"%>

<div class="container orderdetail-info" style="margin-bottom:58px;" >
	<div class="box-shadow clearfix">
			<c:choose>
					<c:when test="${orderService.orderStatus==800}">
						<img class="pull-left" src="${ctx}/images/800.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==802}">
						<img class="pull-left" src="${ctx}/images/802.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==804}">
						<img class="pull-left" src="${ctx}/images/804.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==806}">
						<img class="pull-left" src="${ctx}/images/806.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==808}">
						<img class="pull-left" src="${ctx}/images/808.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==810}">
						<img class="pull-left" src="${ctx}/images/810.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==812}">
						<img class="pull-left" src="${ctx}/images/812.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==814}">
						<img class="pull-left" src="${ctx}/images/814.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==816}">
						<img class="pull-left" src="${ctx}/images/816.png" alt="" width="40">
					</c:when>	
					<c:when test="${orderService.orderStatus==820}">
						<img class="pull-left" src="${ctx}/images/820.png" alt="" width="40">
					</c:when>
					<c:when test="${orderService.orderStatus==822}">
						<img class="pull-left" src="${ctx}/images/822.png" alt="" width="40">
					</c:when>								
			</c:choose>				
		<div class="pull-left" style="line-height:40px;margin-left:10px;color:#333;font-size:15px;" id="fuwu">
			<c:choose>				
				<c:when test="${orderService.orderStatus==800 && webUser.typeId==106}">师傅们正在商量谁服务您</c:when>
				<c:when test="${orderService.orderStatus==802 && webUser.typeId==106}">${orderService.sfUserName}师傅正在准备为您服务</c:when>
				<c:when test="${orderService.orderStatus==804 && webUser.typeId==106}">${orderService.sfUserName}师傅正在为您服务</c:when>
				<c:when test="${orderService.orderStatus==806 && webUser.typeId==106}">${orderService.sfUserName}师傅正在为您服务</c:when>
				<c:when test="${orderService.orderStatus==808 && webUser.typeId==106}">${orderService.sfUserName}师傅已服务完成,请付款</c:when>
				<c:when test="${orderService.orderStatus==810 && webUser.typeId==106}">请为${orderService.sfUserName}师傅的服务做出评价</c:when>
				<c:when test="${orderService.orderStatus==812 && webUser.typeId==106}">服务已完成,希望能再次为您服务</c:when>
				<c:when test="${orderService.orderStatus==814 && webUser.typeId==106}">服务已完成,希望能再次为您服务 </c:when>
				<c:when test="${orderService.orderStatus==816 && webUser.typeId==106}">师傅待确定,希望能再次为您服务</c:when>
				<c:when test="${orderService.orderStatus==820 && webUser.typeId==106}">${orderService.sfUserName}师傅已推送材料</c:when>
				<c:when test="${orderService.orderStatus==822 && webUser.typeId==106}">您已确认材料</c:when>
				<c:when test="${orderService.orderStatus==800 && webUser.typeId==114}">如果服务时间无冲突,请接单</c:when>
				<c:when test="${orderService.orderStatus==802 && webUser.typeId==114}">${orderService.sfUserName}师傅请合理安排时间进行服务</c:when>
				<c:when test="${orderService.orderStatus==804 && webUser.typeId==114}">服务中请注意安全</c:when>
				<c:when test="${orderService.orderStatus==806 && webUser.typeId==114}">请您尽快恢复服务</c:when>
				<c:when test="${orderService.orderStatus==808 && webUser.typeId==114}">服务已完成,请敦促客人付款</c:when>
				<c:when test="${orderService.orderStatus==810 && webUser.typeId==114}">优质的服务来源于客户的评价</c:when>
				<c:when test="${orderService.orderStatus==812 && webUser.typeId==114}">服务已完成</c:when>
				<c:when test="${orderService.orderStatus==814 && webUser.typeId==114}">服务已完成 </c:when>
				<c:when test="${orderService.orderStatus==816 && webUser.typeId==114}">师傅待确定</c:when>
				<c:when test="${orderService.orderStatus==820 && webUser.typeId==114}">师傅已推送材料</c:when>
				<c:when test="${orderService.orderStatus==822 && webUser.typeId==114}">用户已确认材料</c:when>
			</c:choose>
		</div>		
	</div>
	
	<div class="box-shadow">
		<h3 style="font-weight:normal;color:#999;font-size:14px;font-family:'Helvetica Neue', Helvetica, 'Microsoft YaHei', STHeiTi, sans-serif;">
			需求者：
		</h3>
		<div style="line-height:30px;margin-bottom:5px;">
			${orderService.userName}
			<a href="tel:${orderService.phone}" style="margin-left:5px;">${orderService.phone}</a>	
		</div>		
		<h3 style="border-top:1px solid #eee;padding-top:5px;font-weight:normal;color:#999;font-size:14px;
		font-family:'Helvetica Neue', Helvetica, 'Microsoft YaHei', STHeiTi, sans-serif;">
			服务者：
		</h3>
		<div style="line-height:30px;">
			${orderService.sfUserName}
			<a href="tel:${orderService.sfPhone}" style="margin-left:5px;">${orderService.sfPhone}</a>	    
		</div>
	</div>           
	     
	<div class="box-shadow">
		<p>          
			<span class="col-l">订单状态：</span>
			<span class="col-r" id="orderStatus" style="margin-bottom:20px;">
				<c:choose>
					<c:when test="${orderService.orderStatus==800}">待接单</c:when>
					<c:when test="${orderService.orderStatus==802}">待服务</c:when>
					<c:when test="${orderService.orderStatus==804}">服务中</c:when>
					<c:when test="${orderService.orderStatus==806}">挂起中</c:when>
					<c:when test="${orderService.orderStatus==808}">待支付</c:when>
					<c:when test="${orderService.orderStatus==810}">待评价</c:when>
					<c:when test="${orderService.orderStatus==812}">完成</c:when>
					<c:when test="${orderService.orderStatus==814}">关闭 </c:when>
					<c:when test="${orderService.orderStatus==816}">师傅待确定</c:when>
					<c:when test="${orderService.orderStatus==820}">师傅已推送材料</c:when>
					<c:when test="${orderService.orderStatus==822}">用户已确认材料</c:when>
				</c:choose>
			</span>
		</p>
		<p>
			<span class="col-l">订单编号：</span>
			<span class="col-r">${orderService.orderSerialNo}</span>
		</p>
		<p>
			<span class="col-l">服务时间：</span>
			<span class="col-r">
				<jsp:useBean id="appointmentDt" class="java.util.Date"/>
				<jsp:setProperty name="appointmentDt" property="time" value="${orderService.appointmentDt}"/>
				<fmt:formatDate value="${appointmentDt}" type="both" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;${orderService.appointmentPeriodDt}
			</span>
		</p>
		<p>
			<span class="col-l">服务项目：</span>
			<span class="col-r">${serviceType.name}&nbsp;</span>
		</p>
		<p class="address">
			<span class="col-l">服务地址：</span>
			<span class="col-r"> ${orderService.address}&nbsp;</span>
		</p>
		<p class="remark">
			<span class="col-l">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
			<span class="col-r">${orderService.message}&nbsp;</span>
		</p>
	</div>
	<div class="box-shadow">
		<c:forEach items="${orderTrackingServices}" var="orderTrackingService">
			<p style="padding-left:145px;">
				<span class="col-l" style="width:140px;">
					<jsp:useBean id="operateTime" class="java.util.Date"/>
					<jsp:setProperty name="operateTime" property="time" value="${orderTrackingService.operateDt}"/>
					<fmt:formatDate value="${operateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				</span>   
				<span class="col-r" >${orderTrackingService.operateName}</span>    
			</p>    
			
		</c:forEach>
	</div>      
	
	<div class="box-shadow orderdetail">
		<p style="text-align:right;height:18px;">
			<span class="col-l" style="text-align:left;">购买材料</span>
		</p>
		<div style="margin-top:10px;">			
			<ul class="shopping-lists order-list">
				<c:forEach items="${orderServiceDetails}" var="orderServiceDetail">		
						<li class="clearfix" id="${orderServiceDetail.id}" style="border-bottom-color:#eee;">
							<div class="item1">
								<input type="hidden" name="cartIds" id="cartIds${orderServiceDetail.id}" value="${orderServiceDetail.inventoryId}=${orderServiceDetail.buyNum}"/>
								<img src="showProductImage?id=${orderServiceDetail.productInventory.productId}&imgName=${orderServiceDetail.pImgOne}" alt=""/>
							</div>
							<div class="item2">
								<h3>
									${orderServiceDetail.productInventory.name}
									<div class="pull-right" style="font-size:18px;color:#eee;">										 								
										<c:if test="${webUser.typeId==106}">         
											<input type="hidden" value="${orderServiceDetail.status}" id="orderStatus${orderServiceDetail.id}"/>
											<c:if test="${orderServiceDetail.status<822}">	
												<span class="icon-trash" onclick="deleteOrderServiceDetail(${orderServiceDetail.id})"></span> <!-- 删除材料 -->
											</c:if>			
											<span class="icon-ok" onclick="pushCart(${orderServiceDetail.id},'${orderService.repairmanId}')"></span> <!-- 确认材料 -->																																																							
										</c:if>	
									</div>
								</h3>   
								<dl>
									<dt class="clearfix">
										<span class=" icon-angle-right" style="margin-top:4px;"></span>
										<div class="pull-left" style="margin-top:4px;">
											规格：${orderServiceDetail.productInventory.spec}
										</div>										
										
									</dt>
									<dd>材质：${orderServiceDetail.productInventory.material}</dd>
									<c:forEach items="${orderServiceDetail.attrs}" var="attrs">
										<dd>${attrs.attrName}：${attrs.attrValue}</dd>
									</c:forEach>
									
								</dl>           
								<div class="clearfix operate" >     
									<div class="pull-left text-red font-bold">
										<span class="dollar">¥</span>
										<span class="unit">${orderServiceDetail.productInventory.salesPrice}</span>
									</div>
									<div class="pull-right">
										X${orderServiceDetail.buyNum}
									</div>
								</div>						
							</div>
						</li>
				</c:forEach>				
			</ul>
		</div>
	</div>

	<c:if test="${orderService.cancelFlag!=null && orderService.cancelFlag!=''}">
		<div class="box-shadow">
			<p>
				<span class="col-l">取消原因：</span>
				<span class="col-r">用户重新下单</span>
			</p>
		</div>
	</c:if>
	<c:if test="${orderService.orderStatus==812}">
		<div class="box-shadow">
			<div style="color:#999;">用户评价</div> 
			<input type="hidden" value="${orderService.evaluateStart}" id="start">  
			<div class="star">
				<ul>			
					<li>				
						<nav style="left:0;">
							<a class="active" href="javascript:;"></a>
							<a href="javascript:;"></a>
							<a href="javascript:;"></a>
							<a href="javascript:;"></a>
							<a href="javascript:;"></a>
						</nav>				
					</li>				
				</ul>			
			</div>
			<div>${orderService.evaluate}</div>
		</div>
	</c:if>	
</div>
<c:if test="${userType==114}">
	<footer class="footer serve-foot" style="height:auto;">	
		<c:if test="${webUser.typeId==114}">
			<input type="hidden" id="operateBtn" value="${orderService.orderStatus}"/>
			<c:if test="${master!=null && (orderService.orderStatus==802||orderService.orderStatus==806)}">
				<button type="button" style="margin-bottom:10px;" onclick="startServer(${orderService.id})" id="servieBtn" value="${orderService.orderStatus}">开始服务</button>
			</c:if>			
			<c:if test="${master!=null && orderService.orderStatus==804}">
				<button type="button"  onclick="pushShoppingCart(${param.orderServiceId})" id="pushBtn" value="${orderService.orderStatus}">推送材料</button>
			</c:if>     		
		</c:if>		
	</footer>
</c:if>


<form action="${ctx}/wapOrderService/confirmAddMyCart" method="post" id="pushCartForm">
	<input type="hidden" name="repairmanId" id="repairmanId"/>
	<input type="hidden" name="orderListNum" id="orderListNum"/>
	<input type="hidden" name="orderServiceId" id="orderServiceId" value="${param.orderServiceId}"/>
</form> 
		    
</body>             
<script>
 	$(function() {
 		var val = $('#start').val();
		$(document).delegate('.item2 dt','click',function(){
			$(this).find('.icon-angle-right').toggleClass('rotate');
			$(this).siblings('dd').slideToggle();
		});
		$('.star nav a:lt('+ val +')').addClass('active');      
		       
	});
</script>


