$(function()
{
    //点击页面别处隐藏列表
    $(document).bind("click",
        function(event)
        {
            if($(event.target)[0].id != "fuzzDiv1" && $(event.target)[0].id != "goodsName")
            {
                $("#fuzzDiv1").addClass("none");
                $("#div1").addClass("none");
            }
        }
    );
    $("#goodsName").bind("focus",function()
    {
        //文本框获得焦点，显示模糊匹配列表
        if($("#div1").html() != "")
        {
            $("#fuzzDiv1").removeClass("none");
            $("#div1").removeClass("none");
        }
    });
    $("#goodsName").bind("keyup",function()
    {
        if(event.keyCode != 40 && event.keyCode != 13 && event.keyCode != 38)
        {
            $("#divIndex").val("-1");
            if($("#goodsList").val() == "")
            {
                $.ajax({
                   type: "POST",
                   url: "searchGoodNames",
                   success: function(response){
                        $("#goodsList").val(response);
                        showList();
                   }
                });
            }
            else
            {
                showList();
            }
        }
    });
    $("#goodsName").bind("keydown",function()
    {
        var len = $(".textLink1").length;
        if(len > 0)
        {
            $(".textLink1").removeClass("textLink2");
            len --;
            var ind = parseInt($("#divIndex").val());
            var isTrue = false;
            if(event.keyCode == 40)
            {
                ind = ind < len ? ind + 1 : 0;
                isTrue = true;
            }
            else if(event.keyCode == 38)
            {
                ind = ind > 0 ? ind - 1 : len;
                isTrue = true;
            }
            if(isTrue)
            {
                $("#listDiv"+ind).addClass("textLink2");
                $("#divIndex").val(ind);
                var gid = $("#listDiv" + ind).attr("jqueryGoodsId");
                var gname = $("#listDiv" + ind).attr("jqueryGoodsName");
                inputGoodsname(gname,gid,false);
                $("#div").html("");
            }
            if(event.keyCode == 13)
            {
                $("#fuzzDiv1").addClass("none");
                $("#div1").addClass("none");
                $(".textLink1").remove();
            }
        }
    });
});
function showList()
{
    $("#goodsId").val("");
    var keyword = $("#goodsName").val();
    if(keyword == "")
    {
        $("#fuzzDiv1").addClass("none");
        $("#div1").addClass("none");
        return false;
    }
    var list = "";
    var str = $("#goodsList").val().split("=");
    var count = 0;
    for(i=0; i<str.length; i++)
    {
        var idname = str[i].split("::");
        if(idname != "" && idname[1].indexOf(keyword) >= 0)
        {
            list += "<div id=\"listDiv"+count+"\" jqueryGoodsName=\""+idname[1]+"\" jqueryGoodsId=\""+idname[0]+"\" class=\"textLink1\" href=\"javascript:void(0)\" onclick=\"inputGoodsname('"+idname[1]+"','"+idname[0]+"',true)\">" + idname[1].replace(keyword,"<label style=\"color:#ff0000\">"+keyword+"</label>")+"</div>";
            count ++;
        }
    }
    if(list == "")
    {
        $("#fuzzDiv1").addClass("none");
        $("#div1").addClass("none");
    }
    else
    {
        $("#fuzzDiv1").removeClass("none");
        $("#div1").removeClass("none");
        $("#div1").html(list);
    }
}

function inputGoodsname(str,goodsid,isTrue)
{
    $("#goodsName").val(str);
    $("#goodsId").val(goodsid);
    if(isTrue)
    {
        $("#div1").html("");
        $("#fuzzDiv1").addClass("none");
        $("#div1").addClass("none");
    }
}


function isSelectGoods()
{
    if($.trim($("#goodsName").val()) == "")
    {
        alert("商品名称不能为空");
        return false;
    }
    //获取输入商品
    var isHave = "0";
    if($("#goodsList").html() != null)
    {
        var inputGoodsName = $("#goodsName").val();
        var str = $("#goodsList").val().split("=");
        for(i=0; i<str.length; i++)
        {
            var idname = str[i].split("::");
            if(inputGoodsName == idname[1])
            {
                isHave = "1";
                break;
            }
        }
    }
    //不存在的商品
    if($("#goodsId").val() != "")
    {
        return true;
    }
    if(isHave == "0")
    {
        alert("您输入的商品不存在");
        return false;
    }
    if($("#goodsId").val() == "")
    {
        alert("请选择商品");
        return false;
    }
}