<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="积分商城记录管理" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>积分商城记录管理</title>
    <script src="${ctx}/js/hgl/integralMallRecord.js"></script> 
</head>
<body >
<c:set value="integralMallRecord" var="modalName"></c:set>
<div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
		<small>积分商城记录管理</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form id="key_${modalName}_qryFrm" class="form-horizontal">
              <input type="hidden" id="integralMallRecordId" name="integralMallRecordId"/>
              <div  class="col-sm-4">
                <div class="form-group">
                  <label class="col-sm-2 control-label">商品名称</label>
                  <div class="col-sm-8">
                    <input type="text" name="goodsName" id="goodsName" class="form-control" placeholder="请输入查询的商品名称">
                  </div>
                </div>
              </div>
              <div class="col-sm-4 ">
                   <button type="button"  onclick="formsubmit()" class="btn btn-primary">查询</button>
              </div>
             </form>
              <div class="col-sm-12">
                <div id="key_${modalName}_list">
               		<%@include file="integralMallRecordList.jsp"%>
               </div>
              </div>
    		</div>
    		</div>
    	</div>
    </section>
  </div>
<!-- 二级类弹出框  deleteSecond-->
<div class="modal fade" id="integralMallRecordStatus">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>提示</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要发货吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button"  class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goStatusIntegralMallRecord()'>确定</button>
      </div>
    </div>
  </div>
</div>
</body>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
		/*************************************************************************************************/
		 function formsubmit(){
			//var name = $("#name").val();
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params = frm.serialize();
			$.ajax({
		        type : "POST",
		        url : 'searchResult',
		        data :params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    });
		} 
	</script>
</html>
</tiles:put>
</tiles:insert>