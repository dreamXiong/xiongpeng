//*******************************************************************************
// * 日志管理
// *
// * @author Nick.Peng
// * @version 1.0
//*******************************************************************************

/**
 * 弹出层
 */
$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		resizable: "false",
		draggable: "false",
		width: "auto"
	});
});

/**
 * jquery日期控件
 * 绑定到datepicker
 */
jQuery(function($) {
	$('#datepicker').datepicker({
		yearRange : '1900:2099', // 取值范围.
		showOn : 'both', // 输入框和图片按钮都可以使用日历控件。
		buttonImage :  "../styles/images/calendar.gif", // 日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});

});

/**
 * jquery日期控件
 * 绑定到datepicker1
 */
jQuery(function($) {
	$('#datepicker1').datepicker({
		yearRange : '1900:2099', // 取值范围.
		showOn : 'both', // 输入框和图片按钮都可以使用日历控件。
		buttonImage :  "../styles/images/calendar.gif", // 日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
});


/**
 * 搜索日志
 */
function searchLog(id)
{
    var params = $("#logSearchForm").serialize();
    $.ajax({
        type : "POST",
        url : "../log/searchResult",
        data : params,
        success : function(response) {
            $("#" + id).html(response);
               //initTableListSort("logTable");
        }
    });
}

/**
 * 点详细
 */
function onFormSubmit(){
	var val =  $("#userName").val();
	if(val=='' || val.length < 3 ){
		alert("请输入至少3位字符！");
		return;
	}
	$("form")[0].action = "search"; // 修改表单form的action地址
	$("form")[0].submit();			// 表单提交
}


function onShowDetail(id){
	$.ajax({
		   type: "POST",
		   url: "showDetail",
		   data: "&logId=" + id,
		   success: function(response){
			   $("#zchk11LogDetail").html(response);
			   $("#dialog").dialog("open");
		   }
		  });
}

/**
 * 点删除
 */
function onDelete(roleid, version){
	if(confirm("确定要删除这条数据？"))
	$.ajax({
		   type: "POST",
		   url: "delete",
		   data: "roleid=" + roleid + "&version=" + version,
		   success: function(response){
			   $("#zchk11RoleList").html(response);
			   //initTableList("column");
		   }
		  });
}