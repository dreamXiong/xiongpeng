var ImgInfoMsg = false;
/*去添加页面*/
function goAddPage(){
	 window.location.href="addBrandPage";
}
/*品牌添加*/
function addBrand(){
	 window.location.href="addBrand";
}
/*反回主页面*/ 
function goBrandIndex(){
	 window.location.href="../brand/index";
}
function goUpdatePage(id){
	window.location.href="../brand/goUpdateBrandPage?id="+id;
}

function onCKeyupNumber(domEle) {
	var minfos = $("#"+domEle).val();
	var state = domEle.value = minfos.replace(/[^-\d]/g, '');//兼容负整数
	$("#"+domEle).val(state);
}
$(function() {
  $('#input2').change(function(event) {
    $('#input1').val($(this).val());
  });
});

function deleteBrandClass(id){
	$("#"+id).removeClass("onerrInput");
}
/*修改提交*/
function saveUpdateBrand(){
	/*updateValidateBrandInfo();*/
	var addType = validateForm("saveUpdateBrand");
	if(!addType){
		return;
	}
	if(updateValidateInfo == false){
		$("#nameErrorRm").addClass("onerrInput");
		return;
	}
		
	if(!updateValidateBrand){
		return;
	}
	$("#saveUpdateBrand").submit();
}

/*添加品牌提交*/
function subAddBrand(){
	var addType = validateForm("saveBrandPage");
	if(ImgInfoMsg == false){
		$("#imgErrorDiv").addClass("onerrInput");
	}
	if(!ImgInfoMsg || !addType){ 
		return;
	}
	addValidateBrandInfo();
	 if(!addValidateBrand){
		 return;
	 }
	$("#saveBrandPage").submit();
}
function uploadSucced(){
	$("#firstUploadSucceeMsg").html(msg);
	$("#firstUploadSucced").show();
	$("#firstUploadFailed").hide();
}

function uploadFailed(){
	$("#firstUploadFailedMsg").html(msg);
	$("#firstUploadSucced").hide();
	$("#firstUploadFailed").show();
}
function isNull(str) {
    if (str == "") return true;
    var regu = "^[ |\\n|\\r]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
function isNotNull(str) {
    return !isNull(str);
}
/*添加时离焦校验*/
var addValidateBrand=false;
function addValidateBrandInfo(){
	/*品牌名称*/
	var name = $("#nameErrorRm").val();
	/*分类ID*/
	var producttypeId = $("#producttypeId").val();
	
	if(isNull(name) || isNull(producttypeId)){
		return;
	}
	$.ajax({
        type: "POST",
        url: "../brand/addValidateBrand",
        data: "name="+name+"&producttypeId="+producttypeId,
        success: function(response){
            if(response.code == '00000'){
            	$("#nameErrorRm").removeClass("onerrInput");
            	$("#nameReVaildate").text("");
            	addValidateBrand = true;
            }else{
            	$("#nameErrorRm").addClass("onerrInput");
            	$("#nameReVaildate").text("该分类下已存在该品牌！");
            	addValidateBrand = false;
            }
        }
    });
}
/*添加时离焦校验*/
var updateValidateBrand=true;
function updateValidateBrandInfo(){
	/*品牌名称*/
	var name = $("#nameErrorRm").val();
	/*分类ID*/
	var producttypeId = $("#producttypeId").val();
	/*品牌ID*/
	var id = $("#id").val();
	
	if(isNull(name) || isNull(producttypeId) || isNull(id)){
		return;
	}
	$.ajax({
        type: "POST",
        url: "../brand/updateValidateBrand",
        data: "name="+name+"&producttypeId="+producttypeId+"&id="+id,
        success: function(response){
            if(response.code == '00000'){
            	$("#nameErrorRm").removeClass("onerrInput");
            	$("#nameReVaildate").text("");
            	updateValidateBrand = true;
            }else{
            	$("#nameErrorRm").addClass("onerrInput");
            	$("#nameReVaildate").text("该分类下已存在该品牌！");
            	updateValidateBrand = false;
            }
        }
    });
}

function delectBrandValidate(id){
	$("#brandId").val(id);
	$.ajax({
        type: "POST",
        url: "delectBrandValidate",
        data: "id="+id,
        success: function(response){
            if(response.code == '00000'){
            	deleteBrandShow();
            }else{
            	$("#canNotDel").modal("show");
            }
        }
    });
}
function deleteBrandShow(){
	/*var id = $("#brandId").val(id);*/
	$("#deleteBrand").modal("show");
}


function goDeleteBrand(){
	
	var id = $("#brandId").val();
	window.location.href="deleteBrandById?id="+id;
}

