 $(function(){
		  new uploadPreview({ UpBtn: "pImgOne", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
		  new uploadPreview({ UpBtn: "pImgTwo", DivShow: "pImgTwodiv", ImgShow: "pImgTwoShow" });
		  new uploadPreview({ UpBtn: "pImgThree", DivShow: "pImgThreediv", ImgShow: "pImgThreeShow" });
		});
	 $(function(){  
			$(".pImg").fileupload({
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
			        	new uploadPreview({ UpBtn: "pImgOne", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
			      		new uploadPreview({ UpBtn: "pImgTwo", DivShow: "pImgTwodiv", ImgShow: "pImgTwoShow" });
			      		new uploadPreview({ UpBtn: "pImgThree", DivShow: "pImgThreediv", ImgShow: "pImgThreeShow"});
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
	/*点击大分类时，去查找二级分类*/
	function selectParent(){
		 var maindId = $("#mainIdForAddPage option:selected").val();
		 $.ajax({
		        type:"POST",
		        url :"../customerService/linkageMainPage",
		        data:{
		            id:maindId
		        },
		        dataType:"json",
		        success: function(response){
		        	$("#parentType").children("option").remove();
		             $("#parentType").append( "<option value='0'>--请选择--</option>");
		        	if(response.secondList != null){
		        		var tLen = response.secondList.length;
	 	           		for(var i=0; i<tLen; i++){
	 		           		$("#parentType").append( "<option value='"+response.secondList[i].id+"'>"+response.secondList[i].name+"</option>");
	 	           		}
		        	}
	 	        }      
		 });
	}