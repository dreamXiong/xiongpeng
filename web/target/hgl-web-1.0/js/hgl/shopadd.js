 $(function(){
		  new uploadPreview({ UpBtn: "licenseImage1", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
		  new uploadPreview({ UpBtn: "taxpayerImage1", DivShow: "pImgTwodiv", ImgShow: "pImgTwoShow" });
		  new uploadPreview({ UpBtn: "organizationImage1", DivShow: "pImgThreediv", ImgShow: "pImgThreeShow" });
		  new uploadPreview({ UpBtn: "logoName1", DivShow: "pImgFourdiv", ImgShow: "pImgFourShow"});
		  new uploadPreview({ UpBtn: "shopImage1", DivShow: "dImgOnediv", ImgShow: "dImgOneShow"});
    	  new uploadPreview({ UpBtn: "shopImage2", DivShow: "dImgTwodiv", ImgShow: "dImgTwoShow"});
    	  new uploadPreview({ UpBtn: "shopImage3", DivShow: "dImgThreediv", ImgShow: "dImgThreeShow"});
		});
	$(function(){  
			$(".pImg").fileupload({
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
			        	new uploadPreview({ UpBtn: "licenseImage1", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
			      		new uploadPreview({ UpBtn: "taxpayerImage1", DivShow: "pImgTwodiv", ImgShow: "pImgTwoShow" });
			      		new uploadPreview({ UpBtn: "organizationImage1", DivShow: "pImgThreediv", ImgShow: "pImgThreeShow"});
			      		new uploadPreview({ UpBtn: "logoName1", DivShow: "pImgFourdiv", ImgShow: "pImgFourShow"});
			      		new uploadPreview({ UpBtn: "shopImage1", DivShow: "dImgOnediv", ImgShow: "dImgOneShow"});
			      		new uploadPreview({ UpBtn: "shopImage2", DivShow: "dImgTwodiv", ImgShow: "dImgTwoShow"});
			      		new uploadPreview({ UpBtn: "shopImage3", DivShow: "dImgThreediv", ImgShow: "dImgThreeShow"});
			      		
			            if (data.result.code == '0'){
			            	var imgName = data.result.imgNo.split("_");
			            	$("#"+imgName[0]+"_Path").val(data.result.imgNo);
			            }else{
			            
			            }
			}).on('fileuploadsubmit', function (e, data) {
				
		        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
		    });
		}); 
	
	 $("#removeCl").bind("click", function(){
			$("#removeCl").removeClass("onerrInput");
		});
	 
	

