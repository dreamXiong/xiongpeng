<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类管理" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>三级分类修改</title>
	<%@include file="productTypeStyle.jsp"%>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>添加大类</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			 <div class="box-body">
            <div>
              <a href="index" class="btn btn-primary">返回列表</a>
            </div>
    			 <form id="subUpdateThirdProductType" method="post" action="subUpdateThirdProductType"  class="form-horizontal">
              <div class="form-group">
              <input type="hidden" name="id" value="${id}" />
                <label  class="col-sm-2 control-label">产品大类</label>
                <div class="col-sm-8">
                 <select id="mainProductTypeId" onchange="linkageProductType();" name="mainId" class="form-control">
                 	<c:forEach var="item" items="${pLists}" varStatus="s">
	                    <option
	                    	<c:if test="${item.id == tbProductType.mainId}">
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
                <select id="language_tm2009" name="parentId" class="form-control">
                 	 <c:forEach var="it" items="${secondList}" varStatus="s"> 
                 	 	<option
                 	 		<c:if test="${it.id == tbProductType.parentId}">
	                    		selected='selected'
	                    	</c:if>
                 	 	 value="${it.id }" >${it.name }</option> 
	                 </c:forEach>  
                  </select> 
                </div> 
              </div>
             <div class="form-group">
                <label  class="col-sm-2 control-label">三级分类</label>
                <div class="col-sm-8">
                 <input id="productType01" maxlength="30" name="name" type="text" value="${tbProductType.name }" class="form-control">
                 <font id="errorText" style="color: red;" ></font>  
                </div>
              </div> 
              <div class="form-group">
                <label  class="col-sm-2 control-label">分类描述</label>
                <div class="col-sm-8">
                  <textarea id="productType02" maxlength="100" name="describes" class="form-control" rows="3">${tbProductType.describes }</textarea>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button onclick="subUpdateThirdProductType('${tbProductType.id}');" type="button" class="btn btn-primary">确定</button>
                </div>
                <div class="col-sm-6 text-left">
                  <button onclick="goBackIndex();" type="button" class="btn btn-default">取消</button>
                </div>
              </div>
             </form>
    		</div>
    	</div>
    </div>
    </section>
  </div>
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