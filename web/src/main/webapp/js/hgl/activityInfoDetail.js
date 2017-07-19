$("document").ready(function(){
	$("#dialogDetail").dialog({	
		title:'活动详细信息',  //弹出层的标题
	    autoOpen: false,//禁止自己弹出		
	    resizable: true,//禁止弹出层缩放		
	    draggable :false,//禁止拖动		
	    modal: true,//是否有模态框		
	    width:'500',     //设置宽高
	    closeText:'关闭',//closetitle	
	    close:function(){
	    	clearDetails();
	    },
	    buttons:{         //创建btn				                  			 
		  确定:function(){    //btn的回调函数
			  clearDetails();
			$(this).dialog("close");
		  }
	   }	
    });
});


/*显示活动详情*/
function displayActivityDetail(id){
	$.ajax({
		type:"post",
		url:"doSearchDetail",
		data:"id="+id,
		success:function(data){
			var jsonObj= $.parseJSON(data); 
			$("#dialogDetail #activityName").text(jsonObj.activityName);							
			var statusTxt = "";
			if(jsonObj.status ==1100){
				statusTxt ="已开始";
			}else if(jsonObj.status ==1102){
				statusTxt ="未开始";
			}			
			$("#dialogDetail #status").text(statusTxt);
			if(jsonObj.titleImage!=null){
				$("#dialogDetail #title_Image").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.titleImage);
			}
			if(jsonObj.detailImageOne!=null){
				$("#dialogDetail #detailImage_One").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageOne);
			}
			if(jsonObj.detailImageTwo!=null){
				$("#dialogDetail #detailImage_Two").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageTwo);
			}
			if(jsonObj.detailImageThree!=null){
				$("#dialogDetail #detailImage_Three").attr("src","displayImage?id="+jsonObj.id+"&imageName="+jsonObj.detailImageThree);
			}
			
			$("#dialogDetail #activityDetail").text(jsonObj.activityDetail);
			$("#dialogDetail").dialog("open");
		}			
	});
}

function clearDetails(){
	$("#title_Image").attr("src","");
	$("#detailImage_One").attr("src","");
	$("#detailImage_Two").attr("src","");
	$("#detailImage_Three").attr("src","");
}
	
