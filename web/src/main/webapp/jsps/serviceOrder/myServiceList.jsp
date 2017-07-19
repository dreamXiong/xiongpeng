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
		<table class="col-10 stock" >
			<thead>
				<tr>
					<th width="100">客户姓名</th>
					<th width="100">客户电话</th>
					<th width="126">服务类型</th>  
					<th width="143">客户地址</th>
					<th width="100">备注信息</th>
					<th width="69">师傅名称</th>
					<th width="100">师傅电话</th>
					<th width="64">服务状态</th>
					<th width="60">服务金额</th>
					<th width="90">服务操作</th>
				</tr>
			</thead>       
			<tbody>
					<tr style="border-bottom:1px solid #eee;">
						<td>${item.userName}</td>
						<td>${item.phone}</td>
						<td>${item.serciceName}</td>
						<td style="max-width:130px;">${item.address }</td>
						<c:if test="${empty item.message}">
							<td>该客户没有备注信息...</td>
						</c:if>
						<c:if test="${not empty item.message}">
							<td>${item.message}</td>
						</c:if>
						<td>${item.sfUserName}</td>
						<td>${item.sfPhone}</td>
						<td>
							<span style="color:green;">${item.serviceStatus}</span>
						</td>
						<td>
							<c:if test="${item.totalMoney >0}">
								<span style="font-size:0.6rem;">¥</span>${item.totalMoney}
							</c:if>
						</td>
						<td>
							<c:if test="${item.orderStatus == 802}">
								<a href="javascript:;"  onclick="doAltService1(${item.id})" class="btn">取消接单</a>
								<br/>	
								<a href="javascript:;"  onclick="startServer(${item.id})"  class="btn">开始服务</a>
								<br/>	
								<a href="${ctx}/serviceOrder/findMymaster?id=${item.id}"   class="btn">分配订单</a>
							</c:if>
							<c:if test="${item.orderStatus == 804}">
								<a href="javascript:;"  onclick="doAltService2(${item.id})"  class="btn">服务挂起</a>
								<br/>
								<c:if test="${item.totalMoney==null}">
									<a onclick="updatePrice(${item.id},0)"  class="btn">完成服务</a>
								</c:if>
								<c:if test="${item.totalMoney!=null}">
									<a onclick="updatePrice(${item.id},${item.totalMoney})"  class="btn">完成服务</a>
								</c:if>
							</c:if>
							<c:if test="${item.orderStatus == 806}">
								<a href="javascript:;"  onclick="doAltService3(${item.id})"  class="btn">恢复服务</a>
							</c:if>
							<c:if test="${item.orderStatus == 810}">
								<a onclick="updatePrice(${item.id},${item.totalMoney})"  class="btn">修改价格</a>
							</c:if>
							<c:if test="${item.orderStatus == 816}">
								<a href="javascript:;"  onclick="doOrderService2(${item.id})"  class="btn">确认收款</a>
							</c:if>
							<c:if test="${item.orderStatus == 800}">
								<span class="text-red">已取消此订单</span>
							</c:if>
						</td>
				  	</tr>
			</tbody>
		</table>
	</div>
</c:forEach>
	<liguo:pagination dataDomId="key_myService_list" pageAction="myServiceList" />