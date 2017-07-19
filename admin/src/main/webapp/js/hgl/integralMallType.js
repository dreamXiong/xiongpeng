/**
 * 点击修改跳转修改页面
 * @param id
 */
function goUpdatePage(id){
	window.location.href="../integralMallType/getUpdateIntegralMallTypePage?id="+id;
}

/**
 * 点击修改提交
 * @returns {Boolean}
 */
function updateIntegralMallType(){
	if(checkNull()){             
		$("#updateIntegralMallType").submit();	
	}
}

/**
 * 添加
 * @returns {Boolean}
 */
function subAddIntegralMallType(){
	if(checkNull()){
		$("#saveIntegralMallType").submit();	
	}
}

/**
 * 校验是否为空
 * @returns {Boolean}
 */
function checkNull(){
	var checkFlag = false;
	if(isNull($("#goodsTypeName").val())){         
	   $("#goodsTypeName").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#goodsTypeName").removeClass("onerrInput");
	}
	if(isNull($("#input1").val())){                     
	   $("#input1").addClass("onerrInput");
	   checkFlag = true;
	}else{
	   $("#input1").removeClass("onerrInput");
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
	$("#integralMallTypeId").val(id);
	$("#status").val(status);
	integralMallStatusTypeShow();  
}

/**
 * 显示提示框
 */
function integralMallStatusTypeShow(){
	$("#integralMallTypeStatus").modal("show");
}

/**
 * 调用后台修改状态
 */
function goStatusIntegralMallType(){
	var id = $("#integralMallTypeId").val();
	var status = $("#status").val();
	window.location.href="modifyStatusIntegralMallTypeById?id="+id+"&status="+status;
}
