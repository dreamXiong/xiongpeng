<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类管理" />
	<tiles:put name="body" type="String">
<html lang="en">
<style>
	.errorStyle{color: red; }
	.form-control.errorStyleDiv{border:solid 1px red;} 
</style>
<head>
	<meta charset="UTF-8">
	<title>二级分类修改</title>
		<script src="${ctx}/js/hgl/serviceType.js"></script> 
</head>
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        		我的主页
        <small>修改服务类别</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			 <div class="box-body">
            <div>
              <a href="index" class="btn btn-primary">返回列表</a>
            </div>
    			 <form id="updateSecondServiceType" method="post" action="subUpdateSecondServiceType"  class="form-horizontal">
    			  <!--二级分类修改时所用到的Id  -->
    			 <input type="hidden" name="id" id="secondId" value="${productType.id}" />
    			 <input type="hidden" name="version" value="${productType.version}" />
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">产品大类</label>
		                <div class="col-sm-8">
		                 <select name="parentId" class="form-control">
		                 	<c:forEach var="item" items="${firstProductTypeList}" varStatus="s">
			                    <option
			                    	<c:if test="${item.id == productType.parentId}">
			                    		selected='selected'
			                    	</c:if>
			                     value="${item.id}">${item.name}</option> 
		                    </c:forEach>
		                  </select>
		                </div>
		              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">二级分类</label>  
                <div class="col-sm-8">
                  <input id="productType01" maxlength="30" name="name" value="${productType.name }" onchange="updateValidateProductType('${productType.id}');" onfocus="validateProductTypesRepeat();" type="text" class="form-control" />
                    <font id="errorText" style="color: red;" ></font>  
                </div> 
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">分类描述</label>
                <div class="col-sm-8">
                  <textarea maxlength="100" id="productType02" name="describes" class="form-control" rows="3">${productType.describes}</textarea>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button onclick="submitUpdateSecondServiceType();" type="button" class="btn btn-primary">确定</button>
                </div>
                <div class="col-sm-6 text-left">
                  <button  onclick="goBackServiceIndex();" type="button" class="btn btn-default">取消</button>
                </div>
              </div>
             </form>
    		</div>
    	</div>
    </div>
    </section>
</div>
<script>
  $(function() {
    $('.times').click(function(){
      $(this).datetimepicker('show');
    });
    
  });
</script>
</body>
</html>
</tiles:put>
</tiles:insert>