<%@page pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${empty wapAddress.extensionField}">
		<p>添加/选择地址</p>
	</c:when>
	<c:otherwise>
		<p>收货地址:<span>${wapAddress.extensionField }</span></p>
	</c:otherwise>
</c:choose>
