<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>厂家ID</th>
                    <th>品牌名称</th>
                    <th>公司名称</th>
                    <th>平台网址</th>
                 <!--    <th>品牌描述</th> -->
                    <th>分类</th>
                     <th>类型</th>
                  <!--   <th>排序</th> -->
                    <th>操作选项</th>
                  </tr>
             	<c:forEach var="item" items="${bList}" varStatus="s">
                  <tr>
                    <td>${item.id}</td>
                    <td>${item.name }</td>
                    <td>${item.manufacturerName }</td>
                    <td>${item.url }</td>
                  <%--   <td>${item.remark }</td>deleteBrandShow --%>
                    <td>${item.productTypeName }</td> 
                    <td>
                    	 <c:if test="${item.type==0}">厂商</c:if>
			             <c:if test="${item.type==1}">自有</c:if>
                    </td>
                  <%--   <td>${item.sort }</td>delectBrandValidate --%>
                    <td>
                      <button onclick="goUpdatePage(${item.id});" type="button" class="btn btn-primary btn-sm">修改</button>
                      <button type="button" onclick="delectBrandValidate(${item.id});" class="btn btn-primary btn-sm">删除</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_brand_list"  pageAction="serachBrand"/>