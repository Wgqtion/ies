<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent" style="padding: 5px">


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
   		$.fn.zTree.init($("#parkingGarageTree"), setting, zNodes);
   	});
	
	//点击时触发
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("parkingGarageTree"),
		nodes = zTree.getSelectedNodes();
		$("#parkingGarageBoxId").attr("href","${ctx}/work/parkinggarage/list?parkingLotId="+nodes[0].id);
		$("#parkingGarageBoxId").click();
	}
</SCRIPT>
<div>
	<%--显示树形结构栏目--%>
	<div layoutH="10" style="float: left; display: block; overflow: auto; width: 17%; border: solid 1px #CCC; line-height: 21px; background: #fff">
		<a class="buttoA" href="${ctx}/work/parkinggarage/list" target='ajax' rel='parkinggarageBox'>查看全部</a>
		<a class="buttoA" href="#" onclick="controlMenuTree('parkingGarageTree',true);">展开</a>
		<a class="buttoA" href="#" onclick="controlMenuTree('parkingGarageTree',false);">关闭</a>
		<a id="parkingGarageBoxId" style="display: none;" href="#" target='ajax' rel='parkinggarageBox'></a>
		<ul id="parkingGarageTree" class="ztree" style="margin-top:0; width:200px;"></ul>
	</div>
	<div id="parkinggarageBox" class="unitBox" style="margin-left: 220px;">
		<%@ include file="/WEB-INF/views/work/parkinggarage/list.jsp"%>
	</div>
</div>
