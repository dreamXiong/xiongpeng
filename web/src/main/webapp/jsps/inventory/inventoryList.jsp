<%@page pageEncoding="UTF-8"%>
					<table class="col-10 hover" id="mytable">
						<thead class="bg-gray">
							<tr>
								<th width="30"><input type="checkbox" title="全选" onclick="singleAllChecked()" id="singgle"></th>
								<th width="156">名称</th>
								<th width="100">入库量</th>
								<th width="60">库存量</th>
								<th width="60">锁定量</th>
								<th width="80">成本价</th>
								<th width="80">销售价</th>
								<th width="80">市场价</th>
								<th width="60">状态</th>
								<th width="260">操作选项</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${fn:length(tbpiList) <= 0}">
							<tr>
								<td colspan="10">没有数据</td>
							</tr>
						</c:if>
						<c:forEach items="${tbpiList}" var="tbpiItem" varStatus="s">
							<tr  class="${s.index % 2 == 1 ? 'odd' : ''}">
							<td><input type="checkbox" name="tbpInventoryIds" value="${tbpiItem.id}" onclick="futureChecked()"></td>
							<td id="nameTd_${tbpiItem.id}">${tbpiItem.name}</td>
							<td>${tbpiItem.totalInventory}</td>
							<td>${tbpiItem.saleInventory}</td>
							<td>${tbpiItem.unsaleInventory}</td>
							<td>￥${tbpiItem.instockPrice}</td>
							<td>￥${tbpiItem.outstockPrice}</td>
							<td>￥${tbpiItem.salesPrice}</td>
							<td>
								<c:if test="${tbpiItem.status==1}"><span style="color:green;">上架中</span></c:if>
								<c:if test="${tbpiItem.status!=1}"><span style="color:red;">已下架</span></c:if>
							</td>
							<td>
								<%-- <c:if test="${shopFlag == 0 }"> --%>
									<button type="button" class="btn" onclick="updateInventory(${tbpiItem.id})">修改</button> |
									<button type="button" class="btn" onclick="deleteInventory(${tbpiItem.id})">删除</button> |
								<%-- </c:if> --%>
								<%-- <c:if test="${shopFlag != 0 }">
									<button type="hidden" class="btn" onclick="updateInventory(${tbpiItem.id})">修改</button>
									<button type="hidden" class="btn" onclick="deleteInventory(${tbpiItem.id})">删除</button>
								</c:if> --%>
								<button type="button" class="btn" onclick="inventoryDetails(${tbpiItem.id})">详情</button> |
								<button type="button" class="btn" onclick="updateStatus(${tbpiItem.id},${tbpiItem.status})">
									<c:if test="${tbpiItem.status==1}">下架</c:if>
									<c:if test="${tbpiItem.status!=1}">上架</c:if>
								</button>
							</td>
						</tr>
						</c:forEach>
	
						</tbody>
					</table>	
					<liguo:pagination pageAction="serachInventory" />