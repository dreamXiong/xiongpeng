<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的推荐" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	

	
	<title>我的推荐</title>
	
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/account.js"></script>
<script src="${ctx}/Font-Awesome/src/assets/js/ZeroClipboard-1.1.7.min.js"></script>

<body>


<!-- 内容板块开始 -->
	<div class="" style="border-top:none;">
		<div class="area me">
			<div class="main-right pull-right">
				<div class="store">
					<div style="margin-bottom:20px;">       
						<h3 class="bg-gray">
							账户金额
							<c:if test="${money gt 0.0}">
								<c:if test="${fn:length(tList) > 0}">
									<button type="button" class="btn-bg" onclick="showGetMoneyDialog();" style="margin-left:5px;">
										<span>提现</span>
									</button>
								</c:if>
							</c:if>
						</h3>
						<div style="padding:10px 20px;line-height:24px;border:1px solid #eee;">
							<p><b class="text-red">账户总额：</b><fmt:formatNumber value="${tbCashAccount.balance }" pattern="#,##0.00" ></fmt:formatNumber></p>
							<p><b class="text-red">冻结金额：</b><fmt:formatNumber value="${tbCashAccount.freeze }" pattern="#,##0.00" ></fmt:formatNumber></p>
							<p><b class="text-red">可用金额：</b>${money}</p>  
							<input type="hidden" maxlength="18" name="maxMoney" id="maxMoney" value="${money}"/>
						</div>
					</div>
					<div id="bankAccountListId">      
							<%@include file="bankAccountList.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 内容板块结束 -->

<div id="dialog2" class="dialog add-pick">
		  <form id="addBankAccount">  
			    <div>
			  		<label>银行：</label>
				    <input type="text" maxlength="18" class="inputNotNull" id ="bank" placeholder="XX银行" name="bank" style="width:300px;">  
		  		</div>
			   <div>
			  		<label>账户号：</label>
				    <input type="text" maxlength="19" onblur="addValidateAccount();" id="bankAccount" class="inputNotNull number" name="bankAccount" placeholder="16~19数字" style="width:300px;">  
				    <div id="key_info_bankAccountbankAccount" style="dispaly:none;"></div>
				    <div id="bankAccountError" style="width: 180px;color: red;padding: 0 0 0 90px;display: none;" >账户重复</div>
		  		</div>
		  		<div>
			  		<label>支行：</label>
				    <input type="text" maxlength="50" class="inputNotNull" name="branch" placeholder="深圳市龙华新区XX支行" style="width:300px;">  
		  		</div>
		  		<div>
			  		<label>姓名：</label>
				    <input type="text" maxlength="10" class="inputNotNull" name="name" placeholder="张三" style="width:300px;">  
		  		</div>
		  </form>
		    
		  <input type="hidden" id="bankAccountId" value=""/>
	  <input type="hidden" id="userGroupId" />
	</div>
	
	<div id="dialog3" class="dialog">
		<div>该账户下有待审核的提现记录，暂不无删除！！</div>
	</div>
	
	<div id="dialog4" class="dialog">
	   	您是否确定删除该银行账户？
	</div>
</body>
<script>

</script>
</html>
</tiles:put>
</tiles:insert>