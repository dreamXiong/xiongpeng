<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="用户审核列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>厂家审核列表</title>
</head>

<c:set value="suppliers" var="modalName"></c:set>
<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
  <link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
 <script src="${ctx}/js/hgl/jquery-ui.min.js"></script> 
  
 <script src="${ctx}/js/hgl/shop.js"></script> 
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>厂家审核列表</small>
      </h1>
    </section>
   <section class="content">
      <div class="row no-margin">
          <div class="box box-primary">
    	      <div class="box-body">
                  <form onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm">
		             <div class="row">
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">名称</label>
		                  <div class="col-sm-8">
		                    <input id="companyName" name="companyName" type="text" class="form-control" placeholder="请输入企业名称">
		                  </div>
		                </div>
		              </div>
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">状态</label>
		                  <div class="col-sm-8">
		                    <select id="state" name="state" class="form-control">
		                      <option value="">全部</option>
		                      <option value="0">待审核</option>
		                      <option value="1">已审核</option>
		                      <option value="2">审核拒绝</option>
		                      <option value="3">关闭</option>
		                    </select>
		                  </div>
		                </div>
		              </div>
		              <div class="col-sm-2 ">
		                     <button type="submit" class="btn btn-primary" onclick="submitform()">查询</button>
		                </div>
		              </div>
		             </form>
    		 
			 
          	<div id="key_${modalName}_list"  class="col-sm-12">
          	 <%@include file="suppliersUserList.jsp" %>
          	</div> 
         
           </div>
     </div>
   </div>
    </section>
  </div>
</div>
<div id="dialog" class="dialog">
	  <form id="key_${modalName}_frm">
	  <input id="id" name="id" type="hidden">
	   <input id="userId" name="userId" type="hidden">
	    <label for="name">审核</label>
	    	 <select id="state1" name="state" class="form-control">
                <option value="0">待审核</option>
                <option value="1">已审核</option>
                <option value="2">审核拒绝</option>
                <option value="3">关闭</option>
              </select>
	     <textarea style="width: 409px; height: 78px;" type="text" id="checkmesg" name="checkmesg" maxlength="100"></textarea>
	  </form>
	</div>
</body>
<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	 function submitform(){
		  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();;
			$.ajax({
		        type : "POST",
		        url : 'searchResult',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
</script>
</html>
</tiles:put>
</tiles:insert>