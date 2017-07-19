<%@page pageEncoding="UTF-8"%>
<input type="hidden" id="weixinOpenUrl" value="${weixinOpenUrl }"/>
<c:choose>
	<c:when test="${not empty error}">
		${error}
	</c:when>
	<c:otherwise>
		<form id="loginForm" method="post" action="${success.actionUrl}">
			<input type="hidden" id="errorFlag" value="0" />
			<c:forEach var="item" items="${success.list}" varStatus="i">
				<input type="hidden" name="${item.key }" value="${item.value }" />
			</c:forEach>
		</form>
	</c:otherwise>
</c:choose>
