$(function() {
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
	    
	    $(document).delegate('.indent-head', 'click', function(event) {
	    	var span=$(this).find('.icon');
	    	if(span.hasClass('fa fa-angle-right icon')){
	    		span.attr('class','fa fa-angle-down icon');
	    	}else{
	    		span.attr('class','fa fa-angle-right icon');
	    	}
	        $(this).siblings('table').toggle();
	    });  
	    
  });
		  /* 表单提交 */  
		  function submitform(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
				$.ajax({
			        type : "POST",
			        url : ctx+'/sendOutGoods/serachOrderGroup',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  }
		  
	  function sendProduct(id,version){
		/*  var version = $("#version_"+id).val();*/
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/sendOutGoods/sendOutGoods',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        }
		    }); 
	  }