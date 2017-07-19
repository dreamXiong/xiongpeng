<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="分类管理" />
	<tiles:put name="body" type="String">
<html lang="en">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="UTF-8">
	<title>分类管理</title>  
	<%@include file="productTypeStyle.jsp"%>
</head>
<body class="skin-blue fixed">
<input type="hidden" value="${mainId}" name="mainId" id="mainId"/>
	<input type="hidden" value="${parentId}" name="parentId" id="parentId"/>

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>分类列表</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">         
    		<div class="box box-primary">
    			<div class="box-body pad table-responsive">
    				  <div class="fil">
                <div>
                  <ul class="nav text-center one" id="productType">
                    <li class="nav-title">
                      <a onclick="goProductTypeAddPage('addFirstProductType');" >添加一级分类</a>
                    </li>
                    <input type="hidden" id="deleteId" value=""/>
			  	<c:forEach var="item" items="${pList}" varStatus="s">
                	 <li onclick="firstProductType(${item.id});" class="clearfix">  
                	 <div class="pull-left">
                      	<a>${item.name}</a>
                      </div>
                     <div class="pull-right">
	                      <a onclick="updateFirstProductType(${item.id});">修改</a> 
	                      <a onclick="deleteFirstProductType(${item.id})">删除</a>
                      </div>
                    </li>
                </c:forEach>
                
                  <c:if test="${not empty pList}">
                    <ul id="secondForAjax" class="nav text-center two">
                      <li id="asfdsf" onclick="goProductTypeAddPage('addSecondProductType');" class="nav-title ">
                        <a >添加二级分类</a>
                      </li> 
                   	 </c:if>
	                <c:forEach var="sItem" items="${secondList}" varStatus="s">
	                	 <li onclick="scoendProductType(${sItem.id});" class="second_${s.index} clearfix" >
	                	 	<div class="pull-left">
		                      <a >${sItem.name}</a>
		                    </div>
		                    <div class="pull-right">
		                      <a onclick="updateSecondProductType(${sItem.id});">修改</a>
		                      <a onclick="deleteSecondProductType(${sItem.id})">删除</a>
		                    </div>
	                    </li>
	                </c:forEach> 
	                
	                
	                <c:if test="${not empty secondList}">
                      <ul id="thirdForAjax" class="nav text-center three">
                        <li class="nav-title" onclick="goAddThirdProductTypePage();">
                          <a >添加三级分类</a>
                        </li> 
                         <c:forEach var="tItem" items="${thirdList}" varStatus="s">
	                        <li  class="clearfix">
	                        <div class="pull-left">
	                          <a >${tItem.name }</a>
	                         </div>
	                         <div class="pull-right">
	                          <a onclick='updateThirdProductType(${tItem.id});'>修改</a>
	                          <a onclick="deleteThirdProductType(${tItem.id})" >删除</a>
	                         </div>
	                        </li>
                        </c:forEach>
                      </ul>
                       </c:if>
                    </ul>
                    
                  </ul>
                </div>
              </div>
    			</div>
    		</div>
    	</div>
    </section>
  </div>
<!-- 大类弹出框  -->
<div class="modal fade" id="deleteFirst">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body" >
        	你确定要删除该大类吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goDeleteProductType()'>确定</button> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="notDeleteFirst">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body" >
        	有相关数据暂不能删除！
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <!-- <button type="button" class="btn btn-primary btn-sm" onclick='goDeleteFirstProductType()'>确定</button>  -->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 二级类弹出框  -->
<div class="modal fade" id="deleteSecond">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要删除该二级类吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goDeleteProductType()'>确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 三级类弹出框  -->
<div class="modal fade" id="deleteThird">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><strong>删除</strong></h4>
      </div>
      <div class="modal-body">
        	你确定要删除该三级类吗？
      </div>
      <div class="modal-footer" style="text-align:center!important;" >
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary btn-sm" onclick='goDeleteProductType()'>确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
</body>
</html>
</tiles:put>
</tiles:insert>