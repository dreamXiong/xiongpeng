<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <table class="table table-bordered table-hover dataTable text-center table-bordered">
         <tr class="info">
           <th style="width:30%;">公司名称</th>
           <th style="width:30%;">公司地址</th>
           <th style="width:10%;">服务类型</th>
           <th style="width:10%;">联系人</th>
           <th style="width:10%;">联系号码</th>
           <th style="width:10%;">操作选项</th>
         </tr>
         <c:forEach items="${cList}" var="item">
         <tr id="delete_${item.id}">
           <td>${item.companyName}</td>
           <td>${item.provinceName}${item.cityName}${item.countryName}${item.streetName}${item.regAddress}</td>
           <td>${item.tName}</td>
           <td>${item.contract}</td>
           <td>${item.contractPhone}</td>
           <td>  
             <a onclick="showdetailsInfo(${item.id});" class="btn btn-primary btn-xs">详情</a>
             <a href="updateInfoShow?id=${item.id}" class="btn btn-primary btn-xs">修改</a>
             <a onclick="deleteShow(${item.id});" class="btn btn-primary btn-xs">删除</a>
           </td>
         </tr>
         </c:forEach>
         
     </table>
   <liguo:pagination  pageAction="serachInfo" />         