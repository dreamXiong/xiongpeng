<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>编号</th>
                    <th>商品名称</th>
                    <th>用户名称</th>
                    <th>支付金额</th>
                    <th>剩余积分</th>
                    <th>兑换数量</th>
                    <th>兑换时间</th>
                    <th>收货人</th>
                    <th>收货人电话</th>
                    <th>收货人地址</th>
                    <th>备注信息</th>      
                    <th>支付状态</th>
                    <th>发货状态</th>
                    <th>操作</th>
                  </tr>
             	<c:forEach var="item" items="${integralMallRecordList}" varStatus="s">
                  <tr>
                    <td>${s.index+1}</td>
                    <td>${item.goodsName }</td>
                    <td>${item.userName }</td>
                    <td>￥${item.payMoney }</td>
                    <td>${item.remainingIntegral }</td>
                    <td>${item.exchangeNum }</td>
                    <td><liguo:dateFormatLabel value="${item.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
                    <td>${item.recipient }</td>
                    <td>${item.phone }</td>
                    <td>${item.extensionField }</td>
                    <td>${item.remark }</td>
                    <td><liguo:dictLabel key="${item.payStatus }" /></td>
                    <td><liguo:dictLabel key="${item.platStatus }" /></td>
                    <td>
                      <c:if test="${item.platStatus == 1108}">
                     	 <button onclick="operationStatus('${item.id}');" type="button" class="btn btn-primary btn-sm">发货</button>
                      </c:if>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_integralMallRecord_list"  pageAction="searchResult"/>