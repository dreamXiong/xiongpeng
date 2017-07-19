function allAuth(){
	var form = $("#talkForm");
	/*form.action = $("#ctx").val()+"/talk/authOpt";
	form.submit();*/
	
	
	//发送请求道后台，全部审核
   	$.ajax({
        type : "POST",
        url : $("#ctx").val()+"/talk/authOpt?time="+new Date().getTime(),
        data:form.serialize(),
        dataType:"json",
        success : function(response) {
        	var code = response.code;
        	if(code == "0000" || code == "0001"){
        		$("#flushBtn").click();
        	}else{
        		alert("操作失败，请稍后或者刷新后再试！");
        	}
        }
   	,
   	error:function(XMLHttpRequest, textStatus, errorThrown) {
   	 	//取数据出现错误
   		alert("操作失败，请稍后或者刷新后再试！");
   	 }
    });
}

function refuseOpt(id){
	
	//审核不通过
	//发送请求道后台，获取弹幕列表
   	$.ajax({
        type : "GET",
        url : $("#ctx").val()+"/talk/refuseOpt?talkId="+id+"&time="+new Date().getTime(),
        dataType:"json",
        success : function(response) {
        	var code = response.code;
        	if(code == "0000" || code == "0001"){
        		var tr = $("#tr_"+id);
        		tr.remove();
        		
        	}else{
        		alert("操作失败，请稍后或者刷新后再试！");
        	}
        }
   	,
   	error:function(XMLHttpRequest, textStatus, errorThrown) {
   	 	//取数据出现错误
   		alert("操作失败，请稍后或者刷新后再试！");
   	 }
    });
	
}

function flushData(){
	$.ajax({
        type : "GET",
        url : $("#ctx").val()+"/talk/flushList?time="+new Date().getTime(),
        success : function(response) {
        	$("#key_talk_list").html(response);
        }
   	,
   	error:function(XMLHttpRequest, textStatus, errorThrown) {
   	 	//取数据出现错误
   		alert(textStatus);
   		alert("操作失败，请稍后或者刷新后再试！");
   	 }
    });
}