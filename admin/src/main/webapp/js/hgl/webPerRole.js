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

	$('#addRole').click(function(event) {
		$('#modal').modal('show');
	});

	$('#save').click(function(event) {
		EcWeb.currentModalName = '${modalName}';
		var inp = $('.modal-body').find('input');
		var name = inp.val();
		var txt = $('.modal-body').find('textarea');
		var remark = txt.val();
		var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		$.ajax({
			type : 'GET',
			url : 'addRole_ajax?name=' + name + '&remark=' + remark,
			success : function(response) {
				$("#" + dataDomId).html(response);
			}
		});
	});