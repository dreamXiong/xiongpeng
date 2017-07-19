$(
    function()
    {
        $("#dialog").dialog({
            autoOpen: false,
            resizable: "false",
            draggable: "false",
        	width: "704px",
        	dialogClass:" upwinBig320 "
        });
    }
);

//搜索
function searchUser(id) {
    var param = $.trim($("#userQueryFrm #username").val());
    $.ajax({
        type : "POST",
        url : "../users/searchResult",
        data : "name="+param,
        success : function(response) {
            $("#" + id).html(response);
               initTableListSort("userTable");
        }
    });
}

//编辑用户信息
function editUserInfo(id,version){
  $.ajax({
     type: "POST",
     url: "../users/select",
     data: "id="+id+"&version="+version,
     success: function(response){
         $("#editDialog").html(response);
         $("#dialog").dialog("open");
     }
  });
}

//弹出用户详细信息
function showUserInfo(id){
    $.ajax({
       type: "POST",
       url: "../users/showDetail",
       data: "id=" + id,
       success: function(response){
           $("#editDialog").html(response);
           $("#dialog").dialog("open");
       }
    });
}
//删除用户
function deleteUser(id,version)
{
    if(confirm("您确定要删除此用户吗？"))
    {
        $.ajax({
            type: "POST",
            url: "../users/delete",
            data: "id="+id+"&version="+(version+1),
            success: function(response)
            {
        	    alert(response);
        	    searchUser('userList');
			}
        });
    }
}

function enterUpdate(id,version,version1)
{
    $("form")[1].action = "../users/select";
    $("#userid").val(id);
    $("#version").val(version);
    $("#version1").val(version1);
    $("form")[1].submit();
}

//显示角色选项
function showRoleSelect()
{
  $(":checkbox[name=roleidss]").attr("disabled",true);
  var roleidstr=$("form select[name=groupid]").find("option:selected").attr("roleids");
  initSelectRole(roleidstr);
  $("#temproleids").val(roleidstr);
  $("#roleselectTr").css("display","");
}
// 订制角色
function makeRoleFunc()
{
    var roleChk = $(":checkbox[name=roleidss]");
    if($("#makeRole")[0].checked)
    {
        roleChk.attr("disabled",false);
        $("#groupid option[value='']")[0].selected = true;
        
    }
    else
    {
        roleChk.attr("disabled",true);
        roleChk.attr("checked",false);
        $("#groupid option[value="+$("#tempgroupid").val()+"]")[0].selected = true;
        var roleidstr = $("#temproleids").val();
        initSelectRole(roleidstr);
    }
}

function groupSelectChange()
{
    var groupid = $("form select[name=groupid]").find("option:selected").val();
    if(groupid=="0")
    {
        $("#makeRole").attr("checked",true);
        $(":checkbox[name=roleidss]").attr("disabled",false);
        $(":checkbox[name=roleidss]").attr("checked",false);
    }
    else
    {
        $("#makeRole").attr("checked",false);
        $(":checkbox[name=roleidss]").attr("disabled",true);
        $(":checkbox[name=roleidss]").attr("checked",false);
        var roleidstr = $("form select[name=groupid]").find("option:selected").attr("roleids");
        initSelectRole(roleidstr);
    }
}
// 初始化角色选项
function initSelectRole(roleidstr)
{
    var roleidList = roleidstr.split(",");
    for(var i=0;i<roleidList.length;i++){
        var roleid = roleidList[i];
        $(":checkbox[name=roleidss][value="+roleid+"]").attr("checked",true);
    }
}
// 保存提交
function onSubmitUser()
{
    var notSelectGroup = $("#groupid").val() == 0;
    var notSelectRole = $("input[name='roleidss']:checked").length==0;
    if(notSelectGroup && notSelectRole) {
        alert("请选择用户组或角色");
        return false;
    }
    if($("#type option[value='2']")[0].selected ==false && $("input[name='projectTypeIdArray']:checked").length==0) {
        alert("请至少选择一个工程类型");
        return false;
    }
    //检索是否有相同的项目名称
    if($("#isNewProjChk")[0].checked)
    {
        var pjname = $("#newprojectname").val();
        $.ajax({
            type: "POST",
            url: "../users/searchCommonProjectname",
            data: "newprojectname="+pjname,
            success: function(response){
                if(response.indexOf("已存在") > 0)
                {
                    var msg = response.split("::");
                    $("#newProjNameSpan").html("");
                    $("#projectid").css("display","");
                    $("#projectid").attr("class","select inputNotNull");
                    $("#projectid").attr("disabled",false);
                    $("#projectid option[value="+msg[1]+"]")[0].selected = true;
                    $("#isNewProjChk")[0].checked = false;
                    $("#tempNewProjectname").val(pjname);
                    alert(msg[0]);
                }
                else
                {
                    saveUser();
                }
            }
        });
    }
    else
    {
        saveUser();
    }
}

function saveUser()
{
    $.ajax({
        type: "POST",
        url: "../users/update",
        data: $("#editFrm").serialize(),
        success: function(response){
            if(response.length<20){
                alert(response);
                if(response.toString().indexOf("成功")>0){
                    closeDialog();
                    searchUser('userList');
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
// 关闭弹出层
function closeDialog(){
   $("#dialog").dialog("close");
}
function selectToLable()
{
    var selectArray=['groupid','type','address','projectclass','grade','logintype','enterpriseid','projectid','svorgid'];
    $.each(selectArray,function(key,val)
    {
        $("#"+val+"Span").html($("#"+val).find("option:selected").text());
    });
   
}
function getSupervisionDep(){
    var projectId = document.getElementById("projectid").value;
    $.ajax({
           type: "POST",
           url: "findSupervisionDepList",
           data: "projectId=" + projectId,
           dataType: "json",
           success: function(response){
               clearSelect("svorgid");
               
//               var personnelNullOption = document.createElement("option");
//               personnelNullOption.value = "";
//               personnelNullOption.text = "总监办";
//               document.getElementById("svorgid").appendChild(personnelNullOption);
               $("#svorgid").append('<option value="">总监办</option>');
               if (response != null && response.supervisionDepList != null) {
//                   var depSelection = document.getElementById("svorgid");
                  
                   for(var i=0;i<response.supervisionDepList.length;i++){
                       var jsonObject = response.supervisionDepList[i];
                       $("#svorgid").append('<option value="'+ jsonObject.id  +'" >'+ jsonObject.orgname +'</option>');
//                       var newOption = document.createElement("option");
//                       newOption.text = jsonObject.orgname ;
//                       newOption.value = jsonObject.id ;
//                       
//                       depSelection.appendChild(newOption);
                   }
               }
//               else{
//                   var depSelection = document.getElementById("svorgid");
//                   var nullOption = document.createElement("option");
//                   nullOption.value = "";
//                   nullOption.text = "--";
//                   depSelection.appendChild(nullOption);
//               }
           }
    });
}

function clearSelect(id){
    var sel = document.getElementById(id);
    if(sel != null)
        $("#"+id).find("option").remove();

}


/**
 * 
 * userUpdate
 */
//初始化工程类型checkbox
function initProjectType(pTypeIDs)
{
    var idArray = pTypeIDs.split(",");
    for(var i=0;i<idArray.length;i++)
    {
       
        var pID = idArray[i];

        $(":checkbox[name=projectTypeIdArray][value="+pID+"]").attr("checked",true);
    }
}

function showProperty()
{
    var currentOp = $("#type").find("option:selected").val();
    var selects;
    var cGrade = $("#tempGrade").val();
    if(currentOp == 0)
    {
        selects = ['grade','address','projectType'];
        $("#grade").find("option").attr("disabled",false);
        $("#grade").find("option[value=2]").attr("disabled",true);
        $("#projectTypeTr").css("display","");
        if(cGrade == "")
        {
            $("#grade").val("0");
        }
        else
        {
            $("#grade").find("option[value="+cGrade+"]").attr("selected",true);
        }
    }
    if(currentOp == 1)
    {
        selects = ['enterpriseid','projectType'];
    }
    if(currentOp == 2)
    {
        selects = ['grade','projectid','svorgid'];
        $("#grade").find("option[value=0]").attr("disabled",true);
        $("#grade").find("option[value=1]").attr("disabled",true);
        $("#grade").find("option[value=2]").attr("disabled",false);
        $("#grade").val("2");
    }
    showSelect(selects)
}

function showSelect(selects)
{
    clearSelects();
    $.each(selects,function(key,val){
        $("#"+val).attr("disabled",false);
        $("."+val).attr("disabled",false);
        $("#"+val+"Tr").css("display","");
    })
}

function clearSelects()
{
    var selects=['grade','address','enterpriseid','projectid','svorgid','projectType'];
    $.each(selects,function(key,val){
        $("#"+val).attr("disabled",true);
        $("."+val).attr("disabled",true);
        $("#"+val+"Tr").css("display","none");
    })
}

/**
 * 重置密码
 */
function resetPassword(uid)
{
    if(confirm("您确定要重置该用户的密码吗？"))
    {
        var version = $("#versiona").val();
        $.ajax({
            type : "POST",
            url : "../users/resetPassword",
            //data : "id="+uid+"&version="+version,
            data : "id="+uid,
            success : function(response) {
                //var arr = response.split("-");
                alert(response);
                $.ajax({
                    type: "POST",
                    url: "../users/select",
                    data: "id=" + uid,
                    success: function(response){
                        $("#editDialog").html(response);
                    }
                 });
                //alert(arr[1]);
                //document.getElementById("version").value=arr[1];
                //$("#versiona").val(arr[1]);
            }
        });
    }
}

function inputNewProjName()
{
    if($("#isNewProjChk")[0].checked)
    {
        $("#projectid").css("display","none");
        $("#projectid").attr("class","");
        $("#projectid").attr("disabled",true);
        $("#newProjNameSpan").html("<input type=\"text\" name=\"newprojectname\" id=\"newprojectname\" title=\"项目名称\" class=\"tableTxtSing inputNotNull\"/>");
        $("#newprojectname").val($("#tempNewProjectname").val());
    }
    else
    {
        $("#projectid").css("display","");
        $("#projectid").attr("class","select inputNotNull");
        $("#projectid").attr("disabled",false);
        $("#newProjNameSpan").html("");
    }
}