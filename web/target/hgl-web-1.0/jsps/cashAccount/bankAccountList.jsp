<%@page pageEncoding="UTF-8"%>
<h3 class="bg-gray">
	银行账户
	<c:if test="${fn:length(tList) < 5}">
		<button type="button" class="btn-bg" onclick="addDialog();" style="margin-left:5px;">
			<span>添加账户</span>
		</button>
	</c:if>
</h3>
<%-- <c:if test="${fn:length(tList) < 5}">
	<h3 class="bg-gray" onclick="addDialog();">添加账户</h3>  
</c:if> --%>
<div style="padding:10px;border:1px solid #eee;" class="stock">         
	<table class="hover col-10">
		<thead>
			<tr>
				<th width="192">账户号</th>     
				<th width="168">银行</th>             
				<th width="184">支行</th>
				<th width="186">姓名</th>
				<th width="175">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${tList}" varStatus="s" >
				<tr class="${s.index % 2 == 1 ? 'odd' : ''}">
					<td>${item.bankAccount }</td>
					<td>${item.bank }</td>
					<td>${item.branch }</td>
					<td>${item.name }</td>
					<td><button type="button" onclick="deleteDialog(${item.id });" class="btn">删除</button></td>
				</tr>
			</c:forEach>
		</tbody> 
	</table>
</div>
<!-- 提现 -->
	<div id="dialog5" class="dialog add-pick">
		  <form id="getMoneyNowgetMoney" action='${ctx}/cashAccount/getMoney' method="post">  
			    <div>
			  		<label>银行账户：</label>
				    <select id="selector" name="accountBankId" style="width:311px;">
						 <c:forEach var="item" items="${tList}" varStatus="s">
							<option value="${item.id }">${item.bank }/${item.bankAccount }</option>
						</c:forEach>
					</select>
		  		</div>
		  		<div>
			  		<label>提现金额：</label>
				    <input type="text" maxlength="10" class="inputNotNull float " name="money" id="money" style="width:300px;" placeholder="提现最大额度${money}"/>  
				    <div id="moneyError" style="width: 180px;color: red;padding: 0 0 0 90px;display: none;" >提现金额不能超过${money}</div>
		  		</div>
		  </form>
		  
		  <input type="hidden" id="bankAccountId" value=""/>
	  <input type="hidden" id="userGroupId" />
	</div>