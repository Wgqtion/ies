
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/campus" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>校区名称:</label> <input type="text" value="${param['search_LIKE_parkingLot.name']}" name="search_LIKE_parkingLot.name" /></li>

				<li><label>校门名称:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
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
			<li><a class="add" title="添加校门" href="${ctx}/work/campus/new?navTabId=work_parkingcampus" target="dialog" rel="work_campus_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑校门" href="${ctx}/work/campus/update/{sid}?navTabId=work_parkingcampus" target="dialog" rel="work_campus_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/campus/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="name"/>>校门名称</th>
				<th>所属校区</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/campus/view/${varitem.id}" target="dialog" title="查看校门" rel="work_campus_new"> ${varitem.name} </a></td>
					<td><a href="${ctx}/work/parkinglot/view/${varitem.parkingLot.id}" target="dialog" title="查看校区" rel="parkinglot_view"> ${varitem.parkingLot.name} </a></td>

					
					<td><fmt:formatDate value='${varitem.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
