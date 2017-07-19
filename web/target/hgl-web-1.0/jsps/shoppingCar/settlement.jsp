<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="cardPage">
	<tiles:put name="title" value="结算" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>         
	<meta charset="UTF-8">
	<title>结算</title>
	<link rel="stylesheet" href="${ctx}/css/cart.css">
	<script src="${ctx}/js/hgl/settlement.js"></script>
	<script src="${ctx}/js/hgl/publicDecimal.js"></script>
	<script src="${ctx}/js/hgl/district.js"></script>
</head>
<body>

 <input id="couponRule" type="hidden" value="${couponRule}" />
		<!-- 选择商品开始 -->
		<div class="chart clear">
			<div class="chart-top verify"></div>
			<span>选择商品</span>
			<span class="active">核对信息</span>
			<span>付款</span>
			<span>收货</span>
		</div>
		<div class="verify-addr verify-top">
			<h3>请选择收货地址</h3>
			<div class="addr-lists clear" id="myAddress">
				<%@include file="addressList.jsp"  %>
			</div>		
		</div>
		<div class="verify-pay verify-top">
			<h3>支付方式</h3>
			<div class="bg-gray clear" id="payDiv">
				<div class="pull-left">
					<input type="radio" id="pay1" name="pay" checked="true" value="240">
					<label for="pay1">线上支付</label>
				</div>
				<div class="pull-left">
					<input type="radio" id="pay2" name="pay" value="242">
					<label for="pay2">线下支付</label>
				</div>
			</div>
		</div>
		<div class="verify-table verify-top">
			<h3>确定订单信息</h3>
			<div class="chart-table">
				<%@include file="settlementList.jsp"  %>
			</div>
		<div class="verify-total text-right">
			已选商品合计 : &nbsp;&nbsp;<strong class="text-red" id="totalCount">0</strong>&nbsp;&nbsp; 件 , 总金额 : ￥ <strong class="text-red" id="totalMoney">0.00</strong>&nbsp;&nbsp; 元
		</div>
		<div class="verify-pri verify-top" id="couponRemainingAmtHide">
			<h3>优惠</h3>
			<div class="pri" >
				<select>
					<option value="代金券">代金券</option>
				</select>
				当前剩余 &nbsp;&nbsp;<span id="couponRemainingAmt" name="${couponRemainingAmt}" >${couponRemainingAmt}</span>&nbsp;&nbsp; ,当前订单最多使用 &nbsp;&nbsp;
				 <span id="couponRemainingMoney" >0.00</span>。&nbsp;&nbsp; 
				使用 <input type="checkbox" id="couponsPrice" value="0">&nbsp;&nbsp; 
			</div>
		</div>	
		<div class="verify-present verify-top text-right">
			<p>需支付 : &nbsp;&nbsp;￥ <strong id="totalAllMoney">0.00</strong>&nbsp;元</p>
			<p>
				<a href="#" id="submitOrder">提交订单</a>
			</p>
		</div>
		<!-- 选择商品结束 -->
	</div>
</div>
<!-- 弹出层 -->
<div id="dialog" class="dialog" title="收货地址">
	<form id="addressForm" action="" method="post">
		<div class="form-col">
			<input type="hidden" name="hiddenCheck"></input>
			<input type="hidden" id="id" name="id"></input>
			<input type="hidden" id="provinceName" name="provinceName"></input>
			<input type="hidden" name="extensionField" id="extensionField"></input>
			<input type="hidden" id="provinceCode"></input>
			<input type="hidden" id="cityCode"></input>
			<input type="hidden" id="countryCode"></input>
			<input type="hidden" id="streetCode"></input>
			<span class="text-red">*</span>
			<label>收货人</label>
			<input type="text" title='收货人' id="recipient" name="recipient"></input>
			<span class="error text-red" id="recipientError"></span>	
		</div>
		<div class="form-col">
			<span class="text-red">*</span>
			<label>所在地区</label>
			<select id="province" name="province" onchange="changeGrade();" style="width:148px;">
				 <option value="0">请选择</option>
                 <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
                	 <option value="${item.id}">${item.name}</option>
                 </c:forEach>
			</select>
			<select id="city" name="city" onchange="changeCity();"  style="width:148px;">
			</select>
		</div>
		
		<div class="form-col">
			<label style="visibility:hidden;">所在地区</label>
			<select id="country" name="area" onchange="changeCountry();"  style="width:148px;">
			</select>
			<select id="street" name="street" style="width:148px;">
			</select>
			<span class="error text-red" id="countryError"></span>
		</div>
		
		<div class="form-col">
			<span class="text-red">*</span>
			<label>具体地址</label>
			<textarea rows="4" title="街道地址" id="streetDetail" name="streetDetail"></textarea>
			<span class="error text-red" id="streetError"></span>	
		</div>
		<div class="form-col">
			<span class="text-red">*</span>
			<label>手机/电话</label><input title="手机号码" type="text" id="phone" name="phone"></input>
			<span class="error text-red" id="phoneError"></span>
		</div>
		<div class="form-col">
			<label>邮编</label><input title="邮编" type="text" id="code" name="code"></input>
		</div>
		<div class="form-col">
			<label>固定电话</label><input title="固定电话" type="text" id="telephone" name="telephone"></input>
			<span class="error text-red" id="telephoneError"></span>
		</div>
	</form>
</div>
<div id="dialog1" title="提示">您确定要删除该地址吗？</div> 
<div id="dialog3" title="提示">默认地址不能删除</div> 
<div id="dialog4" title="提示">请选择收货地址</div> 
<div id="dialog5" title="提示">订单已提交,请去我的订单查看</div> 
<form id="submitOrderForm" method="post" action="../myOrderGroup/submitOrder">
	<input type="hidden" value="" id="cartIdList" name="cartIdList"/>
	<input type="hidden" value="" id="addressId" name="addressId"/>
	<input type="hidden" value="" id="payType" name="payType"/>
	<input type="text" value="" id="couponsMoney" name="couponsMoney"/>
	<input type="hidden" value="${cartIds}" id="cartIds" name="cartIds"/>
	<input type="hidden" value="0" id="isSubmit"/>
</form>
</body>
</html>
</tiles:put>
</tiles:insert>