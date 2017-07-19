

function agency(){
	var addType = validateForm("agency");
	if(!addType){
		return;
	}
	
	if($("#cbAgreement").prop('checked') == false){
		$("#cbAgreement").addClass("onerrInput");
		alert("请勾选同意用户注册协议");
		return ;
	}
	checkRecommCode();
	
}

	function checkRecommCode(){
		var recommendCode =$("#recommendCode").val();
		if(recommendCode==null||""==recommendCode){
			$("#agency").submit();
			return true;
		}
		$.ajax({
			type:"POST",
			url : 'checkRecommCode_ajax?recommendCode='+ recommendCode,
			dateType:'json',
			success : function(jsonList){
				if("error"==jsonList){
					toastr.error("该推荐码不存在", "操作提示:");
					return false;
				}
				$("#agency").submit();
			}
		});
	}
