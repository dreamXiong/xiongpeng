<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <table class="table table-bordered table-hover dataTable text-center table-bordered">
         <tr class="info">
           <th>用户名</th>
           <th>姓名</th>
           <th>银行账户</th>
           <th>开户行</th>
           <th>金额</th>
           <th>时间</th>
           <th>状态</th>
           <th>操作选项</th>
         </tr>
         <c:forEach items="${withdrawalsDtos}" var="item">
         <tr>
           <td> ${item.userName}</td>
           <td> ${item.cardHolder}</td>
           <td> ${item.bankAccount}</td>
           <td>  <span style="margin-right: 10px;">${item.bank}</span>  ${item.branch}</td>
           <td> ${item.money}</td>
           <td>
           	<liguo:dateFormatLabel
						value="${item.operationDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
           </td>
           <td id="statusId_${item.id}">
           <c:if test="${item.state==0}">待审核</c:if>
			<c:if test="${item.state==1}">审核通过</c:if>
			<c:if test="${item.state==2}">审核拒绝</c:if>
			</td>
          
           <td id="buttonId_${item.id}">
		         <c:if test="${item.state==0}">
		         	<button type="button" class="btn btn-primary btn-sm" onclick="doAutomaticOder(${item.id})">审核通过</button>
		         	<button type="button" class="btn btn-primary btn-sm" onclick="refuseAutomaticShow(${item.id})">审核拒绝</button>
		         </c:if> 
         		<c:if test="${item.state == 1 || item.state == 2}">-</c:if>
           </td>
         </tr>
         </c:forEach>
     </table>

   <liguo:pagination  pageAction="searchResult" />         