<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
		<th>订单ID</th>
		<th>订单号</th>
		<th>购买人</th>
		<th>下单时间</th>
		<th>手机号</th>
		<th>应付总金额</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${orderGroupDto}" var="groupDto">
		<tr>
			<td>${groupDto.id}</td>
			<td>${groupDto.orderSerialNo}</td>
			<td>${groupDto.userName}</td>
			<td>
				<jsp:useBean id="createdt" class="java.util.Date"/>
				<jsp:setProperty property="time" name="createdt" value="${groupDto.createDt}"/>
				<fmt:formatDate value="${createdt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>${groupDto.mobile}</td>
			<td>${groupDto.payMoney}</td>
			<td id="orderStatus${groupDto.id}">
				<c:if test="${groupDto.orderStatus==200}">待确认</c:if>
				<c:if test="${groupDto.orderStatus==202}">待付款</c:if>
				<c:if test="${groupDto.orderStatus==204}">待补发货</c:if>
				<c:if test="${groupDto.orderStatus==206}">已终止</c:if>
				<c:if test="${groupDto.orderStatus==208}">待发货</c:if>
				<c:if test="${groupDto.orderStatus==210}">待确认收货</c:if>
				<c:if test="${groupDto.orderStatus==212}">待登记发票</c:if>
				<c:if test="${groupDto.orderStatus==214}">待确认收票</c:if>
				<c:if test="${groupDto.orderStatus==216}">待买确认终止</c:if>
				<c:if test="${groupDto.orderStatus==218}">待卖确认终止</c:if>
				<c:if test="${groupDto.orderStatus==220}">交易完成</c:if>
				<c:if test="${groupDto.orderStatus==222}">交易关闭</c:if>				
			</td>
			<td><a href="javascript:void(0)" class="btn btn-primary btn-xs" onclick="updateOrderGroupStatus(${groupDto.id},${groupDto.orderStatus})">补单</a></td>
		</tr>
	</c:forEach>
</table>
<liguo:pagination pageAction="doSearchResult"/>