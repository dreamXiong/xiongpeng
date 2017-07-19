
	$(function() {
		$( "#dialog" ).dialog({            
       		title:'提示' ,  
       		autoOpen: false ,
       		resizable: false,
       		draggable :false,
       		modal: true,
       		width:'340',     
       		
       		closeText:'关闭',
       		buttons:{        
       		  关闭:function(){   
       		    $(this).dialog("close");
       		  },
       		  确定:function(){
       			  var asPrice=$("#dialog input[name='price']:checked").val();
       			  if(asPrice==null || typeof(asPrice) == "undefined" || asPrice==''){
       				    $("#dialog7").html("请选择按 入库价格/零售价格 批量定价!!!");
       				    $("#dialog7").dialog('open'); 
       				 	return;
       			  }
       			  var priceMethod=$("#dialog input[name='priceMethod"+asPrice+"']:checked").val();
       			  if(priceMethod==null || typeof(priceMethod) == "undefined" || priceMethod==''){
       				  	$("#dialog7").html("选择按 比例/单价 批量定价!!!");
    				    $("#dialog7").dialog('open'); 
       				  	return;
       			  }
       			  var price=$("input[name='price"+asPrice+"priceMethod"+priceMethod+"']").val();
       			  if(price==null || typeof(price) == "undefined" || price==''){
       				  $("#dialog7").html("请填写批量定价的数值!!!");
       				  $("#dialog7").dialog('open'); 
       				  return;
       			  }else if(isNaN(price)){
       				  $("#dialog7").html("请正确填写批量定价的数值!!!");
      				  $("#dialog7").dialog('open'); 
       				  return ;
       			  }
       			  $("#asPrice").val(asPrice);        
       			  $("#priceMethod").val(priceMethod);
       			  $("#price").val(price);
       			  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
       			  var dataDomId = "key_" + EcWeb.currentModalName + "_list";
       			  var frm = $("#" + frmId);
       			  var params = frm.serialize();
       			  $.ajax({
       		        type : "POST",
       		        url : 'batchUpdatePrice',
       		        data : params,
       		        success : function(response) {
       		        	$("#inventoryId").val('');
       		            $("#" + dataDomId).html(response);
       		        }
       		     }); 
       			 $(this).dialog("close");
       		  }
       		}
   		});
		
   		$( "#dialog1" ).dialog({   
       		autoOpen: false ,
       		resizable: false,
       		draggable :false,
       		modal: true,
       		width:'360',     
       		
       		closeText:'关闭',
       		buttons:{        
       		  关闭:function(){   
       		    $(this).dialog("close");
       		  }
       		}
   		});
   		
		 $( "#dialog2" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  关闭:function(){   
	       		    $(this).dialog("close");
	       		  },
	       		  确定:function(){
	       		    $(this).dialog("close");
	       		  },
	       		},
	   		});
		 
		 $( "#dialog4" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  关闭:function(){   
	       		    $(this).dialog("close");
	       		  },
	       		  确定:function(){
	       			  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
	       			  var dataDomId = "key_" + EcWeb.currentModalName + "_list";
	       			  var frm = $("#" + frmId);
	       			  var params = frm.serialize();
	       			 $.ajax({
	       		        type : "POST",
	       		        url : 'markDelete',
	       		        data : params,
	       		        success : function(response) {
	       		        	$("#inventoryId").val('');
	       		            $("#" + dataDomId).html(response);
	       		        }
	       		     }); 
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
		 
		 $( "#dialog5" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  关闭:function(){   
	       		    $(this).dialog("close");
	       		  },
	       		  确定:function(){
	       			  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
	       			  var dataDomId = "key_" + EcWeb.currentModalName + "_list";
	       			  var frm = $("#" + frmId);
	       			  var params = frm.serialize();
	       			 $.ajax({
	       		        type : "POST",
	       		        url : 'updateStatus',
	       		        data : params,
	       		        success : function(response) {
	       		        	$("#inventoryId").val('');
	       		        	$("#state").val('');
	       		            $("#" + dataDomId).html(response);
	       		        }
	       		     }); 
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
		 
		 $( "#dialog6" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  关闭:function(){   
	       		    $(this).dialog("close");
	       		  },
	       		  确定:function(){
	       			  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
	       			  var dataDomId = "key_" + EcWeb.currentModalName + "_list";
	       			  var frm = $("#" + frmId);
	       			  var params = frm.serialize();
	       			 $.ajax({
	       		        type : "POST",
	       		        url : 'batchUpdateStatus',
	       		        data : params,
	       		        success : function(response) {
	       		        	$("#inventoryId").val('');
	       		        	$("#state").val('');
	       		            $("#" + dataDomId).html(response);
	       		        }
	       		     }); 
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
		 $( "#dialog7" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  确定:function(){
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
		 
		 $( "#dialog8" ).dialog({            
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'360',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  关闭:function(){   
	       		    $(this).dialog("close");
	       		  },
	       		  确定:function(){
	       			  if($("#dialog8 #code").val() == ''){
	       				  $("#dialog9").html('货号不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			  }
	       			  if($("#dialog8 #name").val() == ''){
	       				  $("#dialog9").html('名称不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			  }
	       			  if($("#dialog8 #salesPrice").val() == ''){             
	       				  $("#dialog9").html('市场价不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			  }else{
						  if(isNaN($("#dialog8 #salesPrice").val())){
							  $("#dialog9").html('市场价必须是数值');
		       				  $("#dialog9" ).dialog('open'); 
		       				  return;
						  }
						  if($("#dialog8 #salesPrice").val() < 0){
							  $("#dialog9").html('市场价不能为负数');
		       				  $("#dialog9" ).dialog('open'); 
		       				  return;
						  }
	       			  }
	       			  if($("#dialog8 #instockPrice").val() == ''){
	       				  $("#dialog9").html('成本价不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			  }else{
						  if(isNaN($("#dialog8 #instockPrice").val())){
							 $("#dialog9").html('成本价必须是数值');
		       				 $("#dialog9" ).dialog('open'); 
		       				 return;
						  }
						  if($("#dialog8 #instockPrice").val() < 0){
							 $("#dialog9").html('成本价不能为负数');
		       				 $("#dialog9" ).dialog('open'); 
		       				 return;
						  }
		       		  }
	       			if($("#dialog8 #outstockPrice").val() != ''){
//	       				  $("#dialog9").html('出库价格不能为空');
//	       				  $("#dialog9" ).dialog('open'); 
//	       				  return;
	       				  if(isNaN($("#dialog8 #outstockPrice").val())){
							  $("#dialog9").html('销售价必须是数值');
		       				  $("#dialog9" ).dialog('open'); 
		       				  return;
						  }
	       				  if($("#dialog8 #outstockPrice").val() < 0){
							  $("#dialog9").html('销售价不能为负数');
		       				  $("#dialog9" ).dialog('open'); 
		       				  return;
						  }
	       			  }
//	       			else{
//							if(isNaN($("#dialog8 #outstockPrice").val())){
//								  $("#dialog9").html('出库价格必须是数值');
//			       				  $("#dialog9" ).dialog('open'); 
//			       				  return;
//							}
//		       			  }
	       			if($("#dialog8 #spec").val() == ''){
	       				  $("#dialog9").html('规格不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			}
	       			if($("#dialog8 #saleInventory").val() == ''){
	       				  $("#dialog9").html('库存量不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			}else{
	       				if(isNaN($("#dialog8 #saleInventory").val())){
						  $("#dialog9").html('库存量必须是数值');
		       			  $("#dialog9" ).dialog('open'); 
		       			  return;
						}
	       			}
	       			if($("#dialog8 #material").val() == ''){
	       				  $("#dialog9").html('材质不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			}
	       	/*		if($("#dialog8 #oneboxCount").val() == ''){
	       				  $("#dialog9").html('装箱数不能为空');
	       				  $("#dialog9" ).dialog('open'); 
	       				  return;
	       			  }else{
	       				if(parseInt($("#dialog8 #oneboxCount").val()) != $("#dialog8 #oneboxCount").val()){  
	       					$("#dialog9").html('装箱数必须是整数');
		       				$("#dialog9" ).dialog('open'); 
		       				return;
	       				}
	       			  }*/
	       			var checkAttrValueFlag = true;
	       			$("#saveInventory input[name='attributeValues']").each(function(){
	       				if(this.value == ''){
	       				   $("#dialog9").html('附加属性不能为空');
	       				   $("#dialog9" ).dialog('open'); 
	       				   checkAttrValueFlag = false;
	       				   return false;
	       				}
	       				if(this.value.indexOf(';') != -1){
	       				   $("#dialog9").html('附加属性中有非法字符');
	       				   $("#dialog9" ).dialog('open'); 
	       				   checkAttrValueFlag = false;
	       				   return false;
	       				}
	       			});
	       			if(checkAttrValueFlag == false){
	       				return;
	       			}
	       			if($("#dialog8 #outstockPrice").val() == ''){
	       				$("#dialog8 #outstockPrice").val(0);
	       			}
	       			 var params = $("#saveInventory").serialize();
	       			 $.ajax({
	       		        type : "POST",
	       		        url : 'saveInventory',
	       		        data : params,
	       		        success : function(response) {                
	       		        	$("#submitInventory #productId").val($("#inventProductId").val());
	       		        	$("#submitInventory").submit();
	       		           //window.location.href = window.location.href;
	       		        }
	       		     }); 
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
		 
		 $( "#dialog9" ).dialog({            
	       		title:'提示' ,  
	       		autoOpen: false ,
	       		resizable: false,
	       		draggable :false,
	       		modal: true,
	       		width:'340',     
	       		closeText:'关闭',
	       		buttons:{        
	       		  确定:function(){
	       			$(this).dialog("close");
	       		  },
	       		},
	   		});
//  		$(document).delegate('#put','click',function(){
//   			$('#dialog2').dialog('open');
//   		});
  		
//   		$(document).delegate('#pricing','click',function() {
//      		$( "#dialog" ).dialog('open'); 
//      	});
   		
      /*	$(document).delegate('.details','click',function() {
      		$( "#dialog1" ).dialog('open'); 
      	});*/

      	// 点击批量定价之后
      	 $('.pricing').delegate('.warp','click',function(){
      	 	$('.inner').hide();
      	 	$(this).find('.inner').show();
      	 });
      	 $('tbody tr:odd').addClass('odd');
	});	
	
function submitform(){
  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
    var frm = $("#" + frmId);
    var params=frm.serialize();
	$.ajax({
        type : "POST",
        url : 'serachInventory',
        data : params,
        success : function(response) {
            $("#" + dataDomId).html(response);
            $('tbody tr:odd').addClass('odd');
        }
    }); 
}

function deleteInventory(id){
	$("#inventoryId").val(id);
	$("#dialog4" ).dialog('open'); 
}

function inventoryDetails(id){
	 $.ajax({
	        type : "POST",
	        url : 'getInventoryById?id='+id,
	        data : {},
	        success : function(response) {
	        	$('#dialog1').html(response);
	        	$('#dialog1').dialog('open');
	        }
	    });
}


function singleAllChecked(){
	 var checked = $("#singgle").is(':checked');
	 $("#mytable input[name='tbpInventoryIds']").prop("checked",checked); 
}

function futureChecked(){
	var recodeCount = 0;
	 $("#mytable input[name='tbpInventoryIds']").each(function(){
		if(this.checked == false){
			if(recodeCount == 0){
			   recodeCount = 1;
			}
		}
	});
	if(recodeCount == 1){
		$("#singgle").prop("checked",false); 
	}else{
		$("#singgle").prop("checked",true); 
	}
}

function updateStatus(id,status){
	$("#inventoryId").val(id);
	$("#state").val(status);
	var statuss = '上架';
	if(status == 1){
		statuss = '下架';
	}
	$("#dialog5").html('您确定要'+statuss+'吗??');
	$("#dialog5").dialog('open'); 
}

function batchUpdateStatus(status){
	 var ids = [];
	 $("#mytable input[name='tbpInventoryIds']").each(function(){
		 if(this.checked == true){
			 ids.push(this.value);
		 }
	 }); 
	 $("#state").val(status);
	 var statuss = '下架';
	 if(status == 1){
		statuss = '上架';
	 }
	if(ids.length >0){
		$("#inventoryId").val(ids);
		$("#dialog6").html('您确定要批量'+statuss+'吗??');
		$("#dialog6").dialog('open'); 
	}else{
		$("#dialog7").html('请选择需要批量'+statuss+'的库存');
		$("#dialog7").dialog('open'); 
	}
}

function batchUpdatePrice(){
	 var ids = [];
	 $("#mytable input[name='tbpInventoryIds']").each(function(){
		 if(this.checked == true){
			 ids.push(this.value);
		 }
	 }); 
	if(ids.length >0){
		$("#inventoryId").val(ids);
		$("#dialog").dialog('open'); 
	}else{
		$("#dialog7").html('请选择库存后再批量定价');
		$("#dialog7").dialog('open'); 
	}
}

function addInventory(productId){
	 $.ajax({
	        type : "POST",
	        url : 'operationInventory?productId='+productId,
	        success : function(response) {
	        	
	            $("#dialog8").html(response);
	            $("#dialog8").dialog('open'); 
	        }
	     });      
}

function isUniqueInventoryCode(){
	  var code = $("#dialog8 #code").val();
	  var productId = $("#dialog8 #addProductId").val();
	 $.ajax({
        type : "POST",   
        url : 'isUniqueInventoryCode?productId='+productId+'&code='+code,
        success : function(response) {
        	if(response.result == false){
        		$("#codeTips").html('该货号已经存在');
        	}else{
        		$("#codeTips").html('');
        	}
        }
     }); 
}

function updateInventory(id){
	 $.ajax({
	        type : "POST",
	        url : 'updateInit?id='+id,
	        success : function(response) {
	            $("#dialog8").html(response);
	            $("#dialog8").dialog('open'); 
	        }
	     }); 
}