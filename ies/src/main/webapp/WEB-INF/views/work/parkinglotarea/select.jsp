<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<c:set var="onClickTemplet" value="$.bringBack({id:'{id}', name:'{name}',pname:''})"></c:set>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<ul class="tree treeFolder expand">
			<c:forEach items="${vl}" var="root">
				<li ${(empty param['search_EQ_parent']) ? "class='selected'" : ""}><a>${root.name}</a> <vsc:tree className="tree treeFolder expand" root="${root.rootParkingLotAreas}" isRoot="false" curSelectid="${param['search_EQ_parent'] }"
					noteTemplet="<a  title=\"{name}\" onclick=\"javascript:{onclick}\" >{name}</a>" childFieldName="children" idFieldName="id" nameFieldName="name" pidFieldName="parent" onClickTemplet="${onClickTemplet}">
					</vsc:tree></li>
			</c:forEach>
		</ul>

	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" onclick="javascript:$.bringBack({id:'', name:'',pname:''})">清空</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>


