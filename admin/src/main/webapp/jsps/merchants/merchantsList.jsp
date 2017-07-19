<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
         <tr class="info">
           <th>品牌</th>
           <th>招商层级</th>
           <th>招商区域</th>
           <th>招商位数</th>
           <th>招商情况</th>
           <th>招商购买量</th>
           <th>优惠券数量</th>
           <th>招商状态</th>
           <th>操作选项</th>
         </tr>
        
          <c:forEach items="${merchants}" var="item">
         <tr>
           <td>
           ${item.producttypeName} - ${item.brandName} 
           </td>
           <td>
            ${item.levelName}
           </td>
           <td>
            
             ${item.addressName}
           </td>
             <td>
            
             ${item.places}
           </td>
           <td>
              ${item.views}人次围观/${item.participate}人参与/${item.winning}人成功
           </td>
           <td>
             ${item.number}
           </td>
           <td>
             ${item.coupons}
           </td>
           
           <td id="statusTd_${item.id}">
			<c:if test="${item.state==0}">未发布</c:if>
			<c:if test="${item.state==1}">发布中</c:if>
			<c:if test="${item.state==2}">失效</c:if>
			</td>
           
           <td id="buttonA_${item.id}">
             <a href="merchantsDetails?id=${item.id}" class="btn btn-primary btn-sm">详情</a>
             <a href="updateMerchants?id=${item.id}" class="btn btn-primary btn-sm">修改</a>
           
             <a class="btn btn-primary btn-sm" onclick="updateMerchantsState(${item.id})">
             	  <c:if test="${item.state ==0}">发布</c:if>
             	<c:if test="${item.state ==1}">下线</c:if>
             	<c:if test="${item.state ==2}">再次发布</c:if>
             	</a>
           </td>
         </tr>
         </c:forEach>
        
       </table>
      <liguo:pagination pageAction="serachMerchants" />