<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/productupdate.js"></script> 
   <div class="modal-dialog file">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>修改</strong></h4>
      </div>
      <div class="modal-body">
        <form action="updateProduct" class="form-horizontal" id="updateProductInfo" method="post">
        
	           <input type="hidden" name="pimgOne" id="pImgOne_Up_Show" value="${pifd.pimgOne }"/>
	           <input type="hidden" name="pimgtwo" id="pImgTwo_Up_Show" value="${pifd.pimgtwo }"/>
	           <input type="hidden" name="pimgthree" id="pImgThree_Up_Show" value="${pifd.pimgthree }"/>
	           <input type="hidden" name="dimgone" id="dImgOne_Up_Show" value="${pifd.dimgone }"/>
	           <input type="hidden" name="dimgtwo" id="dImgTwo_Up_Show" value="${pifd.dimgtwo }"/>
	           <input type="hidden" name="dimgthree" id="dImgThree_Up_Show" value="${pifd.dimgthree }"/>
	           <input type="hidden" name="attributes" id="updateAttrList"/>
	           <input type="hidden" name="id" value="${pifd.id}"/>
	           <input type="hidden" name="version" id="version" value="${pifd.version}"/>
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
                <input type="text" maxlength="20" name="name" value='${pifd.name}' class="form-control inputNotNull"/>
              </div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-2 control-label">价格</label>
              <div class="col-sm-9">
                <input type="text" maxlength="8" name="price" id="updatePInfo" value='${pifd.price}' class="form-control float inputNotNull"/>
              </div>
            </div>
             <%-- <div class="form-group">
              <label class="col-sm-2 control-label">装箱数</label>
              <div class="col-sm-9">
                <input type="text" maxlength="8" name="pickno" onchange="onCKeyupNumber('upPicknoErrorRm');" value="${pifd.pickno}" id="upPicknoErrorRm" class="form-control inputNotNull">
              </div>
            </div> --%>
            
            <div class="form-group">
              <label class="col-sm-2 control-label" value='${pifd.meterageunit}'>计量单位</label>
              <div class="col-sm-9">
                <input type="text" maxlength="20" name="meterageunit" class="form-control inputNotNull" value="${pifd.meterageunit }"/>
              </div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
              <div class="col-sm-9">
                <div class="col-sm-3">
                  <input type="file" name="imgFile" class="pUpdate" id="pImgOne_Up"/>
                  <div id="pImgOnedivUp">
	                  <c:if test="${not empty pifd.pimgOne}">
	                    <img id="pImgOneShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.pimgOne}'/>
	                   </c:if>
	                   <c:if test="${empty pifd.pimgOne}">
	                    <img id="pImgOneShowUp"/>
	                   </c:if>
	                    
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" name="imgFile" class="pUpdate" id="pImgTwo_Up"/>
                  <div id="pImgTwodivUp">
	                  <c:if test="${not empty pifd.pimgtwo}">
	                     <img id="pImgTwoShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.pimgtwo}'>
	                   </c:if>
                  	 <c:if test="${empty pifd.pimgtwo}">
	                     <img id="pImgTwoShowUp">
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" name="imgFile" class="pUpdate" id="pImgThree_Up"/>
                  <div id="pImgThreedivUp">
                   <c:if test="${not empty pifd.pimgthree}">
	                     <img id="pImgThreeShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.pimgthree}'>
	                 </c:if>
                    <c:if test="${empty pifd.pimgthree}">
	                     <img id="pImgThreeShowUp" >
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
                  <input type="file" name="imgFile" class="pUpdate" id="dImgOne_Up">
                  <div id="dImgOnedivUp">
                  	 <c:if test="${not empty pifd.dimgone}">
	                     <img id="dImgOneShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.dimgone}'>
	                 </c:if>
	                 <c:if test="${empty pifd.dimgone}">
	                     <img id="dImgOneShowUp">
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" name="imgFile" class="pUpdate" id="dImgTwo_Up">
                  <div id="dImgTwodivUp">
                  	<c:if test="${not empty pifd.dimgtwo}">
	                    <img id="dImgTwoShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.dimgtwo}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgtwo}">
	                    <img id="dImgTwoShowUp">
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" class="pUpdate" name="imgFile" id="dImgThree_Up">
                  <div id="dImgThreedivUp">
                  	<c:if test="${not empty pifd.dimgthree}">
	                   <img id="dImgThreeShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.dimgthree}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgthree}">
	                   <img id="dImgThreeShowUp" >
	                 </c:if>
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                 <div><span class="text-info" style="width:600px;margin:0 0 0 15px;">必须是.gif,jpeg,jpg,png格式且不大于5MB。</span></div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">描述</label>
              <div class="col-sm-9">
                <textarea type="text" maxlength="200" class="form-control" name="describes" rows="5">${pifd.describes}</textarea>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">附加属性</label>
              <div class="col-sm-9 form-line" id="add">
                <div class="update updateInfoDiv add" id="updateInfoDiv">
                
                <c:if test="${sign == false}">
	                <c:forEach var="item" items="${attr}" varStatus="s">
	                  <c:if test="${not empty  item}">
		                <div class="pull-left update-attr">
		                  <input type="text" maxlength="10" name="${item}${s.index}" class="form-control inputNotNull upAttr" value="${item}">
		                </div>
		                </c:if>
	                </c:forEach>
                </c:if>
                
                 <c:if test="${sign == true}">
	                <c:forEach var="item" items="${attr}" varStatus="s">
	                <c:if test="${not empty  item}">
		                <div class="pull-left update-attr">
		                  <input type="text" maxlength="10" name="${item}${s.index}" class="form-control inputNotNull upAttr" value="${item}">
		                   <span class="close inp-close">&times;</span>
		                </div>
		               </c:if>
	                </c:forEach>
                </c:if>
                
                </div>
                <c:if test="${sign == true}">
	                <a href="javascript:;" class="btn btn-default" id="updateInput">添加...</a>
                </c:if>
              </div>
            </div> 
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="subUpdateProductInfo();">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  
  <script>
  
  </script>