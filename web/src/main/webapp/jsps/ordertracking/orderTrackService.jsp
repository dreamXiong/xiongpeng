<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="服务订单详情" />
	<tiles:put name="body" type="String">
		<c:set value="takl" var="modalName"></c:set>
		<html>
<link rel="stylesheet" href="${ctx}/css/me.css">
<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
<script src="${ctx}/js/hgl/toastr.js"></script>
<script src="${ctx}/js/hgl/orderTrackService.js"></script>

<body>
	<div class="main-right pull-right">
		<div class="details">
			<div class="details-list">
				<h3 class="bg-gray">师傅信息</h3>
				<div>
					<p>
						<span>师傅:${worker.name }</span>联系电话 : <em>${worker.mobile }</em>
						<%-- <c:if test="${orderService.orderStatus==802||orderService.orderStatus==806}">
							<button type="button" class="btn-bg" 
									onclick="startServer(${orderService.id})" id="servieBtn"
								style="margin-top: -1px; margin-left: 5px;">
								<span style="margin-right: 0;">开始服务</span>
							</button>
						</c:if>
						
						<c:if test="${orderService.orderStatus == 804}">
							<button type="button" class="btn-bg" 
									onclick="doAltService2(${orderService.id})" 
								style="margin-top: -1px; margin-left: 5px;">
								<span style="margin-right: 0;">服务挂起</span>
							</button>
							
							<button type="button" class="btn-bg" 
									onclick="updatePrice(${orderService.id},${serviceType.price })"
								style="margin-top: -1px; margin-left: 5px;">
								<span style="margin-right: 0;">完成服务</span>
							</button>
						</c:if> --%>
					</p> 
				</div>
			</div> 
			<div class="details-list">
						<h3 class="bg-gray">客户信息</h3>
						<div>
							<p><span>客户 :${orderService.userName }</span>  联系电话 : <em>${orderService.phone }</em></p>
						</div>
					</div> 
					<div class="details-list">
						<h3 class="bg-gray">订单详情</h3>
						<p><b>服务项目</b> : ${serviceType.name }</p>
						<p><b>订单状态</b> : <span style="color:red;">
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
						</span></p>
						<p><b>订单编号</b> : ${orderService.orderSerialNo}</p>
						<p><b>服务时间</b> : <liguo:dateFormatLabel value="${orderService.appointmentDt}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;&nbsp;${orderService.appointmentPeriodDt }
						<p><b>服务地址</b> : ${orderService.address}&nbsp;</p>
						<p><b>备注信息</b> : ${orderService.message}&nbsp;</p>
					</div>
					<div class="details-list">
						<h3 class="bg-gray">订单跟踪</h3>
						<div>
							<c:forEach items="${orderTrackingServices}" var="orderTrackingService">
								<p>
									<span>
										<jsp:useBean id="operateTime" class="java.util.Date"/>
										<jsp:setProperty name="operateTime" property="time" value="${orderTrackingService.operateDt}"/>
										<fmt:formatDate value="${operateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>   
									<span class="col-r" >${orderTrackingService.operateName}</span>    
								</p>    
							</c:forEach>
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
			</div>	
	</div>
		<form action="${ctx}/wapOrderService/confirmAddMyCart" method="post" id="pushCartForm">
			<input type="hidden" name="repairmanId" id="repairmanId"/>
			<input type="hidden" name="orderListNum" id="orderListNum"/>
			<input type="hidden" name="orderServiceId" id="orderServiceId" value="${param.orderServiceId}"/>
			<input type="hidden" id="operateBtn" value="${orderService.orderStatus}"/>
		</form> 
	</body>
	<!-- 改价时弹出框 -->
	<div id="shipcost" class="dialog">
	  <form>
	    <label for="name">请输入新的单价</label>
	    <input type="hidden" name="id" id="id"/>
		<input style="width: 400px;" name="totalMoney" id="totalMoney"/>
		<span id="moneyError" style="color:red;font-size:12px;"></span>	
	  </form>
	</div>
</html>
<script>
	$(function() {
		$(document).delegate('.item2 dt','click',function(){
			$(this).find('.icon-angle-right').toggleClass('rotate');
			$(this).siblings('dd').slideToggle();
		});
	});
</script>
	</tiles:put>
</tiles:insert>