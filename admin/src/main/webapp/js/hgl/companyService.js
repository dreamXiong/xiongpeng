 function submitform(){
		  var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		  var typeId = $('#testSelect option:selected').val();
		  var companyName = $("#companyName").val();
			$.ajax({
		        type : "POST",
		        url : 'serachInfo',
		        data : {
		        	typeId:typeId,
		        	companyName:companyName,
		        },
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
	 function showAddpage(){
		 window.location.href="showAddpage";
	 }
	 function deleteShow(id){
		 $("#deleteId").val(id);
		 $("#deleteProduct").modal("show");
	 }
	 function deleteInfo(){
		
		 var id = $("#deleteId").val();
		 $.ajax({
		        type : "POST",
		        url : 'deleteInfo',
		        data : {
		        	id:id,
		        },
		        success : function(response) {
		            $("#delete_"+id).remove();
		        }
		    }); 
		 $("#deleteProduct").modal("hide");
	 }
	 function showdetailsInfo(id){
		 $.ajax({
		        type : "POST",
		        url : 'showdetailsInfo',
		        data : {
		        	id:id,
		        },
		        success : function(response) {
		            $("#showdetailsInfo").html(response);
		        }
		    }); 
		 $("#showdetailsInfo").modal("show");
	 }
	 