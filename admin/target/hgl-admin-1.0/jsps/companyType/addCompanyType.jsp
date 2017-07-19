<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/companyType.js"></script> 
	<style>
	.onerrInput {
	    border: 1px solid red!important;
	}
	</style>
 <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>添加</strong></h4>
      </div>
      <div class="modal-body">
        <form action="saveAddInfo" id="saveServiceInfo" class="form-horizontal" method="post">
           <input type="hidden" name="imgName" id="pImgOne_val" />
            <div class="form-group">
              <label class="col-sm-2 control-label">服务分类</label>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-9">
                <input type="text" maxlength="20" id="name" onblur="addValidateGroup();" name="name" class="form-control inputNotNull">
              </div><!-- display: none; -->
               <!-- <div id="addNameError" style="width: 180px;color: red;padding: 0 0 0 60px;" >组名重复</div> -->  
            </div>
            <div id="addNameError" style="width:180px;color:red;padding:0 0 0 100px;display: none;" >组名重复</div>
            <div class="form-group">
              <label class="col-sm-2 control-label">服务图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3" id="removeCl">
                  <input type="file" class="pImg" id="pImgOne" name="imgFile"/>
                  <div id="pImgOnediv ">
                    <img id="pImgOneShow">
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div><span class="text-info" style="width:600px;margin:0 0 0 15px;">必须是.gif,jpeg,jpg,png格式且不大于5MB。</span></div>
              </div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-2 control-label">描述</label>
              <div class="col-sm-9">
                <textarea type="text" maxlength="200" name="describes" class="form-control" rows="5"></textarea>
              </div>
            </div>
            <input type="hidden" id="changeImgNo" />
        </form>  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="submitAddServiceInfo();">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  <script>
  var addValidate = false;
  function addValidateGroup(){
  	var addName = $("#name").val();
  	if(isNull(addName)){
  		return;
  	}
  	$.ajax({
          type : "POST",
          url : ctx+'/companyType/addValidateGroup',
          data:{
          	name:addName
          },
          success : function(response) {
          	if(response.code == 0){
          		$("#addNameError").hide();
          		addValidate = true;
          	}else{
          		$("#addNameError").show();
          		$('#addName').toggleClass('onerrInput'); 
          		addValidate = false;
          	}
          }
  	});
  }
  
  
  </script>