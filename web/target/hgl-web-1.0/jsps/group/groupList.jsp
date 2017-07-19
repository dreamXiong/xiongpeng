<%@page pageEncoding="UTF-8"%>
	<div class="merchandise stock">
		<table class="col-10 hover" style="margin-top:5px;">
			<thead>    
				<tr>
					<th width="160">分组名称</th>
					<th width="160">折扣</th>
					<th>说明</th>  
					<th width="160">操作</th>  
				</tr>
			</thead>
			<tbody>
			<c:forEach var="witem" items="${gList}" varStatus="p">
				<tr class="${p.index % 2 == 1 ? 'odd' : ''}">
					<td width="160">${witem.name}</td>  
					<td width="160">${witem.discount }</td>
					<td class="text-ellipsis">${witem.remark}</td>
					<td width="160">
						<button class="btn" type="button" onclick="updateDialog(${witem.id});">修改</button>  
						<%--| <button class="btn" onclick="delectGroupValidate(${witem.id});" type="button">删除</button> --%>
					</td>           
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
