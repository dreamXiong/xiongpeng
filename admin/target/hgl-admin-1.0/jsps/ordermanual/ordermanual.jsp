<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="手动补单" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>	
	<meta charset="UTF-8">
	<title>手动补单</title>
	<script type="text/javascript">
	function updateOrderGroupStatus(orderId,orderStatus){
		$("#modalStatus").modal("show");
		$("#modalStatus #updStateDialogBtn").click(function(){
			$("#modalStatus").modal("hide");
			$.ajax({
				type:"post",
				url:"doUpdateManualOrder",
				data:{"orderId":orderId,"status":orderStatus},
				success:function(data){
					if(data=="true"){ 
						$("#orderStatus"+orderId).text("交易完成");
					}else if(data =="false"){
						$("#modalConfirm").modal("show");
					}
				}
			});
		});
	}
	</script>
</head>
<c:set value="orderManual" var="modalName"></c:set>
<body class="skin-blue fixed">
<div class="modal fade" id="modal">
	<div class="modal-dialog modal-sm">
    	<div class="modal-content">
      		<div class="modal-header">
       	 		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">Modal title</h4>
      		</div>
      		<div class="modal-body">
        		<p>One fine body&hellip;</p>
      		</div>
      		<div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		<button type="button" class="btn btn-primary">Save changes</button>
      		</div>
		</div>
  </div>
</div>
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>手动补单</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
	             <form action="" name="orderManualForm" id="key_${modalName}_qryFrm" class="form-horizontal" method="post">
	             <div class="row">
		         	<div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">流水号</label>
		                  <div class="col-sm-8">
		                    <input type="text" name="orderSerialNo" class="form-control">
		                  </div>
		                </div>
		              </div>
	              <div  class="col-sm-3">
	                <div class="form-group">
	                  <label class="col-sm-3 control-label">购买人</label>
	                  <div class="col-sm-8">
	                    <input type="text" name="userName" class="form-control">
	                  </div>
	                </div>
	              </div>	              	              
	              <div class="col-sm-3 ">
	              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="submitform()" >查询</a>
	              </div>
	             </div>
	             </form>
    		 	</div>
              <div class="box-body">
                  <div id="key_${modalName}_list" class="col-sm-12">
					<%@include file="ordermanuallist.jsp" %>
				  </div>
              </div>
   		</div>
    	</div>
    </section>
  </div>
</div>

<!-- 补单弹出层 -->
<div class="modal fade" id="modalStatus" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">异常补单提示</span>       		
      		</div>
     		<div class="modal-body" style="text-align:center;font-size:18px;font-weight:bold;font-family:serif;">
     			<label>您确认补单吗？</label>	
    		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="updStateDialogBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">取消</button>
    		</div>
    	</div>
	</div>
</div>


<!-- 补单弹出层 -->
<div class="modal fade" id="modalConfirm" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">异常补单提示</span>       		
      		</div>
     		<div class="modal-body" style="text-align:center;font-size:18px;font-weight:bold;font-family:serif;">
     			<label>补单失败</label>	
    		</div>
    		<div class="modal-footer" style="text-align:center">
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">确认</button>
    		</div>
    	</div>
	</div>
</div>

</body>
<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	/*表单提交*/
	function submitform(){
    	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		var frm = $("#" + frmId);
		var params=frm.serialize();
		$.ajax({
			type:"post",
			url:"doSearchResult",
			data:params,
			success:function(data){
				 $("#"+dataDomId).html(data);
			}
		});		
	 }
</script>
</html>
</tiles:put>
</tiles:insert>