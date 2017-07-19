<%@page pageEncoding="UTF-8"%>
	<div class="merchandise stock">

		<table class="col-10 hover">
			<thead>
				<tr>
		           <th>姓名</th>
		           <th>银行账户</th>
		           <th>开户行</th>
		           <th>金额</th>
		           <th>时间</th>
		           <th>状态</th>
		           <th>操作选项</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${withdrawalsDtos}" varStatus="p">
					<tr class="${p.index % 2 == 1 ? 'odd' : ''}">
					   <td> ${item.realName}</td>
			           <td> ${item.bankNo}</td>
			           <td> ${item.bankName}</td>
			           <td> ${item.money}</td>  
			           <td><liguo:dateFormatLabel value="${item.operationDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td></td>
			           <td id="statusId_${item.id}">
				            <c:if test="${item.state==0}">待审核</c:if>
							<c:if test="${item.state==1}">审核通过</c:if>
							<c:if test="${item.state==2}">审核拒绝</c:if>
					   </td>
			           <td id="buttonId_${item.id}">
					         <c:if test="${item.state==0}">
					         	<button type="button" class="btn btn-primary btn-sm" onclick="doAutomaticShow(${item.id})">审核通过</button>
					         	<button type="button" class="btn btn-primary btn-sm" onclick="refuseAutomaticShow(${item.id})">审核拒绝</button>
					         </c:if>   
			         		<c:if test="${item.state == 1 || item.state == 2}">-</c:if>
			           </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<liguo:pagination dataDomId="key_withrawals_list" pageAction="searchResult" />