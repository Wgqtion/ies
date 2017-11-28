<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingorder" method="post">
		<div class="searchBar">
			<ul class="searchContent">

				<li><label>车牌号:</label> <input type="text" value="${param.search_LIKE_inPlateNo}" name="search_LIKE_inPlateNo" /></li>
				<li><label>车辆编号:</label> <input type="text" value="${param.search_EQ_inChargeUser}" name="search_EQ_inChargeUser" /></li>
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_inDoor.parkinglot.name']}" name="search_LIKE_inDoor.parkinglot.name" /></li>
				<li><label>进场时间:</label><input type="text" class="date" size="7" value="${param.search_GTE_inTime}" dateFmt="yyyy-MM-dd" name="search_GTE_inTime" readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_inTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_inTime" readonly="true" /></li>
				<li><label>出场时间:</label> <input type="text" class="date" size="7" value="${param.search_GTE_outTime}" dateFmt="yyyy-MM-dd" name="search_GTE_outTime" readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_outTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_outTime" readonly="true" /></li>

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
			<li><a class="add" title="添加" href="${ctx}/work/parkingorder/new?navTabId=work_parkingorder" target="dialog" rel="parkingorder_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/parkingorder/update/{sid}?navTabId=work_parkingorder" target="dialog" rel="parkingorder_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingorder/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要导出这些记录吗?" target="selectedTodo" rel="ids" href="#" class="excel"><span>导出</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="inPlateNo"/>>车牌号</th>
				<th <vsc:orderField name="inChargeUser"/>>车辆编号</th>
				<th>停车位编号</th>
				<th>停车场</th>
				<th <vsc:orderField name="inTime"/>>进场时间</th>
				<th>进口</th>
				<th <vsc:orderField name="outTime"/>>出场时间</th>
				<th>出口</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/parkingorder/view/${varitem.id}" target="dialog" title="查看" rel="work_parkingorder_view">${varitem.inPlateNo}</a></td>
					<td>${varitem.inChargeUser}</td>
					<td>${varitem.parkingGarage.name}</td>
					<td>${varitem.inDoor.parkinglot.name}</td>
					<td><fmt:formatDate value='${varitem.inTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.inDoor.name}</td>
					<td><fmt:formatDate value='${varitem.outTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.outDoor.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
