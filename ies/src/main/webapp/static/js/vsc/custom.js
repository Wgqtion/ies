
//控制树展开或关闭
function controlTree(id,flag){
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
function showMenu(evt,contentId) {
	var cityOffset = $(evt).position();
	$("#select"+contentId+"Content").css({left:cityOffset.left + "px", top:cityOffset.top + $(evt).outerHeight() + "px"}).slideDown("fast");
	$("#content"+contentId).bind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "select"+contentId+"Content" || $(event.target).parents("#select"+contentId+"Content").length>0)) {
			hideMenu(contentId);
		}
	});	//body绑定鼠标按下事件
}

//隐藏树菜单
function hideMenu(contentId) {
	$("#select"+contentId+"Content").fadeOut("fast");	//隐藏树菜单
	$("#content"+contentId).unbind("mousedown",function onBodyDown(event) {
		if (!(event.target.id == "select"+contentId+"Content" || $(event.target).parents("#select"+contentId+"Content").length>0)) {
			hideMenu(contentId);
		}
	});	//body解除鼠标按下事件
}

//清空树
function clearBtn(id){
	$("#"+id+"Id").val('');
	$("#"+id+"Name").val('');
}
