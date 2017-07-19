function addFavorite2() {
    var url = window.location;
    var title = document.title;
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("360se") > -1) {
        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
    }
    else if (ua.indexOf("msie 8") > -1) {
        window.external.AddToFavoritesBar(url, title); //IE8
    }
    else if (document.all) {
  try{
   window.external.addFavorite(url, title);
  }catch(e){
   alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
  }
    }
    else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    }
    else {
  alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
    }
}
$(function() {	

	$( ".suggest" ).dialog({            //弹出层初始化
    	title:'提出您宝贵的意见' ,  //弹出层的标题
    	autoOpen: false ,//禁止自己弹出
    	resizable: false,//禁止弹出层缩放
    	draggable :false,//禁止拖动
    	modal: true,//是否有模态框
    	width:'400',     //设置宽高
    	closeText:'关闭',//closetitle
    	buttons:{         //创建btn
    	  关闭:function(){          //btn的回调函数
    	    $(this).dialog("close");
    	  },
    	  确定:function(){
    	    $(this).dialog("close");   
    	    show();
    	  }
    	}
	});
	 $( ".suggest-ensure" ).dialog({
	 	title:'提交成功' ,
    	autoOpen: false ,//禁止自己弹出
    	resizable: false,//禁止弹出层缩放
    	draggable :false,//禁止拖动
    	width:'340',
    	closeText:'关闭',
    	buttons:{
    		关闭:function(){
    			$(this).dialog('close');
    		}
    	}
    });
});
function show(){
	$('.suggest-ensure').dialog('open');
	setTimeout(function(){
		$('.suggest-ensure').dialog('close');
	}, 2000);
}