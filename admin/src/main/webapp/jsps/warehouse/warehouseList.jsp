<%@page pageEncoding="UTF-8"%>
       <table class="table table-bordered table-hover dataTable text-center">
            <tr class="info">
              <th>名称</th>
              <th>地址</th>
              <th>联系人</th>
              <th>联系电话</th>
              <th>仓库电话</th>
              <th>仓管人数</th>
              <th>仓管类型</th>
              <th>仓管状态</th>
              <th>操作</th>
            </tr>
           <c:forEach var="item" items="${tList}" varStatus="s">
            <tr>   
              <td>${item.warehouseName}</td>
              <td><div class="text-ellipsis" style="width:400px;">${item.province}-${item.city}-${item.country}-${item.street}-${item.warehouseAddress}</div></td>
              <td>${item.contract}</td>
              <td>${item.contractPhone}</td>
              <td>${item.warehouseTel}</td>
              <td>${item.count}</td>
              <td>
              	<c:if test="${item.shopType == '122'}"> 
              		自营仓库
                </c:if>
                <c:if test="${item.shopType == '123'}">
                	代理仓库
                </c:if>
              </td>
               <td> 
                
                <c:if test="${'0'==item.states}"> 
	                	已停用
	            </c:if>  
	            <c:if test="${'1' == item.states}"> 
	                	启用
	             </c:if>
              </td>
              <td> 
                <button type="button" class="btn btn-primary btn-sm" onclick="showAddUserInfo('${item.id}');">分配管理员</button> 
                <button type="button" class="btn btn-primary btn-sm" onclick="showUpdatePage('${item.id}');">修改</button>
                <c:if test="${'0'==item.states}"> 
	                <button type="button" class="btn btn-primary btn-sm" onclick="changeStates('${item.id}','1');">启用</button>
	            </c:if>  
	            <c:if test="${'1' == item.states}"> 
	                <button type="button" class="btn btn-primary btn-sm" onclick="changeStates('${item.id}','0');">停用</button>
	             </c:if>
              </td>
            </tr>
           </c:forEach>
        </table>
     <liguo:pagination dataDomId="key_warehouse_list"  pageAction="serachWarehouse"/>