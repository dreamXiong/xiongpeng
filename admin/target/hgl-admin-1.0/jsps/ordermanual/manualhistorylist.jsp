<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
		<th>订单ID</th>
		<th>流水号</th>
		<th>购买人</th>
		<th>购买时间</th>
		<th>手机号</th>
		<th>应付总金额</th>
		<th>修改前状态</th>
		<th>修改后状态 </th>
		<th>操作人</th>
		<th>操作时间</th>
	</tr>
	<c:forEach items="${manualOrderDtos}" var="manualOrderDto">
		<tr>
			<td>${manualOrderDto.orderId}</td>
			<td>${manualOrderDto.orderSerialNo}</td>
			<td>${manualOrderDto.buyId}</td>
			<td>
				<jsp:useBean id="buyDt" class="java.util.Date"/>
				<jsp:setProperty property="time" name="buyDt" value="${manualOrderDto.buyDt}"/>
				<fmt:formatDate value="${buyDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>${manualOrderDto.mobile}</td>
			<td>${manualOrderDto.payAmount}</td>
			<td><c:if test="${manualOrderDto.statusBefore==200}">待确认</c:if>
				<c:if test="${manualOrderDto.statusBefore==202}">待付款</c:if>
				<c:if test="${manualOrderDto.statusBefore==204}">待补发货</c:if>
				<c:if test="${manualOrderDto.statusBefore==206}">已终止</c:if>
				<c:if test="${manualOrderDto.statusBefore==208}">待发货</c:if>
				<c:if test="${manualOrderDto.statusBefore==210}">待确认收货</c:if>
				<c:if test="${manualOrderDto.statusBefore==212}">待登记发票</c:if>
				<c:if test="${manualOrderDto.statusBefore==214}">待确认收票</c:if>
				<c:if test="${manualOrderDto.statusBefore==216}">待买确认终止</c:if>
				<c:if test="${manualOrderDto.statusBefore==218}">待卖确认终止</c:if>
				<c:if test="${manualOrderDto.statusBefore==220}">交易完成</c:if>
				<c:if test="${manualOrderDto.statusBefore==222}">交易关闭</c:if>
			</td>
			<td><c:if test="${manualOrderDto.statusAfter==200}">待确认</c:if>
				<c:if test="${manualOrderDto.statusAfter==202}">待付款</c:if>
				<c:if test="${manualOrderDto.statusAfter==204}">待补发货</c:if>
				<c:if test="${manualOrderDto.statusAfter==206}">已终止</c:if>
				<c:if test="${manualOrderDto.statusAfter==208}">待发货</c:if>
				<c:if test="${manualOrderDto.statusAfter==210}">待确认收货</c:if>
				<c:if test="${manualOrderDto.statusAfter==212}">待登记发票</c:if>
				<c:if test="${manualOrderDto.statusAfter==214}">待确认收票</c:if>
				<c:if test="${manualOrderDto.statusAfter==216}">待买确认终止</c:if>
				<c:if test="${manualOrderDto.statusAfter==218}">待卖确认终止</c:if>
				<c:if test="${manualOrderDto.statusAfter==220}">交易完成</c:if>
				<c:if test="${manualOrderDto.statusAfter==222}">交易关闭</c:if>
			</td>
			<td>${manualOrderDto.operateBy}</td>
			<td>
				<jsp:useBean id="operateDt" class="java.util.Date"/>
				<jsp:setProperty property="time" name="operateDt" value="${manualOrderDto.operateDt}"/>
				<fmt:formatDate value="${operateDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
		</tr>
	</c:forEach>
</table>
<liguo:pagination pageAction="doSearchManualOrderResult"/>