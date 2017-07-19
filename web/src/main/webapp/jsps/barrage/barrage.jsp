
<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="账户信息" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	
	<html>
		<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /></head>
		<body>
			<form id="key_${modalName}_qryFrm">
				<input name="nextPageNo" value="2">
			</form>
			<div id="key_${modalName}_list">
					<%@include file="talkList.jsp" %>
			</div>
		</body>
		<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
		</script>
	</html>
	</tiles:put>
</tiles:insert>