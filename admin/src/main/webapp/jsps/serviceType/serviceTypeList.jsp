<%@ page language="java" pageEncoding="UTF-8"%>
<c:if test="${not empty sList}">
<ul id="secondForAjax" class="nav text-center two">
  <li id="asfdsf" onclick="goSecondServiceTypeAddPage();" class="nav-title ">
    <a >添加二级服务</a>
  </li> 
	<c:forEach var="sItem" items="${pList}" varStatus="s">  
	<li onclick="scoendProductType(${sItem.id});" class="second_${s.index} clearfix" >
		<div class="pull-left">
		    <a >${sItem.name}</a>
		 </div>
		 <div class="pull-right">
		   <a onclick="updateSecondServiceType(${sItem.id});">修改</a>
		   <a onclick="deleteSecondServiceType(${sItem.id})">删除</a>
		 </div>
	 </li>
</c:forEach> 
  </ul>
</ul>
</c:if>