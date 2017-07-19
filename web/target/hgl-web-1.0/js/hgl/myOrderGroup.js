	$(function() {
		//防止页面刷新时浏览器缓存问题
		$("#orderState").val(0);
		$("#orderType").val(0);
		
		$('dd').click(function(event) {
			$('dd').removeClass('active');
			$(this).addClass('active');
		});
		$('.orderType a').on('click',function(){
			$(this).addClass('active').siblings('a').removeClass('active');
			$("#orderType").val($(this).attr("name"));
			submitform();
		});
	  $(document).delegate("#orderStateSel a" ,'click',function(){
		  var oLen = $("#orderStateSel .active").length;
		 
		  if($(this).hasClass('active')){
			  
			 if(oLen != 1){
				  $(this).removeClass('active');
			 }
			}else{
				$(this).addClass('active');
			}
		  
		  
		  if($(this).attr("name") == '0'){
			  $("#orderStateSel .active").removeClass("active");
			  
			  $("#allOrderGroup").addClass("active");   
		  }else{
			  $("#allOrderGroup").removeClass("active");   
		  }
		  var os = $("#orderStateSel .active");
			var orderState = '';
			for(var i=0 ; i < os.length ; i++){
				if(os.length == (i+1) ){
					orderState += $(os[i]).attr("name");
				}else{ 
					orderState += $(os[i]).attr("name")+",";
				}
				$("#orderState").val(orderState);
			}
			submitform();     
	    });
		
		$( "#from" ).datepicker({
			onClose: function( selectedDate ) {
        		$( "#to" ).datepicker( "option", "minDate", selectedDate );
      		}
		});
		$( "#to" ).datepicker({ 
			maxDate:0,
			onClose: function( selectedDate ) {
        		$( "#from" ).datepicker( "option", "maxDate", selectedDate );
      		}
		});
		// 获取时间对象
		var oDate = new Date();
		// 获取当前的时间 年-月-日
		var to_year = oDate.getFullYear();
		var to_month = oDate.getMonth()+1;
		var to_date = oDate.getDate();	
		// 30天前的日期
		oDate.setTime(oDate.getTime()- 30 * 24 * 60 * 60 * 1000);
		// 获取30天前的 年-月-日
		var new_year = oDate.getFullYear();
		var new_month = oDate.getMonth()+1;
		var new_date = oDate.getDate();	

		//月-日的length小于两位数 
		if(to_month < 10){
			to_month = '0'+to_month;
		}
		if(to_date < 10){
			to_date = '0'+to_date;
		}
		if(new_month < 10){
			new_month = '0'+new_month;
		}
		if(new_date < 10){
			new_date = '0'+new_date;
		}
		// 获取时间的 值给input
		$('#from').val(new_year+'-'+new_month+'-'+new_date);
		$('#to').val(to_year+'-'+to_month+'-'+to_date);
	});
	 /* 表单提交 */
	  function submitform(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/serachMyOrderGroup',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	  }

	  $(function() {              
		    $( "#dialog" ).dialog({            
		        title:'取消订单' ,  
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
	        	cancleOrderGroup();
	          },
		        },
		    }); 
		  });
	  
	  function showCancleOrderDivlog(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $( "#dialog" ).dialog('open');  
	  }
	  function cancleOrderGroup(){
		  var reson = $("#textShow").val();
		  $("#stopReason").val(reson);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/cancleMyOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $( "#dialog" ).dialog('close'); 
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function payOrderGroup(id){
		  	$("#orderGroupId").val(id);
		  	 var version = $("#version_"+id).val();
			  $("#version").val(version);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/payMyOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function orderStateInfo(params){
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/selectMyOrderStatesCount',
		        data : params,
		        dataType:"json",
		        success : function(response) {
		        	$("#staus200").text("待确定("+response.tbProductList.staus200+")");
		        	$("#staus202").text("待付款("+response.tbProductList.staus202+")");
		        	$("#staus204").text("待补发货("+response.tbProductList.staus204+")");
		        	$("#staus208").text("待发货("+response.tbProductList.staus208+")");
		        	$("#staus210").text("待确定收货("+response.tbProductList.staus210+")");
		        	$("#staus218").text("待确定终止("+response.tbProductList.staus218+")");
		        	$("#staus220").text("交易完成("+response.tbProductList.staus220+")");
		        }
		    }); 
	  }
	  
	  
	  $(function() {              
		    $( "#stopOrderDivlog" ).dialog({            
	    	   title:'终止订单' ,  
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
	        	stopOrderGroup();
	          },
		        },
		    });
		  });
	  
	  function showStopOrderDivlog(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $("#stopOrderDivlog").dialog('open');  
	  }
	  function stopOrderGroup(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var reson = $("#stopTextShow").val();
			$("#stopReason").val(reson);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/stopMyOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#stopOrderDivlog").dialog('close');  
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function cancleStopOrderGroup(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/cancleStopOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  
	  function configStopOrderGroup(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/configStopOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function configGoodsReceipt(id){
		  $("#orderGroupId").val(id);
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/configGoodsReceipt',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  /* 表单提交 */  
	  function submitform(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : ctx+'/myOrderGroup/serachMyOrderGroup',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	  }
	  /**
	   * 漏发错发
	   * @param id
	   */
	  function showReissueOderGrop(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $( "#reissue" ).dialog('open');  
	  }
	  
	  $(function() {              
		    $( "#reissue" ).dialog({            
	    	   title:'漏发错发订单' ,  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		        height:'390',
		        width:'542',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
			关闭:function(){          //btn的回调函数
			    $(this).dialog("close");
			          },
	        确定:function(){
	        	reissueOrderGroup();
	          },
		        },
		    });
		  });
	  function reissueOrderGroup(){
		  
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var reson = $("#reossiueDesciption").val();
		    if(reson==""){
		    	$(".msg").text("请填写描述！");
		    	issubmit =false ;
		    	return ;
		    }
		    $(".msg").text("");
		    var val1 = $('#uinputOne_Path').val();
		    var val2 = $('#uinputTwo_Path').val();
		    var val3 = $('#uinputThree_Path').val();
		    var val4 = $('#uinputFour_Path').val();
		    if(val1==""&&val2==""&&val3==""&&val4==""){
		    	$(".msg").text("");
		    	$(".msg1").text("请上传图片！");
		    	return ;
		    }
		    $(".msg1").text("");
		       $("#stopReason").val(reson);
		    	var images=val1+","+val2+","+val3+","+val4;
		    	 $("#images").val(images);
			    var params=frm.serialize();
			  $.ajax({
			        type : "POST",
			        url : ctx+'/myOrderGroup/reissueOrderGroup',
			        data : params,
			        success : function(response) {
			        	 $("#reissue").dialog('close');  
			        	 $("#" + dataDomId).html(response);
			        	 orderStateInfo(params);
			        }
			    }); 
		    
			
	  }