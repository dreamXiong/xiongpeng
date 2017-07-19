$("document").ready(function(){
	$("#noticeName").focus(function(){
		$("#noticeName").removeClass("border-red");
		$("#nameError").text("");
	});
	
	$("#noticeContent").focus(function(){
		$("#noticeContent").removeClass("border-red");
		$("#contentError").text("");
	});
	
	$("#createddatetime").focus(function(){
		$("#createddatetime").removeClass("border-red");
		$("#datetimeError").text("");
	});
	
	/*提交添加数据*/
	$("#addNoticeBtn").click(function(){
		var isError = true;
		
		var noticeName = $("#noticeName").val();
		var noticeContent = $("#noticeContent").val();
		var createddatetime = $("#createddatetime").val();
		
		/*公告名称不能为空*/
		if(noticeName=="" ){
			$("#nameError").text("公告名称不能为空");
			$("#nameError").addClass("errorText");
			$("#noticeName").addClass("border-red");
			isError = false;
		}
		
		/*公告内容不能为空 */
		if(noticeContent==""){
			$("#contentError").text("公告内容不能为空");
			$("#contentError").addClass("errorText");
			$("#noticeContent").addClass("border-red");
			isError = false;
		}
		
		/*发布时间不能为空*/
		if(createddatetime==""){
			$("#datetimeError").text("发布时间不能为空");
			$("#datetimeError").addClass("errorText");
			$("#createddatetime").addClass("border-red");
			isError = false;
		}
		
		if(!isError){
			return;
		}
		
		var params = $("#noticeInfoForm").serialize();
		$.ajax({
			type:"post",
			url:"addNoticeInfo",
			data:params,		  
		    success:function(data){
		    	if(data=="1"){
		    		$("#noticeName").text("");
				    $("#noticeType").eq(0);
					$("#noticeContent").text("");
					$("#createddatetime").text("");					
					alert("添加成功！");
					location.reload();
		    	}else{
		    		alert("添加失败！");
		    	}
		    }		  
		});			
	});	
	
	/*提交修改公告*/
	$("#updNoticeBtn").click(function(){
		var isError = true;
		var noticeName = $("#noticeName").val();
		var noticeContent = $("#noticeContent").val();
		
		/*公告名称不能为空*/
		if(noticeName=="" ){
			$("#nameError").text("公告名称不能为空");
			$("#nameError").addClass("errorText");
			$("#noticeName").addClass("border-red");
			isError = false;
		}
		
		/*公告内容不能为空 */
		if(noticeContent==""){
			$("#contentError").text("公告内容不能为空");
			$("#contentError").addClass("errorText");
			$("#noticeContent").addClass("border-red");
			isError = false;
		}			
		
		if(!isError){
			return;
		}
		
		noticeForm.action="updateNoticeInfo";
		noticeForm.submit();
	});
});


function deleteNoticeInfo(noticeId){
		if(confirm("您确认删除该条记录吗？")){
			var currentNode =document.getElementById("dtBtn"+noticeId);
			var targetNode = currentNode.parentNode;
			$.ajax({
				type:"post",
				url:"deleteNoticeInfo",
				data:{"id":noticeId},
				success:function(data){
					if(data=="1"){
						 var trtargetNode = targetNode.parentNode;
						 trtargetNode.remove();	
						 
						 submitform();
					}else{
						alert("操作失败！");
					}
				}			
			});	
		}
	}