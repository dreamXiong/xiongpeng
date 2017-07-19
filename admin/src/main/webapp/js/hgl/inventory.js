$(function() {
    var iWidth=$('.input-group').width();
    $('#input2').change(function(){
      $('#input1').val($(this).val());
    });
    
    /*主类联动*/
    $(document).delegate('#linkMainType','change',function(){
  	  var val=$(this).val();
  	  $.ajax({
  	        type:"POST",
  	        url :"../productType/linkageMainPage",
  	        data:{
  	            id:val
  	        },
  	        dataType:"json",
  	        success:function(response){
  	        	var val=$("#linkMainType").children("option").eq(0).val();
	        	 if(val==null || val==""){
	        		$("#linkMainType").children("option").eq(0).remove();
	        	 }
  	        	 $("#linkSecondType").children("option").remove();
  	        	 $("#linkSecondType").append( "<option value=''>---请选择---</option>");
  	        	 var tLen = response.secondList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#linkSecondType").append( "<option value='"+response.secondList[i].id+"'>"+response.secondList[i].name+"</option>");
  	               	}
  	           	}
  	           	 /*品牌*/
  	        	 $("#linkBrand").children("option").remove();
  	        	 $("#linkBrand").append( "<option value=''>---请选择---</option>");
  	        	 tLen = response.tbBrandList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#linkBrand").append( "<option value='"+response.tbBrandList[i].id+"'>"+response.tbBrandList[i].name+"</option>");
  	               	}
  	           	}
  	           	/*产品*/
  	           	$("#productSelect").children("option").remove();
  	        	 $("#productSelect").append( "<option value=''>---请选择---</option>");
  	        	 tLen = response.tbProductList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#productSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
  	               	}
  	           	}
  	        }
  		 });
    });
    /*二级分类联动*/
     $(document).delegate('#linkSecondType','change',function(){
     	  var val=$(this).val();
     	  $.ajax({
     	        type:"POST",
     	        url :"../productType/linkageMainPageSecond",
     	        data:{
     	            id:val
     	        },
     	        dataType:"json",
     	        success:function(response){
     	        	var val=$("#linkSecondType").children("option").eq(0).val();
     	        	 if(val==null || val==""){
     	        		$("#linkSecondType").children("option").eq(0).remove();
     	        	 }
     	           	 /*三级分类*/
     	        	 $("#linkThirdType").children("option").remove();
     	        	 $("#linkThirdType").append( "<option value=''>---请选择---</option>");
     	        	 tLen = response.thirdList.length;
     	           	if(tLen > 0){ 
     	           		for(var i=0; i<tLen; i++){
     		           		$("#linkThirdType").append( "<option value='"+response.thirdList[i].id+"'>"+response.thirdList[i].name+"</option>");
     	               	}
     	           	}
     	           	/*产品*/
     	           	$("#productSelect").children("option").remove();
     	        	 $("#productSelect").append( "<option value=''>---请选择---</option>");
     	        	 tLen = response.tbProductList.length;
     	           	if(tLen > 0){ 
     	           		for(var i=0; i<tLen; i++){
     		           		$("#productSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
     	               	}
     	           	}
     	        }
     		 });
       });
     
     /*三级分类联动*/
     $(document).delegate('#linkThirdType','change',function(){
  	  var val=$(this).val();
  	  $.ajax({
  	        type:"POST",
  	        url :"../productType/linkageMainPageThird",
  	        data:{
  	            id:val
  	        },
  	        dataType:"json",
  	        success:function(response){
  	        	var val=$("#linkThirdType").children("option").eq(0).val();
 	        	 if(val==null || val==""){
 	        		$("#linkThirdType").children("option").eq(0).remove();
 	        	 }
  	           	/*产品*/
  	           	$("#productSelect").children("option").remove();
  	        	 $("#productSelect").append( "<option value=''>---请选择---</option>");
  	        	 tLen = response.tbProductList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#productSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
  	               	}
  	           	}
  	        }
  		 });
    });
     /*品牌联动联动*/
     $(document).delegate('#linkBrand','change',function(){
  	  var val=$(this).val();
  	  $.ajax({
  	        type:"POST",
  	        url :"../productType/linkageMainPageBrand",
  	        data:{
  	            id:val
  	        },
  	        dataType:"json",
  	        success:function(response){
  	        	var val=$("#linkBrand").children("option").eq(0).val();
	        	 if(val==null || val==""){
	        		$("#linkBrand").children("option").eq(0).remove();
	        	 }
  	           	/*产品*/
  	           	$("#productSelect").children("option").remove();
  	        	 $("#productSelect").append( "<option value=''>---请选择---</option>");
  	        	 tLen = response.tbProductList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#productSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
  	               	}
  	           	}
  	        }
  		 });
    });
     /*产品联动*/
     $(document).delegate('#productSelect','change',function(){
  	  var val=$(this).val();
  	  $.ajax({
  	        type:"POST",
  	        url :"../productType/linkProductForAttribute",
  	        data:{
  	            id:val
  	        },
  	        dataType:"json",
  	        success:function(response){
  	           	$(".attributeValueDIV").remove();
  	        	 tLen = response.attributeList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  	           			$("#inventoryTable").append("<tr class='attributeValueDIV'>"
  	           				    +"<td>"+response.attributeList[i]+"</td>"
  	           					+"	<td colspan='3'>"
  	           				    +"	<input type='text' name='attributeValues[]' class='form-control' value=''>"
  	           			        +"	</td></tr>");
  	               	}
  	           	}
  	        }
  		 });
    });
  });
  /*列表查询条件大类联动*/
$(document).delegate('#mainIdSelect','change',function(){
	  var val=$(this).val();
	  $.ajax({
	        type:"POST",
	        url :"../productType/linkageMainPage",
	        data:{
	            id:val
	        },
	        dataType:"json",
	        success:function(response){
	           	 /*品牌*/
	        	 $("#brandIdSelect").children("option").remove();
	        	 $("#brandIdSelect").append( "<option value='-1'>-请选择-</option>");
	        	 tLen = response.tbBrandList.length;
	           	if(tLen > 0){ 
	           		for(var i=0; i<tLen; i++){
		           		$("#brandIdSelect").append( "<option value='"+response.tbBrandList[i].id+"'>"+response.tbBrandList[i].name+"</option>");
	               	}
	           	}
	           		/*产品*/
		           	$("#productIdSelect").children("option").remove();
		        	 $("#productIdSelect").append( "<option value='-1'>-请选择-</option>");
		        	 tLen = response.tbProductList.length;
		           	if(tLen > 0){ 
		           		for(var i=0; i<tLen; i++){
			           		$("#productIdSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
		               	}
		           	}
	        }
		 });
});
/*二级分类联动*/
function linkageMainPageSecond(thirdTypeIdSelect){
	  var val=$(this).val();
	  $.ajax({
	        type:"POST",
	        url :"../productType/linkageMainPageSecond",
	        data:{
	            id:val
	        },
	        dataType:"json",
	        success:function(response){
	        	$("#"+thirdTypeIdSelect).children("option").remove();
	        	 $("#"+thirdTypeIdSelect).append( "<option value='-1'>-请选择-</option>");
	        	 tLen = response.tbBrandList.length;
	           	if(tLen > 0){ 
	           		for(var i=0; i<tLen; i++){
		           		$("#"+thirdTypeIdSelect).append( "<option value='"+response.thirdList[i].id+"'>"+response.thirdList[i].name+"</option>");
	               	}
	           	}
	        }
		 });
  }
  /*列表查询条件品牌联动*/
$(document).delegate('#brandIdSelect','change',function(){
	  var val=$(this).val();
  	  $.ajax({
  	        type:"POST",
  	        url :"../productType/linkageMainPageBrand",
  	        data:{
  	            id:val
  	        },
  	        dataType:"json",
  	        success:function(response){
  	           	/*产品*/
  	           	$("#productIdSelect").children("option").remove();
  	        	 $("#productIdSelect").append( "<option value='-1'>---请选择---</option>");
  	        	 tLen = response.tbProductList.length;
  	           	if(tLen > 0){ 
  	           		for(var i=0; i<tLen; i++){
  		           		$("#productIdSelect").append( "<option value='"+response.tbProductList[i].id+"'>"+response.tbProductList[i].name+"</option>");
  	               	}
  	           	}
  	        }
  		 });
  });
  /*加载模态对话框*/
 function modalAjax(id,operation){
	 $.ajax({
	        type : "POST",
	        url : 'operationInventory?id='+id+'&operation='+operation,
	        data : {},
	        success : function(response) {
	            $("#modalDiv").html(response);
	            $("#modal-1").modal("show");
	        }
	    });
 }
  
 /*保存规格*/
  function saveInventory(formId){
	  var addType = validateForm(formId);
		if(!addType){
			return;
		}
		if("saveInventory" == formId){
			var i = $("#saleInventoryInfo").val();
			var r ="^-?\\d+$";
			var re = new RegExp(r);
			if (i.search(re) != -1){
				$("#saleInventoryInfo").removeClass('onerrInput');
			} else{
				$("#saleInventoryInfo").addClass('onerrInput');
				return;
			}
		}
		var id=$("#"+formId+" input[name='id']").val();
		if(typeof(id) != "undefined" && id!='' && id!=0 && id!='0'){
			doSaveInventory(formId,false);
    		return;
    	}
		var code=$("#"+formId+" input[name='code']").val();
		$.ajax({
	        type : "POST",
	        url : 'isUniqueInventoryCode?inventoryCode='+code,
	        data : {},
	        success : function(response) {
	        		if(!(response.result || response.result=='false')){
		            	$("#promptModal .modal-body p").html("货号已被注册,请修改其他货号再新增!!!");
			            $("#promptModal").modal("show");
		            }else{
		            	doSaveInventory(formId,true);
		            }
	        }
	    });
	 
  }
  
  function doSaveInventory(formId,isAdd){
	  var frm = $("#" + formId);
	  var params=frm.serialize();
	  $.ajax({
	        type : "POST",
	        url : 'saveInventory',
	        data : params,
	        success : function(response) {
	        	/*刷新库存列表页*/
		  		submitform();
		  		/*刷新库存发布页*/
	        	$("#modal-1").modal("hide");
	        	var statusStr="已下架";
	        	if(response.record.status==1){
	        		statusStr="上架中";
	        	}
	            if(isAdd){
	            	$("#newInvertoryListDiv table").append("<tr id='SEPCTR_"+response.record.id+"'>"
	            			+"<td width='200'>"+response.record.spec+"</td>"
	    					+"<td width='200'>"+response.record.material+"</td>"
	    					+"<td width='100'>"+response.record.oneboxCount+"</td>"
	    					+"<td width='100'>"+response.record.totalInventory+"</td>"
	    					+"<td width='100'>"+response.record.saleInventory+"</td>"
	    					+"<td width='100'>"+response.record.unsaleInventory+"</td>"
	    					+"<td width='100'>"+response.record.instockPrice+"</td>"
	    					+"<td width='100'>"+response.record.outstockPrice+"</td>"
	    					+"<td width='100'>"+response.record.salesPrice+"</td>"
	    					+"<td width='100'>"+statusStr+"</td>"
	    					+"<td width='100'>"
	    					+"<button for='"+response.record.id+"' type='button' class='btn btn-primary btn-xs updateButton'>修改</button>"
	    					+"&nbsp;<button for='"+response.record.id+"' type='button' class='btn btn-primary btn-xs deleteButton'>删除</button>"
	    					+"</td></tr>");
	            	$("#addInventoryTable input").val("");
	            	/*弹出提示*/
	            	$("#promptModal .modal-body p").html("添加库存信息成功!!!");
	            	$("#promptModal").modal("show");
	            }else{
	            	$("#SEPCTR_"+response.record.id+" td").eq(0).html(response.record.spec);
	            	$("#SEPCTR_"+response.record.id+" td").eq(1).html(response.record.material);
	            	$("#SEPCTR_"+response.record.id+" td").eq(2).html(response.record.oneboxCount);
	            	$("#SEPCTR_"+response.record.id+" td").eq(3).html(response.record.totalInventory);
	            	$("#SEPCTR_"+response.record.id+" td").eq(4).html(response.record.saleInventory);
	            	$("#SEPCTR_"+response.record.id+" td").eq(5).html(response.record.unsaleInventory);
	            	$("#SEPCTR_"+response.record.id+" td").eq(6).html(response.record.instockPrice);
	            	$("#SEPCTR_"+response.record.id+" td").eq(7).html(response.record.outstockPrice);
	            	$("#SEPCTR_"+response.record.id+" td").eq(8).html(response.record.salesPrice);
	            	$("#SEPCTR_"+response.record.id+" td").eq(9).html(statusStr);
	            	/*弹出提示*/
	            	$("#promptModal .modal-body p").html("修改库存信息成功!!!");
	            	$("#promptModal").modal("show");
	            }
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
	        url : 'serachInventory',
	        data : params,
	        success : function(response) {
	            $("#" + dataDomId).html(response);
	        }
	    }); 
  }
  /* 更新状态 */
  function updateStatus(id,version,status){
	  var name=$("#nameTd_"+id).html();
	  var statusStr='';
	  if(status==0){
		  statusStr='上架';
	  }else{
		  statusStr='下架'; 
	  }
	  if(window.confirm('你确定要'+statusStr+'库存【'+name+'】吗？')){
		  $.ajax({
		        type : "POST",
		        url : 'updateStatus',
		        data : {id:id,version:version,status:status},
		        success : function(response) {
		            $("#promptModal .modal-body p").html(response.result);
		            $("#promptModal").modal("show");
		            if(response.success){
		            	if(response.record!=null){
		            		if(response.record.status==1){
		            			$("#statusTd_"+id).html("上架中");
		            			$("#buttonTd_"+id).children("button").eq(1).html("下架");
		            		}else{
		            			$("#statusTd_"+id).html("已下架");
		            			$("#buttonTd_"+id).children("button").eq(1).html("上架");
		            		}
		            		$("#buttonTd_"+id).children("button").eq(1).attr("onclick","updateStatus("+response.record.id+","+(response.record.version+1)+","+response.record.status+")");
		            	}
		            }
		        }
		    });
	  }
  }
  
  /*标记删除*/
  $(document).delegate('.deleteButton','click',function(){
	  var id=$(this).attr("for");
	  if(window.confirm('你确定要删除库存吗？')){
		  $.ajax({
			  type : "POST",
			  url : 'markDelete',
			  data : {id:id},
			  success : function(response) {
				  $("#SEPCTR_"+id).remove();
				  $("#promptModal .modal-body p").html("删除库存成功!!!");
				  $("#promptModal").modal("show");
				  submitform();
			  }
		  });
	  }
  });

  /*仓库信息联动*/
/*  function changeGradeCC(){
		 var province = $("#province option:selected").val();
		 getWarehouse(province,'','');
		 if(province == "0"){
			$("#city").html("<option value='0'>-请选择-</option>");
	  		$("#country").html("<option value='0'>-请选择-</option>");
			return;
		 }
		 getCitys(province);
	 }
  
  function changeCityCC(){
	  var city = $("#city option:selected").val();
	  var province = $("#province option:selected").val();
	  getWarehouse(province,city,'');
	  if(city == "0"){
	  	$("#country").html("<option value='0'>-请选择-</option>");
	  	$("#warehouse").html("<option value='0'>-请选择-</option>");
		return;
	   }
	  getCountrys(city);
  }
  
  function changeCountryCC(){
	 var country = $("#country option:selected").val();
	 var city = $("#city option:selected").val();
	 var province = $("#province option:selected").val();
	 getWarehouse(province,city,country);
  }
  */
  /*function getWarehouse(province,city,country){
	  $.ajax({
	        type : "POST",
	        url : 'getWarehouse',
	        data : {province:province,city:city,country:country},
	        success : function(response) {
	        	var selectHtml = "<option value='0'>请选择&nbsp;&nbsp;</option>";
				for ( var i = 0; i < response.warehouseList.length; i++) {
					var code = response.warehouseList[i].id;
					var warehouseName = response.warehouseList[i].warehouseName;
					selectHtml += "<option value='" + code + "'>" + warehouseName
							+ "&nbsp;&nbsp;</option>";
				}
				$("#warehouse").html(selectHtml);
	        }
	    });
  }*/
  
  
  $(document).delegate('#mainTypeSelect2','change',function(){
		  var val=$(this).val();
		  $.ajax({
		        type:"POST",
		        url :"../productType/linkageMainPage",
		        data:{
		            id:val
		        },
		        dataType:"json",
		        success:function(response){
		           	 /*品牌*/
		        	 $("#brandIdSelect2").children("option").remove();
		        	 $("#brandIdSelect2").append( "<option value='-1'>-请选择-</option>");
		        	 tLen = response.tbBrandList.length;
		           	if(tLen > 0){ 
		           		for(var i=0; i<tLen; i++){
			           		$("#brandIdSelect2").append( "<option value='"+response.tbBrandList[i].id+"'>"+response.tbBrandList[i].name+"</option>");
		               	}
		           	}
		           	/*二级分类*/
		           	$("#secondTypeSelect2").children("option").remove();
		           	$("#secondTypeSelect2").append( "<option value='-1'>-请选择-</option>");
		           	tLen = response.secondList.length;
		           	if(tLen > 0){ 
		           		for(var i=0; i<tLen; i++){
		           			$("#secondTypeSelect2").append( "<option value='"+response.secondList[i].id+"'>"+response.secondList[i].name+"</option>");
		           		}
		           	}
		        }
			 });
  });
  
  /*仓库改变事件*/
  $(document).delegate('#warehouse','change',function(){
	  var productId=$('#info').attr("for");
	  if(productId==null || productId=='' || typeof(productId) == "undefined"){
		  return;
	  }
	  $.ajax({
	      type:"POST",
	      url :"../inventory/getInventoryByProductId",
	      data:{productId:productId,warehouseId:$(this).val()},
	      //dataType:"json",
	      success:function(response){
	    	  $('#specDiv').html(response);
	      }
		 });
  });
  
  $(document).delegate('#secondTypeSelect2','change',function(){
	  var val=$(this).val();
	  $.ajax({
	      type:"POST",
	      url :"../productType/linkageMainPageSecond",
	      data:{
	          id:val
	      },
	      dataType:"json",
	      success:function(response){
	      	$("#thirdTypeSelect2").children("option").remove();
	      	 $("#thirdTypeSelect2").append( "<option value='-1'>-请选择-</option>");
	      	 tLen = response.thirdList.length;
	         	if(tLen > 0){ 
	         		for(var i=0; i<tLen; i++){
		           		$("#thirdTypeSelect2").append( "<option value='"+response.thirdList[i].id+"'>"+response.thirdList[i].name+"</option>");
	             	}
	         	}
	      }
		 });
	  });
  
  /*查询产品*/
  function searchProductx(){
	  var params=$('#timelineForm').serialize();
	  $.ajax({
	        type : "POST",
	        url : '../product/searchProduct',
	        data : params,
	        success : function(response) {
	        	var html="";
	        	for(var i=0;i<response.productList.length;i++){
	        		var thirdProductTypeName="";
	        		if(response.productList[i].thirdProductTypeName!=null && response.productList[i].thirdProductTypeName!=''){
	        			thirdProductTypeName+="-"+response.productList[i].thirdProductTypeName;
	        		}
	        		var attributes="";
	        		if(response.productList[i].attributes!=null && response.productList[i].attributes!=''){
	        			attributes+=response.productList[i].attributes;
	        		}
	        		html+="<tr for='"+response.productList[i].id+"'><td width='300'>"+response.productList[i].name+"</td>"
						  +"<td width='300'>"+response.productList[i].firstProductTypeName+"-"
						  +response.productList[i].secondProductTypeName
						  +thirdProductTypeName+"</td>"
						  +"<td width='300'>"+response.productList[i].brandName+"</td>"
						  +"<td width='300'>"+attributes+"</td></tr>";
	        	}
	            $("#tab").html(html);
	            /*回到选择产品*/
	            $('.conceal').slideDown(30);
				$("#choice").addClass('hide');
				$('#info label').text('重新选择你产品信息');
				$('#specDiv').text('');
	        }
	    });
  }
  
  /*双击选择产品规格*/
  $(document).delegate('#tab tr',"dblclick",function(event) {
	    var warehouse=$("#warehouse").val();
	    if(warehouse==null || warehouse=='' || warehouse<=0){
	    	$("#promptModal .modal-body p").html("请选择仓库后操作!!!");
   		 	$("#promptModal").modal("show");
	    	return;
	    }
		var text = '';
		for ( var i = 0; i < $(this).find('td').length; i++) {
			text += $(this).find('td').eq(i).text() + ' | ';
		}
		var id=$(this).attr("for");
		$('.conceal').slideUp(30);
		$('#info label').text(text);
		$('#info').attr("for",id);
		$('#choice').removeClass('hide');
		$('#specDiv').css("display","block");
		 $.ajax({
		      type:"POST",
		      url :"../inventory/getInventoryByProductId",
		      data:{productId:id,warehouseId:warehouse},
		      //dataType:"json",
		      success:function(response){
		    	  $('#specDiv').html(response);
		      }
			 });
	});
  
  /*点击修改按钮*/
  $(document).delegate('.updateButton',"click",function(event) {
	  var id=$(this).attr("for");
	  modalAjax(id,'update');
	  /*$.ajax({
		  type:"POST",
		  url :"../inventory/getInventoryById",
		  data:{id:id},
		  //dataType:"json",
		  success:function(response){
			  $("#saveInvertoryForm").append('<input type="hidden" value="'+response.tbpi.id+'" name="id"/>');
		  }
	  });*/
  });
  
  /*批量定价模态对话框*/
  function modalBatchPriceAjax(){
		  var tbpInventoryIds = new Array();
		  $(".tbpInventoryIds:checked").each(function(index,element){
			  tbpInventoryIds[index]=$(this).val();
		  });
		  /*未有选中的行提示先选中再批量定价*/
		  if(tbpInventoryIds.length==0){
			  $("#promptModal .modal-body p").html("请选择库存后再批量定价!!!");
	 		 	$("#promptModal").modal("show");
	 		 	return;
		  }
		  $(".cleanInfo").val("");
	      $("#modal2").modal("show");
   }
  /*批量定价保存*/
  function batchPriceSubmit(){
	  var asPrice=$("input[name='asPrice']:checked").val();
	  if(asPrice==null || typeof(asPrice) == "undefined" || asPrice==''){
		    alert("请选择按 入库价格/零售价格 批量定价!!!");
		 	return;
	  }
	  var priceMethod=$("input[name='priceMethod"+asPrice+"']:checked").val();
	  if(priceMethod==null || typeof(priceMethod) == "undefined" || priceMethod==''){
		  alert("选择按 比例/单价 批量定价!!!");
		  return;
	  }
	  var price=$("input[name='method"+asPrice+"Price"+priceMethod+"']").val();
	  if(price==null || typeof(price) == "undefined" || price==''){
		  alert("请填写批量定价的数值!!!");
		  return;
	  }else if(isNaN(price)){
		  alert("请正确填写批量定价的数值!!!");
		  return ;
	  }
	  var tbpInventoryIds='';
	  $(".tbpInventoryIds:checked").each(function(index,element){
		  if(index>0){
			  tbpInventoryIds+=",";
		  }
		  tbpInventoryIds+=$(this).val();
	  });
	  $.ajax({
		  type:"POST",
		  url :"../inventory/batchUpdatePrice",
		  data:{inventoryIds:tbpInventoryIds
			  		,asPrice:asPrice
			  		,priceMethod:priceMethod
			  		,price:price},
		  success:function(response){
			  $("#modal2").modal("hide");
			  /*重新刷新列表*/
			  submitform();
		  }
	  });
  }
  /*全选全不选*/
  $(document).delegate("#tbpInventoryCheckbox",'click',function(){
	  $(".tbpInventoryIds").prop("checked",this.checked); 
	}); 
//设置全选复选框 
  $(document).delegate(".tbpInventoryIds",'click',function(){ 
	    allchk(); 
	});
  /*复选功能*/
  function allchk(){ 
	    var chknum = $(".tbpInventoryIds").size();//选项总个数 
	    var chk = 0; 
	    $(".tbpInventoryIds").each(function () {   
	        if($(this).prop("checked")==true){ 
	            chk++; 
	        } 
	    }); 
	    if(chknum==chk){//全选 
	        $("#tbpInventoryCheckbox").prop("checked",true); 
	    }else{//不全选 
	        $("#tbpInventoryCheckbox").prop("checked",false); 
	    } 
	}
  /**/
	$(function() {
		$('#choice').click(function(event) {
			$('.conceal').slideDown(30);
			$(this).addClass('hide');
			$('#info label').text('重新选择你产品信息');
			$('#info').attr("for","");
			$('#specDiv').text('');
		});
		$('.one-head .one-title').click(function(event) {
		      $(this).siblings('.two-title').show();
		      $(this).parent('.one-head').siblings('.one-head').children('.two-title').hide();
		 });
	});
  