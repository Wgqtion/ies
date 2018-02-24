<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkinglockeventlog" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>事件类型:</label> <input type="text" value="${param.search_EQ_eventType}" name="search_EQ_eventType" /></li>
				<li><label>地锁编号:</label> <input type="text" value="${param.search_LIKE_lockNum}" name="search_LIKE_lockNum" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th <vsc:orderField name="eventType"/>>事件类型</th>				
				<th>地锁编号</th>
				<th>区域编号</th>
				<th>设备状态和电量</th>
				<th <vsc:orderField name="reportedTime"/>>上报时间</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>
				<th <vsc:orderField name="message"/>>事件说明</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><a href="${ctx}/work/parkinglockeventlog/view/${varitem.id}" target="dialog" title="查看事件日志" rel="parkinglockeventlog_view"> ${varitem.eventType} </a></td>
					<td><a href="${ctx}/work/parkingLock/view/${varitem.parkingLock.id}" target="dialog" title="查看地锁">${varitem.parkingLock.lockNum}</a></td>
					<td>${varitem.parkingLock.ipAddress}</td>
					<td>${varitem.state}</td>
					<td><fmt:formatDate value='${ varitem.reportedTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td><fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.message}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
