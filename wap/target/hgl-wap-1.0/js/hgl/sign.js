function sign(){
	$.ajax({
         type: "POST",
         url: "sign",
         success: function(response){
        	if(response.code==1){//签到成功
            	location.reload();
        	}else if(response.code==0){//签到失败
        		
        	}
    		alert(response.msg);
         }
	});
}
