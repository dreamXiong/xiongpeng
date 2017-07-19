<%@page pageEncoding="UTF-8"%>
 <form action="" id="saveProductInfo" method="post">
 <input type="hidden" name="pimgOne" id="Uinput_val" value="${pifd.pimgOne }"/>
 <input type="hidden" name="pimgTwo" id="Uinput1_val" value="${pifd.pimgTwo }"/>
 <input type="hidden" name="pimgThree" id="Uinput2_val" value="${pifd.pimgThree }"/>
 <input type="hidden" name="dimgOne" id="Uinput3_val" value="${pifd.dimgOne }"/>
 <input type="hidden" name="dimgTwo" id="Uinput4_val" value="${pifd.dimgTwo }"/>
 <input type="hidden" name="dimgThree" id="Uinput5_val" value="${pifd.dimgThree }"/>
 <input type="hidden" name="attributes" id="attributes"/>
 <input type="hidden" name="id" value="${pifd.id}" id="updateId"/>
 <input type="hidden" name="version" id="version" value="${pifd.version}"/>
<div class="clear">
		 <label>分类</label>
         <input type="text" value='${pifd.mainType}' disabled="disabled" style="width: 101px;">
		 <c:if test="${pifd.mainType == pifd.parentType }">
          	<input type="text" value='${pifd.thirdType}' disabled="disabled" style="width: 101px;">
         	<input type="text" value='' disabled="disabled" style="width: 101px;">
         </c:if>
         <c:if test="${pifd.mainType != pifd.parentType }">
          	<input type="text" value='${pifd.parentType}' disabled="disabled" style="width: 101px;">
         	<input type="text" value='${pifd.thirdType}' disabled="disabled" style="width: 101px;">
         </c:if> 
	</div>
	<div class="clear">
		<label>品牌</label>
		<input type="text" value='${pifd.brandname}' disabled="disabled">
	</div>
	<div class="clear">
		<label>名称</label>  
		<input type="text" value='${pifd.name}' name="name" id="name">
	</div>     
	<div class="clear">
		<label>价格</label>
		<input type="text" value='${pifd.price}' name="price"  id="price">
	</div>
	<div class="clear">
		<label>计量单位</label>
		<input type="text" value="${pifd.meterageUnit }" name="meterageUnit" id="meterageunit">
	</div>
	<div class="clear">
		<label>产品图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	 <div id="pImgOnedivUp">
                  <c:if test="${not empty pifd.pimgOne}">
                    <img id="pImgOneShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.pimgOne}'/>
                   </c:if>
                   <c:if test="${empty pifd.pimgOne}">
                    <img id="pImgOneShowUp"/>
                   </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="pImgTwodivUp">
	                  <c:if test="${not empty pifd.pimgTwo}">
	                     <img id="pImgTwoShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.pimgTwo}'>
	                   </c:if>
                  	 <c:if test="${empty pifd.pimgTwo}">
	                     <img id="pImgTwoShowUp">
	                 </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	<div id="pImgThreedivUp">
                   <c:if test="${not empty pifd.pimgThree}">
	                     <img id="pImgThreeShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.pimgThree}'>
	                 </c:if>
                    <c:if test="${empty pifd.pimgThree}">
	                     <img id="pImgThreeShowUp" >
	                 </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
        </div>
	</div>
	<div class="clear des-img">
		<label>产品描述图片</label>
		<div class="pull-left add-img">
            <div class="img-list">
            	 <div id="dImgOnedivUp">
                  	 <c:if test="${not empty pifd.dimgOne}">
	                     <img id="dImgOneShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.dimgOne}'>
	                 </c:if>
	                 <c:if test="${empty pifd.dimgOne}">
	                     <img id="dImgOneShowUp">
	                 </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	 <div id="dImgTwodivUp">
                  	<c:if test="${not empty pifd.dimgTwo}">
	                    <img id="dImgTwoShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.dimgTwo}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgTwo}">
	                    <img id="dImgTwoShowUp">
	                 </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
            <div class="img-list">
            	  <div id="dImgThreedivUp">
                  	<c:if test="${not empty pifd.dimgThree}">
	                   <img id="dImgThreeShowUp" src='${ctx}/albumspace/generateAlbumspaceImage?id=${pifd.id}&imageNameNow=${pifd.dimgThree}'>
	                 </c:if>
                    <c:if test="${empty pifd.dimgThree}">
	                   <img id="dImgThreeShowUp" >
	                 </c:if>
                  </div>
            	<span class="icon-plus"></span>
            </div>
        </div>
	</div>
	<div class="clear">
		<label>描述</label>
		<textarea rows="3" name="describes" id="describes" maxlength="500">${pifd.describes}</textarea>
	</div>
	<div class="clear">
		<label>附加属性</label>
		<div class="pull-left inp-short clear">
		
		<c:if test="${sign == false}">
              <c:forEach var="item" items="${attr}" varStatus="s">
                <c:if test="${not empty item}">
               <div id="addMydiv">
				<input type="text" value="${item}" name="attrInput">
			</div>
               </c:if>
              </c:forEach>
             </c:if>
             
              <c:if test="${sign == true}">
              <c:forEach var="item" items="${attr}" varStatus="s">
              <c:if test="${not empty  item}">
               <div id="addMydiv">
				<input type="text" value="${item}" name="attrInput">
				<span class="inp-close">&times;</span>
			</div>
          </c:if>
          </c:forEach>
         </c:if>
             
			<c:if test="${sign == true}">
            		<input type="button" class="btn" value="添加..." id="addInput">
            </c:if>
		</div>
	</div>
</form>
<script>
	$(function(){
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
			     确定:function(){
			    	 var imgSrc = $('#photo .cur').find('img').attr('src');
			    	 var imgName= $('#photo .cur').find('span').text();
			    	 var endWith= $('#photo .cur').find('span').attr('data-endWith');
			    	 imgName=trimStr(imgName);
			    	 imgName =imgName+ "."+endWith;
			    	 var arr = ['#pImgOneShowUp','#pImgTwoShowUp','#pImgThreeShowUp','#dImgOneShowUp','#dImgTwoShowUp','#dImgThreeShowUp'];
			    	 if(ind==0){
			    		 $(arr[0]).attr('src',imgSrc);
			    		 $("#Uinput_val").val(imgName);
			    	 }
			    	 else{
			    		 var imgId = arr[ind];
			    		 $(imgId).attr('src',imgSrc);
			    		 
			    		 var inputVal_id = "#Uinput"+ind+"_val";
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
		$('#photo').delegate('li','dblclick',function(){
			 var imgSrc = $(this).find('img').attr('src');
	    	 var imgName= $(this).find('span').text();
	    	 var endWith= $(this).find('span').attr('data-endWith');
	    	 imgName=trimStr(imgName);
	    	 imgName =imgName+ "."+endWith;
	    	 var arr = ['#pImgOneShowUp','#pImgTwoShowUp','#pImgThreeShowUp','#dImgOneShowUp','#dImgTwoShowUp','#dImgThreeShowUp'];
	    	 if(ind==0){
	    		 $(arr[0]).attr('src',imgSrc);
	    		 $("#Uinput_val").val(imgName);
	    	 }
	    	 else{
	    		 var imgId = arr[ind];
	    		 $(imgId).attr('src',imgSrc);
	    		 
	    		 var inputVal_id = "#Uinput"+ind+"_val";
	    		 $(inputVal_id).val(imgName);
	    	 }
	    	$('#dialog5').dialog('close');
		});
		$('#photo').delegate('li','click',function(){
			$(this).addClass('cur').siblings('li').removeClass('cur');
		});
		
		function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
		
	});
</script>