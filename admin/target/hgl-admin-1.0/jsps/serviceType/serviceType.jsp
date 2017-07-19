<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类管理" />
	<tiles:put name="body" type="String">
<html lang="en">
 <script src="${ctx}/js/hgl/serviceType.js"></script> 
<head>
	<meta charset="UTF-8">
	<title>服务管理</title>  
</head>
<body class="skin-blue fixed">
<input type="text" value="${Id}" name="mainId" id="mainId"/>
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        		我的主页
        <small>服务列表</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">         
    		<div class="box box-primary">
    			<div class="box-body pad table-responsive">
    				  <div class="fil">
                <div>
                  <ul class="nav text-center one" id="productType">
                    <li class="nav-title">
                      <a onclick="goServiceTypeAddPage();" >添加一级服务</a>
                    </li>
                    <input type="hidden" id="deleteId" value=""/>
			  	<c:forEach var="item" items="${sList}" varStatus="s">
                	 <li onclick="serviceType(${item.id});" class="clearfix">  
                	 <div class="pull-left">
                      	<a>${item.name}</a>
                      </div>
                     <div class="pull-right">
	                      <a onclick="updateServiceType(${item.id});">修改</a> 
	                      <a onclick="deleteFirstServiceType(${item.id})">删除</a>
                      </div>
                    </li>
                </c:forEach>
                <div id="serviceTypeListInfi">
                	<%@include file="serviceTypeList.jsp"%>
                </div>
              </div>
    			</div>
    		</div>
    	</div>
    </section>
  </div>
<!-- 大类弹出框  -->
<div class="modal fade" id="deleteFirst">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body" >
        	你确定要删除该主服务类吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='configDeleteServiceType()'>确定</button> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="notDeleteFirst">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body" >
        	有相关数据暂不能删除！
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 二级类弹出框  -->
<div class="modal fade" id="deleteSecond">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要删除该二级服务类吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='configDeleteServiceType()'>确定</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
</tiles:put>
</tiles:insert>