//-----------------------------------------------------------------

/**
 * 省份-城市联动
 */
function provinceToCity()
{
    var selectVal = $("#selectParentID").val();
    if(selectVal == "")
    {
        $("#cityDiv").html("");
        $("#cityDiv").addClass("none");
        deleteAllSelectedCity(true);
        $("#hiddenParentID").val("");
        return false;
    }
    //隐藏域可用
    $("input[name=selectAddr]").removeAttr("disabled");
    $(".provinceHidden").removeAttr("disabled");
    //已经选择的城市列表div可见
    $(".selectedCityDiv").each(function()
    {
        if($(this).find("div").html() != "")
        {
            $(this).removeClass("none");
        }
    })
    $.ajax({
        type:"POST",
        url:"selectCityList?selectParentID=" + selectVal,
        success:function(response)
        {
            $("#cityDiv").removeClass("none");
            $("#cityDiv").html(response);
            //选中对应的复选框
            selectCityChk();
        }
    });
    
}

/**
 * 关闭城市选择面板
 */
function cancelCity()
{
    $("#cityDiv").html("");
    $("#cityDiv").addClass("none");
//    $("#selectParentID option[value='']")[0].selected=true;
    // 关闭城市选择面板则表示用户刚刚没做操作，下拉框回到先前一步所显示的选项上
    $("#selectParentID").val($("#hiddenParentID").val()).selected="selected";
    $("#hiddenParentID").val($("#selectParentID").val());
}

/**
 * 地区下拉框选项默认
 */
function parentOptionDefault(){

    if($(".provinceHidden").length>0){ // 已经有显示的城市
        if($("#provinceID"+$("#hiddenParentID").val()).length>0){ // 判断上一次操作的省份是否在存在，如存在则下拉框显示为上次操作的省份
            $("#selectParentID").val($("#hiddenParentID").val()).selected="selected";
            $("#hiddenParentID").val($("#selectParentID").val());
        }else{ // 如果不存在则显示所选择的省份中最后一个
          $(".selectedCityDiv").each(function()
          {
              if($(this).find(".provinceHidden").length > 0){
                  $("#selectParentID").val($(this).find(".provinceHidden").val()).selected="selected";
              }
              $("#hiddenParentID").val($("#selectParentID").val());
          });
        }   
    }else{ // 没有可显示的城市则活动地区默认到全部省市选项
        $("#selectParentID").val("").selected="selected";
        $("#hiddenParentID").val($("#selectParentID").val());
    }
}

/**
 * 确定选择
 */
function selectedCity()
{
    var provinceID = $("#selectParentID").val();
    //未选中复选框且对应的省的分类显示为空
    if($("input[name=selectCityChk]:checked").length <= 0 && $("#selectedCityDiv"+provinceID).html() == "")
    {
        alert("请至少选择一个城市");
        return false;
    }
    removeNotSelectCity();
    $("input[name=selectCityChk]:checked").each(function()
    {
        if(!cityIsSelected($(this).val()))
        {
            //标签，显示城市名称
            var city1 = "<a href=\"javascript:void(0)\" title=\"点击删除\" id=\"cityLink"+$(this).val()+"\" class=\"cityDeleteLink\" onclick=\"deleteSelectedCity('"+$(this).val()+"','"+$(this)[0].title+"','"+provinceID+"')\">" + $(this)[0].title + "&nbsp;</a>";
            //隐藏域，用来传值给Action
            city1 += "<input type=\"hidden\" id=\"curCity"+$(this).val()+"\" value=\""+$(this).val()+"\" name=\"selectAddr\"/>";
            $("#selectedProvinceDiv"+provinceID).removeClass("none");
            $("#selectedCityDiv"+provinceID).append(city1);
            clearProvinceShow();
        }
    });
    //获取省名称
//    var provinceName =$("#selectParentID").find("option:selected").text(); 
    $("#selectedProvinceDiv"+provinceID+" .provinceHidden").remove();
    if($("#selectedCityDiv"+provinceID).html() != ""){
        var tempInput = "<input type='hidden' value='"+provinceID+"' id ='provinceID"+provinceID+"' class='provinceHidden' />";
        $("#selectedProvinceDiv"+provinceID).append(tempInput);
    }
    $("#cityDiv").addClass("none");
    if($("input[name=selectCityChk]:checked").length > 0 ){
        $("#hiddenParentID").val($("#selectParentID").val());
    }
    parentOptionDefault();
}

/**
 * 拼接推送地区数据参数
 */
function makeRegionData()
{
    var data;
    var provinceID = $("#selectParentID").val();
    //如果选择的为全部省市
    if(provinceID == "")
    {
        data = "";
    }
    else
    {
        //拼接省份
        var provinces = "";
        //拼接城市
        var citys = "";
        $(".selectedCityDiv").each(function()
        {
            if($(this).find(".provinceHidden").length > 0)
            {
                provinces += $(this).find(".provinceHidden").val();
                provinces += ";";
                
                $(this).find("input[name=selectAddr]").each(function(){
                    citys += $(this).val();
                    citys += "-";
                });
                citys = citys.substring(0,citys.lastIndexOf("-"));
                citys+=";";
            }
        });
        // 判断是否有选中的城市，如没有则返回空
        if($(".selectedCityDiv").find(".provinceHidden").length > 0)
        {
            // 剪切最后一个分隔符
            provinces = provinces.substring(0,provinces.lastIndexOf(";"));
            citys = citys.substring(0,citys.lastIndexOf(";"));
            data = provinces + ":" + citys;
        }else{
            data="";
        }
    }
    return data;
}


/**
 * 清除被取消的城市
 */
function removeNotSelectCity()
{
    //循环比较复选框
    $("input[name=selectCityChk]").each(function()
    {
        //复选框未被选中
        if(!$(this)[0].checked)
        {
            //删除a标签和隐藏域
            deleteCity($(this).val(),$(this)[0].title);
        }
    });
    clearProvinceShow();
}

/**
 * 判断城市是否已经被选过了
 */
function cityIsSelected(cityID)
{
    var isHave = false;
    if($("input[name=selectAddr]").length > 0)
    {
        //循环比较选中的城市的隐藏域
        $("input[name=selectAddr]").each(function()
        {
            //取得ID并与参数比较，如果相同，则表示该城市已被选中
            var myCityID = $(this)[0].id;
            myCityID = myCityID.substring(7);
            if(myCityID == cityID)
            {
                isHave = true;
            }
        });
    }
    return isHave;
}

/**
 * 载入城市面板时选中对应的复选框
 */
function selectCityChk()
{
    if($("input[name=selectAddr]").length > 0)
    {
        //循环隐藏域
        $("input[name=selectAddr]").each(function()
        {
            //取得ID
            var cityID = $(this)[0].id;
            cityID = cityID.substring(7);
            
            //选中复选框
            $("input[id=cityCHK"+cityID+"]").attr("checked","checked");
        });
    }
}

/**
 * 删除已选择的城市
 */
function deleteCity(cityID,cityName)
{
    //根据ID去掉a标签
    $("#cityLink"+cityID).remove();
    //根据name和value去掉隐藏域
    $("#curCity"+cityID).remove();
}
function deleteSelectedCity(cityID,cityName,provinceID)
{
    
    //根据ID去掉a标签
    $("#cityLink"+cityID).remove();
    //根据name和value去掉隐藏域
    $("#curCity"+cityID).remove();
    if($.trim($("#selectedCityDiv"+provinceID).html()) == "")
    {
        $("#selectedCityDiv"+provinceID).html("");
        $("#selectedProvinceDiv"+provinceID).addClass("none");
        $("#provinceID"+provinceID).remove();
    }
    parentOptionDefault();
}

/**
 * 当这个省的所有城市都未被选中时，隐藏这个省对应的div
 */
function clearProvinceShow()
{
    //获取当前选中的省份
    var provinceID = $("#selectParentID").val();
    //无内容
    if($("#selectedCityDiv"+provinceID).html() == "")
    {
        $("#selectedProvinceDiv"+provinceID).addClass("none");    
        $("#provinceID"+provinceID).remove();
    }
}

/**
 * 隐藏指定的div
 */
function hideProvinceDivByID(provinceID)
{
    $("#selectedCityDiv"+provinceID+" input[name=selectAddr]").remove();
    $("#selectedCityDiv"+provinceID+" .cityDeleteLink").remove();
    $("#selectedCityDiv"+provinceID).html("");
    $("#provinceID"+provinceID).remove();
    $("#selectedProvinceDiv"+provinceID).addClass("none");
    parentOptionDefault();
}

/**
 * 删除所有选择过的城市
 * @param 是否真正清除内容，true表示清除内容，false表示只隐藏div
 */
function deleteAllSelectedCity(clearObject)
{
    if(clearObject)
    {
        //删除所有的城市隐藏域
        $("input[name=selectAddr]").remove();
        //删除所有的城市名称
        $(".cityDeleteLink").remove();
        $(".provinceHidden").remove();
    }
    else
    {
        //不可用
        $("input[name=selectAddr]").attr("disabled","disabled");
        $(".provinceHidden").attr("disabled","disabled");
    }
    $(".selectedCityDiv").addClass("none");

}