<%@page pageEncoding="UTF-8"%>
<header class="cart verify-head">
	<span class="icon-angle-left text-center back" onclick="backT(this);"></span>
	<h3 class="text-center">新增收货地址</h3>
</header>
<div class="compile-body">
	<form id="addAddressForm" action="" class="standard-form">
		<div class="form-row">
			<label for="">收货人姓名</label>
			<input type="hidden" name="ids" value="">
			<input type="text" id="recipient" name="recipient">
			<input type="hidden" id="provinceName" name="provinceName">
			<input type="hidden" id="extensionField" name="extensionField">
		</div>
		<div class="form-row">
			<label for="">手&nbsp;&nbsp; 机&nbsp;&nbsp; 号</label>
			<input type="text" id="phone" name="phone">
		</div>
		<div class="form-row">
			<label for="">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
			<select id="province" name="province" onchange="changeGrade();">
			<option value="0">请选择</option>
                <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
               	 <option value="${item.id}">${item.name}</option>
                </c:forEach>
			</select>
		</div>
		<div class="form-row">
			<label for="">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
			<select id="city" name="city" onchange="changeCity();" >
			</select>
		</div>
		<div class="form-row">
			<label for="">区&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;县</label>
			<select id="country" name="area" onchange="changeCountry();">
			</select>
		</div>
		<div class="form-row">
			<label for="">街道&nbsp;/&nbsp;乡镇</label>
			<select id="street" name="street">
			</select>
		</div>
		<div class="form-row">
			<label>详&nbsp;细&nbsp;地&nbsp;址</label>
			<input type="text" id="streetDetail" name="streetDetail">
		</div>
		<a href="#" class="save-addr" onclick="submitAddress()">保存新地址</a>
	</form>
</div>	
<script>
//点击新增或修改提交方法
function submitAddress(formId){
	var recipient = $("#recipient").val();
	var country = $("#country").val();
	var street = $("#streetDetail").val();
	var phone = $("#phone").val();
	if(recipient == ''){
	 	layer.open({
	 		content: '收货人不能为空',
	 		time: 2
		}); 
	 	return;
	}else{
		if(recipient.length>5){
			layer.open({
		 		content: '收货人名称过长',
		 		time: 2
			}); 
			return;
		}
	}
	if(phone == ''){
		layer.open({
	 		content: '手机号码不能为空',
	 		time: 2
		}); 
		return;
	}else{
		if(checkMobile(phone) || isTel(phone)){
		}else{
			layer.open({
		 		content: '手机号码格式不正确',
		 		time: 2
			}); 
			return;
		}
	}
	if(country == '0' || country == null){
		layer.open({
	 		content: '收货地址不能为空',
	 		time: 2
		}); 
		return;
	}
	if(street == ''){
		layer.open({
	 		content: '详细地址不能为空',
	 		time: 2
		}); 
		return;
	}else{
		if(street.length>30){
			layer.open({
		 		content: '详细地址过长',
		 		time: 2
			}); 
			return;
		}
	}
	var address = $("#addAddressForm").serialize();
	$.ajax({
        type: "POST",
        url: "addAddress",  
        data: "address="+address+"&addressId="+$("#mainAddressId").val()+"&random="+Math.random(),
        success: function(response){
        	$("#addressListDiv").html(response);
        	backT($("#addAddressForm"));
        },
        error: function() {
        	
        }   
    });
}
</script>