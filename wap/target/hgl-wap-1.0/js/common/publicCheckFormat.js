
//*************************************************************************************************
//validate  form
//
//*************************************************************************************************
function initValidate() {
    $(":text").addClass("textInput_01");
    $("input[name='page.pageIndex']").removeClass("textInput_01");
    $("select").addClass("textInput_01");
    /*
    $(".date").each(function(idx,domEle){      
        $(domEle).datepicker({dateFormat:"yy-mm-dd"});
    });
    
    *
    *
    */
    $(document).delegate(".inputTips" ,'focus',function(){
    	$(this).removeClass('onerrInput');
    	showTipInfo(1, $(this),$(this).attr("tips"));
    
    });
    
    $(document).delegate(".inputTips" ,'blur',function(){
        if(!$(this).hasClass("onerrInput")){
            showTipInfo(2,$(this),$(this).attr("tips"));                
        }
    });
    $(".date").bind("click", function(evt) {
        SelectDate(evt.target, 'yyyy-MM-dd');
    });

    $(".datetime").bind("click", function(evt) {
        SelectDate(evt.target, 'yyyy-MM-dd hh:mm');
    });
    $(".datetime_full").bind("click", function(evt) {
        SelectDate(evt.target, 'yyyy-MM-dd hh:mm:ss');
    });
    $("input.view").bind("mouseover", function(evt) {
        $(evt.target).removeClass("view");
    });
    $("input.view").bind("mouseout", function(evt) {
        $(evt.target).addClass("view");
    });
    $(".key_menu").bind("click", function(evt) {
        var eleId = evt.target.id;
        $(".key_menu").each(function(idx, domEle) {
            var ele = $(domEle);
            var id = domEle.id;
            var menuItems = $("#" + id + "_items");
            if (eleId == id)
            {
                var eleClass = ele.attr('class');
                if (eleClass.indexOf("on") > 0)
                {
                    menuItems.slideUp("normal");
                    ele.removeClass("on");
                } else
                {
                    menuItems.slideDown("normal");
                    ele.addClass("on");
                }
            } else
            {
                menuItems.slideUp("normal");
                ele.removeClass("on");
            }
        });
    });
   
    $(document).delegate("input","focus",function(evt) {
        var obj = evt.target;
        showTipInfo(2, $(obj),"");
        $(obj).addClass("onFourInput");
        $(obj).removeClass("er-hover");
    });
    
    $(document).delegate("input","blur",function(evt) {
        var obj = evt.target;
        $(obj).removeClass("onFourInput");
    });
    $(document).delegate("input.inputNotNull","blur",function(evt) {
        $(evt.target).removeClass('onerrInput');
    });
    
    $(document).delegate("input.numberMust" ,'blur',function(){
    	var v = $(this).val();
        var checkNotNull = isNotNull(v);
        if ($(this).hasClass("inputNotNull") && !checkNotNull){
            return;
        }
       var reyx = /^[0-9]*$/;
       var validateResult = reyx.test(v);
       var maxlength = $(this).attr('maxlength');
       if(validateResult && maxlength!=undefined){
           validateResult = (v.length == maxlength);
       }
       if(!validateResult){
          $(this).addClass('onerrInput');
          var fleg=validateResult?2:0;
          showTipInfo(0, $(this),$(this).attr('title')+"为"+maxlength+"位数字");
     
          if(fleg==0){
              return false;
          }
       }else{
           showTipInfo(2, $(this),""); 
       }
    });
/*    $("input.numberMust").each(function(){
        $(this).live("blur",function(){
        	var v = $(this).val();
            var checkNotNull = isNotNull(v);
            if ($(this).hasClass("inputNotNull") && !checkNotNull){
                return;
            }
           var reyx = /^[0-9]*$/;
           var validateResult = reyx.test(v);
           var maxlength = $(this).attr('maxlength');
           if(validateResult && maxlength!=undefined){
               validateResult = (v.length == maxlength);
           }
           if(!validateResult){
              $(this).addClass('onerrInput');
              var fleg=validateResult?2:0;
              showTipInfo(0, $(this),$(this).attr('title')+"为"+maxlength+"位数字");
         
              if(fleg==0){
                  return false;
              }
           }else{
               showTipInfo(2, $(this),""); 
           }
        });
    });*/
    $(document).delegate("input.password","blur",
            function(evt) {
                var checkNotNull = isNotNull(evt.target.value);
                if ($(this).hasClass("inputNotNull") && !checkNotNull){
                    return;
                }
                var validateResult = isPassWord(evt.target.value);
                var fleg=validateResult?2:0;
                return showTipInfo(fleg, $(evt.target), "您输入的密码格式不正确");
                if(fleg==0){
                    return false;
                }
            });

    //companyName
    $(document).delegate("input.companyName","blur",
                    function(evt) {
                        var checkNotNull = isNotNull(evt.target.value);
                        if ($(this).hasClass("inputNotNull") && !checkNotNull){
                            return;
                        }
                        var result = false;
                        if (evt.target.value != "")
                        {
                            var checkValue = evt.target.value.trim();
                            var nameLength = checkValue.replace(
                                    /([\u4e00-\u9fa5])/g, '()').replace("（",
                                    "((").replace("）", "))").length;
                            if (nameLength < 4 || nameLength > 40)
                            {
                                result = false;
                                showTipInfo(0, $(evt.target),"全部为字母或者中文，可包括（）、()、'-'、'_'且长度为4-40位字符");
                                return false;
                            } else
                            {
                                var validateResult = /^([\u4e00-\u9fa5-\（\）\_\-\(\)]{2,40}|([A-Za-z-\（\）\_\-\(\)]+\s?){4,40})$/
                                        .test(checkValue);
                                var fleg=validateResult?2:0;
                                return showTipInfo(fleg, $(evt.target),"全部为字母或者中文，可包括（）、()、'-'、'_'且长度为4-40位字符");
                            }

                        }
                    });

    $(document).delegate("input.account","blur",
                    function(evt) {
                        if (evt.target.value == "")
                        {
                            return showTipInfo(2, $(evt.target),"您输入的 账号数据格式不正确");
                        } else
                        {
                            var validateResult = /(^[\u4e00-\u9fa5A-Za-z0-9]{4,20}$)/.test(evt.target.value);
                            var fleg=validateResult?2:0;
                            return showTipInfo(fleg, $(evt.target),"您输入的 账号数据格式不正确");
                            if(fleg==0){
                                return false;
                            }
                        }

                    });
    $(document).delegate("input.accountUnique","blur",function(evt) {
                        if (evt.target.value == ""){
                            return showTipInfo(2, $(evt.target),"您输入的 账号数据格式不正确");
                        } else{
                            var validateResult = /(^[\u4e00-\u9fa5A-Za-z0-9]{4,20}$)/.test(evt.target.value);
                            var fleg=validateResult?2:0;
                            var userId = ($('#accountId').val()?$('#accountId').val():"");
                            if(fleg==2){
                                $.ajax({
                                    type: "POST",
                                    url: "../index/checkAccount",
                                    data: "tbUser.account="+evt.target.value+"&tbUser.id="+userId,
                                    success: function(response){
                                        if(response!=null && response!=""){//新增
                                            showTipInfo(0, $(evt.target),response);
                                            return false;
                                        }else{
                                            showTipInfo(2, $(evt.target),response);
                                        }
                                    }  
                               });
                            }else{
                                showTipInfo(fleg, $(evt.target),"您输入的账号数据格式不正确");
                                return false;                               
                            }
                        }
                    });
    $(document).delegate("input.number","focus",function(evt) {
        onCFocus(this);
        return false;
    });
   $(document).delegate("input.number","keyup",function(evt) {
        onCKeyup_number(this);
        return false;
    });
    $(document).delegate("input.number","blur",function(evt) {
        onCKeyup_number(this);
        return false;
    });
    $(document).delegate("input.integer","focus",function(evt) {
        onCFocus(this);
        return false;
    });
    $(document).delegate("input.integer","keyup",function(evt) {
        onCKeyup_integer(this);
        return false;
    });
    $(document).delegate("input.unsign_number","keyup",function(evt) {
        onCKeyup_unsign_number(this);
        return false;
    });
    $(document).delegate("input.float","keyup",function(evt) {
        onCKeyup_float(this);
        return false;
    });
    $(document).delegate("input.floatZero","blur",function(evt) {
        onCKeyup_floatZero(this,2);
        return false;
    });
    $(document).delegate("input.floatCanZero","blur",function(evt) {
        onCKeyup_floatCanZero(this, 3);
        return false;
    });

    $(document).delegate("input.float1","blur",function(evt) {
        onCKeyup_float(this, 1);
        return false;
    });
    $(document).delegate("input.float2","blur",function(evt) {
        onCKeyup_float(this, 2);
        return false;
    });
    $(document).delegate("input.float3","blur",function(evt) {
        onCKeyup_float(this, 3);
        return false;
    });
    $(document).delegate("input.float4","blur",function(evt) {
        onCKeyup_floata(this, 4);
        return false;
    });
    $(document).delegate(".phone","blur",function(evt) {
        var domEle = evt.target;
        if (domEle.value != "")
        {
            var validateResult = checkMobile(domEle.value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle),"您输入的手机格式不正确");
        }
    });
    $(".tel").bind("blur", function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull)
        {
            return;
        }
        var validateResult = isTel(domEle.value) || checkMobile(domEle.value);
        var fleg=validateResult?2:0;
        showTipInfo(fleg, $(domEle),"您输入的联系方式格式不正确");
    });
    $(document).delegate(".tel","blur",	
    		function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if (checkNotNull)
        {
            var validateResult = isIDno(domEle.value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle),"您输入的身份证格式不正确");
        }
    });
    $(".date").bind("blur", function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull)
        {
            return;
        }
        var validateResult = isDate(domEle.value);
        var fleg=validateResult?2:0;
        showTipInfo(fleg, $(domEle),"您输入的日期格式不正确");
    });

    $(".money").bind("blur", function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull)
        {
            return;
        }
        var s = domEle.value;
        var validateResult = isMoney(s);
        var fleg=validateResult?2:0;
        showTipInfo(fleg, $(domEle),"您输入的金额格式不正确");
    });

    $(".zipCode").bind("blur", function(evt) {
        var domEle = evt.target; 
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull)
        {
            return;
        }
        var validateResult = isZipCode(domEle.value);
        var fleg=validateResult?2:0;
        showTipInfo(fleg, $(domEle),"您输入的邮编格式不正确");
    });

    $(".email").bind("blur", function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull)
        {
            return;
        }
        var fleg=isEmail(domEle.value)?2:0;
        showTipInfo(fleg, $(evt.target),"您输入的邮箱格式不正确");

    });

    $(".emailUnique").bind("blur", function(evt) {
        var domEle = evt.target;
        var checkNotNull = isNotNull(evt.target.value);
        if ($(domEle).hasClass("inputNotNull") && !checkNotNull){
            return;
        }
        var fleg=isEmail(domEle.value)?2:0;
        var userId = ($('#accountId').val()?$('#accountId').val():"");
        if(fleg==2){
            $.ajax({
                type: "POST",
                url: "../index/checkEmail",
                async: false,
                data: "tbUser.email="+evt.target.value+"&tbUser.id="+userId,
                success: function(response){
                    if(response!=null && response!=""){//新增
                        showTipInfo(0, $(evt.target),response);
                    }else{
                        showTipInfo(2, $(evt.target),response);
                    }
                }  
           });
        }else{
            showTipInfo(fleg, $(evt.target),"您输入的邮箱格式不正确");            
        } 
    });

};

/*
 formEle :为需要验证的form对应的 form对象
 * */
function validateForm(formEle) {
    var formDom = formEle;
    if (typeof(formEle) == "string") {
        formDom = $("#" + formEle);
    }
    var result = true;
    var formEles = $(formDom).serializeArray();
    var flag=true;
    for (domEleIdx in formEles) {
        var domEle = $(formDom).find("[name='" + formEles[domEleIdx].name + "']");
        
        if ($(domEle).hasClass("inputNotNull")) {
            var validateResult = isNotNull(domEle[0].value);
            if(validateResult){//如果验证通过这消除原来的提示
                $(domEle[0]).removeClass('onerrInput');
            }else{ 
                showTipInfo(2, $(domEle[0]), "");
                $(domEle[0]).addClass('onerrInput');
            }
            result = result && validateResult;
            if (!validateResult) continue;
        }
        if ($(domEle).hasClass("money")) {
            var validateResult = isMoney(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的金额格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("password")) {
            var validateResult = isPassWord(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg,$(domEle[0]),"您输入的密码格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("phone")) {
            var validateResult = checkMobile(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的手机格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("tel")) {
            var validateResult = isTel(domEle[0].value) || checkMobile(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的联系方式格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("date")) {
            var validateResult = isDate(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的日期格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("datetime")) {
            var validateResult = isDatetime(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的时间格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("email")) {
            var validateResult = isEmail(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的邮箱格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("idno")) {
            var validateResult = isIDno(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的身份证格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("float")) {
            var validateResult = isFloat(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),$(domEle).attr('title')+"格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("numberMust")) {
             var v = $(domEle).val();
             var reyx = /^[0-9]*$/;
             var validateResult = reyx.test(v);
             var maxlength = $(domEle).attr('maxlength');
             if(validateResult && maxlength!=undefined){
                 validateResult = v.length == maxlength;
             }
             if(!validateResult){
                 $(domEle).addClass('onerrInput');
                 if(flag){
                    $(window).scrollTop(domEle.offset().top-100);
                    flag=false;
                  }
                 showTipInfo(0, $(domEle[0]),$(domEle).attr('title')+"为"+maxlength+"位数字");
             }
             result = result && validateResult;
        }
        if ($(domEle).hasClass("number")) {
            showTipInfo(validateResult, $(domEle[0]));
            var validateResult = isNumber(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),$(domEle).attr('title')+"格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("integer")) {
            var validateResult = isInteger(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),$(domEle).attr('title')+"格式不正确");
            result = result && validateResult;
        }
        if ($(domEle).hasClass("zipCode")) {
            var validateResult = isZipCode(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的邮编格式不正确");
            result = result && validateResult;
        }
        //companyName
        if($(domEle).hasClass("companyName")){
            if ($(domEle).value != "" && $(domEle).value != "undefined"){
                var checkValue = ($(domEle).val()+"").trim(); ;
                var nameLength = checkValue.replace(
                        /([\u4e00-\u9fa5])/g, '()').replace("（",
                        "((").replace("）", "))").length;
                if (nameLength < 4 || nameLength > 40){
                    result = false;
                    showTipInfo(0, $($(domEle)),"全部为字母或者中文，可包括（）、()、'-'、'_'且长度为4-40位字符");
                } else{
                    var validateResult = /^([\u4e00-\u9fa5-\（\）\_\-\(\)]{2,40}|([A-Za-z-\（\）\_\-\(\)]+\s?){4,40})$/
                            .test(checkValue);
                    result = result && validateResult;
                    var fleg=validateResult?2:0;
                    showTipInfo(fleg, $($(domEle)),"全部为字母或者中文，可包括（）、()、'-'、'_'且长度为4-40位字符");
                }
            }
        }
       
        //非0正整数
        if ($(domEle).hasClass("numberNotZero")) {
            var validateResult = isNumberNotZero(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),$(domEle).attr('title')+"格式不正确");
            result = result && validateResult;
        }

        //acount
        if ($(domEle).hasClass("account")) {
            var validateResult = /(^[\u4e00-\u9fa5A-Za-z0-9]{4,20}$)/.test(domEle[0].value);
            var fleg=validateResult?2:0;
            showTipInfo(fleg, $(domEle[0]),"您输入的 账号数据格式不正确");
            result = result && validateResult;
        }
    }
    return result;

}
/**
 * 清除所有验证提示
 */
function hidAllTipInfo(){
    $("input").removeClass('onerrInput');
    $.each( $("input"), function(i, n){        
        showTipInfo(2,$(n),"");
    });
}
function showTipInfo(messgeType, el, message) {
    var divId = "key_info_" + el[0].name + el[0].id;
    //消息的排列方式 true表示横向排列
    var msgPosition=true;
    if($("#msgPosition") != 'undefined' && $("#msgPosition").val()=='false'){
        msgPosition=false;
    }    
    var divObj = document.getElementById(divId);
    if(divObj=='undefined' || divObj==null){
        if(messgeType==2){
            return;
        }else{
            createFormatTr(msgPosition, el, divId);
        }
    }
    divObj = document.getElementById(divId);

    if (messgeType==0) {//错误的提示
        document.getElementById(divId).innerHTML = "<p class='errMsgLabel' >"+message+"</p>";
        $(el).addClass("onerrInput");
        $(divObj).show();
    } else if (messgeType==1) {//验证的字段说明
        document.getElementById(divId).innerHTML = "<p class='formateMsgLabel' >"+message+"</p>";
        $(el).removeClass("onerrInput");
        $(divObj).show();
    } else if (messgeType==2) {//验证通过以后的错误消除
        document.getElementById(divId).innerHTML="";
        $(el).removeClass("onerrInput");
        $(divObj).hide();
    } else{
        document.getElementById(divId).innerHTML="";
        $(divObj).hide();
    }
}

/**
 * 创建el对象 用来显示错误提示信息的结构 适用于table
 * @param msgPosition
 * @param el
 * @param divId
 */
function createFormatTr(msgPosition,el,divId){
    if(msgPosition){//横向排列的
        var errorDetailHtmlStr = "<td id="+divId+" ></td>";
        $(el).parents("td").after(errorDetailHtmlStr);
    }else{//竖向排列的
        var nullTd="";//当前输入框td之前的td html
        var nextNullTd="";//当前输入框td之后的td html
        var prevTds=$(el).parents("td").prevAll("td");//当前输入框td之前的td集合
        var nextTds=$(el).parents("td").nextAll("td");//当前输入框td之后的td集合
        var size = prevTds.length;//当前输入框的td之前的所有td数量
        $.each(prevTds , function(i,n){
            nullTd+="<td></td>";
        }); 
        $.each(nextTds , function(i,n){
            nextNullTd+="<td></td>";
        });
        var errorDetailHtmlStr = "<tr id='checkMsgTr'>"+nullTd+"<td id='"+divId+"' ></td>"+nextNullTd+"</tr>";
        
        if($(el).parents("tr").next("tr")!= 'undefined'  && $(el).parents("tr").next("tr").attr("id")=="checkMsgTr"){//如果之前已经创建过下排的tr则不创建
            $($(el).parents("tr").next("tr").children("td")[size]).attr("id",divId);
        }else{//如果之前已经创建过下排的tr则不创建
            $(el).parents("tr").after(errorDetailHtmlStr);            
        }
    }
}


