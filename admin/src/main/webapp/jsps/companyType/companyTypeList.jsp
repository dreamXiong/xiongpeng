<%@page pageEncoding="UTF-8"%>
  <table class="table table-bordered table-hover dataTable text-center table-bordered">
     <tr class="info">
       <th>名称</th>
       <th>操作选项</th>
     </tr>
     <c:forEach var="item" items="${tbCompanyType}" varStatus="s">
     <tr>
       <td>${item.name}</td>
       <td>
         <button type="button" onclick="showdetailsInfo('${item.id}');" class="btn btn-primary btn-sm">详情</button>
         <button type="button" onclick="showUpdate('${item.id}');" class="btn btn-primary btn-sm" data-toggle="modal">修改</button>
         <button type="button" onclick="delServiceInfoVal('${item.id}')" class="btn btn-primary btn-sm">删除</button>
       </td>
     </tr>
     </c:forEach>
   </table>
