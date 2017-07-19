function showUpdatePage(id){
	  $.ajax({
	        type: "POST",
	        url: "showUpdateTbWInfo",
	        data:{
	        	id:id,
	        },
	        success: function(response){
	        	$("#updateTab-modal").html(response);
	        	$("#goAddWInfo").empty();
	        	var provinceCode = $("#provinceCode").val();
	    		if(provinceCode != 0 && provinceCode!=null){
	    		 gradeChange();
	    		}
	        }
	    });
	  	
	  $('#updateTab-modal').modal('show');
	}
$(function() {
    $('.allot').click(function(event) {
      $('#allot').modal('show');
    });
 
   $('#addWInfo').click(function(event) {
	   
	   $.ajax({
	        type: "POST",
	        url: "goAddWarehouseInfo",
	        success: function(response){
	        	$("#cleanUpdateTable").empty();
	        	$("#addTab-modal").html(response);
	        }
	    });
        $('#addTab-modal').modal('show');
    });
  });
  $('#addTab-modal').on('hidden.bs.modal', function (e) {
		window.location.reload();
	});
  $('#updateTab-modal').on('hidden.bs.modal', function (e) {
		window.location.reload();
	});
  $('#allot').on('hidden.bs.modal', function (e) { 
		window.location.reload();
	});
  
  function formsubmit(){
		var name = $("#name").val();
		var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
	    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
	    var frm = $("#" + frmId);
	   var params=frm.serialize();;
		$.ajax({
	        type : "POST",
	        url : 'serachWarehouse',
	        data :params,
	        success : function(response) {
	            $("#" + dataDomId).html(response);
	        }
	    });
	} 
  function submitUpdateInfo(){
	  
	  
	  var country = $("#country").val();
	  var isnull = country == null || country =='' || country ==0;
	  if(!isnull){
		  $("#country").removeClass("onerrInput");
	  }else{
		  $("#country").addClass("onerrInput");
	  }
	  var addType = validateForm("cleanUpdateTable");
		if(!addType || isnull){
			return;
		}
	 /* if(!isNull($("#country").val()) || street == '0'){
		  $("#country").addClass("onerrInput");
	  }else{
		  $("#country").removeClass("onerrInput");
	  }
	  var addType = validateForm("cleanUpdateTable");
		if(!addType || !isNull($("#country").val())){
			return;
		}*/
	  $("#cleanUpdateTable").submit();
  }
  function changeStates(wId ,states){
	  var name = $("#searchText").val();
	  $.ajax({
	        type : "POST",
	        url : 'changeStates',
	        data :{
		        	wId:wId,
		        	states:states,
		        	name:name
	        },
	        success : function(response) {
	            $("#key_warehouse_list").html(response);
	        }
	    });
  }