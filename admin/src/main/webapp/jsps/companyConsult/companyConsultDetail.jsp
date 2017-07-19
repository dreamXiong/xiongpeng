<%@ page language="java" pageEncoding="UTF-8"%>
	<%-- <script src="${ctx}/js/hgl/productupdate.js"></script>  --%>
   <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>详情</strong></h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post">
            <div class="form-group">
              <label class="col-sm-2 control-label">公司名称：</label>
              <div class="col-sm-9">
                      <input type="text" readonly="readonly" value='${pifd.companyName}' class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">联系人：</label>
              <div class="col-sm-9">
                  <input type="text" readonly="readonly" value='${pifd.contract}' class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">联系号码：</label>
              <div class="col-sm-9">
                <input type="text" name="name" readonly="readonly" value='${pifd.contractPhone}' class="form-control"/>
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
  