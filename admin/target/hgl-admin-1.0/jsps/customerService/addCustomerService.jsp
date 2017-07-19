<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/customerServiceAdd.js"></script> 
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
        <form action="saveServiceInfo" id="saveServiceInfo" class="form-horizontal" method="post">
           <input type="hidden" name="cimgOne" id="pImgOne_val" />
           <input type="hidden" name="cimgTwo" id="pImgTwo_val"/>
           <input type="hidden" name="cimgThree" id="pImgThree_val"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">服务分类</label>
              <div class="col-sm-9 ">
                <div class="row">
                  <div class="col-sm-4">
                    <select onchange="selectParent();" id="mainIdForAddPage" name="mainId" class="form-control"><!-- mLists -->
                    <option value='0'>--请选择--</option>
                    <c:forEach var="item" items="${mLists}" varStatus="s">
                      <option value="${item.id}">${item.name}</option>
                     </c:forEach>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="parentType" name="serviceTypeId" class="form-control">
                    	<option value='0'>--请选择--</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">名称</label>
              <div class="col-sm-9">
                <input type="text" maxlength="20" name="name" class="form-control inputNotNull">
              </div>
            </div>
             <div class="form-group">
              <label class="col-sm-2 control-label">价格</label>
              <div class="col-sm-9">
                <input type="text" maxlength="5" id="priceErrorRm" name="price" class="form-control float inputNotNull">
              </div>
             </div>
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
                <div class="col-sm-3">
                  <input id="pImgTwo" name="imgFile" class="pImg" type="file"/>
                  <div id="pImgTwodiv">
                    <img id="pImgTwoShow">
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input id="pImgThree" name="imgFile" class="pImg" type="file">
                  <div id="pImgThreediv">
                    <img id="pImgThreeShow">
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