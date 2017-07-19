<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>年会节目投票结果</title>

<script type="text/javascript" src="${ctx }/js/vote/jQuery.js"></script>
<script  type="text/javascript" src="${ctx }/js/vote/jqplot/jquery.jqplot.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/js/vote/jqplot/jquery.jqplot.min.css" />
<script type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script> 
 <script  type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script> 
 <script type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
 <script  type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
 <script type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.barRenderer.min.js"></script>
 

<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/examples/syntaxhighlighter/scripts/shCore.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/examples/syntaxhighlighter/scripts/shBrushJScript.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/examples/syntaxhighlighter/scripts/shBrushXml.min.js"></script> 
<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.logAxisRenderer.min.js"></script> 



<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script> 


<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.pointLabels.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>


<link type="text/css" rel="stylesheet" href="${ctx }/js/vote/jqplot/examples/syntaxhighlighter/styles/shCoreDefault.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/js/vote/jqplot/examples/syntaxhighlighter/styles/shThemejqPlot.min.css" />


<script language="JavaScript" type="text/javascript" src="${ctx }/js/vote/m_jqplot.js"></script>
<script  type="text/javascript" src="${ctx }/js/vote/vote.js"></script>


</head>
<body>

	<div id="chart1"></div>
	<div style="float:right;position:absolute;width:600px;height:640px;left:1160px;top:50px;">
		<div id="alBut" style="width:100px;margin-left:100px;padding:20px;text-align:center;background-color:#5FAB78;cursor:pointer;color:#fff;" onclick="drawPraise();">
			投票数统计
		</div>
		
		<div id="voteText" style="width:600px;height:500px;border:1px solid #5FAB78;border-radius:5px;margin-top:50px;font-size:20px;font-family:微软雅黑;padding:10px 10px;color:red;">
			
		</div>
	</div>
	


<div style="text-align:center;clear:both;">
</div>

	<div id="chart2" style="width:800px;height:800px;margin-left:300px;"></div>
<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript">
	//发送post请求获取投票数据
	loadVote();
	/* var data = [["快诺迅汇大合唱",100],["联创矿冶小品",100],["北京欢迎您",100],["小苹果",100],["手指舞",100],["双节棍",100]];
	var data_max = 999; //Y轴最大刻度
	var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
	var y_label = "投票数"; //Y轴标题
	var x_label = "节目列表"; //X轴标题
	var x = [1]; //定义X轴刻度值
	var title = "年会节目投票情况"; //统计图标标题
	j.jqplot.diagram.lineExtend("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2); */
	
	
	function step2(){
		var data = [[200],[200],[200],[120],[200],[200]];
		var data_max = 999; //Y轴最大刻度
		var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
		var y_label = "投票数"; //Y轴标题
		var x_label = "节目列表"; //X轴标题
		var x = [1]; //定义X轴刻度值
		var title = "年会节目投票情况"; //统计图标标题
		//j.jqplot.diagram.base("chart1", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 1);
		j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
	
	}
	
	
	function step3(){
		var data = [[250],[250],[200],[120],[250],[250]];
		var data_max = 999; //Y轴最大刻度
		var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
		var y_label = "投票数"; //Y轴标题
		var x_label = "节目列表"; //X轴标题
		var x = [1]; //定义X轴刻度值
		var title = "年会节目投票情况"; //统计图标标题
		//j.jqplot.diagram.base("chart1", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 1);
		j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
	
	}
	
	function step4(){
		var data = [[350],[400],[200],[120],[400],[400]];
		var data_max = 999; //Y轴最大刻度
		var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
		var y_label = "投票数"; //Y轴标题
		var x_label = "节目列表"; //X轴标题
		var x = [1]; //定义X轴刻度值
		var title = "年会节目投票情况"; //统计图标标题
		//j.jqplot.diagram.base("chart1", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 1);
		j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
	
	}
	
	
	function step5(){
		var data = [[350],[420],[200],[120],[500],[500]];
		var data_max = 999; //Y轴最大刻度
		var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
		var y_label = "投票数"; //Y轴标题
		var x_label = "节目列表"; //X轴标题
		var x = [1]; //定义X轴刻度值
		var title = "年会节目投票情况"; //统计图标标题
		//j.jqplot.diagram.base("chart1", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 1);
		j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
	
	}
	
	
	
	function step6(){
		var data = [[350],[420],[200],[120],[540],[600]];
		var data_max = 999; //Y轴最大刻度
		var line_title = ["快诺迅汇大合唱","联创矿冶小品","北京欢迎您","小苹果","手指舞","双节棍"]; //曲线名称
		var y_label = "投票数"; //Y轴标题
		var x_label = "节目列表"; //X轴标题
		var x = [1]; //定义X轴刻度值
		var title = "年会节目投票情况"; //统计图标标题
		//j.jqplot.diagram.base("chart1", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 1);
		j.jqplot.diagram.base("chart2", data, line_title, "年会节目投票情况", x, x_label, y_label, data_max, 2);
	
	}

</script>
</body>
</html>

