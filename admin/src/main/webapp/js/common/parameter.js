/**
 * 保存修改
 */
function onSubmitParameter(id,value,version,name,infoSpanId)
{
    $.ajax({
           type: "POST",
           url: "../parameter/update",
           data: "id="+id+"&value="+value+"&version="+version,
           success: function(response)
           {
                var responseArr = response.split("-");
                if(responseArr[0].indexOf("失败")>0)
                {
                    $("#showInfoSpan"+infoSpanId).html
                    (
                        "<font color=\"#FF0000\">"+responseArr[0]+"：Wrong version:"+responseArr[1]+"。请重新载入页面</font>"
                    );
                }
                else
                {
                    doSearchResult(name,infoSpanId);
                    $("#paramInput"+id).attr("version",responseArr[1]);
                    timeout = setTimeout
                    (
                        function()
                        {
                            $(".showInfoSpan").css("color","green");
                            $("#showInfoSpan"+infoSpanId).html("值“"+name+"”修改成功");
                            var sISpan1 = $("#showInfoSpan"+infoSpanId);
                            sISpan1.css("display","");
                            sISpan1.fadeIn(1);
                            timeout = setTimeout
                            (
                                function()
                                {
                                    sISpan1.fadeOut(1000);
                                },
                                2000
                            );
                        },
                        100
                    );
                    
                    
                }
           },
           error:function(res){
               alert("修改失败");
           }
    });
}


function doSearchResult(name,infoSpanId)
{
    $.ajax({
        type: "POST",
        url: "../parameter/searchResult",
        success: function(response)
        {
            $("#parameterList").html(response);
        }
    });
}
