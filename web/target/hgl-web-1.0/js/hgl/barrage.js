var count = 0;
var lastTop = 0;
var interval;
var showInterval;
var showTalkArray = new Array();//要展示的弹幕
var talkArray = new Array();//从后台取出的弹幕
var topArray = new Array();
var currentNumber = 0;//当前从所有弹幕数据中取数的下标
$(function(){
	getBarrages();
	interval = window.setInterval(getBarrages,2000);//5秒钟取一次talk数据，存入数组
	showInterval = window.setInterval(showBarrages,1000);//1秒钟取talk数据展示一条
});

//发送请求至后台获取弹幕数据
function getBarrages(){
	//发送请求道后台，获取弹幕列表
   	$.ajax({
        type : "GET",
        url : $("#ctx").val()+"/barrage/barrageList?"+new Date().getTime(),
        dataType:"json",
        success : function(response) {
        	var listSize = response.listSize;
        	if(listSize > 0){
        		var talkList = response.talkList;
        		$.each(talkList,function(index){
        			var data = talkList[index];
        			var talk = new Talk(data.id,data.message,false);
        	   		talkArray.push(talk);
        	   		showTalkArray.push(talk);
	            });
        		
        		if(listSize < 5){
        			//如果弹幕条数小于10，需要从已经展示过的弹幕数据中取，补全十条
        			var temp = 5 - listSize;
        			if(talkArray.length >0){
                		for(var j=currentNumber,n=0;n<temp;j++,n++){
                			var temTalk = talkArray[j];
                			if(null != temTalk){
                				
                				if(isExistInArray(showTalkArray,temTalk.getId()) == false){
                					var talk = new Talk(temTalk.getId(),temTalk.getMessage(),false);
                					showTalkArray.push(talk);
                					//alert(temTalk.getMessage());
                				}
                				
                			}
                			
                			if(j > talkArray.length-1){
                				j = 0;
        					}
                			currentNumber = j;
        					
        				}
            		}
        			
        			
        		}
        		
        		
        	}else{
        		//没有取到数据
        		if(talkArray.length >0){
        			var temp = 5;
            		for(var j=currentNumber,n=0;n<temp;j++,n++){
            			
            			var temTalk = talkArray[j];
            			if(null != temTalk){
            				if(isExistInArray(showTalkArray,temTalk.getId()) == false){
            					var talk = new Talk(temTalk.getId(),temTalk.getMessage(),false);
            					showTalkArray.push(talk);
            					//alert(temTalk.getMessage());
            				}
            			}
            			
            			if(j > talkArray.length-1){
            				j = 0;
    					}
            			currentNumber = j;
    					
    				}
        		}
        		
        	}
            
        }
   	,
   	error:function(XMLHttpRequest, textStatus, errorThrown) {
   	 	//取数据出现错误
   	//没有取到数据
		if(talkArray.length >0){
			var temp = 5;
    		for(var j=currentNumber,n=0;n<temp;j++,n++){
    			
    			var temTalk = talkArray[j];
    			if(null != temTalk){
    				if(isExistInArray(showTalkArray,temTalk.getId()) == false){
    					var talk = new Talk(temTalk.getId(),temTalk.getMessage(),false);
    					showTalkArray.push(talk);
    					//alert(temTalk.getMessage());
    				}
    			}
    			
    			if(j > talkArray.length-1){
    				j = 0;
				}
    			currentNumber = j;
				
			}
		}
   	 }
    });
   	
   	
   	//测试代码
   	
   	/* for(var i=0;i<10;i++){
   		var talk = new Talk(count,"这是测试弹幕"+count,false);
   		talkArray.push(talk);
   		count ++;
   	} */
	
	
	
}

//展示弹幕
function showBarrages(){
	if(showTalkArray.length>0){
		for(var i=0;i<showTalkArray.length;i++){
			var talk = showTalkArray[i];
			if(talk.getIsShow() == false){
				/*var top = 0;
				do{
					top=GetRandomNum(1,$(window).height()-100);
				}while((lastTop-top>0?(lastTop-top<=80):(top-lastTop<=80)));*/
				
				
				var top = calucateTop();
			   	var color = setColor();
			   	var wenzi = talk.getMessage();
				var div="<div talkId="+talk.getId()+" style='padding:10px;border:1px solid #FFF;border-radius:10px;-moz-border-radius:10px;-webkit-border-radius:10px;font-weight:bold;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;color:"+color+"; top:"+top+"px;'>"+wenzi+"</div>";
			   	$(".d_show").append(div);
			   	
			   	
			   	move_screen();
			   	
			   	//showTalkArray.deleteTalk(0);
			   	lastTop = top;
			   	
			   	talk.setIsShow(true);
			   	break;
			}
		}
		
		
	}
}

//控制弹幕移动
function move_screen(){
	var _top=0;
	$(".d_show").find("div").show().each(function(){
		var isMove = $(this).attr("isMove");
		if(isMove != "yes"){
			//循环遍历弹幕div，然后动画效果，从屏幕右侧移动到左侧，并隐藏
			var _left=$(window).width();
			var _height=$(window).height();

			_top=_top+76;
			if(_top>=_height-100){
				_top=0;
			}
			//$(this).css({left:_left,top:_top,color:getReandomColor()});
			$(this).css({left:_left+"px"});
			var time= 15000;
			$(this).animate({left:"-"+($(this).width()+100)+"px"},time,function(){
				$(this).remove();//动画结束后，删除节点
				var talkId = $(this).attr("talkId");
				showTalkArray.deleteTalkById(talkId);
			});
			
			$(this).attr("isMove","yes");
		}
		
		
	});
}

//随机获取颜色值
function getReandomColor(){
	return '#'+(function(h){
		return new Array(7-h.length).join("0")+h;
	})((Math.random()*0x1000000<<0).toString(16));
}
//获取min到max的随机值
function GetRandomNum(Min,Max)
{   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return(Min + Math.round(Rand * Range));   
}   

//颜色数组，弹幕颜色从这些中随机选取
var arr = ["#FFFFFF","#F00FF","#000000","#8B008B","#006400"]; 
function setColor(){
	var index = Math.floor((Math.random()*arr.length)); 
    return (arr[index]); 
}
           


//弹幕对象，弹幕id，弹幕内容，是否已显示
function Talk(id,message,isShow){
	this.id = id;
	this.message = message;
	this.isShow = isShow;
}

Talk.prototype.setIsShow = function(newIsShow){
	this.isShow = newIsShow;
}
Talk.prototype.getMessage = function(){
	return this.message;
}
Talk.prototype.getId = function(){
	return this.id;
}
Talk.prototype.getIsShow = function(){
	return this.isShow;
}
Array.prototype.deleteTalk = function(dx){
	if(isNaN(dx) || dx >this.length){
		return false;
	}
	
	for(var i=0,n=0;i<this.length;i++){
		if(this[i] != this[dx]){
			this[n++] = this[i];
		}
	}
	this.length-=1;
}

Array.prototype.deleteTalkById = function(id){
	var dx = -1;
	for(var j=0;j<this.length;j++){
		var talk = this[j];
		if(talk.getId() == id ){
			dx = j;
			break;
		}
	}
	
	if(dx !=-1){
		for(var i=0,n=0;i<this.length;i++){
			if(this[i] != this[dx]){
				this[n++] = this[i];
			}
		}
		this.length-=1;
	}
	
}

function isExistInArray(array,id){
	//alert("id is:"+id+ " length:"+array.length);
	var flag = false;
	for(var i=0;i<array.length;i++){
		//alert("array id is:----"+(array[i].getId() == id));
		if(array[i].getId() == id){
			flag = true;
			break;
		}
	}
	
	
	return flag;
}

function showInfo(){
	var all = $("#all");
	var show = $("#show");
	
	var allText = "";
	var showText = "";
	for(var i=0;i<talkArray.length;i++){
		allText += talkArray[i].getMessage();
		//alert(talkArray[i].getMessage());
	}
	for(var i=0;i<showTalkArray.length;i++){
		showText += (showTalkArray[i].getMessage());
	}
	
	//all.html(allText);
	show.html(showText);
}

//计算高度
function calucateTop(){
	var top = 0;
	do{
		top=GetRandomNum(50,$(window).height()-($(window).height()*0.3+100));
	}while(judgeTop(top)==false);
	
	return top;
}

function judgeTop(top){
	
	for(var i=0;i<topArray.length;i++){
		if((topArray[i]-top>0?(topArray[i]-top<=80):(top-topArray[i]<=80))==true){
			return false;
		}
	}
	
	
	if(topArray.length >=4){
		topArray.deleteTalk(0);
	}
	
	topArray.push(top);
}
