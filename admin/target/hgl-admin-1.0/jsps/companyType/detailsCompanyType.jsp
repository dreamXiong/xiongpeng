<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/companyTypeUpdate.js"></script> 
   <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>详情</strong></h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post">
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-9">
                <input type="text" name="name" readonly="readonly" value='${pifd.name}' class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <div>
	                    <img src='generateImage?id=${pifd.id}&imgName=${pifd.imgName}'/>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">描述</label>
              <div class="col-sm-9">
                <textarea type="text" readonly="readonly" class="form-control"  rows="5">${pifd.describes}</textarea>
              </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
      </div>
    </div>
  </div>
  <script>
  </script>