<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${wapOrderServiceDtoList}" varStatus="o">
	<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
	<div class="merchandise stock">
		<div class="info-title clear">
			<div class="pull-left">
				<i>订单号: </i><a href="${ctx}/orderTracking/doSearchReasult?orderServiceId=${item.id}">${item.orderSerialNo}</a>
			</div>
			<div class="pull-left">
				<i>订单时间: </i><span> <liguo:dateFormatLabel value="${item.createDt}" pattern="yyyy-MM-dd" /></span>
			</div>  
		</div>
		<table class="col-10 stock">
			<thead>
				<tr>
					<th width="116">客户姓名</th>
					<th width="100">客户电话</th>
					<th width="126">服务类型</th>  
					<th width="130">客户地址</th>
					<th width="100">备注信息</th>
					<th width="56">服务状态</th>
					<th width="102">服务操作</th>
				</tr
			</thead>
			<tbody> 
					<tr style="border-bottom:1px solid #eee;">
						<td><div class="info"><div class="pull-left info-text"><p>${item.userName}</p></div></div></td>
						<td>${item.phone}</td>
						<td>${item.serciceName}</td>
						<td>${item.address }</td>
						<c:if test="${empty item.message}">
							<td>该客户没有备注信息...</td>
						</c:if>
						<c:if test="${not empty item.message}">
							<td>${item.message}</td>
						</c:if>
						<td>
							<span style="color:green;">${item.serviceStatus}</span>
						</td>
						<td>
							<a class="btn" href="javascript:void(0);" onclick="orders('${item.id}');">接单</a> 
							<br>
						</td>
				  	</tr>
			</tbody>
		</table>
	</div>
</c:forEach>
<liguo:pagination dataDomId="key_serviceOrder_list" pageAction="pageindex" />