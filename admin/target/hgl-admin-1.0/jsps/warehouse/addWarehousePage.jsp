<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${ctx}/css/move-border.css"> 
<div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加仓管员</h4>
      </div>
      <div class="modal-body">
        <form action="addTbWarehouse" id="goAddWInfo" method="post">
           <table class="table">
            <tr>
              <td>仓库名称</td>
              <td colspan="2"><input type="text" maxlength="20" name="warehouseName" class="form-control inputNotNull"></td>
            </tr>
            <tr> 
              <td>地址</td>
               <td width="220"><!-- <select class="form-control" name="warehouseProvince"></select> -->
              <select id="province" name="warehouseProvince" class="form-control" onchange="changeGrade();" >
               	      <option value="0">请选择</option>
	                  <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
	                  		<option value="${item.id}">${item.name}</option>
	                  </c:forEach>
             </select>
              </td>
              <td width="220">
               	<select id="city" name="warehouseCity" class="form-control" onchange="changeCity();" > </select>
              </td>
            </tr>
            <tr>
              <td class="sr-only">地址</td>
              <td  width="220">
              	 <!-- <select class="form-control" name="warehouseCountry"></select>  -->
              	 <select id="country" name="warehouseCountry"  class="form-control"  onchange="changeCountry();"></select>
              </td>
              <td width="220">   
              	<!--  <select class="form-control" name="warehouseStreet"></select>  -->
              	 <select id="street" name="warehouseStreet" class="form-control"></select> 
              </td> 
            </tr>
            <tr>
              <td>具体地址</td>
              <td colspan="2"><input type="text" class="form-control inputNotNull" maxlength="100" name="warehouseAddress"></td>
            </tr>
            <tr>
              <td>仓库座机</td>
              <td colspan="2"><input type="text" maxlength="20" title="仓库座机" class="form-control inputNotNull tel" name="warehouseTel"></td>
           <!--    <td>&nbsp;</td> -->
              
            </tr>
            <tr>
              <td>联系人</td>
              <td colspan="2"><input type="text" maxlength="20" class="form-control inputNotNull" name="contract"></td>
            </tr>
            <tr>
              <td>联系方式</td>
              <td colspan="2"><input type="text" maxlength="20" title="联系方式" class="form-control inputNotNull tel" name="contractPhone"></td>
            </tr>
            
             <tr>
              <td>仓库类型</td>
              <td colspan="2">
              	<!-- <input type="text" class="form-control inputNotNull" name="shopType"> -->
              	 <select class="form-control" name="shopType">
                    <option value="122">自营仓库</option>
                    <option value="123">代理仓库</option>
                  </select>
              </td>
            </tr>

          </table> 
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="saveWInfo();">保存</button>
      </div>
    </div>
  </div>
  <script>
  
  $("#country").bind("change",function(){
	  $("#country").removeClass("onerrInput");
  });
  function saveWInfo(){
	 /*  alert($("#country").val());
	  alert(!isNull($("#country").val())); */
	  var country = $("#country").val();
	  var isnull = country == null || country =='' || country ==0;
	  if(!isnull){
		  $("#country").removeClass("onerrInput");
	  }else{
		  $("#country").addClass("onerrInput");
	  }
	  var addType = validateForm("goAddWInfo");
		if(!addType || isnull){
			return;
		}
		$("#goAddWInfo").submit();
	}
  </script>