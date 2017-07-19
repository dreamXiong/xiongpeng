$(function(){
		  new uploadPreview({ UpBtn: "pImgOne_Up", DivShow: "pImgOnedivUp", ImgShow: "pImgOneShowUp" });
		  new uploadPreview({ UpBtn: "pImgTwo_Up", DivShow: "pImgTwodivUp", ImgShow: "pImgTwoShowUp" });
		  new uploadPreview({ UpBtn: "pImgThree_Up", DivShow: "pImgThreedivUp", ImgShow: "pImgThreeShowUp" });
		  
		  new uploadPreview({ UpBtn: "dImgOne_Up", DivShow: "dImgOnedivUp", ImgShow: "dImgOneShowUp"});
    	  new uploadPreview({ UpBtn: "dImgTwo_Up", DivShow: "dImgTwodivUp", ImgShow: "dImgTwoShowUp"});
    	  new uploadPreview({ UpBtn: "dImgThree_Up", DivShow: "dImgThreedivUp", ImgShow: "dImgThreeShowUp"});
		});
	 $(function(){
			$(".pUpdate").fileupload({
				  url : ctx+'/register/validateImg',
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
				   		  
				   		  new uploadPreview({ UpBtn: "dImgOne_Up", DivShow: "dImgOnedivUp", ImgShow: "dImgOneShowUp"});
				       	  new uploadPreview({ UpBtn: "dImgTwo_Up", DivShow: "dImgTwodivUp", ImgShow: "dImgTwoShowUp"});
				       	  new uploadPreview({ UpBtn: "dImgThree_Up", DivShow: "dImgThreedivUp", ImgShow: "dImgThreeShowUp"});
			            if (data.result.code == '0'){
			            	var imgName = data.result.imgNo.split("_");
			            	$("#"+imgName[0]+"_Up_Show").val(data.result.imgNo);
			            }else{
			            
			            }
			}).on('fileuploadsubmit', function (e, data) {
		        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
		    });
		});
	 
function shopEdit(){
	
	var shopName= $("#shopName").val();
	if(isNull(shopName)){
		$("#shopName").addClass("onerrInput");
		return;
	}
	var scope= $("#scope").val();
	if(isNull(scope) ){
		$("#scope").addClass("onerrInput");
		return;
	}
	
	var street= $("#street").val();
	if(isNull(street) ||street <=0 ){
		$("#street").addClass("onerrInput");
		return;
	}
/*	if(updateValidateInfo == false){
		$("#nameErrorRm").addClass("onerrInput");
		return;
	}
		
	if(!updateValidateBrand){
		return;
	}*/
	var addType = validateForm("shopEdit");
	if(!addType){
		return;
	}
	$("#shopEdit").submit();
}


