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


function is_numeric(val){
	if(is_null(val) || isNaN(val)){
		return false;
	}
	return true;
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


function initInputLabel(){
	$('input[type=text], input[type=password]').keyup(function(){
		refreshOneInputLabel($(this));
	});
	refreshInputLabels();
}
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
			$msg_pic.html('<img src="'+pa+'/images/common/cha.png"/>');
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
			$msg_pic.html('<img src="'+path+'/images/common/gou_ck.png"/>');
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



/*-------------------------*/

function updateMerchantsState(id){	
	 $.ajax({
	        type: "POST",
	        url: "../merchants/updateMerchantsState",
	        data: "id="+id,
	        async:false,
	        success: function(response){
	            if(response.state == 1){
	            	$("#statusTd_"+id).html("发布中");
        			$("#buttonA_"+id).children("a").eq(2).html("下线");	            	
        			$("#modal").modal("show");  //提示信息弹出层
        			
        			$("#modal #DialogBtn").click(function(){
        				queryMerchantNotice(id);
        				$("#modal").modal("hide"); 
        				$("#merchantsModal").modal("show");  //招商公告弹出层 
        				$("#merchantsModal #merchNoticeBtn").click(function(){
        					publishMerchNotice(id,1);  //1表示发布招商公告
        				});       				
        			});
	            	
	            }else if(response.state == 2){
	            	$("#statusTd_"+id).html("失效");
	            	$("#buttonA_"+id).children("a").eq(2).html("再次发布");
	            	publishMerchNotice(id,2);  //2表示下线招商
	            	alert("成功");
	            }else{
	            	alert("失败");
	            }
	        }
	   });
	 
	 /*发布招商公告 */
	 function publishMerchNotice(merchantId,typeId){
		 var isCheckError = true;
		 var merchNoticeName = $("#merchantsModal #merchNoticeName").val();
		 var merchNoticeDetail = $("#merchantsModal #merchNoticeDetail").val();
		 if(typeId==1){			 
			 if(merchNoticeName==""){
				 $("#merchNoticeNameError").text("公告名称不能为空");
				 $("#merchNoticeNameError").css("color","red");
				 $("#merchNoticeNameError").css("font-size","12px");
				 $("#merchantsModal #merchNoticeName").css("border","1px solid red");	
				 isCheckError = false;
			 }
			 
			 if(merchNoticeDetail==""){
				 $("#merchNoticeDetailError").text("公告详情不能为空");
				 $("#merchNoticeDetailError").css("color","red");
				 $("#merchNoticeDetailError").css("font-size","12px");
				 $("#merchantsModal #merchNoticeDetail").css("border","1px solid red");
				 isCheckError = false;
			 }
			 
			 if(isCheckError == false){
				 return;
			 }
		 }		 
		 
		 $.ajax({
				type:"post",
				url:"../merchants/publishMerchNotice",
				data:{"merchantId":merchantId,"name":merchNoticeName,
					  "detail":merchNoticeDetail,"typeId":typeId},
				success:function(data){
					if(data=="1"){
						$("#merchantsModal").modal("hide"); 
					}						
					location.reload(true);
				}
		});
		 
	 }
	 
	 //查询之前的招商公告
	 function queryMerchantNotice(merchantNoticeId){
		 $.ajax({
			 type:"post",
			 url:"../merchants/doSearchMerchNotice",
			 data:{"merchantId":merchantNoticeId},
			 async:false,
			 success:function(data){
				 var jsonData = eval(data);
				 for(var i=0;i<jsonData.length;i++){
					 $("#merchantsModal #merchNoticeName").val(jsonData[i].name);
					 $("#merchantsModal #merchNoticeDetail").val(jsonData[i].detail);
				 }
			 }
		 });
	 }
	 //点击取消按钮时，清除样式
	 $("#merchantsModal #merchNoticeCnl").click(function(){
		 $("#merchantsModal #merchNoticeName").removeAttr("style");
		 $("#merchNoticeNameError").text("");
		 $("#merchantsModal #merchNoticeDetail").removeAttr("style"); 
		 $("#merchNoticeDetailError").text("");
		 
	 });
	 
	 //获得光标，清除样式
	 $("#merchantsModal #merchNoticeName").focus(function(){
		 $("#merchantsModal #merchNoticeName").removeAttr("style");
		 $("#merchNoticeNameError").text("");
	 });
	 
	 //获得光标，清除样式
	 $("#merchantsModal #merchNoticeDetail").focus(function(){
		 $("#merchantsModal #merchNoticeDetail").removeAttr("style"); 
		 $("#merchNoticeDetailError").text("");
	 });
}

 

 
 
