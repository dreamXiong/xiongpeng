<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../block/block_taglibs.jsp" %>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="招商" />
	<tiles:put name="body" type="String">
<c:set value="merchantsDetails" var="modalName"></c:set>	
<head>
	<meta charset="UTF-8">
	<title>招商管理</title>
	<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
  <link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
 <script src="${ctx}/js/hgl/jquery-ui.min.js"></script> 
	<link rel="stylesheet" href="${ctx}/css/common.min.css">
	 <script src="${ctx}/js/hgl/agency.js"></script> 
</head>
<body>
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>招商详情</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    		<div class="box box-primary">
    			<div class="p8">
              <a href="merchants" class="btn btn-primary">返回列表</a>
         </div> 
        		<div class="fl p5">
               		 <c:if test="${not empty merchants.reserved1}">
	                 <label class="fw">   封面:  <img id="pImgOneShowUp" src='generateImage?id=${merchants.id}' style="width: 120px;height: 120px;"/>
	                 </label>
	                  </c:if>
              	 </div>
                <div class="fl p15">
                  <div>
                    <div class="p5">
                      <label class="fw">品牌: ${merchants.producttypeName} - ${merchants.brandName}</label>
                      
                    </div>
                  </div>
                  <div>
                    <div class="p5">
                      <label class="fw">招商类型:
                      <c:if test="${merchants.type ==1}">
                      	金额
                         
                      </c:if>
                      <c:if test="${merchants.type ==2}">
                    	  数量
                      </c:if>
                     </label>
                    </div>
                  </div>
                  <div>
                    <div class="p5">
                      <label class="fw">招商层级:${merchants.levelName}</label>
                     
                    </div>
                  </div>
                  <div>
                    <div class="p5">
                      <label class="fw">招商数量:${merchants.number}</label>
                     
                    </div>
                  </div>
                  
                  <div>
                    <div class="p5">
                      <label class="fw">优惠券:${merchants.coupons}</label>
                     
                    </div>
                  </div>
                   <div>
                    <div class="p5">
                      <label class="fw">招商区域:${merchants.addressName}</label>
                     
                    </div>
                  </div>
                  <div>
                    <div class="p5">
                      <label class="fw">其他:${merchants.other}</label>
                     
                    </div>
                  </div>
                  </div>
                   
                  <div class="c"/>
                </div>
              
    			
    		</div>
    	</div>

      <div class="col-md-12">
        <div class="box box-primary">
           <div class="box-body">
             <form onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm">
             <input type="hidden" id="id" name="id" value="${param.id}" />
              <div class="row">
                <div  class="col-sm-3">
                  <div class="form-group">
                    <label class="col-sm-4 control-label">名称</label>
                    <div class="col-sm-8">
                      <input id="shopName" name="shopName" type="text" class="form-control" placeholder="请输入查询名称">
                    </div>
                  </div>
                </div>
                <div  class="col-sm-3">
                  <div class="form-group">
                    <label class="col-sm-4 control-label">状态</label>
                    <div class="col-sm-8">
                      <select class="form-control" id="state" name="state">
                        <option value="">全部</option>
                        <option value="0">未生效</option>
                        <option value="1">生效</option>
                        <option value="2">暂时生效</option>
                        <option value="3">失效</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-sm-3 ">
                    
                     <button type="submit" class="btn btn-primary" onclick="submitform()">查询</button>
                </div>
                <div class="col-sm-3">
                 ${merchants.views}人次围观/${merchants.participate}人参与/${merchants.winning}人成功
                </div>
                </div>
                <div id="key_${modalName}_list" class="col-sm-12">
               			<%@include file="agencyList.jsp"  %>
              </div>
              </form>
           </div>
        </div>
      </div>
    </section>
  </div>
</div>
<div id="dialog" class="dialog">
	  <form id="key_${modalName}_frm">
	  <input id="aid" name="id" type="hidden">
	    <label for="name">审核</label>
	    	 <select id="state1" name="state" class="form-control">
                <option value="0">未生效</option>
                <option value="1">暂时生效</option>
                <option value="2">生效</option>
                <option value="3">失效</option>
              </select>
	  </form>
	</div>
	<script>
    	EcWeb.currentModalName = '${modalName}';
    	 /* 表单提交 */
    	 function submitform(){
    		  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
    		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
    		    var frm = $("#" + frmId);
    		    var params=frm.serialize();;
    			$.ajax({
    		        type : "POST",
    		        url : 'serachMerchantsDetails',
    		        data : params,
    		        success : function(response) {
    		            $("#" + dataDomId).html(response);
    		        }
    		    }); 
    	 }
    	</script>
</body>
</tiles:put>
</tiles:insert>