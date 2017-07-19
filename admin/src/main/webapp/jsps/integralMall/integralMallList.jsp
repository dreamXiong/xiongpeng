<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th style="width:5%;">编号</th>
                    <th  style="width:10%;">商品类型名称</th>
                    <th  style="width:15%;">商品名称</th>
                    <th  style="width:10%;">积分</th>
                    <th  style="width:5%;">需支付金额</th>
                    <th  style="width:5%;">商品市场价</th>
                    <th  style="width:30%;">商品描述</th>
                    <th  style="width:5%;">状态</th>
                    <th  style="width:15%;">操作</th>
                  </tr>
             	<c:forEach var="item" items="${integralMallList}" varStatus="s">
                  <tr>
                    <td>${s.index+1}</td>
                    <td>${item.goodsTypeName }</td>
                    <td>${item.goodsName }</td>
                    <td>${item.integral }</td>
                    <td>￥ ${item.payAmount }</td>                            
                    <td>￥ ${item.marketAmount }</td> 
                    <td style="text-align: left;">${item.goodsDescribe }</td> 
                    <td>${item.status == 0 ? "禁用" : "启用"}</td>           
                    <td>
                      <button type="button" onclick="window.location.href = '${ctx}/integralMall/getIntegralMallDetailPage?id=${item.id }'" class="btn btn-primary btn-sm">详情</button>
                      <button onclick="goUpdatePage('${item.id}');" type="button" class="btn btn-primary btn-sm">修改</button>
                      <button onclick="operationStatus('${item.id}','${item.status == 0 ? "1" : "0"}');" type="button" class="btn btn-primary btn-sm">${item.status == 0 ? "启用" : "禁用"}</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_integralMall_list"  pageAction="searchResult"/>