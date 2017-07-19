$(function()
{
    $.ajax({
        type: "POST",
        url: "../users",
        success: function(response){
            $("#show_jsp_div1").html(response);
        }
    })
})

function changePage(url,tabId)
{
    var tabs = ["accountTab1","accountTab2","accountTab3"];
    $.each(tabs,function(key,val){
        if(val == tabId)
        {
            $("#"+tabId).attr("class","on cardTab");
        }
        else
        {
            $("#"+tabId).attr("class","cardTab");
        }
    });
    $.ajax({
        type: "POST",
        url: "../"+url,
        success: function(response){
            $("#show_jsp_div1").html(response);
        }
    })
}