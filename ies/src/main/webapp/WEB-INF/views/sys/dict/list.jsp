<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/dict" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>项目类型：</label>
				<input class="ac_input" name="search_LIKE_type" type="text" value="${search_LIKE_type}" options="{formatItem:formatItem,itemParam:['type']}" dataUrl="${ctx}/sys/dict/typelist"/>
			</li>
			<li>
				<label>项目名称：</label>
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
			<li><a class="add" href="${ctx}/sys/dict/new?navTabId=sys_dict" target="dialog" rel="dict_new" title="添加数据字典"><span>添加</span></a></li>
			<li><a class="edit" href="${ctx}/sys/dict/update/{sid}?navTabId=sys_dict" target="dialog" warn="请选择一条记录" rel="dict_edit" title="编辑数据字典"><span>修改</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/sys/dict/delete" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="70" <vsc:orderField name="type"/>>项目类型</th>
				<th width="120" <vsc:orderField name="name"/>>项目名称</th>
				<th width="120" <vsc:orderField name="code"/>>项目代码</th>
				<th width="120" <vsc:orderField name="description"/>>备注</th>
				<th width="50" align="center" <vsc:orderField name="sort"/>>排序</th> 
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>${item.type}</td>
					<td>${item.name}</td>
					<td>${item.code}</td>
					<td>${item.description}</td>
					<td>${item.sort}</td>
					<td>
					<a title="删除${item.name}" target="ajaxTodo" href="${ctx}/sys/dict/delete/${item.id}" class="btnDel">删除</a> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
