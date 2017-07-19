<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>编号</th>
                    <th>商品类型名称</th>
                    <th>商品类型描述</th>
                    <th>状态</th>
                    <th>操作</th>
                  </tr>
             	<c:forEach var="item" items="${integralMallTypeList}" varStatus="s">
                  <tr>
                    <td>${s.index+1}</td>
                    <td>${item.goodsTypeName }</td>
                    <td style="text-align: left;">${item.goodsTypeDescribe }</td> 
                    <td>${item.status == 0 ? "禁用" : "启用"}</td>           
                    <td>
                      <button type="button" onclick="window.location.href = '${ctx}/integralMallType/getIntegralMallTypeDetailPage?id=${item.id }'" class="btn btn-primary btn-sm">详情</button>
                      <button onclick="goUpdatePage('${item.id}');" type="button" class="btn btn-primary btn-sm">修改</button>
                      <button onclick="operationStatus('${item.id}','${item.status == 0 ? "1" : "0"}');" type="button" class="btn btn-primary btn-sm">${item.status == 0 ? "启用" : "禁用"}</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_integralMallType_list"  pageAction="searchResult"/>