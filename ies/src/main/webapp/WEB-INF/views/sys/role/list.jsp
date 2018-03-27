<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/role" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>角色名称：</label>
				<input type="text" name="search_LIKE_name" value="${param.search_LIKE_name}"/>
			</li>
		</ul> 
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a rel="sys_role_new"  title="添加角色" class="add" href="${ctx}/sys/role/new" target="dialog"><span>添加</span></a></li>
			<li><a rel="sys_role_update" title="编辑角色" class="edit" href="${ctx}/sys/role/update/{sid}" target="dialog" warn="请选择一条记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/sys/role/delete" class="delete"><span>删除</span></a></li>
			<li><a rel="sys_authority_select" title="分配权限资源" class="icon" href="${ctx}/sys/authority/select/{sid}" target="dialog" warn="请选择一条记录"><span>分配权限资源</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
			    <th width="50" align="center">序号</th>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="150" align="center"  <vsc:orderField name="code"/>>角色编码</th>
				<th width="150" align="center"  <vsc:orderField name="name"/>>角色名称</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td align="center">${index.count}</td>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>
						<a href="${ctx}/sys/role/view/${item.id}" target="dialog" title="查看">${item.code}</a>
					</td>
					<td>
					${item.name}
					</td>
					<td> 
						<a title="删除${item.name}" target="ajaxTodo" href="${ctx}/sys/role/delete/${item.id}" class="btnDel">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>