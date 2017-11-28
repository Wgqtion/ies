<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/resource" method="post">
		<div class="searchBar">
			<ul class="searchContent"> 
				<li><label>资源标识：</label> <input type="text" name="search_LIKE_value" value="${param.search_LIKE_value}" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a rel="sys_resource_new" title="添加资源" class="add" href="${ctx}/sys/resource/new" target="dialog"><span>添加</span></a></li>
			<li><a rel="sys_resource_update" title="编辑资源" class="edit" href="${ctx}/sys/resource/update/{sid}" target="dialog" warn="请选择一条记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/sys/resource/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要重置系统所有URL资源记录吗(将清除所有数据重新创建)?" target="ajaxTodo" href="${ctx}/sys/resource/reset" class="icon"><span>重置</span></a></li>
			<li><a title="确实要重新加载安全框架设置吗?" target="ajaxTodo" href="${ctx}/sys/resource/reloadchain" class="icon"><span>加载权限</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50" align="center">序号</th>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="70" align="center" <vsc:orderField name="resourceType"/>>资源类型</th>
				<th <vsc:orderField name="value"/>>资源标识</th>
				<th width="120" align="center" <vsc:orderField name="position"/>>排序</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td align="center">${index.count}</td>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td align="center">${item.resourceType}</td>
					<td><a href="${ctx}/sys/resource/view/${item.id}" target="dialog" title="查看资源" rel="sys_resource_view">${item.value}</a></td>
					<td>${item.position}</td>
					<td> <a title="删除${item.value}" target="ajaxTodo"
						href="${ctx}/sys/resource/delete/${item.id}" class="btnDel">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
