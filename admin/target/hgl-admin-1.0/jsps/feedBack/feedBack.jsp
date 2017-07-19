<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="意见反馈" />
<tiles:put name="body" type="String">
<html lang="en">
<c:set value="feedBack" var="modalName"></c:set>
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
       	 我的主页
        <small>意见反馈列表</small>
      </h1>
    </section>
   	<section class="content">
      <div class="row no-margin">
          <div class="box box-primary">
    	      <div class="box-body">
                  <form  onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm" name="feedBackForm">
                  <div class="row">
                      <div  class="col-sm-3">
                      	<div class="form-group">
                  			<label class="col-sm-4 control-label">用户名</label>
               				<div class="col-sm-8">
                   				<input type="text" name="userName" class="form-control" placeholder="请输入查询用户名" value="${userName}">
               				</div>
               			</div>
              		  </div>
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">手机号码</label>
		                  <div class="col-sm-8">
		                    <input type="text" name="mobile" class="form-control" placeholder="请输入查询手机号码" value="${mobile}">
		                  </div>
		                </div>
		              </div>
		              <div  class="col-sm-3">
		                <div class="form-group">
		                  <label class="col-sm-4 control-label">用户类型</label>
		                  <div class="col-sm-8">
		                  	<select name="typeId" id="typeId" class="form-control">
		                  		<option value="0">全部</option>
		                  		<option value="104">经销商</option>
		                  		<option value="106">终端客户</option>
		                  		<option value="114">师傅</option>
		                  	</select>
		                  </div>
		                </div>
		              </div>
		              <div class="col-sm-3 ">
		              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="submitform()">查询</a>
		              </div>
             	</div>
             </form>
          	<div id="key_${modalName}_list" class="col-sm-12">
               	<%@include file="feedBackList.jsp"  %>
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