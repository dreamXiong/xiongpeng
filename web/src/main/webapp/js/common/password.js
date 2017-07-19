
//检测旧密码
function checkPassword(id)
{
    var inputOldPassword = $("#inputOldPassword").val();
    $.ajax({
        type: "POST",
        url: "../password/select",
        data: "id="+id+"&password="+inputOldPassword,
        success: function(response){
            var pInfo1 = $("#passwordInfo1")
            if(response == "")
            {
                pInfo1.css("display","none");
            }
            else
            {
                pInfo1.css("display","");
            }
        }
     });
}
//修改密码
function changePassword(id)
{
    var newPassword1 = $("#password1").val();
    var newPassword2 = $("#password2").val();
    var inputOldPassword = $("#inputOldPassword").val();
    if(inputOldPassword == "")
    {
        alert("请输入旧密码");
        return false;
    }
    if(newPassword1 == "")
    {
        alert("请输入新密码");
        return false;
    }
    if(newPassword1 != newPassword2)
    {
        alert("两次输入的密码不一致，请重新输入");
        return false;
    }
    var param = $("#passwordForm").serialize();
    $.ajax
    ({
        type: "POST",
        url: "../password/select",
        data: "id="+id+"&password="+inputOldPassword,
        success: function(response){
            if(response == "") //密码匹配正确
            {
                //修改密码
                $.ajax({
                    type: "POST",
                    url: "../password/update",
                    data: param,
                    success: function(response){
                        alert(response);
                        $("#password1").val("");
                        $("#password2").val("");
                        $("#inputOldPassword").val("");
                        $("#version").val(parseInt($("#version").val())+1);
                    }
                 });
            }
            else
            {
                alert(response);
            }
        }
     });
}