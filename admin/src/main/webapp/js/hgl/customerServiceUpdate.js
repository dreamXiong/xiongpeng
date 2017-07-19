$(function(){
		  new uploadPreview({ UpBtn: "pImgOne_Up", DivShow: "pImgOnedivUp", ImgShow: "pImgOneShowUp" });
		  new uploadPreview({ UpBtn: "pImgTwo_Up", DivShow: "pImgTwodivUp", ImgShow: "pImgTwoShowUp" });
		  new uploadPreview({ UpBtn: "pImgThree_Up", DivShow: "pImgThreedivUp", ImgShow: "pImgThreeShowUp" });
		});
	 $(function(){
			$(".pUpdate").fileupload({
			    url : "../customerService/validateImg",
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
	 function subUpdateServiceInfo(){
		 var addType = validateForm("updateProductInfo");
		 if(!addType){
			 return;
		 } 
		$("#updateProductInfo").submit();
	 }
	 function onCKeyupNumber(domEle) {
			var minfos = $("#"+domEle).val();
			var state = domEle.value = minfos.replace(/[^-\d]/g, '');//兼容负整数
			$("#"+domEle).val(state);
		}
	 $('#saveProductInfo :input').blur(function(){
		 $(this).removeClass('onerrInput');
	 });
	$('#updateProduct').on('hidden.bs.modal', function (e) {
		window.location.reload();
		});