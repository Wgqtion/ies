<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent" style="padding: 5px">
	<div>
		<%--显示树形结构栏目--%>
		<div layoutH="10" style="float: left; display: block; overflow: auto; width: 215px; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<ul class="tree treeFolder expand">
			 <c:forEach items="${vl}" var="root">
				
				<li ${(empty param['search_EQ_parent']) ? "class='selected'" : ""}><a title='点击查看停车场停车区' href='${ctx}/work/parkinggarage/list?search_EQ_parkingLotArea.parkingLot=${root.id}' target='ajax' rel='parkinggarageBox'>${root.name}</a>
				 
				<vsc:tree className="tree treeFolder expand" root="${root.rootParkingLotAreas}" isRoot="false" curSelectid="${param['search_EQ_parent'] }" childFieldName="children" idFieldName="id" nameFieldName="name" pidFieldName="parent"
				noteTemplet="<a title='点击查看 {name} 子区域' href='${ctx}/work/parkinggarage/list?parkingLotAreaFullName={id}' target='ajax' rel='parkinggarageBox'>{name}</a>">
				</vsc:tree>
				 
				</li>
				</c:forEach>
			</ul>
		</div>
		<div id="parkinggarageBox" class="unitBox" style="margin-left: 220px;">
			<%@ include file="/WEB-INF/views/work/parkinggarage/list.jsp"%>
		</div>
	</div>
</div>