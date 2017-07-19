<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <table class="table table-bordered table-hover dataTable text-center table-bordered">
         <tr class="info">
           <th>ID</th>
           <th>店铺名称</th>
           <th>企业地址</th>
           <th>经营品类</th>
           <th>手机号码</th>
           <th>状态</th>
           <th>结算服务</th>
           <th>金牌经销商服务</th>
           <th>操作选项</th>
         </tr>
         <c:forEach items="${dealers}" var="item">
         <tr>
           <td>${item.id}</td>
           <td>${item.shopName}</td>
           <td>${item.address}</td>
           <td>${item.scope}</td>
           <td>${item.mobile}</td>
           <td id="statusId_${item.id}">
	            <c:if test="${item.authStatus==0}">待审核</c:if>
				<c:if test="${item.authStatus==1}">审核通过</c:if>
				<c:if test="${item.authStatus==2}">审核拒绝</c:if>
				<c:if test="${item.authStatus==3}">关闭</c:if>
			</td>
          <td id="settlement_${item.id}">
	          <c:if test="${empty item.settlement}">
		           <c:if test="${item.balance==0}">暂无</c:if>
				   <c:if test="${item.balance==1}">结算申请（${item.remark }）</c:if>
			  </c:if>
			    <c:if test="${not empty item.settlement}">
				   <c:if test="${item.balance==1}">平台结算</c:if>
			  </c:if>
		  </td>
		  <td id="medalAgent_${item.id}">
		  	  <c:if test="${item.medalAgentFlag !=1 and item.medalAgentFlag !=2}">
				暂无
			  </c:if>
			  <c:if test="${item.medalAgentFlag ==1}">
				金牌经销商申请（${item.remark }）
			  </c:if>
			  <c:if test="${item.medalAgentFlag ==2}">
				金牌经销商
			  </c:if>
		  </td>
           <td>  
             <c:if test="${empty item.settlement and item.balance != 0}">
             	<a id="showOpenSettlement" onclick="showOpenSettlement('${item.id}','settlement');" class="btn btn-primary btn-sm">开通结算</a>
             </c:if>
             <c:if test="${item.medalAgentFlag == 1}">
             	<a id="showOpenMedalAgentFlag" onclick="showOpenSettlement('${item.id}','medalAgent');" class="btn btn-primary btn-sm">金牌经销商开通结算</a>
             </c:if>
             <a href="dealersDetail?id=${item.id}" class="btn btn-primary btn-sm">详情</a>
             <a href="updateDealers?id=${item.id}" class="btn btn-primary btn-sm">修改</a>
             <button type="button" class="btn btn-primary btn-sm" onclick="showCancleOrderDivlog(${item.id},${item.userId},${item.authStatus })">审核</button>
           </td>
         </tr>
         </c:forEach>
         
     </table>

   <liguo:pagination pageAction="serachDealers" />         