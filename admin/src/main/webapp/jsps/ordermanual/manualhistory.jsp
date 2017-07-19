<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="手动补单" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>	
	<meta charset="UTF-8">
	<title>手动补单记录</title>
</head>
<c:set value="orderManual" var="modalName"></c:set>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>手动补单记录</small>
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
	              <div  class="col-sm-3">
	                <div class="form-group">
	                	<label class="col-sm-3 control-label">补单人</label>
	                  	<div class="col-sm-8">
	                    	<input type="text" name="operateBy" class="form-control">
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
					<%@include file="manualhistorylist.jsp" %>
				  </div>
              </div>
   		</div>
    	</div>
    </section>
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
			url:"doSearchManualOrderResult",
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