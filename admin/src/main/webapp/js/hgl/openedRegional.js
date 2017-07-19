$(document).ready(function() {
		//默认选中第一个管理员
		$("[class='adminUser_0 clearfix']").css('background', '#aaa');
		var roleId = $("#adminRole_0").val();
		$("#adminRoleId").val(roleId);
		//没有管理员返回false
		if (!roleId) {
			return false;
		}
		roleTreeShow(roleId);

	});

	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : zTreeOnCheck
		}
	};

	function zTreeOnCheck(event, treeId, treeNode) {
		// 	    alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		//var parentNodes =treeObj.getNodes();
		//var childNode =treeObj.transformToArray(parentNodes);
		addRolePer(nodes);
	};

	function addRolePer(nodes) {
		var proviceId = $("#adminRoleId").val();
		var version = $("#version").val();
		var node = "";
		var parentNode = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			var level =nodes[i].level;
			if(level==0){
				parentNode += nodes[i].id;
				if (i != l - 1) {
					parentNode = parentNode + ",";
				}
			}
			if(level==1){
				node += nodes[i].id;
				if (i != l - 1) {
					node = node + ",";
				}
			}
		}
		
//		for(var j=0,k=parentNodes.length;j<k;j++){
//			var checked =parentNodes[j].checkedOld;
//			if(checked){
//				parentNode += parentNodes[j].id;
//				if (j != k - 1) {
//					parentNode = parentNode + ",";
//				}
//			}
//		}
		
		$.ajax({
			type : 'GET',
			url : 'addProvice_ajax?openCountry=' + node + '&proviceId=' + proviceId
					+ "&version=" + version+'&openCity='+parentNode,
			dataType : 'json',
			success : function(jsonList) {
				$("#version").val(jsonList[0]);
				addTable(jsonList[1]);
			}
		});
	}

	function roleTreeShow(roleId) {
		$.ajax({
			type : 'GET',
			url : 'cityTreeShow_ajax?id=' + roleId,
			dataType : 'json',
			success : function(jsonList) {
				$("#version").val(jsonList[0]);
				$.fn.zTree.init($("#treeDemo"), setting, jsonList[1]);
				addTable(jsonList[2]);
			}
		});
	};

	function selectRole(id, obj) {
		$("#adminRoleId").val(id);
		//取消全部class的样式
		$("li[class^=adminUser_]").css("background-color", "");
		//添加样式
		$(obj).css("background-color", "#aaa");
		roleTreeShow(id);
	};
	
	function addTable(cityLowestPriceList){
		var tableStr = "";
		for (var i = 0; i < cityLowestPriceList.length; i++) {
	        	var name =cityLowestPriceList[i].name;
	        	var lowestPrice =cityLowestPriceList[i].lowestPrice;
	        	var id =cityLowestPriceList[i].id;
	        	var version =cityLowestPriceList[i].version;
	        	tableStr+="<tr><td class='text-center'>"
								+ name + "</td><td class='text-center'>"
								+ "<input class='form-control' id='"+id+"' name='"+version
								+"' value='"+lowestPrice+"' onchange=\"alterPrice($(this))\"></td></tr>";
/*	        	+"' value='"+lowestPrice+"' onchange=\"alterPrice('"+ cityLowestPriceList[i]+"')\"></td></tr>";
*/	        	
			}
	        $("#cityLowestPriceList").html(tableStr);
	}
	
	function alterPrice(obj){
		 var cityid= obj.attr("id");
		 var version= obj.attr("name");
		 var lowestPrice =obj.val();
		 $.ajax({
			 type:'post',
			 url:ctx+'/openedRegional/alterPrice_ajax',
			 data:{"cityid":cityid,"version":version,"lowestPrice":lowestPrice},
			 success:function(data){
				 obj.attr("name",data);
			 },
			 error:function(){
				 alert("错误");
			 }
		 });
	}

