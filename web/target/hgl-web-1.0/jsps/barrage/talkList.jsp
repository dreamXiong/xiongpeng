<%@page pageEncoding="UTF-8"%>
  <c:if test="${not empty dataDto}">
  	数据为空
  </c:if>
  <c:if test="${not empty dataDto}">
  <table class="col-10 table-border">
	<tr class="line-height text-center ">
		<th class="col-2">id</th>
		<th class="col-2">openId</th>
		<th class="col-2">名称</th>
		<th class="col-3">创建时间</th>
	</tr>  
  	<c:forEach var="item" items="${dataDto}" varStatus="s">
  		<tr id="tr_${item.id }" >
		    <td class="padding"><p>${item.id}</p>
		    
		    	<input type="hidden" name="talkId" value="${item.id }" /> 
		    </td>
		    <td class="padding">
		    	<p><em>${item.openId}</em></p>
		    </td>
		    <td class="padding"><p>${item.nickname}</p></td>
		    <td class="padding">
		    	<p>${item.createTime}</p>
		    </td>
		   
		  
		  
   		</tr>
   		
  	</c:forEach>
 		<tr>
   			<td>
   					<liguo:dateFormatLabel value="1460115180209" pattern="yyyy-MM-dd HH:mm"/>
   			</td>
   		</tr>

</table>
<liguo:pagination  />

  </c:if>


