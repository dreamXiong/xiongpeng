/*启用禁用*/
function updateWebUserStatus(id){
		
	$("#modalState").modal("show");	
	
	$("#updStateDialogBtn").click(function(){
		var state = $("input[name='state']:checked").val();
		
		if(state==null){
			return;
		}
		
		$.ajax({
			type:"post",
			url:"doUpdateWebUserStatus",
			data:{"id":id,"state":state},
			success:function(data){
				if(data=="true"){
					$("#modalState").modal("hide");
					if(state==1){
						$("#td"+id).text("启用");
					}else if(state==3){
						$("#td"+id).text("禁用");						
					}					
				}
			}
		});
	});	

}



/*重置密码*/
function updateWebUserPsd(id){
	$('#modal').modal('show');
		
	$("#updDialogBtn").click(function(){
		$.ajax({
			type:"post",
			url:"doUpdateWebUserPassword",
			data:{"id":id},
			success:function(data){
				if(data=="true"){
					$('#modal').modal('hide');
				}				
			}			
		});		
	});
}