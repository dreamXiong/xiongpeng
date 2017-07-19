<%@page pageEncoding="UTF-8"%>
<table class="col-10">
		<thead class="bg-gray">
			<tr>
				<th>时间</th>
				<th>类型</th>
				<th>数量</th>
				<th>业务号</th>
				<th>说明</th>
				<th>结余</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${experienceList}" var="item">
                   <tr>
                     <td> <liguo:dateFormatLabel
						value="${item.createDt}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
                     <td>
                     	<c:if test="${item.type==0}">购买</c:if>
					<c:if test="${item.type==1}">服务</c:if>
					<c:if test="${item.type==2}">活动</c:if>
                     </td>
                     <td>${item.number}</td>
                     <td><span class="text-info">${item.orderNumber}</span></td>
                     <td>${item.detail}</td>
                     <td>${item.experienceSum}</td>
                   </tr>
                   </c:forEach>
			
		</tbody>
	</table>
<liguo:pagination pageAction="serachExperience"/>