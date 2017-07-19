 $(function() {  
		 
		    $( "#dialog" ).dialog({            
	    	   title:'变更招商状态' ,  
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
			          },
	        确定:function(){
	        	canupdateState();
	        	 $(this).dialog("close");
	          },
		        },
		    });
		  });
	  
	  
	  function showAgency(id,state){
		  $("#aid").val(id);
		  $("#state1").val(state);
		  $( "#dialog" ).dialog('open');  
	  }
	  
	  function canupdateState(){
		  var id=$("#aid").val();
		  var frmId = "key_" + EcWeb.currentModalName + "_frm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();;
		  $.ajax({
		        type : "POST",
		        url : ctx+'/merchants/updateAgencyState',
		        data : params,
		        success : function(response) {
		        	if(response.errcode =="0"){
		        	if(response.state == 0){
		        		$("#statusId_"+id).html("未生效");
		        		alert(response.msg);
		        	}else if(response.state == 1){
		        		$("#statusId_"+id).html("暂时生效");
		        		alert(response.msg);
		        	}else if(response.state == 2){
		        		$("#statusId_"+id).html("生效");
		        		alert(response.msg);
		  			}else if(response.state == 3){
		  				$("#statusId_"+id).html("失效");
		  				alert(response.msg);
		  			}
		        	}else {
		        		alert(response.msg);
		        	}
		        	
		        }
		    }); 
	  }