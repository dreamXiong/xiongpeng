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
	 function subUpdateProductInfo(){
		 var addType = validateForm("updateProductInfo");
		 if(!addType){
			 return;
		 } 
		 var attr = $(".upAttr");
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
		
		 $("#updateAttrList").val(attributeInfo);
		/* alert($("#updateProductInfo").attr("action"));*/
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
		
	$(function() {
	    $('.updateInfoDiv').delegate('.inp-close','click',function(){
	      $(this).parent('div').remove();
	    });
	    $('#updateInput').on('click',function(){
	   /*   var div= $('<div class="pull-left">' 
	                   +' <input type="text" maxlength="10" class="form-control inputNotNull upAttr">'
	                    +'<span class="close inp-close">×</span>'
	                  +'</div>');*/
	      
	      var endTime  = new Date().getTime();
	      var div= $('<div class="pull-left">' 
	                   +' <input type="text" maxlength="10"'
	                   +'name="addAttr'+endTime+'"'
	                   +'class="form-control inputNotNull upAttr">'
	                    +'<span class="close inp-close">×</span>'
	                  +'</div>');
	      
	      if($('.add .upAttr').length<6){
	        $('.add').append(div);
	      }
	    });
	  });
	
	$('#updateProduct').on('hidden.bs.modal', function (e) {
		window.location.reload();
		});