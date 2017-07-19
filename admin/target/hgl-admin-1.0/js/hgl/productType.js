function validateProductTypesRepeat(){
	$("#productType01").removeClass("errorStyleDiv");
	$("#errorText").text("");
}
function goProductTypeAddPage(path){
	var mainId = $("#mainId").val();
	 window.location.href="goProductTypeAddPage?pagePath="+path+"&mainId="+mainId;
}

function goAddThirdProductTypePage(){
	var mainId = $("#mainId").val();
	var parentId = $("#parentId").val();
	 window.location.href="goAddThirdProductTypePage?parentId="+parentId+"&mainId="+mainId;
}
function goBackIndex(){
	 window.location.href="../productType/index";
}
/*修改三级分类*/
function updateThirdProductType(id){
	 window.location.href="updateThirdProductTypePage?id="+id;
}
/*添加时离焦校验*/
var addValidateType=false;
function addValidateProductType(){
	var name = $("#productType01").val();
	if(name==null || ''==name.trim()){
		$("#productType01").addClass("errorStyleDiv");
		return;
		}
	$.ajax({
        type: "POST",
        url: "../productType/addValidateProductType",
        data: "name="+name,
        success: function(response){
            if(response.code == '0'){
            	$("#productType01").removeClass("errorStyleDiv");
            	$("#errorText").text("");
            	addValidateType = true;
            }else{
            	$("#productType01").addClass("errorStyleDiv");
            	$("#errorText").text("产品大类重复！");
            	addValidateType = false;
            }
        }
    });
}

/*更新时离焦校验*/
var updateValidateType=true;
function updateValidateProductType(id){
	var name = $("#productType01").val();
	if(name==null || ''==name.trim()){
		$("#productType01").addClass("errorStyleDiv");
		return;
		}
	$.ajax({
        type: "POST",
        url: "../productType/updateValidateProductType",
        data: "name="+name+"&id="+id,
        async: false,
        success: function(response){
            if(response.code == '0'){
            	$("#productType01").removeClass("errorStyleDiv");
            	$("#errorText").text("");
            	updateValidateType = true;
            }else{
            	$("#productType01").addClass("errorStyleDiv");
            	$("#errorText").text("分类名称重复！");
            	updateValidateType = false;
            }
        }
    });
}
function updateFirstProductType(id){
	 window.location.href="updateProductType?id="+id;
}

function updateSecondProductType (id){
	 window.location.href="updateSecondProductType?id="+id;
}

/*function resetProductType(){
	$("#productType01").val("");
	$("#productType02").val("");
	$("#productType01").removeClass("errorStyleDiv");
	$("#errorText").text("");
}*/
/**********************************************大类操作JS************************************************************/
/*大类新增提交时校验*/
function submitAddFirstProductType(){
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	if(!addValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("产品大类重复！");
		return;
	}
	$("#addFirstProductType").submit();
}
/*大类更新提交时校验*/
function submitUpdateFirstProductType(id){
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	updateValidateProductType(id);
	if(!updateValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("产品大类重复！");
		return;
	}
	$("#updateFirstProductType").submit();
}
/**********************************************二级分类操作JS************************************************************/
/*二级分类新增提交时校验*/
function submitAddSecondProductType(){
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	addValidateProductType();
	if(!addValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("产品大类重复！");
		return;
	}
	$("#addSecondProductType").submit();
}

/*******修改二级分类时提示框，该二级分类下的所有子分类会联动修改*************/
function promptUpdateSecondProductType(id){
	updateValidateProductType(id);
	if(!updateValidateType){
		return;
	}
	$("#updateSecond").modal("show");
}

/*二级类更新提交时校验*/
function submitUpdateSecondProductType(){
	var id = $("#secondId").val();
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	updateValidateProductType(id);
	if(!updateValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("产品大类重复！");
		return;
	}
	$("#updateSecondProductType").submit();
}

/**********************************************三级分类操作JS************************************************************/
/*三级分类新增提交时校验*/
function submitAddThirdProductType(){
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	addValidateProductType();
	if(!addValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("分类已存在！");
		return;
	}
	$("#addThirdProductType").submit();
}
/*三级类更新提交时校验*/
function subUpdateThirdProductType(id){
	var name = $("#productType01").val();
	var secondPype = $("#language_tm2009").val();
	if(secondPype==null || ''==secondPype.trim()){
		$("#language_tm2009").addClass("errorStyleDiv");
	}
	if(name==null || ''==name.trim()){
		$("#productType01").addClass("errorStyleDiv"); 
		$("#errorText").text("");
	}
	if(name==null || ''==name.trim() || secondPype==null || ''==secondPype.trim()){
		return;
		}
	updateValidateProductType(id);
	if(!updateValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("分类名称重复！");
		return;
	}
	$("#subUpdateThirdProductType").submit();
}
/* 三级分类时，二级下拉联动*/
function linkageProductType(){
	var id = $("#mainProductTypeId").find("option:selected").val();
	 $.ajax({
         type:"POST",
         url :"../productType/linkageProductType",
         data:{
             id:id
         },
         dataType:"json",
         success:function(response){
             $("#language_tm2009").empty();
             $("#language_tm2009").append("<option value=''>该产品大类暂无二级分类</option>");
            var len = response.sList.length;
            if(len > 0){
            	$("#language_tm2009").empty();
            	for(var i=0; i<len; i++){
            		 $("#language_tm2009").append( "<option value='"+ response.sList[i].id+"'>"+response.sList[i].name+"</option>");
            	}
            }
         }
     });
}
/***************************************主页面联动显示******************************************************/
function firstProductType(id){
	$("#mainId").val(id);
	 $.ajax({
         type:"POST",
         url :"../productType/linkageMainPage",
         data:{
             id:id
         },
         dataType:"json",
         success:function(response){
        	 $("#secondForAjax").children("li").not(":first").remove(); 
        	 var len = response.secondList.length;
        	 if(len > 0){
        		 $("#secondForAjax").children("li").not(":first").remove(); 
             	for(var i=0; i<len; i++){
             	$("#secondForAjax").append( "<li class='clearfix' onclick='scoendProductType("+response.secondList[i].id+");'>"
             									+"<div class='pull-left'>"
             										+"<a>"+response.secondList[i].name +"</a>"
             									+"</div>"
             									+ "<div class='pull-right'>"
	             									+"<a onclick='updateSecondProductType("+response.secondList[i].id+");'>修改</a>"
	             									+"<a onclick='deleteSecondProductType("+response.secondList[i].id+");'>删除</a>"
	             								+"</div>"
             									+"</li>");
             	}
             	$("#parentId").val(response.secondList[0].id);
             	$("#thirdForAjax").show();
             }
        	 $("#thirdForAjax").children("li").not(":first").remove();
        	  if(len == 0){
        		  $("#thirdForAjax").hide();
        		  return;
        	  }
          	var tLen = response.thirdList.length;
          	if(tLen > 0){
          		 $("#thirdForAjax").show();
          		for(var i=0; i<tLen; i++){
	          		$("#thirdForAjax").append( "<li class='clearfix'>"
	          									+"<div class='pull-left'>"
	          										+"<a>"+response.thirdList[i].name+"</a>"
	          									+"</div>"
	          									+ "<div class='pull-right'>"
		          									+"<a onclick='updateThirdProductType("+response.thirdList[i].id+");'>修改</a>"
		          									+"<a onclick='deleteThirdProductType("+response.thirdList[i].id+");'>删除</a>"
	          									+"</div>"
	          									+"</li>");
              	}
          	}/*else{
          		 $("#thirdForAjax").hide();
          	}*/
          	
         }
     });
}
function scoendProductType(id){
	$("#parentId").val(id);
	 $.ajax({
        type:"POST",
        url :"../productType/linkageMainPageSecond",
        data:{
            id:id
        },
        dataType:"json",
        success:function(response){
        	 $("#thirdForAjax").children("li").not(":first").remove();
           	var tLen = response.thirdList.length;
           	if(tLen > 0){
           		 $("#thirdForAjax").show();
           		for(var i=0; i<tLen; i++){
           		$("#thirdForAjax").append( "<li class='clearfix'>"
           									+"<div class='pull-left'>"
	           									+"<a>"+response.thirdList[i].name+"</a>"
	           							    +"</div>"
	           							    + "<div class='pull-right'>"
	           									+"<a onclick='updateThirdProductType("+response.thirdList[i].id+");'>修改</a>"
	           									+"<a onclick='deleteThirdProductType("+response.thirdList[i].id+");'>删除</a>"
	           								+"</div>"
           									+"</li>");
               	}
           		}
        	}
	 });
}
/**********************************分类删除操作校验*********************************************/
function deleteFirstProductType(id){
	 $("#deleteId").val(id);
	 $.ajax({
		    type: "POST",
		    url: "../productType/deleteFirstProductType",
		    data: "id="+id,
		    success: function(response){
		    	if(response.code == '0'){
		    		$("#deleteFirst").modal("show");
		        }else{
		        	$("#notDeleteFirst").modal("show");
		     }
		    }
		});
	}

/************************二级分类删除操作校验************************************/
function deleteSecondProductType(id){
	 $("#deleteId").val(id);
	 $.ajax({
	        type: "POST",
	        url: "../productType/deleteSecondProductType",
	        data: "id="+id,
	        success: function(response){
	        	  if(response.code == '0'){
	        		  $("#deleteSecond").modal("show");
		            }else{
		            	$("#notDeleteFirst").modal("show");
		         }
	        }
	    });
	}
/************************二级分类删除操作校验************************************/
function deleteThirdProductType(id){
	 $("#deleteId").val(id);
	 $.ajax({
	        type: "POST",
	        url: "../productType/deleteThirdProductType",
	        data: "id="+id,
	        success: function(response){
	            if(response.code == '0'){
	            	 $("#deleteThird").modal("show");
	            }else{
	            	$("#notDeleteFirst").modal("show");
	            }
	        }
	    });
	}
/*确认删除操作*/
function goDeleteProductType(){
	var id =  $("#deleteId").val();
		 $.ajax({
		    type: "POST",
		    url: "../productType/configDeleteProductType",
		    data: "id="+id,
		    success: function(response){
		    	window.location.reload();
	    }
	});
}

$(function() {
    var num=null;
    var oH  = $('.fil .one').height();
    var tH =$('.fil .two').height();
    var thH =$('.fil .three').height();
    var arr=[oH,tH,thH];
    function height(){
    for(var i=0;i<arr.length;i++){
      if(arr[i]-arr[i+1]>0){
      return arr[i];
        }
      }
    }
    $('.fil .one').height(height())
  });