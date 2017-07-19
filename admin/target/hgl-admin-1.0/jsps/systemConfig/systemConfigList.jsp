<%@page pageEncoding="UTF-8"%>
 <table class="table table-bordered table-hover dataTable text-center table-bordered">
                  <tr class="info">
                    <th>配置ID</th>
                    <th>配置名称</th>
                    <th>配置值</th>
                    <th>配置描述</th>
                    <th>操作选项</th>
                  </tr>
             	<c:forEach var="item" items="${systemConfigList}" varStatus="s">
                  <tr>
                    <td>${item.id}</td>
                    <td>${item.configKey }</td>
                    <td>${item.configValue }</td>
                    <td>${item.remark }</td> 
                    <td>
                      <button onclick="goUpdatePage(${item.id});" type="button" class="btn btn-primary btn-sm">修改</button>
                      <button type="button" onclick="delectSystemConfigValidate(${item.id});" class="btn btn-primary btn-sm">删除</button>
                    </td>
                  </tr>
                  </c:forEach>
                </table>
       <liguo:pagination dataDomId="key_systemConfig_list"  pageAction="searchResult"/>