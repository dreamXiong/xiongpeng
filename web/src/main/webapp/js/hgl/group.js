$(function() {      
	   $( "#dialog" ).dialog({            
		   title:'删除失败' ,  
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
	        },
	    });
	   
	   $( "#dialog4" ).dialog({            
		   title:'删除' ,  
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
	    	delectGroup();
	        $(this).dialog("close");
	          },
	        },
	    });
    $( "#dialog2" ).dialog({            
        title:'添加分组' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'440',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
        	  关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	          },
	          确定:function(){ 
				var addType = validateForm("addGroup");
				if(!addValidate){
					$('#addName').toggleClass('onerrInput'); 
					return;
				}
					if(!addType){ 
						return;
					}
					 var params=$("#addGroup").serialize();
						$.ajax({
					        type : "POST",
					        url : ctx+'/group/addGroup',
					        data : params,
					        success : function(response) {
					        	 location.reload() ;
					        }
					    });
						$(this).dialog("close");
		 			},
        		},
    	}); 
    
    $( "#dialog3" ).dialog({            
        title:'修改分组' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'440',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
        	  关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	          },
	          确定:function(){ 
				var addType = validateForm("updateGroup");
					if(!addType){ 
						return;
					}
					updateValidateGroup();
					if(!updateValidate){
						$('#updateName').toggleClass('onerrInput'); 
						return; 
					}
					 var params=$("#updateGroup").serialize();
						$.ajax({
					        type : "POST",
					        url : ctx+'/group/saveUpdateGroup',
					        data : params,
					        success : function(response) {
					        	$("#updateGroupListInfo").html(response);
					        }
					    });
						$(this).dialog("close");
		 			},
        		},
    	}); 
});

function addGroup(){  
  $(".inputNotNull").val("");
  $("#addNameError").hide();
  $(".inputNotNull").removeClass("onerrInput");
  $( "#dialog2" ).dialog('open');  
}

function delectGroupValidate(id){
	$("#userGroupId").val(id);
	$.ajax({
        type : "POST",
        url : ctx+'/group/delectGroupValidate',
        data : {
        	groupId:id
        },
        dataType:"json",
        success : function(response) {
        	if(response.code == 1){
        		$("#dialog").dialog('open');  
        	}else{
        		$("#dialog4").dialog('open');  
        	}
        }
    });
}
function delectGroup(){
	var id = $("#userGroupId").val();
	$.ajax({
        type : "POST",
        url : ctx+'/group/delectGroup',
        data : {
        	groupId:id
        },
        success : function(response) {
        	 location.reload();
        }
    });
}

function updateDialog(id){
	$.ajax({
        type : "POST",
        url : ctx+'/group/updateGroupDialog',
        data : {
        	groupId:id
        },
        success : function(response) {
        	$("#dialog3").html(response);
        	$("#dialog3").dialog('open');
        }
    });
}

var addValidate = true;
function addValidateGroup(){
	var addName = $("#addName").val();
	if(isNull(addName)){
		return;
	}
	$.ajax({
        type : "POST",
        url : ctx+'/group/addValidateGroup',
        data:{
        	name:addName
        },
        success : function(response) {
        	if(response.code == 0){
        		$("#addNameError").hide();
        		addValidate = true;
        	}else{
        		$("#addNameError").show();
        		$('#addName').toggleClass('onerrInput'); 
        		addValidate = false;
        	}
        }
	});
}

var updateValidate = true;
function updateValidateGroup(){
	var updateName = $("#updateName").val();
	var groupId = $("#groupId").val();
	if(isNull(addName)){
		return;
	}
	$.ajax({
        type : "POST",
        url : ctx+'/group/updateValidateGroup',
        data:{
        	name:updateName,
        	groupId:groupId
        },
        success : function(response) {
        	if(response.code == 0){
        		$("#updateNameError").hide();
        		updateValidate = true;
        	}else{
        		$("#updateNameError").show();
        		$('#updateName').toggleClass('onerrInput'); 
        		updateValidate = false;
        	}
        }
	});
}