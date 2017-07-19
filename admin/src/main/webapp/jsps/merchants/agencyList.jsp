<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>代理商名称</th>
                    <th>代理地区</th>
                    <th>招商层级</th>
                    <th>招商服务费</th>
                    <th>服务费状态</th>
                    <th>订单状态</th>
                    <th>招商状态</th>
                  </tr>
                  <c:forEach items="${agencies}" var="item">
                  <tr>
                    <td>
        			 ${item.shopName}
                    </td>
                    <td>
                     ${item.palceName}
                    </td>
                    <td>
                     ${item.levelName}
                    </td>
                    <td>
                      ${item.coupons}
                    </td>
                    <td>
                     <c:if test="${item.couponsState==0}">未支付</c:if>
					 <c:if test="${item.couponsState==1}">已支付</c:if>
                    </td>
                     <td>
                     <c:if test="${item.orderState==200}">未支付</c:if>
					 <c:if test="${item.orderState==220}">已支付</c:if>
                    </td>
                    <td >
                    	<span id="statusId_${item.id}">
                    		<c:if test="${item.state==0}">未生效</c:if>
							 <c:if test="${item.state==1}">暂时生效</c:if>
							 <c:if test="${item.state==2}">生效</c:if>
							 <c:if test="${item.state==3}">失效</c:if>
                    	</span>
                 	  
                      <button type="button" class="btn btn-primary btn-sm" onclick="showAgency(${item.id},${item.state})">变更</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
                
   <liguo:pagination pageAction="serachMerchantsDetails" />             