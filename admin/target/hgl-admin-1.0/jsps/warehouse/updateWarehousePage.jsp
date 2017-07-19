<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal-dialog">
 <script src="${ctx}/js/hgl/warehouse.js"></script> 
 <link rel="stylesheet" href="${ctx}/css/move-border.css"> 
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改仓库</h4>
      </div>
      <div class="modal-body">
        <form id="cleanUpdateTable" action="updateTbWarehouse" method="post">
       		<input type="hidden" id="provinceCode" value="${record.warehouseProvince}"/> 
          	<input type="hidden" id="cityCode" value="${record.warehouseCity}"/>
           	<input type="hidden" id="countryCode" value="${record.warehouseCountry}"/>
           	<input type="hidden" id="streetCode" value="${record.warehouseStreet}"/>
           	<input type="hidden" name="version" value="${record.version }"/>
           	<input type="hidden" name="id" value="${record.id }"/>
          <table class="table">
            <tr>
              <td>仓库名称</td>
              <td colspan="2"><input type="text" maxlength="20" class="form-control inputNotNull" name="warehouseName" value="${record.warehouseName}"></td>
            </tr>
            <tr>
              <td>地址</td>  
              <td width="220"><select id="province" name="warehouseProvince" onchange="changeGrade();" value="${record.warehouseProvince}" class="form-control">
              		 <option value="0">请选择</option>
                          <c:forEach  var="items" items="${provinceInfo}">
                         	  <option
	                    	<c:if test="${items.id == record.warehouseProvince}">
	                    		selected='selected'
	                    	</c:if>
	                 			value="${items.id}"> ${items.name} </option> 
                          </c:forEach>
              		
              </select></td>
              <td width="220"><select id="city" name="warehouseCity" value="${record.warehouseCity}" onchange="changeCity();" class="form-control"></select></td>
            </tr>
            <tr>
              <td class="sr-only">地址</td>   
              <td width="220"><select id="country" name="warehouseCountry" value="${record.warehouseCountry}" onchange="changeCountry();" class="form-control"></select></td>
              <td width="220"><select id="street" name="warehouseStreet" value="${record.warehouseStreet}" class="form-control removeError"></select></td>
            </tr>
            <tr>
              <td>具体地址</td>
              <td colspan="2"><input type="text" maxlength="100"  class="form-control inputNotNull" name="warehouseAddress" value="${record.warehouseAddress }"></td>
            </tr>
            <tr>
              <td>仓库座机</td>
              <td colspan="2"><input type="text" class="form-control inputNotNull tel" maxlength="20"  title="仓库座机" name="warehouseTel" value="${record.warehouseTel }"></td>
            </tr>
            <tr>
              <td>联系人</td>
              <td colspan="2"><input type="text" class="form-control inputNotNull" maxlength="20" name="contract" value="${record.contract }"></td>
            </tr>
            <tr>
              <td>联系方式</td>
              <td colspan="2"><input type="text" title="联系方式" class="form-control inputNotNull tel" maxlength="20" name="contractPhone" value="${record.contractPhone }"></td>
            </tr>
             <tr>
              <td>仓库类型</td>
               <td colspan="2">
              	 <select class="form-control" name="shopType">
                    <option value="122"
                    <c:if test="${record.shopType == '122'}">
                    	selected='selected'
                    </c:if>
                    >自营仓库</option>
                    
                    <option value="123"
                    <c:if test="${record.shopType == '123'}">
                    	selected='selected'
                    </c:if>
                    >代理仓库</option>
                  </select>
              </td>
            </tr>
            <tr>
              <td>状态</td>  
              <td colspan="2">
	              <select id="province" name="states" value="${record.warehouseProvince}" class="form-control">
                        <option <c:if test="${'0' == record.states}">selected='selected' </c:if>value="0">停用</option> 
                        <option <c:if test="${'1' == record.states}">selected='selected' </c:if>value="1">启用</option> 
	              </select>
              </td>
            </tr>
          </table>
        </form>
      </div>  
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="submitUpdateInfo();">保存</button>
      </div>
    </div>
  </div>
   <script>
  $(".form-control").bind("change",function(){
	 $(this).removeClass("onerrInput");
  });
  </script>