var ischeckBrandName = true;

  

function checkBrand(){
	var name=$("#name").val();
	var producttypeId = $("#producttypeId").val();
  	$.ajax({
  		type:"post",
  		url: ctx+"/register/checkBranName",
  		data:{"productTypeId":producttypeId,"brandName":name},
  		 success: function(response){
  	            if(response.errcode == '0'){
  	            	ischeckBrandName = false;
  	            	showTipInfo(2, $("#name")); 
  	            }else{
  	            	ischeckBrandName = true;
  	            	showTipInfo(0, $("#name"),response.msg); 
  	            	/*方法2
  	            	$("#key_info_namename").html("<p class='errMsgLabel'>"+response.msg+"</p>");
  	            	$("#key_info_namename").show();*/
  	            }
  	        }
  		
  	});
  }

function saveBrand(){
	var addType = validateForm("saveBrand");
	if(!addType){
		return;
	}
	
	if(ischeckBrandName){
		return ;
	}
	var url=$("#url").val();
	if(url!="" && !is_url(url)){
		showTipInfo(0, $("#url"),"请输入正确的网址"); 
	}
	var logoName = $("#logoName1_Path").val();
	if(logoName==""){
		showTipInfo(0, $("#logoName1"),"请上传品牌logo图"); 
		return;
	}
  var options=$("#producttypeId option:selected");
  $("#producttypeName").val(options.text());
	$("#saveBrand").submit();
}

function is_url(val){
	return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(val);
};

$(function(){
	  new uploadPreview({ UpBtn: "logoName1", DivShow: "pImgFourdiv", ImgShow: "pImgFourShow"});
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
		      		new uploadPreview({ UpBtn: "logoName1", DivShow: "pImgFourdiv", ImgShow: "pImgFourShow"});
		            if (data.result.code == '0'){
		            	var imgName = data.result.imgNo.split("_");
		            	$("#"+imgName[0]+"_Path").val(data.result.imgNo);
		            }else{
		            
		            }
		}).on('fileuploadsubmit', function (e, data) {
			
	        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
	    });
	}); 