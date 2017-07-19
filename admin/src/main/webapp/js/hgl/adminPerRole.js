$(document).ready(function() {
		init();
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
		addRolePer(nodes);
	};

	function addRolePer(nodes) {
		var roleId = $("#adminRoleId").val();
		var version = $("#version").val();
		var node = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			node += nodes[i].id;
			if (i != l - 1) {
				node = node + ",";
			}
		}
		$.ajax({
			type : 'GET',
			url : 'addRolePer_ajax?permissionIds=' + node + '&id=' + roleId
					+ "&version=" + version,
			dataType : 'json',
			success : function(jsonList) {
				$("#version").val(jsonList[0]);
			}
		});
	}

	function roleTreeShow(roleId) {
		$.ajax({
			type : 'GET',
			url : 'roleTreeShow_ajax?id=' + roleId,
			dataType : 'json',
			success : function(jsonList) {
				$("#version").val(jsonList[0]);
				$.fn.zTree.init($("#treeDemo"), setting, jsonList[1]);
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

	$(document).delegate('#addRole','click',function(event) {
		$('#modal').find('input').val('');
	   $('#modal').find('textarea').val('');
		$('#modal').modal('show');
	});
	
	function init(){
		//默认选中第一个管理员
		$("[class='adminUser_0 clearfix']").css('background', '#aaa');
		var roleId = $("#adminRole_0").val();
		$("#adminRoleId").val(roleId);
		//没有管理员返回false
		if (!roleId) {
			return false;
		}
		roleTreeShow(roleId);
	}
	