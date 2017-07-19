<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="客户盈利" />
<tiles:put name="body" type="String">
<c:set value="takl" var="modalName"></c:set>	
<html>
	<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
	<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
	<link rel="stylesheet" href="${ctx}/css/me.css">
<body>
	<c:set value="inventory" var="modalName"></c:set>
	<div class="area me">
		<div class="main-right pull-right">
			<div class="stock-edit stock">
				<div id="key_${modalName}_list" class="col-sm-12">
					<%@include file="customerInterestsDetailList.jsp"%>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>
<script>
EcWeb.currentModalName = '${modalName}';
</script>
</tiles:put>
</tiles:insert>