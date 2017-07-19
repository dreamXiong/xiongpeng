<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="经销商微信支付配置" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>经销商微信支付配置</title>
	 <script src="${ctx}/js/hgl/dealersWeixinConfig.js"></script> 
</head>
<body >
<c:set value="dealersWeixinConfig" var="modalName"></c:set>
<div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
		<small>经销商微信支付配置</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">
    	<input type="hidden" id="dealersWeixinConfigId">
    	<input type="hidden" id="status">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form id="key_${modalName}_qryFrm" class="form-horizontal">
              <div  class="col-sm-4">
                <div class="form-group">
                  <label class="col-sm-2 control-label">店铺名称</label>
                  <div class="col-sm-8">
                    <input type="text" name="shopName" id="shopName" class="form-control" placeholder="请输入查询的店铺">
                  </div>
                </div>
              </div>
              <div class="col-sm-4 ">
                   <button type="button"  onclick="formsubmit()" class="btn btn-primary">查询</button>
                  <button onclick="window.location.href = 'getInsertDealersWeixinConfigPage'" type="button" class="btn btn-info">添加</button>
              </div>
             </form>
              <div class="col-sm-12">
                <div id="key_${modalName}_list">
               		<%@include file="dealersWeixinConfigList.jsp"%>
               </div>
              </div>
    		</div>
    		</div>
    	</div>
    </section>
  </div>
<!-- 二级类弹出框  deleteSecond-->
<div class="modal fade" id="dealersWeixinConfig">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>提示</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要删除该微信支付配置吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button"  class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goDeleteDealersWeixinConfig()'>确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 二级类弹出框  deleteSecond-->
<div class="modal fade" id="dealersWeixinConfigStatus">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>提示</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要修改微信支付配置状态吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button"  class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goStatusDealersWeixinConfig()'>确定</button>
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