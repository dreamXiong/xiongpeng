 <%@ page language="java" pageEncoding="UTF-8"%>
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">等级修改</h4>
      </div>
      <div class="modal-body">
         <form id="updateLevel" action="updateLevel" class="form-horizontal" method="post">
         <input type="hidden"  id="id" name="id" value="${level.id}"/> 
		 <input type="hidden"  id="version" name="version" value="${level.version}"/> 
		 
		 <table class="table">
            <tr>
              <td>会员等级</td>
              <td>
                <input type="text" class="form-control inputNotNull" id="levelName" placeholder="请输入会员等级" name="levelName" value="${level.levelName}" maxlength="50">
              </td>
            </tr>
            <tr>
              <td>最小分界经验值</td>
              <td>
               <input type="text" class="form-control inputNotNull unsign_number" id="minExp" placeholder="请输入最小分界经验值" name="minExp" value="${level.minExp}" maxlength="9">
              </td>
            </tr>
             <tr>
              <td>最大分界经验值</td>
              <td>
              <input type="text" class="form-control inputNotNull unsign_number" id="maxExp" placeholder="请输入最大分界经验值" name="maxExp" value="${level.maxExp}" maxlength="9">
              </td>
            </tr>
            <tr>
              <td>经验获取规则</td>
              <td>
                <input type="text" class="form-control inputNotNull float2" id="expProportion" placeholder="请输入经验获取规则" name="expProportion" value="${level.expProportion}" maxlength="9">
              </td>
            </tr>
             <tr>
              <td>优惠卷使用规则</td>
              <td>
                 <input type="text" class="form-control inputNotNull float2" id="couponRule" placeholder="请输入优惠卷使用规则" name="couponRule" value="${level.couponRule}" maxlength="9">
              </td>
            </tr>
            <tr>
              <td>可获取折扣</td>
              <td>
              
             <input type="text" class="form-control inputNotNull float2" title="可获取折扣" id="userSale" placeholder="请输入可获取折扣" name="userSale" value="${level.userSale}" maxlength="9">
              </td>
            </tr>
            <tr>
              <td>免费提现次数</td>
              <td>
               
              	 <input type="text" class="form-control inputNotNull number" id="cashNum" placeholder="请输入免费提现次数" name="cashNum" value="${level.cashNum}" maxlength="9">
              </td>
            </tr>
            <tr>
              <td>等级说明</td>
              <td>
                <textarea id="levelRemark"  rows="5" class="form-control" placeholder="请输入等级说明" name="levelRemark" value="${level.levelRemark}" maxlength="50">${level.levelRemark}</textarea>
              </td>
            </tr>
			

          </table>  
       
        </form>
      </div>
      <div class="modal-footer">
        <div class="col-md-11">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" onclick="updateLevel();" class="btn btn-primary btn-sm">保存</button>
        </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
 <script>
 
 </script>