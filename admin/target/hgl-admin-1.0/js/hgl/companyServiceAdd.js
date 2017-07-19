$(function(){
		  new uploadPreview({ UpBtn: "pImgOne_Up", DivShow: "pImgOnedivUp", ImgShow: "pImgOneShowUp" });
		  new uploadPreview({ UpBtn: "pImgTwo_Up", DivShow: "pImgTwodivUp", ImgShow: "pImgTwoShowUp" });
		  new uploadPreview({ UpBtn: "pImgThree_Up", DivShow: "pImgThreedivUp", ImgShow: "pImgThreeShowUp" });
		});
	 $(function(){
			$(".pUpdate").fileupload({
				url : ctx+'/companyType/validateImg',
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
			        	  new uploadPreview({ UpBtn: "pImgOne_Up", DivShow: "pImgOnedivUp", ImgShow: "pImgOneShowUp" });
				   		  new uploadPreview({ UpBtn: "pImgTwo_Up", DivShow: "pImgTwodivUp", ImgShow: "pImgTwoShowUp" });
				   		  new uploadPreview({ UpBtn: "pImgThree_Up", DivShow: "pImgThreedivUp", ImgShow: "pImgThreeShowUp" });
				   		  
			            if (data.result.code == '0'){
			            	var imgName = data.result.imgNo.split("_");
			            	$("#"+imgName[0]+"_Up_Show").val(data.result.imgNo);
			            }else{
			            
			            }
			}).on('fileuploadsubmit', function (e, data) {
		        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
		    });
		});
	 
function updateSuppliers(){
	var addType = validateForm("updateSuppliers");
	if(!addType){
		return;
	}
	var street= $("#street").val();
	if(isNull(street) ||street <=0 ){
		$("#street").addClass("onerrInput");
		return;
	}
	$("#updateSuppliers").submit();
}
function addCompanyService(){
	var addType = validateForm("addCompany");
	var pimgOne = $("#pImgOne_Up_Show").val();
	if(!addType){
		return;
	}
	
	var street= $("#street").val();
	if(isNull(street) ||street <=0 ){
		$("#street").addClass("onerrInput");
		return;
	}
	 if(isNull(pimgOne)){
		 $("#removeCl").addClass("onerrInput");
		 return;
	 }else{
		 $("#removeCl").removeClass("onerrInput");
	 }
	$("#addCompany").submit();
}