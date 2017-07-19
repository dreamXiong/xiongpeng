<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../block/block_taglibs.jsp" %>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="招商管理" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>招商管理</title>
	<%@include file="merchantsStyle.jsp"%>
	<script src="${ctx}/js/hgl/district.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
	

  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>招商添加</small>
      </h1>
    </section>
    <section>
    	<div class="col-md-12">
    
    		<div class="box box-primary">
    	<div class="p8">
              <a href="merchants" class="btn btn-primary">返回列表</a>
         </div>
    			 <div class="p10" style="padding-left: 35px;">
               <form action="addMerchants" class="form-horizontal" method="post">
               	 <input type="hidden" name="reserved1" id="reserved1_Path" />
                  <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">品牌：</div> 
                      <div class="fl">
                        <select id="brandid" name="brandid" class="input">
                        <c:forEach var="item" items="${brands}" varStatus="status">
                          <option value="${item.id}">${item.producttypeName} - ${item.name}</option>
                          </c:forEach>
                        </select>
                      </div>
                  </div>
                  
                   <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">招商类型：</div> 
                      <div class="fl">
                        <select id="type" name="type" class="input">
                          <option value="1">金额</option>
                          <option value="2">数量</option>
                        </select>
                      </div>
                  </div>
                  <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">招商购买量：</div> 
                      <div class="fl">
                        <input id="number" name="number" type="text" class="input" placeholder="大于0">
                      </div>
                      <div class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>
               	<div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">优惠券：</div> 
                      <div class="fl">
                        <input id="coupons" name="coupons" type="text" class="input" placeholder="大于0">
                      </div>
                      <div class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>
                 <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">招商层级：</div> 
                      <div class="fl">
                       <select id="level" name="level" class="input" onchange="levelChang();">
                          <!-- <option value="1">省</option> -->
                          <option value="2">市</option>
                          <option value="3">区县</option>
                          <option value="4">街道</option>
                        </select>
                      </div>
                  </div>
                 <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">其他：</div> 
                      <div class="fl">
                       <input id="other" name="other" type="text" class="input">
                      </div>
                       <div class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>
                   
                  <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label">招商区域：</div> 
                      <div class="fl">
                       <select id="province" name="province" class="select1" onchange="changeGrade();">
                       
                       	   <option value="0">请选择</option>
                          <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
                          <option value="${item.id}">${item.name}</option>
                          </c:forEach>
                        </select>
                      </div>
                      <div class="fl" style="margin-left: 10px;">
                       <select id="city" name="city" class="select1" onchange="changeCity();">
                          
                        </select>
                      </div>
                       <div class="fl">
                       <select id="country" name="country"  class="select1 ml10" style="display:none;" onchange="changeCountry();">
                          
                        </select>
                      </div>
                       <div class="fl">
                       <select id="street" name="street" class="select1 ml10" style="display:none;" >
                        </select>
                      </div>
                      <div class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>
                	
                    <div class="mt20 clearfix file" >
                  	<div class="fl w_120 input_label">封面：</div> 
                      <div class="col-sm-3">
                           <input type="file" name="imgFile" class="pImg" id="reserved1">
                            <div id="pImgdivUp">
				               <img id="pImgShowUp">
			                  </div>
                           <span class="glyphicon glyphicon-plus"></span>
                       </div>
                       <div class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>
               <div class="mt20 clearfix" >
                  	<div class="fl w_120 input_label"></div> 
                     	<div id="ismsg"></div>
                       <div id="vcodeMsg"  class="fl msg">
		                    <span class="msg_pic"></span>
		                    <span class="msg_content" title=""></span>
		             </div>
                  </div>   
              <div class="mt20 clearfix" >
               <div class="fl w_120 input_label"></div> 
                  <div class="fl">
                    <input type="submit" onclick="return confirmAction('确定保存么？', ck_data);" class="btn btn-primary btn-sm"
						value="保存" />

                  </div>
                  <div class="fl" style="margin-left: 20px;">
                    
                    <a href="merchants" class="btn btn-primary btn-sm">取消</a>
                  </div>
                </div>
             </form>
    			 </div>
    		</div>
    	</div>
			
            
                <script type="text/javascript">
              $(function(){
        		  new uploadPreview({ UpBtn: "reserved1", DivShow: "pImgdivUp", ImgShow: "pImgShowUp" });
        		});
           	$(function(){  
      			$(".pImg").fileupload({
      				url : ctx+'/supplier/validateImg',
      			    autoUpload : true,
      			    singleFileUploads : true,
      			    acceptFileTypes : /(\.|\/)(|gif|jpe?g|png|bmp)$/i,
      			    maxFileSize : 5097152,
      			    change: function (e, data) { 
      			        $.each(data.files, function (index, file) {
      			        });
      			    }
      			}).on(
      			        'fileuploaddone',
      			        function(e, data) {
      			        	 new uploadPreview({ UpBtn: "reserved1", DivShow: "pImgdivUp", ImgShow: "pImgShowUp" });
      			            if (data.result.code == '0'){
      			            	var imgName = data.result.imgNo.split("_");
      			            	$("#"+imgName[0]+"_Path").val(data.result.imgNo);
      			            }else{
      			            
      			            }
      			}).on('fileuploadsubmit', function (e, data) {
      				
      		        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
      		    });
      		}); 
            
                
                
                
                function levelChang(){
                	 var level = $("#level option:selected").val();
                	 if(level == 3){
                		// $("#country").removeAttr("style"); 
  						 $("#country").attr("style","display:block;");
  						 $("#street").attr("style","display:none;");
  						 country();
                	 }else if(level == 4){
                		 $("#country").attr("style","display:block;");
  						 $("#street").attr("style","display:block;");
  						//country();
  						street();
                	 }else if(level ==2){
                		 $("#country").attr("style","display:none;");
                		 $("#street").attr("style","display:none;");
                	 }
                	 
                }
                
                
                var ck_data = {'number':'','coupons':'','other':'','province':'','city':'','ismsg':'','reserved1':''};
                function number() {
                    var val = $('#number').val();
                    if(!is_numeric(val) || !is_two_scale(val)|| val <= 0){
                        ck_data["number"] = "招商购买量不能为空，请保留两位小数";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                function coupons() {
                    var val = $('#coupons').val();
                    if(!is_numeric(val) || !is_two_scale(val)||val <= 0){
                       ck_data["coupons"] = "优惠卷不能为空并为空，请保留两位小数";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                
                function other() {
                    var val = $('#other').val();
                    if(!check_length(val,0,100)){
                       ck_data["other"] = "字数不能超过100";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                
                function province() {
                    var val = $('#province').val();
                    if(!is_numeric(val) || val <= 0){
                       ck_data["coupons"] = "请选择招商区域";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                
                function city() {
                    var val = $('#city').val();
                    if(!is_numeric(val) || val <= 0){
                       ck_data["coupons"] = "请选择招商区域";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                
                function country() {
                    var val = $('#country').val();
                    if(!is_numeric(val) || val <= 0){
                       ck_data["country"] = "请选择招商区域";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                function reserved1() {
                    var val = $('#reserved1_Path').val();
                    if(val==""){
                       ck_data["reserved1"] = "请上传招商封面图";
                        return CK_FALSE;
                    } 
                    return CK_TRUE;
                };
                var isckMsg= true;
              
                
                function ckMsg(){
                	 var val = $('#brandid').val();
                	 var val1 = $('#city').val();
                	 var val2 = $('#country').val();
                	 var val3 = $('#street').val();
                	 if(val3!=null&&is_numeric(val3) && val3 >= 0){ 
                	   $.ajax({
	        		        type: "POST",
	        		        async: false ,
	        		        url:ctx+'/merchants/checkMerchants?brandId='+val+"&city="+val1+"&country="+val2+"&street="+val3+"&merchantsId=0",
	        		        success: function(response){
	        		           if(response==false){
	        		        	   isckMsg =false;
	        		           }else{    
	        		        	   isckMsg = true;
	        		           }
	        		        }
	        		    });
                	 }
                }
                
                function ismsg() {
                	ckMsg();
                	 if(isckMsg== false){
                		// f_ck_error($("#vcodeMsg"), "此品牌招商区域的招商已存在！！"); 
                		 ck_data["ismsg"] = "此品牌招商区域的招商已存在！！";
                          return CK_FALSE;
                      } 
                      return CK_TRUE;       
                  
               };
                
                </script>
          
       
    </section>
  </div>
</div>
</body>
</html>
</tiles:put>
</tiles:insert>