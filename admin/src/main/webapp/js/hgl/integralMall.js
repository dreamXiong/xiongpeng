/**
 * 点击修改跳转修改页面
 * @param id
 */
function goUpdatePage(id){
	window.location.href="../integralMall/getUpdateIntegralMallPage?id="+id;
}

/**
 * 点击修改提交
 * @returns {Boolean}
 */
function updateIntegralMall(){
	if(checkNull()){             
		$("#updateIntegralMall").submit();	
	}
}

/**
 * 添加
 * @returns {Boolean}
 */
function subAddIntegralMall(){
	if(checkNull()){
		$("#saveIntegralMall").submit();	
	}
}

/**
 * 校验是否为空
 * @returns {Boolean}
 */
function checkNull(){
	var checkFlag = false;
	if(isNull($("#integralMallTypeId").val())){         
	   $("#integralMallTypeId").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#integralMallTypeId").removeClass("onerrInput");
	}
	if(isNull($("#goodsName").val())){         
	   $("#goodsName").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#goodsName").removeClass("onerrInput");
	}
	if(isNull($("#integral").val())){         
	   $("#integral").addClass("onerrInput");
	   checkFlag = true;
	}else{
		if(parseInt($("#integral").val())!=$("#integral").val() || $("#integral").val()<=0){
			$("#integralError").text("积分必须为整数");
			checkFlag = true;
		}else{
			$("#integralError").text("");
			$("#integral").removeClass("onerrInput");
		}
	}
	if(isNull($("#payAmount").val())){         
	   $("#payAmount").addClass("onerrInput");
	   checkFlag = true;
	}else{
		if(isNaN($("#payAmount").val()) || $("#payAmount").val()<0){
			$("#payAmountError").text("支付金额输入不正确");
			checkFlag = true;
		}else{
			$("#payAmountError").text("");
			$("#payAmount").removeClass("onerrInput");
		}
	}
	if(isNull($("#marketAmount").val())){         
	   $("#marketAmount").addClass("onerrInput");
	   checkFlag = true;
	}else{
		if(isNaN($("#marketAmount").val()) || $("#marketAmount").val()<0){
			$("#marketAmountError").text("商品市场价输入不正确");
			checkFlag = true;
		}else{
			$("#marketAmountError").text("");
			$("#marketAmount").removeClass("onerrInput");
		}
	}
	if(isNull($("#input1").val())){                     
	   $("#input1").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#input1").removeClass("onerrInput");
	}
	if(isNull($("#goodsDescribe").val())){         
	   $("#goodsDescribe").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#goodsDescribe").removeClass("onerrInput");
	}
	if(checkFlag){
		return false;
	}
	return true;
}

/**
 * 判断是否是空
 * @param str
 * @returns
 */
function isNull(str) {
    if (str == "") return true;
    var regu = "^[ |\\n|\\r]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

/**
 * 判断是否不是空
 * @param str
 * @returns
 */
function isNotNull(str) {
    return !isNull(str);
}

/**
 * 修改启动/禁用状态
 * @param id
 * @param status
 */
function operationStatus(id,status){
	$("#integralMallId").val(id);
	$("#status").val(status);
	integralMallStatusShow();  
}

/**
 * 显示提示框
 */
function integralMallStatusShow(){
	$("#integralMallStatus").modal("show");
}

/**
 * 调用后台修改状态
 */
function goStatusIntegralMall(){
	var id = $("#integralMallId").val();
	var status = $("#status").val();
	window.location.href="modifyStatusIntegralMallById?id="+id+"&status="+status;
}
