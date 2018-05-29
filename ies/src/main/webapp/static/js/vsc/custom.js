
//控制树展开或关闭
function controlMenuTree(id,flag){
	var tree = $.fn.zTree.getZTreeObj(id);
    tree.expandAll(flag);
}

//生成选择树
function GenerateSelectZTree(zTreeId,nodes,who,selectId,callbackFunc){
	var zTreeIdn="select"+zTreeId+"Tree";
	//选择树
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: function onClick(e, treeId, treeNode) {
				$("#"+who+"Id").val(treeNode.id);	//
				$("#"+who+"Name").val(treeNode.name);
				$("#select"+zTreeId+"Content").fadeOut("fast");
				if(typeof(callbackFunc)!='undefined'){
					var o={};
					o.fn=callbackFunc;
					o.fn();	
				}
			}
		}
	};
	var zNodes =nodes;
	$.fn.zTree.init($("#"+zTreeIdn), setting, zNodes);
	if(selectId!=''){
		var zTree = $.fn.zTree.getZTreeObj(zTreeIdn);
		var node = zTree.getNodeByParam("id",selectId);
		zTree.selectNode(node);	
	}
	
}


//显示树菜单
function showSelectZTreeMenu(evt,contentId) {
	var cityOffset = $(evt).position();
	$("#select"+contentId+"Content").css({left:cityOffset.left + "px", top:cityOffset.top + $(evt).outerHeight() + "px"}).slideDown("fast");
	$("#content"+contentId).bind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "select"+contentId+"Content" || $(event.target).parents("#select"+contentId+"Content").length>0)) {
			hideSelectZTreeMenu(contentId);
		}
	});	//body绑定鼠标按下事件
}

//隐藏树菜单
function hideSelectZTreeMenu(contentId) {
	$("#select"+contentId+"Content").fadeOut("fast");	//隐藏树菜单
	$("#content"+contentId).unbind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "select"+contentId+"Content" || $(event.target).parents("#select"+contentId+"Content").length>0)) {
			hideSelectZTreeMenu(contentId);
		}
	});	//body解除鼠标按下事件
}

//清空树
function clearSelectZTreeBtn(id){
	$("#"+id+"Id").val('');
	$("#"+id+"Name").val('');
}




//生成条件树
function GenerateSearcchZTree(nodes,who,searchParkingLockCode){
	//选择树
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "ps", "N" : "" }
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onCheck: function onClick(e, treeId, treeNode) {
				var treeObj = $.fn.zTree.getZTreeObj("searchTree"+who);
				var nodes = treeObj.getCheckedNodes(true);
				$("#searchCode"+who).val('');
				for(var i=0;i<nodes.length;i++){
					var pCodes=$("#searchCode"+who).val()+nodes[i].id;
					if(i<pCodes.length-1){
						pCodes+=",";
					}
					$("#searchCode"+who).val(pCodes);
				}
				$("#searchName"+who).val("已选中"+nodes.length+"个场区");
			}
		}
	};
	$.fn.zTree.init($("#searchTree"+who), setting, nodes);
	var checkCodes=searchParkingLockCode.split(',');
	if(checkCodes!=null&&checkCodes!=''){
		var treeObj = $.fn.zTree.getZTreeObj("searchTree"+who);
		for (var i=0, l=checkCodes.length; i < l; i++) {
			var node = treeObj.getNodeByParam("id", checkCodes[i]);
			if(node!=null)
			treeObj.checkNode(node, true, false);
		}
	}
	
}

//显示条件树
function showSearchZTree(evt,who){
	var cityOffset = $(evt).position();
	$("#searchContent"+who).css({left:cityOffset.left + "px", top:cityOffset.top + $(evt).outerHeight() + "px"}).slideDown("fast");
	$("#searchBody"+who).bind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "searchContent"+who || $(event.target).parents("#searchContent"+who).length>0)) {
			hideSearchZTree(who);
		}
	});	//body绑定鼠标按下事件
}

//隐藏条件树
function hideSearchZTree(who) {
	$("#searchContent"+who).fadeOut("fast");	//隐藏树菜单
	$("#searchBody"+who).unbind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "searchContent"+who || $(event.target).parents("#searchContent"+who).length>0)) {
			hideSearchZTree(who);
		}
	});	//body解除鼠标按下事件
}