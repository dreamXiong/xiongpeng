
function updateServiceType(id){
	 window.location.href="updateServiceType?id="+id;
}
function goServiceTypeAddPage(){
	 window.location.href="goServiceTypeAddPage";
}
function goBackServiceIndex(){
	 window.location.href="../serviceType/index";
}

function submitAddFirstProductType(){
	var name = $("#productType01").val();
	if(name==null || name.trim()==''){
		$("#productType01").addClass("errorStyleDiv");
		$("#errorText").text("");
		return;
		}
	if(!addValidateType){
		$("#productType01").addClass("errorStyleDiv");
    	$("#errorText").text("服务类型重复！");
		return;
	}
	$("#addFirstProductType").submit();
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
        url: "../serviceType/addValidateProductType",
        data: "name="+name,
        async: false,
        success: function(response){
            if(response.code == '0'){
            	$("#productType01").removeClass("errorStyleDiv");
            	$("#errorText").text("");
            	addValidateType = true;
            }else{
            	$("#productType01").addClass("errorStyleDiv");
            	$("#errorText").text("服务类型重复！");
            	addValidateType = false;
            }
        }
    });
}
function deleteFirstServiceType(id){
	$("#deleteId").val(id);
	 $.ajax({
		    type: "POST",
		    url: "../serviceType/deleteFirstServiceType",
		    data: "id="+id,
		    async: false,
		    success: function(response){
		    	if(response.code == '0'){
		    		$("#deleteFirst").modal("show");
		        }else{
		        	$("#notDeleteFirst").modal("show");
		     }
		    }
		});
	}

function validateProductTypesRepeat(){
	$("#productType01").removeClass("errorStyleDiv");
	$("#errorText").text("");
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
    	$("#errorText").text("服务类型重复！");
		return;
	}
	$("#updateFirstProductType").submit();
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
        url: "../serviceType/updateValidateProductType",
        data: "name="+name+"&id="+id,
        async: false,
        success: function(response){
            if(response.code == '0'){
            	$("#productType01").removeClass("errorStyleDiv");
            	$("#errorText").text("");
            	updateValidateType = true;
            }else{
            	$("#productType01").addClass("errorStyleDiv");
            	$("#errorText").text("服务类型重复！");
            	updateValidateType = false;
            }
        }
    });
}

function goSecondServiceTypeAddPage(){
	var mainId = $("#mainId").val();
	 window.location.href="goSecondServiceTypeAddPage?mainId="+mainId;
}
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
    	$("#errorText").text("服务类型重复！");
		return;
	}
	$("#addSecondProductType").submit();
}

/************************二级分类删除操作校验************************************/
function deleteSecondServiceType(id){
	 $("#deleteId").val(id);
	 $.ajax({
	        type: "POST",
	        url: "../serviceType/deleteSecondServiceType",
	        data: "id="+id,
	        async: false,
	        success: function(response){
	        	  if(response.code == '0'){
	        		  $("#deleteSecond").modal("show");
		            }else{
		              $("#notDeleteFirst").modal("show");
		         }
	        }
	    });
	}
function updateSecondServiceType(id){
	 window.location.href="updateSecondServiceType?id="+id;
}
/*二级类更新提交时校验*/
function submitUpdateSecondServiceType(){
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
    	$("#errorText").text("服务类型重复！");
		return;
	}
	$("#updateSecondServiceType").submit();
}

/*确认删除操作*/
function configDeleteServiceType(){
	var id =  $("#deleteId").val();
		 $.ajax({
		    type: "POST",
		    url: "../serviceType/configDeleteServiceType",
		    data: "id="+id,
		    success: function(response){
		    	window.location.reload();
	    }
	});
}

function serviceType(id){
	$("#mainId").val(id);
	 $.ajax({
         type:"POST",
         url :"../serviceType/linkageMainPage",
         data:{
             id:id
         },
         success:function(response){
        	 $("#serviceTypeListInfi").html(response);
        	 }
	 	});
	 }
