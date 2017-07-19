/**
 * 定义判断是否存在变量
 */
var isExist = false;

/**
 * 点击添加跳转添加页面
 */
function goAddPage(){
	 window.location.href="../jsps/systemConfig/addSystemConfigPage.jsp";
}

/**
 * 添加页面点击保存
 */
function insertSystemConfig(){
	 window.location.href="insertSystemConfig";
}

/**
 * 添加页面点击取消
 */
function goSystemConfigIndex(){
	 window.location.href="../../systemConfig/index";
}

/**
 * 修改页面点击取消
 */
function goUpdateSystemConfigIndex(){
	 window.location.href="../systemConfig/index";
}

/**
 * 点击修改跳转修改页面
 * @param id
 */
function goUpdatePage(id){
	window.location.href="../systemConfig/getUpdateSystemConfigPage?id="+id;
}

/**
 * 点击修改提交
 * @returns {Boolean}
 */
function saveUpdateSystemConfig(){
	if($("#configValue").val() == ''){
	   $("#configValue").addClass("onerrInput");
	   return false;
	}
	
	$("#saveUpdateSystemConfig").submit();
}

/**
 * 添加页面配置名称鼠标离开事件提示是否存在
 * @returns {Boolean}
 */
function subAddSystemConfig(){
	if($("#configKey").val() == ''){
	   $("#configKey").addClass("onerrInput");
	}
	if($("#configValue").val() == ''){
	   $("#configValue").addClass("onerrInput");
	}
	if($("#configKey").val()=='' || $("#configValue").val() == ''){
		return false;
	}
	addValidateSystemConfigInfo();
	if(!isExist){
    	return false;
    }
	$("#saveSystemConfigPage").submit();
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
 * 配置名称校验,不为空,不能重复,不能输入中文
 * @returns {Boolean}
 */
function addValidateSystemConfigInfo(){
	var configKey = $("#configKey").val();
	if(isNull(configKey)){ //校验不为空
		return false;
	}
    if(/.*[\u4e00-\u9fa5]+.*$/.test(configKey)){ //校验不能输入中文
    	$("#configKey").addClass("onerrInput");
    	$("#configKeyReVaildate").text("该配置名称不能输入中文！");
    	return false;
    }
    checkIsExist();
}

/**
 * 检查key是否重复
 */
function checkIsExist(){
	var configKey = $("#configKey").val();
	$.ajax({
        type: "POST",
        url: "../../systemConfig/validateSystemConfigIsExist",
        data: "configKey="+configKey+"&random="+Math.random(),
        success: function(response){
            if(response.isExit == '1'){
            	$("#configKey").removeClass("onerrInput");
            	$("#configKeyReVaildate").text("");
            	isExist = true;
            }else{
            	$("#configKey").addClass("onerrInput");
            	$("#configKeyReVaildate").text("该配置名称已经存在！");
            	isExist = false;
            }
        }
    });
}

/**
 * 删除调用弹框提示
 * @param id
 */
function delectSystemConfigValidate(id){
	$("#systemConfigId").val(id);
	deleteSystemConfigShow();
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
function deleteSystemConfigShow(){
	$("#deleteSystemConfig").modal("show");
}

/**
 * 调用后台删除
 */
function goDeleteSystemConfig(){
	var id = $("#systemConfigId").val();
	window.location.href="deleteSystemConfigById?id="+id;
}

