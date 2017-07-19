var count = 0;//统计次数
var max = 0;//图表的最大值
var praiseArray = new Array();//排序后的投票数
var voteData = new Array();//投票数据
var plot3 = null;
var program;
var programPraise;
var praiseCount = 6;
function loadVote(){
	$.ajax({
        type : "POST",
        url : $("#ctx").val()+"/weixin/voteTop?time="+new Date().getTime(),
        data:null,
        dataType:"json",
        success : function(response) {
        	var code = response.code;
        	if(code == "0000" || code == "0001"){
        		var data = response.data;
        		$.each(data,function(index){
        			var vote = [data[index].title,data[index].praise];
        			praiseArray.push(data[index].praise);
        			voteData.push(vote);
        		});
        		
        		
        		praiseArray.sort(function(a,b){return a>b?1:-1});
        		max = praiseArray[praiseArray.length-1] +100;
        		showDiagram(generateArray(count,voteData,praiseArray),max);
        		
        		count ++;
        		
        	}else{
        		alert("操作失败，请稍后或者刷新后再试！");
        	}
        }
   	,
   	error:function(XMLHttpRequest, textStatus, errorThrown) {
   	 	//取数据出现错误
   		alert("操作失败，请稍后或者刷新后再试！");
   	 }
    });
	
	
	
}


function showDiagram(voteData,max){
	var data_max = max; //Y轴最大刻度
	var line_title = "年会节目投票情况"; //曲线名称
	var y_label = "投票数"; //Y轴标题
	var x_label = "节目列表"; //X轴标题
	var x = [1]; //定义X轴刻度值
	var title = "年会节目投票情况"; //统计图标标题
	j.jqplot.diagram.lineExtend("chart2", voteData, line_title, title, x, x_label, y_label, data_max, 2);
}


function drawPraise(){
	if(count >= 6){
		alert("投票数统计已完成！");
		return;
	}
	var show = generateArray(count,voteData,praiseArray);
	/*plot3.replot({
		resetAxes:true
	});*/
	showDiagram(show,max);
	if(count < voteData.length-1){
		var text = "节目“"+program +"”的投票数为："+programPraise+"票，恭喜获得第"+(praiseCount-count+1)+"名！<br/>";
		$("#voteText").append(text);
	}else{
		var text = "节目“"+program +"”的投票数为："+programPraise+"票，恭喜获得第"+(praiseCount-count+1)+"名！<br/>";
		$("#voteText").append(text);
		
		
		var currentPraise = praiseArray[praiseArray.length-1];
		for(var j=0;j<voteData.length;j++){
			if(voteData[j][1] == currentPraise){
				
				var lastText = "节目“"+voteData[j][0] +"”的投票数为："+voteData[j][1]+"票，恭喜获得第1名！<br/>";
				$("#voteText").append(lastText);
				break;
			}
		}
		
		
		
	}
	
	count ++;
}

//获取要展示的数据
//count第几次统计，dataArray投票原始数据，praiseArray排序后的投票数据
function generateArray(count,dataArray,praiseArray){
	var showArray = new Array();
	if(count == 0){
		var inintCount = 0;
		for(var i=0;i<dataArray.length;i++){
			var vote = [dataArray[i][0],inintCount];
			showArray.push(vote);
		}
	}else if(count == dataArray.length-1){
		//倒数第二次统计，第一名和第二名同时统计出
		var currentPraise = praiseArray[count-1];
		var tempPraise = 0;
		tempPraise = (praiseArray[dataArray.length-1]);
		debugger;
		for(var j=0;j<dataArray.length;j++){
			if(dataArray[j][1] > currentPraise){
				var vote = [dataArray[j][0],tempPraise];
				showArray.push(vote);
			}else if(dataArray[j][1] == currentPraise){
				program = dataArray[j][0];
				programPraise  = dataArray[j][1];
				var vote = [dataArray[j][0],dataArray[j][1]];
				showArray.push(vote);
			}else{
				var vote = [dataArray[j][0],dataArray[j][1]];
				showArray.push(vote);
			}
		}
	}else{
		var currentPraise = praiseArray[count-1];
		var tempPraise = 0;
		if(count != dataArray.length){
			tempPraise= praiseArray[count-1] + (praiseArray[count]-praiseArray[count-1])/2;
		}else{
			tempPraise = (praiseArray[count-1]);
		}
		debugger;
		for(var j=0;j<dataArray.length;j++){
			if(dataArray[j][1] > currentPraise){
				if(count == dataArray.length){
					var vote = [dataArray[j][0],tempPraise];
					showArray.push(vote);
				}else{
					var vote = [dataArray[j][0],tempPraise];
					showArray.push(vote);
				}
				
			}else if(dataArray[j][1] == currentPraise){
				program = dataArray[j][0];
				programPraise  = dataArray[j][1];
				var vote = [dataArray[j][0],dataArray[j][1]];
				showArray.push(vote);
			}else{
				var vote = [dataArray[j][0],dataArray[j][1]];
				showArray.push(vote);
			}
		}
		
	}
	//alert(showArray+" "+count);
	//alert(praiseArray);
	return showArray;
}