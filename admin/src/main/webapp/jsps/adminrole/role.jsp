<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="角色管理"/>
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script src="${ctx}/js/jquery/jquery-1.12.2.min.js"></script>
  <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
  <script src="${ctx}/js/dist/app.js"></script>
  <script src="${ctx}/js/dist/jquery.slimscroll.min.js"></script>
  <script>
  	$(function(){
  		$('.one').height($('.two').height());
  	})
  </script>
</head>
<body>
	<div class="content-wrapper">
    <section class="content-header">
      <h1>
        我的主页
        <small>角色权限管理</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
        <div class="box box-primary">
          <div class="box-body pad table-responsive">
              <div class="fil">
                <div>
                  <ul class="nav text-center one">
                    <li class="nav-title">
                      <a href="javascript:;" id="addRole">添加角色</a>
                    </li>
                    <li>
                      <a href="javascript:;">厂家</a>
                      <div class="pull-right">
                        <a href="javascript:;">修改</a>
                        <a href="javascript:;">删除</a>
                      </div>
                    </li>
                    <li>
                      <a href="javascript:;">买家财务</a>
                      <div class="pull-right">
                        <a href="javascript:;">修改</a>
                        <a href="javascript:;">删除</a>
                      </div>
                    </li>
                    <li>
                      <a href="javascript:;">王武</a>
                      <div class="pull-right">
                        <a href="javascript:;">修改</a>
                        <a href="javascript:;">删除</a>
                      </div>
                    </li>
                    
                    <ul class="nav text-center two">
                      <li class="nav-title" style="line-height:40px;">
                        菜单
                      </li>
                      <li class="clearfix">
                          <div class="pull-left">
                            <input type="checkbox">
                            <a href="javascript:;">用户菜单</a>
                          </div>
                          <div class="pull-right">
                            <a href="javascript:;">修改</a>
                            <a href="javascript:;">删除</a>
                          </div>
                       </li>
                      <li class="clearfix">
                        <div class="pull-left">
                          <input type="checkbox">
                          <a href="javascript:;">订单管理</a>
                        </div>
                        <div class="pull-right">
                          <a href="javascript:;">修改</a>
                          <a href="javascript:;">删除</a>
                        </div>
                      </li>
                      <li class="clearfix">
                        <div class="pull-left">
                          <input type="checkbox">
                          <a href="javascript:;">订单管理</a>
                        </div>
                        <div class="pull-right">
                          <a href="javascript:;">修改</a>
                          <a href="javascript:;">删除</a>
                        </div>
                      </li>
                      <li class="clearfix">
                        <div class="pull-left">
                          <input type="checkbox">
                          <a href="javascript:;">订单管理</a>
                        </div>
                        <div class="pull-right">
                          <a href="javascript:;">修改</a>
                          <a href="javascript:;">删除</a>
                        </div>
                      </li>
                     <li class="clearfix">
                        <div class="pull-left">
                          <input type="checkbox">
                          <a href="javascript:;">发布管理</a>
                        </div>
                        <div class="pull-right">
                          <a href="javascript:;">修改</a>
                          <a href="javascript:;">删除</a>
                        </div>
                      </li>
                      </ul>
                    </ul>
                  
                  
                </div>
              </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</div>



<!-- 添加角色模态框开始 -->
<div class="modal fade" id="modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加角色</h4>
      </div>
      <div class="modal-body">
        <form action="" class="form-horizontal">
          <div class="form-group">
            <label for="" class="col-sm-2">角色名称</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" placeholder="请输入角色名称">
            </div>
          </div>
          <div class="form-group">
            <label for="" class="col-sm-2">备注</label>
            <div class="col-sm-10">
              <textarea rows="3" class="form-control"></textarea>   
            </div>
          </div>
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="save" data-dismiss="modal">确定</button>
      </div> 
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- 添加角色模态框结束 -->
</body>
</html>
</tiles:put>
</tiles:insert>