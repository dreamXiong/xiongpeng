 $(function(){
		  new uploadPreview({ UpBtn: "pImgOne", DivShow: "pImgOnediv", ImgShow: "pImgOneShow" });
		  new uploadPreview({ UpBtn: "pImgTwo", DivShow: "pImgTwodiv", ImgShow: "pImgTwoShow" });
		  new uploadPreview({ UpBtn: "pImgThree", DivShow: "pImgThreediv", ImgShow: "pImgThreeShow" });
		  
		  new uploadPreview({ UpBtn: "dImgOne", DivShow: "dImgOnediv", ImgShow: "dImgOneShow"});
    	  new uploadPreview({ UpBtn: "dImgTwo", DivShow: "dImgTwodiv", ImgShow: "dImgTwoShow"});
    	  new uploadPreview({ UpBtn: "dImgThree", DivShow: "dImgThreediv", ImgShow: "dImgThreeShow"});
		});
	 $(function(){  
			$(".pImg").fileupload({
			    url : "../product/validateImg",
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
			      		
			      		new uploadPreview({ UpBtn: "dImgOne", DivShow: "dImgOnediv", ImgShow: "dImgOneShow"});
			      		new uploadPreview({ UpBtn: "dImgTwo", DivShow: "dImgTwodiv", ImgShow: "dImgTwoShow"});
			      		new uploadPreview({ UpBtn: "dImgThree", DivShow: "dImgThreediv", ImgShow: "dImgThreeShow"});
			      		
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
	 
	 function submitAddProductInfo(){
		
		 var pimgOne = $("#pImgOne_val").val();
		 var pimgtwo = $("#pImgTwo_val").val();
		 var pimgthree = $("#pImgThree_val").val();
         
		 var addType = validateForm("saveProductInfo");
		 /*onerrInput*/
		 var tb = $("#tbBrandList").val();
		 if(tb == 0){
			 $("#tbBrandList").addClass("onerrInput");
		 }
		 var parentType = $("#parentType").val();
		 var thirdType = $("#thirdType").val();
		 
		 if(parentType == 0 && thirdType == 0){
			 $("#parentType").addClass("onerrInput");
		 }
		 
		 if(isNull(pimgOne) && isNull(pimgtwo) && isNull(pimgthree)){
			 $("#removeCl").addClass("onerrInput");
		 }
		 if(isNull(pimgOne) && isNull(pimgtwo) && isNull(pimgthree)){
			 return;
		 }
		 if(!addType || tb == 0 || (parentType == 0 && thirdType == 0) ){
			 return;
		 } 
		
		 var attr = $(".addAttrInfo");
		 var len = attr.length;
		 var attributeInfo = '';
		 for(var i=0 ; i < len ;i++){
			 if(isNotNull(attr[i].value)){
			 if(len == (i+1)){
				 attributeInfo += attr[i].value;
			 }else{
				 attributeInfo += attr[i].value +"/";
			 }
			 }
		 }
		 $("#attributes").val(attributeInfo);
		 $("#saveProductInfo").submit();
	 }
	 function onCKeyupNumber(domEle) {
			var minfos = $("#"+domEle).val();
			
			var state = domEle.value = minfos.replace(/[^-\d]/g, '');//兼容负整数
			$("#"+domEle).val(state);
		}
	 $('#saveProductInfo :input').blur(function(){
		 $(this).removeClass('onerrInput');
	 });
	$(function() {
		    $('.addaaaa').delegate('.inp-close','click',function(){
		      $(this).parent('div').remove();
		    });
		    $('#addInput').on('click',function(){
		    	var endTime  = new Date().getTime();
		    	 var div= $('<div class="add-attr pull-left" >' 
		                   +' <input type="text" maxlength="10"'
		                   +'name="addAttr'+endTime+'"'
		                   +'class="form-control addAttrInfo inputNotNull">'
		                    +'<span class="close inp-close">×</span>'
		                  +'</div>');
		      if($('.addaaaa .add-attr').length<6){
		        $('.addaaaa').append(div);
		      }
		    });
		  });
	$('#addProduct').on('hidden.bs.modal', function (e) {
		window.location.reload();
		});
	
	
	
	
	/*点击大分类时，去查找二级分类*/
	function selectParent(){
		 var maindId = $("#mainIdForAddPage option:selected").val();
		 $.ajax({
		        type:"POST",
		        url :"../productType/linkageMainPage",
		        data:{
		            id:maindId
		        },
		        dataType:"json",
		        success: function(response){
		        	$("#parentType").children("option").remove();
		             $("#parentType").append( "<option value='0'>--请选择--</option>");
		        	if(response.thirdList != null){
 	        	 var tLen = response.secondList.length;
	 	           		for(var i=0; i<tLen; i++){
	 		           		$("#parentType").append( "<option value='"+response.secondList[i].id+"'>"+response.secondList[i].name+"</option>");
	 	           		}
		        	}
		        	if(response.tbBrandList != null){
		        	$("#tbBrandList").children("option").remove();
		        	 $("#tbBrandList").append( "<option value='0'>--请选择--</option>");
	 	           	 var bLen = response.tbBrandList.length;
	 	           	   for(var i=0; i<bLen; i++){
			           		$("#tbBrandList").append( "<option value='"+response.tbBrandList[i].id+"'>"+response.tbBrandList[i].name+"</option>");
		           	   }
		        	}
	 	        }      
		 });
	}

	/*点击二级分类时，去查找三级分类*/
	function selectThirdType(){
		var parentType = $("#parentType option:selected").val();
		
		 $.ajax({
		        type:"POST",
		        url :"../productType/linkageMainPageSecond",
		        data:{
		            id:parentType
		        },
		        dataType:"json",
		        success:function(response){
		        	 $("#thirdType").children("option").remove();
		        	$("#thirdType").append( "<option value='0'>--请选择--</option>");
		        	 var sLen = response.thirdList.length;
		           	   for(var i=0; i<sLen; i++){
			           		$("#thirdType").append( "<option value='"+response.thirdList[i].id+"'>"+response.thirdList[i].name+"</option>");
		           	   }
		        }
			 });
	}