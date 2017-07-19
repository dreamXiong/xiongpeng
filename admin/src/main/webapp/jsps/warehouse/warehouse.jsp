<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="仓库列表" />
	<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>仓库管理</title>
	  <script src="${ctx}/js/hgl/warehouse.js"></script> 
	  <script src="${ctx}/js/hgl/district.js"></script> 
</head>
	<style>
	.onerrInput {
		    border: 1px solid red!important;
		}
	</style>
<body class="skin-blue fixed">
	<c:set value="warehouse" var="modalName"></c:set>
	<input id="msgPosition" value="false" />
	<input id="warehouseId" value="" />
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
       			 我的主页
        <small>仓库管理</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			  <div class="box-body">
              <form id="key_${modalName}_qryFrm" class="form-horizontal">
	              <div  class="col-sm-4">  
	                <div class="form-group">
	                  <label class="col-sm-2 control-label">名称</label>
	                  <div class="col-sm-8">
	                    <input type="text" id="searchText" name="name" class="form-control" placeholder="请输入查询仓库名称">
	                  </div>
	                </div>
	              </div>
	              <div class="col-sm-4 ">
	                  <button type="button" class="btn btn-primary" onclick="formsubmit();">查询</button>
	                  <button type="button" class="btn btn-info" id="addWInfo">添加</button>
	              </div>
             </form>
	              <div class="col-sm-12">
		               <div id="key_warehouse_list">
		                	<%@include file="warehouseList.jsp"%>
		          		</div>
              	  </div>
    		 </div>
    		</div>
    	</div>
    </section>
  </div>
</div>

<!-- -------------------------------------------------------分配管理员开始 --------------------------------->
 
<div class="modal fade" id="allot" data-backdrop="static">
	<%@include file="warehouseUserPage.jsp"%>
</div>
<!-- -------------------------------------------------------分配管理员结束 --------------------------------->

<!-- 添加弹出框 -->
<div class="modal fade" data-backdrop="static" id="addTab-modal">
  <%@include file="addWarehousePage.jsp"%>
</div>
<!-- 更新弹出框 -->
<div class="modal fade" data-backdrop="static" id="updateTab-modal">
  <%@include file="updateWarehousePage.jsp"%>
</div>
</body>
    <script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
	</script>
</html>
</tiles:put>
</tiles:insert>