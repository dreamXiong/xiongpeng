function authOpt(id){
	//审核不通过
	//发送请求道后台，获取弹幕列表
   	$.ajax({
        type : "GET",
        url : $("#ctx").val()+"/vote/authOpt?id="+id+"&time="+new Date().getTime(),
        dataType:"json",
        success : function(response) {
        	var code = response.code;
        	if(code == "0000" || code == "0001"){
        		
        		window.open($("#ctx").val()+"/vote/authList", "_self");
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