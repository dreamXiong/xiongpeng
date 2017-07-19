/*!
 *	wap端手机分页
 */

var page=1;  
	var finished=0;  
	var sover=0;  
	var divH =  $('.container').outerHeight(true); 
	$('.prolist').css('paddingTop',divH);
	//如果屏幕未到整屏自动加载下一页补满  
	var setdefult=setInterval(function (){  
	    if(sover==1) { 
	        clearInterval(setdefult);
	    }else if($(".prolist").height()<$(window).height()-divH){  
	        loadmore($(window));  
	    }else{
	    	clearInterval(setdefult); 
	    }  
	},500);  
	  
	//加载完  
	function loadover(){  
	    if(sover==1)  
	    {   
	        var overtext="Duang～到底了";  
	        $(".loadmore").remove();  
	        if($(".loadover").length>0)  
	        {  
	            $(".loadover span").eq(0).html(overtext);  
	        }  
	        else  
	        {  
	            var txt='<div class="loadover"><span>'+overtext+'</span></div>' ;
	            $(".prolist").after(txt);  
	        }  
	    }  
	}  
	//加载更多  
	var vid=0;  
	function loadmore(obj){  
	    if(finished==0 && sover==0){    
	        var scrollTop = $(obj).scrollTop();  
	        var scrollHeight = $(document).height();  
	        var windowHeight = $(obj).height();  
	          
	        if($(".loadmore").length==0)  
	        {  
	        	 if($("#pageCount").val() > 1){
	            	var txt='<div class="loadmore"><span class="loading"></span>加载中..</div>';
	        	 }
	            $(".prolist").after(txt);  
	        }  
	          
	        if (scrollTop + windowHeight -scrollHeight<=50 ) {  
	            //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作  
	            //防止未加载完再次执行  
	            finished=1;  
	            var result = "";  
	            if($("#pageCount").val() > 1){
	            	  selectInfo(page);
	            }
	            setTimeout(function(){  
	                page+=1;  
	                finished=0;  
	                //最后一页  
	                if(page==$("#pageCount").val())  
	                {  
	                    sover=1;  
	                    loadover();  
	                }  
	            },1000); 
	        }  
	    }  
	}  
	var bodyT = $('body').scrollTop(); 
	
	$(window).scroll(function(event){
	    var newT = $('body').scrollTop();
	    if(newT > bodyT){
	        loadmore($(this));
	    };
	    bodyT = newT;
	}) ;
	