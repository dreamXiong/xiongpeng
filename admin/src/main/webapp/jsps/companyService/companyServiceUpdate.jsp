<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="经销商详情修改" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>服务公司详情修改</title>
  	<%-- <link rel="stylesheet" href="${ctx}/css/vender.css"> --%>
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/companyServiceUpdate.js"></script>
	<script src="${ctx}/js/hgl/district.js"></script>
	<style>
		.text-left{
			text-align:left!important;
		}
		.select1{
			width:25%;
			float: left;
		}
		.table .col-sm-3{
			margin-right:30px;
			height:188px;
			width:188px;
			border:1px solid #eee;
		}
		.table .col-sm-3 input[type="file"]{
			opacity:0;
			position:absolute;
			top:0;
			left:0;
			width:100%;
			height:100%;
			cursor: pointer;
			z-index:10;
		}
		.table .col-sm-3 img,#myModal .col-sm-3 div{
			width:100%;
			height:100%;
			position: absolute;
			top:0;
			left:0;
			z-index:9;
		}
		.table .col-sm-3 span{
			color:#ccc;
			line-height:180px;
			width:180px;
			text-align:center;
			position: absolute;
			top:0;
			left:0;
			font-size:30px;
			z-index:8;
		}
	</style>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
<input id="msgPosition" value="false" type="hidden"/>

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页<small>服务公司详情修改</small>
      </h1>              
    </section>
   <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
          <form id="updateDealers" action="saveUpdateInfo" class="form-horizontal" method="post">
           <input type="hidden" name="companyImage1" id="pImgOne_Up_Show"  value="${pifd.companyImage1}"/>
           <input type="hidden" name="companyImage2" id="pImgTwo_Up_Show" value="${pifd.companyImage2}"/>
           <input type="hidden" name="companyImage3" id="pImgThree_Up_Show" value="${pifd.companyImage3}"/>
         
           <input type="hidden" id="provinceCode" value="${pifd.regProvince}"/>
           <input type="hidden" id="cityCode" value="${pifd.regCity}"/>
           <input type="hidden" id="countryCode" value="${pifd.regCountry}"/>
           <input type="hidden" id="streetCode" value="${pifd.regStreet}"/>
               	
            <input type="hidden"  id="id" name="id" value="${pifd.id}"/>
             <input type="hidden"  id="version" name="version" value="${pifd.version}"/>    	    	
            <table class="table">
            	 <tr>
                   	<th>公司名称</th>
                    <td>
                    	<input class="form-control inputNotNull"  id="companyName" name="companyName" value="${pifd.companyName }" type="text"  maxlength="30" />
                    </td>
                    <th>服务类型</th>
                    <td>
                    	<input value="${pifd.tName}" type="text" readonly="readonly" class="form-control inputNotNull" maxlength="30" />
                    	<input name="typeId" value="${pifd.typeId}" type="hidden" />
                    </td>
                   	<th>联系人</th>
                    <td><input  id="contract" name="contract" value='${pifd.contract}' type="text" class="form-control inputNotNull" maxlength="30" /></td>
                    <th>联系号码：</th>
                    <td>
                    	<input class="form-control inputNotNull"  id="contractPhone" name="contractPhone" value='${pifd.contractPhone}' type="text"  maxlength="13" />
                    </td>
                </tr>
                <tr>
                     <th>企业地址</th>   
                     <td colspan="5">
                    	<select id="province" name="regProvince" class="select1 form-control"  onchange="changeGrade();" value="${shopDto.regProvince}">
								 <option value="0">请选择</option>
	                          <c:forEach  var="item" items="${provinceInfos}" varStatus="status" >
	                         	
	                         	  <option
		                    	<c:if test="${item.id == pifd.regProvince}">
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
                   	<th>详细地扯</th>
                    <td>
                    	<input id="regAddress" class="form-control" name="regAddress" value="${pifd.regAddress}" type="text" maxlength="30">
                    </td>
                </tr>
                
                <tr>
                 <th>实景图片</th>
                    <td colspan="7">
                    	 <div class="pull-left">
                            <div class="col-sm-3">
                            <input type="file" name="imgFile" class="pUpdate" id="pImgOne_Up"/>
                                <div id="pImgOnedivUp">
				                  <c:if test="${not empty pifd.companyImage1}">
				                    <img id="pImgOneShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.companyImage1}'/>
				                   </c:if>
				                   <c:if test="${empty pifd.companyImage1}">
				                    <img id="pImgOneShowUp"/>
				                   </c:if>
				                    
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                          <div class="pull-left">
                            <div class="col-sm-3">
                             <input type="file" name="imgFile" class="pUpdate" id="pImgTwo_Up"/>
			                  <div id="pImgTwodivUp">
				                   <c:if test="${not empty pifd.companyImage2}">
				                     <img id="pImgTwoShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.companyImage2}'>
				                   </c:if>
				                  <c:if test="${empty pifd.companyImage2}">
					                  <img id="pImgTwoShowUp"/> 
					                </c:if>
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                        
                          <div class="pull-left">
                            <div class="col-sm-3">
                            	<input type="file" name="imgFile" class="pUpdate" id="pImgThree_Up"/>
                               <div id="pImgThreedivUp">
				                  <c:if test="${not empty pifd.companyImage3}">
				                  	<img id="pImgThreeShowUp" src='generateImage?id=${pifd.id}&imgName=${pifd.companyImage3}'>
				                   </c:if>
				                   <c:if test="${empty pifd.companyImage3}">
				                    <img id="pImgThreeShowUp"/>
				                   </c:if>
			                  </div>
                              <span class="glyphicon glyphicon-plus"></span>
                            </div>
                          </div>
                    </td>
                </tr>
                <tr>    
	                <th>公司简介</th>
	                <td colspan="7">
	                	<textarea class="form-control" rows="10" name="describes" style="resize:none;" maxlength="450">
	                		${pifd.describes }
	                	</textarea> 
	                </td>
	               
                </tr>
                <tr>
                  <td class="sr-only">按钮组</td>
                  <td colspan="7" class="text-left">         
                    <button type="button" onclick="updateDealers();" class="btn btn-primary btn-sm">确定</button>
                     <a href="index"  class="btn btn-primary btn-sm">取消</a>
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