<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="品牌列表" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>公司类别</title>
</head>
<body >
<!--文件上传 -->
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/companyType.js"></script> 
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
<c:set value="customerService" var="modalName"></c:set>
<div class="content-wrapper">

<style>
.table-bordered tr td {  
    text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
    border: 1px solid;  
    text-align: left  
}  
</style>
  	<section class="content-header">
       <h1>我的主页<small>公司类别</small>  </h1>
    </section>
    <input type="hidden" id="deleteId" value=""/>
    <section class="content">
    	<div class="row no-margin">
    	<input type="hidden" id="brandId">
    		<div class="box box-primary">
    			 <div class="box-body">
		              <div class="col-sm-4 ">
		                  <button onclick="showAdd();" type="button" class="btn btn-info">添加</button>
		              </div>
		              <div class="col-sm-12">
		                <div id="key_${modalName}_list">
		               		<%@include file="companyTypeList.jsp"%>
		                </div>
		              </div>
    			</div>
    		</div>
    	</div>
    </section>
  </div>
  
 <!--  添加弹出层 -->
<div class="modal fade" id="showdetailsInfo" data-backdrop="static">
  <%@include file="detailsCompanyType.jsp"%>
</div>

 <!--  添加弹出层 -->
<div class="modal fade" id="addProduct" data-backdrop="static">
  <%@include file="addCompanyType.jsp"%>
</div>

 <!--   修改弹出层 -->
<div class="modal fade" id="updateProduct" data-backdrop="static">
  <%@include file="updateCompanyType.jsp"%>
</div>

<!-- 产品删除弹出层 -->
<div class="modal fade" id="deleteProduct">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要删除该类型吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='delCustomerService();'>确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="notDeleteProduct">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body" >
        	有公司不能删除！
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
		
		 /* 表单提交 */
		/*   function submitform(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
				$.ajax({
			        type : "POST",
			        url : 'serachCustomerService',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  } */
	</script>
</html>
</tiles:put>
</tiles:insert>