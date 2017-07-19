/**
 * 产品详情
 * */
function showdetailsInfo(id){
	 $.ajax({
	        type:"POST",
	        url :"showdetailsInfo",
	        data:{
	        	id:id,
	        },
	        success: function(response){
	        	$("#showdetailsInfo").html(response);
	        }
	 	});
	$("#showdetailsInfo").modal("show");
}  
/**
 * 产品
 * */
function showUpdate(id){
	 $.ajax({
	        type:"POST",
	        url :"showUpdateInfo",
	        data:{
	        	id:id,
	        },
	        success: function(response){
	        	$("#updateProduct").html(response);
	        }
	 	});
	$("#updateProduct").modal("show");
}  
function showAdd(){
	$("#addProduct").modal("show");
}

function delCustomerService(){
	 var id =$("#deleteId").val();
	 $.ajax({
		    type: "POST",
		    url: "delCustomerService",
		    data: "id="+id,
		    success: function(response){
		    	window.location.reload();
		    }
	});
}
function delServiceInfoVal(id){
	 $("#deleteId").val(id);
	 $.ajax({
		    type: "POST",
		    url: "delServiceInfoValidate",
		    data: "id="+id,
		    success: function(response){
		    	if(response.code == '00000'){
		    		$("#deleteProduct").modal("show");
		        }else{
		        	$("#notDeleteProduct").modal("show");
		     }
		    }
		});
}
$(function(){
	  new uploadPreview({ UpBtn: "pImgOne", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
	});
$(function(){  
		$(".pImg").fileupload({
		    url : "../companyType/validateImg",
		    autoUpload : true,
		    singleFileUploads : true,
		    acceptFileTypes : /(\.|\/)(|gif|jpe?g|png|bmp)$/i,
		    maxFileSize : 5097152,
		    change: function (e, data) { 
		        $.each(data.files, function (index, file) {
		        });
		    }
		}).on(
		        'fileuploaddone',
		        function(e, data) {
		        	new uploadPreview({ UpBtn: "pImgOne", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
		            if (data.result.code == '0'){
		            
		            	var imgName = data.result.imgNo.split("_");
		            	$("#"+imgName[0]+"_val").val(data.result.imgNo);
		            }else{
		            
		            }
		}).on('fileuploadsubmit', function (e, data) {
	        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
	    });
	}); 

$("#removeCl").bind("click", function(){
		$("#removeCl").removeClass("onerrInput");
	});

function submitAddServiceInfo(){
	 var pimgOne = $("#pImgOne_val").val();
	 var addType = validateForm("saveServiceInfo");
	 var parentType = $("#parentType").val();
	 if(parentType == 0 ){
		 $("#parentType").addClass("onerrInput");
	 }
	 if(!addValidate){
		$('#addName').toggleClass('onerrInput'); 
		return;
		}
	 if(isNull(pimgOne)){
		 $("#removeCl").addClass("onerrInput");
	 }
	 if(isNull(pimgOne)){
		 return;
	 }
	 if(!addType || parentType == 0){
		 return;
	 } 
	 $("#saveServiceInfo").submit();
}
function onCKeyupNumber(domEle) {
		var minfos = $("#"+domEle).val();
		
		var state = domEle.value = minfos.replace(/[^-\d]/g, '');//兼容负整数
		$("#"+domEle).val(state);
	}
$('#saveProductInfo :input').blur(function(){
	 $(this).removeClass('onerrInput');
});

$('#addProduct').on('hidden.bs.modal', function (e) {
	window.location.reload();
	});
