<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>编号</th>
                    <th>店铺名称</th>
                    <th>公众号ID</th>
                    <th>商户号ID</th>
                    <th>商户密钥</th>
                    <th>微信回调URL</th>
                    <th>状态</th>
                    <th>操作</th>
                  </tr>
             	<c:forEach var="item" items="${dealersWeixinConfig}" varStatus="s">
                  <tr>
                    <td>${s.index+1}</td>
                    <td>${item.shopName }</td>
                    <td>${item.appId }</td>
                    <td>${item.mchId }</td> 
                    <td>${item.appKey }</td> 
                    <td>${item.notifyUrl }</td> 
                    <td>${item.status == 0 ? "禁用" : "启用"}</td> 
                    <td>
                      <button onclick="operationStatus('${item.id}','${item.status == 0 ? "1" : "0"}');" type="button" class="btn btn-primary btn-sm">${item.status == 0 ? "启用" : "禁用"}</button>
                      <button onclick="goUpdatePage('${item.id}');" type="button" class="btn btn-primary btn-sm">修改</button>
                      <button type="button" onclick="delectDealersWeixinConfigValidate('${item.id}');" class="btn btn-primary btn-sm">删除</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_dealersWeixinConfig_list"  pageAction="searchResult"/>