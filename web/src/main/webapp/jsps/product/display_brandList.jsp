<%@page pageEncoding="UTF-8"%>
<table class="col-10 hover" style="margin-bottom:10px;">
		<thead class="bg-gray">
                    <th width="214">品牌名称</th>
                    <th width="220">公司名称</th>
                    <!-- <th width="200">平台网址</th> -->
                    <th width="192">分类</th>
                    <th width="120">类型</th>
                 
                 </thead>
		<tbody>
             	<c:forEach var="item" items="${bList}" varStatus="s">
             		<c:if test="${s.index%2 == '0'}" var="isqi">
					  <tr>
					</c:if>
					<c:if test="${!isqi}">
					  <tr class="odd">
					</c:if>   
                    <td>${item.name }(${item.id })</td>
                    <td>${item.manufacturerName }</td>
                   <%--  <td>${item.url }</td> --%>
                    <td>${item.producttypeName }</td> 
                    <td>
                    	 <c:if test="${item.type==0}"><span style="color:blue;">系统</span></c:if>
			             <c:if test="${item.type==1}"><span style="color:green;">非系统</span></c:if>
                    </td>
                  </tr>  
                  </c:forEach>   
            </tbody>   
                </table>
       <liguo:pagination pageAction="serachBrand"/>