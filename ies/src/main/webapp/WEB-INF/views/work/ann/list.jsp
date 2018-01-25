<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/ann" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>标题:</label> <input type="text" value="${param.search_LIKE_title}" name="search_LIKE_title" /></li>
				<li><label>创建时间:</label> <input type="text" class="date" size="9" value="${param.search_GTE_createTime}" dateFmt="yyyy-MM-dd" name="search_GTE_createTime" readonly="true" />
					- <input type="text" class="date" size="9" value="${param.search_LTE_createTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_createTime" readonly="true" /></li>
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
			<li><a class="add" title="添加公告" href="${ctx}/work/ann/new?navTabId=work_ann" target="dialog" rel="work_ann_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑公告" href="${ctx}/work/ann/update/{sid}?navTabId=work_ann" target="dialog" rel="work_ann_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/ann/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要撤销这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/ann/destroy" class="reset"><span>撤销</span></a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="title"/>>标题</th>
				<th <vsc:orderField name="createUser"/>>创建人</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>
				<th <vsc:orderField name="state"/>>状态</th>
				<th width="200">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id}" type="checkbox"></td>
					<td><a href="${ctx}/work/ann/view/${varitem.id}" target="dialog" title="查看公告"> ${varitem.title} </a></td>
					<td><a href="${ctx}/work/ann/view/${varitem.id}" target="dialog" title="查看公告"> ${varitem.createUser.name} </a></td>
					<td><fmt:formatDate value='${varitem.createTime}' pattern='yyyy-MM-dd' /></td>
					<td><a href="${ctx}/work/ann/view/${varitem.id}" target="dialog" title="查看公告"> <s:message code="ann.state.${varitem.state}" /></a></td>
					<td><a title="编辑" target="dialog" ref="work_ann_update" href="${ctx}/work/ann/update/${varitem.id}?navTabId=work_ann" class="btnEdit">编辑</a> <a title="删除" target="ajaxTodo"
						href="${ctx}/work/ann/delete/${varitem.id}" class="btnDel">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
