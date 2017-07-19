<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>新增收货地址</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">

	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/hgl/district.js"></script>   
	<script src="${pageContext.request.contextPath}/js/common/common.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/jquery.linkon.web.js"></script> 
	<script src="${pageContext.request.contextPath}/js/common/publicCheckFormat.js"></script> 
	<script src="${pageContext.request.contextPath}/js/hgl/myAddress.js"></script>    
</head>
<body>
	
<header class="cart verify-head">
	<span class="icon-angle-left text-center"></span>
	<h3 class="text-center">新增收货地址</h3>
</header>

<div class="container verify">
	<form action="addAddress" class="form" method="post" id="addForm">
		<div>
			<label>收货人</label>
			<input type="text" id="recipient" name="recipient">
			<input type="hidden" id="provinceName" name="provinceName">
			<input type="hidden" id="extensionField" name="extensionField">
		</div>
		<div>
			<label>手机号码</label>
			<input type="text" id="phone" name="phone">
		</div>
		<h3>收货地址</h3>
		<div>
			<label>省份</label>
			<select id="province" name="province" onchange="changeGrade();">
			<option value="0">请选择</option>
                <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
               	 <option value="${item.id}">${item.name}</option>
                </c:forEach>
			</select>
		</div>
		<div>
			<label>城市</label>
			<select id="city" name="city" onchange="changeCity();" >
			</select>
		</div>
		<div>
			<label>区县</label>
			<select id="country" name="area" onchange="changeCountry();">
			</select>
		</div>
		<div>
			<label>乡镇</label>
			<select id="street" name="street">
			</select>
		</div>
		<div>
			<label>街道</label>
			<textarea rows="5" id="streetDetail" name="streetDetail"></textarea>
		</div>
		<div>
			<a href="#" class="add-addr" onclick="submitAddress('addForm')">保存地址</a>
		</div>
	</form>
</div>
</body>
</html>