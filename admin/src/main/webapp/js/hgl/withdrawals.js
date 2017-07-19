$(function() {              
	    $( "#dialog" ).dialog({            
	        title:'' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'440',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
		关闭:function(){          //btn的回调函数
		    $(this).dialog("close");
		    location.reload() ;
		          },
     确定:function(){
			   var id =$("#id").val();
			 $.ajax({
		        type : "POST",
		        url : ctx+'/withdrawal/auditWithrawals?id='+id,
		        success : function(response) {
		        	if(response.errcode ==0){
		        		$("#statusId_"+id).html("审核通过");
		        		$("#buttonId_"+id).html("-");
		        		alert(response.msg);
		        	}else{
		        		alert(response.msg);
		        	}
		        }
		     }); 
			$(this).dialog("close");
       },
	        },
	    }); 
	  });

$(function() {              
    $( "#dialog2" ).dialog({            
        title:'' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
    //  height:?
        width:'440',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
	关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	    location.reload() ;
	          },
 确定:function(){
		   var id =$("#withdrawalsId").val();
		 $.ajax({
	        type : "POST",
	        url : ctx+'/withdrawal/refuseAutomaticShow?id='+id,
	        success : function(response) {
	        	if(response.errcode ==0){
	        		$("#statusId_"+id).html("审核拒绝");
	        		$("#buttonId_"+id).html("-");
	        	}
	        }
	     }); 
		$(this).dialog("close");
 		},
      },
    }); 
  });
 
function doAutomaticOder(id){
	  $("#id").val(id);
	  $( "#dialog" ).dialog('open');  
}

function refuseAutomaticShow(id){
	  $("#withdrawalsId").val(id);
	  $( "#dialog2" ).dialog('open');  
}