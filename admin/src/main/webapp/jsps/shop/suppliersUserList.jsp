<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <table class="table table-bordered table-hover dataTable text-center table-bordered">
         <tr class="info">
           <th>ID</th>
           <th>企业名称</th>
           <th>企业地址</th>
           <th>经营品类</th>
           <th>手机号码</th>
           <th>状态</th>
           <th>企业法人</th>
           <th>操作选项</th>
         </tr>
         <c:forEach items="${suppliers}" var="item">
         <tr>
           <td> ${item.id}</td>
           <td>  ${item.companyName}</td>
           <td>  ${item.address}</td>
           <td> ${item.scope}</td>
           <td> ${item.mobile}</td>
           <td id="statusId_${item.id}">
           <c:if test="${item.authStatus==0}">待审核</c:if>
			<c:if test="${item.authStatus==1}">审核通过</c:if>
			<c:if test="${item.authStatus==2}">审核拒绝</c:if>
			<c:if test="${item.authStatus==3}">关闭</c:if>
			</td>
           <td>  ${item.legalRepresentative}</td>
           <td>
             
             <a href="suppliersDetail?id=${item.id}" class="btn btn-primary btn-sm">详情</a>
             <a href="updateSuppliers?id=${item.id}" class="btn btn-primary btn-sm">修改</a>
             <button type="button" class="btn btn-primary btn-sm" onclick="showCancleOrderDivlog(${item.id},${item.userId},${item.authStatus})">审核</button>
           </td>
         </tr>
         </c:forEach>
         
     </table>

   <liguo:pagination pageAction="searchResult" />         