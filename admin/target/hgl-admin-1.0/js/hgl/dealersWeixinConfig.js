/**
 * 定义判断是否存在变量
 */
var isExist = false;

/**
 * 添加页面点击取消
 */
function goDealersWeixinConfigIndex(){   
	 window.location.href="../dealersWeixinConfig/index";
}

/**
 * 点击修改跳转修改页面
 * @param id
 */
function goUpdatePage(id){
	window.location.href="../dealersWeixinConfig/getUpdateDealersWeixinConfigPage?id="+id;
}

/**
 * 点击修改提交
 * @returns {Boolean}
 */
function updateSystemConfig(){
	if(checkNull()){             
		$("#updateDealersWeixinConfigPage").submit();	
	}
}

/**
 * 添加页面配置名称鼠标离开事件提示是否存在
 * @returns {Boolean}
 */
function subAddDealersWeixinConfig(){
	if(checkNull()){
		checkIsExist("saveDealersWeixinConfigPage");
	}
}

//校验是否为空
function checkNull(){
	if($("#shopId").val() == ''){
	   $("#shopId").addClass("onerrInput");
	}else{
	   $("#shopId").removeClass("onerrInput");
	}
	if($("#appId").val() == ''){
	   $("#appId").addClass("onerrInput");
	}else{
	   $("#appId").removeClass("onerrInput");
	}
	if($("#mchId").val() == ''){
	   $("#mchId").addClass("onerrInput");
	}else{
	   $("#mchId").removeClass("onerrInput");
	}
	if($("#appKey").val() == ''){
	   $("#appKey").addClass("onerrInput");
	}else{
	   $("#appKey").removeClass("onerrInput");
	}
	if($("#notifyUrl").val() == ''){
	   $("#notifyUrl").addClass("onerrInput");     
	}else{
	   $("#notifyUrl").removeClass("onerrInput");
	}
	if($("#shopId").val()=='' || $("#appId").val()=='' || $("#mchId").val() == '' || $("#appKey").val() == '' || $("#notifyUrl").val() == ''){
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
 * 检查key是否重复
 */
function checkIsExist(formId){
	var shopId = $("#shopId").val();
	$.ajax({
        type: "POST",
        url: "../dealersWeixinConfig/validateShopIdIsExist",
        data: "shopId="+shopId+"&random="+Math.random(),
        success: function(response){
            if(response.isExit==true){
            	alert("该店铺已经配置过了");
            }else{
            	$("#"+formId).submit();	
            }
        }
    });
}

/**
 * 删除调用弹框提示
 * @param id
 */
function delectDealersWeixinConfigValidate(id){
	$("#dealersWeixinConfigId").val(id);
	deleteDealersWeixinConfigShow();  
}

/**
 * 不显示红色边框
 * @param id
 */
function deleteSystemConfigClass(id){
	$("#"+id).removeClass("onerrInput");
}

/**
 * 显示是否删除的框
 */
function deleteDealersWeixinConfigShow(){
	$("#dealersWeixinConfig").modal("show");
}

/**
 * 调用后台删除
 */
function goDeleteDealersWeixinConfig(){
	var id = $("#dealersWeixinConfigId").val();
	window.location.href="deleteDealersWeixinConfigById?id="+id;
}

function operationStatus(id,status){
	$("#dealersWeixinConfigId").val(id);
	$("#status").val(status);
	deleteDealersWeixinConfigStatusShow();  
}

function deleteDealersWeixinConfigStatusShow(){
	$("#dealersWeixinConfigStatus").modal("show");
}

function goStatusDealersWeixinConfig(){
	var id = $("#dealersWeixinConfigId").val();
	var status = $("#status").val();
	window.location.href="modifyStatusDealersWeixinConfigById?id="+id+"&status="+status;
}
