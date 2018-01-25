<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="hids"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/work/parkinggarage/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_parkingLotArea.parkingLot.name']}" name="search_LIKE_parkingLotArea.parkingLot.name" /></li>
				<li><label>停车片区:</label> <input type="text" value="${param['search_LIKE_parkingLotArea.name']}" name="search_LIKE_parkingLotArea.name" /></li>
				<li><label>车位编号:</label> <input type="text" value="${param.search_LIKE_code}" name="search_LIKE_code" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" onclick="javascript:$.bringBack({id:'',name:''})">清空</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" layoutH="108" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30" align="center"></th>
				<th <vsc:orderField name="name"/>>停车位编号</th>				
				<th>停车片区</th><th>停车场</th>
				<th <vsc:orderField name="isEnabled"/>>状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<td><a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', name:'${item.name}'})" class="btnSelect">选择</a></td>
					 
					<td><a href="${ctx}/work/parkinggarage/view/${item.id}" target="dialog" title="查看停车位" rel="parkinggarage_view">${item.name}</a></td>
					<td>${item.parkingLotArea.name}</td>
					<td>${item.parkingLotArea.parkingLot.name}</td>
					<td><s:message code="parkinggarage.isenabled.${item.isEnabled}"/></td>			
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
	</div>
</div>
