<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="公告列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>	
	<meta charset="UTF-8">
	<title>系统公告列表</title>
	<script src="${ctx}/js/hgl/noticeInfo.js"></script>
</head>

<body class="skin-blue fixed">
<div class="modal fade" id="modal">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<c:set value="noticeInfo" var="modalName"></c:set>
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>公告列表</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
	             <form action="" name="noticeForm" id="key_${modalName}_qryFrm" class="form-horizontal" method="post">
	             <div class="row">
	              <div  class="col-sm-3">
	                <div class="form-group">
	                  <label class="col-sm-4 control-label">名称</label>
	                  <div class="col-sm-8">
	                    <input type="text" name="noticeName" class="form-control">
	                  </div>
	                </div>
	              </div>
	              <div  class="col-sm-3">
	                <div class="form-group">
	                  <label class="col-sm-3 control-label">类型</label>
	                  <div class="col-sm-8">
	                    <select name="noticeType" class="form-control">
	                      <option value="0">全部</option>
	                      <option value="1">升级</option>
	                      <option value="2">公告</option>
	                    </select>
	                  </div>
	                </div>
	              </div>
	              <div class="col-sm-3 ">
	              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="submitform()" >查询</a>
	                  <a href="initAddNoticeInfo" class="add btn btn-info">添加</a>
	              </div>
	             </div>
	             </form>
    		 	</div>
              <div class="box-body">
                  <div id="key_${modalName}_list">
					<%@include file="noticeInfoList.jsp"%>	
				  </div>
              </div>
   		</div>
    	</div>
    </section>
  </div>
</div>
</body>
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
</html>
</tiles:put>
</tiles:insert>