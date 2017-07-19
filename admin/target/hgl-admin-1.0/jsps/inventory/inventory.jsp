<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="库存管理" />
	<tiles:put name="body" type="String">
	<c:set value="inventory" var="modalName"></c:set>	
<html lang="en">
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="${ctx}/js/hgl/inventory.js"></script>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
	<header class="main-header">
  <a href="javascript:;" class="logo">
   惠给利后台管理系统
  </a>
  <nav class="navbar navbar-static-top" role="navigation">
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li>
        	<a href="javascript:;">当前用户 : admin</a> 
        </li>
        <li>
        	<a href="javascript:;">修改密码</a>
        </li>
         <li>
        	<a href="javascript:;">安全退出</a>
        </li>
      </ul>
    </div>
  </nav>
</header>
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>库存管理</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm">
             <div class="row">
              <div  class="col-sm-2">
                <div class="form-group">
                  <label class="col-sm-4 control-label">名称</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="请输入查询名称" name="name">
                  </div>
                </div>
              </div>
              <div  class="col-sm-2">
                <div class="form-group">
                  <label class="col-sm-4 control-label">大类</label>
                  <div class="col-sm-8">
                    <select class="form-control" name="mainId" id="mainIdSelect">
                      <option value="-1">---请选择---</option>
                      <c:forEach items="${mList}" var="item" varStatus="s">
	                      <option value="${item.id}">${item.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
              </div>
              <div  class="col-sm-2">
                <div class="form-group">
                  <label class="col-sm-4 control-label" >品牌</label>
                  <div class="col-sm-8">
                    <select class="form-control" name="brandId" id="brandIdSelect">
                      <option value=""></option>
                    </select>
                  </div>
                </div>
              </div>
              <div  class="col-sm-2">
                <div class="form-group">
                  <label class="col-sm-4 control-label">产品</label>
                  <div class="col-sm-8">
                    <select class="form-control" name="productId" id="productIdSelect">
                      <option value=""></option>
                    </select>
                  </div>
                </div>
              </div>
              <div  class="col-sm-2">
                <div class="form-group">
                  <label class="col-sm-4 control-label">状态</label>
                  <div class="col-sm-8">
                    <select class="form-control" name="status">
                      <option value="-1">全部</option>
                      <option value="1">上架中</option>
                      <option value="0">已下架</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-sm-2 ">
                  <button type="submit" class="btn btn-primary" onclick="submitform()">查询</button>
                  <button type="button" class="btn btn btn-primary">批量定价</button>
              </div>
             </div>
             </form>
	             <div id="key_${modalName}_list" class="col-sm-12">
					<%@include file="inventoryList.jsp"  %>
				</div>
    			 </div>
    		</div>
    	</div>
    </section>
  </div>
</div>
<div id="modalDiv">
	<!-- 模态对话框插入的地方 -->
</div>
<div class="modal fade " id="promptModal">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">温馨提示</h4>
      </div>
      <div class="modal-body">
        <p></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
<script>
EcWeb.currentModalName = '${modalName}';
</script>
</tiles:put>
</tiles:insert>