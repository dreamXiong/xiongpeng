<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="审核修改"/> 
<tiles:put name="body" type="String">   
<head>
	<meta charset="UTF-8">
	<title>审核修改</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/district.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<script src="${ctx}/js/hgl/userimageupdate.js"></script>
<style type="text/css">
.onerrInput {
    border: 1px solid red!important;
}
</style>

</head>

<body>
   

<!-- 内容板块开始 -->
	<div class="cantainer">
			<div class="main-right pull-right">
				<div>${msg}</div>
				<c:if test="${errcode == '2'}">
				<div class="error" style="margin-top: 20px;">
					<form id="shopEdit" action="shopEdit" class="form-horizontal" method="post">
					 <input type="hidden" name="licenseImage" id="pImgOne_Up_Show"  value="${shopDto.licenseImage}"/>
			           <input type="hidden" name="taxpayerImage" id="pImgTwo_Up_Show" value="${shopDto.taxpayerImage}"/>
			           <input type="hidden" name="organizationImage" id="pImgThree_Up_Show" value="${shopDto.organizationImage}"/>
			           <input type="hidden" name="shopImage1" id="dImgOne_Up_Show" value="${shopDto.shopImage1}"/>
			           <input type="hidden" name="shopImage2" id="dImgTwo_Up_Show" value="${shopDto.shopImage2}"/>
			           <input type="hidden" name="shopImage3" id="dImgThree_Up_Show" value="${shopDto.shopImage3}"/>
			           
			           <input type="hidden"  id="provinceCode" value="${shopDto.regProvince}"/>
			           <input type="hidden" id="cityCode" value="${shopDto.regCity}"/>
			           <input type="hidden"  id="countryCode" value="${shopDto.regCountry}"/>
			           <input type="hidden"  id="streetCode" value="${shopDto.regStreet}"/>
			               	
			            <input type="hidden"  id="id" name="id" value="${shopDto.id}"/>
			            <input type="hidden"  id="version" name="version" value="${shopDto.version}"/> 
					<table>
						<tr>
							<th>管理用户名</th>
							<td>${shopDto.userName}</td>
							<th>手机号码</th>
							<td>${shopDto.mobile}</td>
							<th>审核状态</th>
							<td>${shopDto.stateName}</td>
						</tr>
						<tr>
							<th>企业名称</th>
							<td ><input  id="shopName" name="shopName" value="${shopDto.shopName}" type="text" class="form-control inputNotNull "  maxlength="50"></td>
							<th>经营品类</th>
							<td >
								<input id="scope" name="scope" value="${shopDto.scope}" type="text" class="form-control inputNotNull" maxlength="50">
							</td>
							<c:if test="${shopDto.shopType ==102 }" var="iscj">
							<th>电话号码</th>
                   			 <td><input  id="companyTel" name="companyTel" value="${shopDto.companyTel}"  type="text" class="form-control tel inputNotNull"></td>
							</c:if>
							<c:if test="${!iscj}"><th></th><td></td></c:if>
						</tr>
						 <c:if test="${iscj}">
						 <tr>
		                    <th>品牌名称</th>
		                    <td><input id="brandName" name="brandName" value="${shopDto.brandName}" type="text" class="form-control inputNotNull" maxlength="20"></td>
		                    <th>经营类别</th>
		                    <td>
		                    	<select id=productTypeId name="productTypeId"  class="form-control ">
			                          <c:forEach  var="item" items="${productTypes}" varStatus="status">
			                          <option 
			                          	<c:if test="${item.id == shopDto.productTypeId}">
			                    		selected='selected'
			                    	</c:if>
			                          value="${item.id}">${item.name}</option>
			                          </c:forEach>
								</select>
		                    </td>
		                    <th>企业法人</th>
		                    <td><input  id="legalRepresentative" name="legalRepresentative" value="${shopDto.legalRepresentative}" type="text" class="form-control inputNotNull" maxlength="10"></td>
		                </tr>
		                </c:if>
						<tr>
							<th>企业地址</th>
							<td>
								<select id="province" name="regProvince" class="select1 form-control"  onchange="changeGrade();" value="${shopDto.regProvince}">
								 <option value="0">请选择</option>
	                          <c:forEach  var="item" items="${provinceInfos}" varStatus="status" >
	                         	
	                         	  <option
		                    	<c:if test="${item.id == shopDto.regProvince}">
		                    		selected='selected'
		                    	</c:if>
		                 			value="${item.id}">${item.name}</option> 
	                          </c:forEach>
							</select>
							</td>
							<td>
								<select id="city" name="regCity" class="select1 form-control" onchange="changeCity();">
								</select>
							</td>
							<td>
								<select id="country" name="regCountry"  class="select1 form-control"  onchange="changeCountry();">
								
								</select>
							</td>
							<td>
								<select id="street" name="regStreet" class="select1 form-control" >
								
								</select>
							</td>
							<td><input id="regAddress" name="regAddress" value="${shopDto.regAddress}" type="text" class="form-control" maxlength="30"></td>
						</tr>
						<tr class="aptitude">
							<th>企业资质</th>
							<td colspan="5" class="clear">
								<div class="pull-left text-center">
									<p>营业执照</p>
									<div id="imgdiv">
										<input type="file" name="imgFile" class="pUpdate" id="pImgOne_Up"/>
		                                <div id="pImgOnedivUp">
						                  <c:if test="${not empty shopDto.licenseImage}">
						                    <img id="pImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.licenseImage}'/>
						                   </c:if>
						                   <c:if test="${empty shopDto.licenseImage}">
						                    <img id="pImgOneShowUp"/>
						                   </c:if>
						                    
					                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
								<div class="pull-left text-center">
									<p>一般纳税人</p>
									<div id="imgdiv1">
										<input type="file" name="imgFile" class="pUpdate" id="pImgTwo_Up"/>
						                  <div id="pImgTwodivUp">
							                  <c:if test="${not empty shopDto.taxpayerImage}">
							                    <img id="pImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.taxpayerImage}'/>
							                   </c:if>
							                   <c:if test="${empty shopDto.taxpayerImage}">
							                    <img id="pImgTwoShowUp"/>
							                   </c:if>
							                    
						                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
								<div class="pull-left text-center">
									<p>组织机构</p>
									<div id="imgdiv2">
										<input type="file" name="imgFile" class="pUpdate" id="pImgThree_Up"/>
		                               <div id="pImgThreedivUp">
						                  <c:if test="${not empty shopDto.organizationImage}">
						                    <img id="pImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.organizationImage}'/>
						                   </c:if>
						                   <c:if test="${empty shopDto.organizationImage}">
						                    <img id="pImgThreeShowUp"/>
						                   </c:if>
						                    
					                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
							</td>
						</tr>
						<tr class="aptitude">
							<th>工厂图片</th>
							<td colspan="5" class="clear">
								<div class="pull-left text-center">
									<p>工厂图片1</p>
									<div id="imgdiv3">
										<input type="file" name="imgFile" class="pUpdate" id="dImgOne_Up">
						                  <div id="dImgOnedivUp">
						                  	 <c:if test="${not empty shopDto.shopImage1}">
							                     <img id="dImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage1}'>
							                 </c:if>
							                 <c:if test="${empty shopDto.shopImage1}">
							                     <img id="dImgOneShowUp">
							                 </c:if>
						                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
								<div class="pull-left text-center">
									<p>工厂图片2</p>
									<div id="imgdiv4">
										 <input type="file" name="imgFile" class="pUpdate" id="dImgTwo_Up">
						                  <div id="dImgTwodivUp">
						                  	<c:if test="${not empty shopDto.shopImage2}">
							                    <img id="dImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage2}'>
							                 </c:if>
						                    <c:if test="${empty shopDto.shopImage2}">
							                    <img id="dImgTwoShowUp">
							                 </c:if>
						                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
								<div class="pull-left text-center">
									<p>工厂图片3</p>
									<div id="imgdiv5">
										 <input type="file" class="pUpdate" name="imgFile" id="dImgThree_Up">
						                  <div id="dImgThreedivUp">
						                  	<c:if test="${not empty shopDto.shopImage3}">
							                   <img id="dImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage3}'>
							                 </c:if>
						                    <c:if test="${empty shopDto.shopImage3}">
							                   <img id="dImgThreeShowUp" >
							                 </c:if>
						                  </div>
										<span class="icon-plus"></span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td colspan="5">
								<button type="button" onclick="shopEdit();" class="btn ">确定</button>
								
							</td>
						</tr>
					</table>
					</form>
				</div>
				</c:if>
			</div>
	</div>
<!-- 内容板块结束 -->




<div id="datepicker"></div>
</body>

</tiles:put>
</tiles:insert>