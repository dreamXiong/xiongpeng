<%@page pageEncoding="UTF-8"%>
<div class="merchandise stock ">
	<table class="col-10 hover">
		<thead>
			<tr>
				<th style="width: 270px;">用户名</th>
				<th style="width: 270px;">用户账号</th>
				<th style="width: 66px;">电话</th>
				<th style="width: 126px;">类型</th>
				<th style="width: 126px;">用户状态</th>
				<th style="width: 126px;">用户分组操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="witem" items="${uList}" varStatus="p">
				<tr class="${p.index % 2 == 1 ? 'odd' : ''}">
					<td style="width: 270px;">${witem.name}</td>
					<td style="width: 270px;">${witem.userName}****</td>
					<td style="width: 66px;">${witem.mobile }</td>
					<td class="text-ellipsis" style="width: 126px;">
						<c:if test="${witem.typeId == 104}">经销商</c:if>
						<c:if test="${witem.typeId == 106}">终端客户</c:if>
						<c:if test="${witem.typeId == 114}">师傅</c:if>
					</td>
					<td style="width: 66px;">
						<c:if test="${witem.state == 0 }">待审核</c:if>
						<c:if test="${witem.state == 1 }">已审核</c:if>
						<c:if test="${witem.state == 2 }">审核拒绝</c:if>
						<c:if test="${witem.state == 3 }">关闭</c:if>
					</td>
					<td id="checkbox_${p.index}" class="text-ellipsis" style="width:500px;">
						<c:if test="${empty witem.roleId || witem.roleId == 1 }">
							<c:forEach var="item" items="${gList}" varStatus="w">  
								<c:if test="${empty witem.userGroup}">
									<label class="orderGoupList" id="${witem.id }" name="${p.index}"> 
										<input type="checkbox" name="checkbox_${p.index}" value="${item.id}" />${item.name}
									</label>
								</c:if>
								    
								<c:if test="${not empty witem.userGroup}">
									<c:if test="${fn:contains(witem.userGroup,item.id)}">
									    <label class="orderGoupList" id="${witem.id }" name="${p.index}">   
											<input type="checkbox" name="checkbox_${p.index}"  checked="true" value="${item.id}" />${item.name}  
										</label>
									</c:if>   
									<c:if test="${!fn:contains(witem.userGroup,item.id)}">
									   <label class="orderGoupList" id="${witem.id }" name="${p.index}"> 
											<input type="checkbox" name="checkbox_${p.index}" value="${item.id}" />${item.name}
										</label>
									</c:if>
								</c:if>
							</c:forEach> 
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>  
</div>
<liguo:pagination dataDomId="key_userGroup_list" pageAction="serachUserGroup" />