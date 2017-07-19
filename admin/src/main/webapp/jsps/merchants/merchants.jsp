<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="招商" />
	<tiles:put name="body" type="String">
<html lang="en">
<c:set value="merchants" var="modalName"></c:set>	
<%@include file="merchantsStyle.jsp"%>
<body >

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>招商列表</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			 <div class="box-body">
              <form onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm">
             <div class="row">
              <div  class="col-sm-3">
                <div class="form-group">
                  <label class="col-sm-4 control-label">名称</label>
                  <div class="col-sm-8">
                    <input id="brandName" name="brandName" type="text" class="form-control" placeholder="请输入查询名称">
                  </div>
                </div>
              </div>
              <div class="col-sm-3 ">
                  
                  <button type="submit" class="btn btn-primary" onclick="submitform()">查询</button>
                  <a class="btn btn-info" href="addMerchants">添加</a>
              </div>
             </div>
             </form>
              <div id="key_${modalName}_list" class="col-sm-12">
               	<%@include file="merchantsList.jsp"  %>
              </div>
               
             
    			 </div>
    		</div>
    	</div>
    	<script>
    	EcWeb.currentModalName = '${modalName}';
    	 /* 表单提交 */
    	 function submitform(){
    		  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
    		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
    		    var frm = $("#" + frmId);
    		    var params=frm.serialize();;
    			$.ajax({
    		        type : "POST",
    		        url : 'serachMerchants',
    		        data : params,
    		        success : function(response) {
    		            $("#" + dataDomId).html(response);
    		        }
    		    }); 
    	 }
    	</script>
    </section>
  </div>
  
  
<!-- 是否产生招商公告弹出层 -->
<div class="modal fade" id="modal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;">
      		<div class="modal-header">
      			<!--data-dismiss="modal" aria-label="Close"-->
        		<button type="button" class="close" onclick="javascript:void(0)"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">公告发布提示信息</span>       		
      		</div>
     		<div class="modal-body" style="text-align:center;font-size:18px;font-weight:bold;font-family:serif;">
     			<span>该招商活动已经发布，<br>您是否需要生成招商公告？</span>		
    		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="DialogBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onload="javascript:location.reload()">取消</button>
    		</div>
    	</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  
<!-- 招商公告弹出层 -->
<div class="modal fade" id="merchantsModal">
	<div class="modal-dialog">
		<div class="modal-content" style="box-shadow:8px 8px 12px rgba(60,141,188,.0.1);border-radius:10px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">招商公告信息</span>       		
      		</div>
     		<div class="modal-body" style="text-align:center;font-size:18px;font-family:serif;">
     			<form class="form-horizontal" id="merchNotice" name="merchNotice">     				
     				<div class="form-group">	
     					<label class="col-sm-3 control-label">公告名称</label>
     					<div class="col-sm-9">
     						<input type="text" name="merchNoticeName" id="merchNoticeName" maxlength="200" class="form-control" placeholder="公告名称不能为空哦">
     						<span id="merchNoticeNameError" style="float:left;"></span>
     					</div>
     				</div>
     				<div class="form-group">
     					<label class="col-sm-3 control-label">公告详情</label>
     					<div class="col-sm-9">
     						<textarea rows="8"  name="merchNoticeDetail" id="merchNoticeDetail" class="form-control" placeholder="公告详情不能为空哦"></textarea>
     						<span id="merchNoticeDetailError" style="float:left;"></span>
     					</div>	
     				</div>
     			</form>
    		</div>
    		
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="merchNoticeBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" id="merchNoticeCnl" onload="javascript:location.reload(true)">取消</button>
    		</div>
    	</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
</tiles:put>
</tiles:insert>