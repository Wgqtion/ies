
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkinglockoperationevent" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>操作类型:</label> 
					<select class="combox" name="search_EQ_eventType" selectedValue="${param['search_EQ_eventType']}"  dataUrl="${ctx}/static/js/data/parkinglockoperationevent_eventType.json">
						  <vsc:headoption></vsc:headoption>
					</select>
				</li>
				<li><label>来源:</label> 
					<select class="combox" name="search_EQ_sourceType" selectedValue="${param['search_EQ_sourceType']}"  dataUrl="${ctx}/static/js/data/parkinglockoperationevent_sourceType.json">
						  <vsc:headoption></vsc:headoption>
					</select>
				</li>
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
				<th>区域编号</th>
				<th>地锁编号</th>
				<th <vsc:orderField name="eventType"/>>操作类型</th>
				<th <vsc:orderField name="sourceType"/>>来源</th>
				<th>操作人</th>
				<th <vsc:orderField name="createTime"/>>操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td> ${varitem.parkingLock.ipAddress} </td>
					<td><a href="${ctx}/work/parkinglock/view/${varitem.parkingLock.id}" target="dialog" title="查看地锁"> ${varitem.parkingLock.lockNum} </a></td>
					<td><s:message code="parkinglockoperationevent.eventtype.${varitem.eventType}"/></a></td>
					<td><s:message code="parkinglockoperationevent.sourcetype.${varitem.sourceType}"/></td>
					<td><c:if test="${varitem.sourceType eq 1}">${varitem.user.name}</c:if><c:if test="${varitem.sourceType eq 2}">${varitem.wxUser.name}</c:if></td>
					<td><fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
