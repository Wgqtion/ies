  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkinggaragecarnolog" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>车位编号:</label> 
					<input type="text" value="${param.search_LIKE_parkingName}" name="search_LIKE_parkingName" /> </li>   
				<li><label>相机IP:</label> 
					<input type="text" value="${param.search_LIKE_cameraIp}" name="search_LIKE_cameraIp" /> </li>      
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
			<!-- 
			<li><a class="add" title="添加" href="${ctx}/work/parkinggaragecarnolog/new?navTabId=work_parkinggaragecarnolog" target="dialog" rel="parkinggaragecarnolog_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/parkinggaragecarnolog/update/{sid}?navTabId=work_parkinggaragecarnolog" target="dialog" rel="parkinggaragecarnolog_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			 -->
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkinggaragecarnolog/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<!-- <li><a class="icon" href="${ctx}/work/parkinggaragecarnolog/export" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th <vsc:orderField name="parkingName"/>>车位编号</th>   
				<th <vsc:orderField name="cameraIp"/>>相机IP</th>   
				<th <vsc:orderField name="status"/>>状态</th>   
				<th <vsc:orderField name="carNo"/>>车牌号</th>   
				<th <vsc:orderField name="intime"/>>停入时间</th>   
				<th <vsc:orderField name="createTime"/>>创建时间</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<!-- 
					<td> <a href="${ctx}/work/parkinggaragecarnolog/view/${varitem.id}" target="dialog" title="查看"> ${varitem.parkingName} </a></td>   
					<td> <a href="${ctx}/work/parkinggaragecarnolog/view/${varitem.id}" target="dialog" title="查看"> ${varitem.cameraIp} </a></td>   
					<td> <a href="${ctx}/work/parkinggaragecarnolog/view/${varitem.id}" target="dialog" title="查看"> ${varitem.status} </a></td>   
					<td> <a href="${ctx}/work/parkinggaragecarnolog/view/${varitem.id}" target="dialog" title="查看"> ${varitem.carNo} </a> </td> --> 
					<td>${varitem.parkingName}</td> 
					<td>${varitem.cameraIp}</td>			
					<td><s:message code="parkinggaragecarnolog.status.${varitem.status}"/></td> 
					<td>${varitem.carNo}</td> 
					<td><fmt:formatDate value='${varitem.intime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>   
					<td><fmt:formatDate value='${varitem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>  
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
