<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="厂家详情修改" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>厂家详情修改</title>
  <link rel="stylesheet" href="${ctx}/css/vender.css">
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<script src="${ctx}/js/hgl/userimageupdate.js"></script>
<script src="${ctx}/js/hgl/district.js"></script>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
<input id="msgPosition" value="false" type="hidden"/>

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>厂家详情修改</small>
      </h1>
    </section>
   <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
          <form id="updateSuppliers" action="updateSuppliers" class="form-horizontal" method="post">
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
            <table class="table">
            	 <tr>
                   	<td class="th">管理用户名</td>
                    <td class="sel">${shopDto.userName}</td>
                    <td class="th">手机号码</td>
                    <td class="sel">${shopDto.mobile}</td>
                    <td class="th" >审核状态</td>
                    <td class="sel">
                    	${shopDto.stateName}
                    </td>
                </tr>
                <tr>
                    <td class="th">企业名称</td>
                    <td ><input  id="companyName" name="companyName" value="${shopDto.companyName}" type="text" class="form-control inputNotNull" maxlength="50"></td>
                    <td class="th" >经营品类</td>
                    <td ><input id="scope" name="scope" value="${shopDto.scope}" type="text" class="form-control inputNotNull" maxlength="50"></td>
                    
                     <td class="th">电话号码</td>
                    <td><input  id="companyTel" name="companyTel" value="${shopDto.companyTel}"  type="text" class="form-control tel inputNotNull"></td>
                </tr>
                <tr>
                    <td class="th">品牌名称</td>
                    <td><input id="brandName" name="brandName" value="${shopDto.brandName}" type="text" class="form-control inputNotNull" maxlength="20"></td>
                    <td class="th">经营类别</td>
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
                    <td class="th">企业法人</td>
                    <td><input  id="legalRepresentative" name="legalRepresentative" value="${shopDto.legalRepresentative}" type="text" class="form-control inputNotNull" maxlength="10"></td>
                </tr>
              
                <tr>
                   <!--  <td class="th">税务登记号</td>
                    <td><input type="text" class="form-control"></td>
                    <td class="th">营业执照号</td>
                    <td><input type="text" class="form-control"></td> -->
                      <td class="th">企业地址</td>
                     <td colspan="3" class="sel">
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
							<select id="city" name="regCity" class="select1 form-control" onchange="changeCity();">
								
							</select>
							<select id="country" name="regCountry"  class="select1 form-control"  onchange="changeCountry();">
								
							</select>
							<select id="street" name="regStreet" class="select1 form-control" >
								
							</select>
                    </td>
                   <td class="th">具体地址</td>
                    <td><input id="regAddress" name="regAddress" value="${shopDto.regAddress}" type="text" class="form-control" maxlength="30"></td>
                   
                </tr>
                
                <tr>
                    <td class="th">企业资质</td>
                    <td colspan="5">
                    	 <div class="pull-left">
                            <div class="text-center">营业执照</div>
                            <div class="col-sm-3">
                            <input type="file" name="imgFile" class="pUpdate" id="pImgOne_Up"/>
                                <div id="pImgOnedivUp">
				                  <c:if test="${not empty shopDto.licenseImage}">
				                    <img id="pImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.licenseImage}'/>
				                   </c:if>
				                   <c:if test="${empty shopDto.licenseImage}">
				                    <img id="pImgOneShowUp"/>
				                   </c:if>
				                    
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                          <div class="pull-left">
                            <div class="text-center">一般纳税人</div>
                            <div class="col-sm-3">
                             <input type="file" name="imgFile" class="pUpdate" id="pImgTwo_Up"/>
			                  <div id="pImgTwodivUp">
				                  <c:if test="${not empty shopDto.taxpayerImage}">
				                    <img id="pImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.taxpayerImage}'/>
				                   </c:if>
				                   <c:if test="${empty shopDto.taxpayerImage}">
				                    <img id="pImgTwoShowUp"/>
				                   </c:if>
				                    
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                        
                          <div class="pull-left">
                            <div class="text-center">组织机构</div>
                            <div class="col-sm-3">
                            	<input type="file" name="imgFile" class="pUpdate" id="pImgThree_Up"/>
                               <div id="pImgThreedivUp">
				                  <c:if test="${not empty shopDto.organizationImage}">
				                    <img id="pImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.organizationImage}'/>
				                   </c:if>
				                   <c:if test="${empty shopDto.organizationImage}">
				                    <img id="pImgThreeShowUp"/>
				                   </c:if>
				                    
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                    </td>
                </tr>
                <tr>
                    <td class="th">工厂图片</td>
                    <td colspan="5">
                          <div class="pull-left">
                            <div class="text-center">工厂图片1</div>
                            <div class="col-sm-3">
                              <input type="file" name="imgFile" class="pUpdate" id="dImgOne_Up">
			                  <div id="dImgOnedivUp">
			                  	 <c:if test="${not empty shopDto.shopImage1}">
				                     <img id="dImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage1}'>
				                 </c:if>
				                 <c:if test="${empty shopDto.shopImage1}">
				                     <img id="dImgOneShowUp">
				                 </c:if>
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                          <div class="pull-left">
                            <div class="text-center">工厂图片2</div>
                            <div class="col-sm-3">
                              <input type="file" name="imgFile" class="pUpdate" id="dImgTwo_Up">
			                  <div id="dImgTwodivUp">
			                  	<c:if test="${not empty shopDto.shopImage2}">
				                    <img id="dImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage2}'>
				                 </c:if>
			                    <c:if test="${empty shopDto.shopImage2}">
				                    <img id="dImgTwoShowUp">
				                 </c:if>
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                          <div class="pull-left">
                            <div class="text-center">工厂图片3</div>
                            <div class="col-sm-3">
                              <input type="file" class="pUpdate" name="imgFile" id="dImgThree_Up">
			                  <div id="dImgThreedivUp">
			                  	<c:if test="${not empty shopDto.shopImage3}">
				                   <img id="dImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage3}'>
				                 </c:if>
			                    <c:if test="${empty shopDto.shopImage3}">
				                   <img id="dImgThreeShowUp" >
				                 </c:if>
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                    </td>
                </tr>
                <tr>
                  <td class="sr-only">按钮组</td>
                  <td>
                    <button type="button" onclick="updateSuppliers();" class="btn btn-primary btn-sm">确定</button>
                    <a href="suppliers"  class="btn btn-primary btn-sm">取消</a>
                  </td>
                </tr>
            </table>
            
            
          </form>
    			</div>
    		</div>
    	</div>
    </section>
  </div>
</div>
  
<script>

</script>

</body>
</html>
</tiles:put>
</tiles:insert>