<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="经销商详情" />
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
        <small>经销商详情</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
           <table class="table table-bordered">
              <tr>
                <th colspan="6">经销商详细信息</th>
              </tr>
              <tr>
                <th>店铺名称</th>
                <td>${shopDto.shopName}</td>
                <th>经营品类</th>
                <td>${shopDto.scope}</td>
                <th>企业地址</th>
                <td>${shopDto.address}
                	<c:if test="${shopDto.regAddress != ''}">
                		- ${shopDto.regAddress}
                	</c:if>
                </td>
              </tr>
              
              <tr>
                 <th>审核状态</th>
                <td>${shopDto.stateName}</td>
                <th>手机号码</th>
                <td>${shopDto.mobile}</td>
                <th>管理用户名</th>
                <td>
                  ${shopDto.userName}
                </td>
              </tr>
              <tr>
                <th>企业资质</th>
                <td colspan="5">
                 
                  <div class="pull-left text-center" style="width: 220px;">
                    <h4>营业执照</h4>
                     <c:if test="${not empty shopDto.licenseImage}">
	                    <img id="pImgOneShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.licenseImage}'/>
	                   </c:if>
	               
                  </div>
                   <div class="pull-left text-center" style="width: 220px;">
                    <h4>一般纳税人</h4>
                     <c:if test="${not empty shopDto.taxpayerImage}">
				                    <img id="pImgTwoShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.taxpayerImage}'/>
				                   </c:if>
                  </div>
                  <div class="pull-left text-center" style="width: 220px;">
                    <h4>组织机构</h4>
                     <c:if test="${not empty shopDto.organizationImage}">
				                    <img id="pImgThreeShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.organizationImage}'/>
				                   </c:if>
                  </div>
                </td>
              </tr>
              <tr>
                <th>工厂照片</th>
                <td colspan = "5">
                  <div class="pull-left text-center">
                    <c:if test="${not empty shopDto.shopImage1}">
				                     <img id="dImgOneShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage1}'>
				                 </c:if>
                  </div>
                  <div class="pull-left text-center">
                   <c:if test="${not empty shopDto.shopImage2}">
				                    <img id="dImgTwoShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage2}'>
				                 </c:if>
                  </div>
                  <div class="pull-left text-center">
                   	<c:if test="${not empty shopDto.shopImage3}">
				                   <img id="dImgThreeShowUp" src='${ctx}/supplier/generateImage?id=${shopDto.id}&imgName=${shopDto.shopImage3}'>
				                 </c:if>
                  </div>
                </td>
                
              </tr>
                <tr>
              <td></td>
              	<td colspan = "5">
              	
              		<a href="dealers"  class="btn btn-primary btn-sm">返回</a>
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