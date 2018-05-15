  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/reserveTime" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>周:</label> <select class="combox" name="search_EQ_week" selectedValue="${param['search_EQ_week']}"  dataUrl="${ctx}/static/js/data/week.json">
							<vsc:headoption></vsc:headoption></select></li>
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
			<li><a class="add" title="添加" href="${ctx}/work/reserveTime/new?navTabId=basic_reserveTime" target="dialog" rel="reserveTime_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/reserveTime/update/{sid}?navTabId=basic_reserveTime" target="dialog" rel="reserveTime_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/reserveTime/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 	</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th>场区名称</th>
				<th <vsc:orderField name="week"/>>周</th> 
				<th <vsc:orderField name="startTime"/>>开始时间</th>
				<th <vsc:orderField name="endTime"/>>结束时间</th>   
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td>${varitem.parkingLot.name}</td>
					<td><s:message code="week.${varitem.week}"/></td>   
					<td>${varitem.startTime}</td>  
					<td>${varitem.endTime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
