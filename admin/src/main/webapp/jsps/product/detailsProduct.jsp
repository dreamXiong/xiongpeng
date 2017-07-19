<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/productupdate.js"></script> 
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
                      <input type="text" readonly="readonly" value='${pifd.mainType}' class="form-control"/>
                  </div>
                  <div class="col-sm-4">
                      <input type="text" readonly="readonly" value='${pifd.parentType}' class="form-control"/>
                  </div>
                  <div class="col-sm-4">
                      <input type="text" readonly="readonly" value='${pifd.thirdType}' class="form-control"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">品牌</label>
              <div class="col-sm-9">
                  <input type="text" readonly="readonly" value='${pifd.brandname}' class="form-control"/>
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
                <input type="text" name="price" readonly="readonly" value='${pifd.price}' class="form-control"/>
              </div>
            </div>
             <%-- <div class="form-group">
              <label class="col-sm-2 control-label">装箱数</label>
              <div class="col-sm-9">
                <input type="text"  readonly="readonly" name="pickno" value="${pifd.pickno}" class="form-control">
              </div>
            </div> --%>
            
            <div class="form-group">
              <label class="col-sm-2 control-label" value='${pifd.meterageunit}'>计量单位</label>
              <div class="col-sm-9">
                <input type="text" readonly="readonly" class="form-control" value="${pifd.meterageunit }"/>
              </div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <div>
	                  <c:if test="${not empty pifd.pimgOne}">
	                    <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgOne}'/>
	                   </c:if>
	                   <c:if test="${empty pifd.pimgOne}">
	                    <img />
	                   </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div >
	                  <c:if test="${not empty pifd.pimgtwo}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgtwo}'>
	                   </c:if>
                  	 <c:if test="${empty pifd.pimgtwo}">
	                     <img>
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div>
                   <c:if test="${not empty pifd.pimgthree}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgthree}'>
	                 </c:if>
                    <c:if test="${empty pifd.pimgthree}">
	                     <img>
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" style="padding-left:0;padding-right:0">产品描述图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <div>
                  	 <c:if test="${not empty pifd.dimgone}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgone}'>
	                 </c:if>
	                 <c:if test="${empty pifd.dimgone}">
	                     <img>
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div>
                  	<c:if test="${not empty pifd.dimgtwo}">
	                    <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgtwo}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgtwo}">
	                    <img>
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <div>
                  	<c:if test="${not empty pifd.dimgthree}">
	                   <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgthree}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgthree}">
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
            <div class="form-group">
              <label class="col-sm-2 control-label">附加属性</label>
              <div class="col-sm-9 form-line">
                <div>                
	                <c:forEach var="item" items="${attr}" varStatus="s">
		                 <c:if test="${not empty item}">
			                <div class="pull-left">
			                  <input type="text" readonly="readonly" class="form-control" value="${item}">
			                </div>
			                </c:if>
	                </c:forEach>
                </div>
              </div>
            </div> 
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  <script>
  </script>