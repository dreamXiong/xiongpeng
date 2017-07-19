
/**
 * 点详细
 */
function onShowDetail(id){
	$.ajax({
		   type: "POST",
		   url: "../roles/showDetail",
		   data: "id=" + id,
		   success: function(response){
			   $("#editDialog").html(response);
			   $("#dialog").dialog("open");
		   }
		  });
}
/**
 * 点删除
 */
function onDeleteRole(roleid, version){
	if(confirm("确定要删除这条数据？"))
	$.ajax({
		   type: "POST",
		   url: "../roles/delete",
		   data: "id=" + roleid + "&version=" + version,
		   success: function(response){
        	    alert(response);
                searchRole('rolelist');
		   }
		  });
}

/**
 * 修改角色信息
 * @param id
 */
function onUpdate(id , version){
    $("form")[1].action = "../roles/select";
    $("#id").val(id);           // 把参数值写入id为'id'的hidden项
    $("#version").val(version); // 把参数值写入id为'version'的hidden项
    $("form")[1].submit();
}

//搜索角色
function searchRole(id)
{
    var param = $.trim($("#roleQueryFrm #rolename").val());
    $.ajax({
        type : "POST",
        url : "../roles/searchResult",
        data : "name="+param,
        success : function(response) {
            $("#" + id).html(response);
               initTableList("roleTable");
        }
    });
}
function onUpdateRole(id,version)
{
    var data = "id="+id+"&version="+version;
    showRoleEditPanel(data);
}
//显示编辑面板
function showRoleEditPanel(id)
{
    $.ajax({
        type: "POST",
        url: "../roles/select",
        data: "id="+id,
        success: function(response){
            $("#editDialog").html(response);
            $("#dialog").dialog("open");
        }
     });
}
//保存提交
function onSubmitRole(){
    if($("input[name='actionidstemp']:checked").length==0) {
        alert("请至少选择一个动作");
        return false;
    }
    $.ajax({
           type: "POST",
           url: "../roles/update",
           data: $("#editRoleFrm").serialize(),
           success: function(response){
        	   if(response.length<20){
	               alert(response);
				   if(response.toString().indexOf("成功")>0){
					   closeDialog();
					   searchRole('rolelist');
				   }
        	   }else{
        		   alert("请重新登陆");
        	   }
           },
           error:function(res){
               alert("数据保存失败!");
           }
    });
}


/**
 * 
 * roleUpdate
 */
function selectAction(actionidstr)
{
    var actionidList = actionidstr.split(",");
    for(var i=0;i<actionidList.length;i++){
        var actionid = actionidList[i];
        $(":checkbox[name=actionidstemp][value="+actionid+"]").attr("checked",true);
    }
    
}

//得到所有的actionid并选中相应的action
function initActionidstr()
{
    var actionidstr = $("#actionids").val();
    if(actionidstr!="")
    {
        selectAction(actionidstr);
    }
}
//重置选中action
function resetAction()
{
    $(":checkbox[name=actionidstemp]").attr("checked",false);
    initActionidstr();
}


//在“动作”树中关联子节点
function onClickAction(id,pId)
{
    selectChild(id);
    checkParent(pId);
}

//关联父节点
function checkParent(pId)
{
    var pNode = $(":checkbox[name=actionidstemp][id="+pId+"]");
    if($(":checkbox[name=actionidstemp][tabIndex="+pId+"][checked=true]").length<=0)
    {
        pNode.attr("checked",false);
    }
    else
    {
        pNode.attr("checked",true);
    }
    if(pNode.attr("tabIndex") != null)
    {
        checkParent(pNode.attr("tabIndex"));
    }

}

//关联子节点
function selectChild(id)
{
    var actionidList = $(":checkbox[name=actionidstemp][tabIndex="+id+"]");
    if($("#"+id)[0].checked){
        actionidList.attr("checked",true);
    }else{
        actionidList.attr("checked",false);
    }
    for(var i=0;i<actionidList.length;i++){
        var actionid = actionidList[i];
        selectChild(actionid.id);
    }
}
