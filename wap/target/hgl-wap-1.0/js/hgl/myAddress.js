$(function(){                  
//	var provinceCode = $("#provinceCode").val();
//	if(provinceCode != undefined){
//		$("#province").val(provinceCode);              
//		gradeChange();               
//	}
	var defaultAddressChecked = $("#defaultEm");
	//var defaultAddressChecked = $("#checkedDiv input[name='defaultAddressChecked']:checked");
	var li = defaultAddressChecked.parents('li');
	$(".address-list").prepend(li);
});

//修改地址
function modifyAddress(id){
	$("#modifyForm #modifyId").val(id);
	$("#modifyForm").submit();
}

//设置为默认地址 
function setDefaultAddress(id){    
	$("#setDefaultForm #defaultId").val($("#hidDefaultId").val()); 
	$("#setDefaultForm #defaultNewId").val(id);
	$("#setDefaultForm").submit();
}

//删除地址
function deleteAddress(id){ 
	if(id != $("#defaultSpan").text()){  //判断是否是默认地址
		layer.open({
			 //title: '提示',  
			 content:'您确定要删除地址吗？',
			 btn:['确定','取消'],      
			 yes:function(){
				 $("#deleteForm #deleteId").val(id);
				 $("#deleteForm").submit();
			 },
			 no: function(){
				 layer.close();
			 }
		});
	}else{
		layer.open({
	 		content: '默认地址不能删除',
	 		time: 2
		}); 
	}
}

//点击新增或修改提交方法
/*function submitAddress(formId){
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
	 		content: '街道不能为空',
	 		time: 2
		}); 
		return;
	}else{
		if(street.length>30){
			layer.open({
		 		content: '街道地址过长',
		 		time: 2
			}); 
			return;
		}
	}
//	var province = $("#province option:selected").text();         
//	var city = $("#city option:selected").text();
//	var county = $("#country option:selected").text();
//	var street = $("#"+streets).val();
//	county = county.replace(/\s+/g,"");
//	var addre = province+city+county+street;
//	$("#provinceName").val(province);
//	$("#extensionField").val(addre);
	$("#"+formId).submit();
}*/