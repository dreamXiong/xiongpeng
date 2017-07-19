<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="我的地址" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/> 
	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/hgl/myAddress.js"></script>    
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="container verify">
<c:if test="${empty addressList }">
	<div class="container">
		<img src="${ctx}/images/null2.png" alt="">
	</div>
</c:if>
	<ul class="address-list"> 
		<c:forEach var="item" items="${addressList}" varStatus="i">
			<li>	
				<div class="address-input" id="checkedDiv">
					<c:if test="${item.checkFlag == 1}">
						<input type="radio"  name="defaultAddressChecked" onclick="setDefaultAddress(${item.id })">
					</c:if>
					<c:if test="${item.checkFlag == 0}">
						<input type="hidden" id="hidDefaultId" value="${item.id }">
						<input type="radio"  name="defaultAddressChecked" checked="checked">
					</c:if>
				</div>
				<div class="address-list-top">
					<span>${item.recipient }</span>
					<span>${item.phone }</span>
					<c:if test="${item.checkFlag == 1}">
						<button type="button" class="remove" onclick="deleteAddress(${item.id })">删除</button>
					</c:if>
				</div>
				<div class="address-list-button">
					<a href="#" onclick="modifyAddress(${item.id })">
						<c:if test="${item.checkFlag == 0}">
							<em style="color:#f2000e;">[默认]</em>
						</c:if>
							${item.extensionField }
						<span></span>
					</a>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
<footer class="reap-site">
	<a href="addLocationAddressInit" class="add-addr">新增新地址</a>
</footer>

<form action="${ctx}/myAddress/modifyLocationAddressInit" method="post" id="modifyForm">
	<input type="hidden" name="id" id="modifyId" />
</form>
<form action="${ctx}/myAddress/defaultAddress" method="post" id="setDefaultForm">
	<input type="hidden" name="id" id="defaultId" />
	<input type="hidden" name="newId" id="defaultNewId" />
</form> 
<form action="${ctx}/myAddress/deleteAddress" method="post" id="deleteForm">
	<input type="hidden" name="id" id="deleteId" />
</form>      
</body>
</html>