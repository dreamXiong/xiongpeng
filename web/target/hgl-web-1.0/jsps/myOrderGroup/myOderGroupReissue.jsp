<%@page pageEncoding="UTF-8"%>
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	 <script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<form>
	  <input type="hidden" name="uinputOne" id="uinputOne_Path"/>
       <input type="hidden" name="uinputTwo" id="uinputTwo_Path"/>
       <input type="hidden" name="uinputThree" id="uinputThree_Path"/>
       <input type="hidden" name="uinputFour" id="uinputFour_Path"/>
    <div class="clear">
      <label>漏发订单描述：<span class="msg" style="color: red;"></label>
      <textarea rows="5" style="width:482px;" id="reossiueDesciption"></textarea>
    </div>
    
    <div class="clear">
    <label>产品图片(*请至少上传一张图片)<span class="msg1" style="color: red;"></span></label>
    <div class="pull-left add-img">
    
            <div class="img-list">
              <input type="file" id="uinputOne" class="pImg" name="imgFile">
               <div id="pImgdivUpOne">
                  <img id="pImgShowUpOne" src="">
                </div>
              <span class="icon-plus"></span>
            </div>
            <div class="img-list">
              <input type="file" id="uinputTwo" class="pImg" name="imgFile">
              <div id="pImgdivUpTwo">
                   <img id="pImgShowUpTwo" src="">
              </div>
              <span class="icon-plus"></span>
            </div>
            <div class="img-list">
              <input type="file" id="uinputThree" class="pImg" name="imgFile">
              <div id="pImgdivUpThree">
                       <img id="pImgShowUpThree">
                  </div>
              <span class="icon-plus"></span>
            </div>
            <div class="img-list">
              <input type="file" id="uinputFour" class="pImg" name="imgFile">
              <div id="pImgdivUpFour">
                       <img id="pImgShowUpFour">
                  </div>
              <span class="icon-plus"></span>
            </div>
        </div>
  </div>
    
  </form>
  <script>
  $(function(){
	  new uploadPreview({ UpBtn: "uinputOne", DivShow: "pImgdivUpOne", ImgShow: "pImgShowUpOne"});
	  new uploadPreview({ UpBtn: "uinputTwo", DivShow: "pImgdivUpTwo", ImgShow: "pImgShowUpTwo"});
	  new uploadPreview({ UpBtn: "uinputThree", DivShow: "pImgdivUpThree", ImgShow: "pImgShowUpThree"});
	  new uploadPreview({ UpBtn: "uinputFour", DivShow: "pImgdivUpFour", ImgShow: "pImgShowUpFour"});
	});
$(function(){  
		$(".pImg").fileupload({
		    url : ctx+'/register/validateImg',
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
		        	
		        	new uploadPreview({ UpBtn: "uinputOne", DivShow: "pImgdivUpOne", ImgShow: "pImgShowUpOne"});
		      	  new uploadPreview({ UpBtn: "uinputTwo", DivShow: "pImgdivUpTwo", ImgShow: "pImgShowUpTwo"});
		      	  new uploadPreview({ UpBtn: "uinputThree", DivShow: "pImgdivUpThree", ImgShow: "pImgShowUpThree"});
		      	  new uploadPreview({ UpBtn: "uinputFour", DivShow: "pImgdivUpFour", ImgShow: "pImgShowUpFour"});
		            if (data.result.code == '0'){
		            	var imgName = data.result.imgNo.split("_");
		            	$("#"+imgName[0]+"_Path").val(data.result.imgNo);
		            }else{
		            
		            }
		}).on('fileuploadsubmit', function (e, data) {
			
	        data.formData = { imgNo: $(this).attr("id") };  //如果需要额外添加参数可以在这里添加
	    });
	}); 
  </script>