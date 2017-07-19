$(function() {
	$('.attract-nav>ul>li').click(function(){
		var index = $(this).index();
		$(this).addClass('active').siblings('li').removeClass('active');
		$('.slide .show-bg').eq(index).show().siblings('.show-bg').hide();
	});
});

function is_null(obj){
	if(typeof(obj) == 'undefined' || obj == null || obj == ''){
		return true;
	}
	return false;
};

(function($){$.fn.jCarouselLite=function(o){o=$.extend({btnPrev:null,btnNext:null,btnGo:null,mouseWheel:false,auto:null,speed:1000,easing:null,vertical:false,circular:true,visible:1,start:0,scroll:1,beforeStart:null,afterEnd:null},o||{});return this.each(function(){var b=false,animCss=o.vertical?"top":"left",sizeCss=o.vertical?"height":"width";var c=$(this),ul=$("ul",c),tLi=$("li",ul),tl=tLi.size(),v=o.visible;if(o.circular){ul.prepend(tLi.slice(tl-v-1+1).clone()).append(tLi.slice(0,v).clone());o.start+=v}var f=$("li",ul),itemLength=f.size(),curr=o.start;c.css("visibility","visible");f.css({overflow:"hidden",float:o.vertical?"none":"left"});ul.css({margin:"0",padding:"0",position:"relative","list-style-type":"none","z-index":"1"});c.css({overflow:"hidden",position:"relative","z-index":"2",left:"0px"});var g=o.vertical?height(f):width(f);var h=g*itemLength;var j=g*v;f.css({width:f.width(),height:f.height()});ul.css(sizeCss,h+"px").css(animCss,-(curr*g));c.css(sizeCss,j+"px");if(o.btnPrev)$(o.btnPrev).click(function(){return go(curr-o.scroll)});if(o.btnNext)$(o.btnNext).click(function(){return go(curr+o.scroll)});if(o.btnGo)$.each(o.btnGo,function(i,a){$(a).click(function(){return go(o.circular?o.visible+i:i)})});if(o.mouseWheel&&c.mousewheel)c.mousewheel(function(e,d){return d>0?go(curr-o.scroll):go(curr+o.scroll)});if(o.auto)setInterval(function(){go(curr+o.scroll)},o.auto+o.speed);function vis(){return f.slice(curr).slice(0,v)};function go(a){if(!b){if(o.beforeStart)o.beforeStart.call(this,vis());if(o.circular){if(a<=o.start-v-1){ul.css(animCss,-((itemLength-(v*2))*g)+"px");curr=a==o.start-v-1?itemLength-(v*2)-1:itemLength-(v*2)-o.scroll}else if(a>=itemLength-v+1){ul.css(animCss,-((v)*g)+"px");curr=a==itemLength-v+1?v+1:v+o.scroll}else curr=a}else{if(a<0||a>itemLength-v)return;else curr=a}b=true;ul.animate(animCss=="left"?{left:-(curr*g)}:{top:-(curr*g)},o.speed,o.easing,function(){if(o.afterEnd)o.afterEnd.call(this,vis());b=false});if(!o.circular){$(o.btnPrev+","+o.btnNext).removeClass("disabled");$((curr-o.scroll<0&&o.btnPrev)||(curr+o.scroll>itemLength-v&&o.btnNext)||[]).addClass("disabled")}}return false}})};function css(a,b){return parseInt($.css(a[0],b))||0};function width(a){return a[0].offsetWidth+css(a,'marginLeft')+css(a,'marginRight')};function height(a){return a[0].offsetHeight+css(a,'marginTop')+css(a,'marginBottom')}})(jQuery);


$(function() {
	$("#botton-scroll").jCarouselLite({
		btnNext: ".next",
		btnPrev: ".prev"
	});
});

/**
 * 
 * @return {}
 */
function getNowFormatDate() {
	var day = new Date();
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var CurrentDate = "";
	Year = day.getFullYear();// 支持IE和火狐浏览器.
	Month = day.getMonth() + 1;
	Day = day.getDate();
	CurrentDate += Year;
	if (Month >= 10) {
		CurrentDate += Month;
	} else {
		CurrentDate += "0" + Month;
	}
	if (Day >= 10) {
		CurrentDate += Day;
	} else {
		CurrentDate += "0" + Day;
	}
	return CurrentDate;
};
function is_numeric(val){
	if(is_null(val) || isNaN(val)){
		return false;
	}
	return true;
};
function isChineseChar(str){   
   var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
   return reg.test(str);
}
function is_int(val){
	if(!is_numeric(val)){
		return false; 
	}
	if(val.toString().indexOf(".") >= 0){
		return false;
	}
	return true;
}; 
function is_id_card(value) {
	value = $.trim(value);
	value = value.replace(/ /g, ''); 
	
	var idCard = new RegExp(/(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/);
	if (!idCard.test(value)) {
		return false;
	}
	
	var len = value.length;
	if (len == 18) {
		var re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/);
		var arrSplit = value.match(re);
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return false;
		}
	} else if (len == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = value.match(re);
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return false;
		}
	}	
    return true;
}
function is_mobile(value){
	value = $.trim(value); 
	var length = value.length;
	var reg = /^((1[34578]{1}[0-9]{1})+\d{8})$/;
     return (length == 11 && reg.test(value));
};
function is_phone(value) {
	value = $.trim(value);
	var reg = /^\d{3,4}-\d{7,9}$/;
    return reg.test(value);
};
function is_url(val){
	return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(val);
};
function is_ip(ip){
	var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	return pattern.test(ip);
};
function is_email(val){   
	val = val.trim();
    var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;   
    return re.test(val);   
};
function is_json_empty(json){
	if(is_null(json)){
		return true;
	} 
    var val = true; 
    $.each(json, function(){
    	val = false;
    	return false;
    });
    return val;
};
/** 判断是否保存到小数点后两位 */
function is_two_scale(value){
	var twoScale = new RegExp(/(^\d+(\.\d{0,2})?$)/);
	if(!is_numeric(value)){
		return false; 
	}
	if(!twoScale.test(value)){
		return false;
	}
	return true;
};
function check_length(val, min, max){
	if(is_null(val)){
		if(min != 0){
			return false;
		}else{
			return true;
		}
	}else{
		var len = 0;    
	    for (var i=0; i<val.length; i++) {    
	        if (val.charCodeAt(i)>127 || val.charCodeAt(i)==94) {    
	             len += 2;    
	         } else {    
	             len ++;    
	         }    
	     }
		if(is_numeric(min) && len < min){
			return false;
		}
		if(is_numeric(max) && len > max){
			return false;
		}
	}
	return true;
};

function get_byte_range_length(value){
	var length = value.length;
	for (var i=0; i < value.length; i++) {
	 	if(value.charCodeAt(i) > 127)
			length++;
	}
	return length;
};

function isIE6(){
	return ($.browser.msie && ($.browser.version == 6.0) && !$.support.style);
};
function isChrome(){
	return (navigator.userAgent.toLowerCase().match(/chrome/) != null);
};

function formatTimeFromLong(value,pattern) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
        if (isNaN(dt)) {
            value = value.replace(/\/Date(−?\d+)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式  
            dt = new Date();
            dt.setTime(value);
        }
    }
    return dt.format(pattern); //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义  
}

String.prototype.trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
};
String.prototype.ltrim = function() { 
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function() { 
	return this.replace(/(\s*$)/g, ""); 
};


function get_url_param(name) { 
    return get_url_param_by_location(name, window.location);
};
function get_url_param_by_location(name,location) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = location.search.substr(1).match(reg);
    if (r != null){
    	return unescape(r[2]); 
    }
    return null;
};
function focusInput(input_id) { 
	if($('#'+input_id).length > 0){
		var len = $('#'+input_id).val().length;
		focusInputEx(input_id, len, len);
		$('#'+input_id).focus();
	}
};
function focusInputEx(input_id, selectionStart, selectionEnd) { 
	setSelectionRange(document.getElementById(input_id), selectionStart, selectionEnd);
};
function setSelectionRange(input, selectionStart, selectionEnd) {
	if (input.setSelectionRange) {
	    input.focus();
	    input.setSelectionRange(selectionStart, selectionEnd);
	}
	else if (input.createTextRange) {
	    var range = input.createTextRange();
	    range.collapse(true);
	    range.moveEnd('character', selectionEnd);
	    range.moveStart('character', selectionStart);
	    range.select();
	}
};


function splits(tranvalue){
	var value = new Array('','');
	var temp = tranvalue.split(".");
	for(var i=0; i< temp.length; i++){
		value[i] = temp[i];
	}
	return value;
};







function refreshInputLabels(){
	$('input[type=text], input[type=password]').each(function(){
		refreshOneInputLabel($(this));
	});
}
function refreshOneInputLabel($input){
	var id = $input.attr("id");
	var $label = $input.parent().find("label");
	if(!is_null($label) 
			&& $label.attr("for") == id){
		if($input.val() != ""){
			$label.hide();
		}else {
			$label.show();
		}
	}
}



/*
 * Usage:
 */

var CK_TRUE = 'TRUE';
var CK_FALSE = 'FALSE';
var CK_IGNORE = 'IGNORE';//erase effect before
var CK_NONE = 'NONE';//do nothing

/**
 * 判断是否有class="input_ck_err",没有就添加该属性
 * @param $input
 * @return
 */
function f_ck_set_input_err($input){
	if(!$input.hasClass('input_ck_err')){
		$input.addClass('input_ck_err');
	}
};
/**
 * 检查错误信息
 * @param $input
 * @param message
 * @return
 */
function f_ck_error($input, message){
	f_ck_set_input_err($input);
	var $div_msg = get_div_msg($input);
	f_ck_error_div_msg($div_msg, message);
};
/**
 * 显示错误信息
 * @param $div_msg
 * @param message
 * @return
 */
function f_ck_error_div_msg($div_msg, message){
	if($div_msg.length >0){
		$div_msg.show();
		
		var pa = path;
		var $msg_pic = $div_msg.find('.msg_pic');
		var $msg_content = $div_msg.find('.msg_content'); 
		if($msg_pic.length >0){
			$msg_pic.html('');
			$msg_pic.show();
		} 
		if($msg_content.length >0 && !is_null(message)){
			$msg_content.css('color', 'red');
			$msg_content.html(message);
			$msg_content.show();
		}else{ 
			$msg_content.hide();
		}
	}
};
/**
 * 检查正确信息
 * @param $input
 * @param message
 * @return
 */
function f_ck_ok($input, message){
	f_ck_set_input_ok($input);
	var $div_msg = get_div_msg($input);
	f_ck_ok_div_msg($div_msg, message);
};
/**
 * 显示正确信息
 * @param $div_msg
 * @param message
 * @return
 */
function f_ck_ok_div_msg($div_msg, message){
	if($div_msg.length >0){
		$div_msg.show();
		
		var $msg_pic = $div_msg.find('.msg_pic');
		var $msg_content = $div_msg.find('.msg_content');
		if($msg_pic.length >0){
			$msg_pic.html('<img src="'+path+'/images/gou_ck.png"/>');
			$msg_pic.show();
		}
		if($msg_content.length >0 && !is_null(message)){
			$msg_content.css('color', '#82B000');
			$msg_content.html(message);
			$msg_content.show();
		}else{
			$msg_content.hide();
		}
	}
};
/**
 * 判断是否有class="input_ck_err",有就移除该属性
 * @param $input
 * @return
 */
function f_ck_set_input_ok($input){
	if($input.hasClass('input_ck_err')){
		$input.removeClass('input_ck_err');
	}
};

function f_ck_info($input, message){
	f_ck_set_input_ok($input);
	var $div_msg = get_div_msg($input); 
	f_ck_info_div_msg($div_msg, message);
};
function f_ck_info_div_msg($div_msg, message){
	if($div_msg.length >0){
		$div_msg.show();
		
		var $msg_pic = $div_msg.find('.msg_pic');
		var $msg_content = $div_msg.find('.msg_content');
		$msg_pic.hide();
		if($msg_content.length >0){
			if(!is_null(message)){
				$msg_content.css('color', '#8d8d8d');
				$msg_content.html(message);
				$msg_content.show();
			}else{
				$msg_content.hide();
			}
		}
	}
};
/**
 * 等到相应对象的显示信息
 * @param $input
 * @return
 */
function get_div_msg($input){
	/**
	 * 在一级父级元素上找class="msg"，找到则返回
	 */
	var $parent = $input.parent();
	var $div_msg = $parent.find('.msg');
	if($div_msg.length >0){
		return $div_msg;
	}
	/**
	 * 在二级父级元素上找class="msg"，找到则返回
	 */
	$parent = $input.parent().parent();
	$div_msg = $parent.find('.msg');
	if($div_msg.length >0){
		return $div_msg;
	}
	/**
	 * 在三级父级元素上找class="msg"，找到则返回
	 */
	$parent = $input.parent().parent().parent();
	$div_msg = $parent.find('.msg');
	if($div_msg.length >0){
		return $div_msg;
	}
	/**
	 * 在四级父级元素上找class="msg"，找不找的到都返回
	 */
	$parent = $input.parent().parent().parent().parent();
	$div_msg = $parent.find('.msg');
	return $div_msg;
};

var HAD_BIND_CHECK_FORM_BLUR = false;
function check_form(msg, check_data, is_just_checking, is_just_this_id){ 
	var result = true;
	var first_err_id = '';
	$.each(check_data, function(k,v){
		var id = k;
		if(is_null(is_just_this_id) || is_just_this_id == id){
			var $item = $('#'+id);
			var val = $item.val();
			var note = check_data[k];
			if(note == "RICHTEXT"){
				val = "";
			}
			var item_check_result = eval(id+'("'+val+'")');
			note = check_data[k];//set note again
			
			if(item_check_result == CK_FALSE){
				result = false;
				f_ck_error($item, note);
				if(is_null(first_err_id)){
					first_err_id = id;
				}
			}else if(item_check_result == CK_TRUE){
				f_ck_ok($item, "");
			}else if(item_check_result == CK_IGNORE){//default:erase effect before
				f_ck_info($item, "");
			}else if(item_check_result == CK_NONE || is_null(item_check_result)){//do nothing
				result = false;
			}
			
			if(!HAD_BIND_CHECK_FORM_BLUR){
				bindCheckFormBlur(msg, check_data, id);
				bindCheckFormKeyUp(msg, check_data, id);
			}
		}
	}); 
	HAD_BIND_CHECK_FORM_BLUR = true;
	
	if(!result && is_just_checking != true && !is_null(first_err_id)){
		var $first_item = $('#'+first_err_id);
		if($first_item.is('input') && $first_item.attr('type') == 'text'){
			focusInput(first_err_id);
		}
		$first_item.focus();
	}
	if(is_just_checking != true && result && is_null(msg) == false){
		result = confirm(msg);
	}
	return result; 
};

function bindCheckFormBlur(msg, check_data, id) {
	var $item = $('#'+id);
	$item.blur(function(){
		check_form(msg, check_data, true, id);
	});
};

function bindCheckFormKeyUp(msg, check_data, id) {
	var $item = $('#'+id);
	$item.keyup(function(){
		check_form(msg, check_data, true, id); 
	}); 
};



/**
 * 校验图片验证码
 */
function vcodeImage(){
    var val = $('#vcodeImage').val().trim();
    if(!check_length(val,4,4)){
        ck_data['vcodeImage'] = "4个字符";
        return CK_FALSE;
    }
    if(!ischeckVcodeImage){
		 ck_data["vcodeImage"] = "图片验证码错误";
	        return CK_FALSE;
	}
    return CK_TRUE;
};




/**
 * 检查手机验证码
 */
function vcodePhone() { 
	var val = $('#vcodePhone').val().trim();
	if(!check_length(val,4,4) || !is_numeric(val)){
        ck_data["vcodePhone"] = "为4个数字";
        return CK_FALSE;
    } 
	if(!ischeckVcodePhone){
		 ck_data["vcodePhone"] = "手机验证码错误";
	        return CK_FALSE;
	}
    return CK_TRUE;
};

/**
 * 发送验证码之前做必要校验
 * @param {} checkdata
 * @return {}
 */
function checkBeforeSendVcode(checkdata){
	var checkresult = true;
	delete checkdata['vcodePhone'];//从数组中先删除掉验证码，发送前不校验验证码
	if(isExitsFunction("confirmActionEx")){
		checkresult = confirmActionEx('',checkdata);
	}else{
		checkresult = confirmAction('',checkdata);
	}
	checkdata['vcodePhone'] = '';
	return checkresult;
}
function isExitsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}

/**
 * 检查姓名
 */
function realName() {
	var val = $('#realName').val().trim();
	var reg = /^[\u4E00-\u9FA5]+$/; 
	if(is_null(val)){
		ck_data["realName"] = "姓名不能为空";
        return CK_FALSE;
	}
	if(!check_length(val,2,20) || !reg.test(val)){
        ck_data["realName"] = "姓名必须为中文";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

/**
 * 检查省市
 */
function userCity() {
    var val1 = $('#userProvince').val();
    var val2 = $('#userCity').val();
    if(parseInt(val1) == 0 || is_null(val2) || parseInt(val2) == 0){
        ck_data["userCity"] = "请您选择居住城市";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function filterItem1() {
    return filterItemInner("filterItem1");
};

function filterItemInner(filterItemId) {
    var hasChecked = false;
    $(".filterCheckBox").each(function(){
        if($(this).attr("checked")){
        	hasChecked = true;
        	return false;
        }
    });
    
    if(hasChecked == false){ 
    	ck_data[filterItemId] = "至少需要一个条件";
    	return CK_FALSE; 
    }
    return CK_TRUE;
};



/**
 * 按钮点击后，询问确认
 */
function confirmAction(msg, ckData, isTrimInput) {
	if(isTrimInput){
		var textboxList = $("input:text");
		for (var i = 0; i < textboxList.size(); i++) {
            textboxList[i].value=textboxList[i].value.trim();
  		}
	}
    if(check_form(msg, ckData)){
        return true;
    }
    return false;
}; 

/**
 * 按钮点击后，询问确认
 */
function confirmAction2(msg) {
    if(confirm(msg)){
        return true;
    }
    return false;
}; 

var isUsernameExisted = false;
$(function(){
	$("#userName").blur(function(){
		isUsernameExisted = false;//只要重新填写了用户名，都认为用户名是没有被注册的
	    f_ck_info($("#userName"), "用户名即昵称由2到20个字符组成，注册后不可修改");
	    chcekNameExistUsername();
	});
});



var usernameExistedMsg = "用户名已被注册";
function chcekNameExistUsername() {
    if(userName() == CK_TRUE){
        var name = $("#userName").val().trim();
        $.ajax({
	        type: "POST",
	        url:ctx+'/register/checkName?username='+name,
	        success: function(response){
	            if(response.errcode == '0'){
	            	isUsernameExisted = false;
	            	 f_ck_ok($("#usernameMsg"), "恭喜，该用户名可以注册"); 
	            }else{
	            	isUsernameExisted = true;
	            	f_ck_error($("#usernameMsg"), usernameExistedMsg); 
	            }
	        }
	    });
    }
};

var isMobileExisted = false;
$(function(){
	$("#mobile").blur(function(){
		isMobileExisted = false;
	    f_ck_info($("#mobile"), "请输入号码开头为13,14,15,17,18,共11位");
	    chcekUserPhoneAjax();
	});
});


var phoneExistedNote="该手机已被使用";
function chcekUserPhoneAjax() {
    if(mobile() == CK_TRUE){
        var phone = $("#mobile").val().trim();
        $.ajax({
	        type: "POST",
	        url:ctx+'/register/checkMobile?mobile='+phone,
	        success: function(response){
	            if(response.errcode == '0'){
	            	isMobileExisted = false;
	            	 f_ck_ok($("#mobileMsg"), "恭喜，该手机可以注册"); 
	            }else{
	            	isMobileExisted = true;
	            	f_ck_error($("#mobileMsg"), phoneExistedNote); 
	            }
	        }
	    });
    }
};

$(function() {
	$(document).delegate(".verify-img","click",function(){
		 $(".verify-img img").attr("src",ctx+"/login/authImage?K="+Math.random());
	});
	
});
var ischeckVcodeImage=false;
$(function(){
$("#vcodeImage").blur(function(){
	var imageCode = $("#vcodeImage").val();
	if(imageCode==""){
		return;
	}
	
	$.ajax({
		type:"post",
		url:"checkVerify",
		data:{"identCode":imageCode},
		success:function(data){
			if(data=="true"){
				ischeckVcodeImage= true;
				 f_ck_ok($("#imageVcodeMsg"), "验证码正确！"); 				
			}else{
				ischeckVcodeImage=false;
				f_ck_error($("#imageVcodeMsg"), "验证码错误！"); 
			}
		}
		
	});
});

});
var ischeckVcodePhone = false;
$(function(){
$("#vcodePhone").blur(function(){
	var imageCode = $("#vcodePhone").val();
	if(imageCode==""){
		return;
	}
	
	$.ajax({
		type:"post",
		url:"checkPhoneCode",
		data:{"identCode":imageCode},
		success:function(data){
			if(data=="true"){
				ischeckVcodePhone=true;
				 f_ck_ok($("#phoneVcodeMsg"), "手机验证码正确！"); 				
			}else{
				ischeckVcodePhone = false;
				f_ck_error($("#phoneVcodeMsg"), "手机验证码错误！"); 
			}
		}
		
	});
});
});


var ischeckBrandName = false;
$(function(){
$("#brandName").blur(function(){
	var brandName = $("#brandName").val();
	var prodcutTypeId = $("#productTypeId").val();
	if(brandName==""){
		return;
	}
	
	$.ajax({
		type:"post",
		url:"checkBranName",
		data:{"productTypeId":prodcutTypeId,"brandName":brandName},
		 success: function(response){
	            if(response.errcode == '0'){
	            	 f_ck_ok($("#brandMsg"), "恭喜，此品牌可以注册"); 
	            }else{
	            	ischeckBrandName = true;
	            	f_ck_error($("#brandMsg"), "此分类下的此品牌已存在"); 
	            }
	        }
		
	});
});
});


var ischeckRecommedCode = false;
$(function(){
$("#recommendCode").blur(function(){
	var recommendCode = $("#recommendCode").val();
	if(recommendCode!=""){
	$.ajax({
		type:"post",
		url:"checkRecommedCode",
		data:{"recommendCode":recommendCode},
		 success: function(response){
	            if(response.errcode == '0'){
	            	 f_ck_ok($("#rcodeMsg"), response.msg); 
	            }else{
	            	ischeckBrandName = true;
	            	f_ck_error($("#rcodeMsg"), response.msg); 
	            }
	        }
		
	});
	}
});
});

function name() {
    var val = $('#name').val().trim();
	var reg = /^[\u4E00-\u9FA5]+$/; 
	if(is_null(val)){
		ck_data["name"] = "姓名不能为空";
        return CK_FALSE;
	}
	if(!check_length(val,2,20) || !reg.test(val)){
        ck_data["name"] = "必须为中文不允许其他特殊符号或空格,并且长度为2-20字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};
function userName() {
    var val = $('#userName').val();
    var regname = /^[\u4e00-\u9fa5\w]+$/;
   	var regnum = /^\d+$/;
    if(!check_length(val,2,20)){
       ck_data["userName"] = "长度为2-20个字符";
        return CK_FALSE;
    } 
    if(!regname.test(val) || regnum.test(val)){
		ck_data['userName'] = "不能为纯数字，不允许其他特殊符号或空格";
		return CK_FALSE;
	}
    if(isUsernameExisted){
		ck_data['userName'] = usernameExistedMsg;
		return CK_FALSE;
	}
    return CK_TRUE;
};
function password(){
    return passwordInner("password", "长度为6-20字符");
};
function isNull(str) {
    if (str == "")
        return true;
    var regu = "^[ |\\n|\\r]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

function isNotNull(str) {
    return !isNull(str);
}
function qrpassword() {
    var val = $('#password').val();
    var val2 = $('#qrpassword').val();
    if(isNull(val) ){
       ck_data["password"] = "请先设置密码";
        return CK_FALSE;
    } 
    if(val != val2){
    	 ck_data["qrpassword"] = "输入与设置密码不同";
         return CK_FALSE;
    }
    return CK_TRUE;
};

function street() {
	 var val = $('#province').val();
	 var val2 = $('#street').val();
    if(parseInt(val) == 0 || parseInt(val2) == 0){
        ck_data["street"] = "请选择正确的所属地区";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function regAddress(){
	var val = $('#regAddress').val();
	var val1 = $('#street').val();
   if(is_null(val) ){
        return CK_TRUE;
    } 
	if(is_null(val1)&& !is_null(val)){
		ck_data["street"] = "如果填写具体地址请先选择地区";
        return CK_FALSE;
	}
	return CK_TRUE;
}
/*function regAddress() {
    var val = $('#regAddress').val();
    if(!is_numeric(val)){
        ck_data["regAddress"] = "不能为空";
        return CK_FALSE;
    } 
    return CK_TRUE;
};*/

function email() {
    var val = $('#email').val();
    if(is_null(val) ){
        return CK_TRUE;
    } 
    if(!is_email(val)&& !is_null(val)){
        ck_data["email"] = "邮箱格式不正确";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function recommendCode() {
    var val = $('#recommendCode').val();
    if(is_null(val) ){
        return CK_TRUE;
    } 
    if(!ischeckVcodeImage && !is_null(val)){
        ck_data["recommendCode"] = "验证码不正确";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function regAddress() {
    var val = $('#regAddress').val();
    if(is_null(val) ){
        return CK_TRUE;
    } 
    if(!check_length(val,0,50)&& !is_null(val)){
        ck_data["regAddress"] = "长度0-25字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function mobile() {
    var val = $('#mobile').val();
    if(!is_mobile(val)){
        ck_data["mobile"] = "请输入正确的手机号码";
        return CK_FALSE;
    }
    if(isMobileExisted){
    	ck_data["mobile"] = phoneExistedNote;
    	return CK_FALSE;
    }
    return CK_TRUE;
};

function vcodeImage(){
    var val = $('#vcodeImage').val().trim();
    if(!check_length(val,4,4)){
        ck_data['vcodeImage'] = "4个字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function cbAgreement(){
	if($("#cbAgreement").prop('checked') == false){
		//alert("请勾选同意用户注册协议");
		 ck_data['cbAgreement'] = "请勾选同意用户注册协议";
		return CK_FALSE;
	}
	return CK_TRUE;
};

/**
 * 密码校验工具方法
 */
function passwordInner(id, msg){
    var val = $('#' + id).val();
    if(!check_length(val,6,20)){
        ck_data[id] = msg;
        return CK_FALSE;
    } 
    
    if(val.indexOf(" ") >=0 || isChineseChar(val)){
    	ck_data[id] = "英文字符、数字或特殊字符。不能是空格";
        return CK_FALSE;
    }
    return CK_TRUE;
};


function vcodePhoneGet(type){
	var val = $('#mobile').val();
	if(mobile() == CK_TRUE){
		curCount = count;
	    $.ajax({
	        type: "POST",
	        data:{"type":type,"phoneNum":val},
	        url:ctx+'/register/getVodePhone',
	        success: function(response){
	        	if(response==""){
	        		//设置button效果，开始计时
	        		$("#btnSendCode").attr("disabled", "true");
	        		$("#btnSendCode").val("重发验证码(" + curCount + "s)");
	        		InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
	        	}else{
	        		alert(response);
	        	}
	        }
	    });    
	}else if(!is_mobile(val)){
        ck_data["mobile"] = "请先输入手机号码";
        $("#mobile").focus();
	    return false;
    }else if(isMobileExisted){
    	ck_data["mobile"] = phoneExistedNote;
    	return false;}
}



function companyName() {
    var val = $('#companyName').val();
    if(!check_length(val,2,50)){
        ck_data["companyName"] = "长度为2-50字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function shopName() {
    var val = $('#shopName').val();
    if(!check_length(val,2,50)){
        ck_data["shopName"] = "长度为2-50字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function brandName() {
    var val = $('#brandName').val();
    if(!check_length(val,2,20)){
        ck_data["brandName"] = "长度为2-20字符";
        return CK_FALSE;
    } 
    if(ischeckBrandName){
    	 ck_data["brandName"] = "此分类下此品牌已存在";
         return CK_FALSE;
    }
    var options=$("#productTypeId option:selected");
    $("#productTypeName").val(options.text());
    return CK_TRUE;
};

function scope() {
    var val = $('#scope').val();
    if(!check_length(val,2,60)){
        ck_data["scope"] = "长度为2-60字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};



function companyTel() {
    var val = $('#companyTel').val();
    if(!is_phone(val)){
        ck_data["companyTel"] = "请填充正确的号码如:0755-88888888";
        return CK_FALSE;
    } 
    return CK_TRUE;
};
function legalRepresentative() {
    var val = $('#legalRepresentative').val();
    if(!check_length(val,2,10)){
        ck_data["legalRepresentative"] = "长度为2-10字符";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function shopImage3() {
    var val = $('#shopImage1_Path').val();
   // var val1 = $('#shopImage2_Path').val();
   // var val2 = $('#shopImage3_Path').val();
    if(val==""){
       ck_data["shopImage3"] = "第一张图片为必上传";
        return CK_FALSE;
    } 
    return CK_TRUE;
};

function organizationImage1() {
    var val = $('#licenseImage1_Path').val();
    var val1 = $('#taxpayerImage1_Path').val();
    var val2 = $('#organizationImage1_Path').val();
    if(val=="" || val1=="" ||val2==""){
       ck_data["organizationImage1"] = "请上传营业执照，税务登记证，组织机构证";
        return CK_FALSE;
    } 
    return CK_TRUE;
};


function checkCapital(){
 var e = event||window.event; 
  var keyCode = e.keyCode||e.which;
  if(keyCode==20){
	  f_ck_error($("#passwordMsg"), "大写锁定已开启！"); 
  }else{
	  f_ck_error($("#passwordMsg"), "");  
  }
}

function checkCapital1(){
	 var e = event||window.event; 
	  var keyCode = e.keyCode||e.which;
	  if(keyCode==20){
		  f_ck_error($("#passwordMsg1"), "大写锁定已开启！"); 
	  }else{
		  f_ck_error($("#passwordMsg1"), "");  
	  }
	}



var InterValObj; //timer变量，控制时间
var count = 70; //间隔函数，1秒执行
var curCount;//当前剩余秒数

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
    }
    else {
        curCount--;
        $("#btnSendCode").val("重发验证码(" + curCount + "s)");
    }
}