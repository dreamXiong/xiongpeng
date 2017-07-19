function firstProductType(id,name){
	$("#mainId").val(id);
	$("#mainName").val(name);
	 $.ajax({
         type:"POST",
         url : ctx+"/brand/linkageMainPage",
         data:{
             id:id
         },
         dataType:"json",
         success:function(response){
        	 $("#secondForAjax").children("li").not(":first").remove(); 
        	 var len = response.secondList.length;
        	 if(len > 0){
        		 $("#secondForAjax").children("li").not(":first").remove(); 
             	for(var i=0; i<len; i++){
             		
             	var clearfix = 'clearfix';	
             	if(i==0){
             		clearfix = clearfix+' cur';
             	}
             	$("#secondForAjax").append( "<li class='"+clearfix+"' onclick='scoendProductType("+response.secondList[i].id+","+'"'+response.secondList[i].name+'"'+");'>"
             									
             										+"<a>"+response.secondList[i].name +"</a>"
             									
             									+"</li>");
             	}
             	$("#parentId").val(response.secondList[0].id);
             	$("#parentName").val(response.secondList[0].name);
             	$("#thirdForAjax").show();
             }
        	 $("#thirdForAjax").children("li").not(":first").remove();
        	  if(len == 0){
        		  $("#thirdForAjax").hide();
        		  return;
        	  }
          	var tLen = response.thirdList.length;
          	if(tLen > 0){
          		 $("#thirdForAjax").show();
          		for(var i=0; i<tLen; i++){
	          		$("#thirdForAjax").append( "<li class='clearfix'>"
	          								
	          										+"<a>"+response.thirdList[i].name+"</a>"
	          									
	          									+"</li>");
              	}
          	}
          	
         }
     });
}

function scoendProductType(id,name){
	$("#parentId").val(id);
	$("#parentName").val(name);
	 $.ajax({
        type:"POST",
        url :ctx+"/brand/linkageMainPageSecond",
        data:{
            id:id
        },
        dataType:"json",
        success:function(response){
        	 $("#thirdForAjax").children("li").not(":first").remove();
           	var tLen = response.thirdList.length;
           	if(tLen > 0){
           		 $("#thirdForAjax").show();
           		for(var i=0; i<tLen; i++){
           		$("#thirdForAjax").append( "<li class='clearfix'>"
	           									+"<a>"+response.thirdList[i].name+"</a>"
           									+"</li>");
               	}
           		}
        	}
	 });
}

function addSecond(){
	 $("#firstName").val($("#mainName").val());
	 $("#firstId").val($("#mainId").val());
	$( "#dialog" ).dialog('open');
}

$(function() {  
	/*$('.add-title').on('click',function(){
		 $("#firstName").val($("#mainName").val());
		 $("#firstId").val($("#mainId").val());
		$( "#dialog" ).dialog('open'); 
	});*/
	
	$( "#dialog" ).dialog({            //弹出层初始化
	   title:'添加二级菜单' ,                  //弹出层的标题
	   autoOpen: false ,                       //禁止自己弹出
	   resizable: false,                       //禁止弹出层缩放
	   draggable :false,                       //禁止拖动
	   modal: true,                            //是否有模态框
	//  height:?
	   width:'500',                            //设置宽高
	   close:function(){
		   moveText();  
	   },
	   closeText:'关闭',                       //closetitle
	   buttons:{                               //创建btn
	     关闭:function(){
	       $("#errorText").text("");//btn的回调函数
	       $(this).dialog("close");
	       moveText();
	     },
	     确定:function(){
	    	 submitAddSecondProductType();
	      
	     }
	   }
	}
	);

}); 

var addValidateType=false;
function addValidateProductType(){
	var name = $("#name").val();
	if(name==null || ''==name.trim()){
		$("#name").addClass("errorStyleDiv");
		return;
		}
	$.ajax({
        type: "POST",
        url: ctx+"/brand/addValidateProductType",
        data: "name="+name,
        success: function(response){
            if(response.code == '0'){
            	$("#name").removeClass("errorStyleDiv");
            	$("#errorText").text("");
            	addValidateType = true;
            }else{
            	$("#name").addClass("errorStyleDiv");
            	$("#errorText").text("此大类下此分类已存在!");
            	addValidateType = false;
            }
        }
    });
}

function submitAddSecondProductType(){
	var name = $("#name").val();
	if(name==null || name.trim()==''){
		$("#name").addClass("errorStyleDiv");
		$("#errorText").text("分类名称不能为空!");
		return;
		}
	
	if(!addValidateType){
		$("#name").addClass("errorStyleDiv");
		$("#errorText").text("此类下此分类名称已存在!");
		return;
	}
  	  var params = {
		  firstId:$("#firstId").val(),
		  name:$("#name").val(),
	      describes:$("#describes").val(),
  	  };
	  $.ajax({
	        type : "POST",
	        url : ctx+'/brand/addSecondProductType',
	       // url : ctx+'/brand/addSecondProductType',
	        data : params,
	        success : function(response) {
	        	location.reload() ;
	        	 $("#dialog").dialog('close');  
	        	
	        }
	    }); 
	
}

function addthird(){
	 $("#firstName1").val($("#mainName").val());
	 $("#secondName1").val($("#parentName").val());
	 $("#firstId1").val($("#mainId").val());
	 $("#secondId").val($("#parentId").val());
	$( "#dialog1" ).dialog('open');
}

$(function() {  
	/*$('.add-title').on('click',function(){
		 $("#firstName").val($("#mainName").val());
		 $("#firstId").val($("#mainId").val());
		$( "#dialog" ).dialog('open'); 
	});*/
	
	$( "#dialog1" ).dialog({            //弹出层初始化
	   title:'添加三级菜单' ,                  //弹出层的标题
	   autoOpen: false ,                       //禁止自己弹出
	   resizable: false,                       //禁止弹出层缩放
	   draggable :false,                       //禁止拖动
	   modal: true,                            //是否有模态框
	//  height:?
	   width:'500',                            //设置宽高
	   close:function(){
		   moveText();
	   },
	   closeText:'关闭',                       //closetitle
	   buttons:{                               //创建btn
	     关闭:function(){                      //btn的回调函数
	       $(this).dialog("close");
	       moveText();
	     },
	     确定:function(){
	    	 submitAddThirdProductType();
	      
	     }
	   }
	}
	);

}); 

function submitAddThirdProductType(){
	var name = $("#thirdName").val();
	if(name==null || name.trim()==''){
		$("#thirdName").addClass("errorStyleDiv");
		$("#errorText1").text("分类名称不能为空!");
		return;
		}
	
	if(!addValidateType){
		$("#thirdName").addClass("errorStyleDiv");
		$("#errorText1").text("此分类名称已存在!");
		return;
	}
  	  var params = {
		  firstId:$("#firstId1").val(),
		  secondId:$("#secondId").val(),
		  name:$("#thirdName").val(),
	      describes:$("#describes1").val(),
  	  };
	  $.ajax({
	        type : "POST",
	        url : ctx+'/brand/addThirdProductType',
	        data : params,
	   /*     url : ctx+'/brand/addThirdProductType?name='+$("#thirdName").val()+'&firstId='+$("#firstId1").val()+'&secondId='+$("#secondId").val()
	        +'&describes='+$("#describes1").val(),*/
	        success : function(response) {
	        	location.reload() ;
	        	 $("#dialog1").dialog('close');  
	        	
	        }
	    }); 
	
}

function addValidateThirdProductType(){
	var name = $("#thirdName").val();
	if(name==null || ''==name.trim()){
		$("#thirdName").addClass("errorStyleDiv");
		return;
		}
	$.ajax({
        type: "POST",
        url: ctx+"/brand/addValidateProductType",
        data: "name="+name,
        success: function(response){
            if(response.code == '0'){
            	$("#thirdName").removeClass("errorStyleDiv");
            	$("#errorText1").text("");
            	addValidateType = true;
            }else{
            	$("#thirdName").addClass("errorStyleDiv");
            	$("#errorText1").text("此大类下此分类已存在!");
            	addValidateType = false;
            }
        }
    });
}
function moveText(){
	$('#errorText').text('');
	$('#errorText1').text('');
}