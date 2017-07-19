$("document").ready(function(){								
	//删除活动信息弹出窗体
	$("#dialogDlt").dialog({            //弹出层初始化
	    title:'您确定要删除该订单吗？' ,  //弹出层的标题
	    autoOpen: false ,//禁止自己弹出
	    resizable: false,//禁止弹出层缩放
	    draggable :false,//禁止拖动
	    modal: true,//是否有模态框
	    width:'340',     //设置宽高  	        
	    closeText:'关闭',//closetitle
	    close:function(){
	    },
	    buttons:{         //创建btn	        
	        取消:function(){
	        	$(this).dialog("close");
	        },
	        确定:function(){          //btn的回调函数
	        	var id = $("#dltId").val();		            	
	        	$.ajax({
	        		type:"post",
	        		url:"doDeleteActivityInfo",
	        		data:"id="+id,
	        		success:function(data){
	        				submitform();					
	        		}
	        	});
	        	$(this).dialog("close");
	        }
	    }
	});	
				
	//发布活动弹出窗体
	$("#dialogRelease").dialog({            //弹出层初始化
        title:'您确定发布该项活动吗？' ,  //弹出层的标题
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'340',     //设置宽高  	        
        closeText:'关闭',//closetitle
	    close:function(){
	    },
	    buttons:{         //创建btn	         
        	取消:function(){
	            $(this).dialog("close");
	          },
	          确定:function(){          //btn的回调函数
	  			var id = $("#relId").val();		            	
	  			$.ajax({
	  				type:"post",
	  				url:"doUpdateStatus",
	  				data:"id="+id,
	  				success:function(data){
	  					if(data=="true"){
	  						submitform();	
	  					}
	  				}
	  			});
	  	        $(this).dialog("close");
  	    	}
	    }
	});			  
});
	
function empty(){
	$("#activityNameError,#dateError,#timeError,#detailError").text("");
}
	
/*删除活动*/
function deleteActivityInfo(id){
	$("#dltId").val(id);
	$("#dialogDlt").dialog("open");
}
	
/*发布下线活动*/
function updateActivityInfoStatus(id){
	$("#relId").val(id);
	$("#dialogRelease").dialog("open");
}	