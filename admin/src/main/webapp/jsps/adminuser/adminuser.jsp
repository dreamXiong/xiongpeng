<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="用户列表" />
<tiles:put name="body" type="String">
<html lang="en">
<c:set value="adminUser" var="modalName"></c:set>
<script type="text/javascript" src="${ctx}/js/hgl/adminuser.js"></script>

<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
       	 我的主页
        <small>用户列表</small>
      </h1>
    </section>
   	<section class="content">
      <div class="row no-margin">
          <div class="box box-primary">
    	      <div class="box-body">
                  <form  onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm" name="adminUserForm">
                  <div class="row">
                      <div  class="col-sm-3">
                      	<div class="form-group">
                  			<label class="col-sm-4 control-label">姓名</label>
               				<div class="col-sm-8">
                   				<input type="text" name="name" class="form-control" placeholder="请输入查询姓名" value="${name}">
               				</div>
               			</div>
              		  </div>
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">帐号</label>
		                  <div class="col-sm-8">
		                    <input type="text" name="userName" class="form-control" placeholder="请输入查询账号" value="${userName}">
		                  </div>
		                </div>
		              </div>
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">手机号</label>
		                  <div class="col-sm-8">
		                    <input type="text" name="mobile" class="form-control" placeholder="请输入手机号码" value="${mobile}">
		                  </div>
		                </div>
		              </div>
		              <div class="col-sm-3 ">
		              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="submitform()">查询</a>
		              	  <a href="initAddAdminUser" class="btn btn-primary" id="addBtn">添加</a>
		              </div>
             	</div>
             </form>
          	<div id="key_${modalName}_list" class="col-sm-12">
               	<%@include file="adminuserlist.jsp"  %>
            </div>
     </div>
   </div>
  </div>
  <script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	/*表单提交*/
	function submitform(){
    	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		var frm = $("#" + frmId);
		var params=frm.serialize();
		$.ajax({
		       type : "POST",
		       url : 'doSearchResult',
		       data : params,
		       success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
	</script>
    </section>
 </div>
</body>
</html>
</tiles:put>
</tiles:insert>