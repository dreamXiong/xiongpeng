<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="厂家详情" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	
  <style>
    .table img{
      width:200px;
      height:140px;
      margin-right:20px;
    }
    .table h4{
      margin:0;
      line-height:2em;
    }
  </style>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
	

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>厂家详情</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
           <table class="table table-bordered">
              <tr>
                <th colspan="6">入驻厂家详细信息</th>
              </tr>
              <tr>
                <th>企业名称</th>
                <td>${shopDto.companyName}</td>
                <th>经营品类</th>
                <td>${shopDto.scope}</td>
               
                 <th>审核状态</th>
                <td>${shopDto.stateName}</td>
              </tr>
              <tr>
                <th>品牌名称</th>
                <td>${shopDto.brandName}</td>
                <th>经营类别</th>
                <td>${shopDto.productType}</td>
                <th>企业法人</th>
                <td>${shopDto.legalRepresentative}</td>
              </tr>
              <tr>
                <th>电话号码</th>
                <td>${shopDto.companyTel}</td>
                <th>手机号码</th>
                <td>${shopDto.mobile}</td>
                <th>管理用户名</th>
                <td>
                  ${shopDto.userName}
                </td>
              </tr>
              <tr>
               
               	<th>企业地址</th>
                <td colspan="5">
                ${shopDto.address}-${shopDto.regAddress }
                </td>
              </tr>
              
              <tr>
                <th>企业资质</th>
                <td colspan="5">
                 
                  <div class="pull-left text-center">
                    <h4>营业执照</h4>
                     <c:if test="${not empty shopDto.licenseImage}">
	                    <img id="pImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.licenseImage}'/>
	                   </c:if>
                  </div>
                   <div class="pull-left text-center">
                    <h4>一般纳税人</h4>
                     <c:if test="${not empty shopDto.taxpayerImage}">
				                    <img id="pImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.taxpayerImage}'/>
				                   </c:if>
                  </div>
                  <div class="pull-left text-center">
                    <h4>组织机构</h4>
                     <c:if test="${not empty shopDto.organizationImage}">
				                    <img id="pImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.organizationImage}'/>
				                   </c:if>
                  </div>
                </td>
              </tr>
              <tr>
                <th>工厂照片</th>
                <td colspan = "5">
                  <div class="pull-left text-center">
                    <c:if test="${not empty shopDto.shopImage1}">
				                     <img id="dImgOneShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage1}'>
				                 </c:if>
                  </div>
                  <div class="pull-left text-center">
                   <c:if test="${not empty shopDto.shopImage2}">
				                    <img id="dImgTwoShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage2}'>
				                 </c:if>
                  </div>
                  <div class="pull-left text-center">
                   	<c:if test="${not empty shopDto.shopImage3}">
				                   <img id="dImgThreeShowUp" src='generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage3}'>
				                 </c:if>
                  </div>
                </td>
                
              </tr>
              <tr>
              <td></td>
              	<td colspan = "5">
              	
              		<a href="suppliers"  class="btn btn-primary btn-sm">返回</a>
              	</td>
              </tr>
           </table>
    			</div>
    		</div>
    	</div>
    </section>
  </div>
</div>


</body>
</html>
</tiles:put>
</tiles:insert>