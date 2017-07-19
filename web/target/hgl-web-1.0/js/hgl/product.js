$(function() {
	$('tbody tr:odd').addClass('odd');
	$('#key_product_list tbody tr:odd').addClass('odd');
	$("#dialog1").dialog({            
   		autoOpen: false ,
   		resizable: false,
   		draggable :false,
   		modal: true,
   		width:'500',     
   		closeText:'关闭',
   		buttons:{        
   		  关闭:function(){   
   		    $(this).dialog("close");
   		  },
   		  确定:function(){
   			 var parentType = $("#dialog1 #parentType").val();
   			 var thirdType = $("#dialog1 #thirdType").val();
   			 if(parentType == 0 && thirdType == 0){
   				 $("#dialog4").html('分类不能为空');
   			     $("#dialog4").dialog('open'); 
   			     return;
   			 }
   			 if($("#dialog1 #tbBrandList").val() == 0){
   				 $("#dialog4").html('品牌不能为空');
   			     $("#dialog4").dialog('open'); 
   			     return;
   			 }
   			 if($("#dialog1 #name").val() == 0){
   				 $("#dialog4").html('名称不能为空');
   			     $("#dialog4").dialog('open'); 
   			     return;
   			 }
   			 if($("#dialog1 #price").val() == 0){
   				 $("#dialog4").html('价格不能为空');
   			     $("#dialog4").dialog('open');
   			     return;
   			 }else{
					if(isNaN($("#dialog1 #price").val())){
						 $("#dialog4").html('价格必须是数值');
		   			     $("#dialog4").dialog('open');
		   			     return;
					}
     		  }
   			 if($("#dialog1 #meterageunit").val() == 0){
   				 $("#dialog4").html('计量单位不能为空');
   			     $("#dialog4").dialog('open'); 
   			     return;
   			 }
   			if($("#dialog1 #updateId").val() != ''){       
   				var pimgOne = $("#dialog1 #Uinput_val").val();
   				var pimgtwo = $("#dialog1 #Uinput1_val").val();
   				var pimgthree = $("#dialog1 #Uinput2_val").val();
   				if(pimgOne=='' && pimgtwo=='' && pimgthree==''){
   					$("#dialog4").html('产品图片必须上传一张');
   					$("#dialog4").dialog('open'); 
   					return;
   				}
			}else{
				 var pimgOne = $("#dialog1 #input_val").val();
	   			 var pimgtwo = $("#dialog1 #input1_val").val();
	   			 var pimgthree = $("#dialog1 #input2_val").val();
	   			 if(pimgOne=='' && pimgtwo=='' && pimgthree==''){
	   				 $("#dialog4").html('产品图片必须上传一张');
	   			     $("#dialog4").dialog('open'); 
	   			     return;
	   			 }
			}
   			 
   			$("#dialog1 #addMydiv input[name='attrInput']").each(function(){
   				if(this.value == ''){
   					$("#dialog4").html('附加属性不能为空');
   					$("#dialog4").dialog('open'); 
   					return;
   				}
   			});
   			 var attr = $("#dialog1 #addMydiv input[name='attrInput']");
   			 var len = attr.length;
   			 var attributeInfo = '';
   			 for(var i=0 ; i < len ;i++){
   				 if(len == (i+1)){
   					 attributeInfo += attr[i].value;    
   				 }else{
   					 attributeInfo += attr[i].value +"/";
   				 }
   			 }
   			 $("#dialog1 #attributes").val(attributeInfo);
 			  var params = $("#dialog1 #saveProductInfo").serialize();
 			  var url = 'saveProductInfo'; 
 			  if($("#dialog1 #updateId").val() != ''){       
 				 url = 'updateProduct';
 			  }
 			  $.ajax({
 		        type : "POST",
 		        url : url,
 		        data : params,
 		        success : function(response) {                           
 		        	window.location.reload();
 		        	 //window.location.href = window.location.href;
 		        }
 		     }); 
   		    $(this).dialog("close");
   		  },
   		},
	});
	
	$("#dialog3").dialog({            
   		title:'产品详情' ,  
   		autoOpen: false ,
   		resizable: false,  
   		draggable :false,
   		modal: true,
   		width:'500',     
   		closeText:'关闭',
   		buttons:{        
   		  关闭:function(){   
   		    $(this).dialog("close");
       		  }
       		},
   		});
 
	$("#dialog4").dialog({                
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
	
   		$(document).delegate('#add','click',function(){
   		 $.ajax({
 	        type : "POST",
 	        url : 'initializationInfo',
 	        success : function(response) {
 	            $("#dialog1").html(response);
 	            $("#dialog1").dialog('open'); 
 	            
 	       /*	new uploadPreview({ UpBtn: "input", DivShow: "imgdiv", ImgShow: "imgShow" });
			new uploadPreview({ UpBtn: "input1", DivShow: "imgdiv1", ImgShow: "imgShow1" });  
			new uploadPreview({ UpBtn: "input2", DivShow: "imgdiv2", ImgShow: "imgShow2" });
			new uploadPreview({ UpBtn: "input3", DivShow: "imgdiv3", ImgShow: "imgShow3" }); 
			new uploadPreview({ UpBtn: "input4", DivShow: "imgdiv4", ImgShow: "imgShow4" });
			new uploadPreview({ UpBtn: "input5", DivShow: "imgdiv5", ImgShow: "imgShow5" });*/
 	        }
 	     }); 
	});

//	$('.inp-short').delegate('.inp-close','click',function(){
//  		$(this).parent('div').remove();
//	});
//	
//	$('.inp-short').delegate('#addInput','click',function(){
//	
//	  var div= $('<div>' 
//	               +' <input type="text">'
//	                +'<span class="inp-close">&times;</span>'
//	              +'</div>');
//	  if($('.inp-short div').length<9){
//	    $(this).before(div);
//	  }   
//	});   
	     
//	new uploadPreview({ UpBtn: "input", DivShow: "imgdiv", ImgShow: "imgShow" });
//	new uploadPreview({ UpBtn: "input1", DivShow: "imgdiv1", ImgShow: "imgShow1" });  
//	new uploadPreview({ UpBtn: "input2", DivShow: "imgdiv2", ImgShow: "imgShow2" });
//	new uploadPreview({ UpBtn: "input3", DivShow: "imgdiv3", ImgShow: "imgShow3" }); 
//	new uploadPreview({ UpBtn: "input4", DivShow: "imgdiv4", ImgShow: "imgShow4" });
//	new uploadPreview({ UpBtn: "input5", DivShow: "imgdiv5", ImgShow: "imgShow5" });
		
	});

$(document).delegate(
		'#mainIdSelect',    
		'change',
		function() {
			var val = $(this).val();
			$.ajax({
				type : "POST",    
				url : "../productType/linkageMainPage",
				data : {
					id : val
				},
				dataType : "json",
				success : function(response) {
					/* 品牌 */
					$("#brandIdSelect").children("option").remove();
					$("#brandIdSelect").append(
							"<option value=''>-请选择-</option>");
					tLen = response.tbBrandList.length;
					if (tLen > 0) {
						for ( var i = 0; i < tLen; i++) {
							$("#brandIdSelect").append(
									"<option value='"
											+ response.tbBrandList[i].id + "'>"
											+ response.tbBrandList[i].name
											+ "</option>");
						}
					}
				}
			});
		});

function submitform(){
  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
    var frm = $("#" + frmId);
    var params=frm.serialize();
	$.ajax({
        type : "POST",
        url : 'serachProduct',
        data : params,
        success : function(response) {
            $("#" + dataDomId).html(response);
        }
    }); 
}

function modalAjax(id,operation){
	 $.ajax({
        type : "POST",
        url : 'showdetailsInfo?id='+id,
        data : {},
        success : function(response) {
           $('#dialog3').html(response);
           $('#dialog3').dialog('open');
        }
    });
}

function updateProduct(id){
	 $.ajax({
       type : "POST",
       url : 'showUpdateInfo?id='+id,  
       data : {},
       success : function(response) {
          $('#dialog1').html(response);
          $('#dialog1').dialog('open');
		
		/*new uploadPreview({ UpBtn: "Uinput", DivShow: "pImgOnedivUp", ImgShow: "pImgOneShowUp" });
  	    new uploadPreview({ UpBtn: "Uinput1", DivShow: "pImgTwodivUp", ImgShow: "pImgTwoShowUp" });
  		new uploadPreview({ UpBtn: "Uinput2", DivShow: "pImgThreedivUp", ImgShow: "pImgThreeShowUp" });
  	    new uploadPreview({ UpBtn: "Uinput3", DivShow: "dImgOnedivUp", ImgShow: "dImgOneShowUp"});
        new uploadPreview({ UpBtn: "Uinput4", DivShow: "dImgTwodivUp", ImgShow: "dImgTwoShowUp"});
        new uploadPreview({ UpBtn: "Uinput5", DivShow: "dImgThreedivUp", ImgShow: "dImgThreeShowUp"});*/
       }
   });
}

//编辑库存
function submitInventory(productId){
	$("#submitInventory #productId").val(productId);
	$("#submitInventory").submit();
}

/*点击大分类时，去查找二级分类*/
function selectParent(){
	 var maindId = $("#mainIdForAddPage option:selected").val();
	 $.ajax({
	        type:"POST",
	        url :"../productType/linkageMainPageProduct",
	        data:{
	            id:maindId
	        },
	        dataType:"json",
	        success: function(response){
	        	$("#parentType").children("option").remove();
	             $("#parentType").append( "<option value='0'>--请选择--</option>");
	        	if(response.thirdList != null){
	        	 var tLen = response.secondList.length;
 	           		for(var i=0; i<tLen; i++){
 		           		$("#parentType").append( "<option value='"+response.secondList[i].id+"'>"+response.secondList[i].name+"</option>");
 	           		}
	        	}
	        	if(response.tbBrandList != null){
 	           	 var bLen = response.tbBrandList.length;
 	           	   for(var i=0; i<bLen; i++){
		           		$("#tbBrandList").append( "<option value='"+response.tbBrandList[i].id+"'>"+response.tbBrandList[i].name+"</option>");
	           	   }
	        	}
 	        }      
	 });
}

/*点击二级分类时，去查找三级分类*/
function selectThirdType(){
	var parentType = $("#parentType option:selected").val();
	
	 $.ajax({
	        type:"POST",
	        url :"../productType/linkageMainPageSecond",
	        data:{
	            id:parentType
	        },
	        dataType:"json",
	        success:function(response){
	        	 $("#thirdType").children("option").remove();
	        	$("#thirdType").append( "<option value='0'>--请选择--</option>");
	        	 var sLen = response.thirdList.length;
	           	   for(var i=0; i<sLen; i++){
		           		$("#thirdType").append( "<option value='"+response.thirdList[i].id+"'>"+response.thirdList[i].name+"</option>");
	           	   }
	        }
		 });
}
