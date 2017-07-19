$("document").ready(function(){						
	/*修改弹出窗体*/
	$("#dialogUpdate").dialog({	
		title:'修改活动详细信息',  //弹出层的标题
		autoOpen: false,//禁止自己弹出		
		resizable: true,//禁止弹出层缩放		
		draggable :false,//禁止拖动		
		modal: true,//是否有模态框		
		width:'500',     //设置宽高
		closeText:'关闭',//closetitle	
		close:function(){
			emptyValue();
		},
		buttons:{         //创建btn				                  	
			取消:function(){					  
				$(this).dialog("close");	
				emptyValue();
			},
			确定:function(){    //btn的回调函数	
				var isError = true;
				//判定活动名称不能为空
				var activityName = $("#activityInfoUpd #activityName").val();
				if(activityName==""){
					$("#activityInfoUpd #activityNameError").text("活动名称不能为空");
					isError = false;
				}
				var activityDetail = $("#activityInfoUpd #activityDetail").val();
				if(activityDetail==""){
					$("#activityInfoUpd #detailError").text("活动详情不能为空");
					isError = false;
				}
				/*修改活动的图片不能为空*/
				var titleUpdImage = $("#titleUpdImageShow").attr("src");
				if(titleUpdImage==""){
					$("#titleUpdImageError").text("活动标题图片没有上传哦");
					isError = false;
				}
				
				/*修改活动图片必须至少有一张图片*/
				var detailUpdImageOne = $("#detailUpdImageOneShow").attr("src");
				var detailUpdImageTwo = $("#detailUpdImageTwoShow").attr("src");
				var detailUPdImageThree = $("#detailUpdImageThreeShow").attr("src");
				if(detailUpdImageOne =="" && detailUpdImageTwo=="" && detailUPdImageThree==""){
					$("#detailImageUpdError").text("活动详情图片没有上传哦");
					isError = false;
				}
				if(isError ==false){
					return;
				}
				
				var params = $("#activityInfoUpd").serialize();
				$.ajax({
					type:"post",
					url:"doUpdateActivityInfo",
					data:params,
					success:function(data){
						submitform();
					}
				});
				emptyValue();
				$(this).dialog("close");
			}
		}	
	});
	
	//活动名称
	$("#activityInfoUpd #activityName").focus(function(){
		$("#activityInfoUpd #activityNameError").text("");
	});
	
	$("#activityInfoUpd #activityDetail").focus(function(){
		$("#activityInfoUpd #detailError").text("");
	});
	
	/*修改标题图片*/
	$("#titleImage_Upd").change(function(){
		$("#titleUpdImageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var titleUpdImage = document.getElementById("titleImage_Upd");
		var titleUpdImageSize = titleUpdImage.files[0].size;
		if(titleUpdImageSize>5*1024*1024){
			$("#titleUpdImageError").text("上传图片大小不能超过5M");
			$("#titleUpdImageShow").attr("src","");
			return;
		}
		if(objUrl){
			$("#titleUpdImageShow").attr("src",objUrl);
		}
	});
	
	/*修改详情一图片*/
	$("#detailImageOne_Upd").change(function(){
		$("#detailImageUpdError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailUpdImageOne = document.getElementById("detailImageOne_Upd");
		var detailUpdImageOneSize = detailUpdImageOne.files[0].size;
		if(detailUpdImageOneSize>5*1024*1024){
			$("#detailImageUpdError").text("上传图片大小不能超过5M");
			$("#detailUpdImageOneShow").attr("src","");
			return;
		}
		if(objUrl){
			$("#detailUpdImageOneShow").attr("src",objUrl);
		}
	});
	
	/*修改详情二图片*/
	$("#detailImageTwo_Upd").change(function(){
		$("#detailImageUpdError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailUpdImageTwo = document.getElementById("detailImageTwo_Upd");
		var detailUpdImageTwoSize = detailUpdImageTwo.files[0].size;
		if(detailUpdImageTwoSize>5*1024*1024){
			$("#detailImageUpdError").text("上传图片大小不能超过5M");
			$("#detailUpdImageTwoShow").attr("src","");
			return;
		}
		if(objUrl){
			$("#detailUpdImageTwoShow").attr("src",objUrl);
		}
	});
	
	/*修改详情三图片*/
	$("#detailImageThree_Upd").change(function(){
		$("#detailImageUpdError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var detailUpdImageThree = document.getElementById("detailImageThree_Upd");
		console.log(detailUpdImageThree);
		var detailUpdImageThreeSize = detailUpdImageThree.files[0].size;
		console.log(detailUpdImageThreeSize);
		if(detailUpdImageThreeSize>5*1024*1024){
			$("#detailImageUpdError").text("上传图片大小不能超过5M");
			$("#detailUpdImageThreeShow").attr("src","");
			return;
		}
		if(objUrl){
			$("#detailUpdImageThreeShow").attr("src",objUrl);
		}
	});
		
});
			
		
/*修改活动*/
function updateActivityInfo(id){
	$.ajax({                   /*首先查出活动详情*/
		type:"post",
		url:"doSearchActivityInfoDto",
		data:"id="+id,
		success:function(data){
			var jsonObj= $.parseJSON(data);
			$("#dialogUpdate #id").val(jsonObj.id);
			$("#dialogUpdate #activityName").val(jsonObj.activityName);
			$("#dialogUpd #activityDetail").val(jsonObj.activityDetail);
			if(jsonObj.status == 1100){
				$("input[name='status'][value=1100]").attr("checked",true);
			}else if(jsonObj.status == 1102){
				$("input[name='status'][value=1102]").attr("checked",true);
			}
			if(jsonObj.titleImage!=null){
				$("#titleUpdImageShow").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.titleImage);
			}else{
				$("#titleUpdImageShow").attr("src","");
			}
			if(jsonObj.detailImageOne!=null){
				$("#detailUpdImageOneShow").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageOne);
			}else{
				$("#detailUpdImageOneShow").attr("src","");
			}
			if(jsonObj.detailImageTwo!=null){
				$("#detailUpdImageTwoShow").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageTwo);
			}else{
				$("#detailUpdImageTwoShow").attr("src","");
			}
			if(jsonObj.detailImageThree!=null){
				$("#detailUpdImageThreeShow").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageThree);
			}else{
				$("#detailUpdImageThreeShow").attr("src","");
			}		
			$("#dialogUpdate #activityDetail").text(jsonObj.activityDetail);
			$("#dialogUpdate").dialog("open");
		}			
	});
}

function emptyValue(){
	$("#actitityName").text("");
	$("#activityDetail").text("");
	$("#titleUpdImageError,#detailImageUpdError").text("");
	$("#titleUpdImageShow").attr("src","");
	$("#detailUpdImageOneShow").attr("src","");
	$("#detailUpdImageTwoShow").attr("src","");
	$("#detailUpdImageThreeShow").attr("src","");
}