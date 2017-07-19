/***
 *	作者：DevilJie 
 *	创建时间：2014-11-05
 *	个人网站：http://www.w3210.com
 **/
var j = {jqplot :{}};
j.jqplot.diagram = {
	/**
	 * document: 输出图形的位置id
	 * s：柱状图数据 例如：[[1,2,3,4]]单柱状图  [[1,2,3,4],[2,3,4,5]] 双柱状图 以此类推
	 * title：每一个柱状对应的名称 ["参加人数","中奖人数"]
	 * ticks:x轴显示数据例如[1,2,3,4,5,6,7,8]
	 * x_label:x轴名称
	 * y_label:y轴名称
	 * t: 1：曲线图 2：柱状图
	 */
	base : function(document, s, xtitle, title, ticks, x_label, y_label, max, t){
		//j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
		var renderer;
		if(t == 1) renderer = $.jqplot.BlockRenderer ;
		else if(t == 2) renderer = $.jqplot.BarRenderer ;
		
		
		renderer = $.jqplot.CanvasAxisTickRenderer;
        plot3 = $.jqplot(document, s, {
        	title: title,
			legend:{show:true,labels: xtitle},
			animate:true,
			seriesDefaults: {  
               renderer: renderer, // 利用渲染器（BarRenderer）渲染现有图表  
               pointLabels: { show: true } ,
               tickOptions:{
            	   angle:-30
               }
            },
			axes:{
				yaxis:{
            		label: y_label==null?"":y_label,
            		max:max
				},
				xaxis:{
					renderer: $.jqplot.CategoryAxisRenderer, // 设置横（纵）轴上数据加载的渲染器,
					ticks: ticks,
                	label: x_label==null?"":x_label
				}
			}, 
			series:[{color:'#5FAB78'}] 
        });
	},

	lineExtend : function(document, s, xtitle, title, ticks, x_label, y_label, max, t){
		//j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
		
		plot3 = $.jqplot(document, [s], {
			animate:true,
			seriesDefaults:{
				pointLabels:{show:true},
				shadow:false,
				showMarker:true,
				title: title,
				animate:true,
				renderer: $.jqplot.BarRenderer
			},
	        axesDefaults:{
	        	tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	        	tickOptions:{
	        		angle:-15,
	        		fontFamily:'Georgia',
	        		fontSize:'10pt',
	        		fontWeight:'bold'
	        	}
	        },
			axes:{
				yaxis:{
	        		label: y_label==null?"":y_label,
	        		show:true,
	        		/*showTicks:true,
	        		showTickMarks:true,
	        		autoscale:true,*/
	        		borderWidth:1,
	        		tickOptions:{
	        			show:true,
	        			/*showLabel:true,*/
	        			/*showMark:true,*/
	        			showGridline:true,
	        			formatString:'%d票'
	        		},
	        		max:max
				},
				xaxis:{
					renderer: $.jqplot.CategoryAxisRenderer, // 设置横（纵）轴上数据加载的渲染器,
					/*ticks: ticks,*/
	            	label: x_label==null?"":x_label
				}
			},
	    });
	},
	
	
	
	testExtend : function(document, s, xtitle, title, ticks, x_label, y_label, max, t){
		//j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
		var line1 = [['黄河大合唱',6],['小苹果舞曲',12],['鬼子来了，我们打鬼子',9],['山的那边',7],['那人，那狗，那小村庄',5]];
		var s1 = [12,34,34,22,45,65];
		$.jqplot(document, [s1], {
			seriesDefaults: {
			pointLabels: { show: true },
			shadow: false,
			showMarker: true, // 是否强调显示图中的数据节点
			renderer: $.jqplot.BarRenderer,
			rendererOptions: {
				barWidth: 50,
				barMargin: 50
				}
			},
			axes: {
				   xaxis: {
					  show: true,    //是否自动显示坐标轴
					  renderer: $.jqplot.CategoryAxisRenderer,
					  ticks: ticks,
					  showTicks: true,        // 是否显示刻度线以及坐标轴上的刻度值  
					  showTickMarks: true,    //设置是否显示刻度
					  tickOptions: {
						  show: true,
						  fontSize: '14px',
						  fontFamily: 'tahoma,arial,"Hiragino Sans GB",宋体b8b体,sans-serif',
						  showLabel: true, //是否显示刻度线以及坐标轴上的刻度值
						  showMark: false,//设置是否显示刻度
						  showGridline: false // 是否在图表区域显示刻度值方向的网格
					  }
				 },
				 yaxis: {
					 show: true,
					 showTicks: false,        // 是否显示刻度线以及坐标轴上的刻度值  
					 showTickMarks: false,     //设置是否显示刻度
					 autoscale: true,
					 borderWidth: 1,
					 tickOptions: {
						 show: true,
						 showLabel: false,
						 showMark: false,
						 showGridline: true,
						 formatString: '￥%.2f'
					 }
				},
			},
			grid: {
				drawGridLines: true,
				drawBorder: false,
				shadow: false,
				borderColor: '#000000',     // 设置图表的(最外侧)边框的颜色
				borderWidth: 1           //设置图表的（最外侧）边框宽度  
				},
				highlighter: { show: false }
		});
	}
	
	
};