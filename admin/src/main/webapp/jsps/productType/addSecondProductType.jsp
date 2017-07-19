<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类添加" />
	<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>分类添加</title>
	<%@include file="productTypeStyle.jsp"%>
</head>
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        	我的主页
        <small>添加二级类</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			 <div class="box-body">
            <div>
              <a href="index" class="btn btn-primary">返回列表</a>
            </div>
    			 <form id="addSecondProductType" method="post" action="addSecondProductType"  class="form-horizontal">
    			 
    			 <input type="hidden" value="${tbProductType.id}" name="mainId">
              <div class="form-group">
                <label  class="col-sm-2 control-label">产品大类</label>
                <div class="col-sm-8">
                 	<input  class="form-control" type="text" readonly="readonly"  value="${tbProductType.name}" />
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">二级分类</label>
                <div class="col-sm-8">
                  <input id="productType01" name="name" maxlength="30" onchange="addValidateProductType();" onfocus="validateProductTypesRepeat();" type="text" class="form-control" />
                    <font id="errorText" style="color: red;" ></font>  
                </div> 
              </div>
            
              <div class="form-group">
                <label  class="col-sm-2 control-label">分类描述</label>
                <div class="col-sm-8">
                  <textarea id="productType02" maxlength="500" name="describes" class="form-control" rows="3"></textarea>
                </div>
              </div>
              <!-- submitAddSecondProductType -->
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button onclick="submitAddSecondProductType();" type="button" class="btn btn-primary">确定</button>
                </div>
                <div class="col-sm-6 text-left">
                  <button  onclick="goBackIndex();" type="button" class="btn btn-default">取消</button>
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
      $(this).datetimepicker('show')
    });
    
  });
</script>
</body>
</html>
</tiles:put>
</tiles:insert>