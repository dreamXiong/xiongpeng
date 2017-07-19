<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/customerServiceUpdate.js"></script> 
   <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>详情</strong></h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post">
            <div class="form-group">
              <label class="col-sm-2 control-label">分类</label>
              <div class="col-sm-9">
                <div class="row">
                  <div class="col-sm-4">
                      <input type="text" readonly="readonly" value='${pifd.tstName}' class="form-control"/>
                  </div>
                  <div class="col-sm-4">
                      <input type="text" readonly="readonly" value='${pifd.bName}' class="form-control"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-9">
                <input type="text" name="name" readonly="readonly" value='${pifd.name}' class="form-control"/>
              </div>
            </div>
            
             <div class="form-group">
              <label class="col-sm-2 control-label">价格</label>
              <div class="col-sm-9">
                <input type="text" name="name" readonly="readonly" value='${pifd.price}' class="form-control"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <div>
	                  <c:if test="${not empty pifd.cimgOne}">
	                    <img src='generateImage?id=${pifd.id}&imgName=${pifd.cimgOne}'/>
	                   </c:if>
	                   <c:if test="${empty pifd.cimgOne}">
	                    <img />
	                   </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div >
	                  <c:if test="${not empty pifd.cimgTwo}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.cimgTwo}'>
	                   </c:if>
                  	 <c:if test="${empty pifd.cimgTwo}">
	                     <img>
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div>
                   <c:if test="${not empty pifd.cimgThree}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.cimgThree}'>
	                 </c:if>
                    <c:if test="${empty pifd.cimgThree}">
	                     <img>
	                 </c:if>
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