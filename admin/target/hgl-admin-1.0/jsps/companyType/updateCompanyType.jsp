<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/companyTypeUpdate.js"></script> 
   <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>修改</strong></h4>
      </div>
      <div class="modal-body">
        <form action="updateService" class="form-horizontal" id="updateProductInfo" method="post">
	           <input type="hidden" name="imgName" id="pImgOne_Up_Show" value="${pifd.imgName }"/>
	           <input type="hidden" name="id" value="${pifd.id}"/>
	           <input type="hidden" name="version" id="version" value="${pifd.version}"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-9">
                <input type="text" maxlength="20" name="name" value='${pifd.name}' class="form-control inputNotNull"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <input type="file" name="imgFile" class="pUpdate" id="pImgOne_Up"/>
                  <div id="pImgOnedivUp">
	                    <img id="pImgOneShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.imgName}'/>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
              </div>
            </div>
           <div><span class="text-info" style="width:600px;margin:0 0 0 15px;">必须是.gif,jpeg,jpg,png格式且不大于5MB。</span></div>
            <div class="form-group">
              <label class="col-sm-2 control-label">描述</label>
              <div class="col-sm-9">
                <textarea type="text" maxlength="200" class="form-control" name="describes" rows="5">${pifd.describes}</textarea>
              </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="subUpdateServiceInfo();">保存</button>
      </div>
    </div>
  </div>
