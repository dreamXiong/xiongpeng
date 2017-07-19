<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="col-10">
	<thead class="bg-gray">
		<tr>
			<th>时间</th>
			<th>类型</th>
			<th>状态</th>
			<th>数量</th>
			<th>业务号</th>
			<th>说明</th>
			<th>结余</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${couponInfos}" var="couponInfo">
			<tr>
				<td>
					<jsp:useBean id="operatedatetime" class="java.util.Date" />  
					<jsp:setProperty name="operatedatetime" property="time" value="${couponInfo.operatedatetime}" />  
					<fmt:formatDate value="${operatedatetime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${couponInfo.typeId==300}">购买</c:if>
					<c:if test="${couponInfo.typeId==302}">赠送</c:if>
				</td>
				<td>
					<c:if test="${couponInfo.status==400}">获得</c:if>
					<c:if test="${couponInfo.status==402}">使用</c:if>
				</td>
				<td>${couponInfo.amount}</td>
				<td><span class="text-info">${couponInfo.orderSerialNo}</span></td>
				<td>${couponInfo.remark}</td>
				<td>${couponInfo.currentGainAmt-couponInfo.currentEmployAmt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<liguo:pagination pageAction="doSearchResult"/>