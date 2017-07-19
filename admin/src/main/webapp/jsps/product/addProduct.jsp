<%@ page language="java" pageEncoding="UTF-8"%>
	<script src="${ctx}/js/hgl/productAdd.js"></script> 
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
        <form action="saveProductInfo" id="saveProductInfo" class="form-horizontal" method="post">
           <input type="hidden" name="pimgOne" id="pImgOne_val" />
           <input type="hidden" name="pimgtwo" id="pImgTwo_val"/>
           <input type="hidden" name="pimgthree" id="pImgThree_val"/>
           <input type="hidden" name="dimgone" id="dImgOne_val"/>
           <input type="hidden" name="dimgtwo" id="dImgTwo_val"/>
           <input type="hidden" name="dimgthree" id="dImgThree_val"/>
           <input type="hidden" name="attributes" id="attributes"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">分类</label>
              <div class="col-sm-9 ">
                <div class="row">
                  <div class="col-sm-4">
                    <select onchange="selectParent('mainIdForAddPage');" id="mainIdForAddPage" name="mainId" class="form-control"><!-- mLists -->
                    <option value='0'>--请选择--</option>
                    <c:forEach var="item" items="${mLists}" varStatus="s">
                      <option value="${item.id}">${item.name}</option>
                     </c:forEach>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="parentType" name="producttypeId" onchange="selectThirdType('parentType');" class="form-control">
                    	<option value='0'>--请选择--</option>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select class="form-control" name="thirdType" id="thirdType">
                      <option value='0'>--请选择--</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
             <div class="form-group">
              <label class="col-sm-2 control-label">品牌</label>
              <div class="col-sm-9 ">
                <select class="form-control" name="brandId" id="tbBrandList">
                       <option value='0'>--请选择--</option>
                     
                    </select>
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
                <input type="text" maxlength="8" id="priceErrorRm" name="price" class="form-control float inputNotNull">
              </div>
            </div>
            
            <!-- <div class="form-group">
              <label class="col-sm-2 control-label">装箱数</label>
              <div class="col-sm-9">
                <input type="text" maxlength="8" name="pickno" onchange="onCKeyupNumber('picknoErrorRm');" id="picknoErrorRm" class="form-control inputNotNull" />
              </div>
            </div> -->
            
            <div class="form-group">
              <label class="col-sm-2 control-label">计量单位</label>
              <div class="col-sm-9">
                <input type="text" name="meterageunit" maxlength="20" class="form-control inputNotNull">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">产品图片</label>
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
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label" style="padding-left:0;padding-right:0">产品描述图片</label>
              <div class="col-sm-9">
          
              	
                <div class="col-sm-3">
                  <input type="file" class="pImg" name="imgFile" id="dImgOne">
                  <div id="dImgOnediv" >
                    <img id="dImgOneShow">
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" class="pImg" name="imgFile" id="dImgTwo">
                  <div id="dImgTwodiv">
                    <img id="dImgTwoShow">
                  </div>
                  <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="col-sm-3">
                  <input type="file" class="pImg" name="imgFile" id="dImgThree">
                  <div id="dImgThreediv">
                    <img id="dImgThreeShow">
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

            <div class="form-group">
              <label class="col-sm-2 control-label">附加属性</label>
              <div class="col-sm-9 form-line" id="add">
                <div class="addaaaa">
                </div>
                <a href="javascript:;" class="btn btn-default" id="addInput">添加...</a>
              </div>
            </div> 
             <input type="hidden" id="changeImgNo" />
        </form>  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="submitAddProductInfo();">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->