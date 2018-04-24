<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent" style="padding: 5px">
	<div>
		<%--显示树形结构栏目--%>
		<div layoutH="10" style="float: left; display: block; overflow: auto; width: 17%; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<ul class="tree treeFolder expand">
			<a style="margin-left: 5px;" href="${ctx}/work/parkinglot/list" target='ajax' rel='catalogBox'>全部</a>
			 <c:forEach items="${vl}" var="root">
				<li><a title='点击查看场区' href='${ctx}/work/parkinglot/list?parentId=${root.id}' target='ajax' rel='catalogBox'>${root.name}</a>
				<vsc:tree className="tree treeFolder expand" root="${root.children}" isRoot="false" childFieldName="children" idFieldName="id" nameFieldName="name" pidFieldName="parentCode"
				noteTemplet="<a title='点击查看 {name}子场区' href='${ctx}/work/parkinglot/list?parentId={id}' target='ajax' rel='catalogBox'>{name}</a>">
				</vsc:tree>
				</li>
			</c:forEach>
			</ul>
		</div>
		<div id="catalogBox" class="unitBox" style="margin-left: 220px;">
			<%@ include file="/WEB-INF/views/work/parkinglot/list.jsp"%>
		</div>
	</div>
</div>