$("document").ready(function(){
	
	$("#dialogAdd").dialog({	
		title:'添加活动信息',  //弹出层的标题
		autoOpen: false,//禁止自己弹出		
		resizable: true,//禁止弹出层缩放		
		draggable :false,//禁止拖动		
		modal: true,//是否有模态框		
		width:'500',     //设置宽高
		closeText:'关闭',//closetitle	
		close:function(){
			clearValue();
		},
		buttons:{         //创建btn				                  	
			取消:function(){	
				clearValue();
			    $(this).dialog("close");				    
			},
			确定:function(){    //btn的回调函数
				var isError = true;		            	 
				/*验证活动名称*/
		        var activityName = $("#dialogAdd #activityName").val();
		     	if(activityName==""){
		     		$("#dialogAdd #activityNameError").text("您还没有输入活动名称哦");
		     	  	isError = false;
		     	}		
		     	
		     	/*验证活动标题图片必须输入*/
		     	var titleImageVal = $("#titleImage_val").val();
		     	if(titleImageVal==""){
		     		$("#titleImageError").text("活动标题图片没有上传喔");
		     		isError = false;
		     	}
		     	
		     	var detailImageOneVal = $("#detailImageOne_val").val();
		     	var detailImageTwoVal = $("#detailImageTwo_val").val();
		     	var detailImageThreeVal = $("#detailImageThree_val").val();
		     	if(detailImageOneVal=="" && detailImageTwoVal=="" && detailImageThreeVal==""){
		     		$("#detailImageError").text("活动详情图片没有上传喔");
		     		isError = false;
		     	}
				  
				/*活动详情*/
				var activityDetail = $("#dialogAdd #activityDetail").val();
				if(activityDetail==""){
					$("#detailError").text("活动详情必须输入哦");
					 isError = false;
				}
				if(isError == false){
					return;
				}
				  
				var params = $("#activityInfoAdd").serialize();
				$.ajax({
					type:"post",
					url:"doAddActivityInfo",
					data:params,
					success:function(data){
						submitform();
					}
				});
				clearValue();
				$(this).dialog("close");				
			}
		}	
	});

	  /*活动标题获得光标*/
	$("#dialogAdd #activityName").focus(function(){
		$("#dialogAdd #activityNameError").text(""); 
	});
	  
	$("#dialogAdd #activityDetail").focus(function(){
		$("#dialogAdd #detailError").text("");
	});
	
	/*验证图片--标题*/
	$("#titleImage").change(function(){
		$("#titleImageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var titleImage = document.getElementById("titleImage");
		var imageSize = titleImage.files[0].size;
		if(imageSize>5*1024*1024){
			$("#titleImageError").text("上传图片大小不能超过5M");
			return;
		}
		
		if(objUrl){
			$("#titleImageShow").attr("src",objUrl);
		}
	});
	
	/*验证图片--详情一*/
	$("#detailImageOne").change(function(){
		$("#detailImageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailImageOne = document.getElementById("detailImageOne");
		var detailImageOneSize = detailImageOne.files[0].size;
		if(detailImageOneSize>5*1024*1024){
			$("#detailImageError").text("上传图片大小不能超过5M");
			return;
		}
		
		if(objUrl){
			$("#detailImageOneShow").attr("src",objUrl);
		}
	});
	
	/*验证图片--详情二*/
	$("#detailImageTwo").change(function(){
		$("#detailImageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailImageTwo = document.getElementById("detailImageTwo");
		var detailImageTwoSize = detailImageTwo.files[0].size;
		if(detailImageTwoSize>5*1024*1024){
			$("#detailImageError").text("上传图片大小不能超过5M");
			return;		
		}
		
		if(objUrl){
			$("#detailImageTwoShow").attr("src",objUrl);
		}
	});
	
	/*验证图片--详情三*/
	$("#detailImageThree").change(function(){
		$("#detailImageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailImageThree = document.getElementById("detailImageThree");
		var detailImageThreeSize = detailImageThree.files[0].size;
		if(detailImageThreeSize>5*1024*1024){
			$("#detailImageError").text("上传图片大小不能超过5M");
			return;
		}
		if(objUrl){
			$("#detailImageThreeShow").attr("src",objUrl);
		}		
	});
	  
	$(".pImg").fileupload({	
		url : "validateImg",
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
	    function(e, data){
	        if (data.result.code == '0'){
	            var imgName = data.result.imgNo.split("_");
	            $("#"+imgName[0]+"_val").val(data.result.imgNo);
	        }else{
	            
	        }
	}).on('fileuploadsubmit', function(e,data){
		data.formData = {imgNo: $(this).attr("id")};  //如果需要额外添加参数可以在这里添加
	});			  
});
	
function clearValue(){
	$("#dialogAdd #activityName").val("");
	$("#dialogAdd #activityDetail").val("");
	$("#titleImage_val").val("");
	$("#detailImageOne_val").val("");
	$("#detailImageTwo_val").val("");
	$("#detailImageThree_val").val("");
	$("#titleImageError").text("");
	$("#detailError").text("");
	$("#titleImageError").text("");
	$("#detailImageError").text("");
	$("#titleImageShow").attr("src","");
	$("#detailImageOneShow").attr("src","");
	$("#detailImageTwoShow").attr("src","");
	$("#detailImageThreeShow").attr("src","");
}

/*开启添加活动弹出框*/
function addActivityInfo(){
	$("#dialogAdd").dialog("open");
} 