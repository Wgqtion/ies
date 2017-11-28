
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkinglockoperationevent" method="post">
		<div class="searchBar">
			<ul class="searchContent">

				<li><label>操作类型:</label> <input type="text" value="${param.search_LIKE_eventType}" name="search_LIKE_eventType" /></li>
				<li><label>来源:</label> <input type="text" value="${param.search_LIKE_sourceType}" name="search_LIKE_sourceType" /></li>
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
			<li><a class="add" title="添加" href="${ctx}/work/parkinglockoperationevent/new?navTabId=work_parkinglockoperationevent" target="dialog" rel="parkinglockoperationevent_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/parkinglockoperationevent/update/{sid}?navTabId=work_parkinglockoperationevent" target="dialog" rel="parkinglockoperationevent_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkinglockoperationevent/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="eventType"/>>操作类型</th>
				<th <vsc:orderField name="sourceType"/>>来源</th>
				<th>地锁编号</th>
				<th <vsc:orderField name="reportedTime"/>>操作时间</th>
				<th <vsc:orderField name="resultType"/>>操作结果</th>
				<th>操作人</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/parkinglockoperationevent/view/${varitem.id}" target="dialog" title="查看操作日志" rel="parkinglockoperationevent_view"><s:message code="parkinglockoperationevent.eventtype.${varitem.eventType}"/></a></td>
					<td><s:message code="parkinglockoperationevent.sourcetype.${varitem.sourceType}"/></td>
					<td><a href="${ctx}/work/parkinglock/view/${varitem.parkingLock.id}" target="dialog" title="查看地锁"> ${varitem.parkingLock.lockNum} </a></td>
					<td><fmt:formatDate value='${ varitem.reportedTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.resultType}</td>
					<td>${varitem.user.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
