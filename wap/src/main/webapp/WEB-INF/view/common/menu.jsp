<%@page pageEncoding="UTF-8"%>
<ul class="leftMenu"> 
<%-- 	 <c:forEach items="${usrMenuItems }" var="item">  
		  <c:set var="gno" ><fmt:parseNumber value="${platformMenuGroupNo / 10000 }" integerOnly="true" ></fmt:parseNumber></c:set>
		  <c:set var="onFlag" value="${gno==item.id || item.id == 3}"></c:set>
			<li id="key${item.id}"><a  class='<c:if test="${onFlag }"> on </c:if> key_menu' id="key${item.id}_a" href="###">${item.itemName }</a>
				<ul id="key${item.id}_a_items" class='<c:if test="${onFlag == false  }">none</c:if>'> 
				<c:forEach var="d" items="${item.detailItems }">			 
					<li><a class='<c:if test="${platformMenuGroupNo == d.id }"> subon </c:if>'  href="d.url">${d.name }</a></li>
				</c:forEach>
				</ul> 
			</li>
		</c:forEach> --%>
	
</ul>
<div class="leftBottom"></div>


