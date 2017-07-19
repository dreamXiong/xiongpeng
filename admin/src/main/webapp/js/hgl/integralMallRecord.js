/**
 * 修改发货状态
 * @param id
 */
function operationStatus(id){
	$("#integralMallRecordId").val(id);
	integralMallRecordShow();  
}

/**
 * 提示框,是否修改发货状态
 */
function integralMallRecordShow(){
	$("#integralMallRecordStatus").modal("show");
}

/**
 * 发送请求修改
 */
function goStatusIntegralMallRecord(){
	var id = $("#integralMallRecordId").val();
	window.location.href="modifyStatusIntegralMallRecordId?id="+id;
}
