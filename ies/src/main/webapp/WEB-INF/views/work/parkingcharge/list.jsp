<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingcharge" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>停车场或片区名称:</label> <input type="text" value="${param['search_LIKE_name']}" name="search_LIKE_name" /></li>
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
			<li><a class="edit" href="${ctx}/work/parkingcharge/update/{sid}?navTabId=work_parkingcharge" target="dialog" rel="work_parkingcharge_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingcharge/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="147">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="name"/>>停车片区或停车场</th>
				<th <vsc:orderField name="chargeBinding.chargesSettings.name"/>>收费方案</th>
				<th <vsc:orderField name="chargeBinding.updateDate"/>>更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/parkinglot/view/${varitem.id}" target="dialog" title="查看停车片区" rel="parkinglot_view"> ${varitem.name}</a></td>
					<td>${varitem.chargeBinding.chargesSettings.name}</td>
					<td>${varitem.chargeBinding.updateDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
