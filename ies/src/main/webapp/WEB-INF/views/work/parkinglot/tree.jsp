<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<SCRIPT type="text/javascript">
   	
   	$(document).ready(function(){
   		//选择树
   		var setting = {
   			data: {
   				simpleData: {
   					enable: true
   				}
   			},
   			callback: {
   				onClick: onClick	//点击时触发
   			}
   		};
   		var zNodes =[
   	   	   <c:forEach items="${vl}" var="entity" varStatus="index">
   	   	   		{ id:'${entity.id}', pId:'${entity.parent.id}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   	   </c:forEach>
   	   	];
   		$.fn.zTree.init($("#parkingLotTree"), setting, zNodes);
   	});
	
	//点击时触发
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("parkingLotTree"),
		nodes = zTree.getSelectedNodes();
		$("#parkingLotboxId").attr("href","${ctx}/work/parkinglot/list?parentId="+nodes[0].id);
		$("#parkingLotboxId").click();
	}
</SCRIPT>
<div class="pageContent" style="padding: 5px">
	<div>
		<%--显示树形结构栏目--%>
		<div layoutH="10" style="float: left; display: block; overflow: auto; width: 17%; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<a class="buttoA" href="${ctx}/work/parkinglot/list" target='ajax' rel='catalogBox'>查看全部</a>
			<a class="buttoA" href="${ctx}/work/parkinglot/list?search_ISNULL_parentCode=1" target='ajax' rel='catalogBox'>查看根节点</a>
			<a class="buttoA" href="#" onclick="controlMenuTree('parkingLotTree',true);">展开</a>
			<a class="buttoA" href="#" onclick="controlMenuTree('parkingLotTree',false);">关闭</a>
			<a id="parkingLotboxId" style="display: none;" href="#" target='ajax' rel='catalogBox'></a>
			<ul id="parkingLotTree" class="ztree" style="margin-top:0; width:200px;"></ul>
		</div>
		<div id="catalogBox" class="unitBox" style="margin-left: 220px;">
			<%@ include file="/WEB-INF/views/work/parkinglot/list.jsp"%>
		</div>
	</div>
</div>