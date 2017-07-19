//-------------------------------------------------------------------------------------------------
// linkon.web 
//-------------------------------------------------------------------------------------------------
jQuery.namespace = function () {
    var a = arguments, o = null, i, j, d;
    for (i = 0; i < a.length; i = i + 1) {
        d = a[i].split(".");
        o = window;
        for (j = 0; j < d.length; j = j + 1) {
            o[d[j]] = o[d[j]] || {};
            o = o[d[j]];
        }
    }
    return o;
};
jQuery.namespace('linkon.web');
var isIndex = false;
linkon.web.constants = {
    THEORY_UNIT_TYPE : 111,
    POUND_UNIT_TYPE : 112,
    WAITING_ORDER_CONFIRM : 6510,
    WAITING_PAY_MONEY : 6520,
    CANCEL_BACK : 6570,
    BUYER_CANCEL_BACK : 6580,
    VERIFY_PASS : 6600
};
linkon.web.refreshTopBar = function() {
    //刷新top bar 区域
    if (!isLogin)
    {
        return;
    }
    $.ajax({
        type : "GET",
        url : ctx + "/linkon/topBar",
        data : null,
        success : function(response) {
            var ret = $.parseJSON(response);
            $("#shppingItemCt").html(ret.data.shoppingItemCt);
            $("#messageCt").html(ret.data.messageCt);
            $("#favoriteCt").html(ret.data.favoriteCt);
        }
    });
};
/*linkon.web.msgDialog = $("#key-dialog-message").dialog({
    autoOpen : false,
    modal : true,
    resizable : false,
    buttons : {
        Ok : function() {
            $(this).dialog("close");
        }
    }
});
*/
/*linkon.web.showMsg = function(msg) {
    var contentId = "#" + linkon.web.msgDialog[0].id + "-content";
    $(contentId).html(msg);
    linkon.web.msgDialog.dialog("open");
};*/

linkon.web.alertOk=jQuery.noop;
linkon.web.alertClose=jQuery.noop;
/*linkon.web.showMsg = function(msg, okCallback, closeCallBack){
    //暂时修改为alert
    var contentId = "#"+linkon.web.msgDialog[0].id + "-content";
    $(contentId).html(msg);
    linkon.web.msgDialog.dialog("open");
    if(okCallback != undefined){
        linkon.web.alertOk = okCallback;
    }
    if(closeCallBack != undefined){
        linkon.web.alertClose = closeCallBack;
    }
    alert(msg);
};
*/
/*linkon.web.initDialog = function() {

    var confirmId = "#key-dialog-message-confirm";
    var contentId = confirmId + "-content";
    linkon.web.confirmDialog = $(contentId).dialog({
        autoOpen : false,
        resizable : false,
        height : 140,
        modal : true,
        buttons : {
            "确定" : function() {
                linkon.web.confirmOk();
                $(this).dialog("close");
            },
            "取消" : function() {
                $(this).dialog("close");
            }
        }
    });
    */
  /*  linkon.web.msgDialog =  $("#key-dialog-message").dialog({
        autoOpen:false,
        modal: true,
        resizable:false,
        buttons: {
            "确定": function() {
                linkon.web.alertOk(true);
                $(this).dialog( "close" );
            }
        },
        close: function(){
            linkon.web.alertClose(true);
        },
//        open: function(){
//            $(".ui-icon-closethick").hide();
//        }
    });*/

linkon.web.confirmOk = jQuery.noop;
linkon.web.confirmMsg = function(msg, okCallback) {
    var confirmId = "#key-dialog-message-confirm";
    var contentId = confirmId + "-content";
    $(contentId).html(msg);
    linkon.web.confirmDialog.dialog("open");
    linkon.web.confirmOk = okCallback;
};

/*
 * option.text,option.value
 * */
linkon.web.getSelectedOpiton = function(selectEle) {
    var selectObj = selectEle;
    if (typeof (selectEle) == 'string')
    {
        selectObj = document.getElementById(selectEle);
    }
    var index = selectObj.selectedIndex;
    var option = null;
    if (index > 0)
    {
        option = selectObj.options[index];
    }
    return option;
};

linkon.web.generateCheckIds = function(selector) {
    var ids = "";
    $(selector).each(function(idx, dom) {
        ids += dom.value + ",";
    });
    return ids;
};

linkon.web.toPage = function(evt, page) {

    var domEle = evt.target;
    var pageIndex;
    var pageAction = $(domEle).parent()[0].attributes['act'].value;
    var frmId = $(domEle).parent()[0].attributes['formId'].value;
    var dataDomId = $(domEle).parent()[0].attributes['dataDomId'].value;
    if (frmId == '')
    {
        frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
    }
    if (dataDomId == '')
    {
    	dataDomId = "key_" + EcWeb.currentModalName + "_list";
    }
    var frm = $("#" + frmId);
    var params;

    if (page)
    {
        pageIndex = page;
    } else
    {
        pageIndex = $(domEle).attr("idx");
    }

    if (frm[0]['page.pageIndex'])
    { //== 'undefined'
        frm[0]['page.pageIndex'].value = pageIndex;
        params = frm.serialize();
        //
    } else
    {
        params = frm.serialize() + "&pageIndex=" + pageIndex;
    }

    $.ajax({
        type : "POST",
        url : pageAction,
        data : params,
        success : function(response) {
            $("#" + dataDomId).html(response);
            
        }
    });
    return false;
};
function reloadPage() {
    document.location.reload();
}
//linkon.web.timer = window.setInterval("reloadPage()",60*3*1000);

function initPagination() {
    if ($(".paginationForm").length == 0)
    {
        return;
    }
    $(document).delegate(".pg","click", function(evt) {
        linkon.web.toPage(evt);
    });
    $(document).delegate("#goPage","click", function(evt) {
    	 var pageValue = $(evt.target).prev("input")[0].value;
         if('' == pageValue){
             pageValue = $(evt.target).prev("input")[0].value = 1;
//             return;
         }
        linkon.web.toPage(evt, pageValue);
    });
}

function initProductTypes() {
    $("#key_materialClass_container").removeClass("none");
    var pos = $("#key_materialClass").position();
    $("#key_materialClass_container").css({
        "top" : (pos.top + 44),
        "left" : pos.left - 3
    });
}
function initIndexNavItems() {
    $("#key_materialClass").bind("mouseenter", function(evt) {
        initProductTypes();
    });
    $(".plia").bind("mouseenter", function(evt) {
        $(".plid").addClass("none");
        $(".plia").removeClass("on");

        var id = evt.target.id;
        var detailDiv = $("#" + id + "_d");

        detailDiv.removeClass("none");
        $(evt.target).addClass("on");
    });
    $(".plid").bind("mouseleave", function(evt) {
        $(".plid").addClass("none");
        $(".plia").removeClass("on");
    });
    $("#key_materialClass_container").bind("mouseleave", function(evt) {
        {
            $(".plid").addClass("none");
            $(".plia").removeClass("on");
            if (!isIndex)
            {
                $("#key_materialClass_container").addClass("none");
            }
        }
    });
};
linkon.web.initPage = function() {
   /* initIndexNavItems();*/
    //linkon.web.refreshTopBar();
    /*linkon.web.initDialog();*/
    //linkon.web.topBarTimer=window.setTimeout(linkon.web.refreshTopBar, 1000*30);
    initPagination();
    initValidate();
};

