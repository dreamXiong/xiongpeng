function showUserList(id){
	 $.ajax({
         type:"POST",
         url : ctx+"/letter/groupUserList",
         data:{
             gid:id
         },
         dataType:"json",
         success:function(response){
        	 $("#userGroups").children("li").remove(); 
        	 var len = response.userList.length;
        	 if(len>0){
        		 for(var i=0; i<len; i++){
        		 $("#userGroups").append( "<li >"
        				+" <input type='checkbox' id='"+response.userList[i].id+"'/>"+response.userList[i].userName
							
						
						+"</li>");
        		 }
        	 }
         }
	 });
}


function sendLetter(){
	var content = $("#content").val();
	var activityId = $("#activityId").val();
	if(content==""&&activityId==""){
		alert("请输入发送内容!");
		return false;
	}
	var uIdList = []; 
	var uNameList = []; 
	$('.two-list span.active').each(function(){
		uIdList.push($(this).siblings('input').val());
		uNameList.push($(this).siblings('input').attr('name'));
		
	});
	
	var nIds = []; //一个新的临时数组
	for(var i = 0; i < uIdList.length; i++) //遍历当前数组
	{
		//如果当前数组的第i已经保存进了临时数组，那么跳过，
		//否则把当前项push到临时数组里面
		var isExist = false;
		for(var n=0;n<nIds.length;n++){
			if (nIds[n]==uIdList[i]) {
				isExist = true;
				break;
			 };
		}
		if(isExist==false){
			nIds.push(uIdList[i]);
		}
		//alert(nIds.indexOf("122"));
		//if (nIds.indexOf(uIdList[i]) == -1) {nIds.push(uIdList[i])};
	}
	
	var nNames = [];
	for(var i = 0; i < uNameList.length; i++){
		var isExist = false;
		for(var n=0;n<nNames.length;n++){
			if (nNames[n]==uNameList[i]) {
				isExist = true;
				break;
			 };
		}
		if(isExist==false){
			nNames.push(uNameList[i]);
		}
		//if (nNames.indexOf(uNameList[i]) == -1) nNames.push(uNameList[i]);
	}
	
	if(nIds != '' && nIds.length >0){
		$("#submitLetterForm #userListIds").val(nIds);
		$("#submitLetterForm #userListNames").val(nNames);
		$('#submitLetterForm').submit();
	}else{
		alert("请选择发送用户");
	}
	setBox();
	
}
setInterval(interval,3000); 
function interval(){
$.ajax({                       
	type: "POST",
	url: ctx+"/letter/letterByTime",
	success: function(response){
		
		if(response==""){
			//$("#letterCountNum").html(0);
			return false;
		}else{                                           
		
			//$("#letterCountNum").html(response.count);
			//alert(response);  
			 $("#letterNum").html(response);
			 setBox();
		}
		//$("#letterCountNum").html(response.count);
	}
	
});

	
}

