  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingOvertime" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>超时分钟:</label> <input type="text" value="${param.search_EQ_overtimeMin}" name="search_EQ_overtimeMin"  /></li>
				<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">检索</button>
					</div>
				</div></li>   
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加" href="${ctx}/work/parkingOvertime/new?navTabId=basic_parkingOvertime" target="dialog" rel="parkingOvertime_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/parkingOvertime/update/{sid}?navTabId=basic_parkingOvertime" target="dialog" rel="parkingOvertime_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingOvertime/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 	</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>场区名称</th>
				<th <vsc:orderField name="overtimeMin"/>>超时分钟</th>
				<th <vsc:orderField name="appendFee"/>>追加费</th>   
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td>${varitem.parkingLot.name}</td>
					<td>${varitem.overtimeMin}</td>
					<td>${varitem.appendFee}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
