  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingpassages" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>进出口名称:</label> 
					<input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /> </li>   
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
			<li><a class="add" title="添加" href="${ctx}/work/parkingpassages/new?navTabId=parking_passages" target="dialog" rel="parkingpassages_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/parkingpassages/update/{sid}?navTabId=parking_passages" target="dialog" rel="parkingpassages_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingpassages/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 	</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th>场区名称</th>   
				<th <vsc:orderField name="name"/>>进出口名称</th>
				<th <vsc:orderField name="itudeLong"/>>纬度坐标</th>   
				<th <vsc:orderField name="itudeLat"/>>经度坐标</th>   
				<th <vsc:orderField name="isEnabled"/>>状态</th>   
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td>${varitem.parkingLot.name}</td>      
					<td><a href="${ctx}/work/parkingpassages/view/${varitem.id}" target="dialog" title="查看"> ${varitem.name}</a></td>
					<td>${varitem.itudeLong}</td>   
					<td>${varitem.itudeLat}</td>   
					<td><s:message code="parkingpassages.isenabled.${varitem.isEnabled}"/></td>   
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
