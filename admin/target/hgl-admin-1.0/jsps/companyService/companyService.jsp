<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="服务公司列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<c:set value="companyService" var="modalName"></c:set>
<head>
	<meta charset="UTF-8">
	<title>服务公司列表</title>
</head>
 <%--  <link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
  <link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
 <script src="${ctx}/js/hgl/jquery-ui.min.js"></script>  --%>
 <script src="${ctx}/js/hgl/companyService.js"></script>
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页<small>服务公司列表</small></h1>
    </section>
   <section class="content">
      <div class="row no-margin">
          <div class="box box-primary">
    	      <div class="box-body">
                  <form class="form-horizontal" id="key_${modalName}_qryFrm">
		             <div class="row">
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">名称</label>
		                  <div class="col-sm-8">
		                    <input id="companyName" name="companyName" type="text" class="form-control" placeholder="公司名称">
		                  </div>
		                </div>
		              </div>
		               <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">类型</label>
		                  <div class="col-sm-8">
		                   <select id="testSelect" name="typeId" class="form-control">
		                    <option value="" selected="selected">--请选择--</option>
		                    <c:forEach var="item" items="${tbCompanyTypeList}" varStatus="s">
		                     	 <option value="${item.id}">${item.name}</option>
		                     </c:forEach> 
	                    </select>
		                  </div>
		                </div>
		              </div> 
		              <div class="col-sm-2 ">
		                     <input type="button" style="width:60px;height:40px;" value="查询" class="btn btn-primary" onclick="submitform()"></input>
		                </div>
		                <c:if test="${fn:length(tbCompanyTypeList) > 0}">
			                 <div class="col-sm-2 ">
			                      <input type="button" style="width:60px;height:40px;" value="添加" class="btn btn-primary" onclick="showAddpage()"></input>
			                </div>
		                </c:if>
		              </div>
		             </form>
	          	<div id="key_${modalName}_list"  class="col-sm-12">
	          	 	<%@include file="companyServiceList.jsp" %>
	          	</div> 
	          	
	          	<!--  添加弹出层 -->
				<div class="modal fade" id="showdetailsInfo" data-backdrop="static">
				  <%@include file="companyServiceDetail.jsp"%>
				</div>
           </div>
     </div>
   </div>
    </section>
  </div>
</div>

<div class="modal fade" id="deleteProduct">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body">你确定要删除该服务公司吗？</div>
      <input type="hidden" id="deleteId" value=""/>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='deleteInfo();'>确定</button>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	
</script>
</html>
</tiles:put>
</tiles:insert>