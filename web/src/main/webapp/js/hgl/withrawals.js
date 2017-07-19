
		 /* 表单提交 */
		  function searchResult(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
				$.ajax({
			        type : "POST",
			        url : ctx+'/withdrawals/searchResult',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  }
		 function accountWithdraw(){
			 window.location.href = ctx+'/cashAccount/accountWithdraw';
		 }
		 
		 function doAutomaticShow(id){
			  $("#id").val(id);
			  $( "#dialog4" ).dialog('open');  
		}
		 
		 $(function(){
			  $( "#dialog4" ).dialog({            
			        title:'审核通过',  
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
					    	doAutomatic();
			          	},
			        },
			    }); 
		 });
		 $(function(){
			  $( "#dialog5" ).dialog({            
			        title:'审核拒绝',  
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
					    	doAutomatic();
			          	},
			        },
			    }); 
		 });
		 
		
		 function doAutomatic(){
			var id =$("#id").val();
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			 $.ajax({
		        type : "POST",
		        url : ctx+'/withdrawals/auditWithrawals',
		        data : {
		        	id:id,
		        	params:params,
		        },
		        success : function(response) {
		        	$("#key_withrawals_list").html(response);
		        	$("#dialog4").dialog("close");
		        }
		     }); 
		 }
		 function refuseAutomaticShow(id){
			  $("#id").val(id);
			  $( "#dialog5" ).dialog('open');   
		}
		 function refuseAutomatic(){
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			 var id =$("#id").val();
			 $.ajax({
		        type : "POST",
		        url : ctx+'/withdrawal/refuseAutomatic',
		        data : {
		        	id:id,
		        	params:params,
		        },
		        success : function(response) {
		        	  $("#key_withrawals_list").html(response);
		        	  $("#dialog5").dialog("close");
		        }
		     }); 
	 		}