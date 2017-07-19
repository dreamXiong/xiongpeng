
 
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
		        url : ctx+'/shop/doAutomatic?id='+id,
		        success : function(response) {
		           $("#shopmm").html(response.url);
		        }
		     }); 
			$(this).dialog("close");
       },
	        },
	    }); 
	  });

 
 $(function() {              
	    $( "#selectUseSituation" ).dialog({            
	        title:'' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'240',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
		  关闭:function(){          //btn的回调函数
					    $(this).dialog("close");
					    location.reload() ;
		          },
		  确定:function(){
			  			var id = $("#selectUseSituationId").val();
			  			var type = $("#selectUseSituationType").val();
						var useSituation = $("#useSituation_"+id).val();
						$.ajax({
					        type : "POST",
					        url : ctx+'/shop/installTbIntegralRules',
					        data : {
					        		useSituation:useSituation,
					        		type:type,
					        },
					        success : function(response) {
					        	location.reload() ;
					        },error : function(response){
					        	location.reload() ;
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
	          },
 确定:function(){
		   var id =$("#bid").val();
		   var rebate = $("#rebate").val();
		   var twoScale = new RegExp(/(^\d+(\.\d{0,2})?$)/);
			if(rebate=="" || rebate==null){
				$("#msg").text("返利不能为空,为数字并且设置不能50%");
				return false;
			}
			$("#msg").text();
			if(isNaN(rebate) || !twoScale.test(rebate)|| rebate>50){
				$("#msg").text("返利不能为空,并且为数字，最多两位小数并且设置不能50%");
				return false;
			}
		 $.ajax({
	        type : "POST",
	        url : ctx+'/shop/updateEarnings?id='+id+'&rebate='+rebate,
	        success : function(response) {
	        	 location.reload() ;
	        }
	     }); 
		$(this).dialog("close");
   },
        },
    }); 
  });



function updateEarnings(id,rate){
  $("#bid").val(id);
  $("#rebate").val(rate);
  $( "#dialog2" ).dialog('open');  
}


function showRules(id){
	 $.ajax({
	        type:"POST",
	        url :"shopRules",
	        url : ctx+'/shop/shopRules',
	        data:{
	        	rulesId:id,
	        },
	        success: function(response){
	        	$("#myrules").html(response);
	        }
	 	});
	//$("#updateProduct").modal("show");
	  $( "#myrules" ).dialog('open');  
}

$(function() {              
    $( "#myrules" ).dialog({            
	   title:'修改返利规则' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'400',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
	关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	          },
    确定:function(){
    	updateMyRules();
      },
        },
    });
  });

$(function() {              
    $( "#myIntegralrules" ).dialog({            
	   title:'修改积分赠送规则' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'400',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
	关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	          },
    确定:function(){
    	saveIntegralrules("saveIntegralrules");
      },
        },
    });
  });

$(function() {              
    $( "#myIntegralrulesBFB" ).dialog({            
	   title:'修改积分赠送规则' ,  
        autoOpen: false ,//禁止自己弹出
        resizable: false,//禁止弹出层缩放
        draggable :false,//禁止拖动
        modal: true,//是否有模态框
        width:'400',     //设置宽高
        closeText:'关闭',//closetitle
        buttons:{         //创建btn
	关闭:function(){          //btn的回调函数
	    $(this).dialog("close");
	          },
    确定:function(){
    	saveIntegralrules("saveIntegralrulesBFB");
      },
        },
    });
  });

function saveIntegralrules(formId){
	var addType = validateForm(formId);
	if(!addType){
		return;
	}
	$("#" + formId).submit();
}

function updateMyRules(){
	var rewardWay = $("#rewardWay").val();
	var rewards = $("#reward").val();
	var twoScale = new RegExp(/(^\d+(\.\d{0,2})?$)/);
	if(rewards=="" || rewards==null){
		$("#msgrules").text("奖励不能为空,并且为数字");
		return false;
	}
	$("#msgrules").text();
	//alert(!twoScale.test(rewards)); 
	
	if(isNaN(rewards) || !twoScale.test(rewards)){
		$("#msgrules").text("奖励不能为空,并且为数字");
		return false;
	}
	if(rewardWay==0 && rewards>50){
		$("#msgrules").text("奖励的百分比不能超过50%");
		return false;
	}
$("#updateRules").submit();
}

/* 开台平台结算功能和自结算功能 */
$(function(){
	  $( "#dialog5" ).dialog({            
	        title:'服务购买申请' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动  
	        modal: true,//是否有模态框
	    //  height:?
	        width:'320',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn 
	          	关闭:function(){          //btn的回调函数
			    	$(this).dialog("close");
			    },
			    确定:function(){
			    	var settlement = $('#dialog5 input:checked').val();
			    	if(settlement == 0){
			    		weixinPay();
			    	}
			    	if(settlement == 1){
			    		accountPay();
			    	}
			    	if(settlement == 2){
			    		getSettlement();
			    	}
	          	},
	        },
	    }); 
});

$(function(){
	  $( "#dialog4" ).dialog({            
	        title:'服务申请成功' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'640',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn  
			    确定:function(){
			    	$( "#dialog5" ).dialog('close');
			    	refeshPage();
	          	},
	        },
	    }); 
});
/* var payType = $('.layermcont input:checked').val(); */
function refeshPage(){
	 window.location.reload();
}

//上架货品平台结算权限
function getSettlementShow(settlement,shopId,version){
	$("#payMoneySpan").text($("#payMoneyInput").val());
	$("#openType").val("settlement");
	$("#settlementShow").val(settlement);
	$("#shopIdShow").val(shopId);   
	$("#versionShow").val(version);
	$( "#dialog5" ).dialog('open');
}

//金牌代理商购买权限
function getMedalAgentShow(settlement,shopId,version){
	 $.ajax({
        type : "POST",
        url : ctx+'/shop/getMedalAgentMoney',
        data:{
        	random:Math.random()          
        },
        success : function(response) {  
        	$("#payMoneySpan").text(response);
        	$("#openType").val("medalAgent");
        	$("#settlementShow").val(settlement);
        	$("#shopIdShow").val(shopId);   
        	$("#versionShow").val(version);
        	$( "#dialog5" ).dialog('open');
        },
        error : function(){
        	alert('获取支付金额失败');
        }
	});
}

function getSettlement(){
	var settlement = $("#settlementShow").val();
	var shopId = $("#shopIdShow").val();
	var version = $("#versionShow").val();
	var remarkInfo = $("#remarkInfo").val();
	var openType = $("#openType").val();
	 $.ajax({
	        type : "POST",
	        url : ctx+'/shop/getSettlement',
	        data:{
	        	settlement:settlement,
	        	shopId:shopId,
	        	version:version,
	        	remarkInfo:remarkInfo,
	        	openType:openType,
	        },
	        success : function(response) {
	        	 $( "#dialog5" ).dialog('close');
	        	 $("#dialog4").dialog("open");
	        },
		});
}
 function accountPay(){
		var shopId = $("#shopIdShow").val();
		var settlement = $("#settlementShow").val();
		var version = $("#versionShow").val();   
		var openType = $("#openType").val();
		 $.ajax({
		        type : "POST",
		        url : ctx+'/shop/accountPay',
		        data:{
		        	settlement:settlement,
		        	shopId:shopId,
		        	version:version,
		        	openType:openType,
		        },
		        success : function(response) {
		        	 $( "#dialog5" ).dialog('close');
		        	alert("支付成功!!");
		        	window.location.href = window.location.href;
		        },error:function(response){
		        	alert("支付失败，账户余额不足！！");
		        },
			});
	}
 
 function weixinPay(){
	 var openType = $("#openType").val();
	 var shopId = $("#shopIdShow").val();                      
	 window.location.href = ctx+"/shop/weixinPay?shopId="+shopId+"&openType="+openType;
 }
 
 /* 服务购买申请切换 */
 $(function(){
	 
	 $('.pay input[type="radio"]').click(function(){
		 
		 var val = $(this).val();
		 if(val == 2){
			 $('.hidden').show();
		 }else{
			 $('.hidden').hide();
		 }
	 });
	 
 });
 
 
 
 $("document").ready(function(){		
		//上传图像
		$("#input").change(function(){
			
	
			var image = document.getElementById("input");
			var imageSize = image.files[0].size;
			if(imageSize>5*1024*1024){
				alert("上传图片大小不能超过5M");
				return false;
			}
			
			$("#updateImage").submit();				
		});
				
	});	

	
 
 
 
 
 
 
 
 
 
 
 