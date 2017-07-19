//删除用户组
function deleteUsergroup(id,version)
{
    
    if(confirm("您确定要删除此用户组吗？"))
    {
        $.ajax({
            type: "POST",
            url: "../usergroup/delete",
            data: "id="+id+"&version="+version,
            success: function(response){
                searchGroup('usergroupList');
                alert(response);
                
            }
        })
    }
}

//显示详细信息
function onShowUsergroupDetail(id){
    $.ajax({
       type: "POST",
       url: "../usergroup/showDetail",
       data: "&id=" + id,
       success: function(response){
           $("#editDialog").html(response);
           $("#dialog").dialog("open");
       }
    });
}

//搜索用户组
function searchGroup(id) {
    var param = $.trim($("#usergroupQueryFrm #usergroupname").val());
    $.ajax({
        type : "POST",
        url : "../usergroup/searchResult",
        data : "name="+param,
        success : function(response) {
            $("#" + id).html(response);
               initTableList("usergroupTable");
        }
    });
}
//编辑用户组信息
function editUsergroupInfo(id){
  $.ajax({
     type: "POST",
     url: "../usergroup/select",
     data: "id="+id,
     success: function(response){
         $("#editDialog").html(response);
         $("#dialog").dialog("open");
     }
  });
}
//保存提交
function onSubmitUsergroup(){
    if($("input[name='roleidss']:checked").length==0) {
        alert("请至少选择一个角色");
        return false;
    }
    $.ajax({
           type: "POST",
           url: "../usergroup/update",
           data: $("#editUsergroupFrm").serialize(),
           success: function(response){
                alert(response);
                closeDialog();
                searchGroup('usergroupList');
           },
           error:function(res){
               alert("数据保存失败!");
           }
    });
}
//关闭编辑面板
function closeDialog(){
    $("#dialog").dialog("close");
}


function initRoleidCheck()
{
    var roleidstr = $("#roleidValues").val();
    var roleidList = roleidstr.split(",");
    for(var i=0;i<roleidList.length;i++){
        var roleid = roleidList[i];
        $(":checkbox[name=roleidss][value="+roleid+"]").attr("checked","checked");
    }
}


