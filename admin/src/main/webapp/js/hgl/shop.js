	  $(function() {  
		 
		    $( "#dialog" ).dialog({            
	    	   title:'审核' ,  
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
	  
	  
	  function showCancleOrderDivlog(id,userId,state){
		  $("#id").val(id);
		  $("#userId").val(userId);
		  $("#state1").val(state);
		  $( "#dialog" ).dialog('open');  
	  }
	  
	  function canupdateState(){
		/*  var state = $("#state").val();
		  var msg=$("#checkmesg").val();*/
		  var id=$("#id").val();
		  var frmId = "key_" + EcWeb.currentModalName + "_frm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();;
		  $.ajax({
		        type : "POST",
		        url : ctx+'/supplier/updateUserState',
		        data : params,
		        success : function(response) {
		        	if(response.errcode =="0"){
		        	if(response.state == 0){
		        		$("#statusId_"+id).html("待审核");
		        		alert(response.msg);
		        	}else if(response.state == 1){
		        		$("#statusId_"+id).html("审核通过");
		        		alert(response.msg);
		        	}else if(response.state == 2){
		        		$("#statusId_"+id).html("审核拒绝");
		        		alert(response.msg);
		  			}else if(response.state == 3){
		  				$("#statusId_"+id).html("关闭");
		  				alert(response.msg);
		  			}
		        	}else {
		        		alert(response.msg);
		        	}
		        	
		        }
		    }); 
	  }