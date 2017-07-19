<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<tiles:insert beanName="indexPage">
<tiles:put name="tile" value="WAP端师傅的审核"/>
<tiles:put name="body" type="String">    
<html>
<c:set value="webUser" var="modalName"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户管理</title>
<script type="text/javascript">
	function updateWebUserPsd(userId){
		debugger;
		$("#modal").modal("show");
		$("#modal #updDialogBtn").click(function(){
			$.ajax({
				type:"post",
				url:"doUpdateWebUserPassword",
				data:{"id":userId},
				success:function(data){
				}
			});
			$("#modal").modal("hide");
		});
		
		
		
	}
</script>
</head>
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
                  <form  onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm" name="webUserForm">
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
		              </div>
             	</div>
             </form>
          	<div id="key_${modalName}_list" class="col-sm-12">
               <%@include file="workerlist.jsp" %>
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
		   type:"post",
		   url:"doSearchWorker",
		   data:params,
		   success:function(response){
		        $("#" + dataDomId).html(response);
		    }
		}); 
	}
	</script>
    </section>
 </div>
 

<!-- 重置密码弹出层 --> 
<div class="modal fade" id="modal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">重置密码</span>       		
      		</div>
     		<div class="modal-body" style="text-align:center;font-size:18px;font-weight:bold;font-family:serif;">
     			<span>您确定要重置密码吗？</span>		
    		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="updDialogBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">取消</button>
    		</div>
    	</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 
</body>
</html>
</tiles:put>
</tiles:insert>