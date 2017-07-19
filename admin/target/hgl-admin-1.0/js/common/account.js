//*******************************************************************************
// * 物业（小区）管理
// *
// * @author cxl
// * @version 1.0
//*******************************************************************************

$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		resizable: "false",
		draggable: "false",
		width: "auto"
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
	
	var params = $("form").serialize() ;//$("form[name=cxlActionForm]").serialize();
	$.ajax({
		   type: "POST",
		   url: "searchResult",
		   data: params + "&currentPage=" + pageNumber,
		   success: function(response){
			   $("#zchk11Account").html(response);
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
		   data: "&userId=" + id,
		   success: function(response){
			   $("#zchk11AccountDetail").html(response);
			   $("#dialog").dialog("open");
		   }
		  });
}
/**
 * 点删除
 */
function onDelete(userId, version){
	if(confirm("确定要删除这条数据？"))
	$.ajax({
		   type: "POST",
		   url: "delete",
		   data: "userId=" + userId + "&version=" + version,
		   success: function(response){
			   $("#zchk11Account").html(response);
			   initTableList("column");
		   }
		  });
}

/**
 * 点检索
 */
function onSearch(id) {
	var params = $("form").serialize();// $("form[name=cxlActionForm]").serialize();
	$.ajax({
		type : "POST",
		url : "searchResult",
		data : params,
		success : function(response) {
			$("#" + id).html(response);
			   initTableList("column");
		}
	});
}

