
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/ann/send" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>公告标题:</label> <input type="text" value="${param['search_LIKE_announcement.id']}" name="search_LIKE_announcement.id" /></li>
				<li><label>发布时间:</label> <input type="text" class="date" size="9" value="${param.search_GTE_sendDate}" dateFmt="yyyy-MM-dd" name="search_GTE_sendDate" readonly="true" />-
					<input type="text" class="date" size="9" value="${param.search_LTE_sendDate}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_sendDate" readonly="true" /></li>

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
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="announcement"/>>标题</th>
				<th <vsc:orderField name="sendDate"/>>发送时间</th>
				<th <vsc:orderField name="isRead"/>>已阅读</th>
				<th <vsc:orderField name="readTime"/>>阅读时间</th>
				<th width="200">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/ann/send/view/${varitem.id}" target="dialog" title="查看公告"> ${varitem.announcement.title} </a></td>
					<td><fmt:formatDate value='${ varitem.sendDate}' pattern='yyyy-MM-dd' /></td>
					<td><a href="${ctx}/work/ann/send/view/${varitem.id}" target="dialog" title="查看公告"><s:message code="annsend.isread.${varitem.isRead}"></s:message></a></td>
					<td><fmt:formatDate value='${ varitem.readTime}' pattern='yyyy-MM-dd' /></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
