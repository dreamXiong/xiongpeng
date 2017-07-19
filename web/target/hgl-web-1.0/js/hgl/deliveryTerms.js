$("document").ready(function(){		
	$('#clause tbody tr:odd').addClass('odd');
	//添加送货条款	
	$("#dialogAdd").dialog({	
	title:'添加送货条款信息',  //弹出层的标题
    autoOpen: false,//禁止自己弹出		
    resizable: true,//禁止弹出层缩放		
    draggable :false,//禁止拖动		
    modal: true,//是否有模态框		
    width:'500',     //设置宽高
    height:'550',
    closeText:'关闭',//closetitle	
    close:function(){
    	clearAddData();
     },
    buttons:{         //创建btn
    	取消:function(){	
	    	//取消之前清除样式和清空数据
	    	clearAddData();
	    	$(this).dialog("close");		 	        
	    },
          确定:function(){          //btn的回调函数
    	  var isError = true;
          //描叙不能为空
          var description = $("#deliveryTerms_Add #description").val();
          if(description ==""){
             $("#descriptionError").text("描述不能为空");
        	 $("#deliveryTerms_Add #description").css("border","1px red solid");     	 
        	 $("#deliveryTerms_Add #calcMethodBox").css("margin-top","12px");
        	 isError = false; 	        	  
          }
          
          var calcMethod = $("#deliveryTerms_Add #calcMethod").val();
          if(calcMethod==0){
        	  $("#deliveryTerms_Add #calcMethodError").text("您还没有选择计算方式");
        	  isError = false;
          }
                 
          //起始距离和截止距离必须为数字
          var minDistance = $("#deliveryTerms_Add #minDistance").val();
          var maxDistance = $("#deliveryTerms_Add #maxDistance").val();
          if(minDistance!=0 && isNaN(minDistance)){
          	 $("#deliveryTerms_Add #minDistanceError").text("起始距离必须输入数字");
        	 $("#deliveryTerms_Add #minDistance").css("border","1px red solid");
        	 $("#deliveryTerms_Add #maxDistanceBox").css("margin-top","12px");
        	 isError = false;
          }
          if(maxDistance!=0 && isNaN(maxDistance)) {
        	  $("#deliveryTerms_Add #maxDistanceError").text("截止距离必须输入数字");
        	  $("#deliveryTerms_Add #maxDistance").css("border","1px red solid");       	  
        	  $("#deliveryTerms_Add #minAmountBox").css("margin-top","12px");
        	  isError = false;
          }
         
          
          //截止距离必须大于起始距离
          if(!isNaN(minDistance) && minDistance!=0 && maxDistance!=0 && !isNaN(maxDistance) && (parseInt(minDistance)>=parseInt(maxDistance))){
        	  $("#deliveryTerms_Add #maxDistanceError").text("截止距离必须大于起始距离");
        	  $("#deliveryTerms_Add #maxDistance").css("border","1px red solid");
        	  $("#deliveryTerms_Add #minAmountBox").css("margin-top","12px");
        	  isError = false;
          }
          
         //已填写起始截止金额必须填写起始金额
          if(maxDistance!=0 && minDistance==""){
        	  $("#deliveryTerms_Add #minDistanceError").text("起始距离必须填写");
        	  $("#deliveryTerms_Add #minDistance").css("border","1px red solid");      	  
        	  $("#deliveryTerms_Add #maxDistanceBox").css("margin-top","12px");
        	  isError = false;
          }
           	          
          //起始金额和截止金额必须为数字
          var minAmount = $("#deliveryTerms_Add #minAmount").val();
          var maxAmount = $("#deliveryTerms_Add #maxAmount").val();
          if(minAmount!=0 && isNaN(minAmount)){
        	  $("#deliveryTerms_Add #minAmountError").text("起始金额必须为数字");
        	  $("#deliveryTerms_Add #minAmount").css("border","1px red solid");
        	  $("#deliveryTerms_Add #maxAmountBox").css("margin-top","12px");
        	  isError = false;
          }
          if(maxAmount!=0 && isNaN(maxAmount)){
        	  $("#deliveryTerms_Add #maxAmountError").text("截止金额必须为数字");
        	  $("#deliveryTerms_Add #maxAmount").css("border","1px red solid");
        	  $("#deliveryTerms_Add #freightBox").css("margin-top","12px");
        	  isError = false;
          }
          
          //截止金额必须大于起始金额
          if(!isNaN(minAmount) && minAmount!=0 && maxAmount!=0 && !isNaN(maxAmount) && (parseInt(minAmount)>=parseInt(maxAmount))){
        	  $("#deliveryTerms_Add #maxAmountError").text("截止金额必须大于起始金额 ");
        	  $("#deliveryTerms_Add #minAmount").css("border","1px red solid");
        	  $("#deliveryTerms_Add #freightBox").css("margin-top","12px");
        	  isError = false;
          }
          
          //已填写起始截止金额必须填写起始金额
          if(maxAmount!=0 && minAmount==""){
        	  $("#deliveryTerms_Add #minAmountError").text("起始金额必须填写");
        	  $("#deliveryTerms_Add #minAmount").css("border","1px red solid");
        	  $("#deliveryTerms_Add #maxAmountBox").css("margin-top","12px");
        	  isError = false;
          }
	 	          
          //运费必须填写
          var freight = $("#deliveryTerms_Add #freight").val();
          if(freight==""){
        	  $("#deliveryTerms_Add #freightError").text("运费必须填写");
        	  $("#deliveryTerms_Add #freight").css("border","1px red solid");
        	  isError = false;
          }
          if(freight!="" && isNaN(freight)){
        	  $("#deliveryTerms_Add #freightError").text("运费必须为数字");
        	  $("#deliveryTerms_Add #freight").css("border","1px red solid");
        	  isError = false;
          }
          
          if(isError == false){
        	  return;
          }
         	 	          
    	  var params = $("#deliveryTerms_Add").serialize();
 	  	  $.ajax({
 	  		type:"post",
 	  	  	url:"addDeliveryTerms",
 	  		data:params,
 	  		success:function(data){
 	  			if(data!="false"){				
 	  				//添加成功后必须新增一条记录
	 	  			var calcMethodTxt ="";
	 	  			if(calcMethod == 410){
	 	  				calcMethodTxt="按距离";
	 	  			}else if(calcMethod == 412){
	 	  				calcMethodTxt="按总金额";
	 	  			}
	 	  			var html="<tr id='deliveryTerm"+data+"'><td>"+description+"</td><td>"+calcMethodTxt+"</td>"+
							 "<td>"+minDistance+"</td><td>"+maxDistance+"</td><td>"+minAmount+"</td><td>"+maxAmount+"</td><td>"+freight+"</td>"+
							 "<td><a href='javascript:void(0)' class='btn'onclick='updateDeliveryTerms("+data+")'>修改</a> |"+
							 " <a href='javascript:void(0)' class='btn' onclick='deleteDeliveryTerms("+data+")'>删除</a></td></tr>";
	 	  			
					$("table").append(html);
					$('#clause tbody tr:odd').addClass('odd');
					location.reload(true);
 	  			}
 	  			//清除样式和数据
 	  			clearAddData();	 	  			
 	  		}	  	  
 	  	  });
 	  	  $(this).dialog("close");
	    }   	
	    	
	    }	
	});
	 		
  		//获得光标清除样式
  		$("#deliveryTerms_Add #description").focus(function(){
  			$("#deliveryTerms_Add #description").removeAttr("style");
  			$("#deliveryTerms_Add #descriptionError").text("");
  			$("#calcMethodBox").css("margin-top","0");
  		});
  		
  		$("#deliveryTerms_Add #calcMethod").focus(function(){
  			$("#deliveryTerms_Add #calcMethodError").text("");
  		});
  		
  		$("#deliveryTerms_Add #minDistance").focus(function(){
  			$("#deliveryTerms_Add #minDistance").removeAttr("style");
  			$("#deliveryTerms_Add #minDistanceError").text("");
  			$("#deliveryTerms_Add #maxDistanceBox").css("margin-top","0");
  		});
  		
  		$("#deliveryTerms_Add #maxDistance").focus(function(){
  			$("#deliveryTerms_Add #maxDistance").removeAttr("style");
  			$("#deliveryTerms_Add #maxDistanceError").text("");
  			$("#deliveryTerms_Add #minAmountBox").css("margin-top","0");
  		});
  		
  		$("#deliveryTerms_Add #minAmount").focus(function(){
  			$("#deliveryTerms_Add #minAmount").removeAttr("style");
  			$("#deliveryTerms_Add #minAmountError").text("");
  			$("#deliveryTerms_Add #maxAmountBox").css("margin-top","0");
  		});
  		
  		$("#deliveryTerms_Add #maxAmount").focus(function(){
  			$("#deliveryTerms_Add #maxAmount").removeAttr("style");
  			$("#deliveryTerms_Add #maxAmountError").text("");
  			$("#deliveryTerms_Add #freightBox").css("margin-top","0");
  		});
  		
  		$("#deliveryTerms_Add #freight").focus(function(){
  			$("#deliveryTerms_Add #freight").removeAttr("style");
  			$("#deliveryTerms_Add #freightError").text("");
  		});
  		
  		
  		
  				
  			
  		//修改信息	
  		 $("#dialogUpd").dialog({            //弹出层初始化	
 	        title:'修改送货条款信息',  //弹出层的标题		
 	        autoOpen: false ,//禁止自己弹出		
 	        resizable: true,//禁止弹出层缩放		
 	        draggable :false,//禁止拖动		
 	        modal: true,//是否有模态框		
 	        width:'500',     //设置宽高
 	        height:'550',
 	        closeText:'关闭',//closetitle		
 	        close:function(){
 	        	clearUpdData();  
 	        },
 	        buttons:{         //创建btn		
 	        	     		
 	            取消:function(){		        	
   		        $(this).dialog("close");
   		        clearUpdData();  
 	          },
 	         确定:function(){          //btn的回调函数
	        		var isError = true;
	   		        //描叙不能为空
	   		        var description = $("#deliveryTerms_Upd #description").val();
	   		        if(description ==""){
	   		        	$("#deliveryTerms_Upd #descriptionErrorUpd").text("描述不能为空");
	   		        	$("#deliveryTerms_Upd #description").css("border","1px red solid");     	 
	   		        	$("#deliveryTerms_Upd #calcMethodBox").css("margin-top","12px");	   		           	
	   		        	isError = false; 	        	  
	   		        }
	   		          
			        //计算方式必须选择
	   		        var calcMethod = $("#deliveryTerms_Upd #calcMethod").val();
			        if(calcMethod==0){
			        	$("#deliveryTerms_Upd #calcMethodErrorUpd").text("计算方式必须选择");     	 
	   		        	$("#deliveryTerms_Upd #minDistanceBox").css("margin-top","12px");
			        	isError = false;
			        }
	   		          
	 		        //起始距离和截止距离必须为数字
	 		        var minDistance = $("#deliveryTerms_Upd #minDistance").val();
	 		        var maxDistance = $("#deliveryTerms_Upd #maxDistance").val();
	 		        if(minDistance!=0 && isNaN(minDistance)){
	 		            $("#deliveryTerms_Upd #minDistanceErrorUpd").text("起始距离必须输入数字");
	 		        	$("#deliveryTerms_Upd #minDistance").css("border","1px red solid");
	 		        	$("#deliveryTerms_Upd #maxDistanceBox").css("margin-top","12px");
	 		        	isError = false;
	 		        }
	   		        if(maxDistance!=0 && isNaN(maxDistance)) {
	   		        	$("#deliveryTerms_Upd #maxDistanceErrorUpd").text("截止距离必须输入数字");
	   		        	$("#deliveryTerms_Upd #maxDistance").css("border","1px red solid");
	   		        	$("#deliveryTerms_Upd #minAmountBox").css("margin-top","12px");
	   		        	isError = false;
	   		        }
	   		         
	   		          
	   		          //截止距离必须大于起始距离
	   		          if(!isNaN(minDistance) && minDistance!=0 && maxDistance!=0 && !isNaN(maxDistance) && (parseInt(minDistance)>=parseInt(maxDistance))){
	   		        	  $("#deliveryTerms_Upd #maxDistanceErrorUpd").text("截止距离必须大于起始距离");
	   		        	  $("#deliveryTerms_Upd #maxDistance").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #minAmountBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          
	   		         //已填写起始截止距离必须填写起始距离
	   		          if(maxDistance!=0 && minDistance==""){
	   		        	  $("#deliveryTerms_Upd #minDistanceErrorUpd").text("起始距离必须填写");
	   		        	  $("#deliveryTerms_Upd #minDistance").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #maxAmountBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          	   		           	          
	   		          //起始金额和截止金额必须为数字
	   		          var minAmount = $("#deliveryTerms_Upd #minAmount").val();
	   		          var maxAmount = $("#deliveryTerms_Upd #maxAmount").val();
	   		          if(minAmount!=0 && isNaN(minAmount)){
	   		        	  $("#deliveryTerms_Upd #minAmountErrorUpd").text("起始金额必须为数字");
	   		        	  $("#deliveryTerms_Upd #minAmount").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #maxAmountBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          if(maxAmount!=0 && isNaN(maxAmount)){
	   		        	  $("#deliveryTerms_Upd #maxAmountErrorUpd").text("截止金额必须为数字");
	   		        	  $("#deliveryTerms_Upd #maxAmount").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #freightBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          
	   		          //截止金额必须大于起始金额
	   		          if(!isNaN(minAmount) && minAmount!=0 && maxAmount!=0 && !isNaN(maxAmount) &&(parseInt(minAmount)>=parseInt(maxAmount))){
	   		        	  $("#deliveryTerms_Upd #maxAmountErrorUpd").text("截止金额必须大于起始金额 ");
	   		        	  $("#deliveryTerms_Upd #minAmount").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #maxAmount").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #freightBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          
	   		          //已填写起始截止金额必须填写起始金额
	   		          if(maxAmount!=0 && minAmount==""){
	   		        	  $("#deliveryTerms_Upd #minAmountErrorUpd").text("起始金额必须填写");
	   		        	  $("#deliveryTerms_Upd #minAmount").css("border","1px red solid");
	   		        	  $("#deliveryTerms_Upd #maxAmountBox").css("margin-top","12px");
	   		        	  isError = false;
	   		          }
	   		          
	   		          //运费必须填写
	   		          var freight = $("#deliveryTerms_Upd #freight").val();
	   		          if(freight==""){
	   		        	  $("#deliveryTerms_Upd #freightErrorUpd").text("运费必须填写");
	   		        	  $("#deliveryTerms_Upd #freight").css("border","1px red solid");
	   		        	  isError = false;
	   		          }
	   		          if(freight!="" &&isNaN(freight)){
	   		        	 $("#deliveryTerms_Upd #freightErrorUpd").text("运费必须为数字");
	   		        	  $("#deliveryTerms_Upd #freight").css("border","1px red solid");
	   		        	  isError = false;
	   		          }
	   		          
	   		          if(isError == false){
	   		        	  return;
	   		          } 
	        		 	
	        		  var params = $("#deliveryTerms_Upd").serialize();
	        		  $(this).dialog("close");
	        		  
	        		  $.ajax({
	        			  type:"post",
	        			  url:"doUpdateDeliveryTerms",
	        			  data:params,
	        			  success:function(data){	        					
	        					clearUpdData();
	        					var objId = $("#deliveryTerms_Upd #id").val();
	        					var calcMethodTxt = "";
	        					if(calcMethod=="410"){
	        						calcMethodTxt="按距离";
	        					}else if(calcMethod=="412"){
	        						calcMethodTxt="按总金额";
	        					}
	        					$("#description"+objId).text(description);
	        					$("#calcMethod"+objId).text(calcMethodTxt);
	        					$("#minDistance"+objId).text(minDistance);
	        					$("#maxDistance"+objId).text(maxDistance);
	        					$("#minAmount"+objId).text(minAmount);
	        					$("#maxAmount"+objId).text(maxAmount);
	        					$("#freight"+objId).text(freight);
	        					location.reload(true);
	        			  }
	        		  });
	        	}
 	        }		
 	    });
  		 
  		//获得光标清除样式
   		$("#deliveryTerms_Upd #description").focus(function(){
   			$("#deliveryTerms_Upd #description").removeAttr("style");
   			$("#deliveryTerms_Upd #descriptionErrorUpd").text("");
   			$("#deliveryTerms_Upd #calcMethodBox").css("margin-top","0");
   		});
   		
   		
   		
   		$("#deliveryTerms_Upd #calcMethod").focus(function(){
   			$("#deliveryTerms_Upd #calcMethodErrorUpd").text(""); 
   		});
   		  		
   		$("#deliveryTerms_Upd #minDistance").focus(function(){
   			$("#deliveryTerms_Upd #minDistance").removeAttr("style");
   			$("#deliveryTerms_Upd #minDistanceErrorUpd").text("");
   			$("#deliveryTerms_Upd #maxDistanceBox").css("margin-top","0");
   		});
   		
   		$("#deliveryTerms_Upd #maxDistance").focus(function(){
   			$("#deliveryTerms_Upd #maxDistance").removeAttr("style");
   			$("#deliveryTerms_Upd #maxDistanceErrorUpd").text("");
   			$("#deliveryTerms_Upd #minAmountBox").css("margin-top","0");
   		});
   		
   		$("#deliveryTerms_Upd #minAmount").focus(function(){
   			$("#deliveryTerms_Upd #minAmount").removeAttr("style");
   			$("#deliveryTerms_Upd #minAmountErrorUpd").text("");
   			$("#deliveryTerms_Upd #maxAmountBox").css("margin-top","0");
   		});
   		
   		$("#deliveryTerms_Upd #maxAmount").focus(function(){
   			$("#deliveryTerms_Upd #maxAmount").removeAttr("style");
   			$("#deliveryTerms_Upd #maxAmountErrorUpd").text("");
   			$("#deliveryTerms_Upd #freightBox").css("margin-top","0");
   		});
   		
   		$("#deliveryTerms_Upd #freight").focus(function(){
   			$("#deliveryTerms_Upd #freight").removeAttr("style");
   			$("#deliveryTerms_Upd #freightErrorUpd").text("");
   		});
   		
   		
   		//删除交货条款信息
   		$( "#dialogDlt" ).dialog({            //弹出层初始化
   	        title:'您确定要删除该订单吗？' ,  //弹出层的标题
   	        autoOpen: false ,//禁止自己弹出
   	        resizable: false,//禁止弹出层缩放
   	        draggable :false,//禁止拖动
   	        modal: true,//是否有模态框
   	        //height:?
   	        width:'340',     //设置宽高  	        
   	        closeText:'关闭',//closetitle
		     close:function(){
		    	clearAddData();
		     },
   	        buttons:{         //创建btn
   	          
   	          取消:function(){
   	            $(this).dialog("close");
   	          },
   	       确定:function(){          //btn的回调函数
	        	  
	        	  debugger;
	        	var id=$("#dltId").val();
	            $(this).dialog("close");
	   	         $.ajax({
	   	 			type:"post",
	   	 			url:"doDeleteDeliveryTerms",
	   	 			data:{"id":id},
	   	 			success:function(data){
	   	 				if(data=="true"){
	   	 					$("#deliveryTerm"+id).hide();
	   	 				} 				
	   	 			}
	   	 		});
	          }
   	        }

   	    });
  	});
  	
  		
  		
  	//添加
  	function addDeliveryTerms(){
  		
  		$("#dialogAdd").dialog("open");
  		
  	}

  	//清除样式和数据
	function clearAddData(){
		$("#deliveryTerms_Add #description").val("");
        $("#deliveryTerms_Add #description").removeAttr("style");
		$("#deliveryTerms_Add #descriptionError").text("");
		
		$("#deliveryTerms_Add #calcMethod").get(0).selectedIndex =0;
		$("#deliveryTerms_Add #calcMethod").removeAttr("style");
		$("#deliveryTerms_Add #calcMethodError").text("");
		
		$("#deliveryTerms_Add #minDistance").val("");
		$("#deliveryTerms_Add #minDistance").removeAttr("style");
		$("#deliveryTerms_Add #minDistanceError").text("");
		
	    $("#deliveryTerms_Add #maxDistance").val("");
		$("#deliveryTerms_Add #maxDistance").removeAttr("style");
		$("#deliveryTerms_Add #maxDistanceError").text("");
		
		$("#deliveryTerms_Add #minAmount").val("");
		$("#deliveryTerms_Add #minAmount").removeAttr("style");
		$("#deliveryTerms_Add #minAmountError").text("");
		
	    $("#deliveryTerms_Add #maxAmount").val("");
		$("#deliveryTerms_Add #maxAmount").removeAttr("style");
		$("#deliveryTerms_Add #maxAmountError").text("");
		
		$("#deliveryTerms_Add #freight").val("");
		$("#deliveryTerms_Add #freight").removeAttr("style");
		$("#deliveryTerms_Add #freightError").text("");
	}  	
	
  	//清除修改样式和数据           
	function clearUpdData(){
		$("#deliveryTerms_Upd #description").val("");
        $("#deliveryTerms_Upd #description").removeAttr("style");
		$("#deliveryTerms_Upd #descriptionErrorUpd").text("");
		
		$("#deliveryTerms_Upd #calcMethod").get(0).selectedIndex =0;
		$("#deliveryTerms_Upd #calcMethod").removeAttr("style");
		$("#deliveryTerms_Upd #calcMethodErrorUpd").text("");
		
		$("#deliveryTerms_Upd #minDistance").val("");
		$("#deliveryTerms_Upd #minDistance").removeAttr("style");
		$("#deliveryTerms_Upd #minDistanceErrorUpd").text("");
		
	    $("#deliveryTerms_Upd #maxDistance").val("");
		$("#deliveryTerms_Upd #maxDistance").removeAttr("style");
		$("#deliveryTerms_Upd #maxDistanceErrorUpd").text("");
		
		$("#deliveryTerms_Upd #minAmount").val("");
		$("#deliveryTerms_Upd #minAmount").removeAttr("style");
		$("#deliveryTerms_Upd #minAmountErrorUpd").text("");
		
	    $("#deliveryTerms_Upd #maxAmount").val("");
		$("#deliveryTerms_Upd #maxAmount").removeAttr("style");
		$("#deliveryTerms_Upd #maxAmountErrorUpd").text("");
		
		$("#deliveryTerms_Upd #freight").val("");
		$("#deliveryTerms_Add #freight").removeAttr("style");
		$("#deliveryTerms_Add #freightErrorUpd").text("");
	} 
	
	
  	//修改
 	function updateDeliveryTerms(objId){
 		$.ajax({
 	  		type:"post",
 	  	  	url:"doSearchDeliveryTermsById",
 	  		data:"id="+objId,
 	  		dataType:"json",
 	  		success:function(data){
 	  			$("#deliveryTerms_Upd #id").val(data.id);
 	  			$("#deliveryTerms_Upd #version").val(data.version);
 	  			$("#deliveryTerms_Upd #description").val(data.description);
 	  			$("#deliveryTerms_Upd #calcMethod").val(data.calcMethod);
 	  			$("#deliveryTerms_Upd #minDistance").val(data.minDistance);
 	  			$("#deliveryTerms_Upd #maxDistance").val(data.maxDistance);
 	  			$("#deliveryTerms_Upd #minAmount").val(data.minAmount);
 	  			$("#deliveryTerms_Upd #maxAmount").val(data.maxAmount);
 	  			$("#deliveryTerms_Upd #freight").val(data.freight);
 	  			$("#dialogUpd").dialog("open");
 	  		}
 		});	
	}
 	
 	
 	
  	
	 	
 	//删除
 	function deleteDeliveryTerms(objId){
 		$("#dltId").val(objId);
 		$("#dialogDlt").dialog("open");
 	}
 	