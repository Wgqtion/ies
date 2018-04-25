
//控制树展开或关闭
function controlTree(id,flag){
	var tree = $.fn.zTree.getZTreeObj(id);
    tree.expandAll(flag);
}