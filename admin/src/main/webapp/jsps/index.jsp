<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/common/indexPageLayout.jsp"%>
<%@ include file="/WEB-INF/view/common/indexPageHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/menu.jsp"%>
<%@ include file="/WEB-INF/view/common/footer.jsp"%>

<%-- <tiles:insert beanName="indexPage">
	<tiles:put name="title" value="登录---Clochase" />
	<tiles:put name="body" type="String"> --%>
		<%-- <div id="ContentWrapper">
			<div class="QuickMenu locaBox">当前位置：<a href="${f:url('/') }">首页</a></div>
			<div class="clear"></div>
			<div class="rightTitle_01"><h2>待办事物</h2></div>
			<c:if test="${offlineMoney&&cashTransOut}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_02 mar_b_30">
					<tr>
						<th width="50%">
							<a id="cashTransOut" href="./cashTransOutManage?cashstaus=1" class="link_cblue uline mar_l_30"><span>待审核的出金申请 ${cashTransOutCount} 条</span></a>
						</th>
						<th width="50%">
							<a id="offlineMoney" href="./offlineMoneyManage?cashstaus=1" class="link_cblue uline mar_l_30"><span>待审核的线下入金申请  ${offlineMoneyCount} 条</span></a>
						</th>
					</tr>
				</table>
			</c:if>
			<c:if test="${!offlineMoney&&cashTransOut}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_02 mar_b_30">
					<tr>
						<th width="50%">
							<a id="cashTransOut" href="./cashTransOutManage?cashstaus=1" class="link_cblue uline mar_l_30"><span>待审核的出金申请 ${cashTransOutCount} 条</span></a>
						</th>
						<th>&nbsp;</th>
					</tr>
				</table>
			</c:if>
			<c:if test="${offlineMoney&&!cashTransOut}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_02 mar_b_30">
					<tr>
						<th>&nbsp;</th>
						<th width="50%">
							<a id="offlineMoney" href="./offlineMoneyManage?cashstaus=1" class="link_cblue uline mar_l_30"><span>待审核的线下入金申请  ${offlineMoneyCount} 条</span></a>
						</th>
					</tr>
				</table>
			</c:if>
			<c:if test="${forcePay}">
				<div class="clear"></div>
				<div class="rightTitle_01"><h2>建行强制付款通知</h2></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb_02 mar_b_30">
					<tr>
						<th width="50%">
							<a id="forcePay" href="./forcePayManage?payStatus=3" class="link_cblue uline mar_l_30"><span>强制付款通知  ${forcePayCount} 条</span></a>
						</th>
						<th>&nbsp;</th>
					</tr>
				</table>
			</c:if>
		</div> --%>
<%--  	</tiles:put>
</tiles:insert>
 --%>
