<%@page pageEncoding="UTF-8"%>
	<div class="clear">
		<label>分类</label>
		<input type="text" id="typeName1" style="width: 101px;" disabled="disabled" value='${pifd.mainType}'>
		<c:if test="${pifd.mainType == pifd.parentType }">
          	<input type="text" id="typeName2" style="width: 101px;" disabled="disabled" value='${pifd.thirdType}'>
          	<input type="text" id="typeName3" style="width: 101px;" disabled="disabled" value=''>
        </c:if>
        <c:if test="${pifd.mainType != pifd.parentType }">
          	<input type="text" id="typeName2" style="width: 101px;" disabled="disabled" value='${pifd.parentType}'>
          	<input type="text" id="typeName3" style="width: 101px;" disabled="disabled" value='${pifd.thirdType}'>
        </c:if> 
	</div>
	<div class="clear">
		<label>品牌</label>
		<input type="text" id="breadName" disabled="disabled" value='${pifd.brandname}'>
	</div>
	<div class="clear">
		<label>名称</label>
		<input type="text" id="productName" disabled="disabled" value='${pifd.name}'>
	</div>
	<div class="clear">
		<label>价格</label>
		<input type="text" id="price" disabled="disabled" value='${pifd.price}'>
	</div>
	<div class="clear">
		<label>计量单位</label>
		<input type="text" id="metis" disabled="disabled" value="${pifd.meterageUnit }">
	</div>
	<div class="clear">
		<label>产品图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	<c:if test="${not empty pifd.pimgOne}">
                  <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgOne}'/>
                 </c:if>
                 <c:if test="${empty pifd.pimgOne}">
                  <img />
                 </c:if>
            </div>
            <div class="img-list">
            	<c:if test="${not empty pifd.pimgTwo}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgTwo}'>
	                   </c:if>
                  	 <c:if test="${empty pifd.pimgTwo}">
	                     <img>
	                 </c:if>
            </div>
            <div class="img-list">
            	<c:if test="${not empty pifd.pimgThree}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.pimgThree}'>
	                 </c:if>
                    <c:if test="${empty pifd.pimgThree}">
	                     <img>
	                 </c:if>
            </div>
        </div>
	</div>
	<div class="clear des-img">
		<label>产品描述图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	<c:if test="${not empty pifd.dimgOne}">
	                     <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgOne}'>
	                 </c:if>
	                 <c:if test="${empty pifd.dimgOne}">
	                     <img>
	                 </c:if>
            </div>
            <div class="img-list">
            		<c:if test="${not empty pifd.dimgTwo}">
	                    <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgTwo}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgTwo}">
	                    <img>
	                 </c:if>
            </div>
            <div class="img-list">
            	<c:if test="${not empty pifd.dimgThree}">
	                   <img src='generateImage?id=${pifd.id}&imgName=${pifd.dimgThree}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgThree}">
	                   <img>
	                 </c:if>
            </div>
        </div>
	</div>
	<div class="clear">
		<label>描述</label>
		<textarea rows="3" id="descript" disabled="disabled">${pifd.describes}</textarea>
	</div>
	<div class="clear">
		<label>附加属性</label>
		<div class="pull-left inp-short clear">
		 	<c:forEach var="item" items="${attr}" varStatus="s">
              <c:if test="${not empty item}">
              <div class="pull-left">
                <input type="text" disabled="disabled" class="form-control" value="${item}">
              </div>
              </c:if>
            </c:forEach>
		</div>
	</div>