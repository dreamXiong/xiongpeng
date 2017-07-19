<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类添加" />
	<tiles:put name="body" type="String">
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
        <small>添加大类</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			 <div class="box-body">
            <div>
              <a href="classify.html" class="btn btn-primary">返回列表</a>
            </div>
    			 <form action="#"  class="form-horizontal">
              <div class="form-group">
                <label  class="col-sm-2 control-label">产品大类</label>
                <div class="col-sm-10">
                 <select class="form-control">
                    <option value="手动工具">手动工具</option>
                    <option value="电动工具">电动工具</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">二级分类</label>
                <div class="col-sm-10">
                 <select class="form-control">
                    <option value="手动工具">手动工具</option>
                    <option value="电动工具">电动工具</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">三级分类</label>
                <div class="col-sm-10">
                 <input type="text" maxlength="30" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">分类描述</label>
                <div class="col-sm-10">
                  <textarea class="form-control" maxlength="500" rows="3"></textarea>
                </div>
              </div>
              
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button type="button" class="btn btn-primary">确定</button>
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
