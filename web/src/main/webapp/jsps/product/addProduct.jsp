<%@page pageEncoding="UTF-8"%>
 <form action="" id="saveProductInfo" method="post">
 <input type="hidden" name="pimgOne" id="input_val" />
 <input type="hidden" name="pimgTwo" id="input1_val"/>
 <input type="hidden" name="pimgThree" id="input2_val"/>
 <input type="hidden" name="dimgOne" id="input3_val"/>
 <input type="hidden" name="dimgTwo" id="input4_val"/>
 <input type="hidden" name="dimgThree" id="input5_val"/>
 <input type="hidden" name="attributes" id="attributes"/>
 <input type="hidden" name="id" value="" id="updateId"/>
<div class="clear">
		<label>分类</label>
		 <select onchange="selectParent('mainIdForAddPage');" id="mainIdForAddPage" name="mainId" style="width: 113px;">
         <option value='0'>--请选择--</option>
         <c:forEach var="item" items="${mLists}" varStatus="s">
           <option value="${item.id}">${item.name}</option>
          </c:forEach>
         </select>
		 <select id="parentType" name="productTypeId" onchange="selectThirdType('parentType');" style="width: 113px;">
         	<option value='0'>--请选择--</option>
         </select>
		 <select name="thirdType" id="thirdType" style="width: 113px;">
          <option value='0'>--请选择--</option>
         </select>
	</div>
	<div class="clear">
		<label>品牌</label>
		<select name="brandId" id="tbBrandList" style="width: 237px;">
            <option value='0'>--请选择--</option>
         </select>
        <span> <a type="button" class="btn" href="${ctx}/brand/addBrand" style="margin-left:5px;line-height: 52px;background-color: #66b6ff;color:#fff;">添加自有品牌</a></span>
	</div>
	<div class="clear">
		<label>名称</label>  
		<input type="text"  name="name" id="name">
	</div>
	<div class="clear">
		<label>价格</label>
		<input type="text" name="price"  id="price">
	</div>
	<div class="clear">
		<label>计量单位</label>
		<input type="text" name="meterageUnit" id="meterageunit">
	</div>
	<div class="clear">
		<label>产品图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	<div id="imgdiv">
            	  <img id="imgShow">
            	</div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="imgdiv1">
            	  <img id="imgShow1">
            	</div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="imgdiv2">
            	  <img id="imgShow2">
            	</div>
            	<span class="icon-plus"></span>
            </div>
        </div>
	</div>
	<div class="clear des-img">
		<label>产品描述图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	<div id="imgdiv3">
            	  <img id="imgShow3">
            	</div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="imgdiv4">
            	  <img id="imgShow4">
            	</div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="imgdiv5">
            	  <img id="imgShow5">
            	</div>
            	<span class="icon-plus"></span>
            </div>
        </div>
	</div>
	<div class="clear">
		<label>描述</label>
		<textarea rows="3" name="describes" id="describes" maxlength="500"></textarea>
	</div>
	<div class="clear">
		<label>附加属性</label>
		<div class="pull-left inp-short clear">
			<input type="button" class="btn" value="添加..." id="addInput">
		</div>
	</div>
</form>
<script>
	$(function(){
		var ind = 0;
		$('.inp-short').delegate('.inp-close','click',function(){
	  		$(this).parent('div').remove();
		});
		
		$('.inp-short').delegate('#addInput','click',function(){
		
		  var div= $('<div id="addMydiv">' 
		               +' <input type="text" name="attrInput">'
		                +'<span class="inp-close">&times;</span>'
		              +'</div>');
		  if($('.inp-short div').length<6){
		    $(this).before(div);
		  }   
		}); 
		var winH = $(window).height()-80;      
		var winW = $(window).width()-80;
		$( "#dialog5" ).dialog({            //弹出层初始化                           
			   title:'个人相册' ,                  //弹出层的标题
			   autoOpen: false ,                       //禁止自己弹出
			   resizable: false,                       //禁止弹出层缩放
			   draggable :false,                       //禁止拖动
			   modal: false,                            //是否有模态框
			   width:winW, 
			   height:winH,
			   closeText:'关闭',                       //closetitle
			   buttons:{                               //创建btn
			     关闭:function(){
			       $(this).dialog("close");
			     },
			     确定:function (){
			    	 var imgSrc = $('#photo .cur').find('img').attr('src');
			    	 var imgName= $('#photo .cur').find('span').text();
			    	 var endWith= $('#photo .cur').find('span').attr('data-endWith');
			    	 imgName=trimStr(imgName);
			    	 imgName =imgName + "."+endWith;
			    	 if(ind==0){
			    		 $("#imgShow").attr('src',imgSrc);
			    		 $("#input_val").val(imgName);
			    	 }
			    	 else{
			    		 var imgId = "#imgShow"+ind;
			    		 $(imgId).attr('src',imgSrc);
			    		 
			    		 var inputVal_id = "#input"+ind+"_val";
			    		 $(inputVal_id).val(imgName);
			    	 }
			    	 
			    	 $(this).dialog("close");
			     }
			   }
			});
		// 给div添加自定义属性
		$('.img-list').each(function(i){
			$(this).attr('count',i);
		});
		/* $(document).delegate('#photo li','click',function(){
			alert();
		}); */
		$('.img-list').click(function(){
			ind = $(this).attr('count');
			 $.ajax({
				type:'POST',     
				url:ctx+'/albumspace/getMyAlbumspaceListToPro',
				success:function(data){
					$("#photo").html(data);
				}
			}); 
			$('#dialog5').dialog('open');
			
		});
		$('#photo').delegate('li','click',function(){
			$(this).addClass('cur').siblings('li').removeClass('cur');
		});
		$('#photo').delegate('li','dblclick',function(){
			var imgSrc = $('#photo .cur').find('img').attr('src');
	    	 var imgName= $('#photo .cur').find('span').text();
	    	 var endWith= $('#photo .cur').find('span').attr('data-endWith');
	    	 imgName=trimStr(imgName);
	    	 imgName =imgName + "."+endWith;
	    	 if(ind==0){
	    		 $("#imgShow").attr('src',imgSrc);
	    		 $("#input_val").val(imgName);
	    	 }
	    	 else{
	    		 var imgId = "#imgShow"+ind;
	    		 $(imgId).attr('src',imgSrc);
	    		 
	    		 var inputVal_id = "#input"+ind+"_val";
	    		 $(inputVal_id).val(imgName);
	    	 }
	    	$('#dialog5').dialog('close');
	    	//alert(img_src+' '+imgName+' '+endWith);
			//$(this).addClass('cur').siblings('li').removeClass('cur');
		});
		
		function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}  
	});
</script>