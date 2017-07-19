// * 数据字典管理

$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		resizable: "false",
		draggable: "false",
		width: "auto",
		dialogClass:" upwinBig200 "
	});
});


/**
 * 首页，上一页，下一页，尾页
 */
function onQuery(pageNumber, currentPage, pageCount){
	
	//已经是第一页，再点上一页，或已经是最后一页，再点尾页
	if (pageNumber == 0 || pageNumber > pageCount) {
		return;
	}

	//已经是第一页，再点首页，或已经是最后一页，再点尾页
	if (pageNumber == currentPage) {
		return;
	}
	
	var params = $("#dictSearchForm").serialize() ;//$("form[name=cxlActionForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "searchResult",
		   data: params + "&currentPage=" + pageNumber,
		   success: function(response){
			   $("#dictList").html(response);
			   initTableListSort("column");
		   }
		  });
}

/**
 * 点详细
 */
function onShowDetail(id){
	$.ajax({
		   type: "POST",
		   url: "showDetail",
		   data: "&id=" + id,
		   success: function(response){
			   $("#dictDetail").html(response);
			   $("#dialog").dialog("open");
		   }
		  });
}
/**
 * 点删除
 */
function onDelete(id, version){
	if(confirm("确定要删除这条数据？"))
	$.ajax({
		   type: "POST",
		   url: "delete",
		   data: "id=" + id + "&version=" + version,
		   success: function(response){
		   		if(response.length<20){
				    if(response.toString().indexOf("成功")>0){
				    	onSearch('dictList');
				    } else {
				    	alert("操作失败");
				    }
			    }else{
			    	alert("请重新登陆");
			    }
		   }
		  });
}

/**
 * 点检索
 */
function onSearch(id) {
	var params = $("#dictSearchForm").serialize();// $("form[name=cxlActionForm]").serialize();
	$.ajax({
		type : "POST",
		url : "searchResult",
		data : params,
		success : function(response) {
			$("#" + id).html(response);
			initTableListSort("column");
		}
	});
}

/**
 * 修改字典数据信息
 * @param id
 */
function onUpdate(id , version){
	$("form")[1].action = "select";
	$("#id").val(id);			// 把参数值写入id为'id'的hidden项
	$("#version").val(version);	// 把参数值写入id为'version'的hidden项
	$("form")[1].submit();
}

/**
 * 启用/禁用
 * @param id
 */
function onValid(id , version, status)
{
    var message = "您确定启用此数据吗？";
    if(status == 1)
    {
        message = "您确定禁用此数据吗？";
    }
    if(confirm(message))
    {
        $.ajax({
            type: "POST",
            url: "isValid",
            data: "id="+id+"&version="+version+"&status="+status,
            success: function(response){
			    if(response.toString().indexOf("成功")>0){
			    	onSearch('dictList');
			    } else {
			    	alert(response);
			    }
            }
        });
    }
}



/**
 * 
 * dictUpdate
 */
function onReturn(){
    $("#editFrm").attr("action","search");     // 修改表单form的action地址
    $("#editFrm").submit();     // 表单提交
}

//保存提交
function onSubmitDict(){
    $.ajax({
           type: "POST",
           url: "update",
           data: $("#editFrm").serialize(),
           success: function(response){
               if(response.length<30){
                   var res = response.split("::");
                   alert(res[0]);
                   if(res[0].indexOf("成功")>0){
                       if($("#id").val() == "")
                       {
                           $("#id").val(res[1])
                           $("#version").val(res[2]);
                       }
                       else
                       {
                           $("#version").val(parseInt($("#version").val())+1);
                       }
                   }
               }else{
                   alert("您的页面太长时间没有操作，请重新登陆");
               }
//             if(response.toString().indexOf("成功")>0){
//                 onReturn();
//             }
           },
           error:function(res){
               alert("数据保存失败!");
           }
    });
}

//关闭编辑面板
function closeDialog()
{
    $("#dialog").dialog("close");
}
